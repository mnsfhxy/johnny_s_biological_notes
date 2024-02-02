package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.message;

import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.young.EntityYoungBeluga;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * 白鲸鸣奏的网络包
 */
public class BelugaSongParticlePacket {
    private final int entityId;
    private final float pitch;
    private final int particleNumber;

    public BelugaSongParticlePacket(int entityId, float pitch, int particleNumber) {
        this.entityId = entityId;
        this.pitch = pitch;
        this.particleNumber = particleNumber;
    }

    public static void encode(BelugaSongParticlePacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.entityId);
        buffer.writeFloat(packet.pitch);
        buffer.writeInt(packet.particleNumber);
    }

    public static BelugaSongParticlePacket decode(FriendlyByteBuf buffer) {
        return new BelugaSongParticlePacket(buffer.readInt(), buffer.readFloat(), buffer.readInt());
    }

    public static void handle(BelugaSongParticlePacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientLevel level = Minecraft.getInstance().level;
            if (level != null) {
                Entity entity = level.getEntity(packet.entityId);
                if (entity instanceof EntityYoungBeluga) {
                    for (int i = 0; i < packet.particleNumber; ++i) {
                        double offsetX = (2 * level.random.nextDouble() - 1) * entity.getBbWidth();
                        double offsetY = level.random.nextDouble() * entity.getBbHeight();
                        double offsetZ = (2 * level.random.nextDouble() - 1) * entity.getBbWidth();

                        double pitch = packet.pitch / 24.0D;
                        level.addParticle(ParticleTypes.NOTE,
                                entity.getX() + offsetX,
                                entity.getY() + offsetY,
                                entity.getZ() + offsetZ,
                                pitch, 0.0D, 0.0D);  // 使用音高来设置粒子颜色
                    }
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}