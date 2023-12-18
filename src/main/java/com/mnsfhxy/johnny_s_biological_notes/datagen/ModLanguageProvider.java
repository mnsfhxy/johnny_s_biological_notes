package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.ModInit;
import com.mnsfhxy.johnny_s_biological_notes.init.PotionsInit;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.common.data.LanguageProvider;
import net.minecraftforge.fml.common.Mod;

public class ModLanguageProvider extends LanguageProvider {

    public ModLanguageProvider(DataGenerator gen, String locale) {
        super(gen, JohnnySBiologicalNotes.MODID, locale);
    }
    public void  addPotion(String registerName,String name){
        add("item.minecraft.lingering_potion.effect."+registerName,"Lingering "+name);
        add("item.minecraft.splash_potion.effect."+registerName, "Splash "+name);
        add("item.minecraft.potion.effect."+registerName, name);

    }
    public void addSubtitle(String registerName,String name){
        add(JohnnySBiologicalNotes.MODID+".sound.subtitle."+registerName, name);
    }
    public void addItemGroup(CreativeModeTab creativeModeTab,String name){
        add("itemGroup." + creativeModeTab.getRecipeFolderName(), name);
    }
    @Override
    protected void addTranslations() {
        addItemGroup(ModInit.ITEM_GROUP_BLOCK,"JohnnySBiologicalNotes Block");
        addItemGroup(ModInit.ITEM_GROUP_EGG,"JohnnySBiologicalNotes Egg");
        addItemGroup(ModInit.ITEM_GROUP_FOOD,"JohnnySBiologicalNotes Food");
        addItemGroup(ModInit.ITEM_GROUP_TOOL,"JohnnySBiologicalNotes Tool");
        addItemGroup(ModInit.ITEM_GROUP_MATERIAL,"JohnnySBiologicalNotes Material");
        add(RegistrationInit.CRAB.get(), "Crab");
        add(RegistrationInit.CRAB_EGG.get(), "Crab Egg");
        add(RegistrationInit.ITEM_CRAB_MEAT.get(), "Crab Meat");
        add(RegistrationInit.ITEM_COOKED_CRAB_MEAT.get(), "Cooked Crab Meat");
        add(RegistrationInit.ITEM_CRAB_BUCKET.get(), "Crab Bucket");
        add(RegistrationInit.CRAB.get() + ".RED", "Red Crab");
        add(RegistrationInit.CRAB.get() + ".BROWN", "Brown Crab");
        add(RegistrationInit.CRAB.get() + ".SPOTTED", "Spotted Crab");
        add(RegistrationInit.CRAB.get() + ".CYAN", "Cyan Crab");
        add(RegistrationInit.CRAB.get() + ".BLACK", "Black Crab");
        add(PotionsInit.FEAR_WATER.get(), "Fear Water");

        addPotion(PotionsInit.FEAR_WATER_POTION.getId().getPath(),"Fear Water Potion");
        addPotion(PotionsInit.LONG_FEAR_WATER_POTION.getId().getPath(),"Long Fear Water Potion");
        addPotion(PotionsInit.CONCENTRATE_POTION.getId().getPath(),"Concentrate Potion");
        addPotion(PotionsInit.LONG_CONCENTRATE_POTION.getId().getPath(),"Long Concentrate Potion");

//        add("item.minecraft.potion.effect.long_fear_water_potion", "Fear Water Potion");
//        add("item.minecraft.splash_potion.effect.long_fear_water_potion", "Splash Fear Water Potion");
//        add("item.minecraft.lingering_potion.effect.long_fear_water_potion", "Lingering Fear Water Potion");

//        add("item.minecraft.lingering_potion.effect.fear_water_potion", "Lingering Fear Water Potion");
//        add("item.minecraft.splash_potion.effect.fear_water_potion", "Splash Fear Water Potion");
//        add("item.minecraft.potion.effect.fear_water_potion", "Fear Water Potion");



         addSubtitle(SoundInit.CRAB_BUBBLE.getId().getPath(), "Crab Bubble");
         addSubtitle(SoundInit.CRAB_DROP_SHELL.getId().getPath(), "Crab Drop Shell");
         addSubtitle(SoundInit.CRAB_HURT.getId().getPath(),"Crab Hurt");
         addSubtitle(SoundInit.CRAB_TALON.getId().getPath(), "Crab Talon");
         addSubtitle(SoundInit.CRAB_WALKING.getId().getPath(), "Crab Walking");
         addSubtitle(SoundInit.CRAB_DIG.getId().getPath(),"Crab Dig");
        add(RegistrationInit.PEEPER.get(), "Peeper");
        add(RegistrationInit.PEEPER_EGG.get(), "Peeper Egg");
        addSubtitle(SoundInit.PEEPER_DEATH.getId().getPath(), "Peeper Death");
        addSubtitle(SoundInit.PEEPER_HURT.getId().getPath(), "Peeper Hurt");
        addSubtitle(SoundInit.PEEPER_SOUND.getId().getPath(), "Peeper wuwu");

       addSubtitle( SoundInit.DRIFTERS_ADMIRE.getId().getPath(), "Drifters Admire");
       addSubtitle(SoundInit.DRIFTERS_AMBIENT.getId().getPath(), "Drifters Ambient");
       addSubtitle(SoundInit.DRIFTERS_DEATH.getId().getPath(), "Drifters Death");
       addSubtitle(SoundInit.DRIFTERS_HURT.getId().getPath(),"Drifters Hurt");
       addSubtitle(SoundInit.DRIFTERS_VICTORY.getId().getPath(), "Drifters Victory");
        add(RegistrationInit.DRIFTER.get(), "Drifter");
        add(RegistrationInit.DRIFTER_EGG.get(), "Drifter Egg");
        add(RegistrationInit.ITEM_NETHERITE_KATANA.get(), "Netherite Katana");
        add(RegistrationInit.ITEM_WOOD_KATANA.get(), "Wood Katana");
        add(RegistrationInit.ITEM_DIAMOND_KATANA.get(), "Diamond Katana");
        add(RegistrationInit.ITEM_STONE_KATANA.get(), "Stone Katana");
        add(RegistrationInit.ITEM_GOLD_KATANA.get(), "Gold Katana");
        add(RegistrationInit.ITEM_IRON_KATANA.get(), "Iron Katana");
        add(RegistrationInit.ITEM_FORGED_PLATE.get(), "Forged Plate");
        add(RegistrationInit.ITEM_NETHERITE_BLADE.get(), "Netherite Blade");
        add(RegistrationInit.ITEM_WOOD_BLADE.get(), "Wood Blade");
        add(RegistrationInit.ITEM_DIAMOND_BLADE.get(), "Diamond Blade");
        add(RegistrationInit.ITEM_STONE_BLADE.get(), "Stone Blade");
        add(RegistrationInit.ITEM_GOLD_BLADE.get(), "Gold Blade");
        add(RegistrationInit.ITEM_IRON_BLADE.get(), "Iron Blade");

        add(RegistrationInit.JELLY.get(), "Jelly");
        add(RegistrationInit.JELLY_EGG.get(), "Jelly Egg");
        add(RegistrationInit.JELLY_BUBBLE.get(), "Jelly Bubble");
        add(RegistrationInit.BLOCK_JELLY_EMBRYO.get(), "Jelly Embryo Block");
        add(RegistrationInit.ITEM_SEMI_SOLIDFIED_PROTEIN.get(), "Semi Solidfied Protein");
        add(RegistrationInit.ITEM_SOLIDFIED_PROTEIN.get(), "Solidfied Protein");
        add(RegistrationInit.ITEM_JELLY_PLATTER.get(), "Jelly Platter");
        add(RegistrationInit.ITEM_GLUE_BOTTLE.get(), "Glue Bottle");
        add(PotionsInit.VULNUS_RECOVER.get(), "Vulnus Recover");
        add(PotionsInit.CONCENTRATE.get(), "Concentrate");


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

       addSubtitle( SoundInit.JELLY_DEATH.getId().getPath(), "Jelly Death");
       addSubtitle(SoundInit.JELLY_HURT.getId().getPath(),"Jelly Hurt");
       addSubtitle(SoundInit.JELLY_MAKE_BUBBLE.getId().getPath(), "Jelly Make Bubble");
       addSubtitle(SoundInit.JELLY_BLOCK_HIT.getId().getPath(), "Jelly Block Hit");
       addSubtitle(SoundInit.JELLY_BLOCK_PLACE.getId().getPath(), "Jelly Block Place");
       addSubtitle(SoundInit.JELLY_BLOCK_STEP.getId().getPath(), "Jelly Block Step");
       addSubtitle(SoundInit.JELLY_BUBBLE_BROKEN.getId().getPath(), "Jelly Bubble Broken");
       addSubtitle(SoundInit.GLUE_BOTTLE_USED.getId().getPath(), "Glue Bottle Used");
        add(RegistrationInit.TRIDACNA.get(),"Tridacna");
        add(RegistrationInit.TRIDACNA_EGG.get(),"Tridacna Egg");
        add(RegistrationInit.BLOCK_ECO_BOTTLE.get(),"Eco Bottle");
//        add(RegistrationInit.BLOCK_ECO_BOTTLE_BUBBLE_CORAL.get(),"Eco Bottle");
//        add(RegistrationInit.BLOCK_ECO_BOTTLE_FIRE_CORAL .get(),"Eco Bottle");
//        add(RegistrationInit.BLOCK_ECO_BOTTLE_HORN_CORAL .get(),"Eco Bottle");
//        add(RegistrationInit.BLOCK_ECO_BOTTLE_TUBE_CORAL .get(),"Eco Bottle");
//        add(RegistrationInit.BLOCK_ECO_BOTTLE_BRAIN_CORAL.get(),"Eco Bottle");

//        add(RegistrationInit.BLOCK_ITEM_ECO_BOTTLE.get(),"Eco Bottle");
        addSubtitle(SoundInit.TRIDACNA_HURT.getId().getPath(),"Tridacna Hurt");
        addSubtitle(SoundInit.TRIDACNA_DEATH.getId().getPath(), "Tridacna Death");
        addSubtitle(SoundInit.TRIDACNA_BROKEN.getId().getPath(), "Tridacna Broken");
        addSubtitle(SoundInit.TRIDACNA_OPEN.getId().getPath(),"Tridacna Open");
        addSubtitle(SoundInit.TRIDACNA_CLOSE.getId().getPath(), "Tridacna Close");

        add(RegistrationInit.LOITER.get(),"Loiter");
        add(RegistrationInit.LOITER_EGG.get(),"Loiter Egg");
        add(RegistrationInit.ITEM_SOUL_TUMOR.get(),"Soul Tumor");

        addSubtitle(SoundInit.LOITER_AMBIENT.getId().getPath(),"Loiter Ambient");
        addSubtitle(SoundInit.LOITER_HURT.getId().getPath(),"Loiter Hurt");
        addSubtitle(SoundInit.LOITER_DEATH.getId().getPath(),"Loiter Death");
        addSubtitle(SoundInit.LOITER_SATURATE.getId().getPath(),"Loiter Saturate");

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
