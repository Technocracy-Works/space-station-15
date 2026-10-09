package org.technocracy.spacestation.chemistry;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.UseAction;
import net.minecraft.world.World;
import org.technocracy.spacestation.registry.ModComponents;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class ChemContainer extends Item {

    private static final double DRINK_AMOUNT = 5.0; // юнитов за одно питьё
    private static final int MAX_EFFECT_DURATION = 6000;

    public ChemContainer(Settings settings) {
        super(settings);
    }

    @Override
    public Text getName(ItemStack stack) {
        ChemData data = stack.get(ModComponents.CHEM_DATA);
        if (data == null || data.chemicals().isEmpty()) {
            return super.getName(stack);
        }
        String baseKey = this.getTranslationKey(stack);
        if (data.chemicals().size() > 1) {
            return Text.translatable(baseKey+"_of_mixture");
        }

        String chemical = data.chemicals().keySet().iterator().next();
        Text chemicalName = Text.translatableWithFallback(
                "chem.spacestation." + chemical,
                chemical
        );
        return Text.translatable(baseKey+"_of", chemicalName);
    }

    @Override
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.DRINK;
    }

    @Override
    public int getMaxUseTime(ItemStack stack, LivingEntity user) {
        return 8;
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        ChemData data = stack.get(ModComponents.CHEM_DATA);

        if (data == null || data.chemicals().isEmpty()) {
            return TypedActionResult.pass(stack);
        }

        player.setCurrentHand(hand);
        return TypedActionResult.consume(stack);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (world.isClient()) return stack;

        ChemData data = stack.get(ModComponents.CHEM_DATA);
        if (data == null || data.chemicals().isEmpty()) {
            return stack;
        }

        // ============ ПИТЬЁ ============
        ChemData updated = data;
        PlayerEntity player = user instanceof PlayerEntity playerEntity ? playerEntity : null;
        for (Map.Entry<String, Double> entry : data.chemicals().entrySet()) {
            String chem = entry.getKey();
            double amount = entry.getValue();

            if (amount < DRINK_AMOUNT) continue;

            updated = updated.remove(chem, DRINK_AMOUNT);
            if (player != null) {
                applyChemEffects(player, chem);
            }
        }

        if (updated == data) {
            return stack;
        }

        updated = ChemReactor.react(updated);
        stack.set(ModComponents.CHEM_DATA, updated);
        return stack;
    }

    // ============ ЭФФЕКТЫ ============
    private void applyChemEffects(PlayerEntity player, String chem) {
        for (ChemEffects.EffectSpec spec : ChemEffects.getEffects(chem)) {
            addEffect(player, spec.effect(), spec.duration(), spec.amplifier());
        }
    }

    private void addEffect(PlayerEntity player, RegistryEntry<StatusEffect> effect,
                           int duration, int amplifier) {
        StatusEffectInstance current = player.getStatusEffect(effect);
        int combinedDuration = duration;
        int combinedAmplifier = amplifier;
        if (current != null) {
            combinedDuration = Math.min(MAX_EFFECT_DURATION, current.getDuration() + duration);
            combinedAmplifier = Math.max(current.getAmplifier(), amplifier);
        }
        player.addStatusEffect(new StatusEffectInstance(effect, combinedDuration, combinedAmplifier));
    }

    // ============ ТУЛТИП В ИНВЕНТАРЕ ПРИ НАВЕДЕНИИ ============
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context,
                              List<Text> tooltip, TooltipType type) {
        ChemData data = stack.get(ModComponents.CHEM_DATA);
        if (data == null) return;

        if (data.chemicals().isEmpty()) {
            tooltip.add(Text.translatable("tooltip.spacestation.empty").formatted(Formatting.GRAY));
        } else {
            tooltip.add(Text.translatable("tooltip.spacestation.volume_info",
                    String.format(Locale.ROOT, "%.1f", data.totalVolume()),
                    String.format(Locale.ROOT, "%.0f", data.capacity())
            ).formatted(Formatting.GRAY));

            for (Map.Entry<String, Double> entry : data.chemicals().entrySet()) {
                Text chemName = Text.translatableWithFallback(
                        "chem.spacestation." + entry.getKey(),
                        entry.getKey()
                ).formatted(Formatting.WHITE);

                Text amountText = Text.literal(String.format(Locale.ROOT, "%.2f u", entry.getValue()))
                        .formatted(Formatting.YELLOW);

                tooltip.add(Text.translatable("tooltip.spacestation.chem_line", chemName, amountText));
            }
        }
    }
}