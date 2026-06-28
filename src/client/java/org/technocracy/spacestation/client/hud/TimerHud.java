package org.technocracy.spacestation.client.hud;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.RenderTickCounter;
import org.technocracy.spacestation.network.ModPackets;

public class TimerHud {

    private static long startTimeMs = 0;
    private static long totalTimeMs = 0;
    private static boolean isDisassembly = false;

    private enum State { IDLE, ACTIVE, COMPLETE, CANCELLED }
    private static State state = State.IDLE;
    private static long flashEndMs = 0;

    public static void register() {
        ClientPlayNetworking.registerGlobalReceiver(ModPackets.TimerStartPayload.ID, (payload, ctx) -> {
            totalTimeMs = (long)(payload.totalTicks() / 20f * 1000);
            startTimeMs = System.currentTimeMillis();
            isDisassembly = payload.isDisassembly();
            state = State.ACTIVE;
            flashEndMs = 0;
        });

        ClientPlayNetworking.registerGlobalReceiver(ModPackets.TimerCancelPayload.ID, (payload, ctx) -> {
            state = State.CANCELLED;
            flashEndMs = System.currentTimeMillis() + 500; // 0.5 секунды красного
        });

        ClientPlayNetworking.registerGlobalReceiver(ModPackets.TimerCompletePayload.ID, (payload, ctx) -> {
            state = State.COMPLETE;
            flashEndMs = System.currentTimeMillis() + 500; // 0.5 секунды зелёного
        });

        HudRenderCallback.EVENT.register(TimerHud::render);
    }

    private static void render(DrawContext context, RenderTickCounter tickCounter) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        // Прячем после флеша
        if ((state == State.COMPLETE || state == State.CANCELLED) &&
                System.currentTimeMillis() > flashEndMs) {
            state = State.IDLE;
        }

        if (state == State.IDLE) return;

        int screenWidth = context.getScaledWindowWidth();
        int screenHeight = context.getScaledWindowHeight();

        int barWidth = 100;
        int barHeight = 8;
        int x = (screenWidth - barWidth) / 2;
        int y = screenHeight - 55;

        float progress;
        if (state == State.ACTIVE) {
            long elapsed = System.currentTimeMillis() - startTimeMs;
            progress = Math.min(1f, (float) elapsed / totalTimeMs);
        } else {
            progress = state == State.COMPLETE ? 1f : // зелёный — полный
                    (float)(System.currentTimeMillis() - startTimeMs) / totalTimeMs; // красный — где остановился
        }

        int fillColor;
        if (state == State.COMPLETE) {
            fillColor = 0xFF22CC44;
        } else if (state == State.CANCELLED) {
            fillColor = 0xFFCC2222;
        } else {
            fillColor = isDisassembly ? 0xFFEEAA00 : 0xFF4488FF;
        }

        // Фон
        context.fill(x - 1, y - 1, x + barWidth + 1, y + barHeight + 1, 0x88000000);
        // Прогресс
        context.fill(x, y, x + (int)(barWidth * progress), y + barHeight, fillColor);

        // Текст с секундами только во время активного таймера
        if (state == State.ACTIVE) {
            long elapsed = System.currentTimeMillis() - startTimeMs;
            float secondsLeft = Math.max(0, (totalTimeMs - elapsed) / 1000f);
            String text = String.format("%.1fs", secondsLeft);
            int textX = (screenWidth - client.textRenderer.getWidth(text)) / 2;
            context.drawText(client.textRenderer, text, textX, y - 10, 0xFFFFFFFF, true);
        }
    }
}
