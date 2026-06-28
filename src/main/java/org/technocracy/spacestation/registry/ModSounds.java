package org.corvaxcraft.spacestation.registry;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.corvaxcraft.spacestation.SpaceStation;

public final class ModSounds {

    public static final SoundEvent PLUSHIE_SQUEAK_1 = register("plushie.squeak1");
    public static final SoundEvent PLUSHIE_SQUEAK_2 = register("plushie.squeak2");
    public static final SoundEvent PLUSHIE_SQUEAK_3 = register("plushie.squeak3");

    private ModSounds() {}

    private static SoundEvent register(String name) {
        Identifier id = Identifier.of(SpaceStation.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static void register() {}
}
