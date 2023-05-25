package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.effect.EffectFearWater;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.item.alchemy.Potion;
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


    public static final RegistryObject<Potion> FEAR_WATER_POTION =
            POTIONS.register("fear_water_potion", () -> new Potion( new MobEffectInstance(FEAR_WATER.get(),UtilLevel.TIME.MINUTE.getTick()*30) ));
    public static void init (){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MOBEFFECTS.register(bus);
        POTIONS.register(bus);
    }

}
