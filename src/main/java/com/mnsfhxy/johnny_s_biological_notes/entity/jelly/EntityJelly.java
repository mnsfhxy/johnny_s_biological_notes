package com.mnsfhxy.johnny_s_biological_notes.entity.jelly;

import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.bubble.EntityJellyBubble;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ambient.Bat;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.Tags;
import org.jetbrains.annotations.Nullable;

public class EntityJelly extends PathfinderMob {
    public float xBodyRot;
    public float xBodyRotO;
    public float zBodyRot;
    public float zBodyRotO;
    public AnimationState movingAnimation = new AnimationState();
    public EntityJelly(EntityType<? extends PathfinderMob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new FlyingMoveControl(this, 10, true);
        this.movingAnimation.startIfStopped(this.tickCount);
//        this.setDeltaMovement(this.getX(),this.getY()+3,this.getZ());
//        setNoGravity(true);
    }
//    public EntityJelly(EntityType<? extends AmbientCreature> pEntityType, Level pLevel) {
//        super(pEntityType, pLevel);
//
////        setNoGravity(true);
//    }

    @Override
    protected void registerGoals() {
        super.registerGoals();

        this.goalSelector.addGoal(1, new MoveToAmethystGoal(this, 1.0D, 20, 20));
        this.goalSelector.addGoal(2, new WaterAvoidingRandomFlyingGoal(this, 0.3D){
            @Override
            public boolean canUse() {
                if (this.mob.isVehicle()) {
                    return false;
                } else {
                    Vec3 vec3 = this.getPosition();
                    if (vec3 == null) {
                        this.mob.setDeltaMovement(this.mob.getX(),this.mob.getY()+0.1,this.mob.getZ());
                        return false;
                    } else {
                        this.wantedX = vec3.x;
                        this.wantedY = vec3.y;
                        this.wantedZ = vec3.z;
                        this.forceTrigger = false;
                        return true;
                    }
                }
            }
        });
//        this.goalSelector.addGoal(3, new FloatGoal(this));

    }
    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
    }

    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(false);
        return flyingpathnavigation;
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 10.0D).add(Attributes.FLYING_SPEED, (double) 0.6F);
    }

    @Override
    public void aiStep() {

        super.aiStep();
        this.xBodyRotO = this.xBodyRot;
        this.zBodyRotO = this.zBodyRot;
        Vec3 vec3 = this.getDeltaMovement();
        double d0 = vec3.horizontalDistance();
        this.yBodyRot += (-((float)Mth.atan2(vec3.x, vec3.z)) * (180F / (float)Math.PI) - this.yBodyRot) * 0.1F;
        this.setYRot(this.yBodyRot);
        this.zBodyRot += (float)Math.PI * 1 * 1.5F;
        this.xBodyRot += (-((float)Mth.atan2(d0, vec3.y)) * (180F / (float)Math.PI) - this.xBodyRot) * 0.1F;

    }

    private boolean isMoving() {
        return this.getDeltaMovement().horizontalDistanceSqr() > 1.0E-6D;
    }

    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }
    public static boolean checkJellySpawnRules(EntityType<EntityJelly> pBat, LevelAccessor pLevel, MobSpawnType pSpawnType, BlockPos pPos, RandomSource pRandom) {
        if(!pLevel.getBiome(pPos).is(Biomes.SMALL_END_ISLANDS)&&pPos.getY() >= 0 || pPos.getY() <= -64){
            return false;
        }
        if (pPos.getY() >= pLevel.getSeaLevel()) {
            return false;
        } else {
            int i = pLevel.getMaxLocalRawBrightness(pPos);
            int j = 4;
             if (pRandom.nextBoolean()) {
                return false;
            }

            return i > pRandom.nextInt(j) ? false : checkMobSpawnRules(pBat, pLevel, pSpawnType, pPos, pRandom);
        }
    }
    public static void init() {
        SpawnPlacements.register(RegistrationInit.JELLY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
                EntityJelly::checkJellySpawnRules);
    }


    @Override
    protected void dropCustomDeathLoot(DamageSource pSource, int pLooting, boolean pRecentlyHit) {
        super.dropCustomDeathLoot(pSource, pLooting, pRecentlyHit);
        BlockPos onPos = getOnPos();
        int sy = onPos.getY() + 11;
        boolean isPlaced = false;
        for (int i = 0; i < 23; i++) {
            for (int x = -2; x <= 2; x++) {
                for (int z = -2; z <= 2; z++) {
                    BlockPos blockPos = new BlockPos(onPos.getX() + x, sy, onPos.getZ() + z);
                    BlockState blockState = level.getBlockState(blockPos);
                    if (blockState.isAir()
                            || blockState.is(Blocks.LAVA)
                            || blockState.is(Blocks.WATER)
                            || blockState.is(Blocks.GRASS)
                            || blockState.is(Blocks.TALL_GRASS)
                            ||blockState.is(RegistrationInit.BLOCK_JELLY_EMBRYO.get())
                            ||!blockState.isValidSpawn(level, blockPos, EntityType.WARDEN)) {
                        continue;
                    } else {
                        if (level.getBlockState(blockPos.above()).isAir()) {
                            level.setBlock(blockPos.above(), RegistrationInit.BLOCK_JELLY_EMBRYO.get().defaultBlockState(), 2);
                            isPlaced = true;
                            break;
                        }
                        continue;
                    }

                }
                if (isPlaced) break;
            }
            if (isPlaced) break;
            sy--;
        }
    }
