package com.mnsfhxy.johnny_s_biological_notes.entity.beluga;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.beluga.young.ModelYoungBeluga;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RendererBeluga extends MobRenderer<EntityBeluga, EntityModel<EntityBeluga>> {
    private static final ResourceLocation BELUGA_LOCATION = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/beluga.png");
    private static final ResourceLocation YOUNG_BELUGA_LOCATION = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/young_beluga.png");
    private final ModelBeluga adultModel;
    private final ModelYoungBeluga babyModel;

    public RendererBeluga(EntityRendererProvider.Context pContext) {
        super(pContext, null, 0.3F);

        this.adultModel = new ModelBeluga(pContext.bakeLayer(
                ModelBeluga.LAYER_LOCATION
        ));
        this.babyModel = new ModelYoungBeluga(pContext.bakeLayer(
                ModelYoungBeluga.LAYER_LOCATION
        ));
    }

    @Override
    public void render(EntityBeluga pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {
        this.model = pEntity.isBaby() ? this.babyModel : this.adultModel;
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBeluga pEntity) {
        if (pEntity.isBaby())
            return YOUNG_BELUGA_LOCATION;
        return BELUGA_LOCATION;
    }
}
