package org.technocracy.spacestation.registry.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;

public final class FoodItems {

    public static final Item BANANIUM = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bananium"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.3f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 30, 4), 1.0f)
                    .build()))
    );

    public static final Item BOWL_BIG =     Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bowl_big"),
            new Item(new Item.Settings()));


    public static final Item BUN = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bun"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2)
                    .saturationModifier(0.2f)
                    .build()))
    );

    public static final Item BUN_BOTTOM = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bun_bottom"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(1)
                    .saturationModifier(0.1f)
                    .build()))
    );

    public static final Item BUN_TOP = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bun_top"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(1)
                    .saturationModifier(0.1f)
                    .build()))
    );

    public static final Item BURGER = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "burger"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(8).saturationModifier(0.8f).build()))
    );

    public static final Item BURGER_CHEESE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "burger_cheese"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(9).saturationModifier(0.9f).build()))
    );

    public static final Item CUTLET_RAW = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cutlet_raw"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(0.1f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 220, 0), 0.2f)
                    .build()))
    );

    public static final Item CUTLET_COOKED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cutlet_cooked"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4).saturationModifier(0.8f).build()))
    );

    public static final Item CROISSANT = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "croissant"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(5).saturationModifier(0.7f).build()))
    );

    public static final Item CROISSANT_RAW = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "croissant_raw"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.3f).build()))
    );

    public static final Item MEATBALL = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "meatball"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.1f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.POISON, 220, 0), 0.2f)
                    .build()))
    );

    public static final Item MEATBALL_COOKED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "meatball_cooked"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4).saturationModifier(0.8f).build()))
    );

    public static final Item SAUSAGE_BREAD = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "sausage_bread"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(6).saturationModifier(1.3f).build()))
    );

    public static final Item SAUSAGE_BREAD_RAW = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "sausage_bread_raw"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(6).saturationModifier(1.3f).build()))
    );

    public static final Item SAUSAGE_BREAD_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "sausage_bread_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.6f).build()))
    );

    public static final Item SPAGHETTI = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "spaghetti"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.5f).build()))
    );

    public static final Item CHEESE_WHEEL = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cheese_wheel"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(14).saturationModifier(4f).build()).maxCount(1))
    );

    public static final Item CHEESE_WHEEL_FRESH = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cheese_wheel_fresh"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(5).saturationModifier(1.5f).build()).maxCount(1))
    );

    public static final Item CHEESE_WEDGE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cheese_wedge"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1f).build()))
    );

    public static final Item CHEESE_WEDGE_FRESH = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cheese_wedge_fresh"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(1).saturationModifier(0.2f).build()))
    );

    // Pizza
    public static final Item PIZZA_MEAT_RAW = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_meat_raw"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4).saturationModifier(1f).build()).maxCount(1))
    );

    public static final Item PIZZA_MEAT = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_meat"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(16).saturationModifier(6f).build()).maxCount(1))
    );

    public static final Item PIZZA_MEAT_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_meat_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.5f).build()))
    );

    public static final Item PIZZA_PINEAPPLE_RAW = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_pineapple_raw"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.5f).build()))
    );

    public static final Item PIZZA_PINEAPPLE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_pineapple"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(10).saturationModifier(4f).build()))
    );

    public static final Item PIZZA_PINEAPPLE_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_pineapple_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.5f).build()))
    );

    public static final Item PIZZA_MARGHERITA_RAW = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_margherita_raw"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.5f).build()))
    );

    public static final Item PIZZA_MARGHERITA = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_margherita"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(10).saturationModifier(4f).build()))
    );

    public static final Item PIZZA_MARGHERITA_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_margherita_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.5f).build()))
    );

    public static final Item PIZZA_MUSHROOM_RAW = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_mushroom_raw"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4).saturationModifier(1f).build()).maxCount(1))
    );

    public static final Item PIZZA_MUSHROOM = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_mushroom"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(16).saturationModifier(6f).build()).maxCount(1))
    );

    public static final Item PIZZA_MUSHROOM_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_mushroom_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.5f).build()))
    );

    // Salads

    public static final Item SALAD_HERB = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "salad_herb"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(5).saturationModifier(1.5f).usingConvertsTo(BOWL_BIG).build()).maxCount(1))
    );

    public static final Item SALAD_VALID = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "salad_valid"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(5).saturationModifier(1.5f).usingConvertsTo(BOWL_BIG).build()).maxCount(1))
    );



    private FoodItems() {}

    public static void register() {}
}