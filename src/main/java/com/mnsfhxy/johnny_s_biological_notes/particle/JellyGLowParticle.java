package com.mnsfhxy.johnny_s_biological_notes.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class JellyGLowParticle extends TextureSheetParticle {
    private final SpriteSet sprites;
    static final RandomSource RANDOM = RandomSource.create();


    protected JellyGLowParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed, SpriteSet pSprites) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.friction = 0.96F;
        this.speedUpWhenYMotionIsBlocked = true;
        this.sprites = pSprites;
        this.quadSize *= 1.5F;
        this.hasPhysics = false;
        this.setSpriteFromAge(pSprites);
    }

    public void tick() {
        super.tick();
        this.setSpriteFromAge(this.sprites);
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
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spirte;

        public Provider(SpriteSet spirtes) {
            this.spirte = spirtes;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType pType, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            JellyGLowParticle glowparticle = new JellyGLowParticle(pLevel, pX, pY, pZ, 0.5D - JellyGLowParticle.RANDOM.nextDouble(), pYSpeed, 0.5D - JellyGLowParticle.RANDOM.nextDouble(), this.spirte);
            glowparticle.yd *= (double)0.2F;
            if (pXSpeed == 0.0D && pZSpeed == 0.0D) {
                glowparticle.xd *= (double)0.1F;
                glowparticle.zd *= (double)0.1F;
            }

            glowparticle.setLifetime((int)(8.0D / (pLevel.random.nextDouble() * 0.8D + 0.2D)/2));
            return glowparticle;
        }
    }
}

