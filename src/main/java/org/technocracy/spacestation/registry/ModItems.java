package org.technocracy.spacestation.registry;

import net.minecraft.item.Item;
import org.technocracy.spacestation.registry.items.*;

/* Точка входа для регистрации всех айтемов. Сами поля живут в подклассах в пакете registry.items. Для доступа из других мест импортировать нужный под-класс напрямую, или используй шорткаты ниже если нужен общий доступ.*/

public final class ModItems {

    // --- Шорткаты для часто используемых айтемов из других систем ОПТИМИЗАЦИЯ!!!! ---
    public static final Item CROWBAR  = ToolItems.CROWBAR;
    public static final Item STEEL  = MiscItems.STEEL;


    public static void register() {
        FoodItems.register();
        DrinkItems.register();
        ToolItems.register();
        PlushieItems.register();
        ChemItems.register();
        MiscItems.register();
    }

    private ModItems() {}
}