package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.goal;

import com.mnsfhxy.johnny_s_biological_notes.config.Config;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.EntityBeluga;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.message.BelugaRotationSyncPacket;
import com.mnsfhxy.johnny_s_biological_notes.init.NetworkInit;
import com.mnsfhxy.johnny_s_biological_notes.tool.CooldownMonitor;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.network.PacketDistributor;

import java.util.EnumSet;

/**
 * 白鲸破冰的Goal
 */
public class BelugaBreakingIceGoal extends Goal {
    public static final int COOLDOWN_INTERVAL = Config.getInstance().intValueOf("entity.beluga.breakingIce.cooldownInterval");
    public static final int CHECK_SCOPE = Config.getInstance().intValueOf("entity.beluga.breakingIce.checkScope");
    public static final int BREAK_SCOPE = Config.getInstance().intValueOf("entity.beluga.breakingIce.breakScope");

    private final EntityBeluga beluga;
    //用于控制破冰冷却时间的Monitor
    private final CooldownMonitor cooldownMonitor;
    //处理破冰前暂停的Controller
    private final PauseController pauseController;
    //判断是否碰到了其他的方块，碰到了就停止
    private boolean isOtherBlockReached;

    public BelugaBreakingIceGoal(EntityBeluga beluga) {
        this.beluga = beluga;
        this.cooldownMonitor = new CooldownMonitor(COOLDOWN_INTERVAL);
        this.pauseController = new PauseController();

        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        if (!this.cooldownMonitor.checkIsActive()) {
            return false;
        }

        return existsIceAboveCurrentPos();
    }

    @Override
    public void start() {
        this.pauseController.active();
        this.isOtherBlockReached = false;
        startRotation();
    }


    @Override
    public void tick() {
        if (this.pauseController.checkIsActive()) {
            this.beluga.setDeltaMovement(0D, 0D, 0D);

            return;
        }

        // 增加白鲸的移动速度并向上冲击
        this.beluga.setSpeed(this.beluga.getSpeed() * Config.getInstance().floatValueOf("entity.beluga.breakingIce.speedModifier"));
        this.beluga.setDeltaMovement(this.beluga.getDeltaMovement().x, 1.0, this.beluga.getDeltaMovement().z);

        // 破坏沿途的冰
        BlockPos start = new BlockPos(this.beluga.getX() - BREAK_SCOPE, this.beluga.getY() - BREAK_SCOPE, this.beluga.getZ() - BREAK_SCOPE);
        BlockPos end = new BlockPos(this.beluga.getX() + BREAK_SCOPE, this.beluga.getY() + BREAK_SCOPE, this.beluga.getZ() + BREAK_SCOPE);
        for (BlockPos pos : BlockPos.betweenClosed(start, end)) {
            if (this.beluga.level.getBlockState(pos).getBlock() == Blocks.ICE) {
                this.beluga.level.destroyBlock(pos, true);
                // 每破坏一个方块的冰受到1点伤害
                this.beluga.hurt(DamageSource.MAGIC, Config.getInstance().floatValueOf("entity.beluga.breakingIce.hurtDamage"));
            } else if (this.beluga.level.getBlockState(pos).getBlock() != Blocks.WATER
            && this.beluga.level.getBlockState(pos).getBlock() != Blocks.AIR) {
                this.isOtherBlockReached = true;
                return;
            }
        }
    }

    @Override
    public void stop() {
        this.cooldownMonitor.reset();
        stopRotation();
    }

    @Override
    public boolean canContinueToUse() {
        return !isOtherBlockReached;
    }

    /**
     * 当前位置上方是否存在冰块
     *
     * @return
     */
    private boolean existsIceAboveCurrentPos() {
        BlockPos start = new BlockPos(this.beluga.getX(), this.beluga.getY() + 1, this.beluga.getZ());
        BlockPos end = new BlockPos(this.beluga.getX(), this.beluga.getY() + CHECK_SCOPE, this.beluga.getZ());
        return BlockPos.betweenClosedStream(start, end)
                .anyMatch(pos -> this.beluga.level.getBlockState(pos).getBlock() == Blocks.ICE);
    }

    /**
     * 发送网络消息，开始将水平的模型旋转到垂直方向
     */
    private void startRotation() {
        NetworkInit.NETWORK.send(PacketDistributor.ALL.noArg(), new BelugaRotationSyncPacket(this.beluga.getId(), true));
    }

    /**
     * 发送网络消息，开始将垂直的模型恢复到水平方向
     */
    private void stopRotation() {
        NetworkInit.NETWORK.send(PacketDistributor.ALL.noArg(), new BelugaRotationSyncPacket(this.beluga.getId(), false));
    }

    class PauseController {
        public static final int PAUSE_TIME = Config.getInstance().intValueOf("entity.beluga.breakingIce.pauseTime");

        private int pauseTime;

        public PauseController() {
        }

        private void active() {
            this.pauseTime = PAUSE_TIME / 2;  // 40刻等于2秒
        }

        public boolean checkIsActive() {
            if (this.pauseTime <= 0)
                return false;

            update();
            return true;
        }

        private void update() {
            this.pauseTime--;
        }
    }
}
