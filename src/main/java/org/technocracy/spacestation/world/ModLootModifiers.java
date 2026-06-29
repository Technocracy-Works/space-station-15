package org.technocracy.spacestation.world;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKey;
import net.minecraft.item.Item;
import net.minecraft.loot.LootTable;
import org.technocracy.spacestation.registry.items.*;

import java.util.List;
import java.util.Map;

public class ModLootModifiers {

    public static void register() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (!source.isBuiltin()) return;

            addSeedLoot(key, tableBuilder, FoodItems.COTTON_SEEDS, Map.of(
                    List.of(
                            LootTables.VILLAGE_PLAINS_CHEST,
                            LootTables.VILLAGE_SAVANNA_HOUSE_CHEST,
                            LootTables.VILLAGE_TAIGA_HOUSE_CHEST,
                            LootTables.VILLAGE_SNOWY_HOUSE_CHEST,
                            LootTables.VILLAGE_DESERT_HOUSE_CHEST
                    ), 0.3f,
                    List.of(LootTables.SHIPWRECK_SUPPLY_CHEST), 0.2f,
                    List.of(LootTables.SIMPLE_DUNGEON_CHEST), 0.15f
            ));

            addSeedLoot(key, tableBuilder, FoodItems.TOMATO_SEEDS, Map.of(
                    List.of(
                            LootTables.VILLAGE_PLAINS_CHEST,
                            LootTables.VILLAGE_SAVANNA_HOUSE_CHEST,
                            LootTables.VILLAGE_TAIGA_HOUSE_CHEST,
                            LootTables.VILLAGE_SNOWY_HOUSE_CHEST,
                            LootTables.VILLAGE_DESERT_HOUSE_CHEST
                    ), 0.3f,
                    List.of(LootTables.SHIPWRECK_SUPPLY_CHEST), 0.2f,
                    List.of(LootTables.SIMPLE_DUNGEON_CHEST), 0.15f
            ));

            addSeedLoot(key, tableBuilder, FoodItems.TOWERCAP_SEEDS, Map.of(
                    List.of(
                            LootTables.VILLAGE_PLAINS_CHEST,
                            LootTables.VILLAGE_SAVANNA_HOUSE_CHEST,
                            LootTables.VILLAGE_TAIGA_HOUSE_CHEST,
                            LootTables.VILLAGE_SNOWY_HOUSE_CHEST,
                            LootTables.VILLAGE_DESERT_HOUSE_CHEST
                    ), 0.3f,
                    List.of(LootTables.SHIPWRECK_SUPPLY_CHEST), 0.2f,
                    List.of(LootTables.SIMPLE_DUNGEON_CHEST), 0.15f
            ));

            // Что-бы добавить новые семена:
            // addSeedLoot(key, tableBuilder, ModItems.CORN_SEEDS, Map.of(...));
        });
    }

    private static void addSeedLoot(
            RegistryKey<LootTable> key,
            LootTable.Builder tableBuilder,
            Item item,
            Map<List<RegistryKey<LootTable>>, Float> chanceMap
    ) {
        float chance = 0.0f;

        for (Map.Entry<List<RegistryKey<LootTable>>, Float> entry : chanceMap.entrySet()) {
            if (entry.getKey().contains(key)) {
                chance = entry.getValue();
                break;
            }
        }

        if (chance > 0) {
            tableBuilder.pool(LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .with(ItemEntry.builder(item)
                            .apply(SetCountLootFunction.builder(
                                    UniformLootNumberProvider.create(1, 2)
                            ))
                    )
                    .conditionally(RandomChanceLootCondition.builder(chance))
            );
        }
    }
}
