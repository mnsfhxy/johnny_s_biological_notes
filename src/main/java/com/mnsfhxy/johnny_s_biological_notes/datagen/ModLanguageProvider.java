package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.ModInit;
import com.mnsfhxy.johnny_s_biological_notes.init.PotionsInit;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageProvider  extends LanguageProvider {

    public ModLanguageProvider (DataGenerator gen, String locale) {
        super(gen, JohnnySBiologicalNotes.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + ModInit.TAB_NAME, "JohnnySBiologicalNotes");
        add(RegistrationInit.CRAB.get(), "Crab");
        add(RegistrationInit.CRAB_EGG.get(), "Crab Egg");
        add(RegistrationInit.CRAB_MEAT.get(),"Crab Meat");
        add(RegistrationInit.COOKED_CRAB_MEAT.get(),"Cooked Crab Meat");
        add(RegistrationInit.CRAB_BUCKET.get(),"Crab Bucket");
        add(RegistrationInit.CRAB.get()+".RED","Red Crab");
        add(RegistrationInit.CRAB.get()+".BROWN","Brown Crab");
        add(RegistrationInit.CRAB.get()+".SPOTTED","Spotted Crab");
        add(RegistrationInit.CRAB.get()+".CYAN","Cyan Crab");
        add(RegistrationInit.CRAB.get()+".BLACK","Black Crab");
        add(PotionsInit.FEAR_WATER.get(),"Fear Water");
        add("item.minecraft.lingering_potion.effect.fear_water_potion","Lingering Fear Water Potion");
        add("item.minecraft.splash_potion.effect.fear_water_potion","Splash Fear Water Potion");
        add("item.minecraft.potion.effect.fear_water_potion","Fear Water Potion");
        add("item.minecraft.lingering_potion.effect.long_fear_water_potion","Lingering Fear Water Potion");
        add("item.minecraft.splash_potion.effect.long_fear_water_potion","Splash Fear Water Potion");
        add("item.minecraft.potion.effect.long_fear_water_potion","Fear Water Potion");
        add("johnny_s_biological_notes.sound.subtitle.crab_bubble","Crab Bubble");
        add("johnny_s_biological_notes.sound.subtitle.crab_drop_shell","Crab Drop Shell");
        add("johnny_s_biological_notes.sound.subtitle.crab_hurt","Crab Hurt");
        add("johnny_s_biological_notes.sound.subtitle.crab_talon","Crab Talon");
        add("johnny_s_biological_notes.sound.subtitle.crab_walking","Crab Walking");
        add("johnny_s_biological_notes.sound.subtitle.crab_dig","Crab Dig");
        add(RegistrationInit.PEEPER.get(),"Peeper");
        add(RegistrationInit.PEEPER_EGG.get(),"Peeper Egg");
        add("johnny_s_biological_notes.sound.subtitle.peeper_death","Peeper Death");
        add("johnny_s_biological_notes.sound.subtitle.peeper_hurt","Peeper Hurt");
        add("johnny_s_biological_notes.sound.subtitle.peeper_sound","Peeper wuwu");

        add("johnny_s_biological_notes.sound.subtitle.drifter_admire","Drifters Admire");
        add("johnny_s_biological_notes.sound.subtitle.drifter_ambient","Drifters Ambient");
        add("johnny_s_biological_notes.sound.subtitle.drifter_death","Drifters Death");
        add("johnny_s_biological_notes.sound.subtitle.drifter_hurt","Drifters Hurt");
        add("johnny_s_biological_notes.sound.subtitle.drifter_victory","Drifters Victory");
        add(RegistrationInit.DRIFTER.get(),"Drifter");
        add(RegistrationInit.DRIFTER_EGG.get(),"Drifter Egg");
        add(RegistrationInit.NETHERITE_KATANA.get(),"Netherite Katana");
        add(RegistrationInit.WOOD_KATANA.get(),"Wood Katana");
        add(RegistrationInit.DIAMOND_KATANA.get(),"Diamond Katana");
        add(RegistrationInit.STONE_KATANA.get(),"Stone Katana");
        add(RegistrationInit.GOLD_KATANA.get(),"Gold Katana");
        add(RegistrationInit.IRON_KATANA.get(),"Iron Katana");
        add(RegistrationInit.FORGED_PLATE.get(),"Forged Plate");
        add(RegistrationInit.NETHERITE_BLADE.get(),"Netherite Blade");
        add(RegistrationInit.WOOD_BLADE.get(),"Wood Blade");
        add(RegistrationInit.DIAMOND_BLADE.get(),"Diamond Blade");
        add(RegistrationInit.STONE_BLADE.get(),"Stone Blade");
        add(RegistrationInit.GOLD_BLADE.get(),"Gold Blade");
        add(RegistrationInit.IRON_BLADE.get(),"Iron Blade");




    }
}
