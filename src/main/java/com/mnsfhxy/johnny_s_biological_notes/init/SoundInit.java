package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundInit {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, JohnnySBiologicalNotes.MODID);

    public static void init() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        SOUND_EVENTS.register(bus);
    }

    public static final RegistryObject<SoundEvent> CRAB_BUBBLE = createSoundEvent("crab_bubble");
    public static final RegistryObject<SoundEvent> CRAB_HURT = createSoundEvent("crab_hurt");
    public static final RegistryObject<SoundEvent> CRAB_TALON = createSoundEvent("crab_talon");
    public static final RegistryObject<SoundEvent> CRAB_DROP_SHELL = createSoundEvent("crab_drop_shell");
    public static final RegistryObject<SoundEvent> CRAB_WALKING = createSoundEvent("crab_walking");
    public static final RegistryObject<SoundEvent> CRAB_DIG= createSoundEvent("crab_dig");




    private static RegistryObject<SoundEvent> createSoundEvent(final String soundName) {
        return SOUND_EVENTS.register(soundName, () -> new SoundEvent(new ResourceLocation(JohnnySBiologicalNotes.MODID, soundName)));
    }

}
