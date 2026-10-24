package org.technocracy.spacestation.registry;

import net.minecraft.item.Item;
import org.technocracy.spacestation.registry.blocks.TreeBlocks;
import org.technocracy.spacestation.registry.items.*;

public final class ModItems {

    public static void register() {
        FoodItems.register();
        DrinkItems.register();
        ToolItems.register();
        PlushieItems.register();
        ChemItems.register();
        MiscItems.register();
        PlantItems.register();
        ArmorItems.register();
        TreeItems.register();
    }

    private ModItems() {}
}