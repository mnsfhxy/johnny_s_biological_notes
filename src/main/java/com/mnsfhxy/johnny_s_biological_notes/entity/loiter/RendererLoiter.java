package com.mnsfhxy.johnny_s_biological_notes.entity.loiter;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.EntityJelly;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.EntityPeeper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class RendererLoiter extends MobRenderer<EntityLoiter, EntityModel<EntityLoiter>> {
    private static final ResourceLocation LOITER = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/loiter.png");
//    private static final ResourceLocation LOITER_GLOW = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/loiter_glow.png");
    private static final ResourceLocation LOITER_SOUL = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/loiter_soul.png");

    public RendererLoiter(EntityRendererProvider.Context pContext) {
        super(pContext, new ModelLoiter(pContext.bakeLayer(ModelLoiter.LAYER_LOCATION)),1.0F);
        this.addLayer(new LayerLoiterSoul<>(this));
    }


    @Override
    public ResourceLocation getTextureLocation(EntityLoiter pEntity) {
        return pEntity.isSoul()?LOITER_SOUL:LOITER;
    }
    @Override
    protected void setupRotations(EntityLoiter pEntityLiving, PoseStack pMatrixStack, float pAgeInTicks, float pRotationYaw, float pPartialTicks) {
        float f = Mth.lerp(pPartialTicks, pEntityLiving.xBodyRotO, pEntityLiving.xBodyRot);
        float f1 = Mth.lerp(pPartialTicks, pEntityLiving.zBodyRotO, pEntityLiving.zBodyRot);
        pMatrixStack.translate(0.0D, 0.5D, 0.0D);
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(180.0F - pRotationYaw));
        pMatrixStack.mulPose(Vector3f.XP.rotationDegrees(f));
        pMatrixStack.mulPose(Vector3f.YP.rotationDegrees(f1));
        pMatrixStack.translate(0.0D, (double)-1.2F, 0.0D);
    }
}
