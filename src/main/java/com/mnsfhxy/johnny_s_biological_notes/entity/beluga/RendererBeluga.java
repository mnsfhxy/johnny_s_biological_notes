package com.mnsfhxy.johnny_s_biological_notes.entity.beluga;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RendererBeluga extends MobRenderer<EntityBeluga, EntityModel<EntityBeluga>> {
    private static final ResourceLocation BELUGA_LOCATION = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/beluga.png");

    public RendererBeluga(EntityRendererProvider.Context pContext) {
        super(pContext,
                new ModelBeluga(pContext.bakeLayer(
                        ModelBeluga.LAYER_LOCATION
                )), 0.3F);
    }

    /**
     * 重写方法，实体状态为需要旋转时，对白鲸模型进行旋转
     * @param pEntityLiving
     * @param pMatrixStack
     * @param pAgeInTicks
     * @param pRotationYaw
     * @param pPartialTicks
     */
    @Override
    protected void setupRotations(EntityBeluga pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        super.setupRotations(pEntityLiving, pMatrixStack, pAgeInTicks, pRotationYaw, pPartialTicks);

        if (pEntityLiving.isRotation()) {
            pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(90.0F));
        }
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBeluga pEntity) {
        return BELUGA_LOCATION;
    }
}
