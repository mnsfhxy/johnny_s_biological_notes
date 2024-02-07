package com.mnsfhxy.johnny_s_biological_notes.mixin;

import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.EntityBeluga;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.PolarBear;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.function.Predicate;

@Mixin(PolarBear.class)
public class MixinPolarBear {
    @Inject(method = "registerGoals", at = @At("TAIL"))
    private void onRegisterGoals(CallbackInfo ci) {
        // 这里的 "this" 指的是 PolarBearEntity 实例
        ((PolarBear)(Object)this).targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(((PolarBear)(Object)this), EntityBeluga.class, 10, true, true, (Predicate<LivingEntity>)null));
    }
}
