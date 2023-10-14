package com.mnsfhxy.johnny_s_biological_notes;

import com.mnsfhxy.johnny_s_biological_notes.init.*;
import com.mnsfhxy.johnny_s_biological_notes.spawn.SpawnHandler;
import com.mnsfhxy.johnny_s_biological_notes.world.biome.modifier.BiomeModifierTridacnaShell;
import com.mojang.logging.LogUtils;
import com.mojang.serialization.Codec;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(JohnnySBiologicalNotes.MODID)
public class JohnnySBiologicalNotes {

    public static final String MODID = "johnny_s_biological_notes";
    public static final Logger LOGGER = LogUtils.getLogger();

    public JohnnySBiologicalNotes() {
        IEventBus modBusEvent = FMLJavaModLoadingContext.get().getModEventBus();
        ForgeInit.init();
        RegistrationInit.init();//包括物品 方块 效果 实体
        ConfigInit.init();
        SoundInit.init();
        PotionsInit.init();//效果 药水
        ModInit.init();
        modBusEvent.addListener(this::setup);
        modBusEvent.addListener(this::clientSetup);
        final DeferredRegister<Codec<? extends BiomeModifier>> biomeModifiers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, JohnnySBiologicalNotes.MODID);
        biomeModifiers.register(modBusEvent);
        biomeModifiers.register("tridacna_shell_biome_modifier", BiomeModifierTridacnaShell::makeCodec);

//        modBusEvent.addListener(this::gatherData);
//        ConfigBiome.init();
//        final DeferredRegister<Codec<? extends BiomeModifier>> biomeModifiers = DeferredRegister.create(ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, JohnnySBiologicalNotes.MODID);
//        biomeModifiers.register(modBusEvent);
//        biomeModifiers.register("johnny_mob_spawns", SpawnBiomeModifier::makeCodec);
    }
    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(RegistrationInit::initDispenser);
    }
    private void clientSetup(final FMLClientSetupEvent event) {
//        ItemBlockRenderTypes.setRenderLayer(RegistrationInit.BLOCK_JELLY.get(), RenderType.translucent());
    }

}
