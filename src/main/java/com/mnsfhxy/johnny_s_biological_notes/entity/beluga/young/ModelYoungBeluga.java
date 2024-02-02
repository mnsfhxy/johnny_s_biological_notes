package com.mnsfhxy.johnny_s_biological_notes.entity.beluga.young;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class ModelYoungBeluga extends HierarchicalModel<EntityYoungBeluga> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JohnnySBiologicalNotes.MODID, "young_beluga"), "root");
    private final ModelPart root;
    private final ModelPart body;

    public ModelYoungBeluga(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -9.0F, -1.0F, 11.0F, 8.0F, 18.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 25.0F, -9.0F));

        PartDefinition leftarm = body.addOrReplaceChild("leftarm", CubeListBuilder.create(), PartPose.offset(6.0F, -2.0F, 5.9F));

        PartDefinition leftarm_r1 = leftarm.addOrReplaceChild("leftarm_r1", CubeListBuilder.create().texOffs(0, 40).addBox(3.0F, -4.0F, -2.9F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 2.7761F, 1.0001F, 0.0F, -0.1745F, 0.0F));

        PartDefinition rightarm = body.addOrReplaceChild("rightarm", CubeListBuilder.create(), PartPose.offset(-5.0F, -2.0F, 6.0F));

        PartDefinition rightarm_r1 = rightarm.addOrReplaceChild("rightarm_r1", CubeListBuilder.create().texOffs(0, 40).mirror().addBox(-9.0F, -4.0F, -3.9F, 6.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(4.0F, 2.8761F, 1.9001F, 0.0F, 0.1745F, 0.0F));

        PartDefinition tell = body.addOrReplaceChild("tell", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -4.0F, 17.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition tell1 = tell.addOrReplaceChild("tell1", CubeListBuilder.create(), PartPose.offset(0.0F, -0.1124F, -0.1359F));

        PartDefinition tell1_r1 = tell1.addOrReplaceChild("tell1_r1", CubeListBuilder.create().texOffs(59, 0).addBox(-6.0F, 0.5031F, -42.8472F, 9.0F, 7.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, -8.0628F, 41.8621F, 0.0873F, 0.0F, 0.0F));

        PartDefinition tell2 = tell1.addOrReplaceChild("tell2", CubeListBuilder.create(), PartPose.offset(1.0F, -0.0955F, 13.8958F));

        PartDefinition tell2_r1 = tell2.addOrReplaceChild("tell2_r1", CubeListBuilder.create().texOffs(0, 27).addBox(-8.0F, 6.3724F, -34.1415F, 15.0F, 1.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -9.9367F, 33.2605F, 0.0873F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(49, 27).addBox(-4.0F, -2.7F, -7.0F, 9.0F, 5.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -1.0F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(82, 27).addBox(-6.0F, -2.7F, -7.0F, 9.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 5.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(EntityYoungBeluga pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(pEntity.swimmingState, AnimationYoungBeluga.SWIMMING, pAgeInTicks);
        this.animate(pEntity.touchedState, AnimationYoungBeluga.TOUCHED, pAgeInTicks);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }
}
