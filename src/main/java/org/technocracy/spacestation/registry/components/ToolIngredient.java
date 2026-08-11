package org.technocracy.spacestation.registry.components;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import org.technocracy.spacestation.registry.items.ItemTool;

import java.util.HashSet;
import java.util.Set;

public record ToolIngredient(Set<Item> needItems, Set<ToolQuality> needQualities) {
    public static ToolIngredient of (Object... ingredients) {
        HashSet<Item> items = new HashSet<>();
        HashSet<ToolQuality> qualities = new HashSet<>();

        for (Object obj : ingredients) {
            if (obj instanceof Item item) {
                items.add(item);
            } else if (obj instanceof ToolQuality quality) {
                qualities.add(quality);
            }
        }

        return new ToolIngredient(Set.copyOf(items), Set.copyOf(qualities));
    }

    public boolean isEmpty() {
        return needItems.isEmpty() && needQualities.isEmpty();
    }

    public boolean contains(Object obj) {
        if (obj instanceof ItemStack stack) {
            Item item = stack.getItem();
            if (item instanceof ItemTool tool) {
                if (!needQualities.isEmpty()) return tool.getQualities().containsAll(needQualities);
                return needItems.contains(tool);
            }
            else {
                return needItems.contains(item);
            }
        }
        else if (obj instanceof ItemTool tool) {
            if (!needQualities.isEmpty()) return tool.getQualities().containsAll(needQualities);
            return needItems.contains(tool);
        } else if (obj instanceof Item item) {
            return needItems.contains(item);
        }
        return false;
    }
}
