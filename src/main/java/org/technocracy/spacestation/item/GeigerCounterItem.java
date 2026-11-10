package org.technocracy.spacestation.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.ActionResult;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.technocracy.spacestation.system.radiation.RadiationManager;

public class GeigerCounterItem extends Item {
    private static final int USE_COOLDOWN_TICKS = 10;

    public GeigerCounterItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        if (!world.isClient() && player instanceof ServerPlayerEntity serverPlayer) {
            double radiation = RadiationManager.getRadiation(serverPlayer);
            Formatting color = radiation < 200.0 ? Formatting.GREEN
                    : radiation < 500.0 ? Formatting.GOLD
                    : Formatting.RED;
            player.sendMessage(
                    Text.translatable("item.spacestation.geiger_counter.reading",
                            player.getName(), Math.round(radiation))
                            .formatted(color),
                    false
            );
            player.getItemCooldownManager().set(this, USE_COOLDOWN_TICKS);
        }
        return TypedActionResult.success(stack, world.isClient());
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity target, Hand hand) {
        if (user.getWorld().isClient() || !(user instanceof ServerPlayerEntity serverPlayer)) {
            return ActionResult.SUCCESS;
        }

        double radiation = RadiationManager.getRadiation(target);
        Formatting color = radiation < 200.0 ? Formatting.GREEN
                : radiation < 500.0 ? Formatting.GOLD
                : Formatting.RED;
        serverPlayer.sendMessage(
                Text.translatable("item.spacestation.geiger_counter.target_reading",
                        target.getDisplayName(), Math.round(radiation))
                        .formatted(color),
                false
        );
        user.getItemCooldownManager().set(this, USE_COOLDOWN_TICKS);
        return ActionResult.SUCCESS;
    }
}
