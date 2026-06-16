package org.corvaxcraft.spacestation;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.entity.EquipmentSlot;

import java.util.*;

public class AssemblyBlock extends Block {

    record Upgrade(Block result, int cost, float assemblyTime, float disassemblyTime, Set<Item> disassemblyTools) {} {}

    // source + material -> upgrade
    private static final Map<Block, Map<Item, Upgrade>> ASSEMBLY_REGISTRY = new HashMap<>();
    // assembled block -> source (для разбора)
    private static final Map<Block, Upgrade> DISASSEMBLY_REGISTRY = new HashMap<>();

    public static void registerUpgrade(Block source, Item material, Block result,
                                       int cost, float assemblyTime, float disassemblyTime,
                                       Item... tools) {
        Set<Item> toolSet = tools.length > 0 ? Set.of(tools) : Set.of();
        Upgrade upgrade = new Upgrade(result, cost, assemblyTime, disassemblyTime, toolSet);

        ASSEMBLY_REGISTRY.computeIfAbsent(source, k -> new HashMap<>())
                .put(material, upgrade);

        if (!toolSet.isEmpty()) {
            DISASSEMBLY_REGISTRY.put(result, new Upgrade(source, cost, assemblyTime, disassemblyTime, toolSet));
        }
    }

    public AssemblyBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world,
                                             BlockPos pos, PlayerEntity player, Hand hand,
                                             BlockHitResult hit) {
        Item heldItem = stack.getItem();

        // Проверяем есть ли вообще что делать с этим предметом
        Map<Item, Upgrade> upgrades = ASSEMBLY_REGISTRY.get(this);
        boolean canAssemble = upgrades != null && upgrades.containsKey(heldItem);

        Upgrade disassembly = DISASSEMBLY_REGISTRY.get(this);
        boolean canDisassemble = disassembly != null && disassembly.disassemblyTools().contains(heldItem);

        if (!canAssemble && !canDisassemble) return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        if (world.isClient()) return ItemActionResult.SUCCESS;

// Сборка
        if (canAssemble) {
            Upgrade upgrade = upgrades.get(heldItem);
            if (stack.getCount() < upgrade.cost()) return ItemActionResult.FAIL;
            if (ActionTimer.isActive((ServerPlayerEntity) player, pos)) return ItemActionResult.SUCCESS;

            ActionTimer.start((ServerPlayerEntity) player, pos, upgrade.assemblyTime(), false, p -> {
                if (p.getMainHandStack().getItem() == heldItem &&
                        p.getMainHandStack().getCount() >= upgrade.cost()) {
                    world.setBlockState(pos, upgrade.result().getDefaultState());
                    if (!p.getAbilities().creativeMode) {
                        p.getMainHandStack().decrement(upgrade.cost());
                    }
                    spawnAssemblyEffects(world, pos);
                }
            });
            return ItemActionResult.SUCCESS;
        }

// Разбор
        if (canDisassemble) {
            if (ActionTimer.isActive((ServerPlayerEntity) player, pos)) return ItemActionResult.SUCCESS;

            ActionTimer.start((ServerPlayerEntity) player, pos, disassembly.disassemblyTime(), true, p -> {
                world.setBlockState(pos, disassembly.result().getDefaultState());
                if (!p.getAbilities().creativeMode) {
                    stack.damage(1, p, hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
                }
                ASSEMBLY_REGISTRY.getOrDefault(disassembly.result(), Map.of())
                        .entrySet().stream()
                        .filter(e -> e.getValue().result().equals(this))
                        .findFirst()
                        .ifPresent(e -> p.dropItem(new ItemStack(e.getKey(), e.getValue().cost()), false));
                spawnDisassemblyEffects(world, pos);
            });
            return ItemActionResult.SUCCESS;
        }

        return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
    }

    private void spawnAssemblyEffects(World world, BlockPos pos) {
        double x = pos.getX() + 0.5, y = pos.getY() + 0.5, z = pos.getZ() + 0.5;
        ServerWorld sw = (ServerWorld) world;
        sw.spawnParticles(ParticleTypes.ELECTRIC_SPARK, x, y, z, 1, 0.2, 0.2, 0.2, 0.05);
        sw.spawnParticles(ParticleTypes.SMOKE, x, y + 0.3, z, 8, 0.1, 0.1, 0.1, 0.02);
        sw.spawnParticles(ParticleTypes.ASH, x, y, z, 20, 10, 0.2, 0.2, 0);
        world.playSound(null, pos, SoundEvents.ITEM_AXE_SCRAPE, SoundCategory.BLOCKS, 1.0f, 1.0f);
    }

    private void spawnDisassemblyEffects(World world, BlockPos pos) {
        double x = pos.getX() + 0.5, y = pos.getY() + 0.5, z = pos.getZ() + 0.5;
        ServerWorld sw = (ServerWorld) world;
        sw.spawnParticles(ParticleTypes.CRIT, x, y, z, 10, 0.2, 0.2, 0.2, 0.05);
        sw.spawnParticles(ParticleTypes.SMOKE, x, y + 0.3, z, 5, 0.1, 0.1, 0.1, 0.02);
        world.playSound(null, pos, SoundEvents.BLOCK_GRINDSTONE_USE, SoundCategory.BLOCKS, 1.0f, 0.8f);
    }
}