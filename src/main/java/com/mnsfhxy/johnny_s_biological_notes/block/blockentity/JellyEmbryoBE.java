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
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public class JellyEmbryoBE extends BlockEntity {
    private int bornTime;
    private final int BORN_TIME = UtilLevel.TIME.MINUTE.getTick();
    public JellyEmbryoBE(BlockPos pPos, BlockState pBlockState) {
        super(RegistrationInit.JELLY_EMBRYO_BE.get(), pPos, pBlockState);
        bornTime = BORN_TIME;
    }
    private boolean hasEnoughSpace(BlockGetter pLevel, BlockPos pPos) {
        for (BlockPos blockpos : BlockPos.betweenClosed(pPos, pPos.offset(1, 2, 1))) {
            if (!pLevel.getBlockState(blockpos).getCollisionShape(pLevel, blockpos).isEmpty()) {
                return false;
            }
        }

        return true;
    }
    // Called by the block ticker
    public void tickServer() {
        if (level!=null&&--bornTime <= 0) {
            bornTime = BORN_TIME;
            int jellyNum = level.random.nextInt(3) + 1;
            for (int i = 0; i < jellyNum; i++) {
                if (this.hasEnoughSpace(level,worldPosition)) {
                    double rx=level.random.nextInt(2)/3.0;
                    double rz=level.random.nextInt(2)/3.0;

                    BlockPos spawnPos = new BlockPos(worldPosition.getX() + rx, worldPosition.getY(), worldPosition.getZ() + rz);
                    EntityJelly entityJelly = RegistrationInit.JELLY.get().spawn((ServerLevel) level, (CompoundTag) null, (Component) null, (Player) null, spawnPos, MobSpawnType.EVENT, false, false);
                    if (entityJelly != null) {
                        entityJelly.restrictTo(worldPosition, 16);
                    }
                }
            }
            level.destroyBlock(worldPosition, false);
        }
    }
//    // Inside MyBlockEntity
//    public static void tick(Level level, BlockPos pos, BlockState state, MyBlockEntity blockEntity) {
//        // Do stuff
//    }
}
