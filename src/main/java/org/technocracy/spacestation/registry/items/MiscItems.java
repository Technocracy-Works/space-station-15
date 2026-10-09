package org.technocracy.spacestation.registry.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;

public final class MiscItems {

    public static final Item ASSEMBLY_KIT_CM = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "assembly_kit_cm"),
            new Item(new Item.Settings().maxCount(1)));
    public static final Item ASSEMBLY_KIT_SUBLIMATOR = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "assembly_kit_sublimator"),
            new Item(new Item.Settings().maxCount(1)));
    public static final Item BANANIUM           = register("bananium");
    public static final Item BANANIUM_ORE           = register("bananium_ore");
    public static final Item CABLE_LV = register("cable_lv");
    public static final Item CABLE_HV = register("cable_hv");
    public static final Item CIRCUIT_BASE = register("circuit_base");
    public static final Item BLOODY_RED_BALLISTIC_PLATE  = register("bloody_red_ballistic_plate");
    public static final Item CLOTH                  = register("cloth");
    public static final Item PLASMA_ORE             = register("plasma_ore");
    public static final Item PLASMA                 = register("plasma");
    public static final Item TELECRYSTAL            = register("telecrystal");
    public static final Item TRASH_PLASTIC          = register("trash_plastic");
    public static final Item STEEL_ORE              = register("steel_ore");
    public static final Item FLINT_PURIFIED = register("flint_purified");
    public static final Item URANIUM                = register("uranium");
    public static final Item URANIUM_ORE            = register("uranium_ore");
    public static final Item STEEL                  = register("steel");
    public static final Item SOLID_FUEL             = register("solid_fuel");
    public static final Item PLASTEEL               = register("plasteel");
    public static final Item ROD                    = register("rod");
    public static final Item PLASTIC                = register("plastic");
    public static final Item RUBBER_RAW = register("rubber_raw");
    public static final Item RUBBER = register("rubber");
    public static final Item ID_CARD                = register("id_card");

    public static final Item BOWL_BIG =     Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "bowl_big"),
            new Item(new Item.Settings().maxCount(16)));

    private static Item register(String name) {
        return Registry.register(
                Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, name),
                new Item(new Item.Settings())
        );
    }

    private MiscItems() {}

    public static void register() {}
}