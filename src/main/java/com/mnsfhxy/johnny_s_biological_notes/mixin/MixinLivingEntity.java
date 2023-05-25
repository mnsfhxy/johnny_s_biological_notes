package com.mnsfhxy.johnny_s_biological_notes.mixin;

import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.common.extensions.IForgeEntity;
import net.minecraftforge.common.extensions.IForgeLivingEntity;
import net.minecraftforge.fluids.FluidType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class MixinLivingEntity implements IForgeLivingEntity {

    //    @Override
//    public boolean canStartSwimming()
//    {
//        if (((Entity)(Object)this) instanceof LivingEntity) {
//            if (((LivingEntity)(Object)this).hasEffect(RegistrationInit.FEAR_WATER.get()))return false;
//        }
//        return !this.getEyeInFluidType().isAir() && this.canSwimInFluidType(this.getEyeInFluidType());
//    }
//    @Shadow
//    abstract boolean canBreatheUnderwater();
//
//    @Override
//    public boolean canDrownInFluidType(FluidType type) {
//
//        if (((LivingEntity) (Object) this).hasEffect(RegistrationInit.FEAR_WATER.get())) return false;
//
//        if (type == ForgeMod.WATER_TYPE.get()) return !this.canBreatheUnderwater();
//        return type.canDrownIn((LivingEntity) (Object) this);
//    }
    @Override
    public  void jumpInFluid(FluidType type)
    {
        if (!((LivingEntity) (Object) this).hasEffect(RegistrationInit.FEAR_WATER.get()))self().setDeltaMovement(self().getDeltaMovement().add(0.0D, (double)0.04F * self().getAttributeValue(ForgeMod.SWIM_SPEED.get()), 0.0D));
    }
}

