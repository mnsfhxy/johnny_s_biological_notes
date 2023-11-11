package com.mnsfhxy.johnny_s_biological_notes.entity.tridacna;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.EntityCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.bubble.EntityJellyBubble;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.TropicalFish;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.fluids.FluidType;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class EntityTridacna extends PathfinderMob {
    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(EntityTridacna.class, EntityDataSerializers.INT);
//    private static final EntityDataAccessor<Integer> CLOSE_REMAIN_TICK = SynchedEntityData.defineId(EntityTridacna.class, EntityDataSerializers.INT);
    AttributeModifier MODIFIER_CLOSE_SHELL = new AttributeModifier("close_shell", 4, AttributeModifier.Operation.ADDITION);
    AttributeModifier MODIFIER_CLOSE_SHELL_BROKEN = new AttributeModifier("close_shell_broken", 2, AttributeModifier.Operation.ADDITION);
    private int CLOSE_REMAIN_TICK;
    public EntityTridacna(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        xpReward = 3;
        if (random.nextInt(25) == 0) {
            this.setVariant(2);
        } else {
            this.setVariant(0);
        }
        CLOSE_REMAIN_TICK=0;
    }

    private AttributeModifier getCloseShellModifier() {
        return this.getVariant() % 2 == 0 ? MODIFIER_CLOSE_SHELL : MODIFIER_CLOSE_SHELL_BROKEN;
    }

    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);
        pCompound.putInt("Variant", this.getVariant());
    }


    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);
        this.setVariant(pCompound.getInt("Variant"));
    }

    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(VARIANT, 0);
//        this.entityData.define(CLOSE_REMAIN_TICK, 0);
    }

    public boolean isShellOpen() {
        return getCloseRemindTick() == 0;
    }

