package com.mnsfhxy.johnny_s_biological_notes.util;

import net.minecraft.world.entity.item.ItemEntity;

import java.util.Random;

public class UtilItem {
    static Random random = new java.util.Random();

    public static void setDeltaMovement(ItemEntity itemEntity) {
        if (itemEntity != null)
            itemEntity.setDeltaMovement(itemEntity.getDeltaMovement().add((double) ((random.nextFloat() - random.nextFloat()) * 0.1F), (double) (random.nextFloat() * 0.05F), (double) ((random.nextFloat() - random.nextFloat()) * 0.1F)));
    }

}
