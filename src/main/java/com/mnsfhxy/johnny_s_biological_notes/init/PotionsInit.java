package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.effect.EffectFearWater;
import com.mnsfhxy.johnny_s_biological_notes.effect.EffectVulnusRecover;
import com.mnsfhxy.johnny_s_biological_notes.potions.ProperBrewingRecipe;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.brewing.BrewingRecipeRegistry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class PotionsInit {
    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, JohnnySBiologicalNotes.MODID);
    public static final DeferredRegister<MobEffect> MOBEFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, JohnnySBiologicalNotes.MODID);

    //effect注册
    public static RegistryObject<MobEffect> FEAR_WATER =
            MOBEFFECTS.register(
                    "fear_water_effect", () -> new EffectFearWater(MobEffectCategory.HARMFUL, 65793));
    public static RegistryObject<MobEffect> VULNUS_RECOVER =
            MOBEFFECTS.register(
                    "vulnus_recover_effect", () -> new EffectVulnusRecover(MobEffectCategory.BENEFICIAL, 0xc4ecc1));

    //potion注册
    public static final RegistryObject<Potion> FEAR_WATER_POTION =
            POTIONS.register("fear_water_potion", () -> new Potion(new MobEffectInstance(FEAR_WATER.get(), UtilLevel.TIME.SECOND.getTick() * 45)));
    public static final RegistryObject<Potion> LONG_FEAR_WATER_POTION =
            POTIONS.register("long_fear_water_potion", () -> new Potion(new MobEffectInstance(FEAR_WATER.get(), UtilLevel.TIME.SECOND.getTick() * 90)));

//    public static final RegistryObject<Potion> VULNUS_RECOVER_POTION =
//            POTIONS.register("vulnus_recover_effect", () -> new Potion(new MobEffectInstance(VULNUS_RECOVER.get(), UtilLevel.TIME.SECOND.getTick() * 45,0)));



    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MOBEFFECTS.register(bus);
        POTIONS.register(bus);
    }

    public static ItemStack createPotion(Potion potion) {
        return PotionUtils.setPotion(new ItemStack(Items.POTION), potion);
    }

    public static void initBrewing() {
        BrewingRecipeRegistry.addRecipe(Ingredient.of(createPotion(Potions.AWKWARD)), Ingredient.of(RegistrationInit.CRAB_SHELL.get()), createPotion(FEAR_WATER_POTION.get()));
        BrewingRecipeRegistry.addRecipe(new ProperBrewingRecipe(Ingredient.of(createPotion(FEAR_WATER_POTION.get())), Ingredient.of(Items.REDSTONE), createPotion(LONG_FEAR_WATER_POTION.get())));

    }
}
