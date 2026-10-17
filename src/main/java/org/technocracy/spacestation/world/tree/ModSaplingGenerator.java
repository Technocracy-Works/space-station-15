package org.technocracy.spacestation.world.tree;

import net.minecraft.block.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.technocracy.spacestation.SpaceStation;

import java.util.Optional;

public class ModSaplingGenerator {
    public static final RegistryKey<ConfiguredFeature<?, ?>> ORANGE_TREE_KEY = RegistryKey.of(
            RegistryKeys.CONFIGURED_FEATURE,
            Identifier.of(SpaceStation.MOD_ID, "orange_tree")
    );

    public static final SaplingGenerator ORANGE_TREE = new SaplingGenerator(
            SpaceStation.MOD_ID + ":orange_tree",
            Optional.empty(),
            Optional.of(ORANGE_TREE_KEY),
            Optional.empty()
    );
}
