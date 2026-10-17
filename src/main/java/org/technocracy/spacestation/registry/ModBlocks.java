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
import org.technocracy.spacestation.block.MachineFrameBlock;
import org.technocracy.spacestation.chemistry.chemmaster.ChemMasterBlock;
import org.technocracy.spacestation.chemistry.sublimator.SublimatorBlock;
import org.technocracy.spacestation.registry.blocks.PlantBlocks;
import org.technocracy.spacestation.item.components.ToolIngredient;
import org.technocracy.spacestation.item.components.ToolQuality;
import org.technocracy.spacestation.registry.blocks.TreeBlocks;
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

    public static final Block PLASMA_BLOCK = register(
            "plasma_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(1.5f)
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

    public static final Block BANANIUM_BLOCK = register(
            "bananium_block",
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

    public static final Block MACHINE_FRAME = register(
            "machine_frame",
            MachineFrameBlock::new,
            AbstractBlock.Settings.create()
                    .strength(6.0f, 60.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque(),
            true
    );

    public static final Block MACHINE_FRAME_STAGE1 = register(
            "machine_frame_stage1",
            MachineFrameBlock::new,
            AbstractBlock.Settings.create()
                    .strength(6.0f, 60.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque(),
            true
    );

    public static final Block MACHINE_FRAME_STAGE2 = register(
            "machine_frame_stage2",
            MachineFrameBlock::new,
            AbstractBlock.Settings.create()
                    .strength(6.0f, 60.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque(),
            true
    );

    public static final Block MACHINE_FRAME_STAGE3 = register(
            "machine_frame_stage3",
            MachineFrameBlock::new,
            AbstractBlock.Settings.create()
                    .strength(6.0f, 60.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque(),
            true
    );

    public static final Block MACHINE_FRAME_STAGE4 = register(
            "machine_frame_stage4",
            MachineFrameBlock::new,
            AbstractBlock.Settings.create()
                    .strength(6.0f, 60.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.CHAIN)
                    .nonOpaque(),
            true
    );

    public static final Block MACHINE_FRAME_ASSEMBLED = register(
            "machine_frame_assembled",
            MachineFrameBlock::new,
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

    public static final Block URANIUM_BLOCK = register(
            "uranium_block",
            Block::new,
            AbstractBlock.Settings.create()
                    .strength(3.0f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE),
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

    public static final Block CHEM_MASTER = register(
            "chem_master",
            ChemMasterBlock::new,
            AbstractBlock.Settings.create()
                    .strength(5.0f, 30.0f)
                    .requiresTool()
                    .nonOpaque(),
            true
    );

    public static final Block SUBLIMATOR = register(
            "sublimator",
            SublimatorBlock::new,
            AbstractBlock.Settings.create()
                    .strength(5.0f, 30.0f)
                    .requiresTool()
                    .nonOpaque(),
            true
    );

    private ModBlocks() {}

    public static void register() {
        PlantBlocks.register();
        TreeBlocks.register();
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
