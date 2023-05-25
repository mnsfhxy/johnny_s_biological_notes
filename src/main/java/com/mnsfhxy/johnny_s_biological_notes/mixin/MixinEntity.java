package com.mnsfhxy.johnny_s_biological_notes.mixin;

import com.mnsfhxy.johnny_s_biological_notes.init.PotionsInit;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.extensions.IForgeLivingEntity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(Entity.class)
public abstract class MixinEntity implements IForgeLivingEntity {
    @Override
    public boolean canStartSwimming() {
        if (((Entity) (Object) this) instanceof Player) {
            if (((Player) (Object) this).hasEffect(PotionsInit.FEAR_WATER.get())) return false;
        }
        return !this.getEyeInFluidType().isAir() && this.canSwimInFluidType(this.getEyeInFluidType());
    }

}
