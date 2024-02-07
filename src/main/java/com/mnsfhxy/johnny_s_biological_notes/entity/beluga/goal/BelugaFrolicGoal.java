package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.goal;

import com.mnsfhxy.johnny_s_biological_notes.config.Config;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.EntityBeluga;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import com.mnsfhxy.johnny_s_biological_notes.tool.CooldownMonitor;
import com.mnsfhxy.johnny_s_biological_notes.tool.SpeedModifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.EnumSet;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * 白鲸嬉戏的Goal
 */
public class BelugaFrolicGoal extends Goal {
    private static final int COOLDOWN_INTERVAL = Config.getInstance().intValueOf("entity.beluga.frolic.cooldownInterval");
    public static final double SPEED_RATIO = Config.getInstance().doubleValueOf("entity.beluga.frolic.leader.speedRatio");
    public static final int LEADER_DETECT_SCOPE = Config.getInstance().intValueOf("entity.beluga.frolic.leader.scope");
    public static final int FOLLOWER_DETECT_SCOPE = Config.getInstance().intValueOf("entity.beluga.frolic.follower.scope");
    public static final double PROBABILITY = Config.getInstance().doubleValueOf("entity.beluga.frolic.leader.probability");
    public static final int DURING_TIME = Config.getInstance().intValueOf("entity.beluga.frolic.leader.duringTime");
    public static final int RANDOM_TARGET_RETRY_TIMES = Config.getInstance().intValueOf("entity.beluga.frolic.leader.randomTarget.retryTimes");
    private SpeedModifier belugaSpeedModifier;
    private static final int HEAL_INTERVAL = Config.getInstance().intValueOf("entity.beluga.frolic.leader.heal.interval");
    private static final int HEAL_AMOUNT = Config.getInstance().intValueOf("entity.beluga.frolic.leader.heal.amount");
    private CooldownMonitor cooldownMonitor;
    private FrolicRole role;
    private Optional<Vec3> targetPos;
    private EntityBeluga leaderBeluga;

    private final EntityBeluga beluga;
    private int healCounter = 0;

