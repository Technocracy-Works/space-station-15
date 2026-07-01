package org.technocracy.spacestation.block;

import net.minecraft.block.CropBlock;
import net.minecraft.item.ItemConvertible;
import org.technocracy.spacestation.registry.items.PlantItems;

import java.util.function.Supplier;

public class SimpleCropBlock extends CropBlock {
    private final Supplier<ItemConvertible> seedsItemSupplier;

    public SimpleCropBlock(Settings settings, Supplier<ItemConvertible> seedsItemSupplier) {
        super(settings);
        this.seedsItemSupplier = seedsItemSupplier;
    }

    @Override
    protected ItemConvertible getSeedsItem() {
        return seedsItemSupplier.get();
    }
}