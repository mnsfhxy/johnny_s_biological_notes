package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.util.UtilLevel;
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



    public static final RegistryObject<Potion> FEAR_WATER_POTION =
            POTIONS.register("fear_water_potion", () -> new Potion( new MobEffectInstance(RegistrationInit.FEAR_WATER.get(),UtilLevel.TIME.MINUTE.getTick()*30) ));
    public static void init (){
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        POTIONS.register(bus);
    }

}
