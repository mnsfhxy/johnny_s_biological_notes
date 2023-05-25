package com.mnsfhxy.johnny_s_biological_notes.datagen;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.levelgen.structure.StructureSet;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModStructureSetTags  extends TagsProvider<StructureSet> {

    public ModStructureSetTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.STRUCTURE_SETS, JohnnySBiologicalNotes.MODID, helper);
    }

    @Override
    protected void addTags() {
//        tag(Registration.MYSTERIOUS_DIMENSION_STRUCTURE_SET)
//                .add(ResourceKey.create(BuiltinRegistries.STRUCTURE_SETS.key(), new ResourceLocation(TutorialV3.MODID, "portal")))
//                .add(ResourceKey.create(BuiltinRegistries.STRUCTURE_SETS.key(), new ResourceLocation(TutorialV3.MODID, "thiefden")))
//        ;
    }

    @Override
    public String getName() {
        return "JohnnySBiologicalNotes Tags";
    }
}
