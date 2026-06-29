package org.technocracy.spacestation.registry.items;

import net.minecraft.component.type.FoodComponent;
import net.minecraft.item.AliasedBlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.chemistry.ChemContainer;
import org.technocracy.spacestation.chemistry.ChemData;
import org.technocracy.spacestation.chemistry.ModComponents;
import org.technocracy.spacestation.item.SqueakyPlushieItem;

public final class ItemRegistry {

    private ItemRegistry() {}

    public static Item register(String name) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(SpaceStation.MOD_ID, name),
                new Item(new Item.Settings())
        );
    }

    public static Item register(String name, Item item) {
        return Registry.register(
                Registries.ITEM,
                Identifier.of(SpaceStation.MOD_ID, name),
                item
        );
    }

    public static Item registerFood(String name, FoodComponent food) {
        return register(
                name,
                new Item(new Item.Settings().food(food))
        );
    }

    public static Item registerCrop(String name, net.minecraft.block.Block crop) {
        return register(
                name,
                new AliasedBlockItem(crop, new Item.Settings())
        );
    }

    public static Item registerSqueakyPlushie(String name, SoundEvent... sounds) {
        return register(
                name,
                new SqueakyPlushieItem(new Item.Settings(), sounds)
        );
    }

    public static Item registerChemContainer(String name, ChemData defaultData) {
        return register(
                name,
                new ChemContainer(
                        new Item.Settings()
                                .maxCount(1)
                                .component(ModComponents.CHEM_DATA, defaultData)
                )
        );
    }
}