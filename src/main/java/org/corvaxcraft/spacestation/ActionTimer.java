package org.corvaxcraft.spacestation;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class ActionTimer {

    record ActiveAction(
            BlockPos pos,
            Vec3d startPos,
            int totalTicks,
            int remainingTicks,
            Consumer<PlayerEntity> onComplete,
            boolean isDisassembly
    ) {}

    private static final Map<UUID, ActiveAction> ACTIVE = new HashMap<>();

    public static void start(ServerPlayerEntity player, BlockPos pos,
                             float seconds, boolean isDisassembly,
                             Consumer<PlayerEntity> onComplete) {
        int ticks = Math.round(seconds * 20);
        ACTIVE.put(player.getUuid(), new ActiveAction(
                pos, player.getPos(), ticks, ticks, onComplete, isDisassembly
        ));
        // Отправляем клиенту старт
        ModPackets.sendTimerStart(player, ticks, isDisassembly);
    }

    public static void cancel(ServerPlayerEntity player) {
        if (ACTIVE.containsKey(player.getUuid())) {
            ACTIVE.remove(player.getUuid());
            ModPackets.sendTimerCancel(player);
        }
    }

    public static void tick(ServerPlayerEntity player) {
        UUID uuid = player.getUuid();
        ActiveAction action = ACTIVE.get(uuid);
        if (action == null) return;

        // Отменяем если игрок сдвинулся
        if (player.getPos().distanceTo(action.startPos()) > 0.2) {
            cancel(player);
            return;
        }

        // Отменяем если блок уже не тот (например его сломали)
        if (player.getWorld().getBlockState(action.pos()).getBlock()
                != player.getWorld().getBlockState(action.pos()).getBlock()) {
            cancel(player);
            return;
        }

        int remaining = action.remainingTicks() - 1;

        if (remaining <= 0) {
            ACTIVE.remove(uuid);
            action.onComplete().accept(player);
            ModPackets.sendTimerComplete(player, action.isDisassembly());
        } else {
            ACTIVE.put(uuid, new ActiveAction(
                    action.pos(), action.startPos(), action.totalTicks(),
                    remaining, action.onComplete(), action.isDisassembly()
            ));
        }
    }

    public static boolean isActive(ServerPlayerEntity player, BlockPos pos) {
        ActiveAction action = ACTIVE.get(player.getUuid());
        return action != null && action.pos().equals(pos);
    }

    public static void cancelAll() {
        ACTIVE.clear();
    }
}