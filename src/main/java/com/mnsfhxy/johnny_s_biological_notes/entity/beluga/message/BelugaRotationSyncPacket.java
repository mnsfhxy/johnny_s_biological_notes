package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.message;

import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.EntityBeluga;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * 白鲸旋转同步的报文，推送旋转信息到客户端
 */
public class BelugaRotationSyncPacket {
    private final int entityId;
    private final boolean isRotation;

    public BelugaRotationSyncPacket(int entityId, boolean isRotation) {
        this.entityId = entityId;
        this.isRotation = isRotation;
    }

    public static void encode(BelugaRotationSyncPacket packet, FriendlyByteBuf buffer) {
        buffer.writeInt(packet.entityId);
        buffer.writeBoolean(packet.isRotation);
    }

    public static BelugaRotationSyncPacket decode(FriendlyByteBuf buffer) {
        return new BelugaRotationSyncPacket(buffer.readInt(), buffer.readBoolean());
    }

    /**
     * 根据服务端推送的报文信息，设置客户端对应实体的值
     * @param packet
     * @param ctx
     */
    public static void handle(BelugaRotationSyncPacket packet, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ClientLevel level = Minecraft.getInstance().level;
            if (level != null) {
                Entity entity = level.getEntity(packet.entityId);
                if (entity instanceof EntityBeluga) {
                    ((EntityBeluga) entity).setRotation(packet.isRotation);
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
