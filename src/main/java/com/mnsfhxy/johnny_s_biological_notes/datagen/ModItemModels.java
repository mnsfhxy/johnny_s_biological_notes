package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.PotionsInit;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModels extends ItemModelProvider {

    public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator,JohnnySBiologicalNotes.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
//        withExistingParent(Registration.MYSTERIOUS_ORE_OVERWORLD_ITEM.getId().getPath(), modLoc("block/mysterious_ore_overworld"));
//        withExistingParent(Registration.MYSTERIOUS_ORE_NETHER_ITEM.getId().getPath(), modLoc("block/mysterious_ore_nether"));
//        withExistingParent(Registration.MYSTERIOUS_ORE_END_ITEM.getId().getPath(), modLoc("block/mysterious_ore_end"));
//        withExistingParent(Registration.MYSTERIOUS_ORE_DEEPSLATE_ITEM.getId().getPath(), modLoc("block/mysterious_ore_deepslate"));
//
//        withExistingParent(Registration.GENERATOR_ITEM.getId().getPath(), modLoc("block/generator"));
//        withExistingParent(Registration.POWERGEN_ITEM.getId().getPath(), modLoc("block/powergen/main"));
//        withExistingParent(Registration.PORTAL_ITEM.getId().getPath(), modLoc("block/portal"));
//
//        withExistingParent(Registration.THIEF_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
//
//        singleTexture(Registration.RAW_MYSTERIOUS_CHUNK.getId().getPath(),
//                mcLoc("item/generated"),
//                "layer0", modLoc("item/raw_mysterious_chunk"));
//        singleTexture(Registration.MYSTERIOUS_INGOT.getId().getPath(),
//                mcLoc("item/generated"),
//                "layer0", modLoc("item/mysterious_ingot"));
        withExistingParent(RegistrationInit.CRAB_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        singleTexture(RegistrationInit.CRAB_SHELL.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/crab_shell"));
        singleTexture(RegistrationInit.CRAB_MEAT.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/crab_meat"));
        singleTexture(RegistrationInit.COOKED_CRAB_MEAT.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/cooked_crab_meat"));
        singleTexture(RegistrationInit.CRAB_BUCKET.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/crab_bucket"));
//        singleTexture(PotionsInit.FEAR_WATER_POTION.getId().getPath(), mcLoc("item/generated"),"layer0",modLoc("item/fear_water_glass"));
    }
}
