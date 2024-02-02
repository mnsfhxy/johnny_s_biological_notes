package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.goal;

import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.message.BelugaBlowholePacket;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.young.EntityYoungBeluga;
import com.mnsfhxy.johnny_s_biological_notes.init.NetworkInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PacketDistributor;

import java.util.EnumSet;

/**
 * 水上停留的Goal
 */
public class BelugaStayOnSurfaceGoal extends Goal {
    private final EntityYoungBeluga beluga;
    private int startTick;
    private int stayTime;
    private Integer particleInterval;

    public BelugaStayOnSurfaceGoal(EntityYoungBeluga beluga, int stayTime, int particleInterval) {
        this.beluga = beluga;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));

        this.stayTime = stayTime;
        this.particleInterval = particleInterval;
    }

    @Override
    public boolean canUse() {
        return isAirWithEyeFluid()
                && notBubbleColumnWithEyeFluid()
                && !this.beluga.hasStayedOnSurface();
    }

    @Override
    public void start() {
        this.beluga.startStayOnSurface();
        this.startTick = this.beluga.tickCount;
        this.beluga.playSound(SoundInit.BELUGA_BREATH.get(), 1.0f, 1.0f);
    }

    /**
     * 根据配置的粒子效果间隔，触发粒子效果
     */
    @Override
    public void tick() {
        this.beluga.setDeltaMovement(0D, 0D, 0D);

        if (currentStayTime() %
                particleInterval == 0)
            triggerParticle();
    }

    /**
     * 触发粒子效果
     * 通过服务端通信的方式触发
     */
    private void triggerParticle() {
        Vec3 pos = this.beluga.position();
        double blowholeY = pos.y + 1.13D; // 根据实际模型调整气孔高度
        BlockPos triggerPoint = new BlockPos(pos.x, blowholeY, pos.z);

        NetworkInit.NETWORK.send(PacketDistributor.TRACKING_ENTITY.with(() -> this.beluga),
                new BelugaBlowholePacket(triggerPoint));
    }

    @Override
    public boolean canContinueToUse() {
        return currentStayTime() < stayTime;
    }

    /**
     * 通过判断眼部位置是否会溺水，判断是不是空气
     * @return
     */
    private boolean isAirWithEyeFluid() {
        return !this.beluga.canDrownInFluidType(this.beluga.getEyeInFluidType());
    }

    /**
     * 确定眼部位置不是气泡柱
     * @return
     */
    private boolean notBubbleColumnWithEyeFluid() {
        return !this.beluga.level.getBlockState(new BlockPos(this.beluga.getX(), this.beluga.getEyeY(), this.beluga.getZ())).is(Blocks.BUBBLE_COLUMN);
    }

    /**
     * 计算当前白鲸在水面的停留时间
     * @return
     */
    private int currentStayTime() {
        return this.beluga.tickCount - this.startTick;
    }
}