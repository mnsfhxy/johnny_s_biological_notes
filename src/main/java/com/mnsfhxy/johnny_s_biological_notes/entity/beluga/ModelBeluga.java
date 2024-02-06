package com.mnsfhxy.johnny_s_biological_notes.entity.beluga;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

/**
 * 成年白鲸的模型
 */
public class ModelBeluga extends HierarchicalModel<EntityBeluga> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JohnnySBiologicalNotes.MODID, "beluga"), "root");
    private final ModelPart root;
    private final ModelPart body;

    public ModelBeluga(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -10.0F, -1.0F, 11.0F, 12.0F, 36.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 22.0F, -9.0F));

        PartDefinition leftarm = body.addOrReplaceChild("leftarm", CubeListBuilder.create(), PartPose.offset(5.0F, 1.0F, 8.9F));

        PartDefinition leftarm_r1 = leftarm.addOrReplaceChild("leftarm_r1", CubeListBuilder.create().texOffs(0, 0).addBox(0.0F, -1.0F, -3.0F, 9.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.005F, 0.2511F, -0.0119F, 0.0F, -0.1745F, 0.0F));

        PartDefinition rightarm = body.addOrReplaceChild("rightarm", CubeListBuilder.create(), PartPose.offset(-4.0F, 0.0F, 9.0F));

        PartDefinition rightarm_r1 = rightarm.addOrReplaceChild("rightarm_r1", CubeListBuilder.create().texOffs(0, 0).mirror().addBox(-9.0F, -1.0F, -3.0F, 9.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 1.0F, -0.1F, 0.0F, 0.1745F, 0.0F));

        PartDefinition tell = body.addOrReplaceChild("tell", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -6.0F, 3.0F, -0.0873F, 0.0F, 0.0F));

        PartDefinition tell1 = tell.addOrReplaceChild("tell1", CubeListBuilder.create(), PartPose.offset(0.0F, -0.9962F, 32.9128F));

        PartDefinition tell1_r1 = tell1.addOrReplaceChild("tell1_r1", CubeListBuilder.create().texOffs(0, 49).addBox(-6.0F, 0.4031F, -23.8472F, 9.0F, 8.0F, 22.0F, new CubeDeformation(0.8F)), PartPose.offsetAndRotation(2.0F, -6.4069F, 22.9344F, 0.0873F, 0.0F, 0.0F));

        PartDefinition tell2 = tell1.addOrReplaceChild("tell2", CubeListBuilder.create(), PartPose.offset(1.0F, -0.4442F, 17.8806F));

        PartDefinition tell2_r1 = tell2.addOrReplaceChild("tell2_r1", CubeListBuilder.create().texOffs(41, 49).addBox(-9.0F, 6.6834F, -10.9499F, 17.0F, 1.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -7.9321F, 10.3481F, 0.0873F, 0.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(60, 1).addBox(-4.0F, -3.4F, -8.0F, 9.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -6.0F, -1.0F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create(), PartPose.offset(1.0F, 6.0F, 1.0F));

        PartDefinition mouth1 = mouth.addOrReplaceChild("mouth1", CubeListBuilder.create().texOffs(61, 22).addBox(-6.0F, -5.4F, -18.0F, 9.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.0F, 9.0F));

        PartDefinition mouth2 = mouth.addOrReplaceChild("mouth2", CubeListBuilder.create().texOffs(64, 64).addBox(-5.0F, -0.4F, -8.0F, 7.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.0F, -1.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(EntityBeluga pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(pEntity.swimmingState, AnimationBeluga.SWIMMING, pAgeInTicks);
        this.animate(pEntity.touchedState, AnimationBeluga.TOUCHED, pAgeInTicks);
        this.animate(pEntity.attackingState, AnimationBeluga.ATTACKING, pAgeInTicks);
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
