package org.technocracy.spacestation.registry.components;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.chemistry.ChemData;

public class ModComponents {
    public static ComponentType<ChemData> CHEM_DATA;
    public static ComponentType<Float> USE_DELAY_COMPONENT;
    public static ComponentType<Boolean> ITEM_TOGGLE_COMPONENT;
    public static ComponentType<ChargeData> CHARGE_COMPONENT;

    public static void register() {
        CHEM_DATA = register("chem_data", ChemData.CODEC, ChemData.PACKET_CODEC);
        USE_DELAY_COMPONENT = register("use_delay", Codec.FLOAT);
        ITEM_TOGGLE_COMPONENT = register("item_toggle", Codec.BOOL);
        CHARGE_COMPONENT = register("charge", ChargeData.CODEC, ChargeData.PACKET_CODEC);
    }

    public static <T> ComponentType<T> register(String name, Codec<T> codec)
    {
        return Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Identifier.of(SpaceStation.MOD_ID, name),
                ComponentType.<T>builder()
                        .codec(codec)
                        .build()
        );
    }

    public static <T, B> ComponentType<T> register(String name, Codec<T> codec, PacketCodec<? super net.minecraft.network.RegistryByteBuf, T> packetCodec)
    {
        return Registry.register(
                Registries.DATA_COMPONENT_TYPE,
                Identifier.of(SpaceStation.MOD_ID, name),
                ComponentType.<T>builder()
                        .codec(codec)
                        .packetCodec(packetCodec)
                        .build()
        );
    }
}
