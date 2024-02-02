package com.mnsfhxy.johnny_s_biological_notes.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

public class BelugaBlowholeParticle extends TextureSheetParticle {
    protected BelugaBlowholeParticle(ClientLevel pLevel, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(pLevel, x, y, z, motionX, motionY, motionZ);
        this.gravity = 0.01f;
        this.friction = 0.8f;
        this.lifetime = 40;
        this.quadSize = 0.1f;
        this.alpha = 1.0f;
        setColor(0f, 0.749f, 1f); // 设置为水的颜色

        // 选择一个随机的角度
        double angle = random.nextDouble() * 2 * Math.PI;

        // 计算水平速度
        double speed = 0.1; // 你可以根据需要调整这个值
        this.xd = speed * Math.cos(angle);
        this.zd = speed * Math.sin(angle);
    }

    @Override
    public void tick() {
        super.tick();
        double factor = this.age >= this.lifetime / 2 ? 1.5 : 1.0; // 你可以根据需要调整这个值
        this.xd *= factor;
        this.zd *= factor;

        if (this.age <= this.lifetime / 2) {
            this.yd += 0.002;
        } else {
            this.yd -= gravity;
        }

        // 设置粒子高度为原来的5倍
        this.yd *= 2;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel pLevel, double x, double y, double z, double motionX, double motionY, double motionZ) {
            BelugaBlowholeParticle particle = new BelugaBlowholeParticle(pLevel, x, y, z, motionX, motionY, motionZ);
            particle.setSpriteFromAge(spriteSet);
            return particle;
        }

        public ParticleRenderType getRenderType() {
            return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
        }
    }
}