package com.mnsfhxy.johnny_s_biological_notes.mixin;

import com.mnsfhxy.johnny_s_biological_notes.init.PotionsInit;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.player.Player;
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

    @Override
    public void jumpInFluid(FluidType type) {
        if (((LivingEntity) (Object) this).hasEffect(PotionsInit.FEAR_WATER.get()) && (!(((LivingEntity) (Object) this) instanceof Player))) {
        } else {
            self().setDeltaMovement(self().getDeltaMovement().add(0.0D, (double) 0.04F * self().getAttributeValue(ForgeMod.SWIM_SPEED.get()), 0.0D));
        }
    }

//    @Override
//    public boolean canDrownInFluidType(FluidType type) {
//        if (type == ForgeMod.WATER_TYPE.get()) {
//            if(((LivingEntity) (Object) this)).
//            return !self().canBreatheUnderwater();
//        }
//        return type.canDrownIn(self());
//    }
}

