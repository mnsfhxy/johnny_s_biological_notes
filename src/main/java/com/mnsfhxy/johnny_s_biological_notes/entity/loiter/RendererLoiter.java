package com.mnsfhxy.johnny_s_biological_notes.entity.loiter;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.EntityPeeper;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class RendererLoiter extends MobRenderer<EntityLoiter, EntityModel<EntityLoiter>> {
    private static final ResourceLocation LOITER = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/loiter.png");
    private static final ResourceLocation LOITER_GLOW = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/loiter_glow.png");
    private static final ResourceLocation LOITER_SOUL = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/loiter_soul.png");

    public RendererLoiter(EntityRendererProvider.Context pContext) {
        super(pContext, new ModelLoiter(pContext.bakeLayer(ModelLoiter.LAYER_LOCATION)),1.0F);
    }


    @Override
    public ResourceLocation getTextureLocation(EntityLoiter pEntity) {
        return LOITER;
    }
}
