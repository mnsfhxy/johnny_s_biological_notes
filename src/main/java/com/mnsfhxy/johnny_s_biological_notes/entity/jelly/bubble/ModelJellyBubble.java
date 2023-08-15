package com.mnsfhxy.johnny_s_biological_notes.entity.jelly.bubble;


import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.peeper.AnimationPeeper;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.Entity;

public class ModelJellyBubble extends HierarchicalModel<EntityJellyBubble> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor

    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JohnnySBiologicalNotes.MODID, "jelly_bubble"), "root");
    private final ModelPart jelly_bubble;
    private final ModelPart root;
    public ModelJellyBubble(ModelPart root) {
        this.root=root;
        this.jelly_bubble = root.getChild("jelly_bubble");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition jelly_bubble = partdefinition.addOrReplaceChild("jelly_bubble", CubeListBuilder.create().texOffs(0, 0).addBox(-7.6F, -2.0F, -7.4F, 14.0F, 14.0F, 14.0F, new CubeDeformation(-4.5F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        PartDefinition jelly_bubble_core = jelly_bubble.addOrReplaceChild("jelly_bubble_core", CubeListBuilder.create(), PartPose.offset(-0.6F, 5.3F, -0.5F));

        PartDefinition jelly_bubble_core_r1 = jelly_bubble_core.addOrReplaceChild("jelly_bubble_core_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-1.5F, -2.7F, -1.5F, 3.0F, 3.0F, 3.0F, new CubeDeformation(-0.3F)), PartPose.offsetAndRotation(-0.2F, 0.9F, -0.6F, -0.7854F, -0.7854F, 0.7854F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(EntityJellyBubble entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(entity.bubbleAnimationState, AnimationJellyBubble.JELLY_BUBBLE, ageInTicks);

    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {

        jelly_bubble.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
