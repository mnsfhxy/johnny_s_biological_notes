package com.mnsfhxy.johnny_s_biological_notes.entity.peeper;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
@OnlyIn(Dist.CLIENT)
public class LayerPeeperEye<E extends PathfinderMob, E1 extends Model> extends EyesLayer<EntityPeeper, EntityModel<EntityPeeper>> {
    private static final RenderType EYE = RenderType.eyes(new ResourceLocation(JohnnySBiologicalNotes.MODID, "textures/entity/peeper_eye.png"));

    public LayerPeeperEye(RenderLayerParent<EntityPeeper, EntityModel<EntityPeeper>> pRenderer) {
        super(pRenderer);
    }


    public RenderType renderType() {
        return EYE;
    }
}

