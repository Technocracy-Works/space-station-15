package org.technocracy.spacestation.registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.chemistry.ChemData;
import org.technocracy.spacestation.registry.ModComponents;
import org.technocracy.spacestation.registry.items.*;

import java.util.Map;

public final class ModItemGroups {
    public static final ItemGroup CHEMISTRY = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(SpaceStation.MOD_ID, "chemistry"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ChemItems.BEAKER))
                    .displayName(Text.translatable("itemGroup.spacestation.chemistry"))
                    .entries((context, entries) -> {
                        entries.add(ChemItems.BEAKER);
                        entries.add(ChemItems.CANISTER);

                        String[] chemicals = {
                                "acetone", "aloxadone", "ambuzol", "ambuzol_plus", "ammonia",
                                "antiseptic", "arithrazine", "arkryox", "benzene", "bicaridine",
                                "bleach", "britvium", "bruizine", "carbon_dioxide", "charcoal",
                                "chloral_hydrate", "cognizine", "copper_sulfate", "cryoxadone",
                                "cryptobiolin", "dermaline", "desoxyephedrine", "dexalin",
                                "dexalin_plus", "diethylamine", "diphenhydramine",
                                "diphenylmethylamine", "doxarubixadone", "dylovene", "ephedrine",
                                "epinephrine", "ethyloxyephedrine", "ethylredoxrazine", "fertilizer",
                                "fluorosulfuric_acid", "fluorosurfactant", "foaming_agent", "glucose",
                                "haloperidol", "happiness", "heartbreaker_toxin", "hemorrhaginol",
                                "holy_water", "hydrogen_peroxide", "hydroxide", "hyperzine", "hyronalin",
                                "impedrezene", "inaprovaline", "insuzine", "ipecac", "iron_silicide",
                                "kelotane", "lacerinol", "leporazine", "lexorin", "lipolicide", "lipozine",
                                "local_anesthetic", "mannitol", "mindbreaker_toxin", "mute_toxin", "necrosol",
                                "nocturine", "norepinephrine_acid", "nutrient_paste", "nutrient_solution",
                                "oculine", "opporozidone", "paks", "phalangimine", "phenol",
                                "polytrinic_acid", "potassium_iodide", "psicodine", "puncturase", "pyrazine",
                                "saline", "siderlac", "sigynate", "sodium_carbonate", "sodium_chloride",
                                "sodium_hydroxide", "sodium_polycarbonate", "space_cleaner", "space_drugs",
                                "stimulants", "sulfuric_acid", "synaptizine", "table_salt", "tazinide",
                                "thermite", "tranexamic_acid", "tricordrazine", "ultravasculine",
                                "unstable_mutagen", "warfarin"
                        };
                        for (String chemical : chemicals) {
                            entries.add(chemicalBeaker(chemical));
                        }
                    })
                    .build()
    );

    public static final ItemGroup FOOD = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(SpaceStation.MOD_ID, "food"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(FoodItems.BREAD_SAUSAGE))
                    .displayName(Text.translatable("itemGroup.spacestation.food"))
                    .entries((context, entries) -> {
                        // ======== FOOD ========
                        entries.add(FoodItems.BANANIUM);
                        entries.add(FoodItems.BREAD_CORN);
                        entries.add(FoodItems.BREAD_CORN_SLICE);
                        entries.add(FoodItems.BUN);
                        entries.add(FoodItems.BUN_BOTTOM);
                        entries.add(FoodItems.BUN_TOP);
                        entries.add(FoodItems.BURGER_CHEESE);
                        entries.add(FoodItems.BURGER_FIVE_ALARM);
                        entries.add(FoodItems.BURGER_PLAIN);
                        entries.add(FoodItems.BUTTER);
                        entries.add(FoodItems.BUTTER_SLICE);
                        entries.add(FoodItems.CHEESE_WEDGE);
                        entries.add(FoodItems.CHEESE_WEDGE_FRESH);
                        entries.add(FoodItems.CHEESE_WHEEL);
                        entries.add(FoodItems.CHEESE_WHEEL_FRESH);
                        entries.add(FoodItems.CROISSANT);
                        entries.add(FoodItems.CROISSANT_RAW);
                        entries.add(FoodItems.CUTLET_COOKED);
                        entries.add(FoodItems.CUTLET_RAW);
                        entries.add(FoodItems.DOUGH);
                        entries.add(FoodItems.DOUGH_CORN);
                        entries.add(FoodItems.DOUGH_FLAT);
                        entries.add(FoodItems.DOUGH_SLICE);
                        entries.add(FoodItems.FLOUR);
                        entries.add(FoodItems.FLOUR_CORN);
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
                        entries.add(FoodItems.PIZZA_VEGETABLE);
                        entries.add(FoodItems.PIZZA_VEGETABLE_RAW);
                        entries.add(FoodItems.PIZZA_VEGETABLE_SLICE);
                        entries.add(FoodItems.SALAD_HERB);
                        entries.add(FoodItems.SALAD_VALID);
                        entries.add(FoodItems.BREAD_SAUSAGE);
                        entries.add(FoodItems.BREAD_SAUSAGE_RAW);
                        entries.add(FoodItems.BREAD_SAUSAGE_SLICE);
                        entries.add(FoodItems.SOUP_BUNGO);
                        entries.add(FoodItems.SOUP_NETTLE);
                        entries.add(FoodItems.SOUP_ONION);
                        entries.add(FoodItems.SOUP_PEA);
                        entries.add(FoodItems.SOUP_TOMATO_BLOOD);
                        entries.add(FoodItems.SPAGHETTI);
                        entries.add(FoodItems.BREAD_MEAT);
                        entries.add(FoodItems.BREAD_MEAT_RAW);
                        entries.add(FoodItems.BREAD_MEAT_SLICE);
                    })
                    .build()
    );

    public static final ItemGroup PLANTS = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(SpaceStation.MOD_ID, "plants"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(PlantItems.TOMATO))
                    .displayName(Text.translatable("itemGroup.spacestation.plants"))
                    .entries((context, entries) -> {
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
                        entries.add(PlantItems.TOWERCAP_SEEDS);
                    })
                    .build()
    );

    public static final ItemGroup CONTENT = Registry.register(
            Registries.ITEM_GROUP,
            Identifier.of(SpaceStation.MOD_ID, "content"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(MiscItems.STEEL))
                    .displayName(Text.translatable("itemGroup.spacestation.content"))
                    .entries((context, entries) -> {

                        // ======== BLOCKS ========
                        entries.add(ModBlocks.BANANIUM_BLOCK.asItem());
                        entries.add(ModBlocks.BANANIUM_ORE_BLOCK.asItem());
                        entries.add(ModBlocks.CHEM_MASTER.asItem());
                        entries.add(ModBlocks.PLASMA_BLOCK.asItem());
                        entries.add(ModBlocks.PLASMA_ORE_BLOCK.asItem());
                        entries.add(ModBlocks.STEEL_TILE.asItem());
                        entries.add(ModBlocks.STEEL_WALL.asItem());
                        entries.add(ModBlocks.STEEL_WALL_REINFORCED.asItem());
                        entries.add(ModBlocks.SUBLIMATOR.asItem());
                        entries.add(ModBlocks.SUSPICIOUS_GRASS.asItem());
                        entries.add(ModBlocks.TELECRYSTAL_BLOCK.asItem());
                        entries.add(ModBlocks.TELECRYSTAL_CRYSTAL_BLOCK.asItem());
                        entries.add(ModBlocks.URANIUM_BLOCK.asItem());
                        entries.add(ModBlocks.URANIUM_ORE_BLOCK.asItem());
                        entries.add(ModBlocks.MACHINE_FRAME.asItem());
                        entries.add(ModBlocks.MACHINE_FRAME_ASSEMBLED.asItem());
                        entries.add(ModBlocks.WALL_GIRDER.asItem());
                        entries.add(ModBlocks.WALL_GIRDER_REINFORCED.asItem());

                        // ======== CHEMISTRY ========
                        entries.add(ChemItems.BEAKER);
                        entries.add(ChemItems.CANISTER);
                        entries.add(ChemItems.COFFEE_POWDER);
                        entries.add(ChemItems.COPPER_SULFATE);
                        entries.add(ChemItems.SALT);
                        entries.add(ChemItems.UNSTABLE_MUTAGEN_POWDER);

                        // ======== DRINKABLES ========
                        entries.add(DrinkItems.ENZYME);

                        // ======== TOOLS ========
                        entries.add(ToolItems.CROWBAR);
                        entries.add(ToolItems.KNIFE_KITCHEN);
                        entries.add(ToolItems.LIGHTER);
                        entries.add(ToolItems.OMNITOOL);
                        entries.add(ToolItems.PLASTIC_KNIFE);
                        entries.add(ToolItems.ROLLING_PIN);
                        entries.add(ToolItems.SCREWDRIVER);
                        entries.add(ToolItems.WELDER);
                        entries.add(ToolItems.WRENCH);

                        // ======== ARMOR ========
                        entries.add(ArmorItems.STEEL_HELMET);
                        entries.add(ArmorItems.STEEL_CHESTPLATE);
                        entries.add(ArmorItems.STEEL_LEGGINGS);
                        entries.add(ArmorItems.STEEL_BOOTS);
                        entries.add(ArmorItems.NUKEOPS_HELMET);
                        entries.add(ArmorItems.NUKEOPS_CHESTPLATE);
                        entries.add(ArmorItems.NUKEOPS_LEGGINGS);
                        entries.add(ArmorItems.NUKEOPS_BOOTS);

                        // ======== PLUSHIES ========
                        entries.add(PlushieItems.PLUSHIE_BEE);
                        entries.add(PlushieItems.PLUSHIE_IAN);
                        entries.add(PlushieItems.PLUSHIE_LIZARD);
                        entries.add(PlushieItems.PLUSHIE_XENO);

                        // ======== MISC ========
                        entries.add(MiscItems.ASSEMBLY_KIT_CM);
                        entries.add(MiscItems.ASSEMBLY_KIT_SUBLIMATOR);
                        entries.add(MiscItems.BANANIUM);
                        entries.add(MiscItems.BANANIUM_ORE);
                        entries.add(MiscItems.BLOODY_RED_BALLISTIC_PLATE);
                        entries.add(MiscItems.BOWL_BIG);
                        entries.add(MiscItems.CABLE_HV);
                        entries.add(MiscItems.CABLE_LV);
                        entries.add(MiscItems.CIRCUIT_BASE);
                        entries.add(MiscItems.CLOTH);
                        entries.add(MiscItems.ID_CARD);
                        entries.add(MiscItems.FLINT_PURIFIED);
                        entries.add(MiscItems.PLASMA);
                        entries.add(MiscItems.PLASMA_ORE);
                        entries.add(MiscItems.PLASTEEL);
                        entries.add(MiscItems.PLASTIC);
                        entries.add(MiscItems.ROD);
                        entries.add(MiscItems.RUBBER_RAW);
                        entries.add(MiscItems.RUBBER);
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

    private static ItemStack chemicalBeaker(String chemical) {
        ItemStack stack = new ItemStack(ChemItems.BEAKER);
        stack.set(ModComponents.CHEM_DATA,
                new ChemData(Map.of(chemical, 100.0), ChemData.EMPTY_BEAKER.capacity()));
        return stack;
    }

    private ModItemGroups() {}

    public static void register() {}
}
