package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.TagsInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

/*
*
* Block Tag:
Tag 的作用类似于矿物辞典，但又不完全相同。

由于新版本的变化，挖掘的工具由 tag 进行控制，此处的 MINEABLE_WITH_PICKAXE 的意思就是这个方块可以由镐子挖，NEEDS_IRON_TOOL 的意思则是挖掘等级至少在铁以上。
*
* */
public class ModBlockTags extends BlockTagsProvider {

    public ModBlockTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, JohnnySBiologicalNotes.MODID, helper);
    }

    @Override
    protected void addTags() {
//        tag(BlockTags.MINEABLE_WITH_PICKAXE)
//                .add(Registration.FIRST_BLOCK.get());
//        tag(BlockTags.NEEDS_IRON_TOOL)
//                .add(Registration.FIRST_BLOCK.get());
        tag(TagsInit.Blocks.MINEABLE_WITH_KATANA).add(Blocks.BAMBOO, Blocks.COCOA);
        tag(TagsInit.Blocks.SAFE_ON_JELLY).add(
                Blocks.WATER,
                Blocks.LAVA,
                Blocks.OAK_LEAVES,
                Blocks.SPRUCE_LEAVES,
                Blocks.BIRCH_LEAVES,
                Blocks.JUNGLE_LEAVES,
                Blocks.ACACIA_LEAVES,
                Blocks.DARK_OAK_LEAVES,
                Blocks.MANGROVE_LEAVES,
                Blocks.AZALEA_LEAVES,
                Blocks.FLOWERING_AZALEA_LEAVES,
                Blocks.WHITE_WOOL,
                Blocks.ORANGE_WOOL,
                Blocks.MAGENTA_WOOL,
                Blocks.LIGHT_BLUE_WOOL,
                Blocks.YELLOW_WOOL,
                Blocks.LIME_WOOL,
                Blocks.PINK_WOOL,
                Blocks.GRAY_WOOL,
                Blocks.LIGHT_GRAY_WOOL,
                Blocks.CYAN_WOOL,
                Blocks.PURPLE_WOOL,
                Blocks.BLUE_WOOL,
                Blocks.BROWN_WOOL,
                Blocks.GREEN_WOOL,
                Blocks.RED_WOOL,
                Blocks.BLACK_WOOL,
                RegistrationInit.BLOCK_JELLY.get(),
                Blocks.SPONGE,
                Blocks.WET_SPONGE,
                Blocks.SEA_PICKLE,
                Blocks.CANDLE,
                Blocks.TURTLE_EGG);


    }

    @Override
    public String getName() {
        return "JohnnySBiologicalNotes Tags";
    }
}
