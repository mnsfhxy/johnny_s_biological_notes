package com.mnsfhxy.johnny_s_biological_notes.entity.betty;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;

public class RendererBetty extends MobRenderer<EntityBetty, ModelBetty>  {
    private static final ResourceLocation Betty = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/betty.png");

    public RendererBetty(EntityRendererProvider.Context pContext) {
        super(pContext, new ModelBetty(pContext.bakeLayer(ModelBetty.LAYER_LOCATION)), 0.4F);

        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()));
    }

    @Override
    public ResourceLocation getTextureLocation(EntityBetty pEntity) {
        return Betty;
    }
}
