package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagsInit {
    public static void init() {
//        Items.init();
        Blocks.init();
//        Entities.init();
//        BlockEntities.init();
    }


    public static class Blocks {

        private static void init() {
        }

        private Blocks() {
        }

        public static final TagKey<Block> MINEABLE_WITH_KATANA = tag("mineable/katana");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(JohnnySBiologicalNotes.MODID, name));
        }
    }
}
