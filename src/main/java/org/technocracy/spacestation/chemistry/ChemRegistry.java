package org.technocracy.spacestation.chemistry;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.fabricmc.fabric.api.resource.ResourceManagerHelper;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceType;
import net.minecraft.util.Identifier;

import java.io.InputStreamReader;
import java.util.*;

public class ChemRegistry {

    private static final Gson GSON = new Gson();

    // Рецепт дробления: айтем -> результаты
    public record GrindingRecipe(Identifier ingredient, Map<String, Double> results) {}

    // Рецепт реакции
    public record ReactionRecipe(Map<String, Double> reagents, Map<String, Double> results, double minVolume) {}

    private static final Map<Identifier, GrindingRecipe> GRINDING = new HashMap<>();
    private static final List<ReactionRecipe> REACTIONS = new ArrayList<>();

    public static void register() {
        ResourceManagerHelper.get(ResourceType.SERVER_DATA)
                .registerReloadListener(new SimpleSynchronousResourceReloadListener() {
                    @Override
                    public Identifier getFabricId() {
                        return Identifier.of("spacestation", "chem_registry");
                    }

                    @Override
                    public void reload(ResourceManager manager) {
                        loadGrinding(manager);
                        loadReactions(manager);
                    }
                });
    }

    private static void loadGrinding(ResourceManager manager) {
        GRINDING.clear();
        manager.findResources("grinding", id ->
                id.getNamespace().equals("spacestation") && id.getPath().endsWith(".json")
        ).forEach((id, resource) -> {
            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
                JsonObject json = GSON.fromJson(reader, JsonObject.class);
                Identifier ingredient = Identifier.of(json.get("ingredient").getAsString());
                Map<String, Double> results = parseDoubleMap(json.getAsJsonObject("results"));
                GRINDING.put(ingredient, new GrindingRecipe(ingredient, results));
            } catch (Exception e) {
                System.err.println("[SpaceStation] Ошибка загрузки grinding рецепта: " + id + " — " + e.getMessage());
            }
        });
        System.out.println("[SpaceStation] Загружено grinding рецептов: " + GRINDING.size());
    }

    private static void loadReactions(ResourceManager manager) {
        REACTIONS.clear();
        manager.findResources("reactions", id ->
                id.getNamespace().equals("spacestation") && id.getPath().endsWith(".json")
        ).forEach((id, resource) -> {
            try (InputStreamReader reader = new InputStreamReader(resource.getInputStream())) {
                JsonObject json = GSON.fromJson(reader, JsonObject.class);
                // Old format:
                // {
                //   "reagents": {...},
                //   "results": {...},
                //   "min_volume": 0.0
                // }
                if (json.has("reagents") && json.has("results")) {
                    Map<String, Double> reagents = parseDoubleMap(json.getAsJsonObject("reagents"));
                    Map<String, Double> results = parseDoubleMap(json.getAsJsonObject("results"));
                    double minVolume = json.has("min_volume") ? json.get("min_volume").getAsDouble() : 0.0;
                    REACTIONS.add(new ReactionRecipe(reagents, results, minVolume));
                    return;
                }

                // Bulk table format:
                // {
                //   "Алоксадон": { "Алое": 0.2, "Криоксадон": 0.4, ... },
                //   "Амбузол":   { "Аммиак": 0.25, ... }
                // }
                // Each top-level key is produced reagent with output 1.0 by default.
                for (Map.Entry<String, JsonElement> entry : json.entrySet()) {
                    if (!entry.getValue().isJsonObject()) continue;

                    String product = entry.getKey();
                    Map<String, Double> reagents = parseDoubleMap(entry.getValue().getAsJsonObject());
                    Map<String, Double> results = new LinkedHashMap<>();
                    results.put(product, 1.0);

                    double minVolume = reagents.values().stream().mapToDouble(Double::doubleValue).sum();
                    REACTIONS.add(new ReactionRecipe(reagents, results, minVolume));
                }
            } catch (Exception e) {
                System.err.println("[SpaceStation] Ошибка загрузки reaction рецепта: " + id + " — " + e.getMessage());
            }
        });
        System.out.println("[SpaceStation] Загружено reaction рецептов: " + REACTIONS.size());
    }

    private static Map<String, Double> parseDoubleMap(JsonObject obj) {
        Map<String, Double> map = new LinkedHashMap<>();
        for (Map.Entry<String, JsonElement> entry : obj.entrySet()) {
            map.put(entry.getKey(), entry.getValue().getAsDouble());
        }
        return map;
    }

    // Получить рецепт дробления по айтему
    public static Optional<GrindingRecipe> getGrinding(Identifier itemId) {
        return Optional.ofNullable(GRINDING.get(itemId));
    }

    // Получить все рецепты реакций
    public static List<ReactionRecipe> getReactions() {
        return Collections.unmodifiableList(REACTIONS);
    }
}