//    @Override
//    public void tick() {
//        super.tick();
//        if(this.level.isClientSide){
////            if(isMoving()){
////                this.movingAnimation.startIfStopped(this.tickCount);
////            }else{
////                this.movingAnimation.stop();
////            }
//        }
//
//    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundInit.JELLY_DEATH.get();
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource pDamageSource) {
        return SoundInit.JELLY_HURT.get();
    }

    @Override
    protected InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        if (!level.isClientSide) {
            ItemStack itemstack = pPlayer.getItemInHand(pHand);
            if (itemstack.is(Items.POTION)) {

                EntityJelly entityJelly = RegistrationInit.JELLY.get().spawn((ServerLevel) level, (CompoundTag) null, (Component) null, (Player) null, getOnPos().above(), MobSpawnType.EVENT, false, false);
                if (entityJelly != null) {
                    entityJelly.restrictTo(getOnPos().above(), 16);
                }
                int bubbleNum = 1;
                int r = random.nextInt(100);
                if (r >= 52) bubbleNum++;
                if (r > 70) bubbleNum++;
                for (int i = 0; i < bubbleNum; i++) {
                    // 生成随机角度
                    double angle = 2 * Math.PI * random.nextDouble();
                    // 生成随机半径
                    double radius = 3 * Math.sqrt(random.nextDouble());
                    // 计算x坐标和y坐标
                    double x = radius * Math.cos(angle);
                    double z = radius * Math.sin(angle);
                    BlockPos pos = new BlockPos(position().x() + x, position().y(), position().z() + z);
                    EntityJellyBubble entityJellyBubble = RegistrationInit.JELLY_BUBBLE.get().spawn((ServerLevel) level, (CompoundTag) null, (Component) null, (Player) null, pos, MobSpawnType.EVENT, false, false);
                }
                ItemStack filledResult = ItemUtils.createFilledResult(itemstack, pPlayer, new ItemStack(Items.GLASS_BOTTLE));
                pPlayer.setItemInHand(pHand, filledResult);
                playSound(SoundInit.JELLY_MAKE_BUBBLE.get(), 1.0F, 1.0F);
            }

        }

        return InteractionResult.sidedSuccess(this.level.isClientSide);

    }

    class MoveToAmethystGoal extends MoveToBlockGoal {


        public MoveToAmethystGoal(PathfinderMob pMob, double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
            super(pMob, pSpeedModifier, pSearchRange, pVerticalSearchRange);
        }

        @Override
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            BlockState blockState = pLevel.getBlockState(pPos);
            if (blockState.is(Blocks.AMETHYST_BLOCK) || blockState.is(Blocks.AMETHYST_CLUSTER) || blockState.is(Blocks.LARGE_AMETHYST_BUD) || blockState.is(Blocks.MEDIUM_AMETHYST_BUD) || blockState.is(Blocks.SMALL_AMETHYST_BUD)) {
                return true;
            }
            return false;
        }

        @Override
        public void tick() {
            super.tick();
            if (this.mob.level instanceof ServerLevel) {
                ((ServerLevel) this.mob.level).sendParticles(RegistrationInit.JELLY_GLOW_PARTICLE.get(),this.mob.getRandomX(0.6D), this.mob.getRandomY(), this.mob.getRandomZ(0.6D), 1, 0.0D, -1D, 0.0D, 0.0D);
//                ((ServerLevel) this.mob.level).sendParticles(RegistrationInit.JELLY_GLOW_PARTICLE.get(), this.mob.getX(), this.mob.getY(), this.mob.getZ(), 0, -1.0D, 0.0D, 0.0D, 1.0D);
//                ((ServerLevel) this.mob.level).sendParticles(RegistrationInit.JELLY_GLOW_PARTICLE.get(), this.mob.getX(), this.mob.getY(), this.mob.getZ(), 0, 0.0D, -1.0D, -1.0D, 1.0D);


            }
        }
    }
}
