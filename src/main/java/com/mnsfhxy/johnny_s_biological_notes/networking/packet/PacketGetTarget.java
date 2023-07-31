package com.mnsfhxy.johnny_s_biological_notes.networking.packet;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketGetTarget {

    public static final String MESSAGE_GET_TARGET = "message.gettarget";

    public PacketGetTarget() {
    }

    public PacketGetTarget(FriendlyByteBuf buf) {
    }

    public void toBytes(FriendlyByteBuf buf) {
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context ctx = supplier.get();
        ctx.enqueueWork(() -> {
            // Here we are server side
//            ...  TODO for later
        });
        return true;
    }
}
