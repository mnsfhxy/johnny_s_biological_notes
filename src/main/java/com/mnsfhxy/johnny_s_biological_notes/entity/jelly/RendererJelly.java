package com.mnsfhxy.johnny_s_biological_notes.entity.jelly;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

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
}
