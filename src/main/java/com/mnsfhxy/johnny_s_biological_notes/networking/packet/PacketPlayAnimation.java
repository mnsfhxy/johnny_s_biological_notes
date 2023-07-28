package com.mnsfhxy.johnny_s_biological_notes.networking.packet;

import com.mnsfhxy.johnny_s_biological_notes.util.ModAnimation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PacketPlayAnimation {
    public static final String MESSAGE_PLAY_ANIMATION = "message.playanimation";
    public ModAnimation modAnimation;

    public PacketPlayAnimation(ModAnimation modAnimation) {
        this.modAnimation = modAnimation;
    }

    public PacketPlayAnimation(FriendlyByteBuf buf) {
//        buf.writeBytes(modAnimation.)
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
