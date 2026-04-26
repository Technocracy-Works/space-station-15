package org.corvaxcraft.spacestation;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public final class ModItems {
    public static final Item BANANIUM = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "bananium"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(2) // сколько восстанавливает голода
                            .saturationModifier(0.3f) // насыщение
                            .statusEffect(
                                    new StatusEffectInstance(StatusEffects.POISON, 30, 4), // 10 секунд
                                    1.0f // шанс (1.0 = 100%)
                            )
                            .build()
            ))
    );

    public static final Item CROISSANT = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "croissant"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(5)
                            .saturationModifier(0.7f)
                            .build()
            ))
    );

    public static final Item SAUSAGE_BREAD = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "sausage_bread"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(6)
                            .saturationModifier(1.3f)
                            .build()
            ))
    );

    public static final Item SAUSAGE_BREAD_SLICE = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "sausage_bread_slice"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(2)
                            .saturationModifier(0.6f)
                            .build()
            ))
    );

    public static final Item CROISSANT_RAW = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "croissant_raw"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(2)
                            .saturationModifier(0.3f)
                            .build()
            ))
    );

    public static final Item BANANIUM_ORE = register("bananium_ore");
    public static final Item PLASMA_ORE = register("plasma_ore");
    public static final Item PLASMA = register("plasma");
    public static final Item FLOUR = register("flour");
    public static final Item BUTTER = register("butter");
    public static final Item BUTTER_SLICE = register("butter_slice");
    public static final Item DOUGH = register("dough");
    public  static final Item DOUGH_SLICE = register("dough_slice");
    public static final Item DOUGH_FLAT = register("dough_flat");
    public static final Item TELECRYSTAL = register("telecrystal");
    public static final Item STEEL_ORE = register("steel_ore");
    public static final Item URANIUM_ORE = register("uranium_ore");
    public static final Item STEEL = register("steel");
    public static final Item ROD = register("rod");
    public static final Item PLASTIC = register("plastic");
    public static final Item PLASTIC_KNIFE = register("plastic_knife");
    public static final Item ID_CARD = register("id_card");

    private ModItems() {}

    private static Item register(String name) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(SpaceStation.MOD_ID, name),
                new Item(new Item.Settings())
        );
    }

    public static void register() {
    }
}