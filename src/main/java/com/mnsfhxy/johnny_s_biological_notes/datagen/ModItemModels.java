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
        withExistingParent(RegistrationInit.JELLY_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(RegistrationInit.CRAB_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(RegistrationInit.PEEPER_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(RegistrationInit.DRIFTER_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        singleTexture(RegistrationInit.CRAB_SHELL.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/crab_shell"));
        singleTexture(RegistrationInit.CRAB_MEAT.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/crab_meat"));
        singleTexture(RegistrationInit.COOKED_CRAB_MEAT.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/cooked_crab_meat"));
        singleTexture(RegistrationInit.CRAB_BUCKET.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/crab_bucket"));
//        singleTexture(Po
//        tionsInit.FEAR_WATER_POTION.getId().getPath(), mcLoc("item/generated"),"layer0",modLoc("item/fear_water_glass"));
        singleTexture(RegistrationInit.WOOD_BLADE.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/wood_blade"));
        singleTexture(RegistrationInit.GOLD_BLADE.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/gold_blade"));
        singleTexture(RegistrationInit.DIAMOND_BLADE.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/diamond_blade"));
        singleTexture(RegistrationInit.IRON_BLADE.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/iron_blade"));
        singleTexture(RegistrationInit.NETHERITE_BLADE.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/netherite_blade"));
        singleTexture(RegistrationInit.STONE_BLADE.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/stone_blade"));

        singleTexture(RegistrationInit.WOOD_KATANA.getId().getPath(),mcLoc("item/handheld"),"layer0",modLoc("item/wood_katana"));
        singleTexture(RegistrationInit.GOLD_KATANA.getId().getPath(),mcLoc("item/handheld"),"layer0",modLoc("item/gold_katana"));
        singleTexture(RegistrationInit.DIAMOND_KATANA.getId().getPath(),mcLoc("item/handheld"),"layer0",modLoc("item/diamond_katana"));
        singleTexture(RegistrationInit.IRON_KATANA.getId().getPath(),mcLoc("item/handheld"),"layer0",modLoc("item/iron_katana"));
        singleTexture(RegistrationInit.NETHERITE_KATANA.getId().getPath(),mcLoc("item/handheld"),"layer0",modLoc("item/netherite_katana"));
        singleTexture(RegistrationInit.STONE_KATANA.getId().getPath(),mcLoc("item/handheld"),"layer0",modLoc("item/stone_katana"));
        singleTexture(RegistrationInit.FORGED_PLATE.getId().getPath(),mcLoc("item/generated"),"layer0",modLoc("item/forged_plate"));
    }
}
