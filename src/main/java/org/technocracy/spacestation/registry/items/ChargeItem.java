package org.technocracy.spacestation.registry.items;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.technocracy.spacestation.registry.components.ChargeData;
import org.technocracy.spacestation.registry.components.ItemToggle;
import org.technocracy.spacestation.registry.components.ModComponents;

public class ChargeItem extends ItemToggle {
    public ChargeItem(Item.Settings settings) {
        super(settings.maxCount(1));
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand)
    {
        return ChargeData.use(world, user, hand, this);
    }

    @Override
    public boolean isItemBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getItemBarStep(ItemStack stack) {
        return ChargeData.getBarStep(stack);
    }

    @Override
    public int getItemBarColor(ItemStack stack) {
        ChargeData chargeData = stack.getOrDefault(ModComponents.CHARGE_COMPONENT, new ChargeData(0F, 100F));
        return chargeData.charge() <= chargeData.maxCharge() / 4F ? 0xff8C00 : 0xffA500;
    }


    @Override
    public void inventoryTick(ItemStack stack, World world, net.minecraft.entity.Entity entity, int slot, boolean selected) {
        ChargeData.inventoryTick(stack, world, entity, slot,selected);
    }

    @Override
    public boolean allowComponentsUpdateAnimation(PlayerEntity player, Hand hand, ItemStack oldStack, ItemStack newStack) {
        return false;
    }

}
