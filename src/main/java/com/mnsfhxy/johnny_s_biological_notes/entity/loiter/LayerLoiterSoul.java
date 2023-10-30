package com.mnsfhxy.johnny_s_biological_notes.entity.loiter;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ai.control.MoveControl;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.monster.Ghast;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class LayerLoiterSoul<T extends EntityLoiter, M extends EntityModel<T>> extends RenderLayer<T, M> {
    private static final RenderType GLOW = RenderType.eyes(new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/loiter_glow.png"));

    public LayerLoiterSoul(RenderLayerParent<T, M> pRenderer) {
        super(pRenderer);
    }

    @Override
    public void render(PoseStack pPoseStack, MultiBufferSource pBuffer, int pPackedLight, T pLivingEntity, float pLimbSwing, float pLimbSwingAmount, float pPartialTick, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
//        if(pLivingEntity.isSoul()&& !pLivingEntity.isInvisible()) {
        if (pLivingEntity.isSoul()) {
            VertexConsumer vertexconsumer = pBuffer.getBuffer(this.renderType());
            this.getParentModel().renderToBuffer(pPoseStack, vertexconsumer, 15728640, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        }

    }


    public RenderType renderType() {
        return GLOW;
    }

}
