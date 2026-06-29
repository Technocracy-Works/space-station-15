package org.technocracy.spacestation.registry.items;

import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.chemistry.ChemContainer;
import org.technocracy.spacestation.chemistry.ChemData;
import org.technocracy.spacestation.chemistry.ModComponents;

public final class ChemItems {

    public static final Item BEAKER = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "beaker"),
            new ChemContainer(new Item.Settings()
                    .maxCount(1)
                    .component(ModComponents.CHEM_DATA, ChemData.EMPTY_BEAKER))
    );

    public static final Item CANISTER = Registry.register(
            Registries.ITEM, Identifier.of(SpaceStation.MOD_ID, "canister"),
            new ChemContainer(new Item.Settings()
                    .maxCount(1)
                    .component(ModComponents.CHEM_DATA, ChemData.EMPTY_CANISTER))
    );

    private ChemItems() {}

    public static void register() {}
}