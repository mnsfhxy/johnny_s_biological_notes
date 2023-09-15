package com.mnsfhxy.johnny_s_biological_notes.entity.jelly;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.GlowSquid;

public class RendererJelly extends MobRenderer<EntityJelly,ModelJelly> {
    private static final ResourceLocation JELLY = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/jelly.png");
    private RenderType renderType;
    public RendererJelly(EntityRendererProvider.Context pContext) {
        super(pContext, new ModelJelly(pContext.bakeLayer(ModelJelly.LAYER_LOCATION)), 0.385F);
        this.renderType = RenderType.entityTranslucent(JELLY);

    }

    @Override
    public ResourceLocation getTextureLocation(EntityJelly pEntity) {
        return JELLY;
    }

    @Override
    protected RenderType getRenderType(EntityJelly pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        return this.renderType;

    }
    protected void setupRotations(EntityJelly pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        float f = Mth.lerp(pPartialTicks, pEntityLiving.xBodyRotO, pEntityLiving.xBodyRot);
        float f1 = Mth.lerp(pPartialTicks, pEntityLiving.zBodyRotO, pEntityLiving.zBodyRot);
        pMatrixStack.translate(0.0D, 0.5D, 0.0D);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - pRotationYaw));
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(f));
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(f1));
        pMatrixStack.translate(0.0D, (double)-1.2F, 0.0D);
    }
    protected int getBlockLightLevel(EntityJelly p_174146_, BlockPos p_174147_) {
        int i = (int)Mth.clampedLerp(0.0F, 15.0F, 1.0F );
        return i == 15 ? 15 : Math.max(i, super.getBlockLightLevel(p_174146_, p_174147_));
    }
}
