package com.mnsfhxy.johnny_s_biological_notes.entity.crab;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RendererCrab extends MobRenderer<EntityCrab, EntityModel<EntityCrab>> {

    private static final ResourceLocation RED = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/crab/crab_red.png");
    private static final ResourceLocation BROWN = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/crab/crab_brown.png");
    private static final ResourceLocation SPOTTED = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/crab/crab_spotted.png");
    private static final ResourceLocation CYAN = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/crab/crab_cyan.png");
    private static final ResourceLocation BLACK = new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/crab/crab_black.png");

    public RendererCrab(EntityRendererProvider.Context renderManagerIn) {
        super(renderManagerIn, new ModelCrab(renderManagerIn.bakeLayer(ModelCrab.LAYER_LOCATION)), 0.375F);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityCrab entity) {

            switch (entity.getColor()){
                case "RED" :return RED;
                case "BROWN" :return BROWN;
                case "SPOTTED" :return SPOTTED;
                case "CYAN" :return CYAN;
                case "BLACK" :return BLACK;
            }
        return BLACK;
    }

}
