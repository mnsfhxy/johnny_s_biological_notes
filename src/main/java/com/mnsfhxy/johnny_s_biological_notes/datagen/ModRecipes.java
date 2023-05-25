package com.mnsfhxy.johnny_s_biological_notes.datagen;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.crafting.Ingredient;

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

        SimpleCookingRecipeBuilder.smelting(Ingredient.of(RegistrationInit.CRAB_MEAT.get()),
                        RegistrationInit.COOKED_CRAB_MEAT.get(), 0.15f, 200)
                .unlockedBy("has_crab_meat", inventoryTrigger(ItemPredicate.Builder.item().of(ItemTags.PIGLIN_FOOD).build()))
//                .save(consumer);
                .save(consumer, new ResourceLocation(JohnnySBiologicalNotes.MODID,"cooked_crab_meat"));
//
    }
}
