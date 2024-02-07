package com.mnsfhxy.johnny_s_biological_notes.entity.beluga;

import com.mnsfhxy.johnny_s_biological_notes.config.Config;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.goal.*;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.TryFindWaterGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TridentItem;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import static net.minecraft.world.item.Items.SALMON;

/**
 * 白鲸的实体类
 */
public class EntityBeluga extends Animal {
    public static final int INVALID_LEADER_ID = 0;
    // 死亡后掉落的经验球数量
    public static final int EXPERIENCE_REWARD_NUMBER = 3;
    public static final float BABY_SPAWN_CHANCE = 0.1F;
    public final AnimationState swimmingState = new AnimationState();
    public final AnimationState touchedState = new AnimationState();
    public final AnimationState attackingState = new AnimationState();
    protected static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(EntityBeluga.class, EntityDataSerializers.INT);
    //状态变量，确认白鲸当前是否停留在水面
    protected static final EntityDataAccessor<Boolean> STAYED_ON_SURFACE_FLAG = SynchedEntityData.defineId(EntityBeluga.class, EntityDataSerializers.BOOLEAN);
    //白鲸的嬉戏加速状态
    private static final EntityDataAccessor<Boolean> FROLIC_SPEED_FLAG = SynchedEntityData.defineId(EntityBeluga.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> STANDING_FLAG = SynchedEntityData.defineId(EntityBeluga.class, EntityDataSerializers.BOOLEAN);
    private int frolicLeaderId = INVALID_LEADER_ID;
    private boolean dataInitialized = false;

    /**
     * 白鲸构造方法
     *
     * @param pEntityType
     * @param pLevel
     */
    public EntityBeluga(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, inWaterSpeedModifier(), outsideWaterSpeedModifier(), true);
        this.dataInitialized = true;
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, Config.getInstance().doubleValueOf("entity.beluga.attributes.movementSpeed"))
                .add(Attributes.MAX_HEALTH, Config.getInstance().doubleValueOf("entity.beluga.attributes.maxHealth"))
                .add(Attributes.ATTACK_DAMAGE, Config.getInstance().doubleValueOf("entity.beluga.attributes.attackDamage"));
    }

    /**
     * 调整了调用父类的顺序，通常是当前方法处理完成再调用父类的方法
     * 但是充值Attribute需要父类先根据概率生成，之后才能确定是否为幼崽
     * 在此基础上进行属性重置，因此在中间就调用父类的方法了
     * @param pLevel
     * @param pDifficulty
     * @param pReason
     * @param pSpawnData
     * @param pDataTag
     * @return
     */
    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        initAirSupply();

        AgeableMobGroupData customSpawnData = new AgeableMobGroupData(BABY_SPAWN_CHANCE);
        SpawnGroupData spawnGroupData = super.finalizeSpawn(pLevel, pDifficulty, pReason, customSpawnData, pDataTag);

        resetAttributesOnIsBaby();

        return spawnGroupData;
    }

    /**
     * 根据是否为幼崽，进行属性重置
     */
    private void resetAttributesOnIsBaby() {
        this.getAttribute(Attributes.MOVEMENT_SPEED).setBaseValue(movementSpeed());
        this.getAttribute(Attributes.MAX_HEALTH).setBaseValue(maxHealth());
        this.getAttribute(Attributes.ATTACK_DAMAGE).setBaseValue(attackDamage());
    }

    private void initAirSupply() {
        this.setAirSupply(this.getMaxAirSupply());
        this.setXRot(0.0F);
    }

    /**
     * 原来继承WaterAnimal，目前改为继承Animal了（为了繁殖）
     * 因此将WaterAnimal的一些方法加入到当前类中
     */

    public MobType getMobType() {
        return MobType.WATER;
    }

    public boolean checkSpawnObstruction(LevelReader pLevel) {
        return pLevel.isUnobstructed(this);
    }

    /**
     * Get number of ticks, at least during which the living entity will be silent.
     */
    public int getAmbientSoundInterval() {
        return 120;
    }

    public boolean isPushedByFluid() {
        return false;
    }

    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }

    public static boolean checkSurfaceWaterAnimalSpawnRules(EntityType<? extends EntityBeluga> pEntityBeluga, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        int i = pLevel.getSeaLevel();
        int j = i - 13;
        return pPos.getY() >= j && pPos.getY() <= i && pLevel.getFluidState(pPos.below()).is(FluidTags.WATER) && pLevel.getBlockState(pPos.above()).is(Blocks.WATER);
    }

    /**
     * 以上为WaterAnimal的方法
     */


    /**
     * 繁殖相关的内容，实现Animal相关方法
     */

    /**
     * 返回幼崽的实体
     * @param pLevel
     * @param pOtherParent
     * @return
     */
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return RegistrationInit.ENTITY_TYPE_BELUGA.create(pLevel);
    }

    /**
     * 食物
     * @param pStack
     * @return
     */
    @Override
    public boolean isFood(ItemStack pStack) {
        return pStack.is(SALMON);
    }

    /**
     * 喂食时，插入播放音效逻辑
     * @param pPlayer
     * @param pHand
     * @param pStack
     */
    @Override
    protected void usePlayerItem(Player pPlayer, InteractionHand pHand, ItemStack pStack) {
        this.playSound(SoundInit.BELUGA_EAT.get(), 1.0F, 1.0F);
        super.usePlayerItem(pPlayer, pHand, pStack);
    }

    /**
     * 以上为覆盖/实现Animal相关内容
     * @return
     */

    /**
     * 修改默认的交互行为，抚摸时播放动画
     *
     * @param pPlayer
     * @param pHand
     * @return
     */
    @Override
    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        Item item = itemstack.getItem();

        if (item == Items.AIR) {
            this.playSound(SoundInit.BELUGA_HAPPY.get(), 1.0F, .10F);
            touchedState.startIfStopped(this.tickCount);
            return InteractionResult.SUCCESS;
        }

        return super.mobInteract(pPlayer, pHand);
    }

    @Override
    public int getExperienceReward() {
        return EXPERIENCE_REWARD_NUMBER;
    }

    /**
     * 注册AI逻辑
     */
    @Override
    protected void registerGoals() {
        super.registerGoals();
        if (!this.isBaby())
            this.goalSelector.addGoal(0, new BelugaMeleeAttackGoal(this, (double) 1.2F, true));

        this.goalSelector.addGoal(1, new BelugaStayOnSurfaceGoal(this, onSurfaceStayTime(), onSurfaceParticleInterval()));
        this.goalSelector.addGoal(2, new BelugaBreathAirGoal(this, breathMaxAirSupply(), breathInterval()));
        this.goalSelector.addGoal(2, new TryFindWaterGoal(this));

        if (!this.isBaby())
            this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
        if (!this.isBaby())
            this.goalSelector.addGoal(5, new BelugaFollowBoatGoal(this));

        this.goalSelector.addGoal(6, new BelugaSongPlayGoal(this, songPlayScope(), songPlayParticleInterval(), songPlayParticleNumber()));
        this.goalSelector.addGoal(8, new BelugaGroupSwimGoal(this, groupSwimDetectScope(), groupSwimMaxDistance()));
        this.goalSelector.addGoal(9, new BelugaFrolicGoal(this));
        this.goalSelector.addGoal(10, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    @Override
    public void tick() {
        super.tick();

        if (isMoving())
            this.swimmingState.startIfStopped(this.tickCount);

        if (this.isNoAi()) {
            this.setAirSupply(this.getMaxAirSupply());
        } else {
            if (this.isInWaterRainOrBubble()) {
                this.setMoisntessLevel(moistnessMax());
            } else {
                this.setMoisntessLevel(this.getMoistnessLevel() - 1);
                if (this.getMoistnessLevel() <= 0) {
                    this.hurt(DamageSource.DRY_OUT, moistnessDryHurt());
                }

                if (this.onGround) {
                    this.setDeltaMovement(this.getDeltaMovement().add((double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F), 0.5D, (double) ((this.random.nextFloat() * 2.0F - 1.0F) * 0.2F)));
                    this.setYRot(this.random.nextFloat() * 360.0F);
                    this.onGround = false;
                    this.hasImpulse = true;
                }
            }
        }

        // 如果是站立状态，播放攻击动画
        if (this.isStanding()) {
            if (swimmingState.isStarted())
                swimmingState.stop();

            if (isAttackingAnimationFinished())
                attackingState.stop();

            this.attackingState.startIfStopped(this.tickCount);
        }
    }

    private boolean isAttackingAnimationFinished() {
        return this.attackingState.getAccumulatedTime() /
                (AnimationBeluga.ATTACKING.lengthInSeconds() * 1000)
                >= 1.0F;
    }

    /**
     * 增加被攻击时受穿刺附魔影响
     * @param source
     * @param amount
     * @return
     */
    @Override
    public boolean hurt(DamageSource source, float amount) {
        if (source.getEntity() instanceof Player) {
            Player player = (Player) source.getEntity();
            if (player.getMainHandItem().getItem() instanceof TridentItem) {
                int impalingLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.IMPALING, player);
                if (impalingLevel > 0 && this.isInWater()) {
                    amount += impalingLevel * 2.5F;
                }
            }
        }

        return super.hurt(source, amount);
    }

    /**
     * 白鲸的攻击动作，播放攻击音效
     * @param pEntity
     * @return
     */
    @Override
    public boolean doHurtTarget(Entity pEntity) {
        boolean flag = pEntity.hurt(DamageSource.mobAttack(this), (float)((int)this.getAttributeValue(Attributes.ATTACK_DAMAGE)));
        if (flag) {
            this.doEnchantDamageEffects(this, pEntity);
            this.playSound(SoundInit.BELUGA_ATTACK.get(), 1.0F, 1.0F);
        }

        return flag;
    }

    /**
     * 以下部分的代码为基础的运动以及呼吸所必须的方法
     */

    /**
     * 关键方法，设置导航为水中路径导航
     * 有了这个方法，呼吸空气的AI才能正常运作，向上游寻找空气
     *
     * @param pLevel
     * @return
     */
    protected PathNavigation createNavigation(Level pLevel) {
        return new WaterBoundPathNavigation(this, pLevel);
    }

    /**
     * 处理白鲸移动的逻辑
     * 增加之后白鲸移动明显更灵活和快速
     * 有了这个方法白鲸才能在空气耗尽前找到空气
     *
     * @param pTravelVector
     */
    public void travel(Vec3 pTravelVector) {
        if (this.isEffectiveAi() && this.isInWater()) {
            this.moveRelative(this.getSpeed(), pTravelVector);
            this.move(MoverType.SELF, this.getDeltaMovement());
            this.setDeltaMovement(this.getDeltaMovement().scale(0.9D));
            if (this.getTarget() == null) {
                this.setDeltaMovement(this.getDeltaMovement().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(pTravelVector);
        }
    }

    /**
     * 设置白鲸不能再水中呼吸，这样在水中时才会每刻减少空气供应
     *
     * @return
     */
    @Override
    public boolean canBreatheUnderwater() {
        return false;
    }

    /**
     * 最大空气供应
     *
     * @return
     */
    public int getMaxAirSupply() {
        return breathMaxAirSupply();
    }

    /**
     * 补充空气，此时直接回复至最大空气供应
     *
     * @param pCurrentAir
     * @return
     */
    protected int increaseAirSupply(int pCurrentAir) {
        return this.getMaxAirSupply();
    }

    /**
     * 以上部分的代码为基础的运动以及呼吸所必须的方法
     */

    /**
     * 判断当前实体是否在移动
     *
     * @return
     */
    private boolean isMoving() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-7D;
    }

    /**
     * 以下部分为白鲸的音效注册
     */

    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundInit.BELUGA_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.BELUGA_DEATH.get();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return this.isInWater() ? SoundInit.BELUGA_AMBIENT_WATER.get() : SoundInit.BELUGA_AMBIENT.get();
    }

    @Override
    protected SoundEvent getSwimSplashSound() {
        return SoundInit.BELUGA_SPLASH.get();
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundInit.BELUGA_SWIM.get();
    }

    /**
     * 以上部分为白鲸的音效注册
     */

    /**
     * 以下部分为定义同步数据及访问同步数据的方法
     */

    /**
     * 定义同步数据
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(FROLIC_SPEED_FLAG, false);
        this.entityData.define(STANDING_FLAG, false);
        this.entityData.define(STAYED_ON_SURFACE_FLAG, true);
        this.entityData.define(MOISTNESS_LEVEL, moistnessMax());
    }

    /**
     * 游戏保存时保存自定义的求偶状态值
     * @param pCompound
     */
    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);

        pCompound.putBoolean("FrolicSpeedFlag", this.isFrolicSpeed());
        pCompound.putBoolean("StandingFlag", this.isStanding());
        pCompound.putBoolean("StayedOnSurfaceFlag", this.hasStayedOnSurface());
        pCompound.putInt("Moistness", this.getMoistnessLevel());
    }

    /**
     * 游戏启动时加载保存的值
     * @param pCompound
     */
    @Override
    public void readAdditionalSaveData(CompoundTag pCompound) {
        super.readAdditionalSaveData(pCompound);

        this.setFrolicSpeedFlag(pCompound.getBoolean("FrolicSpeedFlag"));
        this.setStanding(pCompound.getBoolean("StandingFlag"));
        this.setStayedOnSurface(pCompound.getBoolean("StayedOnSurfaceFlag"));
        this.setMoisntessLevel(pCompound.getInt("Moistness"));
    }

    public void setFrolicSpeedFlag(boolean flag) {
        this.entityData.set(FROLIC_SPEED_FLAG, flag);
    }

    public boolean isFrolicSpeed() {
        return this.entityData.get(FROLIC_SPEED_FLAG);
    }

    public void setStanding(boolean flag) {
        this.entityData.set(STANDING_FLAG, flag);
    }

    public boolean isStanding() {
        return this.entityData.get(STANDING_FLAG);
    }

    public void startBreathAir() {
        this.setStayedOnSurface(false);
    }

    public void startStayOnSurface() {
        this.setStayedOnSurface(true);
    }

    public void setStayedOnSurface(boolean flag) {
        this.entityData.set(STAYED_ON_SURFACE_FLAG, flag);
    }

    public boolean hasStayedOnSurface() {
        return this.entityData.get(STAYED_ON_SURFACE_FLAG);
    }

    public int getMoistnessLevel() {
        return this.entityData.get(MOISTNESS_LEVEL);
    }

    public void setMoisntessLevel(int pMoistnessLevel) {
        this.entityData.set(MOISTNESS_LEVEL, pMoistnessLevel);
    }

    /**
     * 以上部分为定义同步数据及访问同步数据的方法
     */


    /**
     * 以下部分为读取配置的值，不同实例采用不同配置创建相关的
     * Goal等
     * @return
     */
    private float inWaterSpeedModifier() {
        return this.isBaby()
                ? Config.getInstance().floatValueOf("entity.youngBeluga.swimming.inWaterSpeedModifier")
                : Config.getInstance().floatValueOf("entity.beluga.swimming.inWaterSpeedModifier");
    }

    private float outsideWaterSpeedModifier() {
        return this.isBaby()
                ? Config.getInstance().floatValueOf("entity.youngBeluga.swimming.outsideWaterSpeedModifier")
                : Config.getInstance().floatValueOf("entity.beluga.swimming.outsideWaterSpeedModifier");
    }

    private double movementSpeed() {
        return this.isBaby()
                ? Config.getInstance().doubleValueOf("entity.youngBeluga.attributes.movementSpeed")
                : Config.getInstance().doubleValueOf("entity.beluga.attributes.movementSpeed");
    }

    private double maxHealth() {
        return this.isBaby()
                ? Config.getInstance().doubleValueOf("entity.youngBeluga.attributes.maxHealth")
                : Config.getInstance().doubleValueOf("entity.beluga.attributes.maxHealth");
    }

    private double attackDamage() {
        return this.isBaby() ? 0D : Config.getInstance().doubleValueOf("entity.beluga.attributes.attackDamage");
    }

    /**
     * 这个属性在父类构造函数执行期间就会访问，此时age属性还未初始化
     * 因此增加条件，在初始化后才访问isBaby方法
     * @return
     */
    private int breathMaxAirSupply() {
        return dataInitialized && this.isBaby()
                ? Config.getInstance().intValueOf("entity.youngBeluga.breath.maxAirSupply")
                : Config.getInstance().intValueOf("entity.beluga.breath.maxAirSupply");
    }

    private int breathInterval() {
        return this.isBaby()
                ? Config.getInstance().intValueOf("entity.youngBeluga.breath.interval")
                : Config.getInstance().intValueOf("entity.beluga.breath.interval");
    }

    private int onSurfaceStayTime() {
        return this.isBaby()
                ? Config.getInstance().intValueOf("entity.youngBeluga.onSurface.stayTime")
                : Config.getInstance().intValueOf("entity.beluga.onSurface.stayTime");
    }

    private int onSurfaceParticleInterval() {
        return this.isBaby()
                ? Config.getInstance()
                .intValueOf("entity.youngBeluga.onSurface.particle.interval")
                : Config.getInstance()
                .intValueOf("entity.beluga.onSurface.particle.interval");
    }

    private int songPlayScope() {
        return this.isBaby()
                ? Config.getInstance().intValueOf("entity.youngBeluga.songPlay.scope")
                : Config.getInstance().intValueOf("entity.beluga.songPlay.scope");
    }

    private int songPlayParticleInterval() {
        return this.isBaby()
                ? Config.getInstance()
                .intValueOf("entity.youngBeluga.songPlay.particle.interval")
                : Config.getInstance()
                .intValueOf("entity.beluga.songPlay.particle.interval");
    }

    private int songPlayParticleNumber() {
        return this.isBaby()
                ? Config.getInstance().intValueOf("entity.youngBeluga.songPlay.particle.number")
                : Config.getInstance().intValueOf("entity.beluga.songPlay.particle.number");
    }

    private int groupSwimDetectScope() {
        return this.isBaby()
                ? Config.getInstance().intValueOf("entity.youngBeluga.groupSwim.detectScope")
                : Config.getInstance().intValueOf("entity.beluga.groupSwim.detectScope");
    }

    private int groupSwimMaxDistance() {
        return this.isBaby()
                ? Config.getInstance().intValueOf("entity.youngBeluga.groupSwim.maxDistance")
                : Config.getInstance().intValueOf("entity.beluga.groupSwim.maxDistance");
    }

    private int moistnessMax() {
        return this.isBaby()
                ? Config.getInstance().intValueOf("entity.youngBeluga.moistness.max")
                : Config.getInstance().intValueOf("entity.beluga.moistness.max");
    }

    private float moistnessDryHurt() {
        return this.isBaby()
                ? Config.getInstance().floatValueOf("entity.youngBeluga.moistness.dryHurt")
                : Config.getInstance().floatValueOf("entity.beluga.moistness.dryHurt");
    }

    /**
     * 以上部分为读取配置的值，不同实例采用不同配置创建相关的
     * Goal等
     * @return
     */

    public void setFrolicLeaderId(int leaderId) {
        this.frolicLeaderId = leaderId;
    }

    public int getFrolicLeaderId() {
        return this.frolicLeaderId;
    }

    public boolean hasLeader() {
        return getFrolicLeaderId() != INVALID_LEADER_ID;
    }
}
