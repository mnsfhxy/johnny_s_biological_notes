package com.mnsfhxy.johnny_s_biological_notes.entity.drifter;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.IllagerModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.ItemInHandLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.monster.Vindicator;

public class RendererDrifter extends MobRenderer<EntityDrifter, ModelDrifter> {
    private static final ResourceLocation Drifter = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/drifter.png");

    public RendererDrifter(EntityRendererProvider.Context pContext) {
        super(pContext, new ModelDrifter(pContext.bakeLayer(ModelDrifter.LAYER_LOCATION)), 0.375F);
        this.addLayer(new ItemInHandLayer<>(this, pContext.getItemInHandRenderer()){
            public void render(PoseStack p_116352_, MultiBufferSource p_116353_, int p_116354_, EntityDrifter p_116355_, float p_116356_, float p_116357_, float p_116358_, float p_116359_, float p_116360_, float p_116361_) {
                if (p_116355_.isRenderItem()) {
                    super.render(p_116352_, p_116353_, p_116354_, p_116355_, p_116356_, p_116357_, p_116358_, p_116359_, p_116360_, p_116361_);
                }
            }
        });
    }

    @Override
    public ResourceLocation getTextureLocation(EntityDrifter pEntity) {
        return Drifter;
    }
}
