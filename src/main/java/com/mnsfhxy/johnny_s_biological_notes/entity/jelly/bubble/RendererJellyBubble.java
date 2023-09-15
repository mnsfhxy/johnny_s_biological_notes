package com.mnsfhxy.johnny_s_biological_notes.entity.jelly.bubble;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.texture.TextureAtlas;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RendererJellyBubble extends MobRenderer<EntityJellyBubble, ModelJellyBubble> {
    private static final ResourceLocation LAYER_LOCATION = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/jelly_bubble.png");
    //    ModelJellyBubble modelJellyBubble;
    protected RenderType renderType;

    public RendererJellyBubble(EntityRendererProvider.Context pContext) {
        super(pContext, new ModelJellyBubble(pContext.bakeLayer(ModelJellyBubble.LAYER_LOCATION)), 0.15f);
//        modelJellyBubble=new ModelJellyBubble(pContext.bakeLayer(ModelJellyBubble.LAYER_LOCATION));

    }

    @Override
    public ResourceLocation getTextureLocation(EntityJellyBubble pEntity) {
        return LAYER_LOCATION;

    }

    @Nullable
    @Override
    protected RenderType getRenderType(EntityJellyBubble pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        this.renderType = RenderType.entityTranslucent(LAYER_LOCATION);
        return this.renderType;
    }
    //    @Override
//    public ResourceLocation getTextureLocation(EntityJellyBubble pEntity) {
//        return LAYER_LOCATION;
//    }

    //    @Override
//    public void render(EntityJellyBubble pEntity, float pEntityYaw, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight) {
//        super.render(pEntity, pEntityYaw, pPartialTick, pPoseStack, pBuffer, pPackedLight);
//        pPoseStack.pushPose();
//        RenderType renderType = RenderType.entityTranslucent(getTextureLocation(pEntity));
//        VertexConsumer vertexconsumer = pBuffer.getBuffer(renderType);
//        int i = getOverlayCoords( getWhiteOverlayProgress(pEntity, pPartialTick));
//        modelJellyBubble.renderToBuffer(pPoseStack,vertexconsumer,pPackedLight, 0, 1.0F, 1.0F, 1.0F,  1.0F);
//        pPoseStack.popPose();
//    }
//    public static int getOverlayCoords( float pU) {
//        return OverlayTexture.pack(OverlayTexture.u(pU), OverlayTexture.v(false));
//    }
//    protected float getWhiteOverlayProgress(EntityJellyBubble pLivingEntity, float pPartialTicks) {
//        return 0.0F;
//    }
    protected int getBlockLightLevel(EntityJellyBubble p_174146_, BlockPos p_174147_) {
        int i = (int) Mth.clampedLerp(0.0F, 15.0F, 1.0F);
        return i == 15 ? 15 : Math.max(i, super.getBlockLightLevel(p_174146_, p_174147_));
    }
}
