package org.technocracy.spacestation.system.radiation;

import net.fabricmc.loader.api.FabricLoader;
import org.technocracy.spacestation.SpaceStation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public final class RadiationConfig {
    private static final String CONFIG_NAME = "spacestation-radiation.properties";

    private final int itemExposureIntervalTicks;
    private final double naturalDecayPerSecond;
    private final int effectRefreshIntervalTicks;
    private final List<Level> levels;

    private RadiationConfig(int itemExposureIntervalTicks, double naturalDecayPerSecond,
                            int effectRefreshIntervalTicks, List<Level> levels) {
        this.itemExposureIntervalTicks = itemExposureIntervalTicks;
        this.naturalDecayPerSecond = naturalDecayPerSecond;
        this.effectRefreshIntervalTicks = effectRefreshIntervalTicks;
        this.levels = List.copyOf(levels);
    }

    public static RadiationConfig load() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve(CONFIG_NAME);
        Properties properties = defaults();
        if (Files.exists(path)) {
            try (InputStream input = Files.newInputStream(path)) {
                properties.load(input);
            } catch (IOException exception) {
                throw new IllegalStateException("Could not read radiation config at " + path, exception);
            }
        } else {
            try {
                Files.createDirectories(path.getParent());
                try (OutputStream output = Files.newOutputStream(path)) {
                    properties.store(output, "Space Station radiation settings. Values are loaded on server startup.");
                }
            } catch (IOException exception) {
                throw new IllegalStateException("Could not create radiation config at " + path, exception);
            }
        }

        double itemExposureIntervalSeconds = properties.containsKey("itemExposureIntervalSeconds")
                ? readDouble(properties, "itemExposureIntervalSeconds", 5.0, 0.05)
                : readDouble(properties, "uraniumIntervalSeconds", 5.0, 0.05);
        int itemExposureIntervalTicks = secondsToTicks(itemExposureIntervalSeconds, "itemExposureIntervalSeconds");
        double naturalDecayPerSecond = readDouble(properties, "naturalDecayPerSecond", 0.5, 0.0);
        int effectRefreshIntervalTicks = secondsToTicks(
                readDouble(properties, "effectRefreshIntervalSeconds", 10.0, 0.05),
                "effectRefreshIntervalSeconds");

        List<Level> levels = new ArrayList<>();
        double previousThreshold = 0.0;
        for (int level = 1; level <= 5; level++) {
            String prefix = "level" + level + ".";
            double threshold = readDouble(properties, prefix + "threshold", defaultThreshold(level), 0.0);
            if (threshold <= previousThreshold) {
                throw new IllegalArgumentException(prefix + "threshold must be greater than the previous level");
            }
            previousThreshold = threshold;
            double damage = readDouble(properties, prefix + "damage", defaultDamage(level), 0.0);
            int damageIntervalTicks = secondsToTicks(
                    readDouble(properties, prefix + "damageIntervalSeconds", defaultDamageInterval(level), 0.05),
                    prefix + "damageIntervalSeconds");
            levels.add(new Level(
                    threshold,
                    readBoolean(properties, prefix + "nausea", level >= 1),
                    readBoolean(properties, prefix + "weakness", level >= 2),
                    readInt(properties, prefix + "weaknessAmplifier", level >= 4 ? 1 : 0, 0, 255),
                    readBoolean(properties, prefix + "slowness", level >= 3),
                    readInt(properties, prefix + "slownessAmplifier", level >= 4 ? 1 : 0, 0, 255),
                    damage,
                    damageIntervalTicks
            ));
        }
        return new RadiationConfig(itemExposureIntervalTicks, naturalDecayPerSecond,
                effectRefreshIntervalTicks, levels);
    }

    private static Properties defaults() {
        Properties properties = new Properties();
        properties.setProperty("itemExposureIntervalSeconds", "5.0");
        properties.setProperty("naturalDecayPerSecond", "0.5");
        properties.setProperty("effectRefreshIntervalSeconds", "10.0");
        for (int level = 1; level <= 5; level++) {
            String prefix = "level" + level + ".";
            properties.setProperty(prefix + "threshold", Double.toString(defaultThreshold(level)));
            properties.setProperty(prefix + "nausea", Boolean.toString(level >= 1));
            properties.setProperty(prefix + "weakness", Boolean.toString(level >= 2));
            properties.setProperty(prefix + "weaknessAmplifier", Integer.toString(level >= 4 ? 1 : 0));
            properties.setProperty(prefix + "slowness", Boolean.toString(level >= 3));
            properties.setProperty(prefix + "slownessAmplifier", Integer.toString(level >= 4 ? 1 : 0));
            properties.setProperty(prefix + "damage", Double.toString(defaultDamage(level)));
            properties.setProperty(prefix + "damageIntervalSeconds", Double.toString(defaultDamageInterval(level)));
        }
        return properties;
    }

    private static double defaultThreshold(int level) {
        return switch (level) {
            case 1 -> 100.0;
            case 2 -> 200.0;
            case 3 -> 500.0;
            case 4 -> 1000.0;
            case 5 -> 2000.0;
            default -> throw new IllegalArgumentException("Invalid radiation level: " + level);
        };
    }

    private static double defaultDamage(int level) {
        return switch (level) {
            case 1 -> 0.0;
            case 2 -> 1.0;
            case 3 -> 2.0;
            case 4 -> 4.0;
            case 5 -> 8.0;
            default -> throw new IllegalArgumentException("Invalid radiation level: " + level);
        };
    }

    private static double defaultDamageInterval(int level) {
        return switch (level) {
            case 1 -> 30.0;
            case 2 -> 20.0;
            case 3 -> 10.0;
            case 4 -> 5.0;
            case 5 -> 2.0;
            default -> throw new IllegalArgumentException("Invalid radiation level: " + level);
        };
    }

    private static double readDouble(Properties properties, String key, double defaultValue, double minimum) {
        String value = properties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            double parsed = Double.parseDouble(value);
            if (!Double.isFinite(parsed) || parsed < minimum) {
                throw new NumberFormatException("value must be finite and at least " + minimum);
            }
            return parsed;
        } catch (NumberFormatException exception) {
            SpaceStation.LOGGER.warn("Invalid radiation config value '{}' for {}; using {}", value, key, defaultValue);
            return defaultValue;
        }
    }

    private static int readInt(Properties properties, String key, int defaultValue, int minimum, int maximum) {
        String value = properties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        try {
            int parsed = Integer.parseInt(value);
            if (parsed < minimum || parsed > maximum) {
                throw new NumberFormatException("value out of range");
            }
            return parsed;
        } catch (NumberFormatException exception) {
            SpaceStation.LOGGER.warn("Invalid radiation config value '{}' for {}; using {}", value, key, defaultValue);
            return defaultValue;
        }
    }

    private static boolean readBoolean(Properties properties, String key, boolean defaultValue) {
        String value = properties.getProperty(key);
        if (value == null) {
            return defaultValue;
        }
        if (value.equalsIgnoreCase("true")) {
            return true;
        }
        if (value.equalsIgnoreCase("false")) {
            return false;
        }
        SpaceStation.LOGGER.warn("Invalid radiation config value '{}' for {}; using {}", value, key, defaultValue);
        return defaultValue;
    }

    private static int secondsToTicks(double seconds, String key) {
        double ticks = seconds * 20.0;
        if (!Double.isFinite(ticks) || ticks > Integer.MAX_VALUE) {
            throw new IllegalArgumentException(key + " is too large");
        }
        return Math.max(1, (int) Math.round(ticks));
    }

    public int itemExposureIntervalTicks() {
        return itemExposureIntervalTicks;
    }

    public double naturalDecayPerSecond() {
        return naturalDecayPerSecond;
    }

    public int effectRefreshIntervalTicks() {
        return effectRefreshIntervalTicks;
    }

    public List<Level> levels() {
        return levels;
    }

    public record Level(double threshold, boolean nausea, boolean weakness, int weaknessAmplifier,
                        boolean slowness, int slownessAmplifier, double damage, int damageIntervalTicks) {}
}
