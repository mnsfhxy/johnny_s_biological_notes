package com.mnsfhxy.johnny_s_biological_notes.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.Nullable;

public class ChopParticle  extends TextureSheetParticle {

    protected ChopParticle(ClientLevel pLevel, double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed,SpriteSet spriteSet) {
        super(pLevel, pX, pY, pZ, pXSpeed, pYSpeed, pZSpeed);
        this.friction=1f;
        this.xd=pXSpeed;
        this.yd=pYSpeed;
        this.zd=pZSpeed;
        this.quadSize*=2;
        //显示的tick时间
        this.lifetime=20;
        this.rCol=1F;
        this.gCol=1F;
        this.bCol=1F;
        this.setSpriteFromAge(spriteSet);
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
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
            return new ChopParticle(pLevel,pX,pY,pZ,pXSpeed,pYSpeed,pZSpeed,spirtes);
        }
    }
}
