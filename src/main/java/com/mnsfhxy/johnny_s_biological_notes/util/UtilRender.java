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
    public static Queue<Particle> iterable = new PriorityQueue<>();
    static final RandomSource randomsource = RandomSource.create();
    @OnlyIn(Dist.CLIENT)
    public static <T extends ParticleOptions> void addParticle(T t, int pParticleNum, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
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
                    //最大50个例子防止卡顿
                    if(iterable.size()<50)iterable.add(particle);
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
