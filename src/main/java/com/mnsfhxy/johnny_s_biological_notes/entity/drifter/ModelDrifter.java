package com.mnsfhxy.johnny_s_biological_notes.entity.drifter;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.init.RegistrationInit;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.animation.KeyframeAnimations;
import net.minecraft.client.model.AnimationUtils;
import net.minecraft.client.model.ArmedModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class ModelDrifter  extends HierarchicalModel<EntityDrifter> implements ArmedModel {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JohnnySBiologicalNotes.MODID, "drifter"), "root");
    private final ModelPart body;
    private final ModelPart arms;
    private final ModelPart leftarm;
    private final ModelPart rightarm;
    private final ModelPart LeftLeg;
    private final ModelPart RightLeg;
    private final ModelPart root;
    private final ModelPart head;

//    private final ModelPart item;

    public ModelDrifter(ModelPart root) {
        this.root=root;
        this.body = root.getChild("body");
        this.arms = root.getChild("arms");
        this.leftarm = root.getChild("leftarm");
        this.rightarm = root.getChild("rightarm");
        this.LeftLeg = root.getChild("LeftLeg");
        this.RightLeg = root.getChild("RightLeg");
        this.head=body.getChild("head");
//        this.item=rightarm.getChild("item");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, -1.0F));

        PartDefinition body_1 = body.addOrReplaceChild("body_1", CubeListBuilder.create().texOffs(35, 19).addBox(-4.0F, -7.0F, -3.0F, 8.0F, 12.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-4.0F, -7.0F, -3.0F, 8.0F, 19.0F, 6.0F, new CubeDeformation(0.5F)), PartPose.offset(0.0F, -17.0F, 0.0F));

        PartDefinition head = body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(29, 0).addBox(-4.0F, -10.0F, -4.0F, 8.0F, 10.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -24.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition hat = head.addOrReplaceChild("hat", CubeListBuilder.create().texOffs(0, 26).addBox(-8.0F, -8.0F, -7.0F, 16.0F, 16.0F, 1.0F, new CubeDeformation(0.1F))
                .texOffs(35, 38).addBox(-7.0F, -7.0F, -8.0F, 14.0F, 14.0F, 1.0F, new CubeDeformation(0.1F))
                .texOffs(0, 44).addBox(-5.0F, -5.0F, -9.0F, 10.0F, 10.0F, 1.0F, new CubeDeformation(0.1F))
                .texOffs(62, 0).addBox(-4.0F, -4.0F, -10.0F, 8.0F, 8.0F, 1.0F, new CubeDeformation(0.1F))
                .texOffs(62, 10).addBox(-3.0F, -3.0F, -11.0F, 6.0F, 6.0F, 1.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition nose = head.addOrReplaceChild("nose", CubeListBuilder.create().texOffs(23, 44).addBox(-1.0F, -1.0F, -6.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));

        PartDefinition arms = partdefinition.addOrReplaceChild("arms", CubeListBuilder.create().texOffs(53, 54).addBox(-4.0F, 2.0F, -2.0F, 8.0F, 4.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 56).addBox(-8.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 56).mirror().addBox(4.0F, -2.0F, -2.0F, 4.0F, 8.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 2.0F, -1.0F, -0.7854F, 0.0F, 0.0F));

        PartDefinition leftarm = partdefinition.addOrReplaceChild("leftarm", CubeListBuilder.create().texOffs(19, 52).mirror().addBox(-1.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, 2.0F, -1.0F));

        PartDefinition rightarm = partdefinition.addOrReplaceChild("rightarm", CubeListBuilder.create().texOffs(19, 52).addBox(-3.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, -1.0F));

        PartDefinition LeftLeg = partdefinition.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(36, 54).mirror().addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(2.0F, 12.0F, -1.0F));

        PartDefinition RightLeg = partdefinition.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(36, 54).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 12.0F, -1.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(EntityDrifter entity, float pLimbSwing, float pLimbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        this.head.xRot = headPitch * ((float)Math.PI / 180F);
//        this.animate(entity.alert0AnimationState,AnimationDrifter.DRIFTERS_ALERT_0,ageInTicks);
//        this.animate(entity.alert1AnimationState,AnimationDrifter.DRIFTERS_ALERT_1,ageInTicks);
        this.animate(entity.fightAnimationState,AnimationDrifter.DRIFTERS_FIGHT,ageInTicks);
        this.animate(entity.alertAnimationState,AnimationDrifter.DRIFTERS_ALERT,ageInTicks);
//        this.animate(entity.fightAnimationState.getAnimationState(),AnimationDrifter.DRIFTERS_FIGHT,ageInTicks);
        this.RightLeg.xRot = Mth.cos(pLimbSwing * 0.6662F) * 1.4F * pLimbSwingAmount * 0.5F;
        this.LeftLeg.xRot = Mth.cos(pLimbSwing * 0.6662F + (float)Math.PI) * 1.4F * pLimbSwingAmount * 0.5F;
        this.RightLeg.yRot = 0.0F;
        this.LeftLeg.yRot = 0.0F;
//      this.animate(entity.walkingAnimationState,AnimationDrifter.DRIFTERS_WALKING,ageInTicks);

    }
    public ModelPart getHead() {
        return this.head;
    }

    public ModelPart root() {
        return this.root;
    }

    public void translateToHand(HumanoidArm pSide, PoseStack pPoseStack) {
        this.getArm(pSide).translateAndRotate(pPoseStack);
//        pPoseStack.translate(0,2,0);
    }

    protected ModelPart getArm(HumanoidArm pSide) {
//        return  item;
//        return pSide == HumanoidArm.LEFT ? this.leftarm : this.rightarm;
        return this.rightarm;
    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        arms.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leftarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        rightarm.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        LeftLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        RightLeg.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
