package org.technocracy.spacestation.system.radiation;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.registry.entry.RegistryEntry;
import org.technocracy.spacestation.registry.ModComponents;
import org.technocracy.spacestation.registry.ModDamageTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.function.Predicate;

public final class RadiationManager {
    private static final Map<ServerPlayerEntity, Integer> LAST_LEVELS = new WeakHashMap<>();
    private static final List<ExposureSource> EXPOSURE_SOURCES = new ArrayList<>();
    private static final List<RadiationModifier> MODIFIERS = new ArrayList<>();
    private static RadiationConfig config;

    private RadiationManager() {}

    public static void initialize() {
        config = RadiationConfig.load();
    }

    public static void registerExposureSource(Predicate<ServerPlayerEntity> isExposed, double dose, int intervalTicks) {
        if (!Double.isFinite(dose) || dose < 0.0 || intervalTicks < 1) {
            throw new IllegalArgumentException("Radiation sources require a non-negative finite dose and positive interval");
        }
        EXPOSURE_SOURCES.add(new ExposureSource(isExposed, dose, intervalTicks));
    }

    public static void registerModifier(RadiationModifier modifier) {
        MODIFIERS.add(modifier);
    }

    public static double getRadiation(LivingEntity entity) {
        return Math.max(0.0, data(entity).spacestation$getRadiation());
    }

    public static void addRadiation(LivingEntity entity, double amount) {
        if (!Double.isFinite(amount) || amount < 0.0) {
            throw new IllegalArgumentException("Radiation amount must be finite and non-negative");
        }
        if (amount == 0.0) {
            return;
        }
        setRadiation(entity, Math.min(Double.MAX_VALUE, getRadiation(entity) + amount));
    }

    public static void removeRadiation(LivingEntity entity, double amount) {
        if (!Double.isFinite(amount) || amount < 0.0) {
            throw new IllegalArgumentException("Radiation amount must be finite and non-negative");
        }
        if (amount == 0.0) {
            return;
        }
        setRadiation(entity, Math.max(0.0, getRadiation(entity) - amount));
    }

    public static void clearRadiation(LivingEntity entity) {
        setRadiation(entity, 0.0);
    }

    public static void copyRadiation(ServerPlayerEntity source, ServerPlayerEntity target) {
        setRadiation(target, getRadiation(source));
    }

    private static void setRadiation(LivingEntity entity, double value) {
        data(entity).spacestation$setRadiation(Double.isFinite(value) ? Math.max(0.0, value) : 0.0);
    }

    public static void tick(ServerPlayerEntity player) {
        if (config == null) {
            throw new IllegalStateException("RadiationManager must be initialized before ticking");
        }

        double decayPerTick = config.naturalDecayPerSecond() / 20.0;
        for (RadiationModifier modifier : MODIFIERS) {
            decayPerTick += Math.max(0.0, modifier.additionalDecayPerTick(player));
        }
        if (decayPerTick > 0.0) {
            removeRadiation(player, decayPerTick);
        }

        if (player.age % config.itemExposureIntervalTicks() == 0) {
            double itemDose = getHeldRadioactivity(player.getMainHandStack())
                    + getHeldRadioactivity(player.getOffHandStack());
            if (itemDose > 0.0) {
                addRadiation(player, itemDose * getExposureMultiplier(player));
            }
        }

        for (ExposureSource source : EXPOSURE_SOURCES) {
            if (player.age % source.intervalTicks() == 0 && source.isExposed().test(player)) {
                addRadiation(player, source.dose() * getExposureMultiplier(player));
            }
        }

        int level = getLevel(getRadiation(player));
        Integer lastLevel = LAST_LEVELS.put(player, level);
        if (lastLevel == null) {
            refreshLevelEffects(player, level);
        } else if (lastLevel != level) {
            applyLevelEffects(player, lastLevel, level);
        } else if (player.age % config.effectRefreshIntervalTicks() == 0) {
            refreshLevelEffects(player, level);
        }

        if (level > 0) {
            RadiationConfig.Level effects = config.levels().get(level - 1);
            if (effects.damage() > 0.0 && player.age % effects.damageIntervalTicks() == 0) {
                player.damage(ModDamageTypes.of(player.getWorld(), ModDamageTypes.RADIATION_DAMAGE_TYPE),
                        (float) effects.damage());
            }
        }
    }

