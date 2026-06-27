package org.corvaxcraft.spacestation.registry;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.corvaxcraft.spacestation.SpaceStation;
import org.corvaxcraft.spacestation.chemistry.ChemContainer;
import org.corvaxcraft.spacestation.chemistry.ChemData;
import org.corvaxcraft.spacestation.chemistry.ModComponents;

public final class ModItems {

    public static class DrinkableItem extends Item {

        private final Item remainderItem;

        public DrinkableItem(Settings settings) {
            this(settings, Items.GLASS_BOTTLE);
        }

        public DrinkableItem(Settings settings, Item remainderItem) {
            super(settings);
            this.remainderItem = remainderItem;
        }

        @Override
        public UseAction getUseAction(ItemStack stack) {
            return UseAction.DRINK;
        }

        @Override
        public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
            ItemStack result = super.finishUsing(stack, world, user);

            // Креатив без депа бутылок
            if (user instanceof PlayerEntity player && player.getAbilities().creativeMode) {
                return result;
            }

            if (!world.isClient() && remainderItem != null) {
                ItemStack remainder = new ItemStack(remainderItem);

                if (user instanceof PlayerEntity player) {
                    if (!player.getInventory().insertStack(remainder)) {
                        player.dropItem(remainder, false);
                    }
                } else {
                    user.dropStack(remainder);
                }
            }

