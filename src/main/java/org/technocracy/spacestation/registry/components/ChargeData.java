package org.technocracy.spacestation.registry.components;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public record ChargeData(float charge, float maxCharge) {
    public ChargeData {
        charge = Math.clamp(charge, 0f, maxCharge);
    }
    public static final Codec<ChargeData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            Codec.FLOAT.fieldOf("charge").forGetter(ChargeData::charge),
            Codec.FLOAT.fieldOf("max_charge").forGetter(ChargeData::maxCharge)
    ).apply(instance, ChargeData::new));

    public static final PacketCodec<RegistryByteBuf, ChargeData> PACKET_CODEC = PacketCodec.tuple(
            PacketCodecs.FLOAT, ChargeData::charge,
            PacketCodecs.FLOAT, ChargeData::maxCharge,
            ChargeData::new
    );

    public ChargeData withCharge(float newCharge) {
        float clamped = Math.clamp(newCharge, 0F, this.maxCharge);
        return new ChargeData(clamped, this.maxCharge);
    }

    public static void inventoryTick(ItemStack stack, World world, net.minecraft.entity.Entity entity, int slot, boolean selected) {
        if (!world.isClient() && stack.getOrDefault(ModComponents.ITEM_TOGGLE_COMPONENT, false)) {// every 1 second

            ChargeData data = stack.getOrDefault(ModComponents.CHARGE_COMPONENT, new ChargeData(0F, 100F));

            if (data.charge() > 1) {
                stack.set(ModComponents.CHARGE_COMPONENT, data.withCharge(data.charge() - 0.05F));
            } else {
                stack.set(ModComponents.CHARGE_COMPONENT, new ChargeData(0F, 100F));
                stack.set(ModComponents.ITEM_TOGGLE_COMPONENT, false);

                world.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
                        net.minecraft.sound.SoundEvents.BLOCK_LAVA_EXTINGUISH,
                        net.minecraft.sound.SoundCategory.PLAYERS, 1.0F, 1.0F);
            }
        }
    }

    public static TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand, Item item) {
        ItemStack stack = user.getStackInHand(hand);


        ChargeData chargeData = stack.get(ModComponents.CHARGE_COMPONENT);
        if (chargeData == null) return TypedActionResult.pass(stack);

        if (chargeData.charge() <= 0 && !stack.getOrDefault(ModComponents.ITEM_TOGGLE_COMPONENT, false)) {
            return TypedActionResult.fail(stack);
        }

        return ItemToggle.toggle(world, user, hand, item);
    }

    public static int getBarStep(ItemStack stack) {
        ChargeData chargeData = stack.getOrDefault(ModComponents.CHARGE_COMPONENT, new ChargeData(0F, 100F));
        return Math.round(chargeData.charge() * 13 / chargeData.maxCharge());
    }
}
