package com.mnsfhxy.johnny_s_biological_notes.block.berenderer;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.block.blockentity.BETridacnaShell;
import com.mnsfhxy.johnny_s_biological_notes.block.model.ModelTridacnaShellOpen;
import com.mnsfhxy.johnny_s_biological_notes.entity.tridacna.ModelTridacna;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;

public class RendererTridacnaShell implements BlockEntityRenderer<BETridacnaShell> {
    ResourceLocation TridacnaShellRS=new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/block/tridacna_shell.png");
    private final ModelTridacna OPEN_MODEL;
//    private final Model CLOSED_MODEL;

    public RendererTridacnaShell(BlockEntityRendererProvider.Context pContext) {
        this.OPEN_MODEL=new ModelTridacna(pContext.bakeLayer(ModelTridacnaShellOpen.LAYER_LOCATION));
    }
    @Override
    public void render(BETridacnaShell pBlockEntity, float pPartialTick, PoseStack pPoseStack, MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        pPoseStack.pushPose();
        pPoseStack.translate(0.5D, -0.5D, 0.5D);

        VertexConsumer vertexconsumer = pBufferSource.getBuffer(RenderType.entityNoOutline(TridacnaShellRS));
        this.OPEN_MODEL.renderToBuffer(pPoseStack,vertexconsumer,pPackedLight, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        pPoseStack.popPose();

    }
}
