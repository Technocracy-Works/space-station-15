package org.technocracy.spacestation.chemistry;

import com.mojang.serialization.MapCodec;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class ChemMasterBlock extends BlockWithEntity {

    public ChemMasterBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new ChemMasterBlockEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos,
                              PlayerEntity player, BlockHitResult hit) {
        if (world.isClient()) return ActionResult.SUCCESS;

        BlockEntity be = world.getBlockEntity(pos);
        if (be instanceof ChemMasterBlockEntity entity) {
            player.openHandledScreen(entity);
        }
        return ActionResult.SUCCESS;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return createCodec(ChemMasterBlock::new);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state,
                                                                  BlockEntityType<T> type) {
        if (world.isClient()) return null;
        return validateTicker(type, ModBlockEntities.CHEM_MASTER,
                (w, pos, s, be) -> be.tick(w, pos, s));
    }
}