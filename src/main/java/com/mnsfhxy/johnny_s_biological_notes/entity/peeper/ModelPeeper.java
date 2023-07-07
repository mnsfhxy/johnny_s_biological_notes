package com.mnsfhxy.johnny_s_biological_notes.entity.peeper;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mnsfhxy.johnny_s_biological_notes.entity.crab.AnimationCrab;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class ModelPeeper extends HierarchicalModel<EntityPeeper> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JohnnySBiologicalNotes.MODID, "peeper"), "root");
    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart legr;
    private final ModelPart legl;
    private final ModelPart root;
    public ModelPeeper(ModelPart root) {
        this.root=root;
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.legr = root.getChild("legr");
        this.legl = root.getChild("legl");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -7.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
                .texOffs(28, 15).mirror().addBox(-4.0F, 3.0F, -17.0F, 8.0F, 0.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false)
                .texOffs(28, 36).addBox(-4.0F, -4.0F, -17.0F, 0.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(28, 15).addBox(-4.0F, -4.0F, -17.0F, 8.0F, 0.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(28, 36).mirror().addBox(4.0F, -4.0F, -17.0F, 0.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.0F, 5.0F, 2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition grassbody = body.addOrReplaceChild("grassbody", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, -1.5708F, 0.0F, 0.0F));

        PartDefinition cube_r1 = grassbody.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(0, 31).addBox(0.3632F, -2.0F, -4.4289F, 0.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.6F, 0.0F, 2.2864F, 0.0F));

        PartDefinition cube_r2 = grassbody.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 31).addBox(0.0179F, -2.0F, 0.2174F, 0.0F, 2.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, -3.4F, 0.0F, 0.8465F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head", CubeListBuilder.create().texOffs(33, 0).addBox(-4.0F, 1.0F, 0.0F, 8.0F, 8.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 4.0F, -8.0F));

        PartDefinition headbody = head.addOrReplaceChild("headbody", CubeListBuilder.create(), PartPose.offset(0.0F, 20.0F, 8.0F));

        PartDefinition cube_r3 = headbody.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(37, 26).addBox(0.0F, -2.0F, -3.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, -5.0F, 0.0F, 0.9163F, 0.0F));

        PartDefinition cube_r4 = headbody.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(37, 26).addBox(-4.0F, -2.0F, 0.0F, 8.0F, 2.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -19.0F, -5.0F, 0.0F, 0.6545F, 0.0F));

        PartDefinition legr = partdefinition.addOrReplaceChild("legr", CubeListBuilder.create().texOffs(0, 16).mirror().addBox(0.3F, -13.2F, -2.0F, 4.0F, 19.0F, 5.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-8.3F, 18.2F, 1.0F));

        PartDefinition graasslegr = legr.addOrReplaceChild("graasslegr", CubeListBuilder.create(), PartPose.offset(4.3F, -13.2F, -6.0F));

        PartDefinition cube_r5 = graasslegr.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(20, 17).addBox(-0.3023F, -2.0F, -2.5929F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 0.0F, 6.0F, 0.0F, 0.672F, 0.0F));

        PartDefinition cube_r6 = graasslegr.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(20, 17).addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 4.0F, 0.0F, -0.672F, 0.0F));

        PartDefinition legl = partdefinition.addOrReplaceChild("legl", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -13.0F, -2.0F, 4.0F, 19.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, 18.0F, 1.0F));

        PartDefinition grasslegl = legl.addOrReplaceChild("grasslegl", CubeListBuilder.create(), PartPose.offset(-2.0F, -13.0F, 0.0F));

        PartDefinition cube_r7 = grasslegl.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(20, 17).addBox(-0.3023F, -2.0F, -2.5929F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.672F, 0.0F));

        PartDefinition cube_r8 = grasslegl.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(20, 17).mirror().addBox(0.0F, -2.0F, 0.0F, 0.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(2.0F, 0.0F, -2.0F, 0.0F, -0.672F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }



    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        legr.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        legl.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(EntityPeeper entity, float pLimbSwing, float pLimbSwingAmount, float ageInTicks, float pNetHeadYaw, float pHeadPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.animate(entity.walkingAnimationState, AnimationPeeper.WALKING, ageInTicks);
        this.animate(entity.waitingAnimationState, AnimationPeeper.WAITING, ageInTicks);

    }
}
