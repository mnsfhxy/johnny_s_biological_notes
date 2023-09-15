package com.mnsfhxy.johnny_s_biological_notes.entity.tridacna;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
public class ModelTridacna extends HierarchicalModel<EntityTridacna> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JohnnySBiologicalNotes.MODID, "tridacna"), "root");

    private  ModelPart shell;
    private  ModelPart root;

    public ModelTridacna(ModelPart root) {
        this.root=root;
        this.shell = root.getChild("shell");
    }

    public ModelTridacna() {

    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition shell = partdefinition.addOrReplaceChild("shell", CubeListBuilder.create(), PartPose.offset(-16.0F, 23.9F, 0.0F));

        PartDefinition rightshell = shell.addOrReplaceChild("rightshell", CubeListBuilder.create(), PartPose.offset(16.0F, 0.0F, 0.0F));

        PartDefinition rightshell2_r1 = rightshell.addOrReplaceChild("rightshell2_r1", CubeListBuilder.create().texOffs(33, 50).addBox(-4.0F, -10.1F, -7.0F, 2.0F, 10.0F, 14.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(3.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition rightshell1_r1 = rightshell.addOrReplaceChild("rightshell1_r1", CubeListBuilder.create().texOffs(39, 0).addBox(-3.0F, -10.0F, -7.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition body2 = rightshell.addOrReplaceChild("body2", CubeListBuilder.create(), PartPose.offset(-18.0F, -2.0F, 0.0F));

        PartDefinition body2_r1 = body2.addOrReplaceChild("body2_r1", CubeListBuilder.create().texOffs(0, 75).mirror().addBox(-2.0F, -8.0F, -6.0F, 2.0F, 9.0F, 12.0F, new CubeDeformation(0.4F)).mirror(false), PartPose.offsetAndRotation(20.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition seagrass1 = rightshell.addOrReplaceChild("seagrass1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition seagrass1_r1 = seagrass1.addOrReplaceChild("seagrass1_r1", CubeListBuilder.create().texOffs(0, 25).mirror().addBox(-3.0F, -10.0F, -7.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(4.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.2618F));

        PartDefinition leftshell = shell.addOrReplaceChild("leftshell", CubeListBuilder.create(), PartPose.offset(16.0F, 0.0F, 0.0F));

        PartDefinition leftshell2_r1 = leftshell.addOrReplaceChild("leftshell2_r1", CubeListBuilder.create().texOffs(0, 50).addBox(2.0F, -10.1F, -7.0F, 2.0F, 10.0F, 14.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition leftshell1_r1 = leftshell.addOrReplaceChild("leftshell1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -10.0F, -7.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition body1 = leftshell.addOrReplaceChild("body1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition body1_r1 = body1.addOrReplaceChild("body1_r1", CubeListBuilder.create().texOffs(0, 75).addBox(0.0F, -8.0F, -6.0F, 2.0F, 9.0F, 12.0F, new CubeDeformation(0.4F)), PartPose.offsetAndRotation(-2.0F, -2.0F, 0.0F, 0.0F, 0.0F, -0.2618F));

        PartDefinition seagrass2 = leftshell.addOrReplaceChild("seagrass2", CubeListBuilder.create(), PartPose.offset(-12.0F, 0.0F, 0.0F));

        PartDefinition seagrass2_r1 = seagrass2.addOrReplaceChild("seagrass2_r1", CubeListBuilder.create().texOffs(40, 25).addBox(-2.0F, -10.0F, -8.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(8.0F, 0.0F, 1.0F, 0.0F, 0.0F, -0.2618F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }
    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        shell.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(EntityTridacna pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }
}
