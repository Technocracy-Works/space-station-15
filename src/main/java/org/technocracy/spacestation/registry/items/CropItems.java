package org.technocracy.spacestation.registry.items;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.registry.blocks.CropBlocks;

public final class CropItems {

    public static final Item COTTON        = register("cotton");
    public static final Item COTTON_RAW    = register("cotton_raw");

    public static final Item COTTON_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "cotton_seeds"),
            new AliasedBlockItem(CropBlocks.COTTON_CROP, new Item.Settings())
    );

    public static final Item TOMATO = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "tomato"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(3).saturationModifier(0.3f).build()))
    );

    public static final Item TOMATO_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "tomato_seeds"),
            new AliasedBlockItem(CropBlocks.TOMATO_CROP, new Item.Settings())
    );


    public static final Item TOWERCAP_SEEDS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "towercap_seeds"),
            new AliasedBlockItem(CropBlocks.TOWERCAP_CROP, new Item.Settings())
    );

    // TODO (asnden): pineapple stuff
    public static final Item PINEAPPLE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "pineapple"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.5f).build()))
    );

    private static Item register(String name) {
        return Registry.register(
                Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, name),
                new Item(new Item.Settings())
        );
    }

    private CropItems() {}

    public static void register() {}
}