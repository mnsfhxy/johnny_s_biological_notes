package com.mnsfhxy.johnny_s_biological_notes.effect;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.WaterAnimal;

import java.util.function.Predicate;

public class EffectFearWater extends MobEffect {
    public EffectFearWater(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }
    Predicate<LivingEntity> canFearWaterEffect = (p) -> {
        if (p instanceof WaterAnimal && !(p instanceof Dolphin)) {
            return false;
        }
        return true;
    };
    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
    if(canFearWaterEffect.test(pLivingEntity)){
        pLivingEntity.setSwimming(false);
        if(pLivingEntity.getDeltaMovement().x > 0|| pLivingEntity.getDeltaMovement().z> 0){
            pLivingEntity.setDeltaMovement( pLivingEntity.getDeltaMovement().multiply(0, 1, 0));
        }
    }
        super.applyEffectTick(pLivingEntity, pAmplifier);
    }

    @Override
    public String getDescriptionId() {
        return JohnnySBiologicalNotes.MODID+".potion.fear_water";
    }
}
