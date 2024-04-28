package com.mnsfhxy.johnny_s_biological_notes.entity.betty;

import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.TagsInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.*;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.behavior.BehaviorUtils;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.MoveToBlockGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.memory.MemoryModuleType;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import net.minecraft.world.entity.ai.goal.FloatGoal;

import java.util.List;
import java.util.Objects;

public class EntityBetty extends TamableAnimal implements InventoryCarrier, FlyingAnimal {
    public AnimationState idleAnimationState = new AnimationState();
    public AnimationState flyAnimationState = new AnimationState();
    public AnimationState holdItemAnimationState = new AnimationState();
    public AnimationState holdItemFlyAnimationState = new AnimationState();
    public AnimationState clapAnimationState = new AnimationState();
    private final SimpleContainer inventory = new SimpleContainer(1);
    private boolean dancing = false;
    public EntityBetty(EntityType<? extends TamableAnimal> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.moveControl = new FlyingMoveControl(this, 20, true);
//        this.navigation = this.createNavigation(pLevel);
//        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0F);
//        this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 0.0F);
//        if(this.level.isClientSide)
//        crabWalkingSoundInstance=new CrabWalkingSoundInstance(SoundInit.CRAB_WALKING.get(),this.getSoundSource(),this.getSoundVolume(),this.getVoicePitch(),this,this.getRandom().nextLong());
    }
//    public VoxelShape makeShape(){
//        VoxelShape shape = VoxelShape.empty();
//        shape = VoxelShapes.join(shape, VoxelShapes.box(0.34375, 0.25, 0.34375, 0.65625, 0.5625, 0.65625), IBooleanFunction.OR);
//        shape = VoxelShapes.join(shape, VoxelShapes.box(0.26250000000000007, 0.6875, 0.28125000000000006, 0.7625000000000002, 0.6875, 0.7812500000000001), IBooleanFunction.OR);
//        shape = VoxelShapes.join(shape, VoxelShapes.box(0.40625, 0, 0.4375, 0.59375, 0.25, 0.5625), IBooleanFunction.OR);
//        shape = VoxelShapes.join(shape, VoxelShapes.box(0.40625, -0.0625, 0.4375, 0.59375, 0.25, 0.5625), IBooleanFunction.OR);
//        shape = VoxelShapes.join(shape, VoxelShapes.box(0.59375, 0, 0.4375, 0.65625, 0.25, 0.5625), IBooleanFunction.OR);
//        shape = VoxelShapes.join(shape, VoxelShapes.box(0.34375, 0, 0.4375, 0.40625, 0.25, 0.5625), IBooleanFunction.OR);
//        shape = VoxelShapes.join(shape, VoxelShapes.box(0.4375, -0.0625, 0.5625, 0.4375, 0.25, 1.0625), IBooleanFunction.OR);
//        shape = VoxelShapes.join(shape, VoxelShapes.box(0.5625, -0.0625, 0.5625, 0.5625, 0.25, 1.0625), IBooleanFunction.OR);
//
//        return shape;
//    }

