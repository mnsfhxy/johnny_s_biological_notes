package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.goal;

import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.young.EntityYoungBeluga;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

/**
 * 白鲸呼吸空气的逻辑
 */
public class BelugaBreathAirGoal extends Goal {
    private final EntityYoungBeluga beluga;
    private final int maxAirSupply;
    private final int interval;
    private final int allowedIceSize = 1;
    private boolean hasBreakIce;

    public BelugaBreathAirGoal(EntityYoungBeluga pBeluga, int maxAirSupply, int interval) {
        this.beluga = pBeluga;
        this.maxAirSupply = maxAirSupply;
        this.interval = interval;

        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    /**
     * 判断剩余空气供应量的方式来确定是否要上浮呼吸空气
     * @return
     */
    @Override
    public boolean canUse() {
        return this.beluga.getAirSupply() <
                this.maxAirSupply
                - this.interval;
    }

    @Override
    public void start() {
        this.findAirPosition();
        this.beluga.startBreathAir();
        this.hasBreakIce = false;
    }

    public void tick() {
        this.findAirPosition();
        this.beluga.moveRelative(0.02F, new Vec3((double)this.beluga.xxa, (double)this.beluga.yya, (double)this.beluga.zza));
        this.beluga.move(MoverType.SELF, this.beluga.getDeltaMovement());
    }

    public boolean canContinueToUse() {
        return this.canUse();
    }

    public boolean isInterruptable() {
        return false;
    }

    private void findAirPosition() {
        Iterable<BlockPos> iterable = BlockPos.betweenClosed(Mth.floor(this.beluga.getX() - 1.0D), this.beluga.getBlockY(), Mth.floor(this.beluga.getZ() - 1.0D), Mth.floor(this.beluga.getX() + 1.0D), Mth.floor(this.beluga.getY() + 8.0D), Mth.floor(this.beluga.getZ() + 1.0D));
        BlockPos blockpos = null;

        for (BlockPos blockpos1 : iterable) {
            if (this.givesAir(this.beluga.level, blockpos1)) {
                if (!hasBreakIce
                        || hasBreakIce
                        && pathTo(blockpos1.above(1)).canReach()) {
                    blockpos = blockpos1;
                    break;
                }
            }
        }

        breakIceWhichHit(blockpos);

        if (blockpos == null) {
            blockpos = new BlockPos(this.beluga.getX(), this.beluga.getY() + 8.0D, this.beluga.getZ());
        }

        this.beluga.getNavigation().moveTo((double)blockpos.getX(), (double)(blockpos.getY() + 1), (double)blockpos.getZ(), 1.0D);
    }

    private boolean givesAir(LevelReader pLevel, BlockPos pPos) {
        BlockState blockstate = pLevel.getBlockState(pPos);
        return (pLevel.getFluidState(pPos).isEmpty() || blockstate.is(Blocks.BUBBLE_COLUMN)) && blockstate.isPathfindable(pLevel, pPos, PathComputationType.LAND);
    }

    private void breakIceIfNeeded(BlockPos airPos) {
        if (airPos == null)
            return;

        Path path = pathTo(airPos);
        if (!path.canReach()) {
            BlockPos abovePos = this.beluga.getOnPos().above();
            if (this.beluga.level.getBlockState(abovePos).getBlock() == Blocks.ICE
            && path.getDistToTarget() <= allowedIceSize + 1) {
                this.hasBreakIce = true;
                 for (BlockPos pos : frontBlocksFrom(abovePos)) {
                    if (this.beluga.level.getBlockState(pos).getBlock() == Blocks.ICE)
                        breakIce(pos);
                }
            }
        }
    }

    /**
     * 破坏碰撞的冰块
     * @param airPos
     */
    private void breakIceWhichHit(BlockPos airPos) {
        if (airPos == null)
            return;

        airPos = airPos.above(1);
        Path path = pathTo(airPos);
        if (!path.canReach()) {
            BlockPos abovePos = this.beluga.getOnPos().above();
            if (this.beluga.level.getBlockState(abovePos).getBlock() == Blocks.ICE
                    && path.getDistToTarget() <= allowedIceSize + 2) {
                this.hasBreakIce = true;

                breakIceAboveBoundingBox();
            }
        }
    }

    private void breakIceOnTheRoad() {
        AABB boundingBox = this.beluga.getBoundingBox().move(this.beluga.getDeltaMovement());
        this.beluga.level.getCollisions(this.beluga, boundingBox).forEach(
                shape -> {
                    BlockPos pos = new BlockPos(shape.bounds().getCenter());
                    BlockState state = this.beluga.level.getBlockState(pos);
                    if (state.getBlock() == Blocks.ICE) {
                        breakIce(pos);
                    }
                }
        );
    }

    private void breakIceAboveBoundingBox() {
        AABB boundBox = this.beluga.getBoundingBox();
        for (BlockPos pos : BlockPos.betweenClosed(
                new BlockPos(boundBox.minX, boundBox.minY + 1, boundBox.minZ),
                new BlockPos(boundBox.maxX, boundBox.maxY + 1, boundBox.maxZ))) {
            if (this.beluga.level.getBlockState(pos).getBlock() == Blocks.ICE)
                breakIce(pos);
        }
    }


    /**
     * 白鲸到目标方块的路径
     * @param airPos
     * @return
     */
    @Nullable
    private Path pathTo(BlockPos airPos) {
        return this.beluga.getNavigation().createPath(airPos, 1);
    }

    /**
     * 获取头顶正前方的方块
     * @param headAbovePos
     * @return
     */
    private Iterable<BlockPos> frontBlocksFrom(BlockPos headAbovePos) {
        Direction facing = this.beluga.getDirection();

        BlockPos end;
        if (facing == Direction.NORTH) {
            end = headAbovePos.relative(facing, 3).offset(2, 0, -0.6); // 向北看，向右移动意味着Z轴减小
        } else if (facing == Direction.SOUTH) {
            end = headAbovePos.relative(facing, 3).offset(2, 0, 0.6); // 向南看，向右移动意味着Z轴增大
        } else if (facing == Direction.WEST) {
            end = headAbovePos.relative(facing, 3).offset(-0.6, 0, 2); // 向西看，向右移动意味着X轴减小
        } else { // 假设只有东方剩下
            end = headAbovePos.relative(facing, 3).offset(0.6, 0, 2); // 向东看，向右移动意味着X轴增大
        }

        return BlockPos.betweenClosed(headAbovePos, end);
    }

    /**
     * 碎冰
     * @param icePos
     */
    private void breakIce(BlockPos icePos) {
        this.beluga.level.destroyBlock(icePos, true);
        // 每破坏一个方块的冰受到1点伤害
        this.beluga.hurt(DamageSource.MAGIC, 1.0F);
    }
}
