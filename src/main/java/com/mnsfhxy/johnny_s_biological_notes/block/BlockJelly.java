package com.mnsfhxy.johnny_s_biological_notes.block;

import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.AbstractGlassBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HalfTransparentBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.common.util.ForgeSoundType;

public class BlockJelly extends HalfTransparentBlock {


    public BlockJelly() {
        super(BlockBehaviour.Properties.of(Material.CLAY, MaterialColor.GRASS).friction(0.8F).sound(SoundInit.JELLY_BLOCK).noOcclusion().strength(0,1200));
    }

    @Override
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity) {
        if (!pEntity.isSteppingCarefully()) {
//            this.destroyEgg(pLevel, pState, pPos, pEntity, 100);
            pLevel.destroyBlock(pPos, false);
        }

        super.stepOn(pLevel, pPos, pState, pEntity);
    }

    @Override
    public void fallOn(Level pLevel, BlockState pState, BlockPos pPos, Entity pEntity, float pFallDistance) {
        pLevel.destroyBlock(pPos, false);
        super.fallOn(pLevel, pState, pPos, pEntity, pFallDistance);
    }
}
