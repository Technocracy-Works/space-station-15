package org.technocracy.spacestation.registry.items;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.technocracy.spacestation.SpaceStation;

public final class DrinkItems {

    public static class DrinkableItem extends Item {
        private final Item remainderItem;

        public DrinkableItem(Settings settings) {
            this(settings, Items.GLASS_BOTTLE);
        }

        public DrinkableItem(Settings settings, Item remainderItem) {
            super(settings);
            this.remainderItem = remainderItem;
        }

        @Override
        public UseAction getUseAction(ItemStack stack) {
            return UseAction.DRINK;
        }

        @Override
        public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
            ItemStack result = super.finishUsing(stack, world, user);
            // Креатив без депа бутылок
            if (user instanceof PlayerEntity player && player.getAbilities().creativeMode) {
                return result;
            }
            if (!world.isClient() && remainderItem != null) {
                ItemStack remainder = new ItemStack(remainderItem);
                if (user instanceof PlayerEntity player) {
                    if (!player.getInventory().insertStack(remainder)) {
                        player.dropItem(remainder, false);
                    }
                } else {
                    user.dropStack(remainder);
                }
            }
            return result;
        }
    }

    public static final Item ENZYME = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "enzyme"),
            new DrinkableItem(new Item.Settings()
                    .food(new FoodComponent.Builder()
                            .alwaysEdible()
                            .nutrition(2).saturationModifier(0.3f)
                            .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 30, 4), 1.0f)
                            .build())
                    .recipeRemainder(Items.GLASS_BOTTLE)
                    .maxCount(16))
    );

    private DrinkItems() {}

    public static void register() {}
}