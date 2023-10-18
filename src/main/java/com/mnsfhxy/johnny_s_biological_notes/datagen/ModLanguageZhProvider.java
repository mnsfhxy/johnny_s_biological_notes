package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.ModInit;
import com.mnsfhxy.johnny_s_biological_notes.init.PotionsInit;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mnsfhxy.johnny_s_biological_notes.init.SoundInit;
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
        add(RegistrationInit.CRAB_MEAT.get(), "生蟹");
        add(RegistrationInit.COOKED_CRAB_MEAT.get(), "熟蟹");
        add(RegistrationInit.CRAB_BUCKET.get(), "蟹桶");
        add(RegistrationInit.CRAB_SHELL.get(), "甲片");
        add(RegistrationInit.CRAB.get() + ".RED", "红色螃蟹");
        add(RegistrationInit.CRAB.get() + ".BROWN", "褐色螃蟹");
        add(RegistrationInit.CRAB.get() + ".SPOTTED", "斑纹螃蟹");
        add(RegistrationInit.CRAB.get() + ".CYAN", "青色螃蟹");
        add(RegistrationInit.CRAB.get() + ".BLACK", "黑色螃蟹");
        add(PotionsInit.FEAR_WATER.get(), "恐水");
        add("item.minecraft.lingering_potion.effect.fear_water_potion", "滞留型恐水药水");
        add("item.minecraft.splash_potion.effect.fear_water_potion", "喷溅型恐水药水");
        add("item.minecraft.potion.effect.fear_water_potion", "恐水药水");
        add("item.minecraft.lingering_potion.effect.long_fear_water_potion", "滞留型恐水药水");
        add("item.minecraft.splash_potion.effect.long_fear_water_potion", "喷溅型恐水药水");
        add("item.minecraft.potion.effect.long_fear_water_potion", "恐水药水");
        add("johnny_s_biological_notes.sound.subtitle.crab_bubble", "螃蟹：吐泡");
        add("johnny_s_biological_notes.sound.subtitle.crab_drop_shell", "螃蟹：脱壳");
        add("johnny_s_biological_notes.sound.subtitle.crab_hurt", "螃蟹：受伤");
        add("johnny_s_biological_notes.sound.subtitle.crab_talon", "螃蟹：钳击");
        add("johnny_s_biological_notes.sound.subtitle.crab_walking", "螃蟹：脚步");
        add("johnny_s_biological_notes.sound.subtitle.crab_dig", "螃蟹：挖掘");
        add(RegistrationInit.PEEPER.get(), "窥视者");
        add(RegistrationInit.PEEPER_EGG.get(), "窥视者蛋");
        add("johnny_s_biological_notes.sound.subtitle.peeper_death", "窥视者：死亡");
        add("johnny_s_biological_notes.sound.subtitle.peeper_hurt", "窥视者：受伤");
        add("johnny_s_biological_notes.sound.subtitle.peeper_sound", "窥视者：呜呜");

        add("johnny_s_biological_notes.sound.subtitle.drifter_admire", "浪客：欣赏");
        add("johnny_s_biological_notes.sound.subtitle.drifter_ambient", "浪客：自言自语");
        add("johnny_s_biological_notes.sound.subtitle.drifter_death", "浪客：死亡");
        add("johnny_s_biological_notes.sound.subtitle.drifter_hurt", "浪客：死亡");
        add("johnny_s_biological_notes.sound.subtitle.drifter_victory", "浪客：祝胜");
        add(RegistrationInit.DRIFTER.get(), "浪客");
        add(RegistrationInit.DRIFTER_EGG.get(), "浪客蛋");
        add(RegistrationInit.NETHERITE_KATANA.get(), "下界合金刀");
        add(RegistrationInit.WOOD_KATANA.get(), "木刀");
        add(RegistrationInit.DIAMOND_KATANA.get(), "钻石刀");
        add(RegistrationInit.STONE_KATANA.get(), "石刀");
        add(RegistrationInit.GOLD_KATANA.get(), "金刀");
        add(RegistrationInit.IRON_KATANA.get(), "铁刀");
        add(RegistrationInit.FORGED_PLATE.get(), "锻刀模板");
        add(RegistrationInit.NETHERITE_BLADE.get(), "下界合金刀刃");
        add(RegistrationInit.WOOD_BLADE.get(), "木刀刃");
        add(RegistrationInit.DIAMOND_BLADE.get(), "钻石刀刃");
        add(RegistrationInit.STONE_BLADE.get(), "石刀刃");
        add(RegistrationInit.GOLD_BLADE.get(), "金刀刃");
        add(RegistrationInit.IRON_BLADE.get(), "铁刀刃");

        add(RegistrationInit.JELLY.get(), "果冻体");
        add(RegistrationInit.BLOCK_JELLY_EMBRYO.get(), "果冻体幼胚");
        add(RegistrationInit.SEMI_SOLIDFIED_PROTEIN.get(), "半凝合蛋白");
        add(RegistrationInit.SOLIDFIED_PROTEIN.get(), "凝合蛋白");
        add(RegistrationInit.JELLY_PLATTER.get(), "果冻拼盘");
