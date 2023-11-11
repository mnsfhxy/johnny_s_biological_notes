package com.mnsfhxy.johnny_s_biological_notes.entity.drifter;

import com.google.gson.Gson;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import com.mnsfhxy.johnny_s_biological_notes.init.TagsInit;
import com.mnsfhxy.johnny_s_biological_notes.util.ModAnimation;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.level.pathfinder.WalkNodeEvaluator;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class EntityDrifter extends PathfinderMob {
    private static final EntityDataAccessor<String> FAV_STR = SynchedEntityData.defineId(EntityDrifter.class, EntityDataSerializers.STRING);
    public Map<UUID, Favorability> favorability = new HashMap<>();
    public AnimationState walkingAnimationState = new AnimationState();
    public AnimationState alertAnimationState = new AnimationState();
    //
//    public AnimationState alert0AnimationState = new AnimationState();
//    public AnimationState alert1AnimationState = new AnimationState();
        public AnimationState fightAnimationState = new AnimationState();
    //    public ModAnimation walkingAnimationState = new ModAnimation();
//    public ModAnimation alert0AnimationState = new ModAnimation();
//    public ModAnimation alert1AnimationState = new ModAnimation();
//    public ModAnimation fightAnimationState = new ModAnimation();
    private boolean renderItem = false;
    private boolean haveGift = true;

    public EntityDrifter(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.xpReward = 5;
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.35F);
        builder = builder.add(Attributes.MAX_HEALTH, 50);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64);
        return builder;
    }

    private static class FavJson {
        private String uuid;
        private float value;

        public String getUuid() {
            return uuid;
        }

        public void setUuid(String uuid) {
            this.uuid = uuid;
        }

        public float getValue() {
            return value;
        }

        public void setValue(float value) {
            this.value = value;
        }
    }

    private static HashMap<UUID, Favorability> favStrToMap(String s) {
        HashMap<UUID, Favorability> stringFavorabilityHashMap = new HashMap<>();
        if (s == null || s.equals("")) return stringFavorabilityHashMap;
        Gson gson = new Gson();
        FavJson[] favJsons = gson.fromJson(s, FavJson[].class);
        for (var fav : favJsons) {
            stringFavorabilityHashMap.put(UUID.fromString(fav.getUuid()), new Favorability(fav.getValue()));
        }
        return stringFavorabilityHashMap;
    }

    private static String MapToFavStr(Map<UUID, Favorability> map) {
        List<FavJson> favJsons = new ArrayList<>();
        Gson gson = new Gson();
        for (var key : map.keySet()) {
            FavJson favJson = new FavJson();
            favJson.setUuid(key.toString());
            favJson.setValue(map.get(key).getValue());
            favJsons.add(favJson);
        }
        return gson.toJson(favJsons);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new DrifterMeleeAttackGoal(this, 1.0D, false){});
        this.goalSelector.addGoal(3, new FllowPlayerGoal(this));
        this.goalSelector.addGoal(4, new LookAtPlayerGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(5, new LookAtPlayerGoal(this, Mob.class, 8.0F));
        this.goalSelector.addGoal(6, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this, Player.class) {
            @Override
            public boolean canUse() {
                return (!(this.mob.getLastHurtByMob() instanceof Player)) && super.canUse();
            }
        }));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (p_28879_) -> {
            return p_28879_ instanceof Enemy && !(p_28879_ instanceof Creeper);
        }));
        //        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Zombie.class, true));
