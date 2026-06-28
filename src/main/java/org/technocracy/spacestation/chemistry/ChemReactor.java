package org.technocracy.spacestation.chemistry;

import java.util.HashMap;
import java.util.Map;

public class ChemReactor {

    private static final int MAX_CHAIN_DEPTH = 20; // защита от бесконечных цепочек

    public static ChemData react(ChemData data) {
        if (data.chemicals().isEmpty()) return data;

        ChemData current = data;
        int depth = 0;

        while (depth < MAX_CHAIN_DEPTH) {
            ChemData next = tryReact(current);
            if (next == current) break; // реакций больше нет
            current = next;
            depth++;
        }

        return current;
    }

    private static ChemData tryReact(ChemData data) {
        for (ChemRegistry.ReactionRecipe recipe : ChemRegistry.getReactions()) {

            // Проверяем минимальный объём
            double totalReagents = recipe.reagents().values().stream()
                    .mapToDouble(Double::doubleValue).sum();
            if (totalReagents < recipe.minVolume()) continue;

            // Проверяем сколько раз можно провести реакцию
            double times = getReactionTimes(data, recipe);
            if (times < 0.01) continue;

            // Проводим реакцию
            Map<String, Double> updated = new HashMap<>(data.chemicals());

            // Убираем реагенты
            for (Map.Entry<String, Double> reagent : recipe.reagents().entrySet()) {
                double remove = Math.round(reagent.getValue() * times * 100.0) / 100.0;
                double current = updated.getOrDefault(reagent.getKey(), 0.0);
                double newVal = Math.round((current - remove) * 100.0) / 100.0;
                if (newVal <= 0.0) {
                    updated.remove(reagent.getKey());
                } else {
                    updated.put(reagent.getKey(), newVal);
                }
            }

            // Добавляем результаты
            for (Map.Entry<String, Double> result : recipe.results().entrySet()) {
                double add = Math.round(result.getValue() * times * 100.0) / 100.0;
                updated.merge(result.getKey(), add, (a, b) ->
                        Math.round((a + b) * 100.0) / 100.0);
            }

            updated.values().removeIf(v -> v <= 0.0);
            return new ChemData(updated, data.capacity());
        }

        return data; // ни одна реакция не сработала
    }

    // Считаем сколько раз можно провести реакцию исходя из доступных реагентов
    private static double getReactionTimes(ChemData data, ChemRegistry.ReactionRecipe recipe) {
        double minTimes = Double.MAX_VALUE;

        for (Map.Entry<String, Double> reagent : recipe.reagents().entrySet()) {
            double available = data.chemicals().getOrDefault(reagent.getKey(), 0.0);
            double needed = reagent.getValue();
            if (needed <= 0.0) continue; // катализатор/метка: не ограничивает количество реакций

            if (available < 0.01) return 0.0; // реагента нет совсем

            double possible = available / needed;
            minTimes = Math.min(minTimes, possible);
        }

        return minTimes == Double.MAX_VALUE ? 0.0 : minTimes;
    }
}