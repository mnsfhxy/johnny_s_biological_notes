package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.goal;

import com.mnsfhxy.johnny_s_biological_notes.config.Config;
import com.mnsfhxy.johnny_s_biological_notes.tool.SpeedModifier;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.vehicle.Boat;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;
import java.util.List;

public class BelugaFollowBoatGoal extends Goal {
    private int timeToRecalcPath;
    private final PathfinderMob mob;
    @Nullable
    private Player following;
    private Boat followingBoat;
    private BoatGoals currentGoal;

    public static final double SPEED_RATIO = 1.3D;
    private SpeedModifier belugaSpeedModifier;
    public BelugaFollowBoatGoal(PathfinderMob pMob) {
        this.mob = pMob;
        belugaSpeedModifier = new SpeedModifier(this.mob.getAttribute(Attributes.MOVEMENT_SPEED), SPEED_RATIO, "白鲸跟船加速");
    }

    /**
     * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
     * method as well.
     */
    public boolean canUse() {
        List<Boat> list = this.mob.level.getEntitiesOfClass(Boat.class, this.mob.getBoundingBox().inflate(Config.getInstance().doubleValueOf("entity.beluga.boat.scope")));
        boolean flag = false;

        for(Boat boat : list) {
            Entity entity = boat.getControllingPassenger();
            if (entity instanceof Player && (Mth.abs(((Player)entity).xxa) > 0.0F || Mth.abs(((Player)entity).zza) > 0.0F)) {
                flag = true;
                break;
            }
        }

        return this.following != null && (Mth.abs(this.following.xxa) > 0.0F || Mth.abs(this.following.zza) > 0.0F) || flag;
    }

    public boolean isInterruptable() {
        return true;
    }

    /**
     * Returns whether an in-progress EntityAIBase should continue executing
     */
    public boolean canContinueToUse() {
        return this.following != null && this.following.isPassenger() && (Mth.abs(this.following.xxa) > 0.0F || Mth.abs(this.following.zza) > 0.0F);
    }

    /**
     * Execute a one shot task or start executing a continuous task
     */
    public void start() {
        for(Boat boat : this.mob.level.getEntitiesOfClass(Boat.class, this.mob.getBoundingBox().inflate(Config.getInstance().doubleValueOf("entity.beluga.boat.scope")))) {
            if (boat.getControllingPassenger() != null && boat.getControllingPassenger() instanceof Player) {
                this.following = (Player)boat.getControllingPassenger();
                this.followingBoat = boat;
                break;
            }
        }

        this.timeToRecalcPath = 0;
        this.currentGoal = BoatGoals.GO_TO_BOAT;

        belugaSpeedModifier.enable();
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.following = null;
        belugaSpeedModifier.disable();
    }

    /**
     * Keep ticking a continuous task that has already been started
     */
    public void tick() {
        boolean flag = Mth.abs(this.following.xxa) > 0.0F || Mth.abs(this.following.zza) > 0.0F;
        float f = this.currentGoal == BoatGoals.GO_IN_BOAT_DIRECTION ? (flag ? 0.01F : 0.0F) : 0.015F;
        this.mob.moveRelative(f, new Vec3((double)this.mob.xxa, (double)this.mob.yya, (double)this.mob.zza));
        this.mob.move(MoverType.SELF, this.mob.getDeltaMovement());
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10);
            if (this.currentGoal == BoatGoals.GO_TO_BOAT) {
                BlockPos blockpos = this.following.blockPosition().relative(this.following.getDirection().getOpposite());
                blockpos = blockpos.offset(0, -1, 0);
                this.mob.getNavigation().moveTo((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), 1.0D);
                if (this.mob.distanceTo(this.following) < 4.0F) {
                    this.timeToRecalcPath = 0;
                    this.currentGoal = BoatGoals.GO_IN_BOAT_DIRECTION;
                }
            } else if (this.currentGoal == BoatGoals.GO_IN_BOAT_DIRECTION) {
                Direction direction = this.following.getMotionDirection();
                BlockPos blockpos1 = this.following.blockPosition().relative(direction, 10);
                this.mob.getNavigation().moveTo((double)blockpos1.getX(), (double)(blockpos1.getY() - 1), (double)blockpos1.getZ(), 1.0D);
                if (this.mob.distanceTo(this.following) > 12.0F) {
                    this.timeToRecalcPath = 0;
                    this.currentGoal = BoatGoals.GO_TO_BOAT;
                }
            }
        }

        modifyBoatSpeed();
    }

    /**
     * 修改跟随的船只的移速
     */
    private void modifyBoatSpeed() {
        Vec3 speedVector = followingBoat.getDeltaMovement();
        Vec3 modifiedSpeedVector = speedVector.scale(SPEED_RATIO);
        followingBoat.setDeltaMovement(modifiedSpeedVector);
    }

    enum BoatGoals {
        GO_TO_BOAT,
        GO_IN_BOAT_DIRECTION;
    }
}
