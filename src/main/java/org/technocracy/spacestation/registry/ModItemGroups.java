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

                        // ======== BLOCKS ========
                        entries.add(ModBlocks.BANANIUM_ORE_BLOCK.asItem());
                        entries.add(ModBlocks.CHEM_MASTER_BLOCK.asItem());
                        entries.add(ModBlocks.SUSPICIOUS_GRASS.asItem());
                        entries.add(ModBlocks.PLASMA_ORE_BLOCK.asItem());
                        entries.add(ModBlocks.STEEL_TILE.asItem());
                        entries.add(ModBlocks.STEEL_WALL.asItem());
                        entries.add(ModBlocks.STEEL_WALL_REINFORCED.asItem());
                        entries.add(ModBlocks.TELECRYSTAL_BLOCK.asItem());
                        entries.add(ModBlocks.TELECRYSTAL_CRYSTAL_BLOCK.asItem());
                        entries.add(ModBlocks.URANIUM_ORE_BLOCK.asItem());
                        entries.add(ModBlocks.WALL_GIRDER.asItem());
                        entries.add(ModBlocks.WALL_GIRDER_REINFORCED.asItem());

                        // ======== CHEMISTRY ========
                        entries.add(ChemItems.BEAKER);
                        entries.add(ChemItems.CANISTER);

                        // ======== DRINKABLES ========
                        entries.add(DrinkItems.ENZYME);

                        // ======== FOOD ========
                        entries.add(FoodItems.BANANIUM);
                        entries.add(FoodItems.BOWL_BIG);
                        entries.add(FoodItems.BUN);
                        entries.add(FoodItems.BUN_BOTTOM);
                        entries.add(FoodItems.BUN_TOP);
                        entries.add(FoodItems.BURGER);
                        entries.add(FoodItems.BURGER_CHEESE);
                        entries.add(FoodItems.CHEESE_WEDGE);
                        entries.add(FoodItems.CHEESE_WEDGE_FRESH);
                        entries.add(FoodItems.CHEESE_WHEEL);
                        entries.add(FoodItems.CHEESE_WHEEL_FRESH);
                        entries.add(FoodItems.CROISSANT);
                        entries.add(FoodItems.CROISSANT_RAW);
                        entries.add(FoodItems.CUTLET_COOKED);
                        entries.add(FoodItems.CUTLET_RAW);
                        entries.add(FoodItems.MEATBALL);
                        entries.add(FoodItems.MEATBALL_COOKED);
                        entries.add(FoodItems.PIZZA_MARGHERITA);
                        entries.add(FoodItems.PIZZA_MARGHERITA_RAW);
                        entries.add(FoodItems.PIZZA_MARGHERITA_SLICE);
                        entries.add(FoodItems.PIZZA_MEAT);
                        entries.add(FoodItems.PIZZA_MEAT_RAW);
                        entries.add(FoodItems.PIZZA_MEAT_SLICE);
                        entries.add(FoodItems.PIZZA_MUSHROOM);
                        entries.add(FoodItems.PIZZA_MUSHROOM_RAW);
                        entries.add(FoodItems.PIZZA_MUSHROOM_SLICE);
                        entries.add(FoodItems.PIZZA_PINEAPPLE);
                        entries.add(FoodItems.PIZZA_PINEAPPLE_RAW);
                        entries.add(FoodItems.PIZZA_PINEAPPLE_SLICE);
                        entries.add(FoodItems.SALAD_HERB);
                        entries.add(FoodItems.SALAD_VALID);
                        entries.add(FoodItems.SAUSAGE_BREAD);
                        entries.add(FoodItems.SAUSAGE_BREAD_RAW);
                        entries.add(FoodItems.SAUSAGE_BREAD_SLICE);
                        entries.add(FoodItems.SPAGHETTI);

                        // ======== TOOLS ========
                        entries.add(ToolItems.CROWBAR);
                        entries.add(ToolItems.CROWBAR_BRASS);
                        entries.add(ToolItems.CROWBAR_RED);
                        entries.add(ToolItems.KNIFE_KITCHEN);
                        entries.add(ToolItems.LIGHTER);
                        entries.add(ToolItems.OMNITOOL);
                        entries.add(ToolItems.PLASTIC_KNIFE);
                        entries.add(ToolItems.ROLLING_PIN);
                        entries.add(ToolItems.SCREWDRIVER);
                        entries.add(ToolItems.WELDER);
                        entries.add(ToolItems.WRENCH);

                        // ========= PLANTS ===========
                        entries.add(PlantItems.ALOE);
                        entries.add(PlantItems.ALOE_CREAM);
                        entries.add(PlantItems.ALOE_SEEDS);
                        entries.add(PlantItems.AMBROSIA);
                        entries.add(PlantItems.AMBROSIA_SEEDS);
                        entries.add(PlantItems.AMBROSIA_OLYMPIC);
                        entries.add(PlantItems.AMBROSIA_OLYMPIC_SEEDS);
                        entries.add(PlantItems.BLOOD_TOMATO);
                        entries.add(PlantItems.BLOOD_TOMATO_SEEDS);
                        entries.add(PlantItems.BLOONION);
                        entries.add(PlantItems.BLOONION_SEEDS);
                        entries.add(PlantItems.BLUE_TOMATO);
                        entries.add(PlantItems.BLUE_TOMATO_SEEDS);
                        entries.add(PlantItems.BUNGO);
                        entries.add(PlantItems.BUNGO_SEEDS);
                        entries.add(PlantItems.CABBAGE);
                        entries.add(PlantItems.CABBAGE_SEEDS);
                        entries.add(PlantItems.CHILI);
                        entries.add(PlantItems.CHILI_SEEDS);
                        entries.add(PlantItems.CHILLY);
                        entries.add(PlantItems.CHILLY_SEEDS);
                        entries.add(PlantItems.CORN);
                        entries.add(PlantItems.CORN_SEEDS);
                        entries.add(PlantItems.COTTON);
                        entries.add(PlantItems.COTTON_RAW);
                        entries.add(PlantItems.COTTON_SEEDS);
                        entries.add(PlantItems.DEATHBLOOM);
                        entries.add(PlantItems.DEATHBLOOM_SEEDS);
                        entries.add(PlantItems.EGGPLANT);
                        entries.add(PlantItems.EGGPLANT_SEEDS);
                        entries.add(PlantItems.EGGY_SEEDS);
                        entries.add(PlantItems.GARLIC);
                        entries.add(PlantItems.GARLIC_SEEDS);
                        entries.add(PlantItems.KOIBEAN);
                        entries.add(PlantItems.KOIBEAN_SEEDS);
                        entries.add(PlantItems.LAUGHIN_PEA);
                        entries.add(PlantItems.LAUGHIN_PEA_SEEDS);
                        entries.add(PlantItems.MEATWHEAT);
                        entries.add(PlantItems.MEATWHEAT_SEEDS);
                        entries.add(PlantItems.NETTLE);
                        entries.add(PlantItems.NETTLE_SEEDS);
                        entries.add(PlantItems.OAT);
                        entries.add(PlantItems.OAT_SEEDS);
                        entries.add(PlantItems.ONION);
                        entries.add(PlantItems.ONION_SEEDS);
                        entries.add(PlantItems.ONION_RED);
                        entries.add(PlantItems.ONION_RED_SEEDS);
                        entries.add(PlantItems.PEA);
                        entries.add(PlantItems.PEA_SEEDS);
                        entries.add(PlantItems.PINEAPPLE);
                        entries.add(PlantItems.PINEAPPLE_SEEDS);
                        entries.add(PlantItems.PYROTTON);
                        entries.add(PlantItems.PYROTTON_SEEDS);
                        entries.add(PlantItems.RICE);
                        entries.add(PlantItems.RICE_SEEDS);
                        entries.add(PlantItems.SOYBEANS);
                        entries.add(PlantItems.SOYBEANS_SEEDS);
                        entries.add(PlantItems.TOMATO);
                        entries.add(PlantItems.TOMATO_SEEDS);
                        entries.add(PlantItems.TOWERCAP_SEEDS); // (asnden): not implemented yet

                        // ======== PLUSHIES ========
                        entries.add(PlushieItems.PLUSHIE_BEE);
                        entries.add(PlushieItems.PLUSHIE_IAN);
                        entries.add(PlushieItems.PLUSHIE_LIZARD);
                        entries.add(PlushieItems.PLUSHIE_XENO);

                        // ======== MISC ========
                        entries.add(MiscItems.BANANIUM_ORE);
                        entries.add(MiscItems.BLOODY_RED_BALLISTIC_PLATE);
                        entries.add(MiscItems.BUTTER);
                        entries.add(MiscItems.BUTTER_SLICE);
                        entries.add(MiscItems.CLOTH);
                        entries.add(MiscItems.DOUGH);
                        entries.add(MiscItems.DOUGH_FLAT);
                        entries.add(MiscItems.DOUGH_SLICE);
                        entries.add(MiscItems.FLOUR);
                        entries.add(MiscItems.ID_CARD);
                        entries.add(MiscItems.PLASMA);
                        entries.add(MiscItems.PLASMA_ORE);
                        entries.add(MiscItems.PLASTEEL);
                        entries.add(MiscItems.PLASTIC);
                        entries.add(MiscItems.ROD);
                        entries.add(MiscItems.SOLID_FUEL);
                        entries.add(MiscItems.STEEL);
                        entries.add(MiscItems.STEEL_ORE);
                        entries.add(MiscItems.TELECRYSTAL);
                        entries.add(MiscItems.TRASH_PLASTIC);
                        entries.add(MiscItems.URANIUM);
                        entries.add(MiscItems.URANIUM_ORE);

                    })
                    .build()
    );

    private ModItemGroups() {}

    public static void register() {}
}
