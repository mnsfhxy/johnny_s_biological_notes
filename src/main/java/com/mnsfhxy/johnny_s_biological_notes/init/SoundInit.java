package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.common.util.ForgeSoundType;
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

    public static final RegistryObject<SoundEvent> PEEPER_SOUND=createSoundEvent("peeper_sound");
    public static final RegistryObject<SoundEvent> PEEPER_HURT=createSoundEvent("peeper_hurt");
    public static final RegistryObject<SoundEvent> PEEPER_DEATH=createSoundEvent("peeper_death");

    public static final RegistryObject<SoundEvent> DRIFTERS_ADMIRE =createSoundEvent("drifter_admire");
    public static final RegistryObject<SoundEvent> DRIFTERS_AMBIENT =createSoundEvent("drifter_ambient");
    public static final RegistryObject<SoundEvent> DRIFTERS_DEATH =createSoundEvent("drifter_death");
    public static final RegistryObject<SoundEvent> DRIFTERS_HURT =createSoundEvent("drifter_hurt");
    public static final RegistryObject<SoundEvent> DRIFTERS_VICTORY =createSoundEvent("drifter_victory");

    public static final RegistryObject<SoundEvent> JELLY_DEATH =createSoundEvent("jelly_death");
    public static final RegistryObject<SoundEvent> JELLY_HURT =createSoundEvent("jelly_hurt");
    public static final RegistryObject<SoundEvent> JELLY_MAKE_BUBBLE =createSoundEvent("jelly_make_bubble");
    public static final RegistryObject<SoundEvent> JELLY_BLOCK_HIT =createSoundEvent("jelly_block_hit");
    public static final RegistryObject<SoundEvent> JELLY_BLOCK_PLACE =createSoundEvent("jelly_block_place");
    public static final RegistryObject<SoundEvent> JELLY_BLOCK_STEP =createSoundEvent("jelly_block_step");
    public static final RegistryObject<SoundEvent> JELLY_BUBBLE_BROKEN =createSoundEvent("jelly_bubble_broken");
    public static final RegistryObject<SoundEvent> GLUE_BOTTLE_USED =createSoundEvent("glue_bottle_used");
    public static final RegistryObject<SoundEvent> TRIDACNA_BROKEN =createSoundEvent("tridacna_broken");
    public static final RegistryObject<SoundEvent> TRIDACNA_CLOSE =createSoundEvent("tridacna_close");
    public static final RegistryObject<SoundEvent> TRIDACNA_DEATH =createSoundEvent("tridacna_death");
    public static final RegistryObject<SoundEvent> TRIDACNA_HURT =createSoundEvent("tridacna_hurt");
    public static final RegistryObject<SoundEvent> TRIDACNA_OPEN =createSoundEvent("tridacna_open");
    public static final RegistryObject<SoundEvent> LOITER_AMBIENT=createSoundEvent("loiter_ambient");
    public static final RegistryObject<SoundEvent> LOITER_HURT=createSoundEvent("loiter_hurt");
    public static final RegistryObject<SoundEvent> LOITER_DEATH=createSoundEvent("loiter_death");
    public static final RegistryObject<SoundEvent> LOITER_SATURATE =createSoundEvent("loiter_saturate");
    public static final RegistryObject<SoundEvent> BELUGA_AMBIENT =createSoundEvent("beluga_ambient");
    public static final RegistryObject<SoundEvent> BELUGA_AMBIENT_WATER =createSoundEvent("beluga_ambient_water");
    public static final RegistryObject<SoundEvent> BELUGA_ATTACK =createSoundEvent("beluga_attack");
    public static final RegistryObject<SoundEvent> BELUGA_BREATH =createSoundEvent("beluga_breath");
    public static final RegistryObject<SoundEvent> BELUGA_DEATH =createSoundEvent("beluga_death");
    public static final RegistryObject<SoundEvent> BELUGA_EAT =createSoundEvent("beluga_eat");
    public static final RegistryObject<SoundEvent> BELUGA_HAPPY =createSoundEvent("beluga_happy");
    public static final RegistryObject<SoundEvent> BELUGA_HURT =createSoundEvent("beluga_hurt");
    public static final RegistryObject<SoundEvent> BELUGA_PLAY =createSoundEvent("beluga_play");
    public static final RegistryObject<SoundEvent> BELUGA_SING =createSoundEvent("beluga_sing");
    public static final RegistryObject<SoundEvent> BELUGA_SPLASH =createSoundEvent("beluga_splash");
    public static final RegistryObject<SoundEvent> BELUGA_SWIM =createSoundEvent("beluga_swim");

    public static final ForgeSoundType JELLY_BLOCK = new ForgeSoundType(1.0F, 1.0F, JELLY_BLOCK_HIT, JELLY_BLOCK_STEP, JELLY_BLOCK_PLACE, JELLY_BLOCK_HIT, JELLY_BLOCK_PLACE);


    private static RegistryObject<SoundEvent> createSoundEvent(final String soundName) {
        return SOUND_EVENTS.register(soundName, () -> new SoundEvent(new ResourceLocation(JohnnySBiologicalNotes.MODID, soundName)));
    }

}
