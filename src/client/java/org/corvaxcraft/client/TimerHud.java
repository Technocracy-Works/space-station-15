package org.corvaxcraft.client;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import org.corvaxcraft.spacestation.ModPackets;

public class TimerHud {

    private static int totalTicks = 0;
    private static int currentTick = 0;
    private static boolean isDisassembly = false;

    // Состояния: IDLE, ACTIVE, COMPLETE, CANCELLED
    private enum State { IDLE, ACTIVE, COMPLETE, CANCELLED }
    private static State state = State.IDLE;
    private static int flashTicks = 0; // сколько тиков показываем финальный цвет

    public static void register() {
        // Слушаем пакеты
        ClientPlayNetworking.registerGlobalReceiver(ModPackets.TimerStartPayload.ID, (payload, ctx) -> {
            totalTicks = payload.totalTicks();
            currentTick = 0;
            isDisassembly = payload.isDisassembly();
            state = State.ACTIVE;
            flashTicks = 0;
        });

        ClientPlayNetworking.registerGlobalReceiver(ModPackets.TimerCancelPayload.ID, (payload, ctx) -> {
            state = State.CANCELLED;
            flashTicks = 10; // 0.5 секунды показываем красный
        });

        ClientPlayNetworking.registerGlobalReceiver(ModPackets.TimerCompletePayload.ID, (payload, ctx) -> {
            currentTick = totalTicks;
            state = State.COMPLETE;
            flashTicks = 10; // 0.5 секунды показываем зелёный
        });

        // Тикаем прогресс
        HudRenderCallback.EVENT.register(TimerHud::render);
    }

    private static void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // Тикаем флеш
        if (flashTicks > 0) {
            flashTicks--;
            if (flashTicks == 0) state = State.IDLE;
        }

        if (state == State.IDLE) return;

        // Тикаем прогресс
        if (state == State.ACTIVE) {
            currentTick++;
            if (currentTick > totalTicks) currentTick = totalTicks;
        }

        int screenWidth = context.getScaledWindowWidth();
        int screenHeight = context.getScaledWindowHeight();

        int barWidth = 100;
        int barHeight = 8;
        int x = (screenWidth - barWidth) / 2;
        int y = screenHeight - 55; // чуть выше хотбара

        float progress = totalTicks > 0 ? (float) currentTick / totalTicks : 0f;

        // Цвет в зависимости от состояния
        int fillColor;
        if (state == State.COMPLETE) {
            fillColor = 0xFF22CC44; // зелёный
        } else if (state == State.CANCELLED) {
            fillColor = 0xFFCC2222; // красный
        } else {
            fillColor = isDisassembly
                    ? 0xFFEEAA00  // оранжевый для разборки
                    : 0xFF4488FF; // синий для сборки
        }

        // Фон бара
        context.fill(x - 1, y - 1, x + barWidth + 1, y + barHeight + 1, 0x88000000);
        // Заливка прогресса
        context.fill(x, y, x + (int)(barWidth * progress), y + barHeight, fillColor);

        // Текст с секундами
        if (state == State.ACTIVE) {
            float secondsLeft = (float)(totalTicks - currentTick) / 20f;
            String text = String.format("%.1fs", secondsLeft);
            int textX = (screenWidth - client.textRenderer.getWidth(text)) / 2;
            context.drawText(client.textRenderer, text, textX, y - 10, 0xFFFFFFFF, true);
        }
    }
}