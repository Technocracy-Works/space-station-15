package org.technocracy.spacestation.registry;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.ShortPlantBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.block.AssemblyBlock;
import org.technocracy.spacestation.chemistry.ChemMasterBlock;
import org.technocracy.spacestation.registry.blocks.PlantBlocks;
import org.technocracy.spacestation.registry.components.ToolIngredient;
import org.technocracy.spacestation.registry.components.ToolQuality;
import org.technocracy.spacestation.registry.items.*;

import java.util.concurrent.locks.AbstractOwnableSynchronizer;
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

    public static final Block SUSPICIOUS_GRASS = register(
            "suspicious_grass",
            ShortPlantBlock::new,
            AbstractBlock.Settings.copy(Blocks.SHORT_GRASS),
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

        // ============ КАК ДОБАВЛЯТЬ КАСТОМНУЮ СБОРКУ/РАЗБОРКУ ============

        // Несколько инструментов для разбора:
        // AssemblyBlock.registerUpgrade(<НАЗВАНИЕ_БЛОКА_С_КОТОРЫМ_ВЗАИМОДЕЙСТВУЕМ>, ModItems.<НАЗВАНИЕ_ИНГРИДИЕНТА>, <НАЗВАНИЕ_БЛОКА_КОТОРЫЙ_ПОЛУЧИМ>, <ВРЕМЯ_РАЗБОРА>, ModItems.<ПЕРВЫЙ_ПРЕДМЕТ_ДЛЯ_РАЗБОРА>, ModItems.<ВТОРОЙ, ТРЕТИЙ, ..._ПРЕДМЕТ_ДЛЯ_РАЗБОРА>);

        // Без разбора вообще:
        // AssemblyBlock.registerUpgrade(НАЗВАНИЕ_БЛОКА_С_КОТОРЫМ_ВЗАИМОДЕЙСТВУЕМ, ModItems.<НАЗВАНИЕ_ИНГРИДИЕНТА>, <НАЗВАНИЕ_БЛОКА_КОТОРЫЙ_ПОЛУЧИМ>, <ВРЕМЯ_РАЗБОРА>);

        // ============ КОНЕЦ ============

        // ============ НИЖЕ РЕЦЕПТЫ ИНГЕЙМ КРАФТА ============
        AssemblyBlock.registerUpgrade(WALL_GIRDER, STEEL_WALL, 2, 0.4f, 1.2f, ToolIngredient.of(MiscItems.STEEL, ToolQuality.IGNITION), ToolIngredient.of(ToolItems.OMNITOOL, ToolQuality.WELDING));
        AssemblyBlock.registerUpgrade(WALL_GIRDER_REINFORCED, STEEL_WALL_REINFORCED, 2, 0.4f, 1.2f, ToolIngredient.of(MiscItems.PLASTEEL), ToolIngredient.of(ToolItems.OMNITOOL, ToolQuality.PRYING));
        AssemblyBlock.registerUpgrade(WALL_GIRDER, WALL_GIRDER_REINFORCED, 4, 0.4f, 1.2f, ToolIngredient.of(MiscItems.ROD), ToolIngredient.of(ToolItems.OMNITOOL, ToolQuality.SCREWING));



        AssemblyBlock.registerUpgrade(WALL_GIRDER, TELECRYSTAL_BLOCK, 9,  0.4f, ToolIngredient.of(MiscItems.TELECRYSTAL));
        // Смотреть Комментарий в МодИтемс.жава

        PlantBlocks.register();
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
