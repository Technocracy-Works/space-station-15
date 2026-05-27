package org.corvaxcraft.spacestation;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import org.corvaxcraft.spacestation.chemistry.*;

public class SpaceStation implements ModInitializer {
    public static final String MOD_ID = "spacestation";

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            server.getPlayerManager().getPlayerList()
                    .forEach(ActionTimer::tick);
        });

        ModComponents.register();
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