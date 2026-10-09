package org.technocracy.spacestation.registry;

import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.effect.RadiationStatusEffect;

public class ModEffects {

    public static final StatusEffect RADIATION = register("radiation", new RadiationStatusEffect());
    public static final StatusEffect STIMULATOR = register("stimulator",
            new StatusEffect(StatusEffectCategory.BENEFICIAL, 16720896) {}
                    .addAttributeModifier(EntityAttributes.GENERIC_MOVEMENT_SPEED, Identifier.ofVanilla("effect.stim_speed"), 0.13, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(EntityAttributes.GENERIC_JUMP_STRENGTH, Identifier.ofVanilla("effect.stim_jump"), 0.13, EntityAttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(EntityAttributes.GENERIC_ATTACK_SPEED, Identifier.ofVanilla("effect.stim_haste"), 0.1F, EntityAttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));



    private static StatusEffect register(String path, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, Identifier.of(SpaceStation.MOD_ID, path), effect);
    }

    public static void register() {}
}
