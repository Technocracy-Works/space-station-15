package org.corvaxcraft.spacestation.chemistry;

import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.corvaxcraft.spacestation.SpaceStation;

public class ModComponents {

    public static ComponentType<ChemData> CHEM_DATA;

    public static void register() {
        CHEM_DATA = Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Identifier.of(SpaceStation.MOD_ID, "chem_data"),
                ComponentType.<ChemData>builder()
                        .codec(ChemData.CODEC)
                        .packetCodec(ChemData.PACKET_CODEC)
                        .build()
        );
    }
}