package com.mnsfhxy.johnny_s_biological_notes.entity.jelly;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class ModelJelly extends HierarchicalModel<EntityJelly> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JohnnySBiologicalNotes.MODID, "jelly"), "root");
    private final ModelPart bell_0;
    private final ModelPart tentacle;
    private final ModelPart oral_arms;
    private final ModelPart core;
    private final ModelPart root;

    public ModelJelly(ModelPart root) {
        this.root=root;
        this.bell_0 = root.getChild("bell_0");
        this.tentacle = root.getChild("tentacle");
        this.oral_arms = root.getChild("oral_arms");
        this.core = root.getChild("core");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bell_0 = partdefinition.addOrReplaceChild("bell_0", CubeListBuilder.create().texOffs(33, 1).addBox(-3.0F, -13.0F, -3.0F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition tentacle = partdefinition.addOrReplaceChild("tentacle", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -7.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition oral_arms = partdefinition.addOrReplaceChild("oral_arms", CubeListBuilder.create().texOffs(0, 13).addBox(-3.0F, -7.0F, -3.0F, 6.0F, 7.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition core = partdefinition.addOrReplaceChild("core", CubeListBuilder.create().texOffs(19, 13).addBox(-1.0F, -2.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 16.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }



    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bell_0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        tentacle.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        oral_arms.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }


    @Override
    public void setupAnim(EntityJelly pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(pEntity.movingAnimation, AnimationJelly.JELLY_MOVING, pAgeInTicks);

    }
    @Override
    public ModelPart root() {
        return this.root;
    }
}
