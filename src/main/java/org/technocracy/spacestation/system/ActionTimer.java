package org.technocracy.spacestation.system;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.technocracy.spacestation.network.ModPackets;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class ActionTimer {

    record ActiveAction(
            BlockPos pos,
            Vec3d startPos,
            int totalTicks,
            int remainingTicks,
            Consumer<PlayerEntity> onComplete,
            boolean isDisassembly,
            Predicate<PlayerEntity> canContinue
    ) {}

    private static final Map<UUID, ActiveAction> ACTIVE = new HashMap<>();

    public static void start(ServerPlayerEntity player, BlockPos pos,
                             float seconds, boolean isDisassembly,
                             Consumer<PlayerEntity> onComplete) {
        start(player, pos, seconds, isDisassembly, onComplete, p -> true);
    }
    public static void start(ServerPlayerEntity player, BlockPos pos,
                             float seconds, boolean isDisassembly,
                             Consumer<PlayerEntity> onComplete,
                             Predicate<PlayerEntity> canContinue) {
        int ticks = Math.round(seconds * 20);
        ACTIVE.put(player.getUuid(), new ActiveAction(
                pos, player.getPos(), ticks, ticks, onComplete, isDisassembly, canContinue
        ));
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

        if (!action.canContinue().test(player)) {
            cancel(player);
            return;
        }

        if (player.getPos().distanceTo(action.startPos()) > 0.2) {
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
                    remaining, action.onComplete(), action.isDisassembly(), action.canContinue()
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
