package com.mnsfhxy.johnny_s_biological_notes.block.blockentity;

import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BESigil extends BlockEntity {
    private int bornTime;
    private final int BORN_TIME = UtilLevel.TIME.MINUTE.getTick()*10;

    public BESigil( BlockPos pPos, BlockState pBlockState) {
        super(RegistrationInit.SIGIL_BE.get(), pPos, pBlockState);
        bornTime = BORN_TIME;
    }
    public void tickServer() {
        if (level!=null&&--bornTime <= 0) {
            level.blockUpdated(worldPosition, this.getBlockState().getBlock());
        }
    }
}
