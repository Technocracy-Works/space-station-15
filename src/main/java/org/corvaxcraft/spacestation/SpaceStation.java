package org.corvaxcraft.spacestation;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;

public class SpaceStation implements ModInitializer {
    public static final String MOD_ID = "spacestation";

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            server.getPlayerManager().getPlayerList()
                    .forEach(ActionTimer::tick);
        });
        ModItems.register();
        ModBlocks.register();
        ModItemGroups.register();
        ModWorldGeneration.init();
        ModLootModifiers.register();
        ModPackets.register();
    }
}