    public BelugaFrolicGoal(EntityBeluga beluga) {
        this.beluga = beluga;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));

        this.cooldownMonitor = new CooldownMonitor(COOLDOWN_INTERVAL);
        belugaSpeedModifier = new SpeedModifier(this.beluga.getAttribute(Attributes.MOVEMENT_SPEED), SPEED_RATIO, "白鲸嬉戏加速");
        this.role = FrolicRole.FOLLOWER;
    }

    /**
     * 当Goal冷却且领航者附近探测到白鲸时，尝试加速的概率，
     * 成功则成为领航者，失败则重置冷却时间
     *
     * @return
     */
    @Override
    public boolean canUse() {
        // 跟随者判断
        if (this.beluga.hasLeader()) {
            this.role = FrolicRole.FOLLOWER;
            leaderBeluga = (EntityBeluga) this.beluga.level.getEntity(this.beluga.getFrolicLeaderId());

            return true;
        }

        // 领航者判断，必须是成年白鲸
        if (!this.beluga.isBaby()
                && this.cooldownMonitor.checkIsActive()) {
            Optional<EntityBeluga> firstFoundBeluga =
                    firstBelugaInScopeWith(LEADER_DETECT_SCOPE);
            if (firstFoundBeluga.isPresent()) {
                if (isWinTheChange()) {
                    this.role = FrolicRole.LEADER;
                    leaderNotifyOthersWith(this.beluga.getId(), FOLLOWER_DETECT_SCOPE);

                    return true;
                } else {
                    this.cooldownMonitor.reset();
                }
            }
        }

        return false;
    }

    @Override
    public void start() {
        if (this.role.equals(FrolicRole.LEADER)) {
            belugaSpeedModifier.enable();
            this.beluga.setFrolicSpeedFlag(true);

            targetPos = randomCanReachedTarget(RANDOM_TARGET_RETRY_TIMES);
            healCounter = 0;

            this.beluga.playSound(SoundInit.BELUGA_PLAY.get(), 1.0F, 1.0F);
        }
    }

    @Override
    public void tick() {
        // 领航者向目标随机位置移动，并治疗自己
        if (this.role.equals(FrolicRole.LEADER)
                && targetPos.isPresent()) {
            beluga.getNavigation().moveTo(targetPos.get().x(), targetPos.get().y(), targetPos.get().z(), 1.0);

            heal();
        }

        // 跟随者向领航者移动
        if (this.role.equals(FrolicRole.FOLLOWER))
            this.beluga.getNavigation().moveTo(leaderBeluga, 1.0);
    }

    @Override
    public boolean canContinueToUse() {
        if (this.role.equals(FrolicRole.LEADER))
            return this.cooldownMonitor.checkIsActive() &&
                    this.targetPos.isPresent();
        return leaderBeluga.isFrolicSpeed()
                || (!leaderBeluga.isFrolicSpeed()
                && this.beluga.distanceTo(leaderBeluga) < FOLLOWER_DETECT_SCOPE);
    }

    @Override
    public void stop() {
        if (this.role.equals(FrolicRole.LEADER)) {
            belugaSpeedModifier.disable();
            this.beluga.setFrolicSpeedFlag(false);
        }

        if (this.role.equals(FrolicRole.FOLLOWER))
            this.beluga.setFrolicLeaderId(EntityBeluga.INVALID_LEADER_ID);

        cooldownMonitor.reset();
    }

    /**
     * 查找范围内符合条件的第一个白鲸
     *
     * @param scope
     * @return
     */
    private Optional<EntityBeluga> firstBelugaInScopeWith(int scope) {
        Predicate<Entity> predicate = (entity) ->
                entity instanceof EntityBeluga;
        Optional<EntityBeluga> firstFoundBegula = this.beluga.level.getEntities(this.beluga, this.beluga.getBoundingBox().inflate(scope), predicate)
                .stream()
                .map(entity -> (EntityBeluga) entity)
                .findFirst();
        return firstFoundBegula;
    }

    /**
     * leader通知其他白鲸自己的信息
     *
     * @param scope
     */
    private void leaderNotifyOthersWith(int leaderId, int scope) {
        Predicate<Entity> predicate = (entity) ->
                entity instanceof EntityBeluga
                        && !((EntityBeluga) entity).hasLeader()
                        && !((EntityBeluga) entity).isFrolicSpeed();
        this.beluga.level.getEntities(this.beluga, this.beluga.getBoundingBox().inflate(scope), predicate)
                .stream()
                .forEach(entity -> ((EntityBeluga) entity).setFrolicLeaderId(leaderId));
    }

    /**
     * 进行随机抽奖，看是否获得加速的机会
     *
     * @return
     */
    private boolean isWinTheChange() {
        return beluga.getRandom().nextFloat() < PROBABILITY;
    }

    /**
     * 获得一个随机的可达目标位置
     *
     * @param retryTimes
     * @return
     */
    private Optional<Vec3> randomCanReachedTarget(int retryTimes) {
        double distance = calculateDistance();

        for (int i = 0; i < retryTimes; i++) {
            Vec3 randomVec = randomTarget(distance);
            Path path = this.beluga.getNavigation().createPath(randomVec.x(), randomVec.y(), randomVec.z(), 1);
            if (path != null)
                return Optional.of(randomVec);
        }

        return Optional.empty();
    }

    /**
     * 根据距离计算随机的目标位置
     *
     * @param distance
     * @return
     */
    @NotNull
    private Vec3 randomTarget(double distance) {
        RandomSource random = beluga.getRandom();
        double dx = (random.nextFloat() * 2.0 - 1.0) * distance;
        double dz = (random.nextFloat() * 2.0 - 1.0) * distance;
        Vec3 targetPos = new Vec3(beluga.getX() + dx, beluga.getY(), beluga.getZ() + dz);
        return targetPos;
    }

    /**
     * 根据当前速度和Goal的持续时间，计算出目标的距离
     *
     * @return
     */
    private double calculateDistance() {
        double speed = beluga.getAttribute(Attributes.MOVEMENT_SPEED).getValue();
        double distance = speed * DURING_TIME;
        return distance;
    }

    /**
     * 隔一段时间治疗自己
     */
    private void heal() {
        healCounter++;
        if (healCounter >= HEAL_INTERVAL / 2) {
            beluga.heal(HEAL_AMOUNT);
            healCounter = 0;
        }
    }

    /**
     * 嬉戏Goal中担任的角色
     */
    enum FrolicRole {
        // 领航者
        LEADER,
        // 跟随者
        FOLLOWER;
    }
}

