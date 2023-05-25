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



    }
}
