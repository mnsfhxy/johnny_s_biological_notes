package com.mnsfhxy.johnny_s_biological_notes.effect;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.Dolphin;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraftforge.common.Tags;

import java.util.function.Predicate;

public class EffectVulnusRecover extends MobEffect {
    public EffectVulnusRecover(MobEffectCategory pCategory, int pColor) {
        super(pCategory, pColor);
    }

    Predicate<LivingEntity> canEffect = (p) -> {
        if (p.getType().is(Tags.EntityTypes.BOSSES)) {
            return false;
        }
        return true;
    };

    @Override
    public void applyEffectTick(LivingEntity pLivingEntity, int pAmplifier) {
        if (canEffect.test(pLivingEntity)) {
            if(pLivingEntity.getHealth()<pLivingEntity.getMaxHealth()){
                pLivingEntity.setHealth(Math.min(pLivingEntity.getHealth()+3+pAmplifier,pLivingEntity.getMaxHealth()));
                pLivingEntity.removeEffect(this);
            }

        }
        super.applyEffectTick(pLivingEntity, pAmplifier);


    }

    public boolean isDurationEffectTick(int duration, int amplifier) {
        return duration>0;
    }

    @Override
    public String getDescriptionId() {
        return JohnnySBiologicalNotes.MODID + ".potion.vulnus_recover";
    }
}
