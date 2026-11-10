package org.technocracy.spacestation.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import org.technocracy.spacestation.registry.ModDamageTypes;

public class RadiationStatusEffect extends StatusEffect {
    public RadiationStatusEffect() {
        super(
                StatusEffectCategory.HARMFUL,
                0x98D982); // color in RGB
    }

    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        entity.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0));
        entity.damage(ModDamageTypes.of(entity.getWorld(), ModDamageTypes.RADIATION_DAMAGE_TYPE), 6.0f);

        return true;
    }

    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        if (duration % (600/(amplifier+1)) == 0) {
            return true;
        }
        return false;
    }
}
