package com.mnsfhxy.johnny_s_biological_notes.entity.drifter;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RendererDrifter extends MobRenderer<EntityDrifter, EntityModel<EntityDrifter>> {
    private static final ResourceLocation Drifter = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/drifter.png");

    public RendererDrifter(EntityRendererProvider.Context pContext) {
        super(pContext,new ModelDrifter(pContext.bakeLayer(ModelDrifter.LAYER_LOCATION)),0.375F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDrifter pEntity) {
        return Drifter;
    }
}
