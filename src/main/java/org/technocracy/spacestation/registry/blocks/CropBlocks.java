package org.technocracy.spacestation.registry.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.block.CottonCropBlock;
import org.technocracy.spacestation.block.TomatoCropBlock;
import org.technocracy.spacestation.block.TowercapCropBlock;

public class CropBlocks {
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

    private CropBlocks() {}

    public static void register() {}
}
