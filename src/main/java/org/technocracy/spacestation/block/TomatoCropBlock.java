package org.technocracy.spacestation.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import org.technocracy.spacestation.registry.items.CropItems;

public class TomatoCropBlock extends CropBlock {
    public TomatoCropBlock(AbstractBlock.Settings settings) {
        super(settings);
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return CropItems.TOMATO_SEEDS;
    }
}
