package com.mnsfhxy.johnny_s_biological_notes.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.particle.WaterDropParticle;
import net.minecraft.core.particles.SimpleParticleType;

public class BelugaBlowholeParticle extends WaterDropParticle {
    BelugaBlowholeParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(pLevel, pX, pY, pZ);
        this.gravity = 0.04F;

        this.yd = pYSpeed;
        if (pYSpeed == 0.0D && (pXSpeed != 0.0D || pZSpeed != 0.0D)) {
            this.xd = pXSpeed;
            this.yd = 0.1D;
            this.zd = pZSpeed;
        }
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            BelugaBlowholeParticle particle = new BelugaBlowholeParticle(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
            particle.pickSprite(this.spriteSet);
            return particle;
        }
    }
}