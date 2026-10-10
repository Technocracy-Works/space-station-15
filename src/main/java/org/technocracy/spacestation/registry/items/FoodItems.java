package org.technocracy.spacestation.registry.items;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;

public final class FoodItems {

    //
    // =============== INGREDIENTS ===============
    //

    public static final Item FLOUR = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "flour"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(1).saturationModifier(0.1f)
                    .build()))
    );

    public static final Item BUTTER = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "butter"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(6).saturationModifier(1.2f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 300, 0), 0.25f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 150, 0), 0.25f)
                    .build()))
    );

    public static final Item BUTTER_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "butter_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.4f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 100, 0), 0.25f)
                    .build()))
    );

    public static final Item DOUGH = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "dough"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(1).saturationModifier(0.1f)
                    .build()))
    );

    public static final Item DOUGH_FLAT = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "dough_flat"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(1).saturationModifier(0.1f)
                    .build()))
    );

    public static final Item DOUGH_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "dough_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(1).saturationModifier(0.1f)
                    .build()))
    );

    public static final Item FLOUR_CORN = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "flour_corn"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(1).saturationModifier(0.1f)
                    .build()))
    );

    public static final Item DOUGH_CORN = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "dough_corn"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(1).saturationModifier(0.1f)
                    .build()))
    );

    public static final Item BANANIUM = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bananium"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(0.4f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 300, 4), 0.25f)
                    .build()))
    );

    public static final Item BUN = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bun"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(3)
                    .saturationModifier(0.3f)
                    .build()))
    );

    public static final Item BUN_BOTTOM = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bun_bottom"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(1)
                    .saturationModifier(0.15f)
                    .build()))
    );

    public static final Item BUN_TOP = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bun_top"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(1)
                    .saturationModifier(0.15f)
                    .build()))
    );

    //
    // =============== BURGERS ===============
    //

    public static final Item BURGER_CHEESE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "burger_cheese"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(8).saturationModifier(1.0f).build()))
    );

    public static final Item BURGER_PLAIN = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "burger_plain"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(7).saturationModifier(0.9f).build()))
    );

    public static final Item BURGER_FIVE_ALARM = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "burger_five_alarm"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(7).saturationModifier(0.9f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 200, 0), 1.0f)
                    .build()))
    );

    public static final Item CUTLET = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cutlet"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.2f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 0), 0.25f)
                    .build()))
    );

    public static final Item CUTLET_COOKED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cutlet_cooked"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(5).saturationModifier(0.8f).build()))
    );

    public static final Item CROISSANT_COOKED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "croissant_cooked"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(5).saturationModifier(0.7f).build()))
    );

    public static final Item CROISSANT = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "croissant"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.2f).build()))
    );

    public static final Item MEATBALL = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "meatball"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.1f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 0), 0.25f)
                    .build()))
    );

    public static final Item MEATBALL_COOKED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "meatball_cooked"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4).saturationModifier(0.7f).build()))
    );

    //
    // =============== BREAD ===============
    //

    public static final Item BREAD_MEAT_COOKED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bread_meat_cooked"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(6).saturationModifier(0.8f).build()))
    );

    public static final Item BREAD_MEAT = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bread_meat"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(0.3f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 0), 0.25f)
                    .build()))
    );

    public static final Item BREAD_MEAT_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bread_meat_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.3f).build()))
    );

    public static final Item BREAD_CORN = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bread_corn"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(6).saturationModifier(0.8f).build()))
    );

    public static final Item BREAD_CORN_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bread_corn_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.3f).build()))
    );

    public static final Item BREAD_SAUSAGE_COOKED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bread_sausage_cooked"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(7).saturationModifier(0.9f).build()))
    );

    public static final Item BREAD_SAUSAGE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bread_sausage"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(0.3f)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HUNGER, 100, 0), 0.25f)
                    .build()))
    );

    public static final Item BREAD_SAUSAGE_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bread_sausage_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.3f).build()))
    );

    public static final Item SPAGHETTI = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "spaghetti"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(5).saturationModifier(0.6f).build()))
    );

    public static final Item CHEESE_WHEEL = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cheese_wheel"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(12).saturationModifier(1.2f).build()).maxCount(1))
    );

    public static final Item CHEESE_WHEEL_FRESH = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cheese_wheel_fresh"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(8).saturationModifier(0.7f).build()).maxCount(1))
    );

    public static final Item CHEESE_WEDGE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cheese_wedge"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(0.4f).build()))
    );

    public static final Item CHEESE_WEDGE_FRESH = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cheese_wedge_fresh"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.25f).build()))
    );

    //
    // =============== PIZZA ===============
    //

    public static final Item PIZZA_MEAT = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_meat"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.3f).build()).maxCount(1))
    );

    public static final Item PIZZA_MEAT_COOKED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_meat_cooked"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(16).saturationModifier(1.6f).build()).maxCount(1))
    );

    public static final Item PIZZA_MEAT_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_meat_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.2f).build()))
    );

    public static final Item PIZZA_PINEAPPLE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_pineapple"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.3f).build()))
    );

    public static final Item PIZZA_PINEAPPLE_COOKED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_pineapple_cooked"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(14).saturationModifier(1.4f).build()))
    );

    public static final Item PIZZA_PINEAPPLE_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_pineapple_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.2f).build()))
    );

    public static final Item PIZZA_MARGHERITA_RAW = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_margherita_raw"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.3f).build()))
    );

    public static final Item PIZZA_MARGHERITA = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_margherita"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(14).saturationModifier(1.6f).build()))
    );

    public static final Item PIZZA_MARGHERITA_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_margherita_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.2f).build()))
    );

    public static final Item PIZZA_MUSHROOM_RAW = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_mushroom_raw"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.3f).build()).maxCount(1))
    );

    public static final Item PIZZA_MUSHROOM = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_mushroom"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(16).saturationModifier(1.6f).build()).maxCount(1))
    );

    public static final Item PIZZA_MUSHROOM_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_mushroom_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.2f).build()))
    );

    public static final Item PIZZA_VEGETABLE_RAW = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_vegetable_raw"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.3f).build()).maxCount(1))
    );

    public static final Item PIZZA_VEGETABLE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_vegetable"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(14).saturationModifier(1.4f).build()).maxCount(1))
    );

    public static final Item PIZZA_VEGETABLE_SLICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pizza_vegetable_slice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(0.2f).build()))
    );

    //
    // =============== SALADS ===============
    //

    public static final Item SALAD_HERB = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "salad_herb"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4).saturationModifier(0.3f).usingConvertsTo(MiscItems.BOWL_BIG).build()).maxCount(1))
    );

    public static final Item SALAD_VALID = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "salad_valid"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(5).saturationModifier(0.5f).usingConvertsTo(MiscItems.BOWL_BIG).build()).maxCount(1))
    );

    //
    // =============== SOUPS ===============
    //

    public static final Item SOUP_BUNGO = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "soup_bungo"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                            .nutrition(7).saturationModifier(0.7f).usingConvertsTo(MiscItems.BOWL_BIG).build())
                    .maxCount(1)
                    .recipeRemainder(Items.BUCKET))
    );

    public static final Item SOUP_NETTLE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "soup_nettle"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                            .nutrition(5).saturationModifier(0.5f).usingConvertsTo(MiscItems.BOWL_BIG).build())
                    .maxCount(1)
                    .recipeRemainder(Items.BUCKET))
    );

    public static final Item SOUP_ONION = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "soup_onion"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                            .nutrition(5).saturationModifier(0.5f).usingConvertsTo(MiscItems.BOWL_BIG).build())
                    .maxCount(1)
                    .recipeRemainder(Items.BUCKET))
    );

    public static final Item SOUP_PEA = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "soup_pea"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                            .nutrition(5).saturationModifier(0.5f).usingConvertsTo(MiscItems.BOWL_BIG).build())
                    .maxCount(1)
                    .recipeRemainder(Items.BUCKET))
    );

    public static final Item SOUP_TOMATO_BLOOD = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "soup_tomato_blood"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(6).saturationModifier(0.6f).usingConvertsTo(MiscItems.BOWL_BIG).build()).maxCount(1))
    );

    private FoodItems() {}

    public static void register() {}
}