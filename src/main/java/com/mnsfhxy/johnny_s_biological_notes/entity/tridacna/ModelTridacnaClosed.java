package com.mnsfhxy.johnny_s_biological_notes.entity.tridacna;

import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;

public class ModelTridacnaClosed extends ModelTridacna {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JohnnySBiologicalNotes.MODID, "tridacna_closed"), "root");

    private final ModelPart shell;
    private final ModelPart root;
    private final ModelPart bb_main;
    public ModelTridacnaClosed(ModelPart root) {
        super();
        this.root=root;
        this.shell = root.getChild("shell");
        this.bb_main = root.getChild("bb_main");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition shell = partdefinition.addOrReplaceChild("shell", CubeListBuilder.create(), PartPose.offset(0.0F, 27.0F, 0.0F));

        PartDefinition leftshell = shell.addOrReplaceChild("leftshell", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -10.1F, -7.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(0, 50).addBox(-1.5F, -10.1F, -7.0F, 2.0F, 10.0F, 14.0F, new CubeDeformation(0.3F)), PartPose.offset(-1.0F, -2.9F, 0.0F));

        PartDefinition leftseagrass = leftshell.addOrReplaceChild("leftseagrass", CubeListBuilder.create().texOffs(40, 25).addBox(-7.0F, -10.1F, -7.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.1F)), PartPose.offset(1.0F, 0.0F, 0.0F));

        PartDefinition rightshell = shell.addOrReplaceChild("rightshell", CubeListBuilder.create().texOffs(39, 0).addBox(2.0F, -10.1F, -7.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.0F))
                .texOffs(33, 50).addBox(0.5F, -10.1F, -7.0F, 2.0F, 10.0F, 14.0F, new CubeDeformation(0.3F)), PartPose.offset(-0.4F, -2.9F, 0.0F));

        PartDefinition rightseagrass = rightshell.addOrReplaceChild("rightseagrass", CubeListBuilder.create().texOffs(0, 25).mirror().addBox(1.0F, -10.1F, -7.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offset(1.0F, 0.0F, 0.0F));

        PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(78, 0).addBox(-1.7F, -10.0F, -7.0F, 3.0F, 10.0F, 14.0F, new CubeDeformation(0.25F)), PartPose.offset(0.0F, 24.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        shell.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    public void setupAnim(EntityTridacna pEntity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {

    }
}
