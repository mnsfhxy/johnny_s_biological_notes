package com.mnsfhxy.johnny_s_biological_notes.init;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.message.BelugaBlowholePacket;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.message.BelugaSongParticlePacket;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

/**
 * 初始化通信网络
 */
@Mod.EventBusSubscriber(modid = JohnnySBiologicalNotes.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class NetworkInit {
    public static SimpleChannel NETWORK;


    /**
     * 初始化通信网络，注册消息及消息处理程序
     * @param event
     */
    @SubscribeEvent
    public static void onCommonSetup(FMLCommonSetupEvent event) {
        NETWORK = NetworkRegistry.newSimpleChannel(
                new ResourceLocation(JohnnySBiologicalNotes.MODID, "main"),
                () -> "1.0",
                s -> true,
                s -> true
        );

        //白鲸喷水粒子效果相关消息及处理器的注册
        NETWORK.registerMessage(
                0,
                BelugaBlowholePacket.class,
                BelugaBlowholePacket::toBytes,
                buf -> {
                    BelugaBlowholePacket pkt = new BelugaBlowholePacket();
                    pkt.fromBytes(buf);
                    return pkt;
                },
                BelugaBlowholePacket.Handler::onMessage
        );

        //白鲸鸣奏粒子效果相关消息及处理器的注册
        NETWORK.registerMessage(1,
                BelugaSongParticlePacket.class,
                BelugaSongParticlePacket::encode,
                BelugaSongParticlePacket::decode,
                BelugaSongParticlePacket::handle);
    }
}
