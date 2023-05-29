package com.mnsfhxy.johnny_s_biological_notes.entity.crab;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class ModelCrab extends HierarchicalModel<EntityCrab> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JohnnySBiologicalNotes.MODID, "crab"), "root");
    private final ModelPart body;
    private final ModelPart claw;
    private final ModelPart paw;
    private final ModelPart root;

    public ModelCrab(ModelPart root) {
        this.root = root;
        this.body = root.getChild("body");
        this.claw = root.getChild("claw");
        this.paw = root.getChild("paw");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -5.0F, -4.0F, 5.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(19, 2).addBox(0.0F, -6.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(19, 2).addBox(0.0F, -6.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition claw = partdefinition.addOrReplaceChild("claw", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition clawL1 = claw.addOrReplaceChild("clawL1", CubeListBuilder.create(), PartPose.offset(1.0F, -2.0F, 3.0F));

        PartDefinition clawL1_r1 = clawL1.addOrReplaceChild("clawL1_r1", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.48F, 0.2182F, 0.0F));

        PartDefinition clawL2 = claw.addOrReplaceChild("clawL2", CubeListBuilder.create(), PartPose.offset(-1.0F, -2.0F, 3.0F));

        PartDefinition clawL2_r1 = clawL2.addOrReplaceChild("clawL2_r1", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4712F, 0.1745F, 0.0F));

        PartDefinition clawL3 = claw.addOrReplaceChild("clawL3", CubeListBuilder.create(), PartPose.offset(-3.0F, -2.0F, 3.0F));

        PartDefinition clawL3_r1 = clawL3.addOrReplaceChild("clawL3_r1", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, -0.4712F, 0.0F, 0.0F));

        PartDefinition clawL4 = claw.addOrReplaceChild("clawL4", CubeListBuilder.create(), PartPose.offset(-3.0F, -2.0F, 2.0F));

        PartDefinition clawL4_r1 = clawL4.addOrReplaceChild("clawL4_r1", CubeListBuilder.create().texOffs(13, 12).addBox(-1.0F, -1.0F, 0.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.1F, 0.0F, -0.5227F, -0.5251F, 0.2483F));

        PartDefinition clawR1 = claw.addOrReplaceChild("clawR1", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -3.0F));

        PartDefinition clawR1_r1 = clawR1.addOrReplaceChild("clawR1_r1", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(0.0F, -1.0F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.48F, -0.2182F, 0.0F));

        PartDefinition clawR2 = claw.addOrReplaceChild("clawR2", CubeListBuilder.create(), PartPose.offset(-1.0F, -2.0F, -3.0F));

        PartDefinition clawR2_r1 = clawR2.addOrReplaceChild("clawR2_r1", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-1.0F, -1.0F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4712F, -0.1745F, 0.0F));

        PartDefinition clawR3 = claw.addOrReplaceChild("clawR3", CubeListBuilder.create(), PartPose.offset(-3.0F, -2.0F, -3.0F));

        PartDefinition clawR3_r1 = clawR3.addOrReplaceChild("clawR3_r1", CubeListBuilder.create().texOffs(0, 19).mirror().addBox(-1.0F, -1.0F, -4.0F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.4712F, 0.0F, 0.0F));

        PartDefinition clawR4 = claw.addOrReplaceChild("clawR4", CubeListBuilder.create(), PartPose.offset(-3.0F, -2.0F, -2.0F));

        PartDefinition clawR4_r1 = clawR4.addOrReplaceChild("clawR4_r1", CubeListBuilder.create().texOffs(13, 12).addBox(-1.0F, -1.0F, -5.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1F, 0.0F, 0.0F, 0.5227F, 0.5251F, 0.2483F));

        PartDefinition paw = partdefinition.addOrReplaceChild("paw", CubeListBuilder.create(), PartPose.offset(-1.0F, 23.9F, 0.0F));

        PartDefinition pawL = paw.addOrReplaceChild("pawL", CubeListBuilder.create(), PartPose.offset(2.0F, -2.0F, 4.2F));

        PartDefinition pawL_r1 = pawL.addOrReplaceChild("pawL_r1", CubeListBuilder.create().texOffs(0, 12).addBox(0.0F, -2.0F, -4.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.5F, 0.0F, -0.2F, 0.1309F, -0.3491F, 0.0F));

        PartDefinition pawR = paw.addOrReplaceChild("pawR", CubeListBuilder.create(), PartPose.offset(2.0F, -2.0F, -4.0F));

        PartDefinition pawR_r1 = pawR.addOrReplaceChild("pawR_r1", CubeListBuilder.create().texOffs(11, 19).addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(-0.1F)), PartPose.offsetAndRotation(-0.5F, 0.0F, 0.0F, -0.1309F, 0.3491F, 0.0F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }


    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        claw.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        paw.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(EntityCrab entity, float pLimbSwing, float pLimbSwingAmount, float ageInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(entity.walkingAnimationState, AnimationCrab.WALKING, ageInTicks);
        this.animate(entity.restAnimationState, AnimationCrab.REST, ageInTicks);
        this.animate(entity.diggingAnimationState, AnimationCrab.DIGGING, ageInTicks);
        this.animate(entity.diggingOutAnimationState, AnimationCrab.DIGGING_OUT, ageInTicks);

    }
}
