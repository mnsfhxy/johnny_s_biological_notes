package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
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
    }

    @Override
    public String getName() {
        return "JohnnySBiologicalNotes Tags";
    }
}
