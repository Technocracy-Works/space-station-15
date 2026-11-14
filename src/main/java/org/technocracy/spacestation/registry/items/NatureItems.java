package org.technocracy.spacestation.registry.items;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.registry.blocks.NatureBlocks;

public class NatureItems {
    // Orange tree
    public static final Item ORANGE_TREE_LOG = Registry.register(
            Registries.ITEM,  Identifier.of(SpaceStation.MOD_ID, "orange_tree_log"),
            new BlockItem(NatureBlocks.ORANGE_TREE_LOG, new Item.Settings()));
    public static final Item ORANGE_TREE_WOOD = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "orange_tree_wood"),
            new BlockItem(NatureBlocks.ORANGE_TREE_WOOD, new Item.Settings()));
    public static final Item ORANGE_TREE_LOG_STRIPPED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "orange_tree_log_stripped"),
            new BlockItem(NatureBlocks.ORANGE_TREE_LOG_STRIPPED, new Item.Settings()));
    public static final Item ORANGE_TREE_WOOD_STRIPPED = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "orange_tree_wood_stripped"),
            new BlockItem(NatureBlocks.ORANGE_TREE_WOOD_STRIPPED, new Item.Settings()));

    public static final Item ORANGE_TREE_PLANKS = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "orange_tree_planks"),
            new BlockItem(NatureBlocks.ORANGE_TREE_PLANKS, new Item.Settings()));
    public static final Item ORANGE_TREE_LEAVES = Registry.register(
            Registries.ITEM,Identifier.of(SpaceStation.MOD_ID, "orange_tree_leaves"),
            new BlockItem(NatureBlocks.ORANGE_TREE_LEAVES, new Item.Settings()));

    public static final Item ORANGE_TREE_SAPLING = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "orange_tree_sapling"),
            new BlockItem(NatureBlocks.ORANGE_TREE_SAPLING, new Item.Settings()));

    public static final Item ORANGE = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "orange"),
            new Item(new Item.Settings().food(new FoodComponent.Builder()
                    .nutrition(2).saturationModifier(1.8f).build()))
    );

    private NatureItems() {}

    public static void register() {}
}
