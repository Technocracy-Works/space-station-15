package org.corvaxcraft.spacestation.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.RenderLayer;
import org.corvaxcraft.spacestation.client.chemistry.ChemMasterScreen;
import org.corvaxcraft.spacestation.client.hud.TimerHud;
import org.corvaxcraft.spacestation.chemistry.ModScreenHandlers;
import org.corvaxcraft.spacestation.registry.ModBlocks;

public class SpaceStationClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        ModScreenHandlers.register(); // добавь это первым!
        HandledScreens.register(ModScreenHandlers.CHEM_MASTER, ChemMasterScreen::new);
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WALL_GIRDER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.WALL_GIRDER_REINFORCED, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.COTTON_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TOMATO_CROP, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.TOWERCAP_CROP, RenderLayer.getCutout());
        TimerHud.register();
    }
}
