package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.goal;

import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.EntityBeluga;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;

/**
 * 参考北极熊的攻击实现的白鲸攻击Goal
 */
public class BelugaMeleeAttackGoal extends MeleeAttackGoal {
    private final EntityBeluga beluga;
    public BelugaMeleeAttackGoal(EntityBeluga pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
        super(pMob, pSpeedModifier, pFollowingTargetEvenIfNotSeen);
        this.beluga = pMob;
    }

    protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
        double d0 = this.getAttackReachSqr(pEnemy);
        if (pDistToEnemySqr <= d0 && this.isTimeToAttack()) {
            this.resetAttackCooldown();
            this.beluga.doHurtTarget(pEnemy);
            this.beluga.setStanding(false);
        } else if (pDistToEnemySqr <= d0 * 2.0D) {
            if (this.isTimeToAttack()) {
                this.beluga.setStanding(false);
                this.resetAttackCooldown();
            }

            if (this.getTicksUntilNextAttack() <= 10) {
                this.beluga.setStanding(true);
            }
        } else {
            this.resetAttackCooldown();
            this.beluga.setStanding(false);
        }
    }

    /**
     * Reset the task's internal state. Called when this task is interrupted by another one
     */
    public void stop() {
        this.beluga.setStanding(false);
        super.stop();
    }

    protected double getAttackReachSqr(LivingEntity pAttackTarget) {
        return (double)(4.0F + pAttackTarget.getBbWidth());
    }
}
