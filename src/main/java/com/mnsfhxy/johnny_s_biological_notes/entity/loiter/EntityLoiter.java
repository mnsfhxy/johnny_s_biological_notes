package com.mnsfhxy.johnny_s_biological_notes.entity.loiter;

import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class EntityLoiter extends Monster {
    public float xBodyRot;
    public float xBodyRotO;
    public float zBodyRot;
    public float zBodyRotO;
    private static final float MOVEMENT_SPEED = 0.05F;
    private static final EntityDataAccessor<Boolean> SOUL = SynchedEntityData.defineId(EntityLoiter.class, EntityDataSerializers.BOOLEAN);
    public AnimationState movingAnimationState = new AnimationState();
    public AnimationState tailMovingAnimationState = new AnimationState();

    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() || this.isControlledByLocalInstance()) {
            if (this.isInWater()) {
                this.moveRelative(0.02F, pTravelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.8F));
            } else if (this.isInLava()) {
                this.moveRelative(0.02F, pTravelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale(0.5D));
            } else {
                BlockPos ground = new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ());
                float f = 0.91F;
                if (this.onGround) {
                    f = this.level.getBlockState(ground).getFriction(this.level, ground, this) * 0.91F;
                }

                float f1 = 0.16277137F / (f * f * f);
                f = 0.91F;
                if (this.onGround) {
                    f = this.level.getBlockState(ground).getFriction(this.level, ground, this) * 0.91F;
                }

                this.moveRelative(this.onGround ? 0.1F * f1 : 0.02F, pTravelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale((double) f));
            }
        }

        this.calculateEntityAnimation(this, false);
    }
    private boolean isMoving() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }
    public boolean onClimbable() {
        return false;
    }

    public EntityLoiter(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 6;
        this.moveControl = new EntityLoiter.EntityLoiterMoveControl(this);
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder
                .add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED)
                .add(Attributes.MAX_HEALTH, 25)
                .add(Attributes.ATTACK_DAMAGE, 0)
                .add(Attributes.FLYING_SPEED, (double) 0.3F);
        return builder;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, MOVEMENT_SPEED));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(5, new FloatGoal(this));
        this.goalSelector.addGoal(2, new EntityLoiter.RandomFloatAroundGoal(this));
        this.goalSelector.addGoal(3, new EntityLoiter.EntityLoiterLookGoal(this));
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, UtilLevel.TIME.SECOND.getTick() * 6));
        return super.hurt(pSource, pAmount);
    }

    public MobType getMobType() {
        return MobType.UNDEAD;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SOUL, Boolean.FALSE);
    }

    public boolean isSoul() {
        return this.entityData.get(SOUL);
    }

    public void setSoul(boolean b) {
        this.entityData.set(SOUL, b);
    }

    protected boolean isSunSensitive() {
        return true;
    }

    public void aiStep() {
        if (this.isAlive()) {
            if (this.isSunSensitive() && this.isSunBurnTick()) {
                this.setSecondsOnFire(8);
            }
        }

        super.aiStep();
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        if (this.isSoul()) {
            this.spawnAtLocation(new ItemStack(RegistrationInit.ITEM_SOUL_TUMOR.get(), pLooting + 1));
        }
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundInit.LOITER_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.LOITER_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundInit.LOITER_AMBIENT.get();
    }

    @Override
    public void tick() {
        super.tick();
        if (!this.level.isClientSide) {
            if (!isSoul()) {
                for (LivingEntity livingEntity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(24D))) {
                    if (livingEntity.isDeadOrDying()) {
                        this.playSound(SoundInit.LOITER_SATURATE.get(), this.getSoundVolume(), this.getVoicePitch());
                        this.setSoul(true);
                    }
                }
            }
        }else{
            if(this.isMoving()){
                this.movingAnimationState.startIfStopped(this.tickCount);
                this.tailMovingAnimationState.startIfStopped(this.tickCount);
            }else{
                this.movingAnimationState.stop();
                this.tailMovingAnimationState.stop();
            }
        }

    }

    static class RandomFloatAroundGoal extends Goal {
        private final EntityLoiter entityLoiter;

        public RandomFloatAroundGoal(EntityLoiter pEntityLoiter) {
            this.entityLoiter = pEntityLoiter;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            MoveControl movecontrol = this.entityLoiter.getMoveControl();
            if (!movecontrol.hasWanted()) {
                return true;
            } else {
                double d0 = movecontrol.getWantedX() - this.entityLoiter.getX();
                double d1 = movecontrol.getWantedY() - this.entityLoiter.getY();
                double d2 = movecontrol.getWantedZ() - this.entityLoiter.getZ();
                double d3 = d0 * d0 + d1 * d1 + d2 * d2;
                return d3 < 1.0D || d3 > 3600.0D;
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            return false;
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            RandomSource randomsource = this.entityLoiter.getRandom();
            double d0 = this.entityLoiter.getX() + (double) ((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d1 = this.entityLoiter.getY() + (double) ((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            double d2 = this.entityLoiter.getZ() + (double) ((randomsource.nextFloat() * 2.0F - 1.0F) * 16.0F);
            this.entityLoiter.getMoveControl().setWantedPosition(d0, d1, d2, 1.0D);
        }
    }

    static class EntityLoiterMoveControl extends MoveControl {
        private final EntityLoiter entityLoiter;
        private int floatDuration;

        public EntityLoiterMoveControl(EntityLoiter pEntityLoiter) {
            super(pEntityLoiter);
            this.entityLoiter = pEntityLoiter;
        }

        public void tick() {
            if (this.operation == MoveControl.Operation.MOVE_TO) {
                if (this.floatDuration-- <= 0) {
                    this.floatDuration += this.entityLoiter.getRandom().nextInt(5) + 2;
                    Vec3 vec3 = new Vec3(this.wantedX - this.entityLoiter.getX(), this.wantedY - this.entityLoiter.getY(), this.wantedZ - this.entityLoiter.getZ());
                    double d0 = vec3.length();
                    vec3 = vec3.normalize();
                    if (this.canReach(vec3, Mth.ceil(d0))) {
                        this.entityLoiter.setDeltaMovement(this.entityLoiter.getDeltaMovement().add(vec3.scale(0.1D)));
                    } else {
                        this.operation = MoveControl.Operation.WAIT;
                    }
                }

            }
        }

        private boolean canReach(Vec3 pPos, int pLength) {
            AABB aabb = this.entityLoiter.getBoundingBox();

            for (int i = 1; i < pLength; ++i) {
                aabb = aabb.move(pPos);
                if (!this.entityLoiter.level.noCollision(this.entityLoiter, aabb)) {
                    return false;
                }
            }

            return true;
        }
    }

    static class EntityLoiterLookGoal extends Goal {
        private final EntityLoiter entityLoiter;

        public EntityLoiterLookGoal(EntityLoiter entityLoiter) {
            this.entityLoiter = entityLoiter;
            this.setFlags(EnumSet.of(Goal.Flag.LOOK));
        }

        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            return true;
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            if (this.entityLoiter.getTarget() == null) {
                Vec3 vec3 = this.entityLoiter.getDeltaMovement();
                this.entityLoiter.setYRot(-((float)Mth.atan2(vec3.x, vec3.z)) * (180F / (float)Math.PI));
                this.entityLoiter.yBodyRot = this.entityLoiter.getYRot();
            } else {
                LivingEntity livingentity = this.entityLoiter.getTarget();
                double d0 = 64.0D;
                if (livingentity.distanceToSqr(this.entityLoiter) < 4096.0D) {
                    double d1 = livingentity.getX() - this.entityLoiter.getX();
                    double d2 = livingentity.getZ() - this.entityLoiter.getZ();
                    this.entityLoiter.setYRot(-((float)Mth.atan2(d1, d2)) * (180F / (float)Math.PI));
                    this.entityLoiter.yBodyRot = this.entityLoiter.getYRot();
                }
            }

        }
    }
}

