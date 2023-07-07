package com.mnsfhxy.johnny_s_biological_notes.entity.peeper;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.EntityCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.goal.AvoidBlockGoal;
import com.mnsfhxy.johnny_s_biological_notes.init.LootTablesInit;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.util.DefaultRandomPos;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityPeeper extends PathfinderMob {
    private static final float MOVEMENT_SPEED = 0.5F;
    //0 未给战利品不敌对 1 未给战利品敌对 2 给完战利品不敌对 3给完战利品敌对
    Map<Player, Integer> playerList = new HashMap<>() {
        @Override
        public boolean containsKey(Object key) {
            if (key instanceof Player) {
                for (Player p : keySet()) {
                    if (p.getUUID().equals(((Player) key).getUUID())) return true;
                }
            }
            return false;
        }
        // other methods
    };
    Player peepingPlayer = null;

    public EntityPeeper(EntityType<EntityPeeper> type, Level world) {
        super(type, world);
        xpReward = 5;
        setNoAi(false);
    }

    @Override
    public Packet<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    boolean isLookingAtMe(Player pPlayer) {
        Vec3 vec3 = pPlayer.getViewVector(1.0F).normalize();
        Vec3 vec31 = new Vec3(this.getX() - pPlayer.getX(), this.getEyeY() - pPlayer.getEyeY(), this.getZ() - pPlayer.getZ());
        double d0 = vec31.length();
        vec31 = vec31.normalize();
        double d1 = vec3.dot(vec31);
        return d1 > 1.0D - 0.025D / d0 ? pPlayer.hasLineOfSight(this) : false;
    }

    @Override
    public SoundEvent getAmbientSound() {
        return SoundInit.PEEPER_SOUND.get();
    }
    @Override
    protected void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(1, new RunAwayFireGoal(this));
        this.goalSelector.addGoal(2, new RunAwayPlayerGoal(this));
        this.goalSelector.addGoal(3, new PeepGoal(this));
        this.goalSelector.addGoal(4, new PeeperRandomStrollGoal(this, MOVEMENT_SPEED));
        this.goalSelector.addGoal(5, new PeeperRandomLookAroundGoal(this));
        this.goalSelector.addGoal(6, new FloatGoal(this));
    }

    @Override
    public MobType getMobType() {
        return MobType.UNDEFINED;
    }

    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHitIn) {
        super.dropCustomDeathLoot(source, looting, recentlyHitIn);
        this.spawnAtLocation(new ItemStack(Items.STICK));
    }

    @Override
    public SoundEvent getHurtSound(DamageSource ds) {
        return SoundInit.PEEPER_HURT.get();
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        float amount = pAmount;
        if (pSource.isFire()) {
            amount = pAmount * 2;
        }
        Entity entity = pSource.getEntity();
        if (entity instanceof Player) {
            if (peepingPlayer == entity) peepingPlayer = null;
            if (playerList.containsKey(entity)) {
                if (playerList.get(entity) == 0 || playerList.get(entity) == 2)
                    playerList.replace((Player) entity, playerList.get(entity) + 1);
            } else {
                playerList.put((Player) entity, 1);

            }
        }
        return super.hurt(pSource, amount);
    }

    public static Vec3 getBlockBehindPlayer(Player player) {
        // 获取玩家当前位置信息

        double playerX = player.getX();
        double playerY = player.getY();
        double playerZ = player.getZ();
        Vec3 vec3 = player.getViewVector(1.0F).normalize();
        vec3.multiply(-1, 1, -1);

        // 计算出玩家背后的方向向量
        // 计算出背后方块的坐标
        int backX = (int) Math.round(playerX + vec3.x);
        int backY = (int) Math.round(playerY + vec3.y);
        int backZ = (int) Math.round(playerZ + vec3.z);
        return new Vec3(backX, backY, backZ);
        // 创建新的位置信息并返回
//        return new Location(player.getWorld(), backX, backY, backZ);
    }

    public static void init() {
        SpawnPlacements.register(RegistrationInit.PEEPER.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                (entityType, world, reason, pos, random) -> ( Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
    }

    @Override
    public SoundEvent getDeathSound() {
        return SoundInit.PEEPER_DEATH.get();
    }

    public static boolean checkPeeperSpawnRule(EntityType<EntityPeeper> entityType,
                                               ServerLevelAccessor world,
                                               MobSpawnType reason,
                                               BlockPos pos,
                                               RandomSource random){
        return Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random);
    }

    public AnimationState walkingAnimationState = new AnimationState();
    public AnimationState waitingAnimationState = new AnimationState();

    public static AttributeSupplier.Builder prepareAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED);
        builder = builder.add(Attributes.MAX_HEALTH, 20);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64);
        return builder;
    }

    //使用avoid entity goal需要重写 否则无法使用   要保证peeper. canAttack(Player)为true
    @Override
    public boolean canAttack(LivingEntity pTarget) {
//        return pTarget instanceof Player && this.level.getDifficulty() != Difficulty.PEACEFUL ;
        return true;
    }

    @Override
    public void tick() {
        super.tick();
//        if(Minecraft.getInstance().player!=null) {
//            JohnnySBiologicalNotes.LOGGER.info("player x rotation: " + String.valueOf(Minecraft.getInstance().player.getXRot()));
//            JohnnySBiologicalNotes.LOGGER.info("player y rotation: " + String.valueOf(Minecraft.getInstance().player.getYRot()));
//            JohnnySBiologicalNotes.LOGGER.info("peeper x rotation: " + String.valueOf(this.getXRot()));
//            JohnnySBiologicalNotes.LOGGER.info("peeper y rotation: " + String.valueOf(this.getYRot()));
//        }
        for (Player player : this.level.getEntitiesOfClass(Player.class, this.getBoundingBox().inflate(20D))) {
            if (!playerList.containsKey(player)) playerList.put(player, 0);
        }
        if (this.isMoving()) {
            this.waitingAnimationState.stop();
            this.walkingAnimationState.startIfStopped(this.tickCount);
        } else {
            this.waitingAnimationState.startIfStopped(this.tickCount);
            this.walkingAnimationState.stop();
        }

    }


    private boolean isMoving() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }

    private class RunAwayPlayerGoal extends AvoidEntityGoal<Player> {
        EntityPeeper peeper;
//        Player nearestPlayer;


        public RunAwayPlayerGoal(EntityPeeper peeper) {
            super(peeper, Player.class, 12, MOVEMENT_SPEED, MOVEMENT_SPEED, (p) -> peeper.playerList.containsKey(p) && peeper.playerList.get(p) % 2 != 0);
            this.peeper = peeper;
        }

    }

    private class RunAwayFireGoal extends AvoidBlockGoal {
        EntityPeeper peeper;
//        Player nearestPlayer;

        public RunAwayFireGoal(EntityPeeper peeper) {
            super(peeper, Block.class, 8, MOVEMENT_SPEED, MOVEMENT_SPEED, (p) -> Blocks.LAVA.toString().equals(p.toString()) || Blocks.FIRE.toString().equals(p.toString()) || Blocks.SOUL_FIRE.toString().equals(p.toString()));
            this.peeper = peeper;
        }

    }

    private class PeepGoal extends Goal {
        EntityPeeper peeper;
        int peeperTick;
        int watchTick;
        PathNavigation pathNav;
        Path path;

        public PeepGoal(EntityPeeper peeper) {
            this.peeper = peeper;

        }

        @Override
        public boolean canUse() {
//            peeper.getLevel().getEntitiesOfClass(LivingEntity.class,)
            if (peeper.peepingPlayer != null) return true;
            for (var p : peeper.playerList.entrySet()) {
                if (p.getValue() == 0&&!p.getKey().isCreative()) {
                    peeper.peepingPlayer = p.getKey();
                    peeperTick = 0;
                    watchTick=0;
                    if(peeper.hasLineOfSight(p.getKey())) return true;
                }
            }
            return false;
        }

        @Override
        public void start() {
            super.start();

            for (var e : peeper.playerList.entrySet()) {
                if (e.getValue() == 0 && e.getKey().distanceTo(peeper) < peeper.peepingPlayer.distanceTo(peeper)) {
                    peeper.peepingPlayer = e.getKey();
                }
            }
            this.pathNav = peeper.getNavigation();
        }

        @Override
        public boolean canContinueToUse() {
            return super.canContinueToUse() && peeper.peepingPlayer != null;
        }

        @Override
        public void tick() {
            super.tick();
            if(!this.peeper.peepingPlayer.isAlive()||this.peeper.peepingPlayer.isCreative()){
                stop();
                return;
            }
            if(peeper.hasLineOfSight(peepingPlayer)) {
//            if(BehaviorUtils.canSee(peeper,peepingPlayer)) {
                this.pathNav=peeper.getNavigation();
                if (peepingPlayer == null || this.pathNav == null) {
                    stop();
                    return;
                }
                ;
                if (!peeper.isMoving()) {
                    peeper.getLookControl().setLookAt(peepingPlayer);
                }
                if (this.path != null && this.pathNav.isDone()) {
                    //移动完成
                    this.path = null;
                } else {
                    peeperTick++;
                    if(isLookingAtMe(this.peeper.peepingPlayer))watchTick++;
                    if (watchTick == 5) {
                        watchTick=0;
                        Vec3 posAway = DefaultRandomPos.getPosAway(peeper, 20, 10, peepingPlayer.position());
                        while (posAway == null)
                            posAway = DefaultRandomPos.getPosAway(peeper, 20, 10, peepingPlayer.position());
                        this.path = this.pathNav.createPath(posAway.x, posAway.y, posAway.z, 0);
                        this.pathNav.moveTo(this.path, MOVEMENT_SPEED * 2);
                    }
//                else if (peeperTick > 5 && peeperTick < UtilLevel.TIME.SECOND.getTick() * 30) {}
                         if (peeperTick >= UtilLevel.TIME.SECOND.getTick() * 12) {
                        if (peepingPlayer.distanceToSqr(peeper.position()) > 2) {
                            peeper.getNavigation().moveTo(peepingPlayer, MOVEMENT_SPEED);
//
                        } else {
                            //观察足够时间
                            LootTable loottable = peeper.level.getServer().getLootTables().get(LootTablesInit.PEEPER_GIFT);
                            List<ItemStack> list = loottable.getRandomItems((new LootContext.Builder((ServerLevel)peeper.level)).withParameter(LootContextParams.THIS_ENTITY, peeper).withRandom(peeper.level.random).create(LootTablesInit.PEEPER_GIFT_PARAM));
                            for(ItemStack itemstack : list) {
                                BehaviorUtils.throwItem(peeper, itemstack, peeper.position().add(0.0D, 1.0D, 0.0D));
                            }
                            peeper.playerList.replace(peepingPlayer,2);
                            stop();
                            return;

                        }
//                    peeper.spawnLootToPlayer(peeper.peepingPlayer);
                    }
                }
            }else{
                stop();
                return;

            }
        }

        @Override
        public void stop() {
            super.stop();
            peeper.peepingPlayer = null;
            peeperTick = 0;
            watchTick=0;
            path = null;
            pathNav = null;
        }
    }
    private class PeeperRandomStrollGoal extends RandomStrollGoal{
        EntityPeeper peeper;
        public PeeperRandomStrollGoal(EntityPeeper pMob, double pSpeedModifier) {
            super(pMob, pSpeedModifier);
            this.peeper=pMob;

        }

        @Override
        public boolean canUse() {
            return peeper.peepingPlayer==null&&super.canUse();
        }
    }
    private class PeeperRandomLookAroundGoal extends RandomLookAroundGoal{
        EntityPeeper peeper;

        public PeeperRandomLookAroundGoal(EntityPeeper pMob) {
            super(pMob);
            this.peeper=pMob;

        }
        @Override
        public boolean canUse() {
            return peeper.peepingPlayer==null&&super.canUse();
        }
    }
}