//        add(RegistrationInit.GLUE_BOTTLE.get(), "胶液瓶");
        add(PotionsInit.VULNUS_RECOVER.get(), "创伤恢复");
        add(RegistrationInit.JELLY_EGG.get(), "果冻体蛋");
        add(RegistrationInit.JELLY_BUBBLE.get(), "果冻泡");

        add(RegistrationInit.BLOCK_JELLY.get(), "果冻方块");
        add(RegistrationInit.BLOCK_JELLY_BLACK.get(), "黑色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_BLUE.get(), "蓝色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_BROWN.get(), "棕色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_CYAN.get(), "青色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_GRAY.get(), "灰色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_GREEN.get(), "绿色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_LIGHTBLUE.get(), "浅蓝色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_LIGHTGRAY.get(), "浅灰色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_LIGHTGREEN.get(), "浅绿色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_MAGENTA.get(), "洋红色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_ORANGE.get(), "橙色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_PINK.get(), "粉红色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_PURPLE.get(), "紫色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_RED.get(), "红色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_WHITE.get(), "白色果冻方块");
        add(RegistrationInit.BLOCK_JELLY_YELLOW.get(), "黄色果冻方块");

//        add(RegistrationInit.BLOCK_ITEM_JELLY.get(), "果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_BLACK.get(), "黑色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_BLUE.get(), "蓝色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_BROWN.get(), "棕色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_CYAN.get(), "青色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_GRAY.get(), "灰色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_GREEN.get(), "绿色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_LIGHTBLUE.get(), "浅蓝色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_LIGHTGRAY.get(), "浅灰色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_LIGHTGREEN.get(), "浅绿色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_MAGENTA.get(), "洋红色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_ORANGE.get(), "橙色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_PINK.get(), "粉红色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_PURPLE.get(), "紫色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_RED.get(), "红色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_WHITE.get(), "白色果冻方块");
//        add(RegistrationInit.BLOCK_ITEM_JELLY_YELLOW.get(), "黄色果冻方块");

//Block(Block_Item无需注册)
        add(RegistrationInit.BLOCK_GLUED_SAND.get(), "涂胶的沙子");
        add(RegistrationInit.BLOCK_GLUED_RED_SAND.get(), "涂胶的红沙");
        add(RegistrationInit.BLOCK_GLUED_WHITE_CONCRETE_POWDER.get(), "涂胶的白色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_ORANGE_CONCRETE_POWDER.get(), "涂胶的橙色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_MAGENTA_CONCRETE_POWDER.get(), "涂胶的洋红色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_LIGHT_BLUE_CONCRETE_POWDER.get(), "涂胶的浅蓝色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_YELLOW_CONCRETE_POWDER.get(), "涂胶的黄色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_LIME_CONCRETE_POWDER.get(), "涂胶的石灰色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_PINK_CONCRETE_POWDER.get(), "涂胶的粉红色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_GRAY_CONCRETE_POWDER.get(), "涂胶的灰色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_LIGHT_GRAY_CONCRETE_POWDER.get(), "涂胶的浅灰色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_CYAN_CONCRETE_POWDER.get(), "涂胶的青色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_PURPLE_CONCRETE_POWDER.get(), "涂胶的紫色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_BLUE_CONCRETE_POWDER.get(), "涂胶的蓝色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_BROWN_CONCRETE_POWDER.get(), "涂胶的棕色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_GREEN_CONCRETE_POWDER.get(), "涂胶的绿色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_RED_CONCRETE_POWDER.get(), "涂胶的红色混凝土粉末");
        add(RegistrationInit.BLOCK_GLUED_BLACK_CONCRETE_POWDER.get(), "涂胶的黑色混凝土粉末");
        add(RegistrationInit.BLOCK_TRIDACNA_SHELL.get(),"砗磲壳");
        add(RegistrationInit.BLOCK_TRIDACNA_SHELL_BROKEN.get(),"破损的砗磲壳");
        add(RegistrationInit.BLOCK_OLDER_TRIDACNA_SHELL.get(),"古老的砗磲壳");
        add(RegistrationInit.BLOCK_OLDER_TRIDACNA_SHELL_BROKEN.get(),"古老的破损的砗磲壳");

