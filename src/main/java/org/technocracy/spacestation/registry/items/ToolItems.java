package org.technocracy.spacestation.registry.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;

public final class ToolItems {

    public static final Item CROWBAR = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "crowbar"),
            new Item(new Item.Settings().maxCount(1).maxDamage(100))
    );

    public static final Item CROWBAR_RED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "crowbar_red"),
            new Item(new Item.Settings().maxCount(1).maxDamage(100))
    );

    public static final Item CROWBAR_BRASS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "crowbar_brass"),
            new Item(new Item.Settings().maxCount(1).maxDamage(100))
    );

    public static final Item SCREWDRIVER = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "screwdriver"),
            new Item(new Item.Settings().maxCount(1).maxDamage(100))
    );

    public static final Item WRENCH = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "wrench"),
            new Item(new Item.Settings().maxCount(1).maxDamage(100))
    );

    public static final Item WELDER = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "welder"),
            new Item(new Item.Settings().maxCount(1).maxDamage(100))
    );

    public static final Item PLASTIC_KNIFE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "plastic_knife"),
            new Item(new Item.Settings()
                    .recipeRemainder(MiscItems.PLASTIC)
                    .maxCount(16))
    );

    private ToolItems() {}

    public static void register() {}
}