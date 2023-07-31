package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.ShapelessRecipe;
import net.minecraft.world.level.ItemLike;

import java.util.function.Consumer;

//合成表
public class ModRecipes extends RecipeProvider {

    public ModRecipes(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void buildCraftingRecipes(Consumer<FinishedRecipe> consumer) {
//        consumerShapedRecipeBuilder.shaped(Registration.POWERGEN.get())
//                .pattern("mmm")
//                .pattern("x#x")
//                .pattern("#x#")
//                .define('x', Tags.Items.DUSTS_REDSTONE)
//                .define('#', Tags.Items.INGOTS_IRON)
//                .define('m', Registration.MYSTERIOUS_INGOT.get())
//                .group("tutorialv3")
//                .unlockedBy("mysterious", InventoryChangeTrigger.TriggerInstance.hasItems(Registration.MYSTERIOUS_INGOT.get()))
//                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RegistrationInit.DIAMOND_BLADE.get())
                .requires(Items.DIAMOND, 2)
                .requires(RegistrationInit.FORGED_PLATE.get())
                .unlockedBy("has_forged_plate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistrationInit.FORGED_PLATE.get()).build()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RegistrationInit.WOOD_BLADE.get())
                .requires(Ingredient.of(ItemTags.PLANKS), 2)
                .requires(RegistrationInit.FORGED_PLATE.get())
                .unlockedBy("has_forged_plate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistrationInit.FORGED_PLATE.get()).build()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RegistrationInit.IRON_BLADE.get())
                .requires(Items.IRON_INGOT, 2)
                .requires(RegistrationInit.FORGED_PLATE.get())
                .unlockedBy("has_forged_plate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistrationInit.FORGED_PLATE.get()).build()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RegistrationInit.GOLD_BLADE.get())
                .requires(Items.GOLD_INGOT, 2)
                .requires(RegistrationInit.FORGED_PLATE.get())
                .unlockedBy("has_forged_plate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistrationInit.FORGED_PLATE.get()).build()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RegistrationInit.STONE_BLADE.get())
                .requires(Ingredient.of(Items.STONE), 2)
                .requires(RegistrationInit.FORGED_PLATE.get())
                .unlockedBy("has_forged_plate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistrationInit.FORGED_PLATE.get()).build()))
                .save(consumer);
//        ShapelessRecipeBuilder.shapeless(RegistrationInit.NETHERITE_BLADE.get())
//                .requires(Items.NETHERITE_INGOT, 2)
//                .requires(RegistrationInit.FORGED_PLATE.get())
//                .unlockedBy("has_forged_plate", inventoryTrigger(ItemPredicate.Builder.item().of(RegistrationInit.FORGED_PLATE.get()).build()))
//                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RegistrationInit.DIAMOND_KATANA.get())
                .requires(RegistrationInit.DIAMOND_BLADE.get())
                .requires(Items.STICK)
                .unlockedBy("has_diamond_blade", hasItems(RegistrationInit.DIAMOND_BLADE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RegistrationInit.WOOD_KATANA.get())
                .requires(RegistrationInit.WOOD_BLADE.get())
                .requires(Items.STICK)
                .unlockedBy("has_wood_blade", hasItems(RegistrationInit.WOOD_BLADE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RegistrationInit.STONE_KATANA.get())
                .requires(RegistrationInit.STONE_BLADE.get())
                .requires(Items.STICK)
                .unlockedBy("has_stone_blade", hasItems(RegistrationInit.STONE_BLADE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RegistrationInit.IRON_KATANA.get())
                .requires(RegistrationInit.IRON_BLADE.get())
                .requires(Items.STICK)
                .unlockedBy("has_iron_blade", hasItems(RegistrationInit.IRON_BLADE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RegistrationInit.GOLD_KATANA.get())
                .requires(RegistrationInit.GOLD_BLADE.get())
                .requires(Items.STICK)
                .unlockedBy("has_gold_blade", hasItems(RegistrationInit.GOLD_BLADE.get()))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RegistrationInit.NETHERITE_KATANA.get())
                .requires(RegistrationInit.NETHERITE_BLADE.get())
                .requires(Items.STICK)
                .unlockedBy("has_netherite_blade", hasItems(RegistrationInit.NETHERITE_BLADE.get()))
                .save(consumer);
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(RegistrationInit.CRAB_MEAT.get()),
                        RegistrationInit.COOKED_CRAB_MEAT.get(), 0.15f, 200)
                .unlockedBy("has_crab_meat", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.PIGLIN_FOOD).build()))
//                .save(consumer);
                .save(consumer, new ResourceLocation(JohnnySBiologicalNotes.MODID, "cooked_crab_meat"));
//      Sim

    }

    protected static InventoryChangeTrigger.TriggerInstance hasItems(ItemLike... items) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(items);
    }
}
