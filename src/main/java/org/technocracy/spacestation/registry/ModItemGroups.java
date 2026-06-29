package org.technocracy.spacestation.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.registry.items.*;

public final class ModItemGroups {
    public static final ItemGroup CONTENT = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(SpaceStation.MOD_ID, "content"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(MiscItems.STEEL))
                    .displayName(Text.translatable("itemGroup.spacestation.content"))
                    .entries((context, entries) -> {
                        entries.add(FoodItems.BANANIUM);
                        entries.add(MiscItems.BANANIUM_ORE);
                        entries.add(ChemItems.BEAKER);
                        entries.add(FoodItems.BUN);
                        entries.add(FoodItems.BUN_BOTTOM);
                        entries.add(FoodItems.BUN_TOP);
                        entries.add(FoodItems.BURGER);
                        entries.add(FoodItems.BURGER_CHEESE);
                        entries.add(MiscItems.CLOTH);
                        entries.add(FoodItems.CUTLET_RAW);
                        entries.add(FoodItems.CUTLET_COOKED);
                        entries.add(MiscItems.BUTTER);
                        entries.add(MiscItems.BUTTER_SLICE);
                        entries.add(ToolItems.CROWBAR);
                        entries.add(ToolItems.CROWBAR_RED);
                        entries.add(ToolItems.CROWBAR_BRASS);
                        entries.add(ToolItems.SCREWDRIVER);
                        entries.add(ChemItems.CANISTER);
                        entries.add(FoodItems.CROISSANT);
                        entries.add(FoodItems.CROISSANT_RAW);
                        entries.add(FoodItems.CHEESE_WEDGE);
                        entries.add(FoodItems.CHEESE_WHEEL);
                        entries.add(FoodItems.CHEESE_WHEEL_FRESH);
                        entries.add(FoodItems.CHEESE_WEDGE_FRESH);
                        entries.add(MiscItems.DOUGH);
                        entries.add(MiscItems.DOUGH_SLICE);
                        entries.add(MiscItems.DOUGH_FLAT);
                        entries.add(DrinkItems.ENZYME);
                        entries.add(FoodItems.SAUSAGE_BREAD);
                        entries.add(FoodItems.SAUSAGE_BREAD_SLICE);
                        entries.add(MiscItems.FLOUR);
                        entries.add(MiscItems.TELECRYSTAL);
                        entries.add(MiscItems.COTTON);
                        entries.add(MiscItems.COTTON_RAW);
                        entries.add(FoodItems.COTTON_SEEDS);
                        entries.add(FoodItems.TOMATO);
                        entries.add(FoodItems.TOMATO_SEEDS);
                        entries.add(MiscItems.TRASH_PLASTIC);
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
                        entries.add(MiscItems.URANIUM);
                        entries.add(MiscItems.URANIUM_ORE);
                        entries.add(MiscItems.STEEL_ORE);
                        entries.add(MiscItems.STEEL);
                        entries.add(FoodItems.SPAGHETTI);
                        entries.add(MiscItems.PLASTEEL);
                        entries.add(MiscItems.ROD);
                        entries.add(MiscItems.PLASTIC);
                        entries.add(ToolItems.PLASTIC_KNIFE);
                        entries.add(MiscItems.PLASMA_ORE);
                        entries.add(MiscItems.PLASMA);
                        entries.add(PlushieItems.PLUSHIE_BEE);
                        entries.add(PlushieItems.PLUSHIE_IAN);
                        entries.add(PlushieItems.PLUSHIE_LIZARD);
                        entries.add(PlushieItems.PLUSHIE_XENO);
                        entries.add(FoodItems.PINEAPPLE);
                        entries.add(FoodItems.PIZZA_PINEAPPLE);
                        entries.add(FoodItems.PIZZA_MEAT);
                        entries.add(FoodItems.PIZZA_MEAT_RAW);
                        entries.add(FoodItems.PIZZA_MEAT_SLICE);
                        entries.add(FoodItems.PIZZA_MUSHROOM);
                        entries.add(FoodItems.PIZZA_MUSHROOM_RAW);
                        entries.add(FoodItems.PIZZA_MUSHROOM_SLICE);
                        entries.add(ToolItems.WRENCH);
                        entries.add(ToolItems.WELDER);
                        entries.add(MiscItems.ID_CARD);
                    })
                    .build()
    );

    private ModItemGroups() {}

    public static void register() {}
}