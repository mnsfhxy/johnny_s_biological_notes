package com.mnsfhxy.johnny_s_biological_notes.mixin;

import com.mnsfhxy.johnny_s_biological_notes.block.BlockJelly;
import com.mnsfhxy.johnny_s_biological_notes.init.TagsInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public class MixinBlock {
    @Inject(method = "stepOn", at = @At("TAIL"))
    public void stepOn(Level pLevel, BlockPos pPos, BlockState pState, Entity pEntity, CallbackInfo ci) {
        //使方块下方果冻块碎裂
        if (!pEntity.isSteppingCarefully()) {
            BlockState bl = pLevel.getBlockState(pPos);
            if (!bl.is(TagsInit.Blocks.SAFE_ON_JELLY)) {
                BlockState blockState = pLevel.getBlockState(pPos.below());
                if (blockState.getBlock() instanceof BlockJelly) {
                    pLevel.destroyBlock(pPos.below(), false);
                }
            }
        }

    }
}
