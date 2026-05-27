package org.corvaxcraft.spacestation.chemistry;

import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.corvaxcraft.spacestation.ModBlocks;
import org.corvaxcraft.spacestation.SpaceStation;

public class ModBlockEntities {

    public static BlockEntityType<ChemMasterBlockEntity> CHEM_MASTER;

    public static void register() {
        CHEM_MASTER = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                Identifier.of(SpaceStation.MOD_ID, "chem_master"),
                FabricBlockEntityTypeBuilder.create(ChemMasterBlockEntity::new,
                        ModBlocks.CHEM_MASTER_BLOCK).build()
        );
    }
}