//        add(RegistrationInit.BLOCK_ITEM_GLUED_WHITE_CONCRETE_POWDER.get(), "白色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_ORANGE_CONCRETE_POWDER.get(), "橙色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_MAGENTA_CONCRETE_POWDER.get(), "洋红色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_LIGHT_BLUE_CONCRETE_POWDER.get(), "浅蓝色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_YELLOW_CONCRETE_POWDER.get(), "黄色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_LIME_CONCRETE_POWDER.get(), "石灰色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_PINK_CONCRETE_POWDER.get(), "粉红色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_GRAY_CONCRETE_POWDER.get(), "灰色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_LIGHT_GRAY_CONCRETE_POWDER.get(), "浅灰色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_CYAN_CONCRETE_POWDER.get(), "青色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_PURPLE_CONCRETE_POWDER.get(), "紫色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_BLUE_CONCRETE_POWDER.get(), "蓝色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_BROWN_CONCRETE_POWDER.get(), "棕色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_GREEN_CONCRETE_POWDER.get(), "绿色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_RED_CONCRETE_POWDER.get(), "红色胶合混凝土粉末");
//        add(RegistrationInit.BLOCK_ITEM_GLUED_BLACK_CONCRETE_POWDER.get(), "黑色胶合混凝土粉末");

        add(RegistrationInit.ITEM_JELLY.get(), "果冻");
        add(RegistrationInit.ITEM_JELLY_BLACK.get(), "黑色果冻");
        add(RegistrationInit.ITEM_JELLY_BLUE.get(), "蓝色果冻");
        add(RegistrationInit.ITEM_JELLY_BROWN.get(), "棕色果冻");
        add(RegistrationInit.ITEM_JELLY_CYAN.get(), "青色果冻");
        add(RegistrationInit.ITEM_JELLY_GRAY.get(), "灰色果冻");
        add(RegistrationInit.ITEM_JELLY_GREEN.get(), "绿色果冻");
        add(RegistrationInit.ITEM_JELLY_LIGHTBLUE.get(), "浅蓝色果冻");
        add(RegistrationInit.ITEM_JELLY_LIGHTGRAY.get(), "浅灰色果冻");
        add(RegistrationInit.ITEM_JELLY_LIGHTGREEN.get(), "浅绿色果冻");
        add(RegistrationInit.ITEM_JELLY_MAGENTA.get(), "洋红色果冻");
        add(RegistrationInit.ITEM_JELLY_ORANGE.get(), "橙色果冻");
        add(RegistrationInit.ITEM_JELLY_PINK.get(), "粉红色果冻");
        add(RegistrationInit.ITEM_JELLY_PURPLE.get(), "紫色果冻");
        add(RegistrationInit.ITEM_JELLY_RED.get(), "红色果冻");
        add(RegistrationInit.ITEM_JELLY_WHITE.get(), "白色果冻");
        add(RegistrationInit.ITEM_JELLY_YELLOW.get(), "黄色果冻");

        add("johnny_s_biological_notes.sound.subtitle.jelly_death", "果冻体：死亡");
        add("johnny_s_biological_notes.sound.subtitle.jelly_hurt", "果冻体：受伤");
        add("johnny_s_biological_notes.sound.subtitle.jelly_make_bubble", "果冻体：吐泡");
        add("johnny_s_biological_notes.sound.subtitle.jelly_block_hit", "果冻块：破坏");
        add("johnny_s_biological_notes.sound.subtitle.jelly_block_place", "果冻块：放置");
        add("johnny_s_biological_notes.sound.subtitle.jelly_block_step", "果冻块：移动");
        add("johnny_s_biological_notes.sound.subtitle.jelly_bubble_broken", "果冻泡：破裂");
        add("johnny_s_biological_notes.sound.subtitle.glue_bottle_used", "胶液瓶：倾倒");

        add(RegistrationInit.TRIDACNA.get(),"砗磲");
        add(RegistrationInit.TRIDACNA_EGG.get(),"砗磲蛋");
        add(RegistrationInit.BLOCK_ECO_BOTTLE.get(),"生态瓶");
        add(RegistrationInit.BLOCK_ECO_BOTTLE_BUBBLE_CORAL.get(),"生态瓶");
        add(RegistrationInit.BLOCK_ECO_BOTTLE_FIRE_CORAL .get(),"生态瓶");
        add(RegistrationInit.BLOCK_ECO_BOTTLE_HORN_CORAL .get(),"生态瓶");
        add(RegistrationInit.BLOCK_ECO_BOTTLE_TUBE_CORAL .get(),"生态瓶");
        add(RegistrationInit.BLOCK_ECO_BOTTLE_BRAIN_CORAL.get(),"生态瓶");
//        add(RegistrationInit.BLOCK_ITEM_ECO_BOTTLE.get(),"生态瓶");
        add("johnny_s_biological_notes.sound.subtitle.tridacna_hurt", "砗磲：受伤");
        add("johnny_s_biological_notes.sound.subtitle.tridacna_death", "砗磲：死亡");
        add("johnny_s_biological_notes.sound.subtitle.tridacna_broken", "砗磲：外壳破裂");
        add("johnny_s_biological_notes.sound.subtitle.tridacna_open", "砗磲：打开外壳");
        add("johnny_s_biological_notes.sound.subtitle.tridacna_close", "砗磲：关闭外壳");

    }
}
