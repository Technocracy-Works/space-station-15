package org.corvaxcraft.spacestation.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SqueakyPlushieItem extends Item {

    private static final int COOLDOWN_TICKS = 15;
    private static final float VOLUME = 1.0f;
    private static final Map<UUID, Long> LAST_SQUEAK_TICK = new HashMap<>();

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

        if (world.isClient()) {
            return TypedActionResult.success(stack);
        }

        long now = world.getTime();
        Long lastTick = LAST_SQUEAK_TICK.get(player.getUuid());
        if (lastTick != null && now - lastTick < COOLDOWN_TICKS) {
            return TypedActionResult.fail(stack);
        }

        SoundEvent sound = squeakSounds[world.getRandom().nextInt(squeakSounds.length)];
        float pitch = 0.85f + world.getRandom().nextFloat() * 0.3f;

        world.playSound(
                null,
                player.getX(),
                player.getY(),
                player.getZ(),
                sound,
                SoundCategory.PLAYERS,
                VOLUME,
                pitch
        );

        LAST_SQUEAK_TICK.put(player.getUuid(), now);
        player.swingHand(hand, true);

        return TypedActionResult.success(stack);
    }
}
