package org.technocracy.spacestation.registry.blocks;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.world.tree.ModSaplingGenerator;

public class TreeBlocks {

    // Orange tree
    public static final Block ORANGE_TREE_LOG = Registry.register(
            Registries.BLOCK, Identifier.of(SpaceStation.MOD_ID, "orange_tree_log"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG)));
    public static final Block ORANGE_TREE_WOOD = Registry.register(
            Registries.BLOCK, Identifier.of(SpaceStation.MOD_ID, "orange_tree_wood"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD)));
    public static final Block ORANGE_TREE_LOG_STRIPPED = Registry.register(
            Registries.BLOCK, Identifier.of(SpaceStation.MOD_ID, "orange_tree_log_stripped"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG)));
    public static final Block ORANGE_TREE_WOOD_STRIPPED = Registry.register(
            Registries.BLOCK, Identifier.of(SpaceStation.MOD_ID, "orange_tree_wood_stripped"),
            new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD)));

    public static final Block ORANGE_TREE_PLANKS = Registry.register(
            Registries.BLOCK, Identifier.of(SpaceStation.MOD_ID, "orange_tree_planks"),
            new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS)));
    public static final Block ORANGE_TREE_LEAVES = Registry.register(
            Registries.BLOCK,Identifier.of(SpaceStation.MOD_ID, "orange_tree_leaves"),
            new LeavesBlock(AbstractBlock.Settings.copy(Blocks.OAK_LEAVES)));

    public static final Block ORANGE_TREE_SAPLING = Registry.register(
            Registries.BLOCK, Identifier.of(SpaceStation.MOD_ID, "orange_tree_sapling"),
            new SaplingBlock(ModSaplingGenerator.ORANGE_TREE, AbstractBlock.Settings.copy(Blocks.OAK_SAPLING)));

    private TreeBlocks() {}

    public static void register() {
        // Orange tree
        StrippableBlockRegistry.register(TreeBlocks.ORANGE_TREE_LOG, TreeBlocks.ORANGE_TREE_LOG_STRIPPED);
        StrippableBlockRegistry.register(TreeBlocks.ORANGE_TREE_WOOD, TreeBlocks.ORANGE_TREE_WOOD_STRIPPED);

        FlammableBlockRegistry.getDefaultInstance().add(TreeBlocks.ORANGE_TREE_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(TreeBlocks.ORANGE_TREE_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(TreeBlocks.ORANGE_TREE_LOG_STRIPPED, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(TreeBlocks.ORANGE_TREE_WOOD_STRIPPED, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(TreeBlocks.ORANGE_TREE_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(TreeBlocks.ORANGE_TREE_LEAVES, 30, 60);
    }
}
