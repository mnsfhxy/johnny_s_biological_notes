package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.TagsInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.world.entity.EntityType;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

public class ModEntityTypeTags extends EntityTypeTagsProvider {
    public ModEntityTypeTags(DataGenerator pGenerator, String modId, @Nullable ExistingFileHelper existingFileHelper) {
        super(pGenerator, modId, existingFileHelper);
    }

    @Override
    protected void addTags() {
//        Set<EntityType<?>> hydrobios=new HashSet<>();
//        hydrobios.add(Squid)
//        ForgeRegistries.ENTITY_TYPES.getValues().forEach(x->{
//
//        });

//        ForgeRegistries.ENTITY_TYPES.getValue()
        tag(TagsInit.Entities.DRIFTER_FAVORABILITY_ADD_QUARTER).add(
                EntityType.ZOMBIE,
                EntityType.ZOMBIE_VILLAGER,
                EntityType.WITHER_SKELETON,
                EntityType.WITCH,
                EntityType.VINDICATOR,
                EntityType.VEX,
                EntityType.STRAY,
                EntityType.SLIME,
                EntityType.SKELETON,
                EntityType.SHULKER,
                EntityType.PHANTOM,
                EntityType.MAGMA_CUBE,
                EntityType.HUSK,
                EntityType.HOGLIN,
                EntityType.GHAST,
                EntityType.ENDERMITE,
                EntityType.ELDER_GUARDIAN,
                EntityType.DROWNED,
                EntityType.BLAZE,
                EntityType.PIGLIN_BRUTE
                );
        tag(TagsInit.Entities.DRIFTER_FAVORABILITY_ADD_ONE).add(
                EntityType.GUARDIAN,
                EntityType.RAVAGER,
                EntityType.PILLAGER,
                EntityType.EVOKER,
                EntityType.ILLUSIONER
        );
        tag(TagsInit.Entities.DRIFTER_ATTACKABLE).add(
                EntityType.ZOMBIE,
                EntityType.ZOMBIE_VILLAGER,
                EntityType.WITHER_SKELETON,
                EntityType.WITCH,
                EntityType.VINDICATOR,
                EntityType.VEX,
                EntityType.STRAY,
                EntityType.SLIME,
                EntityType.SKELETON,
                EntityType.SHULKER,
                EntityType.PHANTOM,
                EntityType.MAGMA_CUBE,
                EntityType.HUSK,
                EntityType.HOGLIN,
                EntityType.GHAST,
                EntityType.ENDERMITE,
                EntityType.ELDER_GUARDIAN,
                EntityType.DROWNED,
                EntityType.BLAZE,
                EntityType.PIGLIN_BRUTE,
                EntityType.GUARDIAN,
                EntityType.RAVAGER,
                EntityType.PILLAGER,
                EntityType.EVOKER,
                EntityType.ILLUSIONER,
                EntityType.PLAYER
        );
    }
}
