package org.corvaxcraft.spacestation.chemistry;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

import java.util.HashMap;
import java.util.Map;

public record ChemData(Map<String, Double> chemicals, double capacity) {

    public static final ChemData EMPTY_BEAKER = new ChemData(new HashMap<>(), 100.0);
    public static final ChemData EMPTY_CANISTER = new ChemData(new HashMap<>(), 200.0);

    public static final Codec<ChemData> CODEC = RecordCodecBuilder.create(instance ->
            instance.group(
                    Codec.unboundedMap(Codec.STRING, Codec.DOUBLE)
                            .fieldOf("chemicals")
                            .forGetter(ChemData::chemicals),
                    Codec.DOUBLE
                            .fieldOf("capacity")
                            .forGetter(ChemData::capacity)
            ).apply(instance, ChemData::new)
    );

    public static final PacketCodec<RegistryByteBuf, ChemData> PACKET_CODEC =
            PacketCodec.tuple(
                    PacketCodecs.map(HashMap::new, PacketCodecs.STRING, PacketCodecs.DOUBLE),
                    ChemData::chemicals,
                    PacketCodecs.DOUBLE,
                    ChemData::capacity,
                    ChemData::new
            );

    // Сколько всего химикатов в хранилище
    public double totalVolume() {
        return chemicals.values().stream().mapToDouble(Double::doubleValue).sum();
    }

    // Сколько свободного места
    public double freeSpace() {
        return Math.max(0, capacity - totalVolume());
    }

    // Добавить химикат, возвращает сколько реально добавилось
    public ChemData add(String chem, double amount) {
        double canAdd = Math.min(amount, freeSpace());
        if (canAdd <= 0.01) return this;

        Map<String, Double> updated = new HashMap<>(chemicals);
        updated.merge(chem, canAdd, Double::sum);
        updated.replaceAll((k, v) -> Math.round(v * 100.0) / 100.0);
        updated.values().removeIf(v -> v <= 0.0);

        ChemData result = new ChemData(updated, capacity);
        return ChemReactor.react(result); // реакция сразу при добавлении
    }

    // Убрать химикат
    public ChemData remove(String chem, double amount) {
        if (!chemicals.containsKey(chem)) return this;

        Map<String, Double> updated = new HashMap<>(chemicals);
        double current = updated.getOrDefault(chem, 0.0);
        double newAmount = Math.round((current - amount) * 100.0) / 100.0;

        if (newAmount <= 0.0) {
            updated.remove(chem);
        } else {
            updated.put(chem, newAmount);
        }
        return new ChemData(updated, capacity);
    }

    // Есть ли химикат в нужном количестве
    public boolean has(String chem, double amount) {
        return chemicals.getOrDefault(chem, 0.0) >= amount - 0.001;
    }
}