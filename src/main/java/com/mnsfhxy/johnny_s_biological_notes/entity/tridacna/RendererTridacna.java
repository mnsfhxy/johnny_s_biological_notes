package com.mnsfhxy.johnny_s_biological_notes.entity.tridacna;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.jelly.ModelJelly;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class RendererTridacna extends MobRenderer<EntityTridacna, ModelTridacna> {

    private final ModelTridacnaClosed modelTridacnaClosed;
    private final ModelTridacna modelTridacna=this.getModel();

    public RendererTridacna(EntityRendererProvider.Context pContext) {
        super(pContext, new ModelTridacna(pContext.bakeLayer(ModelTridacna.LAYER_LOCATION)), 0.385F);
        this.modelTridacnaClosed = new ModelTridacnaClosed(pContext.bakeLayer(ModelTridacnaClosed.LAYER_LOCATION));
    }

    private static final ResourceLocation TRIDACNA = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/tridacna/tridacna.png");
    private static final ResourceLocation OLDER_TRIDACNA = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/tridacna/older_tridacna.png");
    private static final ResourceLocation TRIDACNA_BROKEN = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/tridacna/tridacna_broken.png");
    private static final ResourceLocation OLDER_TRIDACNA_BROKEN = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/tridacna/older_tridacna_broken.png");

    @Override
    public @NotNull ResourceLocation getTextureLocation(EntityTridacna pEntity) {
        if(pEntity.getVariant()==0){
            return TRIDACNA;
        }else if(pEntity.getVariant()==1){
            return TRIDACNA_BROKEN;
        }else if(pEntity.getVariant()==2){
            return OLDER_TRIDACNA;
        }else if(pEntity.getVariant()==3){
            return OLDER_TRIDACNA_BROKEN;
        }else {
            return TRIDACNA;
        }

    }

    @Override
    public void render(EntityTridacna pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack, MultiBufferSource pBuffer, int pPackedLight) {

        if (pEntity.isShellOpen()) {
            this.model=modelTridacna;

        } else {
            this.model=modelTridacnaClosed;
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
