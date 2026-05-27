package org.corvaxcraft.spacestation.chemistry;

import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.slot.Slot;
import net.minecraft.util.math.BlockPos;

public class ChemMasterScreenHandler extends ScreenHandler {

    public final ChemMasterBlockEntity entity;
    private final PlayerEntity player;

    public ChemMasterScreenHandler(int syncId, PlayerInventory playerInventory, BlockPos pos) {
        super(ModScreenHandlers.CHEM_MASTER, syncId);

        this.player = playerInventory.player;

        BlockEntity be = playerInventory.player.getWorld().getBlockEntity(pos);
        this.entity = be instanceof ChemMasterBlockEntity cm ? cm : null;

        if (this.entity != null) {
            // Grinding input (left) and flask/canister (right), side by side
            this.addSlot(new Slot(this.entity, 0, 24, 39));

            this.addSlot(new Slot(this.entity, 1, 64, 39) {
                @Override
                public boolean canInsert(ItemStack stack) {
                    return stack.getItem() instanceof ChemContainer;
                }
            });
        }

        int playerInvY = 226;
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                this.addSlot(new Slot(playerInventory, col + row * 9 + 9, 8 + col * 18, playerInvY + row * 18));
            }
        }

        for (int col = 0; col < 9; col++) {
            this.addSlot(new Slot(playerInventory, col, 8 + col * 18, playerInvY + 58));
        }
    }

    public void moveToContainer(String chem, double amount) {
        if (player.getWorld().isClient() || entity == null) return;
        entity.moveToContainer(chem, amount);
    }

    public void moveToMaster(String chem, double amount) {
        if (player.getWorld().isClient() || entity == null) return;
        entity.moveToMaster(chem, amount);
    }

    @Override
    public ItemStack quickMove(PlayerEntity player, int slotIndex) {
        ItemStack result = ItemStack.EMPTY;
        Slot slot = this.slots.get(slotIndex);

        if (slot.hasStack()) {
            ItemStack stack = slot.getStack();
            result = stack.copy();

            if (slotIndex < 2) {
                if (!this.insertItem(stack, 2, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else {
                if (stack.getItem() instanceof ChemContainer) {
                    if (!this.insertItem(stack, 1, 2, false)) return ItemStack.EMPTY;
                } else {
                    if (!this.insertItem(stack, 0, 1, false)) return ItemStack.EMPTY;
                }
            }

            if (stack.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
        }

        return result;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return entity != null && entity.canPlayerUse(player);
    }
}