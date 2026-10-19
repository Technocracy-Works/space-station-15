package org.technocracy.spacestation.registry;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import org.technocracy.spacestation.SpaceStation;

public class ModDamageTypes {

    public static final RegistryKey<DamageType> RADIATION_DAMAGE_TYPE = register("radiation");

    private static RegistryKey<DamageType> register(String path) {
        return RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Identifier.of(SpaceStation.MOD_ID, path)
        );
    }

    public static DamageSource of(World world, RegistryKey<DamageType> key) {
        return new DamageSource(world.getRegistryManager().get(RegistryKeys.DAMAGE_TYPE).entryOf(key));
    }
}