    private static double getHeldRadioactivity(ItemStack stack) {
        double radioactivity = stack.getOrDefault(ModComponents.RADIOACTIVITY, 0.0);
        return Double.isFinite(radioactivity) ? Math.max(0.0, radioactivity) : 0.0;
    }

    private static double getExposureMultiplier(ServerPlayerEntity player) {
        double exposureMultiplier = 1.0;
        for (RadiationModifier modifier : MODIFIERS) {
            exposureMultiplier *= Math.max(0.0, modifier.exposureMultiplier(player));
        }
        return exposureMultiplier;
    }

    private static int getLevel(double radiation) {
        int level = 0;
        for (RadiationConfig.Level threshold : config.levels()) {
            if (radiation < threshold.threshold()) {
                break;
            }
            level++;
        }
        return level;
    }

    private static void applyLevelEffects(ServerPlayerEntity player, int previousLevel, int level) {
        RadiationConfig.Level previous = previousLevel == 0 ? null : config.levels().get(previousLevel - 1);
        RadiationConfig.Level current = level == 0 ? null : config.levels().get(level - 1);
        if (isEnabled(previous, RadiationConfig.Level::nausea) && !isEnabled(current, RadiationConfig.Level::nausea)) {
            player.removeStatusEffect(StatusEffects.NAUSEA);
        }
        if (isEnabled(previous, RadiationConfig.Level::weakness)
                && !isEnabled(current, RadiationConfig.Level::weakness)) {
            player.removeStatusEffect(StatusEffects.WEAKNESS);
        }
        if (isEnabled(previous, RadiationConfig.Level::slowness)
                && !isEnabled(current, RadiationConfig.Level::slowness)) {
            player.removeStatusEffect(StatusEffects.SLOWNESS);
        }
        refreshLevelEffects(player, level);
    }

    private static boolean isEnabled(RadiationConfig.Level level,
                                     java.util.function.Predicate<RadiationConfig.Level> enabled) {
        return level != null && enabled.test(level);
    }

    private static void refreshLevelEffects(ServerPlayerEntity player, int level) {
        if (level == 0) {
            return;
        }
        RadiationConfig.Level effects = config.levels().get(level - 1);
        int duration = config.effectRefreshIntervalTicks() + 40;
        if (effects.nausea()) {
            refreshEffect(player, StatusEffects.NAUSEA, duration, 0);
        }
        if (effects.weakness()) {
            refreshEffect(player, StatusEffects.WEAKNESS, duration, effects.weaknessAmplifier());
        }
        if (effects.slowness()) {
            refreshEffect(player, StatusEffects.SLOWNESS, duration, effects.slownessAmplifier());
        }
    }

    private static void refreshEffect(ServerPlayerEntity player, RegistryEntry<StatusEffect> effect,
                                      int duration, int amplifier) {
        StatusEffectInstance current = player.getStatusEffect(effect);
        if (current == null || current.getDuration() <= 40 || current.getAmplifier() != amplifier) {
            player.addStatusEffect(new StatusEffectInstance(effect, duration, amplifier));
        }
    }

    private static RadiationDataAccess data(LivingEntity entity) {
        if (!(entity instanceof RadiationDataAccess access)) {
            throw new IllegalStateException("LivingEntity is missing the radiation data mixin");
        }
        return access;
    }

    private record ExposureSource(Predicate<ServerPlayerEntity> isExposed, double dose, int intervalTicks) {}
}
