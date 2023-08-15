package com.mnsfhxy.johnny_s_biological_notes.Item;


import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUtils;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ConcretePowderBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.RegistryObject;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Objects;

public class ItemGlueBottle extends Item {

    public ItemGlueBottle(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        try {
            BlockState blockState = pContext.getLevel().getBlockState(pContext.getClickedPos());
//            ItemStack itemstack = pContext.getItemInHand();
            if (blockState.is(Tags.Blocks.SAND) || blockState.is(Tags.Blocks.SAND_RED) || blockState.is(Tags.Blocks.GRAVEL) || blockState.getBlock() instanceof ConcretePowderBlock) {
                String name = blockState.getBlock().getDescriptionId().toUpperCase(Locale.ROOT).replace("BLOCK.MINECRAFT.", "BLOCK_GLUED_");
                Class<?> blocks = RegistrationInit.class;
                Field field = blocks.getDeclaredField(name);
                Block block = (Block) ((RegistryObject<?>) field.get(null)).get();
                pContext.getLevel().setBlock(pContext.getClickedPos(), block.defaultBlockState(), 2);
                ItemStack filledResult =new ItemStack(Items.GLASS_BOTTLE);
                Objects.requireNonNull(pContext.getPlayer()).setItemInHand(pContext.getHand(), filledResult);
                return InteractionResult.sidedSuccess(pContext.getLevel().isClientSide);

            } else {
                return InteractionResult.PASS;
            }
        } catch (Exception e) {
            return InteractionResult.PASS;

        }
    }
}




