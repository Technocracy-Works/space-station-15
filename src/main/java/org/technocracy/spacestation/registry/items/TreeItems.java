package org.technocracy.spacestation.registry.items;

import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.registry.blocks.TreeBlocks;

public class TreeItems {
    // Orange tree
    public static final Item ORANGE_TREE_LOG = Registry.register(
            Registries.ITEM,  Identifier.of(SpaceStation.MOD_ID, "orange_tree_log"),
            new BlockItem(TreeBlocks.ORANGE_TREE_LOG, new Item.Settings()));
    public static final Item ORANGE_TREE_WOOD = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "orange_tree_wood"),
            new BlockItem(TreeBlocks.ORANGE_TREE_WOOD, new Item.Settings()));
    public static final Item ORANGE_TREE_LOG_STRIPPED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "orange_tree_log_stripped"),
            new BlockItem(TreeBlocks.ORANGE_TREE_LOG_STRIPPED, new Item.Settings()));
    public static final Item ORANGE_TREE_WOOD_STRIPPED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "orange_tree_wood_stripped"),
            new BlockItem(TreeBlocks.ORANGE_TREE_WOOD_STRIPPED, new Item.Settings()));

    public static final Item ORANGE_TREE_PLANKS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "orange_tree_planks"),
            new BlockItem(TreeBlocks.ORANGE_TREE_PLANKS, new Item.Settings()));
    public static final Item ORANGE_TREE_LEAVES = Registry.register(
            Registries.ITEM,Identifier.of(SpaceStation.MOD_ID, "orange_tree_leaves"),
            new BlockItem(TreeBlocks.ORANGE_TREE_LEAVES, new Item.Settings()));

    public static final Item ORANGE_TREE_SAPLING = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "orange_tree_sapling"),
            new BlockItem(TreeBlocks.ORANGE_TREE_SAPLING, new Item.Settings()));

    private TreeItems() {}

    public static void register() {}
}
