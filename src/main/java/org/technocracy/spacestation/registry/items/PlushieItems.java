package org.technocracy.spacestation.registry.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.item.SqueakyPlushieItem;
import org.technocracy.spacestation.registry.ModSounds;

public final class PlushieItems {

    public static final Item PLUSHIE_BEE = register(
            "plushie_bee", ModSounds.PLUSHIE_SQUEAK_1, ModSounds.PLUSHIE_SQUEAK_2
    );

    public static final Item PLUSHIE_IAN = register(
            "plushie_ian", ModSounds.PLUSHIE_SQUEAK_2, ModSounds.PLUSHIE_SQUEAK_3
    );

    public static final Item PLUSHIE_LIZARD = register(
            "plushie_lizard", ModSounds.PLUSHIE_SQUEAK_1, ModSounds.PLUSHIE_SQUEAK_3
    );

    public static final Item PLUSHIE_XENO = register(
            "plushie_xeno", ModSounds.PLUSHIE_SQUEAK_1, ModSounds.PLUSHIE_SQUEAK_2, ModSounds.PLUSHIE_SQUEAK_3
    );

    private static Item register(String name, SoundEvent... squeakSounds) {
        return Registry.register(
                Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, name),
                new SqueakyPlushieItem(new Item.Settings(), squeakSounds)
        );
    }

    private PlushieItems() {}

    public static void register() {}
}