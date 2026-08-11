package org.technocracy.spacestation.registry.items;

import net.minecraft.component.ComponentMap;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.technocracy.spacestation.registry.components.ChargeData;
import org.technocracy.spacestation.registry.components.ModComponents;
import org.technocracy.spacestation.registry.components.ToolQuality;
import org.technocracy.spacestation.registry.components.Utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ItemTool extends Item {
    public final HashSet<ToolQuality> QUALITIES;
    public final Float SPEED;
    public final SoundEvent SOUND_ON_USE;

    public ItemTool(Item.Settings settings, ToolQuality... qualities) {
        this(settings, new HashSet<>(Set.of(qualities)), 1f, SoundEvents.BLOCK_LEVER_CLICK);
    }

    public ItemTool(Item.Settings settings, Float speed, ToolQuality... qualities) {
        this(settings, new HashSet<>(Set.of(qualities)), speed, SoundEvents.BLOCK_LEVER_CLICK);
    }

    public ItemTool(Item.Settings settings, SoundEvent soundOnUse, ToolQuality... qualities) {
        this(settings, new HashSet<>(Set.of(qualities)), 1f, soundOnUse);
    }

    public ItemTool(Item.Settings settings, Float speed, SoundEvent soundOnUse, ToolQuality... qualities) {
        this(settings, new HashSet<>(Set.of(qualities)), speed, soundOnUse);
    }
    public ItemTool(Item.Settings settings, HashSet<ToolQuality> qualities, Float speed, SoundEvent soundOnUse) {
        super(settings.maxCount(1));
        QUALITIES = qualities;
        SPEED = speed;
        SOUND_ON_USE = soundOnUse;
    }

    public HashSet<ToolQuality> getQualities() {
        return QUALITIES;
    }

    public float getSpeed() {
        return SPEED;
    }

    public SoundEvent getSoundOnUse() {
        return SOUND_ON_USE;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        ItemStack stack = user.getStackInHand(hand);
        ComponentMap allComps = stack.getComponents();
        if (allComps.contains(ModComponents.CHARGE_COMPONENT)) return ChargeData.use(world, user, hand, this);
        return super.use(world, user, hand);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        HashSet<ToolQuality> qualities = getQualities();
        if (qualities.contains(ToolQuality.WELDING)) return Utils.ignite(context);
        if (qualities.contains(ToolQuality.IGNITION)) return Utils.ignite(context);
        return super.useOnBlock(context);
    }



    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        @Nullable
        ChargeData data = stack.get(ModComponents.CHARGE_COMPONENT);
        if (data == null) return super.isItemBarVisible(stack);
        return data.charge() < data.maxCharge();
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        ChargeData data = stack.get(ModComponents.CHARGE_COMPONENT);
        if (data == null) return super.getItemBarStep(stack);
        return Math.clamp(ChargeData.getBarStep(stack), 0, 13);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        ChargeData data = stack.get(ModComponents.CHARGE_COMPONENT);
        if (data == null) return super.getItemBarStep(stack);
        return data.charge() < data.maxCharge() / 4f ? 0xff8C00 : 0xffA500;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, net.minecraft.entity.Entity entity, int slot, boolean selected) {
        ChargeData.inventoryTick(stack, world, entity, slot,selected);
    }

    @Override
    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context, List<Text> tooltip, TooltipType type) {
        if (QUALITIES.isEmpty()) {
            super.appendTooltip(stack, context, tooltip, type);
            return;
        }


        List<String> names = new ArrayList<>();
        for (ToolQuality quality : QUALITIES) {
            names.add(quality.name());
        }
        tooltip.add(Text.translatable("tooltip.spacestation.tool", String.join(", ", names)));

        @Nullable
        ChargeData data = stack.getComponents().get(ModComponents.CHARGE_COMPONENT);
        if (data != null) {
            String curCharge = String.format("%6.2f", data.charge());
            String maxCharge = String.format("%-6.2f", data.maxCharge());
            Text text = Text.literal(curCharge + "/" + maxCharge).styled(style -> style.withColor(data.charge() < data.maxCharge() / 4f ? 0xff8C00 : 0xffA500));
            tooltip.add(Text.translatable("tooltip.spacestation.charge", text));
        }
    }
}
