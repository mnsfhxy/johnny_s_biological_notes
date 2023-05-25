package com.mnsfhxy.johnny_s_biological_notes.datagen;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.world.level.biome.Biome;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModBiomeTags extends TagsProvider<Biome> {

    public ModBiomeTags(DataGenerator generator, ExistingFileHelper helper) {
        super(generator, BuiltinRegistries.BIOME, JohnnySBiologicalNotes.MODID, helper);
    }

    @Override
    protected void addTags() {
//        ForgeRegistries.BIOMES.getValues().forEach(biome -> {
//            tag(Registration.HAS_ORE).add(TagEntry.tag(BiomeTags.IS_OVERWORLD.location()));
//            tag(Registration.HAS_ORE).add(TagEntry.tag(BiomeTags.IS_NETHER.location()));
//            tag(Registration.HAS_ORE).add(TagEntry.tag(BiomeTags.IS_END.location()));
//            tag(Registration.HAS_PORTAL).add(biome);
//            tag(Registration.HAS_THIEFDEN).add(biome);
//        });
    }

    @Override
    public String getName() {
        return "JohnnySBiologicalNotes Tags";
    }
}
