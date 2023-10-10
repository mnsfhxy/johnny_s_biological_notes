package com.mnsfhxy.johnny_s_biological_notes.block.blockentity;

import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BETridacnaShell extends BlockEntity {
    public BETridacnaShell(BlockPos pPos, BlockState pBlockState) {
        super(RegistrationInit.TRIDACNA_SHELL_BE.get(), pPos, pBlockState);
    }
}
