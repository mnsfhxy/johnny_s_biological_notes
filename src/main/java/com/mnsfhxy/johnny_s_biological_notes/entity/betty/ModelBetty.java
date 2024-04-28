package com.mnsfhxy.johnny_s_biological_notes.entity.betty;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.AnimationCrab;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.EntityCrab;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.HumanoidArm;

public class ModelBetty extends HierarchicalModel<EntityBetty> implements ArmedModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JohnnySBiologicalNotes.MODID, "root"), "root");
    private final ModelPart body;
    private final ModelPart root;
    private final ModelPart head;
    private final ModelPart right_arm;
    private final ModelPart left_arm;
    private final ModelPart right_wing;
    private final ModelPart left_wing;
    public ModelBetty(ModelPart root) {
        this.root = root.getChild("root");;
        this.head = this.root.getChild("head");
        this.body = this.root.getChild("body");
        this.right_arm = this.body.getChild("right_arm");
        this.left_arm = this.body.getChild("left_arm");
        this.right_wing = this.body.getChild("right_wing");
        this.left_wing = this.body.getChild("left_wing");
    }


    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 25.0F, 0.0F));

        PartDefinition head = root.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-2.5F, -5.0F, -2.5F, 5.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition headed = head.addOrReplaceChild("headed", CubeListBuilder.create().texOffs(4, 12).addBox(-4.0F, -2.0F, -3.9F, 8.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 10).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 4.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(-0.2F)), PartPose.offset(0.0F, -5.0F, 0.0F));

        PartDefinition rightItem = body.addOrReplaceChild("rightItem", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 5.0F, -2.0F, -1.3963F, 0.0F, 0.0F));

        PartDefinition right_arm = body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(23, 0).addBox(-0.75F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.75F, 0.5F, 0.0F));

        PartDefinition left_arm = body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(23, 6).addBox(-0.25F, -0.5F, -1.0F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(1.75F, 0.5F, 0.0F));

        PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(0, 19).addBox(0.0F, -1.0F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 1.0F, 1.0F));

        PartDefinition right_wing = body.addOrReplaceChild("right_wing", CubeListBuilder.create(), PartPose.offset(-1.0F, 0.0F, 1.0F));

        PartDefinition right_wing_r1 = right_wing.addOrReplaceChild("right_wing_r1", CubeListBuilder.create().texOffs(16, 14).addBox(0.0F, -1.0F, 0.0F, 0.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        root.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
    @Override
    public ModelPart root() {
        return this.root;
    }
    @Override
    public void setupAnim(EntityBetty entity, float pLimbSwing, float pLimbSwingAmount, float ageInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(entity.clapAnimationState, AnimationBetty.clap,ageInTicks);
        this.animate(entity.holdItemFlyAnimationState, AnimationBetty.hold_item_fly, ageInTicks);
        this.animate(entity.holdItemAnimationState, AnimationBetty.hold_item, ageInTicks);
        this.animate(entity.idleAnimationState, AnimationBetty.idle, ageInTicks);
        this.animate(entity.flyAnimationState, AnimationBetty.fly, ageInTicks);
    }

    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        this.getArm(pSide).translateAndRotate(pPoseStack);
//        float f = -1.5F;
//        float f1 = 1.5F;
//        this.root.translateAndRotate(pPoseStack);
//        this.body.translateAndRotate(pPoseStack);
//        pPoseStack.translate(0.0D, -0.09375D, 0.09375D);
//        pPoseStack.mulPose(Vector3f.XP.rotation(this.right_arm.xRot + 0.43633232F));
//        pPoseStack.scale(0.7F, 0.7F, 0.7F);
//        pPoseStack.translate(0.0625D, 0.0D, 0.0D);
    }
    protected ModelPart getArm(HumanoidArm pSide) {
//        return  item;
//        return pSide == HumanoidArm.LEFT ? this.leftarm : this.rightarm;
        return this.right_arm;
    }
}