            return result;
        }
    }


    public static final Item BANANIUM = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "bananium"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(2) // сколько восстанавливает голода
                            .saturationModifier(0.3f) // насыщение
                            .statusEffect(
                                    new StatusEffectInstance(StatusEffects.POISON, 30, 4), // 10 секунд
                                    1.0f // шанс (1.0 = 100%)
                            )
                            .build()
            ))
    );

    public static final Item BUN = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "bun"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(2)
                            .saturationModifier(0.2f)
                            .build()
            ))
    );

    public static final Item BUN_BOTTOM = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "bun_bottom"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(1)
                            .saturationModifier(0.1f)
                            .build()
            ))
    );

    public static final Item BUN_TOP = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "bun_top"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(1)
                            .saturationModifier(0.1f)
                            .build()
            ))
    );

    public static final Item BURGER = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "burger"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(8)
                            .saturationModifier(0.8f)
                            .build()
            ))
    );

    public static final Item BURGER_CHEESE = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "burger_cheese"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(9)
                            .saturationModifier(0.9f)
                            .build()
            ))
    );

    public static final Item CUTLET_RAW = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "cutlet_raw"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(3)
                            .saturationModifier(0.1f)
                            .statusEffect(
                                    new StatusEffectInstance(StatusEffects.POISON, 220, 0),
                                    0.2f
                            )
                            .build()
            ))
    );

    public static final Item CUTLET_COOKED = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "cutlet_cooked"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(4)
                            .saturationModifier(0.8f)
                            .build()
            ))
    );

    public static final Item CROISSANT = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "croissant"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(5)
                            .saturationModifier(0.7f)
                            .build()
            ))
    );

    public static final Item CROWBAR = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "crowbar"),
            new Item(new Item.Settings()
                    .maxCount(1)
                    .maxDamage(100) 
            )
    );

    public static final Item CROWBAR_RED = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "crowbar_red"),
            new Item(new Item.Settings()
                    .maxCount(1)
                    .maxDamage(100)
            )
    );

    public static final Item CROWBAR_BRASS = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "crowbar_brass"),
            new Item(new Item.Settings()
                    .maxCount(1)
                    .maxDamage(100)
            )
    );

    public static final Item SCREWDRIVER = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "screwdriver"),
            new Item(new Item.Settings()
                    .maxCount(1)
                    .maxDamage(100)
            )
    );

    public static final Item WRENCH = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "wrench"),
            new Item(new Item.Settings()
                    .maxCount(1)
                    .maxDamage(100)
            )
    );

    public static final Item SAUSAGE_BREAD = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "sausage_bread"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(6)
                            .saturationModifier(1.3f)
                            .build()
            ))
    );

    public static final Item SAUSAGE_BREAD_SLICE = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "sausage_bread_slice"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(2)
                            .saturationModifier(0.6f)
                            .build()
            ))
    );

    public static final Item Spaghetti = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "spaghetti"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(2)
                                    .saturationModifier(1.5f)
                                    .build()
                    )
            )
    );

    public static final Item XENOMORPH_PLUSH_TOY = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "xenomprph_plush_toy"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(2)
                                    .saturationModifier(1.5f)
                                    .build()
                    )
            )
    );

    public static final Item CROISSANT_RAW = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "croissant_raw"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(2)
                            .saturationModifier(0.3f)
                            .build()
            ))
    );

    public static final Item ENZYME = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "enzyme"),
            new DrinkableItem(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .alwaysEdible()
                                    .nutrition(2)
                                    .saturationModifier(0.3f)
                                    .statusEffect(
                                            new StatusEffectInstance(StatusEffects.POISON, 30, 4),
                                            1.0f
                                    )
                                    .build()
                    )
                    .recipeRemainder(Items.GLASS_BOTTLE) // возвращает бутылку
                    .maxCount(16) // макс. стак
            )
    );

    public static final Item CHEESE_WHEEL = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "cheese_wheel"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(14)
                                    .saturationModifier(4f)
                                    .build()
                    )
                    .maxCount(1)
            )
    );

    public static final Item CHEESE_WHEEL_FRESH = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "cheese_wheel_fresh"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(5)
                                    .saturationModifier(1.5f)
                                    .build()
                    )
                    .maxCount(1)
            )
    );

    public static final Item CHEESE_WEDGE = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "cheese_wedge"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(2)
                            .saturationModifier(1f)
                            .build()
            ))
    );

    public static final Item CHEESE_WEDGE_FRESH = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "cheese_wedge_fresh"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(1)
                            .saturationModifier(0.2f)
                            .build()
            ))
    );

    public static final Item BANANIUM_ORE = register("bananium_ore");
    public static final Item CLOTH = register("cloth");
    public static final Item PLASMA_ORE = register("plasma_ore");
    public static final Item PLASMA = register("plasma");
    public static final Item FLOUR = register("flour");
    public static final Item BUTTER = register("butter");
    public static final Item BUTTER_SLICE = register("butter_slice");
    public static final Item DOUGH = register("dough");
    public  static final Item DOUGH_SLICE = register("dough_slice");
    public static final Item DOUGH_FLAT = register("dough_flat");
    public static final Item TELECRYSTAL = register("telecrystal");

    public static final Item COTTON = register("cotton");
    public static final Item COTTON_RAW = register("cotton_raw");

    public static final Item COTTON_SEEDS = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "cotton_seeds"),
            new AliasedBlockItem(ModBlocks.COTTON_CROP, new Item.Settings())
    );

    public static final Item TOMATO = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "tomato"),
            new Item(new Item.Settings().food(
                    new FoodComponent.Builder()
                            .nutrition(3)
                            .saturationModifier(0.3f)
                            .build()
            ))
    );

    public static final Item TOMATO_SEEDS = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "tomato_seeds"),
            new AliasedBlockItem(ModBlocks.TOMATO_CROP, new Item.Settings())
    );

    public static final Item TOWERCAP_SEEDS = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "towercap_seeds"),
            new AliasedBlockItem(ModBlocks.TOWERCAP_CROP, new Item.Settings())
    );

    public static final Item TRASH_PLASTIC = register("trash_plastic");

    public static final Item STEEL_ORE = register("steel_ore");
    public static final Item URANIUM = register("uranium");
    public static final Item URANIUM_ORE = register("uranium_ore");
    public static final Item STEEL = register("steel");
    public static final Item PLASTEEL = register("plasteel");
    public static final Item ROD = register("rod");
    public static final Item PLASTIC = register("plastic");

    public static final Item PLASTIC_KNIFE = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "plastic_knife"),
            new Item(new Item.Settings()
                    .recipeRemainder(ModItems.PLASTIC)
                    .maxCount(16)
            )
    );

    public static final Item PIZZA_MEAT_RAW = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "pizza_meat_raw"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(4)
                                    .saturationModifier(1f)
                                    .build()
                    )
                    .maxCount(1)
            )
    );

    public static final Item PIZZA_Pineapple = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "pizza_pineapple"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(2)
                                    .saturationModifier(1.5f)
                                    .build()
                    )
            )
    );

    public static final Item Pineapple = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "pineapple"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(2)
                                    .saturationModifier(1.5f)
                                    .build()
                    )
            )
    );

    public static final Item PIZZA_MEAT_SLICE = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "pizza_meat_slice"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(2)
                                    .saturationModifier(1.5f)
                                    .build()
                    )
            )
    );

    public static final Item PIZZA_MEAT = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "pizza_meat"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(16)
                                    .saturationModifier(6f)
                                    .build()
                    )
                    .maxCount(1)
            )
    );

    public static final Item PIZZA_MUSHROOM_RAW = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "pizza_mushroom_raw"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(4)
                                    .saturationModifier(1f)
                                    .build()
                    )
                    .maxCount(1)
            )
    );

    public static final Item PIZZA_MUSHROOM_SLICE = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "pizza_mushroom_slice"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(2)
                                    .saturationModifier(1.5f)
                                    .build()
                    )
            )
    );

    public static final Item PIZZA = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "pizza"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(2)
                                    .saturationModifier(1.5f)
                                    .build()
                    )
            )
    );

    public static final Item PIZZA_MUSHROOM = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "pizza_mushroom"),
            new Item(new Item.Settings()
                    .food(
                            new FoodComponent.Builder()
                                    .nutrition(16)
                                    .saturationModifier(6f)
                                    .build()
                    )
                    .maxCount(1)
            )
    );

    public static final Item PLUSHIE_BEE = register("plushie_bee");
    public static final Item PLUSHIE_IAN = register("plushie_ian");
    public static final Item PLUSHIE_LIZARD = register("plushie_lizard");

    public static final Item ID_CARD = register("id_card");

    public static final Item WELDER = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "welder"),
            new Item(new Item.Settings()
                    .maxCount(1)
                    .maxDamage(100)
            )
    );

    public static final Item BEAKER = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "beaker"),
            new ChemContainer(new Item.Settings()
                    .maxCount(1)
                    .component(ModComponents.CHEM_DATA, ChemData.EMPTY_BEAKER))
    );

    public static final Item CANISTER = Registry.register(
            Registries.ITEM,
            Identifier.of(SpaceStation.MOD_ID, "canister"),
            new ChemContainer(new Item.Settings()
                    .maxCount(1)
                    .component(ModComponents.CHEM_DATA, ChemData.EMPTY_CANISTER))
    );

    private ModItems() {}

    private static Item register(String name) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(SpaceStation.MOD_ID, name),
                new Item(new Item.Settings())
        );
    }

    public static void register() {
    }
}
