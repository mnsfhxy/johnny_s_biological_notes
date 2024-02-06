package com.mnsfhxy.johnny_s_biological_notes.entity.beluga;

import com.mnsfhxy.johnny_s_biological_notes.config.Config;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.goal.BelugaFollowBoatGoal;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.goal.BelugaMeleeAttackGoal;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.young.EntityYoungBeluga;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl;
import net.minecraft.world.entity.ai.goal.BreedGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

/**
 * 白鲸的实体类
 */
public class EntityBeluga extends EntityYoungBeluga {
    // 死亡后掉落的经验球数量
    public static final int EXPERIENCE_REWARD_NUMBER = 3;
    public final AnimationState attackingState = new AnimationState();
    //白鲸的嬉戏加速状态
    private static final EntityDataAccessor<Boolean> FROLIC_SPEED_FLAG = SynchedEntityData.defineId(EntityBeluga.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Boolean> STANDING_FLAG = SynchedEntityData.defineId(EntityBeluga.class, EntityDataSerializers.BOOLEAN);

    /**
     * 白鲸构造方法
     *
     * @param pEntityType
     * @param pLevel
     */
    public EntityBeluga(EntityType<? extends Animal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

        this.moveControl = new SmoothSwimmingMoveControl(this, 85, 10, Config.getInstance().floatValueOf("entity.beluga.swimming.inWaterSpeedModifier"), Config.getInstance().floatValueOf("entity.beluga.swimming.outsideWaterSpeedModifier"), true);
    }

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
        return RegistrationInit.ENTITY_TYPE_YOUNG_BELUGA.create(pLevel);
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    /**
     * 以上为覆盖/实现Animal相关内容
     * @return
     */

    public static AttributeSupplier.Builder prepareAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, Config.getInstance().doubleValueOf("entity.beluga.attributes.movementSpeed"))
                .add(Attributes.MAX_HEALTH, Config.getInstance().doubleValueOf("entity.beluga.attributes.maxHealth"))
                .add(Attributes.ATTACK_DAMAGE, Config.getInstance().doubleValueOf("entity.beluga.attributes.attackDamage"));
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

        this.goalSelector.addGoal(0, new BelugaMeleeAttackGoal(this, (double)1.2F, true));
        this.goalSelector.addGoal(4, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(5, new BelugaFollowBoatGoal(this));
    }

    @Override
    public void tick() {
        super.tick();

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
     * 消费物品
     * @param player
     * @param stack
     * @param amount
     */
    public void consumeItemFromStack(Player player, ItemStack stack, int amount) {
        // 如果玩家的物品栈中的物品数量大于或等于需要消耗的数量
        if (stack.getCount() >= amount) {
            // 从物品栈中减去需要消耗的数量
            stack.shrink(amount);
        } else {
            // 如果玩家的物品栈中的物品数量小于需要消耗的数量，就把物品栈清空
            player.getInventory().removeItem(stack);
        }
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

        this.entityData.define(FROLIC_SPEED_FLAG, false);
        this.entityData.define(STANDING_FLAG, false);
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

    /**
     * 以上部分为定义同步数据及访问同步数据的方法
     */


    /**
     * 以下部分为读取配置的值，不同实例采用不同配置创建相关的
     * Goal等
     * @return
     */

    @Override
    protected int breathMaxAirSupply() {
        return Config.getInstance().intValueOf("entity.beluga.breath.maxAirSupply");
    }

    @Override
    protected int breathInterval() {
        return Config.getInstance().intValueOf("entity.beluga.breath.interval");
    }

    @Override
    protected int onSurfaceStayTime() {
        return Config.getInstance().intValueOf("entity.beluga.onSurface.stayTime");
    }

    @Override
    protected int onSurfaceParticleInterval() {
        return Config.getInstance()
                .intValueOf("entity.beluga.onSurface.particle.interval");
    }

    @Override
    protected int songPlayScope() {
        return Config.getInstance().intValueOf("entity.beluga.songPlay.scope");
    }

    @Override
    protected int songPlayParticleInterval() {
        return Config.getInstance()
                .intValueOf("entity.beluga.songPlay.particle.interval");
    }

    @Override
    protected int songPlayParticleNumber() {
        return Config.getInstance().intValueOf("entity.beluga.songPlay.particle.number");
    }

    @Override
    protected int groupSwimDetectScope() {
        return Config.getInstance().intValueOf("entity.beluga.groupSwim.detectScope");
    }

    @Override
    protected int groupSwimMaxDistance() {
        return Config.getInstance().intValueOf("entity.beluga.groupSwim.maxDistance");
    }

    @Override
    protected int moistnessMax() {
        return Config.getInstance().intValueOf("entity.beluga.moistness.max");
    }

    @Override
    protected float moistnessDryHurt() {
        return Config.getInstance().floatValueOf("entity.beluga.moistness.dryHurt");
    }

    /**
     * 以上部分为读取配置的值，不同实例采用不同配置创建相关的
     * Goal等
     * @return
     */
}
