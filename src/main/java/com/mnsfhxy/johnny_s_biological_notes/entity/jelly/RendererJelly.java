package com.mnsfhxy.johnny_s_biological_notes.entity.jelly;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.ModelCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.EntityDrifter;
import com.mnsfhxy.johnny_s_biological_notes.entity.drifter.ModelDrifter;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.ModelPeeper;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class RendererJelly extends MobRenderer<EntityJelly, ModelJelly> {
    protected RenderType renderType;
    private static final ResourceLocation LAYER_LOCATION = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/jelly.png");

    public RendererJelly(EntityRendererProvider.Context pContext) {
        super(pContext, new ModelJelly(pContext.bakeLayer(ModelJelly.LAYER_LOCATION)), 0.375F);
    }


    @Override
    public ResourceLocation getTextureLocation(EntityJelly pEntity) {
        return LAYER_LOCATION;
    }

    @Nullable
    @Override
    protected RenderType getRenderType(EntityJelly pLivingEntity, boolean pBodyVisible, boolean pTranslucent, boolean pGlowing) {
        this.renderType = RenderType.entityTranslucent(LAYER_LOCATION);
        return this.renderType;
    }
}
