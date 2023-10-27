package com.mnsfhxy.johnny_s_biological_notes.particle;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Quaternion;
import com.mojang.math.Vector3f;
import net.minecraft.client.Camera;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ConcentrateParticle extends TextureSheetParticle implements  Comparable {
    private final SpriteSet sprites;
    protected ConcentrateParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pQuadSizeMultiplier, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ, 0.0D, 0.0D, 0.0D);
        this.sprites = pSprites;
        this.lifetime = 4;
        float f = this.random.nextFloat() * 0.6F + 0.4F;
        this.rCol = f;
        this.gCol = f;
        this.bCol = f;
        this.quadSize = 1.0F - (float)pQuadSizeMultiplier * 0.5F;
        this.setSpriteFromAge(pSprites);
    }
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.age++ >= this.lifetime) {
            this.remove();
        } else {
            this.setSpriteFromAge(this.sprites);
        }
    }
//    @Override
//    public ParticleRenderType getRenderType() {
//        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
//    }
    @Override
    public ParticleRenderType getRenderType() {
        return CONCENTRATE_RENDERTYPE;
    }

    ParticleRenderType CONCENTRATE_RENDERTYPE = new ParticleRenderType() {
        public void begin(BufferBuilder p_107448_, TextureManager p_107449_) {
//      RenderSystem.setShader(GameRenderer::getRendertypeLinesShader);
            RenderSystem.disableCull();
//      RenderSystem.disableTexture();
            RenderSystem.disableDepthTest();
            RenderSystem.depthMask(false);


            RenderSystem.disableBlend();
//      RenderSystem.depthMask(true);
            RenderSystem.setShader(GameRenderer::getParticleShader);
            RenderSystem.setShaderTexture(0, TextureAtlas.LOCATION_PARTICLES);
            p_107448_.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.PARTICLE);
        }

        public void end(Tesselator p_107451_) {
            p_107451_.end();
        }

        public String toString() {
            return "PARTICLE_SHEET_OPAQUE";
        }
    };

    @Override
    public int compareTo(@NotNull Object o) {
        if(o instanceof ConcentrateParticle){
            return (int)(this.quadSize-((ConcentrateParticle) o).quadSize);
        }else {
            return 0;

        }
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
    private final SpriteSet spirtes;


        public Provider(SpriteSet spirtes) {
            this.spirtes = spirtes;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new ConcentrateParticle(pLevel,pX,pY,pZ, pXSpeed,spirtes);
        }
    }
    @Override
    protected int getLightColor(float pPartialTick) {
//        float f = ((float)this.age + pPartialTick) / (float)this.lifetime;
//        f = Mth.clamp(f, 0.0F, 1.0F);
//        int i = super.getLightColor(pPartialTick);
//        int j = i & 255;
//        int k = i >> 16 & 255;
//        j += (int)(f * 15.0F * 16.0F);
//        if (j > 240) {
//            j = 240;
//        }

        return 160;
    }

    @Override
    public void render(VertexConsumer p_107678_, Camera p_107679_, float p_107680_) {
        Vec3 vec3 = p_107679_.getPosition();
        float f = (float) (Mth.lerp((double) p_107680_, this.xo, this.x) - vec3.x());
        float f1 = (float) (Mth.lerp((double) p_107680_, this.yo, this.y) - vec3.y());
        float f2 = (float) (Mth.lerp((double) p_107680_, this.zo, this.z) - vec3.z());
        Quaternion quaternion;
        if (this.roll == 0.0F) {
            quaternion = p_107679_.rotation();
        } else {
            quaternion = new Quaternion(p_107679_.rotation());
            float f3 = Mth.lerp(p_107680_, this.oRoll, this.roll);
            quaternion.mul(Vector3f.ZP.rotation(f3));
        }

        Vector3f vector3f1 = new Vector3f(-1.0F, -1.0F, 0.0F);
        vector3f1.transform(quaternion);
        Vector3f[] avector3f =
                new Vector3f[] {
                        new Vector3f(-1.0F, -1.0F, 0.0F),
                        new Vector3f(-1.0F, 1.0F, 0.0F),
                        new Vector3f(1.0F, 1.0F, 0.0F),
                        new Vector3f(1.0F, -1.0F, 0.0F)
                };
        float f4 = this.getQuadSize(p_107680_);

        for (int i = 0; i < 4; ++i) {
            Vector3f vector3f = avector3f[i];
            vector3f.transform(quaternion);
            vector3f.mul(f4);
            vector3f.add(f, f1, f2);
        }

        float f7 = this.getU0();
        float f8 = this.getU1();
        float f5 = this.getV0();
        float f6 = this.getV1();
        int j = this.getLightColor(p_107680_);
        p_107678_
                .vertex((double) avector3f[0].x(), (double) avector3f[0].y(), (double) avector3f[0].z())
                .uv(f8, f6)
                .color(this.rCol, this.gCol, this.bCol, this.alpha)
                .uv2(j)
                .endVertex();
        p_107678_
                .vertex((double) avector3f[1].x(), (double) avector3f[1].y(), (double) avector3f[1].z())
                .uv(f8, f5)
                .color(this.rCol, this.gCol, this.bCol, this.alpha)
                .uv2(j)
                .endVertex();
        p_107678_
                .vertex((double) avector3f[2].x(), (double) avector3f[2].y(), (double) avector3f[2].z())
                .uv(f7, f5)
                .color(this.rCol, this.gCol, this.bCol, this.alpha)
                .uv2(j)
                .endVertex();
        p_107678_
                .vertex((double) avector3f[3].x(), (double) avector3f[3].y(), (double) avector3f[3].z())
                .uv(f7, f6)
                .color(this.rCol, this.gCol, this.bCol, this.alpha)
                .uv2(j)
                .endVertex();
    }

}
