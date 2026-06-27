package org.corvaxcraft.spacestation.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.corvaxcraft.spacestation.SpaceStation;

public final class ModItemGroups {
    public static final ItemGroup CONTENT = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(SpaceStation.MOD_ID, "content"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.STEEL))
                    .displayName(Text.translatable("itemGroup.spacestation.content"))
                    .entries((context, entries) -> {
                        entries.add(ModItems.BANANIUM);
                        entries.add(ModItems.BANANIUM_ORE);
                        entries.add(ModItems.BEAKER);
                        entries.add(ModItems.BUN);
                        entries.add(ModItems.BUN_BOTTOM);
                        entries.add(ModItems.BUN_TOP);
                        entries.add(ModItems.BURGER);
                        entries.add(ModItems.BURGER_CHEESE);
                        entries.add(ModItems.CLOTH);
                        entries.add(ModItems.CUTLET_RAW);
                        entries.add(ModItems.CUTLET_COOKED);
                        entries.add(ModItems.BUTTER);
                        entries.add(ModItems.BUTTER_SLICE);
                        entries.add(ModItems.CROWBAR);
                        entries.add(ModItems.CROWBAR_RED);
                        entries.add(ModItems.CROWBAR_BRASS);
                        entries.add(ModItems.SCREWDRIVER);
                        entries.add(ModItems.CANISTER);
                        entries.add(ModItems.CROISSANT);
                        entries.add(ModItems.CROISSANT_RAW);
                        entries.add(ModItems.CHEESE_WEDGE);
                        entries.add(ModItems.CHEESE_WHEEL);
                        entries.add(ModItems.CHEESE_WHEEL_FRESH);
                        entries.add(ModItems.CHEESE_WEDGE_FRESH);
                        entries.add(ModItems.DOUGH);
                        entries.add(ModItems.DOUGH_SLICE);
                        entries.add(ModItems.DOUGH_FLAT);
                        entries.add(ModItems.ENZYME);
                        entries.add(ModItems.SAUSAGE_BREAD);
                        entries.add(ModItems.SAUSAGE_BREAD_SLICE);
                        entries.add(ModItems.FLOUR);
                        entries.add(ModItems.TELECRYSTAL);
                        entries.add(ModItems.COTTON);
                        entries.add(ModItems.COTTON_RAW);
                        entries.add(ModItems.COTTON_SEEDS);
                        entries.add(ModItems.TOMATO);
                        entries.add(ModItems.TOMATO_SEEDS);
                        entries.add(ModItems.TRASH_PLASTIC);
                        entries.add(ModBlocks.TELECRYSTAL_BLOCK.asItem());
                        entries.add(ModBlocks.TELECRYSTAL_CRYSTAL_BLOCK.asItem());
                        entries.add(ModBlocks.BANANIUM_ORE_BLOCK.asItem());
                        entries.add(ModBlocks.PLASMA_ORE_BLOCK.asItem());
                        entries.add(ModBlocks.WALL_GIRDER.asItem());
                        entries.add(ModBlocks.WALL_GIRDER_REINFORCED.asItem());
                        entries.add(ModBlocks.STEEL_TILE.asItem());
                        entries.add(ModBlocks.STEEL_WALL.asItem());
                        entries.add(ModBlocks.STEEL_WALL_REINFORCED.asItem());
                        entries.add(ModBlocks.URANIUM_ORE_BLOCK.asItem());
                        entries.add(ModBlocks.CHEM_MASTER_BLOCK.asItem());
                        entries.add(ModItems.URANIUM);
                        entries.add(ModItems.URANIUM_ORE);
                        entries.add(ModItems.STEEL_ORE);
                        entries.add(ModItems.STEEL);
                        entries.add(ModItems.Spaghetti);
                        entries.add(ModItems.XENOMORPH_PLUSH_TOY);
                        entries.add(ModItems.PLASTEEL);
                        entries.add(ModItems.ROD);
                        entries.add(ModItems.PLASTIC);
                        entries.add(ModItems.PLASTIC_KNIFE);
                        entries.add(ModItems.PLASMA_ORE);
                        entries.add(ModItems.PLASMA);
                        entries.add(ModItems.PLUSHIE_BEE);
                        entries.add(ModItems.PLUSHIE_IAN);
                        entries.add(ModItems.PLUSHIE_LIZARD);
                        entries.add(ModItems.Pineapple);
                        entries.add(ModItems.PIZZA_Pineapple);
                        entries.add(ModItems.PIZZA);
                        entries.add(ModItems.PIZZA_MEAT);
                        entries.add(ModItems.PIZZA_MEAT_RAW);
                        entries.add(ModItems.PIZZA_MEAT_SLICE);
                        entries.add(ModItems.PIZZA_MUSHROOM);
                        entries.add(ModItems.PIZZA_MUSHROOM_RAW);
                        entries.add(ModItems.PIZZA_MUSHROOM_SLICE);
                        entries.add(ModItems.WRENCH);
                        entries.add(ModItems.WELDER);
                        entries.add(ModItems.ID_CARD);
                    })
                    .build()
    );

    private ModItemGroups() {}

    public static void register() {
        // Ничего не нужно делать
    }
}