    public boolean isDancing() {
        return dancing;
    }
    public void setDancing(boolean dancing) {
        this.dancing = dancing;
    }
    public static AttributeSupplier.Builder prepareAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MOVEMENT_SPEED, (double) 0.1F)
                .add(Attributes.MAX_HEALTH, 20)
                .add(Attributes.ATTACK_DAMAGE, 0)
                .add(Attributes.FLYING_SPEED, (double) 0.25F)
                .add(Attributes.FOLLOW_RANGE, 48.0D);
    }

    public boolean causeFallDamage(float pFallDistance, float pMultiplier, DamageSource pSource) {
        return false;
    }

    @Override
    public boolean isOrderedToSit() {
        return false;
    }

    @Override
    protected void registerGoals() {
//        super();
        //唤回  正常
        this.goalSelector.addGoal(0, new BettyFlyingGoal(this, 0.4D) {
            @Override
            public boolean canUse() {
                if (this.betty == null) return false;
                if (!this.betty.isTame()) {
                    return false;
                }
                if (this.betty.getOwner() == null) return false;
                //与主人距离大于15格返回false
                LivingEntity owner = this.betty.getOwner();
                ItemStack itemstack = owner.getItemInHand(InteractionHand.MAIN_HAND);
                //判断与主人的距离大于40
                if (this.betty.distanceTo(owner) > 40) {
                    return false;
                }
                if (itemstack.is(RegistrationInit.ITEM_BETTY_WITNESS.get())) {
                    Vec3 vec3 = this.getPosition();
                    if (vec3 == null) {
                        this.mob.setDeltaMovement(this.mob.getX(), this.mob.getY() + 0.1, this.mob.getZ());
                        return false;
                    } else {
                        this.wantedX = vec3.x;
                        this.wantedY = vec3.y;
                        this.wantedZ = vec3.z;
                        this.forceTrigger = false;
                        return true;
                    }
                }
                return false;
            }


            @Nullable
            @Override


            protected Vec3 getPosition() {
                return this.betty.getOwner() == null ? null : this.betty.getOwner().position();
            }

            @Override
            public boolean canContinueToUse() {
                LivingEntity owner = this.betty.getOwner();
                ItemStack itemstack = owner.getItemInHand(InteractionHand.MAIN_HAND);
                return itemstack.is(RegistrationInit.ITEM_BETTY_WITNESS.get()) && super.canContinueToUse();

            }
        });
        //飞行到指南针指向位置 正常
        this.goalSelector.addGoal(1, new BettyFlyingGoal(this, 0.3D) {
            @Override
            public boolean canUse() {
                if (this.betty == null) return false;
                if (!this.betty.isTame()) {
                    return false;
                }
                if (this.betty.getOwner() == null) return false;
                //与主人距离大于15格返回false

                if (this.mob.isVehicle()) {
                    return false;
                }
                ItemStack itemstack = this.betty.getItemInHand(InteractionHand.MAIN_HAND);

                if (!(itemstack.is(Items.COMPASS) || itemstack.is(Items.RECOVERY_COMPASS))) {

                    return false;
                } else {
                    Vec3 vec3 = this.getPosition();
                    if (vec3 == null) {
                        this.mob.setDeltaMovement(this.mob.getX(), this.mob.getY() + 0.1, this.mob.getZ());
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

            @Override
            public void tick() {
                super.tick();
//                播放粒子效果
                ((ServerLevel) this.mob.level).sendParticles(ParticleTypes.END_ROD, this.mob.getRandomX(0.6D), this.mob.getRandomY(), this.mob.getRandomZ(0.6D), 1, 0.0D, -1D, 0.0D, 0.0D);

            }

            @Nullable
            @Override
            protected Vec3 getPosition() {
                ItemStack itemstack = this.betty.getItemInHand(InteractionHand.MAIN_HAND);
                GlobalPos Position = null;
                if (itemstack.is(Items.COMPASS)) {
                    if (itemstack.getTag() != null)
                        Position = CompassItem.getLodestonePosition(itemstack.getTag()) == null ? CompassItem.getSpawnPosition(this.betty.level) : CompassItem.getLodestonePosition(itemstack.getTag());
                    else {
                        Position = CompassItem.getSpawnPosition(this.betty.level);
                    }
                    //把Position的坐标转换为Vec3

                } else if (itemstack.is(Items.RECOVERY_COMPASS)) {
                    Position = ((Player) (this.betty.getOwner())).getLastDeathLocation().orElse((GlobalPos) null);
                }
                if (!(Position == null) && !Position.dimension().equals(this.betty.level.dimension())) Position = null;
                return Position == null ? super.getPosition() : new Vec3(Position.pos().getX(), Position.pos().getY(), Position.pos().getZ());
            }

            @Override
            public boolean canContinueToUse() {
                if (this.betty.distanceTo(this.betty.getOwner()) > 15) {
                    return false;
                }
                ItemStack itemstack = this.betty.getItemInHand(InteractionHand.MAIN_HAND);
                return (itemstack.is(Items.COMPASS) || itemstack.is(Items.RECOVERY_COMPASS)) && super.canContinueToUse();
            }
        });
        //跟随主人
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 0.3D, 6.0F, 3.0F, true));
        //闲逛
        this.goalSelector.addGoal(6, new BettyFlyingGoal(this, 0.3D)
        {
            @Override
            public boolean canUse() {
                if (this.betty.getOwner() == null) return false;
                if (this.mob.isVehicle()) {
                    return false;
                } else {
                    Vec3 vec3 = this.getPosition();
                    if (vec3 == null) {
                        this.mob.setDeltaMovement(this.mob.getX(), this.mob.getY() + 0.1, this.mob.getZ());
                        return false;
                    } else {
                        this.wantedX = vec3.x;
                        this.wantedY = vec3.y;
                        this.wantedZ = vec3.z;
                        this.forceTrigger = false;
                        return super.canUse();
                    }
                }
            }
        });

//        this.goalSelector.addGoal(2, new LightSigilGoal(this, 0.3D, 21, 2));
//
//        this.goalSelector.addGoal(3, new PlaceSigilGoal(this, 0.3D, 21, 2));

        this.goalSelector.addGoal(5, new DacinglGoal(this, 0.3D, 5));


    }

    //防止被主人攻击
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (this.isInvulnerableTo(pSource)) {
            return false;
        } else {
            Entity entity = pSource.getEntity();
            if (!this.level.isClientSide) {
                this.setOrderedToSit(false);
            }

            if (entity != null && entity.getType() == EntityType.PLAYER) {
                Player player = (Player) entity;
                if (player.getUUID().equals(this.getOwnerUUID())) {
                    return false;
                }
            }
            return super.hurt(pSource, pAmount);
        }
    }

    public boolean hasItemInHand() {
        return !this.getItemInHand(InteractionHand.MAIN_HAND).isEmpty();
    }



    private void removeInteractionItem(Player pPlayer, ItemStack pStack) {
        if (!pPlayer.getAbilities().instabuild) {
            pStack.shrink(1);
        }

    }

    public boolean canTakeItem(ItemStack pItemstack) {
        return false;
    }

    public InteractionResult mobInteract(Player pPlayer, InteractionHand pHand) {
        ItemStack itemstack = pPlayer.getItemInHand(pHand);
        ItemStack itemstack1 = this.getItemInHand(InteractionHand.MAIN_HAND);
        if (this.level.isClientSide) {
            return InteractionResult.CONSUME;
        } else {
            if (this.isTame()) {
                if (itemstack1.isEmpty() && !itemstack.isEmpty()) {
                    ItemStack itemstack3 = itemstack.copy();
                    itemstack3.setCount(1);
                    this.setItemInHand(InteractionHand.MAIN_HAND, itemstack3);
                    this.removeInteractionItem(pPlayer, itemstack);
//                    this.level.playSound(pPlayer, this, SoundEvents.ALLAY_ITEM_GIVEN, SoundSource.NEUTRAL, 2.0F, 1.0F);
//                    this.getBrain().setMemory(MemoryModuleType.LIKED_PLAYER, pPlayer.getUUID());
                    return InteractionResult.SUCCESS;
                } else if (!itemstack1.isEmpty() && pHand == InteractionHand.MAIN_HAND && itemstack.isEmpty()) {
                    this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY);
//                    this.level.playSound(pPlayer, this, SoundEvents.ALLAY_ITEM_TAKEN, SoundSource.NEUTRAL, 2.0F, 1.0F);
                    this.swing(InteractionHand.MAIN_HAND);

                    for (ItemStack itemstack2 : this.getInventory().removeAllItems()) {
                        BehaviorUtils.throwItem(this, itemstack2, this.position());
                    }

//                    this.getBrain().eraseMemory(MemoryModuleType.LIKED_PLAYER);
                    pPlayer.addItem(itemstack1);
                    return InteractionResult.SUCCESS;
                } else {
                    return super.mobInteract(pPlayer, pHand);
                }
            } else if (itemstack.is(RegistrationInit.ITEM_BETTY_CONTRACT.get())) {

                if (!pPlayer.getAbilities().instabuild) {
                    itemstack.shrink(1);
                    pPlayer.addItem(new ItemStack(RegistrationInit.ITEM_BETTY_WITNESS.get()));
                }

                if (!net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, pPlayer)) {
                    this.tame(pPlayer);
                    this.navigation.stop();
                    this.setTarget((LivingEntity) pPlayer);
                    this.setOrderedToSit(true);
                    this.level.broadcastEntityEvent(this, (byte) 7);
                    return InteractionResult.SUCCESS;
                }

                return super.mobInteract(pPlayer, pHand);
            }
            return super.mobInteract(pPlayer, pHand);
        }
    }

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
                this.moveRelative(this.getSpeed(), pTravelVector);
                this.move(MoverType.SELF, this.getDeltaMovement());
                this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.91F));
            }
        }

        this.calculateEntityAnimation(this, false);
    }

    public boolean isFlapping() {
        return !this.isOnGround();
    }

    protected PathNavigation createNavigation(Level pLevel) {
        FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, pLevel);
        flyingpathnavigation.setCanOpenDoors(false);
        flyingpathnavigation.setCanFloat(true);
        flyingpathnavigation.setCanPassDoors(true);
        return flyingpathnavigation;
    }

    TargetingConditions bettyGlowTarget = TargetingConditions.forCombat().ignoreLineOfSight();

    public Monster findNearestUndead() {
        return this.getLevel().getNearestEntity(Monster.class, bettyGlowTarget, this, this.position().x(), this.position().y(), this.position().z(), this.getBoundingBox().inflate(16.0D, 8.0D, 16.0D));
    }


    @Override
    public void tick() {
        super.tick();
        if (this.tickCount % 20 == 0) {
            this.heal(2);
        }
        if (this.tickCount % 400 == 0) {
            Monster nearestUndead = findNearestUndead();
            if (nearestUndead != null) {
                nearestUndead.addEffect(new MobEffectInstance(MobEffects.GLOWING, 100));
            }

        }
        ItemStack itemstack1 = this.getItemInHand(InteractionHand.MAIN_HAND);
        if(this.isDancing()){
            this.clapAnimationState.startIfStopped(this.tickCount);
            this.flyAnimationState.stop();
            this.holdItemAnimationState.stop();
            this.holdItemFlyAnimationState.stop();
            this.idleAnimationState.stop();


        }else {
            if (this.isFlying()) {
                if (itemstack1.isEmpty()) {
                    this.flyAnimationState.startIfStopped(this.tickCount);
                    this.holdItemAnimationState.stop();
                    this.holdItemFlyAnimationState.stop();
                    this.idleAnimationState.stop();
                } else {
                    this.flyAnimationState.startIfStopped(this.tickCount);
                    this.holdItemAnimationState.stop();
                    this.holdItemFlyAnimationState.startIfStopped(this.tickCount);
                    this.idleAnimationState.stop();
                }

            } else {
                if (itemstack1.isEmpty()) {
                    this.idleAnimationState.startIfStopped(this.tickCount);
                    this.holdItemAnimationState.stop();
                    this.flyAnimationState.stop();
                    this.holdItemFlyAnimationState.stop();
                    this.idleAnimationState.stop();

                } else {
                    this.flyAnimationState.stop();
                    this.holdItemAnimationState.startIfStopped(this.tickCount);
                    this.holdItemFlyAnimationState.stop();
                    this.idleAnimationState.stop();

                }
            }
        }


    }

    //检测是否在飞行
    public boolean isFlying() {
        return !this.onGround;
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }

    @Override
    public SimpleContainer getInventory() {
        return this.inventory;
    }

    protected void pickUpItem(ItemEntity pItemEntity) {
        InventoryCarrier.pickUpItem(this, this, pItemEntity);
    }

    class PlaceSigilGoal extends MoveToBlockGoal {
        EntityBetty betty;

        @Override
        public boolean canUse() {
            return this.betty.isTame() && this.betty.level.getBrightness(LightLayer.BLOCK, this.betty.getOnPos()) < 7&&this.findNearestBlock();
        }


        public PlaceSigilGoal(EntityBetty pMob, double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
            super(pMob, pSpeedModifier, pSearchRange, pVerticalSearchRange);
            this.betty = pMob;
        }

        @Override
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            //亮度小于7的地方
            if (pLevel.getBrightness(LightLayer.BLOCK, pPos) < 7) {
                //pPos的四周和下方是SIGIL_PLACEABLE标签
                if (pLevel.getBlockState(pPos).is(Blocks.AIR) &&
                        (pLevel.getBlockState(pPos.north()).is(TagsInit.Blocks.SIGIL_PLACEABLE) ||
                                pLevel.getBlockState(pPos.south()).is(TagsInit.Blocks.SIGIL_PLACEABLE) ||
                                pLevel.getBlockState(pPos.east()).is(TagsInit.Blocks.SIGIL_PLACEABLE) ||
                                pLevel.getBlockState(pPos.west()).is(TagsInit.Blocks.SIGIL_PLACEABLE) ||
                                pLevel.getBlockState(pPos.above()).is(TagsInit.Blocks.SIGIL_PLACEABLE) ||
                                pLevel.getBlockState(pPos.below()).is(TagsInit.Blocks.SIGIL_PLACEABLE))) {
                    return true;
                }
                return false;
            }
            return false;

        }

        @Override
        public void tick() {
            super.tick();
            if (this.isReachedTarget()) {
                if (this.mob.getMainHandItem().getItem() == Items.AIR) {
                    this.mob.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(RegistrationInit.BLOCK_SIGIL.get()));
                    //放置sigil
                    this.mob.level.setBlockAndUpdate(this.blockPos, RegistrationInit.BLOCK_SIGIL.get().defaultBlockState());
                    this.mob.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    this.stop();
                }
            }

        }
    }

    class LightSigilGoal extends MoveToBlockGoal {
        EntityBetty betty;

        @Override
        public boolean canUse() {
            return this.betty.isTame()&& this.betty.level.getBrightness(LightLayer.BLOCK, this.betty.getOnPos()) < 7 &&this.findNearestBlock();
        }

        public LightSigilGoal(EntityBetty pMob, double pSpeedModifier, int pSearchRange, int pVerticalSearchRange) {
            super(pMob, pSpeedModifier, pSearchRange, pVerticalSearchRange);
            this.betty = pMob;
        }



        @Override
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {
            //亮度小于7的地方
            if (pLevel.getBrightness(LightLayer.BLOCK, pPos) < 7) {
                //pPos的四周和下方是SIGIL_PLACEABLE标签
                if (pLevel.getBlockState(pPos).is(RegistrationInit.BLOCK_LOSE_SIGIL.get())) {
                    return true;
                }
                return false;
            }
            return false;

        }

        @Override
        public void tick() {
            super.tick();
            if (this.isReachedTarget()) {
                if (this.mob.getMainHandItem().getItem() == Items.AIR) {
                    this.mob.setItemInHand(InteractionHand.MAIN_HAND, new ItemStack(RegistrationInit.BLOCK_SIGIL.get()));
                    //放置sigil
                    this.mob.level.setBlockAndUpdate(this.blockPos, RegistrationInit.BLOCK_SIGIL.get().defaultBlockState());
                    this.mob.setItemInHand(InteractionHand.MAIN_HAND, ItemStack.EMPTY);
                    this.stop();
                }
            }

        }
    }

    class DacinglGoal extends MoveToBlockGoal {
        EntityBetty betty;

        public DacinglGoal(EntityBetty pMob, double pSpeedModifier, int pSearchRange) {
            super(pMob, pSpeedModifier, pSearchRange);
            this.betty = pMob;
        }

        @Override
        public boolean canUse() {
            return this.findNearestBlock();
        }


        @Override
        protected boolean isValidTarget(LevelReader pLevel, BlockPos pPos) {

            BlockState blockState = pLevel.getBlockState(blockPos.below());
            if (blockState.is(Blocks.CANDLE_CAKE)) {
                return true;
            }
            return false;

        }

        @Override
        public void start() {
            super.start();
            this.betty.setDancing(true);
        }

        @Override
        public void stop() {
            super.stop();
            this.betty.setDancing(false);
        }
    }

    class BettyFlyingGoal extends WaterAvoidingRandomFlyingGoal {
        EntityBetty betty;

        public BettyFlyingGoal(PathfinderMob p_25981_, double p_25982_) {
            super(p_25981_, p_25982_);
            this.betty = (EntityBetty) p_25981_;
        }


//      hh
    }
}
