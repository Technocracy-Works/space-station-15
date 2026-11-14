package org.technocracy.spacestation.registry.blocks;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;

public class MiscBlocks {
    public static final Block ORANGE_TREE_PLANKS = Registry.register(
            Registries.BLOCK, Identifier.of(SpaceStation.MOD_ID, "orange_tree_planks"),
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));

    private MiscBlocks() {}

    public static void register() {
        FlammableBlockRegistry.getDefaultInstance().add(NatureBlocks.ORANGE_TREE_PLANKS, 5, 20);
    }
}
