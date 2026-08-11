package org.technocracy.spacestation.block;

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
import org.jetbrains.annotations.Nullable;
import org.technocracy.spacestation.registry.components.ChargeData;
import org.technocracy.spacestation.registry.components.ModComponents;
import org.technocracy.spacestation.registry.components.ToolIngredient;
import org.technocracy.spacestation.registry.components.ToolQuality;
import org.technocracy.spacestation.registry.items.ItemTool;
import org.technocracy.spacestation.registry.items.ToolItems;
import org.technocracy.spacestation.system.ActionTimer;

import javax.tools.Tool;
import java.util.*;

public class AssemblyBlock extends Block {
    public static final Float CHARGE_USED = 20f; // test var

    record Upgrade(Block result, int cost, float assemblyTime, float disassemblyTime, ToolIngredient tools) {}
    // source + material -> upgrade
    private static final Map<Block, Map<ToolIngredient, AssemblyBlock.Upgrade>> ASSEMBLY_REGISTRY = new HashMap<>();
    // assembled block -> source (для разбора)
    private static final Map<Block, AssemblyBlock.Upgrade> DISASSEMBLY_REGISTRY = new HashMap<>();

    public static void registerUpgrade(Block source, Block result,
                                       int cost, float assemblyTime, float disassemblyTime,
                                       ToolIngredient assembly, ToolIngredient disassembly) {
        Upgrade upgrade = new Upgrade(result, cost, assemblyTime, 1f, assembly);

        ASSEMBLY_REGISTRY.computeIfAbsent(source, k -> new HashMap<>())
                .put(assembly, upgrade);

        if (!disassembly.isEmpty()) {
            DISASSEMBLY_REGISTRY.put(result, new Upgrade(source, cost, assemblyTime, disassemblyTime, disassembly));
        }
    }
    public static void registerUpgrade(Block source, Block result,
                                       int cost, float assemblyTime,
                                       ToolIngredient assembly) {
        registerUpgrade(source, result, cost, assemblyTime, 0f, assembly, ToolIngredient.of());
    }

    public AssemblyBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world,
                                             BlockPos pos, PlayerEntity player, Hand hand,
                                             BlockHitResult hit) {
        Item heldItem = stack.getItem();

        boolean canToggle = stack.contains(ModComponents.ITEM_TOGGLE_COMPONENT);
        boolean isActivated = stack.getOrDefault(ModComponents.ITEM_TOGGLE_COMPONENT, true);

        Map<ToolIngredient, Upgrade> upgrades = ASSEMBLY_REGISTRY.get(this);
        Optional<Map.Entry<ToolIngredient, Upgrade>> match = upgrades == null
                ? Optional.empty()
                : upgrades.entrySet().stream()
                .filter(e -> e.getKey().contains(stack))
                .findFirst();
        boolean canAssemble = match.isPresent();

        @Nullable
        Upgrade disassembly = DISASSEMBLY_REGISTRY.get(this);
        boolean canDisassemble = disassembly != null && disassembly.tools().contains(stack);

        if (!canAssemble && !canDisassemble) return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        if (canToggle && !isActivated) return ItemActionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;

        if (world.isClient()) return ItemActionResult.SUCCESS;

        @Nullable
        ChargeData data = stack.get(ModComponents.CHARGE_COMPONENT);
        float speed = heldItem instanceof ItemTool tool ? tool.SPEED : 1f;

        // Assemble
        if (canAssemble) {
            Upgrade upgrade = match.get().getValue();
            if (stack.getCount() < upgrade.cost() && stack.getMaxCount() != 1) return ItemActionResult.FAIL;
            if (ActionTimer.isActive((ServerPlayerEntity) player, pos)) return ItemActionResult.SUCCESS;

            ActionTimer.start((ServerPlayerEntity) player, pos, upgrade.assemblyTime() / speed, false, p -> {
                if (data != null) {
                    stack.set(ModComponents.CHARGE_COMPONENT, data.withCharge(data.charge() - CHARGE_USED));
                }

                if (p.getMainHandStack().getItem() == heldItem &&
                        (p.getMainHandStack().getCount() >= upgrade.cost() || p.getMainHandStack().getMaxCount() == 1)) {
                    world.setBlockState(pos, upgrade.result().getDefaultState());
                    if (!p.getAbilities().creativeMode && data == null) {
                        p.getMainHandStack().decrement(upgrade.cost());
                    }
                    spawnAssemblyEffects(world, pos);
                }
            },
                    p -> {
                        ItemStack curStack = p.getStackInHand(hand);
                        boolean rightTool = upgrade.tools.contains(curStack);
                        boolean isNowActivated = curStack.getOrDefault(ModComponents.ITEM_TOGGLE_COMPONENT, true);
                        boolean hasFuel = !curStack.contains(ModComponents.CHARGE_COMPONENT)
                                || curStack.getOrDefault(ModComponents.CHARGE_COMPONENT, new ChargeData(0f, 100f)).charge() > 0f;
                        return rightTool && isNowActivated && hasFuel;
            });
            return ItemActionResult.SUCCESS;
        }

        // Disassemble
        if (ActionTimer.isActive((ServerPlayerEntity) player, pos)) return ItemActionResult.SUCCESS;

        ActionTimer.start((ServerPlayerEntity) player, pos, disassembly.disassemblyTime() / speed, true, p -> {
            world.setBlockState(pos, disassembly.result().getDefaultState());
            if (data != null) {
                stack.set(ModComponents.CHARGE_COMPONENT, data.withCharge(data.charge() - CHARGE_USED));
            }

            if (!p.getAbilities().creativeMode && data == null) {
                stack.damage(1, p, hand == Hand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND);
            }

            ASSEMBLY_REGISTRY.getOrDefault(disassembly.result(), Map.of())
                    .entrySet().stream()
                    .filter(e -> e.getValue().result().equals(this))
                    .findFirst()
                    .ifPresent(e -> p.dropItem(new ItemStack(e.getKey().needItems().iterator().next(), e.getValue().cost()), false));
            spawnDisassemblyEffects(world, pos);
        },
                p -> {
                    ItemStack curStack = p.getStackInHand(hand);
                    boolean rightTool = disassembly.tools.contains(curStack);
                    boolean isNowActivated = curStack.getOrDefault(ModComponents.ITEM_TOGGLE_COMPONENT, true);
                    boolean hasFuel = !curStack.contains(ModComponents.CHARGE_COMPONENT)
                            || curStack.getOrDefault(ModComponents.CHARGE_COMPONENT, new ChargeData(0f, 100f)).charge() > 0f;
                    return rightTool && isNowActivated && hasFuel;
                });
        return ItemActionResult.SUCCESS;

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
