package com.mnsfhxy.johnny_s_biological_notes.block.gluedblock;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BlockGluedConcretePowder extends ConcretePowderBlock {
    public BlockGluedConcretePowder(Block pConcrete, Properties pProperties) {
        super(pConcrete, pProperties);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {

    }
}
