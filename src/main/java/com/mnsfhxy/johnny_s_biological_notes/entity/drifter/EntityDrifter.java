package com.mnsfhxy.johnny_s_biological_notes.entity.drifter;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class EntityDrifter extends AbstractVillager {
    public EntityDrifter(EntityType<? extends AbstractVillager> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public static AttributeSupplier.Builder prepareAttributes() {
        AttributeSupplier.Builder builder= Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, 0.35F);
        builder = builder.add(Attributes.MAX_HEALTH, 20);
        builder = builder.add(Attributes.ARMOR, 0);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
        builder = builder.add(Attributes.FOLLOW_RANGE, 64);
        return builder;
    }

    @Override
    protected void rewardTradeXp(MerchantOffer pOffer) {

    }

    @Override
    protected void updateTrades() {

    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel pLevel, AgeableMob pOtherParent) {
        return null;
    }
}
