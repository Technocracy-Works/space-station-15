package org.technocracy.spacestation.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.technocracy.spacestation.system.radiation.RadiationDataAccess;

@Mixin(LivingEntity.class)
public class LivingEntityRadiationMixin implements RadiationDataAccess {
    @Unique
    private static final String SPACESTATION_RADIATION_KEY = "SpaceStationRadiation";

    @Unique
    private double spacestation$radiation;

    @Override
    public double spacestation$getRadiation() {
        return spacestation$radiation;
    }

    @Override
    public void spacestation$setRadiation(double radiation) {
        spacestation$radiation = Double.isFinite(radiation) ? Math.max(0.0, radiation) : 0.0;
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("TAIL"))
    private void spacestation$readRadiation(NbtCompound nbt, CallbackInfo ci) {
        spacestation$setRadiation(nbt.getDouble(SPACESTATION_RADIATION_KEY));
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("TAIL"))
    private void spacestation$writeRadiation(NbtCompound nbt, CallbackInfo ci) {
        nbt.putDouble(SPACESTATION_RADIATION_KEY, spacestation$radiation);
    }
}
