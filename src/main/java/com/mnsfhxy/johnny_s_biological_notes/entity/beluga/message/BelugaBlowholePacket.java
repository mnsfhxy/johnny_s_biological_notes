package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.message;

import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

/**
 * 由于粒子效果只能在客户端生成，而触发该特效的控制逻辑及数据的AI在服务端运行，
 * 因此使用基于SimpleImpl的通信机制来完成相关功能
 * 定义包含白鲸坐标信息的数据包，以及在事件Handler中生成粒子效果
 */
public class BelugaBlowholePacket {
    // 实体位置信息
    private BlockPos pos;

    // 空构造函数，用于反序列化
    public BelugaBlowholePacket() {}

    // 构造函数，用于发送实体位置信息
    public BelugaBlowholePacket(BlockPos pos) {
        this.pos = pos;
    }

    // 用于序列化数据
    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBlockPos(pos);
    }

    // 用于反序列化数据
    public void fromBytes(FriendlyByteBuf buf) {
        pos = buf.readBlockPos();
    }

    // 自定义网络包处理器
    public static class Handler {
        @OnlyIn(Dist.CLIENT)
        public static void onMessage(BelugaBlowholePacket message, Supplier<NetworkEvent.Context> ctx) {
            // 在客户端执行粒子生成代码
            ctx.get().enqueueWork(() -> {
                Minecraft mc = Minecraft.getInstance();
                Level level = mc.level;
                Vec3 vec = Vec3.atCenterOf(message.pos);
                double x = vec.x;
                double y = vec.y;
                double z = vec.z;
                for (int i = 0; i < 10; ++i) {
                    double offsetX = level.random.nextGaussian() * 0.02D;
                    double offsetY = level.random.nextGaussian() * 0.02D;
                    double offsetZ = level.random.nextGaussian() * 0.02D;
                    level.addParticle(ParticleTypes.SPLASH, x, y, z, 0, 1, 0);
                }
            });
            ctx.get().setPacketHandled(true);
        }
    }
}