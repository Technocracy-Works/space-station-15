package org.technocracy.spacestation.chemistry;

import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.technocracy.spacestation.SpaceStation;
import org.technocracy.spacestation.registry.ModBlocks;

public class ModBlockEntities {

    public static BlockEntityType<ChemMasterBlockEntity> CHEM_MASTER;

    public static void register() {
        CHEM_MASTER = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(SpaceStation.MOD_ID, "chem_master"),
                BlockEntityType.Builder.create(ChemMasterBlockEntity::new,
                        ModBlocks.CHEM_MASTER_BLOCK).build()
        );
    }
}
