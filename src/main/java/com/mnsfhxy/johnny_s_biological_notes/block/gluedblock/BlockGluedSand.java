package com.mnsfhxy.johnny_s_biological_notes.block.gluedblock;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class BlockGluedSand extends SandBlock {
    public BlockGluedSand(int pDustColor, Properties pProperties) {
        super(pDustColor, pProperties);
    }

    @Override
    public void tick(BlockState pState, ServerLevel pLevel, BlockPos pPos, RandomSource pRandom) {
//        super.tick(pState, pLevel, pPos, pRandom);
    }


}
