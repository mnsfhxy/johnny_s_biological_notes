package com.mnsfhxy.johnny_s_biological_notes.capability.spirit;

import com.mnsfhxy.johnny_s_biological_notes.Item.ItemKatana;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.client.gui.overlay.IGuiOverlay;

import java.util.concurrent.atomic.AtomicInteger;

public class SpiritOverlay {
    public static final ResourceLocation SPIRIT_0 = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/gui/spirit_0.png");
    public static final ResourceLocation SPIRIT_1 = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/gui/spirit_1.png");
    public static final ResourceLocation SPIRIT_2= new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/gui/spirit_2.png");
    public static final ResourceLocation SPIRIT_3 = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/gui/spirit_3.png");
    public static final ResourceLocation SPIRIT_4 = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/gui/spirit_4.png");
    public static final ResourceLocation SPIRIT_5 = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/gui/spirit_5.png");
    public static final ResourceLocation SPIRIT_FIGHT = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/gui/spirit_fight.png");
    public static ResourceLocation HUD=SPIRIT_0;
    public static void updateHUD(Player player) {
        AtomicInteger a = new AtomicInteger();
        player.getCapability(PlayerSpiritProvider.PLAYER_SPIRIT).ifPresent(s -> {
            a.set(s.getSpirit());
        });
        switch (a.get()){
            case 0:HUD=SPIRIT_0;break;
            case 1:HUD=SPIRIT_1;break;
            case 2:HUD=SPIRIT_2;break;
            case 3:HUD=SPIRIT_3;break;
            case 4:HUD=SPIRIT_4;break;
            case 5:HUD=SPIRIT_5;break;
        }
    }

    public static final IGuiOverlay HUD_SPIRIT = (gui, poseStack, partialTicks, width, height) -> {
        Player player=null;
        player=Minecraft.getInstance().player;
        int x=width/2;
        int y=height;
        if(Minecraft.getInstance().player==null)return;
        if(!(player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof ItemKatana))return;
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F,1.0F,1.0F,1.0F);
        RenderSystem.setShaderTexture(0,HUD);
        GuiComponent.blit(poseStack,x-94,y-54,0, 0, 113, 20, 113, 20 );
//        gui.setupOverlayRenderState(true, true,HUD);
//        gui.blit(poseStack, 123, 180,0, 0, 113, 20, 113, 20 );
    };
}
