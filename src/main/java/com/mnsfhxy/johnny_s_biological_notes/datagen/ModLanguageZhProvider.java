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
        add("johnny_s_biological_notes.sound.subtitle.crab_bubble","螃蟹：吐泡");
        add("johnny_s_biological_notes.sound.subtitle.crab_drop_shell","螃蟹：脱壳");
        add("johnny_s_biological_notes.sound.subtitle.crab_hurt","螃蟹：受伤");
        add("johnny_s_biological_notes.sound.subtitle.crab_talon","螃蟹：钳击");
        add("johnny_s_biological_notes.sound.subtitle.crab_walking","螃蟹：脚步");
        add("johnny_s_biological_notes.sound.subtitle.crab_dig","螃蟹：挖掘");
        add(RegistrationInit.PEEPER.get(),"窥视者");
        add(RegistrationInit.PEEPER_EGG.get(),"窥视者蛋");
        add("johnny_s_biological_notes.sound.subtitle.peeper_death","窥视者：死亡");
        add("johnny_s_biological_notes.sound.subtitle.peeper_hurt","窥视者：受伤");
        add("johnny_s_biological_notes.sound.subtitle.peeper_sound","窥视者：呜呜");

        add("johnny_s_biological_notes.sound.subtitle.drifter_admire","浪客：欣赏");
        add("johnny_s_biological_notes.sound.subtitle.drifter_ambient","浪客：自言自语");
        add("johnny_s_biological_notes.sound.subtitle.drifter_death","浪客：死亡");
        add("johnny_s_biological_notes.sound.subtitle.drifter_hurt","浪客：死亡");
        add("johnny_s_biological_notes.sound.subtitle.drifter_victory","浪客：祝胜");
        add(RegistrationInit.DRIFTER.get(),"浪客");
        add(RegistrationInit.DRIFTER_EGG.get(),"浪客蛋");
        add(RegistrationInit.NETHERITE_KATANA.get(),"下界合金刀");
        add(RegistrationInit.WOOD_KATANA.get(),"木刀");
        add(RegistrationInit.DIAMOND_KATANA.get(),"钻石刀");
        add(RegistrationInit.STONE_KATANA.get(),"石刀");
        add(RegistrationInit.GOLD_KATANA.get(),"金刀");
        add(RegistrationInit.IRON_KATANA.get(),"铁刀");
        add(RegistrationInit.FORGED_PLATE.get(),"锻刀模板");
        add(RegistrationInit.NETHERITE_BLADE.get(),"下界合金刀刃");
        add(RegistrationInit.WOOD_BLADE.get(),"木刀刃");
        add(RegistrationInit.DIAMOND_BLADE.get(),"钻石刀刃");
        add(RegistrationInit.STONE_BLADE.get(),"石刀刃");
        add(RegistrationInit.GOLD_BLADE.get(),"金刀刃");
        add(RegistrationInit.IRON_BLADE.get(),"铁刀刃");
    }
}