//    public void setCloseRemindTick(int p) {
//        this.entityData.set(CLOSE_REMAIN_TICK, p);
//    }
//
//    public int getCloseRemindTick() {
//        return this.entityData.get(CLOSE_REMAIN_TICK);
//    }
public void setCloseRemindTick(int p) {
    CLOSE_REMAIN_TICK=p;
}

    public int getCloseRemindTick() {
        return CLOSE_REMAIN_TICK;
    }
    public void setVariant(int pVariantId) {
        this.entityData.set(VARIANT, pVariantId);
    }

    public int getVariant() {
        return this.entityData.get(VARIANT);
    }

    public int getArmorValue() {
        return Mth.floor(this.getAttributeValue(Attributes.ARMOR));
    }

    public void closeShell() {
        if (!(Objects.requireNonNull(this.getAttributes().getInstance(Attributes.ARMOR)).hasModifier(getCloseShellModifier()))) {
            playSound(SoundInit.TRIDACNA_CLOSE.get(), this.getSoundVolume(), this.getVoicePitch());
            Objects.requireNonNull(this.getAttributes().getInstance(Attributes.ARMOR)).addPermanentModifier(getCloseShellModifier());
        }
        ;
        this.setCloseRemindTick(UtilLevel.TIME.MINUTE.getTick() * 3);
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.TRIDACNA_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundInit.TRIDACNA_HURT.get();
    }

    public void openShell() {
        playSound(SoundInit.TRIDACNA_OPEN.get(), this.getSoundVolume(), this.getVoicePitch());
        if (Objects.requireNonNull(this.getAttributes().getInstance(Attributes.ARMOR)).hasModifier(MODIFIER_CLOSE_SHELL)) {
            Objects.requireNonNull(this.getAttributes().getInstance(Attributes.ARMOR)).removeModifier(MODIFIER_CLOSE_SHELL);

        } else {
            Objects.requireNonNull(this.getAttributes().getInstance(Attributes.ARMOR)).removeModifier(MODIFIER_CLOSE_SHELL_BROKEN);

        }

    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getMsgId().equals("hotFloor")) {
            pAmount = 0;
            return false;
        }
        if (!isShellOpen()) {
            Entity entity = pSource.getDirectEntity();
            if (entity instanceof AbstractArrow) {
                return false;
            }
        }
        if (!isShellOpen() && pAmount > 10) {
            playSound(SoundInit.TRIDACNA_BROKEN.get(), this.getSoundVolume(), this.getVoicePitch());
            this.setVariant(this.getVariant() + 1);
        }
        if (pAmount > 0) closeShell();
        return super.hurt(pSource, pAmount);
    }


    public void handleAirSupply(int pAirSupply) {
        if (this.isAlive() && !this.isInWaterOrBubble()) {
            this.setAirSupply(pAirSupply - 1);
            if (this.getAirSupply() == -20) {
                this.setAirSupply(0);
                this.hurt(DamageSource.DROWN, 1.0F);
            }
        } else {
            this.setAirSupply(getMaxAirSupply());
        }

    }

    @Override
    public int getMaxAirSupply() {
        return UtilLevel.TIME.MINUTE.getTick() * 2;
    }

    public static AttributeSupplier.Builder prepareAttributes() {
//        System.out.println("aaaaaaaaaaa");
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.ARMOR, 0).add(Attributes.KNOCKBACK_RESISTANCE, Integer.MAX_VALUE);


    }



    public static boolean checkSpawnRules(EntityType<? extends Mob> pType, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        return true;
    }

    @Override
    public boolean canDrownInFluidType(FluidType type) {
        return ForgeMod.WATER_TYPE.get() != type;
    }

    //!!!没有Override会导致无法在水中生成(详见NaturalSpawner$isValidPositionForMob)
    public boolean checkSpawnObstruction(LevelReader pLevel) {
        return pLevel.isUnobstructed(this);
    }
    public boolean isPushedByFluid() {
        return false;
    }

    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }

    public void baseTick() {
        int i = this.getAirSupply();
        super.baseTick();
        this.handleAirSupply(i);
    }

    @Override
    public boolean canBreatheUnderwater() {
        return true;
    }

    public MobType getMobType() {
        return MobType.WATER;
    }

    @Override
    public void tick() {
        super.tick();
//        System.out.println("llllllllllllllllllll");
        updateShellState();
        if (this.isInWater()) {
            if (this.level instanceof ServerLevel) {
                if(this.level.isWaterAt(this.getOnPos().above().above())) {
                    double randomX = this.getRandomX(0.6D);
                    double randomY = this.getRandomY();
                    double randomZ = this.getRandomZ(0.6D);
                    ((ServerLevel) this.level).sendParticles(ParticleTypes.BUBBLE, randomX, randomY, randomZ, 1, 0.0D, -1D, 0.0D, 0.0D);
                }
            }
            for (LivingEntity livingEntity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(3D))) {
                livingEntity.setAirSupply(livingEntity.getMaxAirSupply());
            }
            for (TropicalFish tropicalFish : this.level.getEntitiesOfClass(TropicalFish.class, this.getBoundingBox().inflate(15D))) {
                if (!tropicalFish.getMoveControl().hasWanted())
                    tropicalFish.getMoveControl().setWantedPosition(this.getX(), this.getY(), this.getZ(), 0.3);

            }
        }
    }

    @Override
    public void push(Entity pEntity) {
        if (!(pEntity instanceof AbstractFish)) closeShell();

    }

    @Override
    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        closeShell();
        return super.causeFallDamage(pFallDistance, pMultiplier, pSource);
    }

    @Override
    public boolean isPushable() {
        return false;
    }

    private void updateShellState() {
        if (!this.isInWater()) {
            closeShell();
        }
        if (this.getBlockStateOn().isAir() || this.getBlockStateOn().is(Blocks.WATER)) {
            closeShell();
        }
        if (this.getCloseRemindTick() == 1) {
            openShell();
        }
        this.setCloseRemindTick(Math.max(0, this.getCloseRemindTick() - 1));

    }
}
