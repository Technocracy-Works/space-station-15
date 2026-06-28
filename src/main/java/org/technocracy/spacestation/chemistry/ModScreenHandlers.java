package org.technocracy.spacestation.chemistry;

import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import org.technocracy.spacestation.SpaceStation;

public class ModScreenHandlers {

    public static final ExtendedScreenHandlerType<ChemMasterScreenHandler, BlockPos> CHEM_MASTER =
            Registry.register(
                    Registries.SCREEN_HANDLER,
                    Identifier.of(SpaceStation.MOD_ID, "chem_master"),
                    new ExtendedScreenHandlerType<>(
                            ChemMasterScreenHandler::new,
                            BlockPos.PACKET_CODEC
                    )
            );

    public static void register() {
        // Пусто. Нужно только чтобы класс загрузился.
    }
}