package org.technocracy.spacestation.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.client.chemistry.ChemMasterScreen;
import org.technocracy.spacestation.client.hud.TimerHud;
import org.technocracy.spacestation.chemistry.ModScreenHandlers;
import org.technocracy.spacestation.registry.ModBlocks;
import org.technocracy.spacestation.registry.blocks.PlantBlocks;
import org.technocracy.spacestation.registry.items.ToolItems;

import static org.technocracy.spacestation.registry.components.ModComponents.ITEM_TOGGLE_COMPONENT;

public class SpaceStationClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ModScreenHandlers.register(); // добавь это первым!
        HandledScreens.register(ModScreenHandlers.CHEM_MASTER, ChemMasterScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.SUSPICIOUS_GRASS, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WALL_GIRDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WALL_GIRDER_REINFORCED, RenderLayer.getCutout());
        // Crops
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.ALOE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.AMBROSIA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.AMBROSIA_OLYMPIC_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.BLOOD_TOMATO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.BLOONION_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.BLUE_TOMATO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.BUNGO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.CABBAGE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.CHILI_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.CHILLY_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.CORN_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.COTTON_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.DEATHBLOOM_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.EGGPLANT_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.EGGY_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.GARLIC_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.KOIBEAN_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.LAUGHIN_PEA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.MEATWHEAT_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.NETTLE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.OAT_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.ONION_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.ONION_RED_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.PEA_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.PINEAPPLE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.PYROTTON_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.RICE_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.SOYBEANS_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.TOMATO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(PlantBlocks.TOWERCAP_CROP, RenderLayer.getCutout());
        TimerHud.register();
        registerToggleableItem(ToolItems.WELDER);
        registerToggleableItem(ToolItems.LIGHTER);
    }

    public static void registerToggleableItem(Item item) {
        ModelPredicateProviderRegistry.register(
                item,
                Identifier.of(SpaceStation.MOD_ID, "item_toggle"),
                (stack, world, entity, seed) -> {
                    boolean isActivate = stack.getOrDefault(ITEM_TOGGLE_COMPONENT, false);
                    return isActivate ? 1.0F : 0.0F;
                }
        );
    }
}
