package com.mnsfhxy.johnny_s_biological_notes.entity.jelly.bubble;

import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import com.mojang.math.Vector3f;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class EntityJellyBubble extends Mob {
    public AnimationState bubbleAnimationState = new AnimationState();

    public EntityJellyBubble(EntityType<? extends Mob> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        if (level.isClientSide) bubbleAnimationState.start(this.tickCount);
        setNoGravity(true);
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        return builder;
    }



    @Override
    public void push(Entity pEntity) {
        if (!(pEntity instanceof EntityJelly||pEntity instanceof EntityJellyBubble)) pop();

    }


    @Override
    public void aiStep() {
        super.aiStep();
//        if (this.isAlive()) {
//            for (LivingEntity livingEntity : this.level.getEntitiesOfClass(LivingEntity.class, this.getBoundingBox(), (p)->!(p instanceof EntityJelly))) {
////                JohnnySBiologicalNotes.LOGGER.info("mob type:" + mob.getType().getDescription().getString());
//                this.pop();
//                break;
//            }
//        }
    }

    @Override
    public boolean isColliding(BlockPos pPos, BlockState pState) {
        return super.isColliding(pPos, pState);
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();

    }

    private void pop() {
//        playSound(AdditionsSounds.POP.get(), 1, 1);
        int i2 = random.nextInt(10);
        if (!level.isClientSide && i2 == 0) {
            BlockPos worldPosition = getOnPos().below();
            double rx = level.random.nextInt(2) / 3.0;
            double rz = level.random.nextInt(2) / 3.0;
            BlockPos spawnPos = new BlockPos(worldPosition.getX() + rx, worldPosition.getY(), worldPosition.getZ() + rz);
            EntityJelly entityJelly = RegistrationInit.JELLY.get().spawn((ServerLevel) level, (CompoundTag) null, (Component) null, (Player) null, spawnPos, MobSpawnType.EVENT, false, false);
            if (entityJelly != null) {
                entityJelly.restrictTo(worldPosition, 16);
            }

        }
        if(level.isClientSide)playSound(SoundInit.JELLY_BUBBLE_BROKEN.get(),1.0F,1.0F);
        discard();
    }

    @Override
    public boolean hurt(DamageSource pSource, float pAmount) {
        if (pSource.getEntity() instanceof Player) {
            int i = random.nextInt(100);
            if (i < 65) {
                spawnAtLocation(new ItemStack(RegistrationInit.SEMI_SOLIDFIED_PROTEIN.get()));
            } else {
                spawnAtLocation(new ItemStack(RegistrationInit.SOLIDFIED_PROTEIN.get()));

            }
        }
        pop();
        return super.hurt(pSource, pAmount);

    }

    @Override
    public void tick() {
        super.tick();
        if (this.tickCount > UtilLevel.TIME.SECOND.getTick() * 30) pop();
//        System.out.println(this.tickCount);
        xo = getX();
        yo = getY();
        zo = getZ();
        if (getY() >= level.getMaxBuildHeight()) {
            pop();
            return;
        }
        Vec3 motion = getDeltaMovement();
//        setDeltaMovement(motion.x(), Math.min(motion.y() * 1.02F, 0.2F), motion.z());
        setDeltaMovement(motion.x(), 0.005, motion.z());
        move(MoverType.SELF, getDeltaMovement());

//        motion = getDeltaMovement();
//        motion = motion.multiply(0.98, 0, 0.98);
//
//        if (onGround) {
//            motion = motion.multiply(0.7, 0, 0.7);
//        }
//        if (motion.y() == 0) {
//            motion = new Vec3(motion.x(), 0.04, motion.z());
//        }
//        setDeltaMovement(motion);
    }
}
