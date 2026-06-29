package org.technocracy.spacestation.registry.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;

public final class MiscItems {

    public static final Item BANANIUM_ORE  = register("bananium_ore");
    public static final Item CLOTH         = register("cloth");
    public static final Item PLASMA_ORE    = register("plasma_ore");
    public static final Item PLASMA        = register("plasma");
    public static final Item FLOUR         = register("flour");
    public static final Item BUTTER        = register("butter");
    public static final Item BUTTER_SLICE  = register("butter_slice");
    public static final Item DOUGH         = register("dough");
    public static final Item DOUGH_SLICE   = register("dough_slice");
    public static final Item DOUGH_FLAT    = register("dough_flat");
    public static final Item TELECRYSTAL   = register("telecrystal");
    public static final Item COTTON        = register("cotton");
    public static final Item COTTON_RAW    = register("cotton_raw");
    public static final Item TRASH_PLASTIC = register("trash_plastic");
    public static final Item STEEL_ORE     = register("steel_ore");
    public static final Item URANIUM       = register("uranium");
    public static final Item URANIUM_ORE   = register("uranium_ore");
    public static final Item STEEL         = register("steel");
    public static final Item PLASTEEL      = register("plasteel");
    public static final Item ROD           = register("rod");
    public static final Item PLASTIC       = register("plastic");
    public static final Item ID_CARD       = register("id_card");

    private static Item register(String name) {
        return Registry.register(
                Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, name),
                new Item(new Item.Settings())
        );
    }

    private MiscItems() {}

    public static void register() {}
}