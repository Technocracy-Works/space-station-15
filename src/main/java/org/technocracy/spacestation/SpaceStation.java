package org.technocracy.spacestation;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import org.technocracy.spacestation.chemistry.*;
import org.technocracy.spacestation.network.ModPackets;
import org.technocracy.spacestation.registry.*;
import org.technocracy.spacestation.system.ActionTimer;
import org.technocracy.spacestation.world.ModLootModifiers;
import org.technocracy.spacestation.world.ModWorldGeneration;

public class SpaceStation implements ModInitializer {
    public static final String MOD_ID = "spacestation";

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            server.getPlayerManager().getPlayerList()
                    .forEach(ActionTimer::tick);
        });

        ModComponents.register();
        ModSounds.register();
        ModItems.register();
        ModBlocks.register();
        ModBlockEntities.register();
        ModScreenHandlers.register();
        ChemRegistry.register();
        ModItemGroups.register();
        ModWorldGeneration.init();
        ModLootModifiers.register();
        ModPackets.register();
        PayloadTypeRegistry.playC2S().register(ModPackets.ChemMovePayload.ID, ModPackets.ChemMovePayload.CODEC);

        ServerPlayNetworking.registerGlobalReceiver(ModPackets.ChemMovePayload.ID, (payload, ctx) -> {
            ctx.server().execute(() -> {
                PlayerEntity player = ctx.player();
                if (player.currentScreenHandler instanceof ChemMasterScreenHandler handler) {
                    if (payload.toContainer()) {
                        handler.moveToContainer(payload.chem(), payload.amount());
                    } else {
                        handler.moveToMaster(payload.chem(), payload.amount());
                    }
                }
            });
        });
    }
}
