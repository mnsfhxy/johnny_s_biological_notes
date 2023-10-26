package com.mnsfhxy.johnny_s_biological_notes.util;

import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.minecraft.client.Camera;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.RenderStateShard;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.vehicle.Minecart;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderLevelStageEvent;

import java.lang.reflect.InvocationTargetException;
import java.util.PriorityQueue;
import java.util.Queue;

public class UtilRender {
    private static RenderType XRAY_TYPE = null;
    public static Queue<Particle> iterable = new PriorityQueue<>();

    static final RandomSource randomsource = RandomSource.create();

    public static RenderType buildRenderType() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        var compositeState = RenderType.CompositeState.builder()
                .setShaderState(new RenderStateShard.ShaderStateShard(GameRenderer::getRendertypeLinesShader))
                //.setDepthTestState(new RenderStateShard.DepthTestStateShard("always",GL11.GL_ALWAYS))
                .setCullState(new RenderStateShard.CullStateShard(false))
                .setTransparencyState(new RenderStateShard.TransparencyStateShard("xray", () -> {
                    RenderSystem.enableBlend();
                    RenderSystem.blendFunc(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA);
                }, () -> {
                    RenderSystem.disableBlend();
                })).createCompositeState(true);

        return RenderType.create("xray", DefaultVertexFormat.POSITION_COLOR_NORMAL, VertexFormat.Mode.LINES, 1024, false, false, compositeState);

    }

    @OnlyIn(Dist.CLIENT)
    public static <T extends ParticleOptions> void addParticle(T t, int pParticleNum, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
//    System.out.println("drawParticle");
        //        final Player player = Minecraft.getInstance().player;
        //        final Level world = Minecraft.getInstance().level;
        //
        //        System.out.println("drawParticle-----");
        //        System.out.println(ConcentrateEntity.SoundingEntity.size());
        //        for(Entity entity : ConcentrateEntity.SoundingEntity){
        //            System.out.println(entity.getType().toShortString());
        //            assert world != null;
        ////
        // world.addParticle(ParticleTypes.EXPLOSION,entity.getX(),entity.getY()+1,entity.getZ(),0,0,0);
        ////
        // world.addAlwaysVisibleParticle(Init.CONCENTRATE_PARTICLE.get(),entity.getX(),entity.getY()+1,entity.getZ(),0,0,0);
        //            if(entity instanceof LivingEntity ){
        //                ((LivingEntity) entity).addEffect(new
        // MobEffectInstance(MobEffects.GLOWING,1));
        //            }
        //            ConcentrateEntity.SoundingEntity.remove(entity);
        //        }
        if (XRAY_TYPE == null) {
            try {
                XRAY_TYPE = buildRenderType();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        Minecraft minecraft = Minecraft.getInstance();
        if (minecraft != null) {
//            Camera camera = minecraft.gameRenderer.getMainCamera();
//            if (camera.isInitialized() && minecraft.particleEngine != null) {
            if ( minecraft.particleEngine != null) {

//                Queue<Particle> iterable = new PriorityQueue<>();
                for (int i = 0; i < pParticleNum; ++i) {
                    double d21 = randomsource.nextDouble() * 4.0D;
                    double d26 = randomsource.nextDouble() * Math.PI * 2.0D;
                    double d30 = Math.cos(d26) * d21;
                    double d2 = 0.01D + randomsource.nextDouble() * 0.5D;
                    double d4 = Math.sin(d26) * d21;
                    Particle particle = minecraft.particleEngine.createParticle(t, pX, pY, pZ, d30, d2, d4);
                    iterable.add(particle);
                }


            }


        }


    }
    public static void drawParticle(RenderLevelStageEvent event){
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        if (iterable!= null)
            try {
                Particle poll = iterable.poll();
                if(poll!=null)poll.render(bufferbuilder, event.getCamera(), 1.0F);
            }catch (Exception e){

            }
    }

}
