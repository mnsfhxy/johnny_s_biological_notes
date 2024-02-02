package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.young;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RendererYoungBeluga extends MobRenderer<EntityYoungBeluga, EntityModel<EntityYoungBeluga>> {
    private static final ResourceLocation YOUNG_BELUGA_LOCATION = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/young_beluga.png");

    public RendererYoungBeluga(EntityRendererProvider.Context pContext) {
        super(pContext,
                new ModelYoungBeluga(pContext.bakeLayer(
                        ModelYoungBeluga.LAYER_LOCATION
                )), 0.3F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityYoungBeluga pEntity) {
        return YOUNG_BELUGA_LOCATION;
    }
}
