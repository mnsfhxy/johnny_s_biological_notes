package com.mnsfhxy.johnny_s_biological_notes.entity.loiter;
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
public class ModelLoiter extends HierarchicalModel<EntityLoiter> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("modid", "loiter"), "main");
    private final ModelPart body;
    private final ModelPart root;

    public ModelLoiter(ModelPart root) {
        this.root=this.root();
        this.body = root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create().texOffs(46, 45).addBox(-9.0F, -7.8F, -45.8F, 18.0F, 8.0F, 7.0F, new CubeDeformation(0.9F))
                .texOffs(0, 0).addBox(-9.0F, -10.0F, -39.0F, 18.0F, 10.0F, 34.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 22.0F));

        PartDefinition body2 = body.addOrReplaceChild("body2", CubeListBuilder.create().texOffs(0, 45).addBox(-5.0F, -8.0F, 1.5F, 10.0F, 8.0F, 25.0F, new CubeDeformation(-0.3F)), PartPose.offset(0.0F, -1.0F, -7.0F));

        PartDefinition tell = body2.addOrReplaceChild("tell", CubeListBuilder.create().texOffs(0, 66).addBox(0.0F, -5.0F, 0.0F, 0.0F, 6.0F, 24.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 26.0F));

        PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, -2.0F, -18.0F));

        PartDefinition leftarms = arms.addOrReplaceChild("leftarms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition leftarm1 = leftarms.addOrReplaceChild("leftarm1", CubeListBuilder.create(), PartPose.offset(8.9847F, -2.0F, -19.6756F));

        PartDefinition leftarm1_r1 = leftarm1.addOrReplaceChild("leftarm1_r1", CubeListBuilder.create().texOffs(78, 81).addBox(-2.0F, 0.0F, -4.0F, 15.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.9847F, 0.0F, 3.6756F, 0.0F, -0.4363F, 0.0F));

        PartDefinition leftarm2 = leftarms.addOrReplaceChild("leftarm2", CubeListBuilder.create(), PartPose.offset(8.9847F, -1.0F, -11.6756F));

        PartDefinition leftaem2_r1 = leftarm2.addOrReplaceChild("leftaem2_r1", CubeListBuilder.create().texOffs(39, 80).addBox(-2.0F, 0.0F, -4.0F, 15.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0153F, 0.0F, 4.6756F, 0.0F, -0.4363F, 0.0F));

        PartDefinition leftarm3 = leftarms.addOrReplaceChild("leftarm3", CubeListBuilder.create(), PartPose.offset(9.0F, 0.0F, -2.0F));

        PartDefinition leftarm3_r1 = leftarm3.addOrReplaceChild("leftarm3_r1", CubeListBuilder.create().texOffs(0, 79).addBox(14.4545F, 0.0F, -13.5F, 15.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-20.0F, 0.0F, 4.0F, 0.0F, -0.5236F, 0.0F));

        PartDefinition leftarm4 = leftarms.addOrReplaceChild("leftarm4", CubeListBuilder.create(), PartPose.offset(9.0F, 0.0F, 8.0F));

        PartDefinition leftarm4_r1 = leftarm4.addOrReplaceChild("leftarm4_r1", CubeListBuilder.create().texOffs(63, 71).addBox(-2.0F, 0.0F, -3.0F, 15.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, 3.0F, 0.0F, -0.7854F, 0.0F));

        PartDefinition rightarms = arms.addOrReplaceChild("rightarms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition rightarm1 = rightarms.addOrReplaceChild("rightarm1", CubeListBuilder.create(), PartPose.offset(-9.0F, -2.0F, -20.0F));

        PartDefinition rightarm1_r1 = rightarm1.addOrReplaceChild("rightarm1_r1", CubeListBuilder.create().texOffs(46, 61).addBox(-13.0F, 0.0F, -4.0F, 15.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 4.0F, 0.0F, 0.4363F, 0.0F));

        PartDefinition rightarm2 = rightarms.addOrReplaceChild("rightarm2", CubeListBuilder.create(), PartPose.offset(-9.0F, -1.0F, -10.0F));

        PartDefinition rightarm2_r1 = rightarm2.addOrReplaceChild("rightarm2_r1", CubeListBuilder.create().texOffs(71, 0).addBox(-13.0F, 0.0F, -4.0F, 15.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 3.0F, 0.0F, 0.4363F, 0.0F));

        PartDefinition rightarm3 = rightarms.addOrReplaceChild("rightarm3", CubeListBuilder.create(), PartPose.offset(-9.0F, 0.0F, -2.0F));

        PartDefinition rightarm3_r1 = rightarm3.addOrReplaceChild("rightarm3_r1", CubeListBuilder.create().texOffs(71, 9).addBox(-13.0F, 0.0F, -4.0F, 15.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 0.0F, 4.0F, 0.0F, 0.5236F, 0.0F));

        PartDefinition rightarm4 = rightarms.addOrReplaceChild("rightarm4", CubeListBuilder.create(), PartPose.offset(-8.0F, 0.0F, 8.0F));

        PartDefinition rightarm4_r1 = rightarm4.addOrReplaceChild("rightarm4_r1", CubeListBuilder.create().texOffs(71, 18).addBox(-13.0F, 0.0F, -3.0F, 15.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 3.0F, 0.0F, 0.7854F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }



    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    @Override
    public ModelPart root() {
        return this.root;
    }

    @Override
    public void setupAnim(EntityLoiter pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }
}
