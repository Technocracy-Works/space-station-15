package org.technocracy.spacestation.system.radiation;

import net.minecraft.server.network.ServerPlayerEntity;

public interface RadiationModifier {
    default double exposureMultiplier(ServerPlayerEntity player) {
        return 1.0;
    }

    default double additionalDecayPerTick(ServerPlayerEntity player) {
        return 0.0;
    }
}
