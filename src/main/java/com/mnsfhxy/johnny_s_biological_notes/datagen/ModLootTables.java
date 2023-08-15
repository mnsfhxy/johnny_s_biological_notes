package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.block.BlockJelly;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.data.DataGenerator;
import net.minecraft.world.level.block.Blocks;

public class ModLootTables extends BaseLootTableProvider {

    public ModLootTables(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    @Override
    protected void addTables() {
        lootTables.put(RegistrationInit.BLOCK_GLUED_SAND.get(), createSimpleTable("glued_sand", Blocks.SAND));
        lootTables.put(RegistrationInit.BLOCK_GLUED_SAND.get(), createSimpleTable("glued_sand", Blocks.SAND));

        lootTables.put(RegistrationInit.BLOCK_GLUED_SAND.get(), createSimpleTable("glued_sand", Blocks.SAND));
        lootTables.put(RegistrationInit.BLOCK_GLUED_RED_SAND.get(), createSimpleTable("glued_red_sand", Blocks.RED_SAND));
        lootTables.put(RegistrationInit.BLOCK_GLUED_WHITE_CONCRETE_POWDER.get(), createSimpleTable("glued_white_concrete_powder", Blocks.WHITE_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_ORANGE_CONCRETE_POWDER.get(), createSimpleTable("glued_orange_concrete_powder", Blocks.ORANGE_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_MAGENTA_CONCRETE_POWDER.get(), createSimpleTable("glued_magenta_concrete_powder", Blocks.MAGENTA_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_LIGHT_BLUE_CONCRETE_POWDER.get(), createSimpleTable("glued_light_blue_concrete_powder", Blocks.LIGHT_BLUE_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_YELLOW_CONCRETE_POWDER.get(), createSimpleTable("glued_yellow_concrete_powder", Blocks.YELLOW_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_LIME_CONCRETE_POWDER.get(), createSimpleTable("glued_lime_concrete_powder", Blocks.LIME_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_PINK_CONCRETE_POWDER.get(), createSimpleTable("glued_pink_concrete_powder", Blocks.PINK_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_GRAY_CONCRETE_POWDER.get(), createSimpleTable("glued_gray_concrete_powder", Blocks.GRAY_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_LIGHT_GRAY_CONCRETE_POWDER.get(), createSimpleTable("glued_light_gray_concrete_powder", Blocks.LIGHT_GRAY_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_CYAN_CONCRETE_POWDER.get(), createSimpleTable("glued_cyan_concrete_powder", Blocks.CYAN_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_PURPLE_CONCRETE_POWDER.get(), createSimpleTable("glued_purple_concrete_powder", Blocks.PURPLE_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_BLUE_CONCRETE_POWDER.get(), createSimpleTable("glued_blue_concrete_powder", Blocks.BLUE_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_BROWN_CONCRETE_POWDER.get(), createSimpleTable("glued_brown_concrete_powder", Blocks.BROWN_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_GREEN_CONCRETE_POWDER.get(), createSimpleTable("glued_green_concrete_powder", Blocks.GREEN_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_RED_CONCRETE_POWDER.get(), createSimpleTable("glued_red_concrete_powder", Blocks.RED_CONCRETE_POWDER));
        lootTables.put(RegistrationInit.BLOCK_GLUED_BLACK_CONCRETE_POWDER.get(), createSimpleTable("glued_black_concrete_powder", Blocks.BLACK_CONCRETE_POWDER));

        lootTables.put(RegistrationInit.BLOCK_JELLY.get(), createSimpleTable("jelly_block", RegistrationInit.BLOCK_JELLY.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_BLACK.get(), createSimpleTable("jelly_block_black", RegistrationInit.BLOCK_JELLY_BLACK.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_BLUE.get(), createSimpleTable("jelly_block_blue", RegistrationInit.BLOCK_JELLY_BLUE.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_BROWN.get(), createSimpleTable("jelly_block_brown", RegistrationInit.BLOCK_JELLY_BROWN.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_CYAN.get(), createSimpleTable("jelly_block_cyan", RegistrationInit.BLOCK_JELLY_CYAN.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_GRAY.get(), createSimpleTable("jelly_block_gray", RegistrationInit.BLOCK_JELLY_GRAY.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_GREEN.get(), createSimpleTable("jelly_block_green", RegistrationInit.BLOCK_JELLY_GREEN.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_LIGHTBLUE.get(), createSimpleTable("jelly_block_lightblue", RegistrationInit.BLOCK_JELLY_LIGHTBLUE.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_LIGHTGRAY.get(), createSimpleTable("jelly_block_lightgray", RegistrationInit.BLOCK_JELLY_LIGHTGRAY.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_LIGHTGREEN.get(), createSimpleTable("jelly_block_lightgreen", RegistrationInit.BLOCK_JELLY_LIGHTGREEN.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_MAGENTA.get(), createSimpleTable("jelly_block_magenta", RegistrationInit.BLOCK_JELLY_MAGENTA.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_ORANGE.get(), createSimpleTable("jelly_block_orange", RegistrationInit.BLOCK_JELLY_ORANGE.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_PINK.get(), createSimpleTable("jelly_block_pink", RegistrationInit.BLOCK_JELLY_PINK.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_PURPLE.get(), createSimpleTable("jelly_block_purple", RegistrationInit.BLOCK_JELLY_PURPLE.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_RED.get(), createSimpleTable("jelly_block_red", RegistrationInit.BLOCK_JELLY_RED.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_WHITE.get(), createSimpleTable("jelly_block_white", RegistrationInit.BLOCK_JELLY_WHITE.get()));
        lootTables.put(RegistrationInit.BLOCK_JELLY_YELLOW.get(), createSimpleTable("jelly_block_yellow", RegistrationInit.BLOCK_JELLY_YELLOW.get()));

//        BLOCK_JELLY
//        BLOCK_JELLY_BLACK
//        BLOCK_JELLY_BLUE
//        BLOCK_JELLY_BROWN
//        BLOCK_JELLY_CYAN
//        BLOCK_JELLY_GRAY
//        BLOCK_JELLY_GREEN
//        BLOCK_JELLY_LIGHTBLUE
//        BLOCK_JELLY_LIGHTGRAY
//        BLOCK_JELLY_LIGHTGREEN
//        BLOCK_JELLY_MAGENTA
//        BLOCK_JELLY_ORANGE
//        BLOCK_JELLY_PINK
//        BLOCK_JELLY_PURPLE
//        BLOCK_JELLY_RED
//        BLOCK_JELLY_WHITE
//        BLOCK_JELLY_YELLOW
//        "jelly_block"
//        "jelly_block_black"
//        "jelly_block_blue"
//        "jelly_block_brown"
//        "jelly_block_cyan"
//        "jelly_block_gray"
//        "jelly_block_green"
//        "jelly_block_lightblue"
//        "jelly_block_lightgray"
//        "jelly_block_lightgreen"
//        "jelly_block_magenta"
//        "jelly_block_orange"
//        "jelly_block_pink"
//        "jelly_block_purple"
//        "jelly_block_red"
//        "jelly_block_white"
//        "jelly_block_yellow"

//        BLOCK_WHITE_CONCRETE_POWDER
//        BLOCK_ORANGE_CONCRETE_POWDER
//        BLOCK_MAGENTA_CONCRETE_POWDER
//        BLOCK_LIGHT_BLUE_CONCRETE_POWDER
//        BLOCK_YELLOW_CONCRETE_POWDER
//        BLOCK_LIME_CONCRETE_POWDER
//        BLOCK_PINK_CONCRETE_POWDER
//        BLOCK_GRAY_CONCRETE_POWDER
//        BLOCK_LIGHT_GRAY_CONCRETE_POWDER
//        BLOCK_CYAN_CONCRETE_POWDER
//        BLOCK_PURPLE_CONCRETE_POWDER
//        BLOCK_BLUE_CONCRETE_POWDER
//        BLOCK_BROWN_CONCRETE_POWDER
//        BLOCK_GREEN_CONCRETE_POWDER
//        BLOCK_RED_CONCRETE_POWDER
//        BLOCK_BLACK_CONCRETE_POWDER
//        "glued_white_concrete_powder"
//        "glued_orange_concrete_powder"
//        "glued_magenta_concrete_powder"
//        "glued_light_blue_concrete_powder"
//        "glued_yellow_concrete_powder"
//        "glued_lime_concrete_powder"
//        "glued_pink_concrete_powder"
//        "glued_gray_concrete_powder"
//        "glued_light_gray_concrete_powder"
//        "glued_cyan_concrete_powder"
//        "glued_purple_concrete_powder"
//        "glued_blue_concrete_powder"
//        "glued_brown_concrete_powder"
//        "glued_green_concrete_powder"
//        "glued_red_concrete_powder"
//        "glued_black_concrete_powder"

    }
}