//        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, ZombieVillager.class, true));
//        this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WitherSkeleton.class, true));
//        this.targetSelector.addGoal(5, new NearestAttackableTargetGoal<>(this, Witch.class, true));
//        this.targetSelector.addGoal(6, new NearestAttackableTargetGoal<>(this, Vindicator.class, true));
//        this.targetSelector.addGoal(7, new NearestAttackableTargetGoal<>(this, Vex.class, true));
//        this.targetSelector.addGoal(8, new NearestAttackableTargetGoal<>(this, Stray.class, true));
//        this.targetSelector.addGoal(9, new NearestAttackableTargetGoal<>(this, Slime.class, true));
//        this.targetSelector.addGoal(10, new NearestAttackableTargetGoal<>(this, Shulker.class, true));
//        this.targetSelector.addGoal(11, new NearestAttackableTargetGoal<>(this, Phantom.class, true));
//        this.targetSelector.addGoal(12, new NearestAttackableTargetGoal<>(this, MagmaCube.class, true));
//        this.targetSelector.addGoal(13, new NearestAttackableTargetGoal<>(this, Husk.class, true));
//        this.targetSelector.addGoal(14, new NearestAttackableTargetGoal<>(this, Hoglin.class, true));
//        this.targetSelector.addGoal(15, new NearestAttackableTargetGoal<>(this, Ghast.class, true));
//        this.targetSelector.addGoal(16, new NearestAttackableTargetGoal<>(this, Endermite.class, true));
//        this.targetSelector.addGoal(17, new NearestAttackableTargetGoal<>(this, ElderGuardian.class, true));
//        this.targetSelector.addGoal(18, new NearestAttackableTargetGoal<>(this, Drowned.class, true));
//        this.targetSelector.addGoal(19, new NearestAttackableTargetGoal<>(this, Blaze.class, true));
//        this.targetSelector.addGoal(20, new NearestAttackableTargetGoal<>(this, PiglinBrute.class, true));
//        this.targetSelector.addGoal(20, new NearestAttackableTargetGoal<>(this, Pillager.class, true));

        this.targetSelector.addGoal(3, new NearestAttackablePlayerGoal(this, true));

    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(FAV_STR, "");
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getEntity() instanceof Player) {
            Favorability favorability = this.favorability.get(((Player) pSource.getEntity()).getUUID());
            if (favorability != null && favorability.canFollow()) return false;
        }
        return super.hurt(pSource, pAmount);

    }

    @Nullable
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        RandomSource randomsource = pLevel.getRandom();
        this.populateDefaultEquipmentSlots(randomsource, pDifficulty);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);

        if (this.random.nextInt(0, 100) < 9) {
            ItemStack itemStack = new ItemStack(RegistrationInit.ITEM_IRON_KATANA.get());
            itemStack.setDamageValue(75);
//            itemStack = new ItemStack(ItemKatana(Tiers.IRON, 0, 0, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT).durability(75)));
            this.spawnAtLocation(itemStack);
        }
    }

    protected void populateDefaultEquipmentSlots(RandomSource pRandom, DifficultyInstance pDifficulty) {
        super.populateDefaultEquipmentSlots(pRandom, pDifficulty);
        this.setItemSlot(EquipmentSlot.MAINHAND, new ItemStack(RegistrationInit.ITEM_IRON_KATANA.get()));
    }


    public Favorability updateFavorability(Player pPlayer, LivingEntity pTarget) {
        Favorability favorability = null;
        if (this.favorability.containsKey(pPlayer.getUUID())) {
            favorability = this.favorability.get(pPlayer.getUUID());
            favorability.update(pTarget);
            this.favorability.replace(pPlayer.getUUID(), favorability);
            if (this.haveGift && favorability.canReward()) {
                if (pPlayer.distanceTo(this) >= 3) {
                    this.moveTo((double) pPlayer.getX() + 0.5D, (double) pPlayer.getY(), (double) pPlayer.getZ() + 0.5D, this.getYRot(), this.getXRot());
                    this.navigation.stop();
                }
                BehaviorUtils.throwItem(this, new ItemStack(RegistrationInit.ITEM_FORGED_PLATE.get()), this.position().add(0.0D, 1.0D, 0.0D));
                this.haveGift = false;

            }
            if (favorability.value == favorability.FOLLOW_VALUE) {
                this.playSound(SoundInit.DRIFTERS_ADMIRE.get(), this.getSoundVolume(), this.getVoicePitch());
            }
        } else {
            favorability = new Favorability();
            favorability.update(pTarget);
            this.favorability.put(pPlayer.getUUID(), favorability);
        }
        return favorability;
    }

    @Override
    public boolean canAttackType(EntityType<?> pType) {
//        return pType.is(TagsInit.Entities.DRIFTER_ATTACKABLE);
        return true;
    }

    @Override
    public void tick() {
        super.tick();
//        LivingEntity target = getTarget();
        if (this.level.isClientSide) {
            this.favorability = favStrToMap(this.entityData.get(FAV_STR));
        } else {
            this.entityData.set(FAV_STR, MapToFavStr(this.favorability));
        }

    }

    @Override
    public boolean canAttack(LivingEntity pTarget) {
        boolean flag = true;
        if (pTarget instanceof Player) {
            Favorability favorability = this.favorability.get(pTarget.getUUID());
            if (favorability != null) flag = favorability.canAttack();
        }
        return flag && super.canAttack(pTarget);
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundInit.DRIFTERS_AMBIENT.get();
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.DRIFTERS_DEATH.get();
    }

    @Override
    public boolean doHurtTarget(Entity pEntity) {
        boolean b = super.doHurtTarget(pEntity);
        if (pEntity instanceof LivingEntity) {
            if (((LivingEntity) pEntity).getHealth() <= 0 && random.nextInt(0, 4) == 1) {
                playSound(SoundInit.DRIFTERS_VICTORY.get(), this.getSoundVolume(), this.getVoicePitch());
            }
        }
        return b;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundInit.DRIFTERS_HURT.get();
    }

    public boolean isRenderItem() {
        return renderItem;
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.level.isClientSide) {
            List<LivingEntity> entitiesOfClass = this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(10, 4.0D, 10), (x) -> {
                return true;
            });
            float minDisToEnemy = Float.MAX_VALUE;
            for (LivingEntity target : entitiesOfClass) {
                if (target.isAlive() && target instanceof Enemy && !(target instanceof Creeper) && !(target instanceof EntityDrifter)) {
                    if (target instanceof Player) {
                        Favorability favorability = this.favorability.get(((Player) target).getUUID());
                        if (favorability != null && (!favorability.canAttack())) continue;
                    }
                    minDisToEnemy = Math.min(target.distanceTo(this), minDisToEnemy);
                }
            }
