package com.mnsfhxy.johnny_s_biological_notes.entity.loiter;

import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.EntityDrifter;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.EntityPeeper;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import net.minecraft.Util;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EntityLoiter extends Monster {
    private static final float MOVEMENT_SPEED = 0.5F;
    private static final EntityDataAccessor<Boolean> SOUL = SynchedEntityData.defineId(EntityLoiter.class, EntityDataSerializers.BOOLEAN);

    public EntityLoiter(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward=6;
    }
    public static AttributeSupplier.Builder prepareAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED);
        builder = builder.add(Attributes.MAX_HEALTH, 25);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 0);
        return builder;
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, MOVEMENT_SPEED));
        this.goalSelector.addGoal(2, new RandomLookAroundGoal(this));
        this.goalSelector.addGoal(3, new FloatGoal(this));
    }
    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        this.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, UtilLevel.TIME.SECOND.getTick()*6));
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
    public boolean isSoul(){
        return this.entityData.get(SOUL);
    }
    public void setSoul(boolean b){
        this.entityData.set(SOUL,b);
    }
    protected boolean isSunSensitive() {
        return true;
    }
    public void aiStep() {
        if (this.isAlive()) {
            if ( this.isSunSensitive() && this.isSunBurnTick()) {
                    this.setSecondsOnFire(8);
                }
            }
        super.aiStep();
        }
    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        if(this.isSoul()){
            this.spawnAtLocation(new ItemStack(RegistrationInit.ITEM_SOUL_TUMOR.get(),pLooting+1));
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
       if(!this.level.isClientSide){
           if(!isSoul()){
               for (LivingEntity livingEntity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(8D))) {
                   if (livingEntity.isDeadOrDying()){
                       this.playSound(SoundInit.LOITER_SATURATE.get(),this.getSoundVolume(),this.getVoicePitch());
                       this.setSoul(true);
                   }
               }
           }
        }

    }
}

