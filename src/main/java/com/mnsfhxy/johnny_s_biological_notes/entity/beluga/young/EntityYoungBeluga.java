package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.young;

import com.mnsfhxy.johnny_s_biological_notes.config.Config;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.EntityBeluga;
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

import javax.annotation.Nullable;

import static net.minecraft.world.item.Items.SALMON;

/**
 * 幼年白鲸
 */
public class EntityYoungBeluga extends Animal {
    public static final int INVALID_LEADER_ID = 0;
    public final AnimationState swimmingState = new AnimationState();
    public final AnimationState touchedState = new AnimationState();
    protected static final EntityDataAccessor<Integer> MOISTNESS_LEVEL = SynchedEntityData.defineId(EntityYoungBeluga.class, EntityDataSerializers.INT);
    //状态变量，确认白鲸当前是否停留在水面
    protected static final EntityDataAccessor<Boolean> STAYED_ON_SURFACE_FLAG = SynchedEntityData.defineId(EntityYoungBeluga.class, EntityDataSerializers.BOOLEAN);
    private int frolicLeaderId = INVALID_LEADER_ID;

    public EntityYoungBeluga(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.lookControl = new SmoothSwimmingLookControl(this, 10);
        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, Config.getInstance().floatValueOf("entity.youngBeluga.swimming.inWaterSpeedModifier"), Config.getInstance().floatValueOf("entity.youngBeluga.swimming.outsideWaterSpeedModifier"), true);
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, Config.getInstance().doubleValueOf("entity.youngBeluga.attributes.movementSpeed"))
                .add(Attributes.MAX_HEALTH, Config.getInstance().doubleValueOf("entity.youngBeluga.attributes.maxHealth"));
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

    public int getExperienceReward() {
        return 1 + this.level.random.nextInt(3);
    }

    public boolean isPushedByFluid() {
        return false;
    }

    public boolean canBeLeashed(Player pPlayer) {
        return false;
    }

    public static boolean checkSurfaceWaterAnimalSpawnRules(EntityType<? extends EntityYoungBeluga> pEntityYoungBeluga, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        int i = pLevel.getSeaLevel();
        int j = i - 13;
        return pPos.getY() >= j && pPos.getY() <= i && pLevel.getFluidState(pPos.below()).is(FluidTags.WATER) && pLevel.getBlockState(pPos.above()).is(Blocks.WATER);
    }

    /**
     * 以上为WaterAnimal的方法
     */

    /**
     * 继承自Animal后，有些方法需要实现或覆盖
     */

    /**
     * 返回幼年对象，当前类为幼年类，直接返回空
     * 在成年白鲸类中实现
     * @param pLevel
     * @param pOtherParent
     * @return
     */
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    @Override
    public boolean isBaby() {
        return true;
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

    @Override
    public void ageUp(int pAmount, boolean pForced) {
        if (this.isBaby()) { // 当生物长大时
            super.ageUp(1160, pForced);
        } else {
            super.ageUp(pAmount, pForced);
        }
    }

    @Override
    protected void ageBoundaryReached() {
        if (!this.level.isClientSide && this.isBaby() && this.getAge() >= 0) { // 当生物长大时
            EntityBeluga adult = RegistrationInit.ENTITY_TYPE_BELUGA.create(this.level);
            adult.setPos(this.getX(), this.getY(), this.getZ()); // 将位置、速度等信息从当前实体复制到成年实体
            adult.setHealth(this.getHealth()); // 设置最大生命值

            this.level.addFreshEntity(adult); // 将新的成年实体添加到世界中
            this.remove(RemovalReason.DISCARDED); // 移除当前的幼年实体
        }
    }

    /**
     * 以上为Animal的方法处理
     */


    /**
     * 生成幼年白鲸时调用的方法，初始化白鲸的空气供应信息
     *
     * @param pLevel
     * @param pDifficulty
     * @param pReason
     * @param pSpawnData
     * @param pDataTag
     * @return
     */
    @Nullable
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor pLevel, DifficultyInstance pDifficulty, MobSpawnType pReason, @Nullable SpawnGroupData pSpawnData, @Nullable CompoundTag pDataTag) {
        this.setAirSupply(this.getMaxAirSupply());
        this.setXRot(0.0F);
        return super.finalizeSpawn(pLevel, pDifficulty, pReason, pSpawnData, pDataTag);
    }

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

    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new BelugaStayOnSurfaceGoal(this, onSurfaceStayTime(), onSurfaceParticleInterval()));
        this.goalSelector.addGoal(2, new BelugaBreathAirGoal(this, breathMaxAirSupply(), breathInterval()));
        this.goalSelector.addGoal(2, new TryFindWaterGoal(this));
        this.goalSelector.addGoal(6, new BelugaSongPlayGoal(this, songPlayScope(), songPlayParticleInterval(), songPlayParticleNumber()));
        this.goalSelector.addGoal(8, new BelugaGroupSwimGoal(this, groupSwimDetectScope(), groupSwimMaxDistance()));
        this.goalSelector.addGoal(9, new BelugaFrolicGoal(this));
        this.goalSelector.addGoal(10, new RandomSwimmingGoal(this, 1.0D, 10));
        this.goalSelector.addGoal(10, new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
    }

    /**
     * 每刻执行的逻辑
     */
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
     * 以下部分为定义同步数据及访问同步数据的方法
     */

    /**
     * 定义同步数据
     */
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

        this.entityData.define(STAYED_ON_SURFACE_FLAG, true);
        this.entityData.define(MOISTNESS_LEVEL, moistnessMax());
    }

    /**
     * 游戏保存时保存自定义值
     * @param pCompound
     */
    @Override
    public void addAdditionalSaveData(CompoundTag pCompound) {
        super.addAdditionalSaveData(pCompound);

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

        this.setStayedOnSurface(pCompound.getBoolean("StayedOnSurfaceFlag"));
        this.setMoisntessLevel(pCompound.getInt("Moistness"));
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

    protected int breathMaxAirSupply() {
        return Config.getInstance().intValueOf("entity.youngBeluga.breath.maxAirSupply");
    }

    protected int breathInterval() {
        return Config.getInstance().intValueOf("entity.youngBeluga.breath.interval");
    }

    protected int onSurfaceStayTime() {
        return Config.getInstance().intValueOf("entity.youngBeluga.onSurface.stayTime");
    }

    protected int onSurfaceParticleInterval() {
        return Config.getInstance()
                .intValueOf("entity.youngBeluga.onSurface.particle.interval");
    }

    protected int songPlayScope() {
        return Config.getInstance().intValueOf("entity.youngBeluga.songPlay.scope");
    }

    protected int songPlayParticleInterval() {
        return Config.getInstance()
                .intValueOf("entity.youngBeluga.songPlay.particle.interval");
    }

    protected int songPlayParticleNumber() {
        return Config.getInstance().intValueOf("entity.youngBeluga.songPlay.particle.number");
    }

    protected int groupSwimDetectScope() {
        return Config.getInstance().intValueOf("entity.youngBeluga.groupSwim.detectScope");
    }

    protected int groupSwimMaxDistance() {
        return Config.getInstance().intValueOf("entity.youngBeluga.groupSwim.maxDistance");
    }

    protected int moistnessMax() {
        return Config.getInstance().intValueOf("entity.youngBeluga.moistness.max");
    }

    protected float moistnessDryHurt() {
        return Config.getInstance().floatValueOf("entity.youngBeluga.moistness.dryHurt");
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
