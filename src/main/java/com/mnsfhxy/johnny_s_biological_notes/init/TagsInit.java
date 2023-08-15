package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class TagsInit {
    public static void init() {
//        Items.init();
        Blocks.init();
        Entities.init();
//        BlockEntities.init();
    }


    public static class Blocks {

        private static void init() {
        }

        private Blocks() {
        }

        public static final TagKey<Block> MINEABLE_WITH_KATANA = tag("mineable/katana");
        public static final TagKey<Block> SAFE_ON_JELLY=tag("safe_on_jelly");
        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(JohnnySBiologicalNotes.MODID, name));
        }
    }
    public static class Entities {

        private static void init() {
        }

        private Entities() {
        }
        public static final TagKey<EntityType<?>> DRIFTER_FAVORABILITY_ADD_QUARTER = tag("drifter_favorability_add_quarter");
        public static final TagKey<EntityType<?>> DRIFTER_FAVORABILITY_ADD_ONE = tag("drifter_favorability_add_one");
        public static final TagKey<EntityType<?>> DRIFTER_ATTACKABLE = tag("drifter_attackable");

        private static TagKey<EntityType<?>> tag(String name) {
            return TagKey.create(Registry.ENTITY_TYPE_REGISTRY, new ResourceLocation(JohnnySBiologicalNotes.MODID, name));
        }
    }

}
