package org.technocracy.spacestation.network;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;

public class ModPackets {

    // Пакет старта таймера
    public record TimerStartPayload(int totalTicks, boolean isDisassembly) implements CustomPayload {
        public static final Id<TimerStartPayload> ID =
                new Id<>(Identifier.of(SpaceStation.MOD_ID, "timer_start"));
        public static final PacketCodec<PacketByteBuf, TimerStartPayload> CODEC =
                PacketCodec.tuple(
                        PacketCodecs.INTEGER, TimerStartPayload::totalTicks,
                        PacketCodecs.BOOL, TimerStartPayload::isDisassembly,
                        TimerStartPayload::new
                );
        @Override public Id<TimerStartPayload> getId() { return ID; }
    }

    // Пакет отмены
    public record TimerCancelPayload() implements CustomPayload {
        public static final Id<TimerCancelPayload> ID =
                new Id<>(Identifier.of(SpaceStation.MOD_ID, "timer_cancel"));
        public static final PacketCodec<PacketByteBuf, TimerCancelPayload> CODEC =
                PacketCodec.unit(new TimerCancelPayload());
        @Override public Id<TimerCancelPayload> getId() { return ID; }
    }

    // Пакет завершения
    public record TimerCompletePayload(boolean isDisassembly) implements CustomPayload {
        public static final Id<TimerCompletePayload> ID =
                new Id<>(Identifier.of(SpaceStation.MOD_ID, "timer_complete"));
        public static final PacketCodec<PacketByteBuf, TimerCompletePayload> CODEC =
                PacketCodec.tuple(
                        PacketCodecs.BOOL, TimerCompletePayload::isDisassembly,
                        TimerCompletePayload::new
                );
        @Override public Id<TimerCompletePayload> getId() { return ID; }
    }

    // Пакет перемещения химиката
    public record ChemMovePayload(String chem, double amount, boolean toContainer) implements CustomPayload {
        public static final Id<ChemMovePayload> ID =
                new Id<>(Identifier.of(SpaceStation.MOD_ID, "chem_move"));
        public static final PacketCodec<PacketByteBuf, ChemMovePayload> CODEC =
                PacketCodec.tuple(
                        PacketCodecs.STRING, ChemMovePayload::chem,
                        PacketCodecs.DOUBLE, ChemMovePayload::amount,
                        PacketCodecs.BOOL, ChemMovePayload::toContainer,
                        ChemMovePayload::new
                );
        @Override public Id<ChemMovePayload> getId() { return ID; }
    }

    public static void register() {
        PayloadTypeRegistry.playS2C().register(TimerStartPayload.ID, TimerStartPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(TimerCancelPayload.ID, TimerCancelPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(TimerCompletePayload.ID, TimerCompletePayload.CODEC);
    }

    public static void sendTimerStart(ServerPlayerEntity player, int ticks, boolean isDisassembly) {
        ServerPlayNetworking.send(player, new TimerStartPayload(ticks, isDisassembly));
    }

    public static void sendTimerCancel(ServerPlayerEntity player) {
        ServerPlayNetworking.send(player, new TimerCancelPayload());
    }

    public static void sendTimerComplete(ServerPlayerEntity player, boolean isDisassembly) {
        ServerPlayNetworking.send(player, new TimerCompletePayload(isDisassembly));
    }
}
