package org.technocracy.spacestation.mixin;

import com.mojang.datafixers.util.Pair;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.network.packet.s2c.play.EntityEquipmentUpdateS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.technocracy.spacestation.registry.components.ChargeData;
import org.technocracy.spacestation.registry.components.ModComponents;
import org.technocracy.spacestation.registry.items.ChargeItem;

import java.util.List;

@Mixin(ServerPlayerEntity.class)
public class ServerPlayerEntityMixin {

    @Inject(method = "dropSelectedItem", at = @At("HEAD"), cancellable = true)
    private void OnDropItem(boolean dropAll, CallbackInfoReturnable<Boolean> cir) {
        ServerPlayerEntity player = (ServerPlayerEntity) (Object) this;

        ItemStack mainHand = player.getMainHandStack();
        ItemStack offHand = player.getOffHandStack();

        if (mainHand.isOf(Items.LAVA_BUCKET) && offHand.getItem().getComponents().contains(ModComponents.CHARGE_COMPONENT)) { // yeah, shitcode
            ChargeData data = offHand.getOrDefault(ModComponents.CHARGE_COMPONENT, new ChargeData(0F, 100F));

            if (data.charge() >= data.maxCharge()) return;

            offHand.set(ModComponents.CHARGE_COMPONENT, data.withCharge(data.charge() + 50));

            if (!player.getAbilities().creativeMode) {
                player.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.BUCKET));
            }

            player.networkHandler.sendPacket(new EntityEquipmentUpdateS2CPacket(
                    player.getId(),
                    List.of(
                            Pair.of(EquipmentSlot.MAINHAND, player.getMainHandStack()),
                            Pair.of(EquipmentSlot.OFFHAND, player.getOffHandStack())
                    )
            ));

            player.getWorld().playSound(null, player.getX(), player.getY(), player.getZ(),
                    SoundEvents.BLOCK_LAVA_AMBIENT, SoundCategory.PLAYERS, 1.0F, 1.2F);

            cir.setReturnValue(false);
        }
    }
}
