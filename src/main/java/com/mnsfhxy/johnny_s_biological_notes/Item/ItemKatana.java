package com.mnsfhxy.johnny_s_biological_notes.Item;

import com.mnsfhxy.johnny_s_biological_notes.capability.spirit.PlayerSpiritProvider;
import com.mnsfhxy.johnny_s_biological_notes.capability.spirit.SpiritOverlay;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.TagsInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class ItemKatana extends SwordItem {
    public ItemKatana(final Tier tier, int pAttackDamageModifier, float pAttackSpeedModifier) {
        super(tier, pAttackDamageModifier, pAttackSpeedModifier, new Item.Properties().tab(CreativeModeTab.TAB_COMBAT));
    }
    public ItemKatana(final Tier tier, int pAttackDamageModifier, float pAttackSpeedModifier,Item.Properties pProperties) {
        super(tier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);
    }
    @Override
    public boolean hurtEnemy(ItemStack pStack, LivingEntity pTarget, LivingEntity pAttacker) {
        AtomicReference<Float> amount = new AtomicReference<>(0F);
        AtomicBoolean isMax = new AtomicBoolean(false);
        pAttacker.getCapability(PlayerSpiritProvider.PLAYER_SPIRIT).ifPresent(s -> {
            if (s.isMax()) {
                isMax.set(true);
                double d0 = (double)(-Mth.sin(pAttacker.getYRot() * ((float)Math.PI / 180F)));
                double d1 = (double)Mth.cos(pAttacker.getYRot() * ((float)Math.PI / 180F));
                if (pAttacker.level instanceof ServerLevel) {
                    ((ServerLevel)pAttacker.level).sendParticles(RegistrationInit.CHOP_PARTICLE.get(), pAttacker.getX() + d0, pAttacker.getY(0.5D), pAttacker.getZ() + d1, 0, d0, 0.0D, d1, 0.0D);
                }
                SpiritOverlay.HUD = SpiritOverlay.SPIRIT_FIGHT;
                amount.updateAndGet(v -> (float) (v + (5 - pTarget.getArrowCount())));
                s.cleanSpirit();
            } else {
                s.addSpirit();
            }
        });
        if (pAttacker instanceof Player) {
            if (!isMax.get())
            { SpiritOverlay.updateHUD((Player) pAttacker);}
            pTarget.hurt(DamageSource.playerAttack((Player) pAttacker), amount.get());

        } else {
            pTarget.hurt(DamageSource.mobAttack(pAttacker), amount.get());
        }
        Thread thread = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(500); // 500毫秒 = 0.5秒
                    SpiritOverlay.updateHUD((Player) pAttacker);
                    // 执行延迟后的代码
                } catch (InterruptedException e) {
                    // 处理异常
                }
            }
        });

        thread.start(); // 启动新线程
        pStack.hurtAndBreak(1, pAttacker, (p_43296_) -> {
            p_43296_.broadcastBreakEvent(EquipmentSlot.MAINHAND);
        });
        return true;

    }
    @Override
    public float getDestroySpeed(@NotNull ItemStack stack, @NotNull BlockState state) {
        float speed = super.getDestroySpeed(stack, state);
        if (speed == 1 && state.is(TagsInit.Blocks.MINEABLE_WITH_KATANA)) {
            speed=15F;
        }
        return speed;
    }

}
