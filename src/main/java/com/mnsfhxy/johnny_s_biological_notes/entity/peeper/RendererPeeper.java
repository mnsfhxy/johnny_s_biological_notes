package com.mnsfhxy.johnny_s_biological_notes.entity.peeper;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RendererPeeper extends MobRenderer<EntityPeeper, EntityModel<EntityPeeper>> {
    private static final ResourceLocation PEEPER = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/peeper.png");

    public RendererPeeper(EntityRendererProvider.Context pContext) {
        super(pContext, new ModelPeeper(pContext.bakeLayer(ModelPeeper.LAYER_LOCATION)), 0.375F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPeeper pEntity) {
        return PEEPER;
    }
}
