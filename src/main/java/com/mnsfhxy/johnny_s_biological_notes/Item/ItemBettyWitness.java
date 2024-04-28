package com.mnsfhxy.johnny_s_biological_notes.Item;

import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ItemBettyWitness extends Item {


    public ItemBettyWitness(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public boolean isFoil(ItemStack pStack) {
        return true;
    }
}
