package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModels extends ItemModelProvider {

    public ModItemModels(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, JohnnySBiologicalNotes.MODID, existingFileHelper);
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
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY.getId().getPath(), modLoc("block/jelly_block"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_BLACK.getId().getPath(), modLoc("block/jelly_block_black"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_BLUE.getId().getPath(), modLoc( "block/jelly_block_blue"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_BROWN.getId().getPath(), modLoc( "block/jelly_block_brown"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_CYAN.getId().getPath(), modLoc( "block/jelly_block_cyan"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_GRAY.getId().getPath(), modLoc( "block/jelly_block_gray"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_GREEN.getId().getPath(), modLoc("block/jelly_block_green"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_LIGHTBLUE.getId().getPath(), modLoc("block/jelly_block_lightblue"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_LIGHTGRAY.getId().getPath(), modLoc("block/jelly_block_lightgray"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_LIGHTGREEN.getId().getPath(), modLoc("block/jelly_block_lightgreen"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_MAGENTA.getId().getPath(), modLoc("block/jelly_block_magenta"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_ORANGE.getId().getPath(), modLoc("block/jelly_block_orange"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_PINK.getId().getPath(), modLoc("block/jelly_block_pink"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_PURPLE.getId().getPath(), modLoc("block/jelly_block_purple"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_RED.getId().getPath(), modLoc("block/jelly_block_red"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_WHITE.getId().getPath(), modLoc("block/jelly_block_white"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_JELLY_YELLOW.getId().getPath(), modLoc("block/jelly_block_yellow"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_GLUED_SAND.getId().getPath(), mcLoc("block/sand"));
        withExistingParent(RegistrationInit.BLOCK_ITEM_GLUED_RED_SAND.getId().getPath(), mcLoc("block/red_sand"));

        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_WHITE_CONCRETE_POWDER     .getId().getPath(), mcLoc("block/white_concrete_powder"     ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_ORANGE_CONCRETE_POWDER    .getId().getPath(), mcLoc("block/orange_concrete_powder"    ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_MAGENTA_CONCRETE_POWDER   .getId().getPath(), mcLoc("block/magenta_concrete_powder"   ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_LIGHT_BLUE_CONCRETE_POWDER.getId().getPath(), mcLoc("block/light_blue_concrete_powder"));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_YELLOW_CONCRETE_POWDER    .getId().getPath(), mcLoc("block/yellow_concrete_powder"    ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_LIME_CONCRETE_POWDER      .getId().getPath(), mcLoc("block/lime_concrete_powder"      ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_PINK_CONCRETE_POWDER      .getId().getPath(), mcLoc("block/pink_concrete_powder"      ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_GRAY_CONCRETE_POWDER      .getId().getPath(), mcLoc("block/gray_concrete_powder"      ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_LIGHT_GRAY_CONCRETE_POWDER.getId().getPath(), mcLoc("block/light_gray_concrete_powder"));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_CYAN_CONCRETE_POWDER      .getId().getPath(), mcLoc("block/cyan_concrete_powder"      ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_PURPLE_CONCRETE_POWDER    .getId().getPath(), mcLoc("block/purple_concrete_powder"    ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_BLUE_CONCRETE_POWDER      .getId().getPath(), mcLoc("block/blue_concrete_powder"      ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_BROWN_CONCRETE_POWDER     .getId().getPath(), mcLoc("block/brown_concrete_powder"     ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_GREEN_CONCRETE_POWDER     .getId().getPath(), mcLoc("block/green_concrete_powder"     ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_RED_CONCRETE_POWDER       .getId().getPath(), mcLoc("block/red_concrete_powder"       ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_GLUED_BLACK_CONCRETE_POWDER     .getId().getPath(), mcLoc("block/black_concrete_powder"     ));
        withExistingParent(RegistrationInit. BLOCK_ITEM_ECO_BOTTLE                      .getId().getPath(), modLoc("block/eco_bottle_ceiling"     ));
















        withExistingParent(RegistrationInit.TRIDACNA_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        withExistingParent(RegistrationInit.JELLY_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(RegistrationInit.CRAB_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(RegistrationInit.PEEPER_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        withExistingParent(RegistrationInit.DRIFTER_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));
        singleTexture(RegistrationInit.CRAB_SHELL.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/crab_shell"));
        singleTexture(RegistrationInit.CRAB_MEAT.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/crab_meat"));
        singleTexture(RegistrationInit.COOKED_CRAB_MEAT.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/cooked_crab_meat"));
        singleTexture(RegistrationInit.CRAB_BUCKET.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/crab_bucket"));
//        singleTexture(Po
//        tionsInit.FEAR_WATER_POTION.getId().getPath(), mcLoc("item/generated"),"layer0",modLoc("item/fear_water_glass"));
        singleTexture(RegistrationInit.WOOD_BLADE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/wood_blade"));
        singleTexture(RegistrationInit.GOLD_BLADE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/gold_blade"));
        singleTexture(RegistrationInit.DIAMOND_BLADE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/diamond_blade"));
        singleTexture(RegistrationInit.IRON_BLADE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/iron_blade"));
        singleTexture(RegistrationInit.NETHERITE_BLADE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/netherite_blade"));
        singleTexture(RegistrationInit.STONE_BLADE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/stone_blade"));

        singleTexture(RegistrationInit.WOOD_KATANA.getId().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/wood_katana"));
        singleTexture(RegistrationInit.GOLD_KATANA.getId().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/gold_katana"));
        singleTexture(RegistrationInit.DIAMOND_KATANA.getId().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/diamond_katana"));
        singleTexture(RegistrationInit.IRON_KATANA.getId().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/iron_katana"));
        singleTexture(RegistrationInit.NETHERITE_KATANA.getId().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/netherite_katana"));
        singleTexture(RegistrationInit.STONE_KATANA.getId().getPath(), mcLoc("item/handheld"), "layer0", modLoc("item/stone_katana"));
        singleTexture(RegistrationInit.FORGED_PLATE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/forged_plate"));
        singleTexture(RegistrationInit.BLOCK_ITEM_JELLY_EMBRYO.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_embryo_item"));


        singleTexture(RegistrationInit.ITEM_JELLY.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly"));
        singleTexture(RegistrationInit.ITEM_JELLY_BLACK.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_black"));
        singleTexture(RegistrationInit.ITEM_JELLY_BLUE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc( "item/jelly_blue"));
        singleTexture(RegistrationInit.ITEM_JELLY_BROWN.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc( "item/jelly_brown"));
        singleTexture(RegistrationInit.ITEM_JELLY_CYAN.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc( "item/jelly_cyan"));
        singleTexture(RegistrationInit.ITEM_JELLY_GRAY.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc( "item/jelly_gray"));
        singleTexture(RegistrationInit.ITEM_JELLY_GREEN.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_green"));
        singleTexture(RegistrationInit.ITEM_JELLY_LIGHTBLUE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_lightblue"));
        singleTexture(RegistrationInit.ITEM_JELLY_LIGHTGRAY.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_lightgray"));
        singleTexture(RegistrationInit.ITEM_JELLY_LIGHTGREEN.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_lightgreen"));
        singleTexture(RegistrationInit.ITEM_JELLY_MAGENTA.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_magenta"));
        singleTexture(RegistrationInit.ITEM_JELLY_ORANGE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_orange"));
        singleTexture(RegistrationInit.ITEM_JELLY_PINK.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_pink"));
        singleTexture(RegistrationInit.ITEM_JELLY_PURPLE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_purple"));
        singleTexture(RegistrationInit.ITEM_JELLY_RED.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_red"));
        singleTexture(RegistrationInit.ITEM_JELLY_WHITE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_white"));
        singleTexture(RegistrationInit.ITEM_JELLY_YELLOW.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_yellow"));
        singleTexture(RegistrationInit.SEMI_SOLIDFIED_PROTEIN.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/semi_solidfied_protein"));
        singleTexture(RegistrationInit.SOLIDFIED_PROTEIN.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/solidfied_protein"));
        singleTexture(RegistrationInit.JELLY_PLATTER.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/jelly_platter"));
        singleTexture(RegistrationInit.GLUE_BOTTLE.getId().getPath(), mcLoc("item/generated"), "layer0", modLoc("item/glue_bottle"));
    }
}
