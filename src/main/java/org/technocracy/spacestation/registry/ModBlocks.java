package org.technocracy.spacestation.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.block.AssemblyBlock;
import org.technocracy.spacestation.block.CottonCropBlock;
import org.technocracy.spacestation.block.TomatoCropBlock;
import org.technocracy.spacestation.block.TowercapCropBlock;
import org.technocracy.spacestation.chemistry.ChemMasterBlock;
import org.technocracy.spacestation.registry.items.*;

import java.util.function.Function;

public final class ModBlocks {
    public static final Block TELECRYSTAL_BLOCK = register(
            "telecrystal_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f)
                    .requiresTool(),
            true
    );

    public static final Block TELECRYSTAL_CRYSTAL_BLOCK = register(
            "telecrystal_crystal_block",
            Block::new,
            AbstractBlock.Settings.copy(Blocks.AMETHYST_BLOCK)
                    .requiresTool(),
            true
    );

    public static final Block PLASMA_ORE_BLOCK = register(
            "plasma_ore_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f)
                    .requiresTool(),
            true
    );

    public static final Block BANANIUM_ORE_BLOCK = register(
            "bananium_ore_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f)
                    .requiresTool(),
            true
    );

    public static final Block WALL_GIRDER = register(
            "wall_girder",
            AssemblyBlock::new,  // <-- Кастомный класс чтоб работали крафты, НЕ ИЗМЕНЯТЬ!!!
            AbstractBlock.Settings.create()
                    .strength(4.0f, 30.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque(),
            true
    );

    public static final Block WALL_GIRDER_REINFORCED = register(
            "wall_girder_reinforced",
            AssemblyBlock::new,  // <-- Кастомный класс чтоб работали крафты, НЕ ИЗМЕНЯТЬ!!!
            AbstractBlock.Settings.create()
                    .strength(6.0f, 60.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque(),
            true
    );

    public static final Block COTTON_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "cotton_crop"),
            new CottonCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)
                    .nonOpaque()
                    .ticksRandomly())
    );

    public static final Block TOMATO_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "tomato_crop"),
            new TomatoCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)
                    .nonOpaque()
                    .ticksRandomly())
    );

    public static final Block TOWERCAP_CROP = Registry.register(
            Registries.BLOCK,
            Identifier.of(SpaceStation.MOD_ID, "towercap_crop"),
            new TowercapCropBlock(AbstractBlock.Settings.copy(Blocks.WHEAT)
                    .nonOpaque()
                    .ticksRandomly())
    );

    public static final Block STEEL_TILE = register(
            "steel_tile",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f, 6.0f)
                    .requiresTool(),
            true
    );

    public static final Block STEEL_WALL = register(
            "steel_wall",
            AssemblyBlock::new,
            AbstractBlock.Settings.create()
                    .strength(8.0f, 120.0f)
                    .requiresTool(),
            true
    );

    public static final Block STEEL_WALL_REINFORCED = register(
            "steel_wall_reinforced",
            AssemblyBlock::new,
            AbstractBlock.Settings.create()
                    .strength(12.0f, 1200.0f)
                    .requiresTool(),
            true
    );

    public static final Block URANIUM_ORE_BLOCK = register(
            "uranium_ore_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(3.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE),
            true
    );

    public static final Block CHEM_MASTER_BLOCK = register(
            "chem_master_block",
            ChemMasterBlock::new,
            AbstractBlock.Settings.create()
                    .strength(5.0f, 30.0f)
                    .requiresTool(),
            true
    );

    private ModBlocks() {}

    public static void register() {
        // Рецепты для крафта ингейм
        // Без инструментов будет только сборка, без разбора
        AssemblyBlock.registerUpgrade(WALL_GIRDER, MiscItems.STEEL, STEEL_WALL, 2, 0.4f, 1.2f, ModItems.CROWBAR);
        AssemblyBlock.registerUpgrade(WALL_GIRDER_REINFORCED, MiscItems.PLASTEEL, STEEL_WALL_REINFORCED, 2, 0.4f, 1.2f, ModItems.CROWBAR);
        AssemblyBlock.registerUpgrade(WALL_GIRDER, MiscItems.ROD, WALL_GIRDER_REINFORCED, 4, 0.4f, 1.2f, ToolItems.SCREWDRIVER);

        // Несколько инструментов для разбора:
        // AssemblyBlock.registerUpgrade(WALL_GIRDER, ModItems.STEEL, STEEL_WALL, 2, ModItems.CROWBAR, ModItems.CROWBAR2);

        // Без разбора вообще:
        // AssemblyBlock.registerUpgrade(WALL_GIRDER, ModItems.TELECRYSTAL, TELECRYSTAL_BLOCK, 9);

        AssemblyBlock.registerUpgrade(WALL_GIRDER, MiscItems.TELECRYSTAL, TELECRYSTAL_BLOCK, 9,  0.4f, 1.2f);
        // Смотреть Комментарий в МодИтемс.жава
    }

    private static <T extends Block> T register(
            String name,
            Function<AbstractBlock.Settings, T> factory,
            AbstractBlock.Settings settings,
            boolean registerBlockItem
    ) {
        Identifier id = Identifier.of(SpaceStation.MOD_ID, name);
        T block = factory.apply(settings);
        Registry.register(Registries.BLOCK, id, block);

        if (registerBlockItem) {
            Registry.register(Registries.ITEM, id, new BlockItem(block, new Item.Settings()));
        }

        return block;
    }
}
