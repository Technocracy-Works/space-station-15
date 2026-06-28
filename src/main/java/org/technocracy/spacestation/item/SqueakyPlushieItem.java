package org.technocracy.spacestation.item;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class SqueakyPlushieItem extends Item {

    private static final int COOLDOWN_TICKS = 15;
    private static final float VOLUME = 1.0f;

    private final SoundEvent[] squeakSounds;

    public SqueakyPlushieItem(Settings settings, SoundEvent... squeakSounds) {
        super(settings);
        if (squeakSounds.length == 0) {
            throw new IllegalArgumentException("SqueakyPlushieItem requires at least one squeak sound");
        }
        this.squeakSounds = squeakSounds;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);

        player.getItemCooldownManager().set(this, COOLDOWN_TICKS);

        if (!world.isClient()) {
            SoundEvent sound = squeakSounds[world.getRandom().nextInt(squeakSounds.length)];
            float pitch = 0.85f + world.getRandom().nextFloat() * 0.3f;

            world.playSound(
                    null,
                    player.getX(), player.getY(), player.getZ(),
                    sound,
                    SoundCategory.PLAYERS,
                    VOLUME, pitch
            );

            player.swingHand(hand, true);
        }

        return TypedActionResult.success(stack);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (!attacker.getWorld().isClient() && attacker instanceof PlayerEntity player) {
            float damage = (float) player.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
            target.heal(damage);

            SoundEvent sound = squeakSounds[attacker.getRandom().nextInt(squeakSounds.length)];
            attacker.getWorld().playSound(
                    null,
                    attacker.getX(), attacker.getY(), attacker.getZ(),
                    sound,
                    SoundCategory.PLAYERS,
                    VOLUME, 1.0f
            );
        }

        return true;
    }

    @Override
    public boolean canMine(net.minecraft.block.BlockState state, World world,
                           net.minecraft.util.math.BlockPos pos, PlayerEntity miner) {
        return false;
    }
}