//            System.out.println(this.fightAnimationState.isStarted());
            if (!this.fightAnimationState.isStarted()) {
                    if (minDisToEnemy < 10) {
                        alertAnimationState.start(this.tickCount);
                    } else {
                        alertAnimationState.stop();
                    }
            }
            if(this.fightAnimationState.isStarted()|| alertAnimationState.isStarted()){
                renderItem = true;
            }else{
                renderItem =false;
            }

//            if (this.isAggressive()) {
////                renderItem = true;
////                if (!fightAnimationState.isStarted()) {
//                renderItem = true;
////                    fightAnimationState.playOnce(this.tickCount, UtilLevel.TIME.SECOND.getTick()*2);
//                fightAnimationState.start(this.tickCount);
////                }
//            } else {
//                fightAnimationState.stop();
//                renderItem = false;
//                if (minDisToEnemy < 10) {
//                    alertAnimationState.start(this.tickCount);
//                    renderItem = true;
//                } else {
//                    alertAnimationState.stop();
//                    renderItem = false;
//                }
//            }
//            alert0AnimationState.stop();
//            alert1AnimationState.stop();
//            alertAnimationState.stop();
//            if (!this.fightAnimationState.isStarted()) {
////                if (minDisToEnemy < 4) {
////                    if (this.isAggressive()) {
////                        fightAnimationState.playOnce(this.tickCount, 500);
////                    } else {
////                        alert1AnimationState.start(this.tickCount);
////                    }
////                    renderItem = true;
////                } else
//                    if (minDisToEnemy < 10) {
//                        alertAnimationState.start(this.tickCount);
//                    renderItem = true;
//                } else {
//                        alertAnimationState.stop();
//                    renderItem = false;
//                }
//            }
        }

        Player nearestPlayer = this.level.getNearestPlayer(this, 64F);
        if (nearestPlayer != null && !favorability.containsKey(nearestPlayer.getUUID())) {
            favorability.put(nearestPlayer.getUUID(), new Favorability());
        }
    }

    public static class Favorability {
        private float value;
        private final float MAX_VALUE = 5F;
        private final float MIN_VALUE = -1F;
        private final float FOLLOW_VALUE = 3F;

        public Favorability() {
            this.value = 0;
        }

        public Favorability(float value) {
            this.value = value;
        }

        private void add(float add) {
            this.value = Math.min(this.value + add, MAX_VALUE);
        }

        private boolean canFollow() {
//            return true;
//            System.out.println(value);
            return value >= FOLLOW_VALUE;
        }

        public float getValue() {
            return value;
        }

        private boolean canAttack() {
            return value == MIN_VALUE;
        }

        private boolean canReward() {
            return value >= MAX_VALUE;
        }

        private void sub(float sub) {
            this.value = Math.max(this.value - sub, MIN_VALUE);
        }

        public void update(LivingEntity pTarget) {
            if (pTarget.getType().is(TagsInit.Entities.DRIFTER_FAVORABILITY_ADD_QUARTER)) {
                add(0.25F);
            } else if (pTarget.getType().is(TagsInit.Entities.DRIFTER_FAVORABILITY_ADD_ONE)) {
                add(1F);
            } else if (pTarget instanceof AbstractIllager) {
                add(0.5F);
            } else if (pTarget instanceof Villager) {
                if (((Villager) pTarget).isBaby()) sub(2F);
                else sub(1F);
            } else if (pTarget instanceof EntityDrifter) {
                if (value > 3) sub(0.5F);
                else sub(1F);
            }
        }

    }

    private class NearestAttackablePlayerGoal extends NearestAttackableTargetGoal {
        private EntityDrifter entityDrifter;

        public NearestAttackablePlayerGoal(EntityDrifter entityDrifter, boolean pMustSee) {
            super(entityDrifter, Player.class, pMustSee);
            this.entityDrifter = entityDrifter;
        }

        @Override
        public boolean canUse() {
//            if(entityDrifter.getTarget() instanceof Player){
//                Favorability favorability = entityDrifter.favorability.get(entityDrifter.getTarget());
//                if(favorability!=null&&!favorability.canAttack())entityDrifter.setTarget(null);
//            }
            return super.canUse();
        }

        @Override
        protected void findTarget() {
            this.target = this.mob.level.getNearestPlayer(this.targetConditions, this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
            if (this.target != null) {
                Favorability favorability = entityDrifter.favorability.get(this.target.getUUID());
                if (favorability == null || !favorability.canAttack()) {
                    this.target = null;
                }

            }

        }
    }

    private class FllowPlayerGoal extends Goal {
        private Player followPlayer;
        private int timeToRecalcPath;
        private final PathNavigation navigation;

        private EntityDrifter entityDrifter;

        public FllowPlayerGoal(EntityDrifter entityDrifter) {
            this.entityDrifter = entityDrifter;
            this.navigation = entityDrifter.getNavigation();

        }

        @Override
        public boolean canUse() {
            for (var player : entityDrifter.favorability.keySet()) {
                if (entityDrifter.favorability.get(player).canFollow()) {
                    this.followPlayer = getLevel().getPlayerByUUID(player);
                    return true;
                }
            }
            return false;
        }

        @Override
        public boolean canContinueToUse() {
            if (this.navigation.isDone()) {
                return false;
            } else {
                return super.canContinueToUse() && followPlayer != null && entityDrifter.favorability.get(followPlayer.getUUID()).canFollow();
            }
        }

        @Override
        public void stop() {
            super.stop();
            this.navigation.stop();
        }

        @Override
        public void start() {
            super.start();
            this.timeToRecalcPath = 0;

        }

        private int randomIntInclusive(int pMin, int pMax) {
            return this.entityDrifter.getRandom().nextInt(pMax - pMin + 1) + pMin;
        }

        private boolean canTeleportTo(BlockPos pPos) {
            BlockPathTypes blockpathtypes = WalkNodeEvaluator.getBlockPathTypeStatic(this.entityDrifter.level, pPos.mutable());
            if (blockpathtypes != BlockPathTypes.WALKABLE) {
                return false;
            } else {
                BlockState blockstate = this.entityDrifter.level.getBlockState(pPos.below());
                if (blockstate.getBlock() instanceof LeavesBlock) {
                    return false;
                } else {
                    BlockPos blockpos = pPos.subtract(this.entityDrifter.blockPosition());
                    return this.entityDrifter.level.noCollision(this.entityDrifter, this.entityDrifter.getBoundingBox().move(blockpos));
                }
            }
        }

        private boolean maybeTeleportTo(int pX, int pY, int pZ) {
            if (Math.abs((double) pX - this.entityDrifter.getX()) < 2.0D && Math.abs((double) pZ - this.entityDrifter.getZ()) < 2.0D) {
                return false;
            } else if (!this.canTeleportTo(new BlockPos(pX, pY, pZ))) {
                return false;
            } else {
                this.entityDrifter.moveTo((double) pX + 0.5D, (double) pY, (double) pZ + 0.5D, this.entityDrifter.getYRot(), this.entityDrifter.getXRot());
                this.navigation.stop();
                return true;
            }
        }

        private void teleportToOwner() {
            BlockPos blockpos = this.followPlayer.blockPosition();

            for (int i = 0; i < 10; ++i) {
                int j = this.randomIntInclusive(-3, 3);
                int k = this.randomIntInclusive(-1, 1);
                int l = this.randomIntInclusive(-3, 3);
                boolean flag = this.maybeTeleportTo(blockpos.getX() + j, blockpos.getY() + k, blockpos.getZ() + l);
                if (flag) {
                    return;
                }
            }

        }

        @Override
        public void tick() {
            super.tick();
            if (this.followPlayer != null) {
                this.entityDrifter.getLookControl().setLookAt(this.followPlayer, 10.0F, (float) this.entityDrifter.getMaxHeadXRot());
                if (--this.timeToRecalcPath <= 0) {
                    this.timeToRecalcPath = this.adjustedTickDelay(10);
                    if (!this.entityDrifter.isLeashed() && !this.entityDrifter.isPassenger()) {
                        if (this.entityDrifter.distanceToSqr(this.followPlayer) >= 144.0D) {
                            this.teleportToOwner();
                        } else {
                            this.navigation.moveTo(this.followPlayer, 0.8F);
                            this.navigation.moveTo(this.followPlayer, 0.8F);
                        }

                    }
                }
            }
        }
    }
    class DrifterMeleeAttackGoal extends Goal {
        protected final EntityDrifter mob;
        private final double speedModifier;
        private final boolean followingTargetEvenIfNotSeen;
        private Path path;
        private double pathedTargetX;
        private double pathedTargetY;
        private double pathedTargetZ;
        private int ticksUntilNextPathRecalculation;
        private int ticksUntilNextAttack;
        private final int attackInterval = UtilLevel.TIME.SECOND.getTick()*2;
        private long lastCanUseCheck;
        private static final long COOLDOWN_BETWEEN_CAN_USE_CHECKS = 20L;
        private int failedPathFindingPenalty = 0;
        private boolean canPenalize = false;

        public DrifterMeleeAttackGoal(EntityDrifter pMob, double pSpeedModifier, boolean pFollowingTargetEvenIfNotSeen) {
            this.mob = pMob;
            this.speedModifier = pSpeedModifier;
            this.followingTargetEvenIfNotSeen = pFollowingTargetEvenIfNotSeen;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
        }



        /**
         * Returns whether execution should begin. You can also read and cache any state necessary for execution in this
         * method as well.
         */
        public boolean canUse() {
            long i = this.mob.level.getGameTime();
            if (i - this.lastCanUseCheck < 20L) {
                return false;
            } else {
                this.lastCanUseCheck = i;
                LivingEntity livingentity = this.mob.getTarget();
                if (livingentity == null) {
                    return false;
                } else if (!livingentity.isAlive()) {
                    return false;
                } else {
                    if (canPenalize) {
                        if (--this.ticksUntilNextPathRecalculation <= 0) {
                            this.path = this.mob.getNavigation().createPath(livingentity, 0);
                            this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                            return this.path != null;
                        } else {
                            return true;
                        }
                    }
                    this.path = this.mob.getNavigation().createPath(livingentity, 0);
                    if (this.path != null) {
                        return true;
                    } else {
                        return this.getAttackReachSqr(livingentity) >= this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                    }
                }
            }
        }

        /**
         * Returns whether an in-progress EntityAIBase should continue executing
         */
        public boolean canContinueToUse() {
            LivingEntity livingentity = this.mob.getTarget();
            if (livingentity == null) {
                return false;
            } else if (!livingentity.isAlive()) {
                return false;
            } else if (!this.followingTargetEvenIfNotSeen) {
                return !this.mob.getNavigation().isDone();
            } else if (!this.mob.isWithinRestriction(livingentity.blockPosition())) {
                return false;
            } else {
                return !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player)livingentity).isCreative();
            }
        }

        /**
         * Execute a one shot task or start executing a continuous task
         */
        public void start() {
            this.mob.getNavigation().moveTo(this.path, this.speedModifier);
            this.mob.setAggressive(true);
            this.ticksUntilNextPathRecalculation = 0;
            this.ticksUntilNextAttack = 0;
        }

        /**
         * Reset the task's internal state. Called when this task is interrupted by another one
         */
        public void stop() {
            LivingEntity livingentity = this.mob.getTarget();
            if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
                this.mob.setTarget((LivingEntity)null);
            }

            this.mob.setAggressive(false);
            this.mob.getNavigation().stop();
        }

        public boolean requiresUpdateEveryTick() {
            return true;
        }

        /**
         * Keep ticking a continuous task that has already been started
         */
        public void tick() {
            LivingEntity livingentity = this.mob.getTarget();
            if (livingentity != null) {
                this.mob.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
                double d0 = this.mob.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
                this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
                if ((this.followingTargetEvenIfNotSeen || this.mob.getSensing().hasLineOfSight(livingentity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.mob.getRandom().nextFloat() < 0.05F)) {
                    this.pathedTargetX = livingentity.getX();
                    this.pathedTargetY = livingentity.getY();
                    this.pathedTargetZ = livingentity.getZ();
                    this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                    if (this.canPenalize) {
                        this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
                        if (this.mob.getNavigation().getPath() != null) {
                            net.minecraft.world.level.pathfinder.Node finalPathPoint = this.mob.getNavigation().getPath().getEndNode();
                            if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                                failedPathFindingPenalty = 0;
                            else
                                failedPathFindingPenalty += 10;
                        } else {
                            failedPathFindingPenalty += 10;
                        }
                    }
                    if (d0 > 1024.0D) {
                        this.ticksUntilNextPathRecalculation += 10;
                    } else if (d0 > 256.0D) {
                        this.ticksUntilNextPathRecalculation += 5;
                    }

                    if (!this.mob.getNavigation().moveTo(livingentity, this.speedModifier)) {
                        this.ticksUntilNextPathRecalculation += 15;
                    }

                    this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
                }

                this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
                this.checkAndPerformAttack(livingentity, d0);
            }
        }

        protected void checkAndPerformAttack(LivingEntity pEnemy, double pDistToEnemySqr) {
            double d0 = this.getAttackReachSqr(pEnemy);
            if (pDistToEnemySqr <= d0 && this.ticksUntilNextAttack <= 0) {
                this.resetAttackCooldown();
//                this.mob.swing(InteractionHand.MAIN_HAND);
//                 this.mob.fightAnimationState.fightAnimationState.playOnce(this.mob.tickCount,this.attackInterval);
                this.mob.alertAnimationState.stop();
                AnimationState fightAnimationState = this.mob.fightAnimationState;
                fightAnimationState .start(this.mob.tickCount);
                Thread thread=new Thread(){
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(UtilLevel.TIME.SECOND.getTick()* 2L);
                            fightAnimationState.stop();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
                this.mob.doHurtTarget(pEnemy);
            }

        }

        protected void resetAttackCooldown() {
            this.ticksUntilNextAttack = this.adjustedTickDelay(20);
        }

        protected boolean isTimeToAttack() {
            return this.ticksUntilNextAttack <= 0;
        }

        protected int getTicksUntilNextAttack() {
            return this.ticksUntilNextAttack;
        }

        protected int getAttackInterval() {
            return this.adjustedTickDelay(20);
        }

        protected double getAttackReachSqr(LivingEntity pAttackTarget) {
            return (double)(this.mob.getBbWidth() * 2.0F * this.mob.getBbWidth() * 2.0F + pAttackTarget.getBbWidth());
        }
    }

}

