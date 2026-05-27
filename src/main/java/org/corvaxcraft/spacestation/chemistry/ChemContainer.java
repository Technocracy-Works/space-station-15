package org.corvaxcraft.spacestation.chemistry;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.tooltip.TooltipType;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.Map;

public class ChemContainer extends Item {

    private static final double DRINK_AMOUNT = 5.0; // юнитов за одно питьё

    public ChemContainer(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity player, Hand hand) {
        ItemStack stack = player.getStackInHand(hand);
        ChemData data = stack.get(ModComponents.CHEM_DATA);

        if (data == null || data.chemicals().isEmpty()) {
            return TypedActionResult.pass(stack);
        }

        if (world.isClient()) return TypedActionResult.success(stack);

        // Пьём каждый химикат по 5 юнитов
        ChemData updated = data;
        for (Map.Entry<String, Double> entry : data.chemicals().entrySet()) {
            String chem = entry.getKey();
            double amount = entry.getValue();

            if (amount < DRINK_AMOUNT) continue; // недостаточно — пропускаем

            updated = updated.remove(chem, DRINK_AMOUNT);
            applyChemEffect(player, chem, DRINK_AMOUNT);
        }

        if (updated == data) {
            // Ни один химикат не был выпит (всех меньше 5 юнитов)
            return TypedActionResult.fail(stack);
        }

        // Запускаем реакции после питья
        updated = ChemReactor.react(updated);

        stack.set(ModComponents.CHEM_DATA, updated);
        return TypedActionResult.success(stack);
    }

    // Эффекты от химикатов — добавляй сюда новые
    private void applyChemEffect(PlayerEntity player, String chem, double amount) {
        switch (chem) {
            case "carbon" -> {} // ничего
            case "calcium" ->
                    player.addStatusEffect(new StatusEffectInstance(
                            StatusEffects.RESISTANCE, 60, 0)); // 3 сек резист
            case "bicaridine" ->
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 120, 1));
            case "kelotane", "dermaline" ->
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 0));
            case "leporazine", "cryoxadone" ->
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 120, 0));
            case "dexalin", "dexalin_plus" ->
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.WATER_BREATHING, 160, 0));
            case "ephedrine", "stimulants" ->
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 140, 1));
            case "paks", "space_drugs", "lipolicide", "mindbreaker_toxin", "heartbreaker_toxin" ->
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 200, 0));
            case "uranium", "fluorosulfuric_acid", "thermite", "mute_toxin" ->
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 220, 1));
            default ->
                    // TODO: Назначить точечные эффекты для каждого нового реагента/препарата.
                    // Временно: безопасный дефолт-эффект, чтобы вещество не было "пустым".
                    player.addStatusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 80, 0));
        }
    }

    // Тултип — показываем содержимое
    @Override
    public void appendTooltip(ItemStack stack, TooltipContext context,
                              List<Text> tooltip, TooltipType type) {
        ChemData data = stack.get(ModComponents.CHEM_DATA);
        if (data == null) return;

        if (data.chemicals().isEmpty()) {
            tooltip.add(Text.literal("§7Пусто"));
        } else {
            tooltip.add(Text.literal(String.format("§7%.1f / %.0f u", data.totalVolume(), data.capacity())));
            for (Map.Entry<String, Double> entry : data.chemicals().entrySet()) {
                String chemName = Text.translatableWithFallback(
                        "chem.spacestation." + entry.getKey(),
                        entry.getKey()
                ).getString();
                tooltip.add(Text.literal(String.format("  §f%s: §e%.2f u", chemName, entry.getValue())));
            }
        }
    }
}