package org.technocracy.spacestation.registry.items;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.registry.blocks.PlantBlocks;

public final class PlantItems {

    public static final Item ALOE        = register("aloe");
    public static final Item ALOE_CREAM        = register("aloe_cream");

    public static final Item ALOE_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "aloe_seeds"),
            new AliasedBlockItem(PlantBlocks.ALOE_CROP, new Item.Settings())
    );

    public static final Item AMBROSIA        = register("ambrosia");

    public static final Item AMBROSIA_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "ambrosia_seeds"),
            new AliasedBlockItem(PlantBlocks.AMBROSIA_CROP, new Item.Settings())
    );

    public static final Item AMBROSIA_OLYMPIC       = register("ambrosia_olympic");

    public static final Item AMBROSIA_OLYMPIC_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "ambrosia_olympic_seeds"),
            new AliasedBlockItem(PlantBlocks.AMBROSIA_OLYMPIC_CROP, new Item.Settings())
    );

    public static final Item BLOONION        = register("bloonion");

    public static final Item BLOONION_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bloonion_seeds"),
            new AliasedBlockItem(PlantBlocks.BLOONION_CROP, new Item.Settings())
    );

    public static final Item BLUE_TOMATO = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "blue_tomato"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4).saturationModifier(3.6f).build()))
    );

    public static final Item BLUE_TOMATO_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "blue_tomato_seeds"),
            new AliasedBlockItem(PlantBlocks.BLUE_TOMATO_CROP, new Item.Settings())
    );

    public static final Item BUNGO = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bungo"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4).saturationModifier(3.6f).build()))
    );

    public static final Item BUNGO_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bungo_seeds"),
            new AliasedBlockItem(PlantBlocks.BUNGO_CROP, new Item.Settings())
    );

    public static final Item CABBAGE        = register("cabbage");

    public static final Item CABBAGE_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cabbage_seeds"),
            new AliasedBlockItem(PlantBlocks.CABBAGE_CROP, new Item.Settings())
    );

    public static final Item CHILI = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "chili"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.8f).build()))
    );

    public static final Item CHILI_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "chili_seeds"),
            new AliasedBlockItem(PlantBlocks.CHILI_CROP, new Item.Settings())
    );

    public static final Item CHILLY = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "chilly"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.8f).build()))
    );

    public static final Item CHILLY_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "chilly_seeds"),
            new AliasedBlockItem(PlantBlocks.CHILLY_CROP, new Item.Settings())
    );

    public static final Item CORN = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "corn"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(3.6f).build()))
    );

    public static final Item CORN_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "corn_seeds"),
            new AliasedBlockItem(PlantBlocks.CORN_CROP, new Item.Settings())
    );

    public static final Item COTTON        = register("cotton");
    public static final Item COTTON_RAW    = register("cotton_raw");

    public static final Item COTTON_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cotton_seeds"),
            new AliasedBlockItem(PlantBlocks.COTTON_CROP, new Item.Settings())
    );

    public static final Item DEATHBLOOM        = register("deathbloom");

    public static final Item DEATHBLOOM_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "deathbloom_seeds"),
            new AliasedBlockItem(PlantBlocks.DEATHBLOOM_CROP, new Item.Settings())
    );

    public static final Item EGGPLANT = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "eggplant"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4).saturationModifier(3.6f).build()))
    );

    public static final Item EGGPLANT_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "eggplant_seeds"),
            new AliasedBlockItem(PlantBlocks.EGGPLANT_CROP, new Item.Settings())
    );

    public static final Item EGGY_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "eggy_seeds"),
            new AliasedBlockItem(PlantBlocks.EGGY_CROP, new Item.Settings())
    );

    public static final Item GARLIC = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "garlic"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.8f).build()))
    );

    public static final Item GARLIC_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "garlic_seeds"),
            new AliasedBlockItem(PlantBlocks.GARLIC_CROP, new Item.Settings())
    );

    public static final Item KOIBEAN = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "koibean"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.8f).build()))
    );

    public static final Item KOIBEAN_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "koibean_seeds"),
            new AliasedBlockItem(PlantBlocks.KOIBEAN_CROP, new Item.Settings())
    );

    public static final Item LAUGHIN_PEA = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "laughin_pea"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.8f).build()))
    );

    public static final Item LAUGHIN_PEA_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "laughin_pea_seeds"),
            new AliasedBlockItem(PlantBlocks.LAUGHIN_PEA_CROP, new Item.Settings())
    );

    public static final Item MEATWHEAT = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "meatwheat"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(6).saturationModifier(9.6f).build()))
    );

    public static final Item MEATWHEAT_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "meatwheat_seeds"),
            new AliasedBlockItem(PlantBlocks.MEATWHEAT_CROP, new Item.Settings())
    );

    public static final Item NETTLE        = register("nettle");

    public static final Item NETTLE_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "nettle_seeds"),
            new AliasedBlockItem(PlantBlocks.NETTLE_CROP, new Item.Settings())
    );

    public static final Item OAT        = register("oat");

    public static final Item OAT_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "oat_seeds"),
            new AliasedBlockItem(PlantBlocks.OAT_CROP, new Item.Settings())
    );

    public static final Item ONION = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "onion"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.8f).build()))
    );

    public static final Item ONION_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "onion_seeds"),
            new AliasedBlockItem(PlantBlocks.ONION_CROP, new Item.Settings())
    );

    public static final Item ONION_RED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "onion_red"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.8f).build()))
    );

    public static final Item ONION_RED_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "onion_red_seeds"),
            new AliasedBlockItem(PlantBlocks.ONION_RED_CROP, new Item.Settings())
    );

    public static final Item PEA = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pea"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.8f).build()))
    );

    public static final Item PEA_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pea_seeds"),
            new AliasedBlockItem(PlantBlocks.PEA_CROP, new Item.Settings())
    );

    public static final Item PINEAPPLE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pineapple"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(4).saturationModifier(3.6f).build()))
    );

    public static final Item PINEAPPLE_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pineapple_seeds"),
            new AliasedBlockItem(PlantBlocks.PINEAPPLE_CROP, new Item.Settings())
    );

    public static final Item PYROTTON        = register("pyrotton");

    public static final Item PYROTTON_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pyrotton_seeds"),
            new AliasedBlockItem(PlantBlocks.PYROTTON_CROP, new Item.Settings())
    );

    public static final Item RICE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "rice"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(3.6f).build()))
    );

    public static final Item RICE_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "rice_seeds"),
            new AliasedBlockItem(PlantBlocks.RICE_CROP, new Item.Settings())
    );

    public static final Item SOYBEANS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "soybeans"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(1).saturationModifier(0.6f).build()))
    );

    public static final Item SOYBEANS_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "soybeans_seeds"),
            new AliasedBlockItem(PlantBlocks.SOYBEANS_CROP, new Item.Settings())
    );

    public static final Item TOMATO = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "tomato"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(0.3f).build()))
    );

    public static final Item TOMATO_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "tomato_seeds"),
            new AliasedBlockItem(PlantBlocks.TOMATO_CROP, new Item.Settings())
    );

    public static final Item TOMATO_BLOOD = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "tomato_blood"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(6).saturationModifier(9.6f).build()))
    );

    public static final Item TOMATO_BLOOD_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "tomato_blood_seeds"),
            new AliasedBlockItem(PlantBlocks.TOMATO_BLOOD_CROP, new Item.Settings())
    );

    public static final Item TOWERCAP_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "towercap_seeds"),
            new AliasedBlockItem(PlantBlocks.TOWERCAP_CROP, new Item.Settings())
    );

    private static Item register(String name) {
        return Registry.register(
                Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, name),
                new Item(new Item.Settings())
        );
    }

    private PlantItems() {}

    public static void register() {}
}