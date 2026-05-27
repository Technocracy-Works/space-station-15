package org.corvaxcraft.spacestation.chemistry;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.registry.Registries;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class ChemMasterBlockEntity extends BlockEntity implements Inventory, ExtendedScreenHandlerFactory<BlockPos> {

    public final DefaultedList<ItemStack> slots = DefaultedList.ofSize(2, ItemStack.EMPTY);
    private final Map<String, Double> masterChemicals = new HashMap<>();

    private int grindTicker = 0;
    private static final int GRIND_INTERVAL = 10;

    public ChemMasterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CHEM_MASTER, pos, state);
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) return;

        grindTicker++;
        if (grindTicker < GRIND_INTERVAL) return;
        grindTicker = 0;

        ItemStack grindSlot = slots.get(0);
        if (grindSlot.isEmpty()) return;

        // Специальный случай: вода в бутылке/ведре даёт H/O и возвращает пустую тару
        if (tryExtractFromWaterContainer(grindSlot, world, pos, state)) {
            return;
        }

        Identifier itemId = Registries.ITEM.getId(grindSlot.getItem());
        Optional<ChemRegistry.GrindingRecipe> recipe = ChemRegistry.getGrinding(itemId);

        recipe.ifPresent(r -> {
            r.results().forEach((chem, amount) ->
                    masterChemicals.merge(chem, amount, (a, b) ->
                            Math.round((a + b) * 100.0) / 100.0)
            );

            grindSlot.decrement(1);
            markDirty();
            syncToClients(world, pos, state);
        });
    }

    private boolean tryExtractFromWaterContainer(ItemStack grindSlot, World world, BlockPos pos, BlockState state) {
        if (grindSlot.isOf(Items.WATER_BUCKET)) {
            masterChemicals.merge("water", 1.0, (a, b) -> Math.round((a + b) * 100.0) / 100.0);
            masterChemicals.merge("hydrogen", 2.0, (a, b) -> Math.round((a + b) * 100.0) / 100.0);
            masterChemicals.merge("oxygen", 1.0, (a, b) -> Math.round((a + b) * 100.0) / 100.0);
            slots.set(0, new ItemStack(Items.BUCKET));
            markDirty();
            syncToClients(world, pos, state);
            return true;
        }

        if (grindSlot.isOf(Items.POTION)) {
            // В 1.21.1 water bottle это тоже potion-item; для простоты считаем любой potion водой.
            // При желании можно сузить до проверки компонента зелья.
            masterChemicals.merge("water", 0.5, (a, b) -> Math.round((a + b) * 100.0) / 100.0);
            masterChemicals.merge("hydrogen", 1.0, (a, b) -> Math.round((a + b) * 100.0) / 100.0);
            masterChemicals.merge("oxygen", 0.5, (a, b) -> Math.round((a + b) * 100.0) / 100.0);
            slots.set(0, new ItemStack(Items.GLASS_BOTTLE));
            markDirty();
            syncToClients(world, pos, state);
            return true;
        }

        return false;
    }

    public void moveToContainer(String chem, double amount) {
        ItemStack containerSlot = slots.get(1);
        if (containerSlot.isEmpty()) return;

        ChemData data = containerSlot.get(ModComponents.CHEM_DATA);
        if (data == null) return;

        double available = masterChemicals.getOrDefault(chem, 0.0);
        double toMove = Math.min(amount, Math.min(available, data.freeSpace()));
        if (toMove < 0.01) return;

        ChemData updated = data.add(chem, toMove);
        containerSlot.set(ModComponents.CHEM_DATA, updated);

        double remaining = Math.round((available - toMove) * 100.0) / 100.0;
        if (remaining <= 0.0) masterChemicals.remove(chem);
        else masterChemicals.put(chem, remaining);

        markDirty();
        syncToClients(world, pos, getCachedState());
    }

    public void moveToMaster(String chem, double amount) {
        ItemStack containerSlot = slots.get(1);
        if (containerSlot.isEmpty()) return;

        ChemData data = containerSlot.get(ModComponents.CHEM_DATA);
        if (data == null || !data.has(chem, amount)) return;

        ChemData updated = data.remove(chem, amount);
        containerSlot.set(ModComponents.CHEM_DATA, updated);

        masterChemicals.merge(chem, amount, (a, b) ->
                Math.round((a + b) * 100.0) / 100.0);

        markDirty();
        syncToClients(world, pos, getCachedState());
    }

    public Map<String, Double> getMasterChemicals() {
        return masterChemicals;
    }

    public ItemStack getContainerStack() {
        return slots.get(1);
    }

    @Override
    public int size() {
        return slots.size();
    }

    @Override
    public boolean isEmpty() {
        for (ItemStack stack : slots) {
            if (!stack.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public ItemStack getStack(int slot) {
        return slots.get(slot);
    }

    @Override
    public ItemStack removeStack(int slot, int amount) {
        ItemStack result = Inventories.splitStack(slots, slot, amount);
        if (!result.isEmpty()) markDirty();
        return result;
    }

    @Override
    public ItemStack removeStack(int slot) {
        ItemStack result = Inventories.removeStack(slots, slot);
        if (!result.isEmpty()) markDirty();
        return result;
    }

    @Override
    public void setStack(int slot, ItemStack stack) {
        slots.set(slot, stack);
        if (stack.getCount() > getMaxCountPerStack()) {
            stack.setCount(getMaxCountPerStack());
        }
        markDirty();
    }

    @Override
    public void clear() {
        for (int i = 0; i < slots.size(); i++) {
            slots.set(i, ItemStack.EMPTY);
        }
        markDirty();
    }

    @Override
    public boolean canPlayerUse(PlayerEntity player) {
        return Inventory.canPlayerUse(this, player);
    }

    @Override
    protected void writeNbt(NbtCompound nbt, net.minecraft.registry.RegistryWrapper.WrapperLookup lookup) {
        super.writeNbt(nbt, lookup);

        NbtList slotList = new NbtList();
        for (int i = 0; i < slots.size(); i++) {
            ItemStack stack = slots.get(i);
            if (!stack.isEmpty()) {
                NbtCompound slotNbt = new NbtCompound();
                slotNbt.putInt("Slot", i);
                slotNbt.put("Item", stack.encode(lookup));
                slotList.add(slotNbt);
            }
        }
        nbt.put("Slots", slotList);

        NbtCompound chemNbt = new NbtCompound();
        masterChemicals.forEach(chemNbt::putDouble);
        nbt.put("MasterChemicals", chemNbt);
    }

    @Override
    protected void readNbt(NbtCompound nbt, net.minecraft.registry.RegistryWrapper.WrapperLookup lookup) {
        super.readNbt(nbt, lookup);

        for (int i = 0; i < slots.size(); i++) {
            slots.set(i, ItemStack.EMPTY);
        }

        NbtList slotList = nbt.getList("Slots", NbtElement.COMPOUND_TYPE);
        for (int i = 0; i < slotList.size(); i++) {
            NbtCompound slotNbt = slotList.getCompound(i);
            int slot = slotNbt.getInt("Slot");
            if (slot >= 0 && slot < slots.size()) {
                slots.set(slot, ItemStack.fromNbt(lookup, slotNbt.getCompound("Item")).orElse(ItemStack.EMPTY));
            }
        }

        masterChemicals.clear();
        NbtCompound chemNbt = nbt.getCompound("MasterChemicals");
        for (String key : chemNbt.getKeys()) {
            masterChemicals.put(key, chemNbt.getDouble(key));
        }
    }

    void syncToClients(World world, BlockPos pos, BlockState state) {
        world.updateListeners(pos, state, state, 3);
    }

    @Nullable
    @Override
    public Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt(net.minecraft.registry.RegistryWrapper.WrapperLookup lookup) {
        return createNbt(lookup);
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Chem Master");
    }

    @Override
    public BlockPos getScreenOpeningData(ServerPlayerEntity player) {
        return pos;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ChemMasterScreenHandler(syncId, playerInventory, pos);
    }
}