package org.technocracy.spacestation.registry.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.item.KnifeItem;
import org.technocracy.spacestation.registry.components.ChargeData;
import org.technocracy.spacestation.registry.components.ModComponents;
import org.technocracy.spacestation.registry.components.ToolQuality;

public final class ToolItems {

    public static final Item CROWBAR = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "crowbar"),
            new ItemTool(new Item.Settings()
                    .maxDamage(100),
                    ToolQuality.PRYING)
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
            new ItemTool(new Item.Settings()
                    .maxDamage(100), ToolQuality.SCREWING)
    );

    public static final Item WRENCH = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "wrench"),
            new Item(new Item.Settings().maxCount(1).maxDamage(100))
    );

    public static final Item WELDER = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "welder"),
            new ItemTool(new Item.Settings()
                    .component(ModComponents.USE_DELAY_COMPONENT, 1f)
                    .component(ModComponents.CHARGE_COMPONENT, new ChargeData(100f, 100f))
                    .component(ModComponents.ITEM_TOGGLE_COMPONENT, false),
                    ToolQuality.WELDING)
    );

    public static final Item OMNITOOL = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "omnitool"),
            new ItemTool(new Item.Settings(), 100f, ToolQuality.ALL)
    );

    public static final Item KNIFE_KITCHEN = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "knife_kitchen"),
            new KnifeItem(new Item.Settings()
                    .maxDamage(128))
    );

    public static final Item LIGHTER = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "lighter"),
            new ItemTool(new Item.Settings()
                    .component(ModComponents.USE_DELAY_COMPONENT, 1f)
                    .component(ModComponents.CHARGE_COMPONENT, new ChargeData(20f, 20f))
                    .component(ModComponents.ITEM_TOGGLE_COMPONENT, false),
                    ToolQuality.IGNITION)
    );

    public static final Item PLASTIC_KNIFE = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "plastic_knife"),
            new KnifeItem(new Item.Settings()
                    .maxDamage(8))
    );

    private ToolItems() {}

    public static void register() {}
}