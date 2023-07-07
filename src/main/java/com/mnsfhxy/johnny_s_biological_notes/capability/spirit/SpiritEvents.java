package com.mnsfhxy.johnny_s_biological_notes.capability.spirit;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.LogicalSide;

public class SpiritEvents {
    // Whenever a new object of some type is created the AttachCapabilitiesEvent will fire. In our case we want to know
    // when a new player arrives so that we can attach our capability here
    public static void onAttachCapabilitiesPlayer(AttachCapabilitiesEvent<Entity> event){
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerSpiritProvider.PLAYER_SPIRIT).isPresent()) {
                // The player does not already have this capability so we need to add the capability provider here
                event.addCapability(new ResourceLocation(JohnnySBiologicalNotes.MODID, "playerspirit"), new PlayerSpiritProvider());
            }
        }
    }

    // When a player dies or teleports from the end capabilities are cleared. Using the PlayerEvent.Clone event
    // we can detect this and copy our capability from the old player to the new one
    public static void onPlayerCloned(PlayerEvent.Clone event) {
        if (event.isWasDeath()) {
            Player original = event.getOriginal();
            //Revive the player's caps
            original.reviveCaps();
            // We need to copyFrom the capabilities
            original.getCapability(PlayerSpiritProvider.PLAYER_SPIRIT).ifPresent(oldStore -> {
                event.getEntity().getCapability(PlayerSpiritProvider.PLAYER_SPIRIT).ifPresent(newStore -> {
                    oldStore.cleanSpirit();
                    newStore.copyFrom(oldStore);
                    SpiritOverlay.updateHUD(event.getEntity());
                });
            });
        }
    }

    // Finally we need to register our capability in a RegisterCapabilitiesEvent
    public static void onRegisterCapabilities(RegisterCapabilitiesEvent event) {
        event.register(PlayerSpirit.class);
    }

//    public static void onPlayerTick(TickEvent.PlayerTickEvent event){
//        if(event.side.isClient()){
//            new SpiritOverlay.HUD_SPIRIT.render(event.ge);
//        }
//    }
}
