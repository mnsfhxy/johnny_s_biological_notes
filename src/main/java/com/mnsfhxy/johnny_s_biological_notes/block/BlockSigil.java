package com.mnsfhxy.johnny_s_biological_notes.block;

import com.mnsfhxy.johnny_s_biological_notes.block.blockentity.BEJellyEmbryo;
import com.mnsfhxy.johnny_s_biological_notes.block.blockentity.BESigil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEventListener;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import org.jetbrains.annotations.Nullable;

public class BlockSigil extends HalfTransparentBlock implements EntityBlock {
    public BlockSigil() {
        super(BlockBehaviour.Properties.of(Material.STONE, MaterialColor.STONE).lightLevel((x)->{return 15;}).strength(0.3F,6F).sound(SoundType.STONE));
    }


    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new BESigil(pPos, pState);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level pLevel, BlockState pState, BlockEntityType<T> pBlockEntityType) {
        if (!pLevel.isClientSide()) {
            return (lvl, pos, stt, te) -> {
                if (te instanceof BESigil) ((BESigil) te).tickServer();
            };
        }
        return null;
    }



}
