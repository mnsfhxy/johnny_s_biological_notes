package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
//https://github.com/McJty/TutorialV3/blob/1.19/src/main/java/com/example/tutorialv3/datagen/
//    json 会被生成到“generated”文件夹，不要手动更改里面的内容，因为每次运行 runData 都会覆盖里面的内容。
@Mod.EventBusSubscriber(modid = JohnnySBiologicalNotes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {

    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        generator.addProvider(event.includeServer(), new ModRecipes(generator));
        generator.addProvider(event.includeServer(), new ModLootTables(generator));
        ModBlockTags blockTags = new ModBlockTags(generator, event.getExistingFileHelper());
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(), new ModItemTags(generator, blockTags, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(),new ModEntityTypeTags(generator,JohnnySBiologicalNotes.MODID,event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new ModBiomeTags(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeServer(), new ModStructureSetTags(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new ModBlockStates(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new ModItemModels(generator, event.getExistingFileHelper()));
        generator.addProvider(event.includeClient(), new ModLanguageProvider(generator, "en_us"));
        generator.addProvider(event.includeClient(), new ModLanguageZhProvider(generator, "zh_cn"));

    }
}
