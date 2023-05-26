package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.ModInit;
import com.mnsfhxy.johnny_s_biological_notes.init.PotionsInit;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.LanguageProvider;

public class ModLanguageZhProvider extends LanguageProvider {

    public ModLanguageZhProvider(DataGenerator gen, String locale) {
        super(gen, JohnnySBiologicalNotes.MODID, locale);
    }

    @Override
    protected void addTranslations() {
        add("itemGroup." + ModInit.TAB_NAME, "Johnny的生物笔记");
        add(RegistrationInit.CRAB.get(), "螃蟹");
        add(RegistrationInit.CRAB_EGG.get(), "螃蟹蛋");
        add(RegistrationInit.CRAB_MEAT.get(),"生蟹");
        add(RegistrationInit.COOKED_CRAB_MEAT.get(),"熟蟹");
        add(RegistrationInit.CRAB_BUCKET.get(),"蟹桶");
        add(RegistrationInit.CRAB_SHELL.get(),"甲片");
        add(RegistrationInit.CRAB.get()+".RED","红色螃蟹");
        add(RegistrationInit.CRAB.get()+".BROWN","褐色螃蟹");
        add(RegistrationInit.CRAB.get()+".SPOTTED","斑纹螃蟹");
        add(RegistrationInit.CRAB.get()+".CYAN","青色螃蟹");
        add(RegistrationInit.CRAB.get()+".BLACK","黑色螃蟹");
        add(PotionsInit.FEAR_WATER.get(),"恐水");
        add("item.minecraft.lingering_potion.effect.fear_water_potion","滞留型恐水药水");
        add("item.minecraft.splash_potion.effect.fear_water_potion","喷溅型恐水药水");
        add("item.minecraft.potion.effect.fear_water_potion","恐水药水");
        add("item.minecraft.lingering_potion.effect.long_fear_water_potion","滞留型恐水药水");
        add("item.minecraft.splash_potion.effect.long_fear_water_potion","喷溅型恐水药水");
        add("item.minecraft.potion.effect.long_fear_water_potion","恐水药水");


    }
}
