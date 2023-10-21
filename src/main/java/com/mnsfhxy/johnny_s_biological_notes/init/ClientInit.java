package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.block.berenderer.RendererTridacnaShell;
import com.mnsfhxy.johnny_s_biological_notes.block.model.ModelTridacnaShellOpen;
import com.mnsfhxy.johnny_s_biological_notes.capability.spirit.SpiritOverlay;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.ModelCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.RendererCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.ModelDrifter;
import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.RendererDrifter;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.ModelJelly;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.RendererJelly;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.bubble.ModelJellyBubble;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.bubble.RendererJellyBubble;
import com.mnsfhxy.johnny_s_biological_notes.entity.loiter.ModelLoiter;
import com.mnsfhxy.johnny_s_biological_notes.entity.loiter.RendererLoiter;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.ModelPeeper;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.RendererPeeper;
import com.mnsfhxy.johnny_s_biological_notes.entity.tridacna.ModelTridacna;
import com.mnsfhxy.johnny_s_biological_notes.entity.tridacna.ModelTridacnaClosed;
import com.mnsfhxy.johnny_s_biological_notes.entity.tridacna.RendererTridacna;
import com.mnsfhxy.johnny_s_biological_notes.particle.ChopParticle;
import com.mnsfhxy.johnny_s_biological_notes.particle.JellyGLowParticle;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.*;
import net.minecraftforge.client.gui.overlay.VanillaGuiOverlay;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = JohnnySBiologicalNotes.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClientInit {
    //        注册生物Layers
    @SubscribeEvent
    public static void onRegisterLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModelCrab.LAYER_LOCATION, ModelCrab::createBodyLayer);
        event.registerLayerDefinition(ModelDrifter.LAYER_LOCATION,ModelDrifter::createBodyLayer);
        event.registerLayerDefinition(ModelPeeper.LAYER_LOCATION,ModelPeeper::createBodyLayer);
        event.registerLayerDefinition(ModelJelly.LAYER_LOCATION,ModelJelly::createBodyLayer);
        event.registerLayerDefinition(ModelJellyBubble.LAYER_LOCATION,ModelJellyBubble::createBodyLayer);
        event.registerLayerDefinition(ModelTridacna.LAYER_LOCATION,ModelTridacna::createBodyLayer);
        event.registerLayerDefinition(ModelTridacnaClosed.LAYER_LOCATION,ModelTridacnaClosed::createBodyLayer);
        event.registerLayerDefinition(ModelLoiter.LAYER_LOCATION,ModelLoiter::createBodyLayer);
//        event.registerLayerDefinition(ModelTridacnaShellOpen.LAYER_LOCATION,ModelTridacnaShellOpen::createBodyLayer);

    }

    @SubscribeEvent
    public static void onRegisterRenderer(EntityRenderersEvent.RegisterRenderers event) {
        //注意Render new只能有一个参数
        event.registerEntityRenderer(RegistrationInit.CRAB.get(), RendererCrab::new);
        event.registerEntityRenderer(RegistrationInit.DRIFTER.get(), RendererDrifter::new);
        event.registerEntityRenderer(RegistrationInit.PEEPER.get(), RendererPeeper::new);
        event.registerEntityRenderer(RegistrationInit.JELLY.get(), RendererJelly::new);
        event.registerEntityRenderer(RegistrationInit.JELLY_BUBBLE.get(), RendererJellyBubble::new);
        event.registerEntityRenderer(RegistrationInit.TRIDACNA.get(), RendererTridacna::new);
        event.registerEntityRenderer(RegistrationInit.LOITER.get(), RendererLoiter::new);
//        event.registerBlockEntityRenderer(RegistrationInit.TRIDACNA_SHELL_BE.get(), RendererTridacnaShell::new);

    }

    @SubscribeEvent
    public static void onTextureStitch(TextureStitchEvent.Pre event) {
//        if (!event.getAtlas().location().equals(TextureAtlas.LOCATION_BLOCKS)) {
//            return;
//        }
//        event.addSprite(PowergenRenderer.HALO);
    }
    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        event.register(RegistrationInit.CHOP_PARTICLE.get(), ChopParticle.Provider::new);
        event.register(RegistrationInit.JELLY_GLOW_PARTICLE.get(), JellyGLowParticle.Provider::new);


    }
    @SubscribeEvent
    public static void onModelRegistryEvent(ModelEvent.RegisterGeometryLoaders event) {
//        event.register(GeneratorModelLoader.GENERATOR_LOADER.getPath(), new GeneratorModelLoader());
    }

    @SubscribeEvent
    public static void onKeyBindRegister(RegisterKeyMappingsEvent event) {
//        KeyBindings.init(event);
    }
    @SubscribeEvent
    public static void onRegisterOverlays(RegisterGuiOverlaysEvent event) {
//        event.registerAbove(VanillaGuiOverlay.HOTBAR.id(), "mana_overlay", ManaOverlay.HUD_MANA);
        event.registerAbove(VanillaGuiOverlay.PLAYER_HEALTH.id(), "spirit_overlay", SpiritOverlay.HUD_SPIRIT);

    }
}
