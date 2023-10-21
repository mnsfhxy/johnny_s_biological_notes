package com.mnsfhxy.johnny_s_biological_notes.entity.loiter;

import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.EntityDrifter;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EntityLoiter extends Monster {
    private static final float MOVEMENT_SPEED = 0.5F;
    private static final EntityDataAccessor<Boolean> SOUL = SynchedEntityData.defineId(EntityDrifter.class, EntityDataSerializers.BOOLEAN);

    public EntityLoiter(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }
    public static AttributeSupplier.Builder prepareAttributes() {
        AttributeSupplier.Builder builder = Mob.createMobAttributes();
        builder = builder.add(Attributes.MOVEMENT_SPEED, MOVEMENT_SPEED);
        builder = builder.add(Attributes.MAX_HEALTH, 25);
        builder = builder.add(Attributes.ATTACK_DAMAGE, 0);
        return builder;
    }

    @Override
    protected void defineSynchedData() {
        super.defineSynchedData();
        this.entityData.define(SOUL, Boolean.FALSE);
    }

    protected boolean isSunSensitive() {
        return true;
    }
    public void aiStep() {
        if (this.isAlive()) {
            if ( this.isSunSensitive() && this.isSunBurnTick()) {
                    this.setSecondsOnFire(8);
                }
            }
        super.aiStep();
        }


    }

