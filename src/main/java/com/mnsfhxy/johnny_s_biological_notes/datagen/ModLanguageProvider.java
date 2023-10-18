package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.ModInit;
import com.mnsfhxy.johnny_s_biological_notes.init.PotionsInit;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen, String locale) {
        super(gen, JohnnySBiologicalNotes.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + ModInit.TAB_NAME, "JohnnySBiologicalNotes");
        add(RegistrationInit.CRAB.get(), "Crab");
        add(RegistrationInit.CRAB_EGG.get(), "Crab Egg");
        add(RegistrationInit.CRAB_MEAT.get(), "Crab Meat");
        add(RegistrationInit.COOKED_CRAB_MEAT.get(), "Cooked Crab Meat");
        add(RegistrationInit.CRAB_BUCKET.get(), "Crab Bucket");
        add(RegistrationInit.CRAB.get() + ".RED", "Red Crab");
        add(RegistrationInit.CRAB.get() + ".BROWN", "Brown Crab");
        add(RegistrationInit.CRAB.get() + ".SPOTTED", "Spotted Crab");
        add(RegistrationInit.CRAB.get() + ".CYAN", "Cyan Crab");
        add(RegistrationInit.CRAB.get() + ".BLACK", "Black Crab");
        add(PotionsInit.FEAR_WATER.get(), "Fear Water");
        add("item.minecraft.lingering_potion.effect.fear_water_potion", "Lingering Fear Water Potion");
        add("item.minecraft.splash_potion.effect.fear_water_potion", "Splash Fear Water Potion");
        add("item.minecraft.potion.effect.fear_water_potion", "Fear Water Potion");
        add("item.minecraft.lingering_potion.effect.long_fear_water_potion", "Lingering Fear Water Potion");
        add("item.minecraft.splash_potion.effect.long_fear_water_potion", "Splash Fear Water Potion");
        add("item.minecraft.potion.effect.long_fear_water_potion", "Fear Water Potion");
        add("johnny_s_biological_notes.sound.subtitle.crab_bubble", "Crab Bubble");
        add("johnny_s_biological_notes.sound.subtitle.crab_drop_shell", "Crab Drop Shell");
        add("johnny_s_biological_notes.sound.subtitle.crab_hurt", "Crab Hurt");
        add("johnny_s_biological_notes.sound.subtitle.crab_talon", "Crab Talon");
        add("johnny_s_biological_notes.sound.subtitle.crab_walking", "Crab Walking");
        add("johnny_s_biological_notes.sound.subtitle.crab_dig", "Crab Dig");
        add(RegistrationInit.PEEPER.get(), "Peeper");
        add(RegistrationInit.PEEPER_EGG.get(), "Peeper Egg");
        add("johnny_s_biological_notes.sound.subtitle.peeper_death", "Peeper Death");
        add("johnny_s_biological_notes.sound.subtitle.peeper_hurt", "Peeper Hurt");
        add("johnny_s_biological_notes.sound.subtitle.peeper_sound", "Peeper wuwu");

        add("johnny_s_biological_notes.sound.subtitle.drifter_admire", "Drifters Admire");
        add("johnny_s_biological_notes.sound.subtitle.drifter_ambient", "Drifters Ambient");
        add("johnny_s_biological_notes.sound.subtitle.drifter_death", "Drifters Death");
        add("johnny_s_biological_notes.sound.subtitle.drifter_hurt", "Drifters Hurt");
        add("johnny_s_biological_notes.sound.subtitle.drifter_victory", "Drifters Victory");
        add(RegistrationInit.DRIFTER.get(), "Drifter");
        add(RegistrationInit.DRIFTER_EGG.get(), "Drifter Egg");
        add(RegistrationInit.NETHERITE_KATANA.get(), "Netherite Katana");
        add(RegistrationInit.WOOD_KATANA.get(), "Wood Katana");
        add(RegistrationInit.DIAMOND_KATANA.get(), "Diamond Katana");
        add(RegistrationInit.STONE_KATANA.get(), "Stone Katana");
        add(RegistrationInit.GOLD_KATANA.get(), "Gold Katana");
        add(RegistrationInit.IRON_KATANA.get(), "Iron Katana");
        add(RegistrationInit.FORGED_PLATE.get(), "Forged Plate");
        add(RegistrationInit.NETHERITE_BLADE.get(), "Netherite Blade");
        add(RegistrationInit.WOOD_BLADE.get(), "Wood Blade");
        add(RegistrationInit.DIAMOND_BLADE.get(), "Diamond Blade");
        add(RegistrationInit.STONE_BLADE.get(), "Stone Blade");
        add(RegistrationInit.GOLD_BLADE.get(), "Gold Blade");
        add(RegistrationInit.IRON_BLADE.get(), "Iron Blade");

        add(RegistrationInit.JELLY.get(), "Jelly");
        add(RegistrationInit.JELLY_EGG.get(), "Jelly Egg");
        add(RegistrationInit.JELLY_BUBBLE.get(), "Jelly Bubble");
        add(RegistrationInit.BLOCK_JELLY_EMBRYO.get(), "Jelly Embryo Block");
        add(RegistrationInit.SEMI_SOLIDFIED_PROTEIN.get(), "Semi Solidfied Protein");
        add(RegistrationInit.SOLIDFIED_PROTEIN.get(), "Solidfied Protein");
        add(RegistrationInit.JELLY_PLATTER.get(), "Jelly Platter");
//        add(RegistrationInit.GLUE_BOTTLE.get(), "Glue Bottle");
        add(PotionsInit.VULNUS_RECOVER.get(), "Vulnus Recover");

        add(RegistrationInit.BLOCK_JELLY.get(), "Jelly Block");
        add(RegistrationInit.BLOCK_JELLY_BLACK.get(), "Jelly Block Black");
        add(RegistrationInit.BLOCK_JELLY_BLUE.get(), "Jelly Block Blue");
        add(RegistrationInit.BLOCK_JELLY_BROWN.get(), "Jelly Block Brown");
        add(RegistrationInit.BLOCK_JELLY_CYAN.get(), "Jelly Block Cyan");
        add(RegistrationInit.BLOCK_JELLY_GRAY.get(), "Jelly Block Gray");
        add(RegistrationInit.BLOCK_JELLY_GREEN.get(), "Jelly Block Green");
        add(RegistrationInit.BLOCK_JELLY_LIGHTBLUE.get(), "Jelly Block Lightblue");
        add(RegistrationInit.BLOCK_JELLY_LIGHTGRAY.get(), "Jelly Block Lightgray");
        add(RegistrationInit.BLOCK_JELLY_LIGHTGREEN.get(), "Jelly Block Lightgreen");
        add(RegistrationInit.BLOCK_JELLY_MAGENTA.get(), "Jelly Block Magenta");
        add(RegistrationInit.BLOCK_JELLY_ORANGE.get(), "Jelly Block Orange");
        add(RegistrationInit.BLOCK_JELLY_PINK.get(), "Jelly Block Pink");
        add(RegistrationInit.BLOCK_JELLY_PURPLE.get(), "Jelly Block Purple");
        add(RegistrationInit.BLOCK_JELLY_RED.get(), "Jelly Block Red");
        add(RegistrationInit.BLOCK_JELLY_WHITE.get(), "Jelly Block White");
        add(RegistrationInit.BLOCK_JELLY_YELLOW.get(), "Jelly Block Yellow");


//        add(RegistrationInit.BLOCK_ITEM_JELLY           .get(),"Jelly Block");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_BLACK     .get(),"Jelly Block Black");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_BLUE      .get(),"Jelly Block Blue");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_BROWN     .get(),"Jelly Block Brown");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_CYAN      .get(),"Jelly Block Cyan");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_GRAY      .get(),"Jelly Block Gray");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_GREEN     .get(),"Jelly Block Green");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_LIGHTBLUE .get(),"Jelly Block Lightblue");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_LIGHTGRAY .get(),"Jelly Block Lightgray");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_LIGHTGREEN.get(),"Jelly Block Lightgreen");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_MAGENTA   .get(),"Jelly Block Magenta");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_ORANGE    .get(),"Jelly Block Orange");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_PINK      .get(),"Jelly Block Pink");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_PURPLE    .get(),"Jelly Block Purple");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_RED       .get(),"Jelly Block Red");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_WHITE     .get(),"Jelly Block White");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_YELLOW     .get(),"Jelly Block Yellow");

        add(RegistrationInit.BLOCK_GLUED_SAND.get(), "Glued Sand");
        add(RegistrationInit.BLOCK_GLUED_RED_SAND.get(), "Glued Red Sand");
        add(RegistrationInit.BLOCK_GLUED_WHITE_CONCRETE_POWDER.get(), "Glued White Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_ORANGE_CONCRETE_POWDER.get(), "Glued Orange Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_MAGENTA_CONCRETE_POWDER.get(), "Glued Magenta Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_LIGHT_BLUE_CONCRETE_POWDER.get(), "Glued Light Blue Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_YELLOW_CONCRETE_POWDER.get(), "Glued Yellow Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_LIME_CONCRETE_POWDER.get(), "Glued Lime Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_PINK_CONCRETE_POWDER.get(), "Glued Pink Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_GRAY_CONCRETE_POWDER.get(), "Glued Gray Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_LIGHT_GRAY_CONCRETE_POWDER.get(), "Glued Light Gray Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_CYAN_CONCRETE_POWDER.get(), "Glued Cyan Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_PURPLE_CONCRETE_POWDER.get(), "Glued Purple Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_BLUE_CONCRETE_POWDER.get(), "Glued Blue Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_BROWN_CONCRETE_POWDER.get(), "Glued Brown Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_GREEN_CONCRETE_POWDER.get(), "Glued Green Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_RED_CONCRETE_POWDER.get(), "Glued Red Concrete Powder");
        add(RegistrationInit.BLOCK_GLUED_BLACK_CONCRETE_POWDER.get(), "Glued Black Concrete Powder");

//        add(RegistrationInit.BLOCK_ITEM_GLUED_WHITE_CONCRETE_POWDER                .get(),"Glued White Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_ORANGE_CONCRETE_POWDER               .get(),"Glued Orange Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_MAGENTA_CONCRETE_POWDER              .get(),"Glued Magenta Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_LIGHT_BLUE_CONCRETE_POWDER           .get(),"Glued Light Blue Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_YELLOW_CONCRETE_POWDER               .get(),"Glued Yellow Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_LIME_CONCRETE_POWDER                 .get(),"Glued Lime Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_PINK_CONCRETE_POWDER                 .get(),"Glued Pink Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_GRAY_CONCRETE_POWDER                 .get(),"Glued Gray Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_LIGHT_GRAY_CONCRETE_POWDER           .get(),"Glued Light Gray Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_CYAN_CONCRETE_POWDER                 .get(),"Glued Cyan Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_PURPLE_CONCRETE_POWDER               .get(),"Glued Purple Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_BLUE_CONCRETE_POWDER                 .get(),"Glued Blue Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_BROWN_CONCRETE_POWDER                .get(),"Glued Brown Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_GREEN_CONCRETE_POWDER                .get(),"Glued Green Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_RED_CONCRETE_POWDER                  .get(),"Glued Red Concrete Powder");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_BLACK_CONCRETE_POWDER                .get(),"Glued Black Concrete Powder");

        add(RegistrationInit.ITEM_JELLY.get(), "JELLY");
        add(RegistrationInit.ITEM_JELLY_BLACK.get(), "JELLY BLACK");
        add(RegistrationInit.ITEM_JELLY_BLUE.get(), "JELLY BLUE");
        add(RegistrationInit.ITEM_JELLY_BROWN.get(), "JELLY BROWN");
        add(RegistrationInit.ITEM_JELLY_CYAN.get(), "JELLY CYAN");
        add(RegistrationInit.ITEM_JELLY_GRAY.get(), "JELLY GRAY");
        add(RegistrationInit.ITEM_JELLY_GREEN.get(), "JELLY GREEN");
        add(RegistrationInit.ITEM_JELLY_LIGHTBLUE.get(), "JELLY LIGHTBLUE");
        add(RegistrationInit.ITEM_JELLY_LIGHTGRAY.get(), "JELLY LIGHTGRAY");
        add(RegistrationInit.ITEM_JELLY_LIGHTGREEN.get(), "JELLY LIGHTGREEN");
        add(RegistrationInit.ITEM_JELLY_MAGENTA.get(), "JELLY MAGENTA");
        add(RegistrationInit.ITEM_JELLY_ORANGE.get(), "JELLY ORANGE");
        add(RegistrationInit.ITEM_JELLY_PINK.get(), "JELLY PINK");
        add(RegistrationInit.ITEM_JELLY_PURPLE.get(), "JELLY PURPLE");
        add(RegistrationInit.ITEM_JELLY_RED.get(), "JELLY RED");
        add(RegistrationInit.ITEM_JELLY_WHITE.get(), "JELLY WHITE");
        add(RegistrationInit.ITEM_JELLY_YELLOW.get(), "JELLY YELLOW");

        add("johnny_s_biological_notes.sound.subtitle.jelly_death", "Jelly Death");
        add("johnny_s_biological_notes.sound.subtitle.jelly_hurt", "Jelly Hurt");
        add("johnny_s_biological_notes.sound.subtitle.jelly_make_bubble", "Jelly Make Bubble");
        add("johnny_s_biological_notes.sound.subtitle.jelly_block_hit", "Jelly Block Hit");
        add("johnny_s_biological_notes.sound.subtitle.jelly_block_place", "Jelly Block Place");
        add("johnny_s_biological_notes.sound.subtitle.jelly_block_step", "Jelly Block Step");
        add("johnny_s_biological_notes.sound.subtitle.jelly_bubble_broken", "Jelly Bubble Broken");
        add("johnny_s_biological_notes.sound.subtitle.glue_bottle_used", "Glue Bottle Used");

        add(RegistrationInit.TRIDACNA.get(),"Tridacna");

        add(RegistrationInit.TRIDACNA_EGG.get(),"Tridacna Egg");
        add(RegistrationInit.BLOCK_ECO_BOTTLE.get(),"Eco Bottle");
        add(RegistrationInit.BLOCK_ECO_BOTTLE_BUBBLE_CORAL.get(),"Eco Bottle");
        add(RegistrationInit.BLOCK_ECO_BOTTLE_FIRE_CORAL .get(),"Eco Bottle");
        add(RegistrationInit.BLOCK_ECO_BOTTLE_HORN_CORAL .get(),"Eco Bottle");
        add(RegistrationInit.BLOCK_ECO_BOTTLE_TUBE_CORAL .get(),"Eco Bottle");
        add(RegistrationInit.BLOCK_ECO_BOTTLE_BRAIN_CORAL.get(),"Eco Bottle");

//        add(RegistrationInit.BLOCK_ITEM_ECO_BOTTLE.get(),"Eco Bottle");
        add("johnny_s_biological_notes.sound.subtitle.tridacna_hurt", "Tridacna Hurt");
        add("johnny_s_biological_notes.sound.subtitle.tridacna_death", "Tridacna Death");
        add("johnny_s_biological_notes.sound.subtitle.tridacna_broken", "Tridacna Broken");
        add("johnny_s_biological_notes.sound.subtitle.tridacna_open", "Tridacna Open");
        add("johnny_s_biological_notes.sound.subtitle.tridacna_close", "Tridacna Close");

//        ITEM_JELLY
//        ITEM_JELLY_BLACK
//        ITEM_JELLY_BLUE
//        ITEM_JELLY_BROWN
//        ITEM_JELLY_CYAN
//        ITEM_JELLY_GRAY
//        ITEM_JELLY_GREEN
//        ITEM_JELLY_LIGHTBLUE
//        ITEM_JELLY_LIGHTGRAY
//        ITEM_JELLY_LIGHTGREEN
//        ITEM_JELLY_MAGENTA
//        ITEM_JELLY_ORANGE
//        ITEM_JELLY_PINK
//        ITEM_JELLY_PURPLE
//        ITEM_JELLY_RED
//        ITEM_JELLY_WHITE
    }
}
