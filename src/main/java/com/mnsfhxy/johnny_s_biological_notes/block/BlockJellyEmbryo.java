package com.mnsfhxy.johnny_s_biological_notes.block;

import com.mnsfhxy.johnny_s_biological_notes.block.blockentity.JellyEmbryoBE;
import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.EntityDrifter;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BlockJellyEmbryo extends HalfTransparentBlock implements EntityBlock {

    private static final VoxelShape SHAPE = Block.box(5.0D, 0.0D, 5.0D, 12.0D, 1.5D, 12.0D);

    public BlockJellyEmbryo() {
        super(BlockBehaviour.Properties
                .of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundType.SLIME_BLOCK).noOcclusion().strength(0).randomTicks().noCollission());
    }






    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return SHAPE;
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new JellyEmbryoBE(pPos,pState);
    }
    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (!pLevel.isClientSide()) {
            return (lvl, pos, stt, te) -> {
                if (te instanceof JellyEmbryoBE jellyEmbryoBE) jellyEmbryoBE.tickServer();
            };
        }
        return null;
    }


}
