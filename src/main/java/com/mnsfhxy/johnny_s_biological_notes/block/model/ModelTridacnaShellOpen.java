// Made with Blockbench 4.8.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

package com.mnsfhxy.johnny_s_biological_notes.block.model;
import com.mnsfhxy.johnny_s_biological_notes.JohnnySBiologicalNotes;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.Model;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;

public class ModelTridacnaShellOpen  extends Model {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(JohnnySBiologicalNotes.MODID, "tridacna_shell_open"), "root");
	private final ModelPart shell;
	public final ModelPart root;

	public ModelTridacnaShellOpen(ModelPart root) {
		super(RenderType::entityCutoutNoCull);
		this.root = root;
		this.shell = root.getChild("shell");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition shell = partdefinition.addOrReplaceChild("shell", CubeListBuilder.create(), PartPose.offset(-16.0F, 23.9F, 0.0F));

		PartDefinition rightshell = shell.addOrReplaceChild("rightshell", CubeListBuilder.create(), PartPose.offset(16.0F, 0.0F, 0.0F));

		PartDefinition rightshell2_r1 = rightshell.addOrReplaceChild("rightshell2_r1", CubeListBuilder.create().texOffs(33, 50).addBox(-4.0F, -10.1F, -7.0F, 2.0F, 10.0F, 14.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(3.0F, -0.8F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition rightshell1_r1 = rightshell.addOrReplaceChild("rightshell1_r1", CubeListBuilder.create().texOffs(39, 0).addBox(-3.0F, -10.0F, -7.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -0.8F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition seagrass1 = rightshell.addOrReplaceChild("seagrass1", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition seagrass1_r1 = seagrass1.addOrReplaceChild("seagrass1_r1", CubeListBuilder.create().texOffs(0, 25).mirror().addBox(-3.0F, -10.0F, -7.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.1F)).mirror(false), PartPose.offsetAndRotation(4.0F, -0.8F, 0.0F, 0.0F, 0.0F, 0.3927F));

		PartDefinition leftshell = shell.addOrReplaceChild("leftshell", CubeListBuilder.create(), PartPose.offset(16.0F, 0.0F, 0.0F));

		PartDefinition leftshell2_r1 = leftshell.addOrReplaceChild("leftshell2_r1", CubeListBuilder.create().texOffs(0, 50).addBox(2.0F, -10.1F, -7.0F, 2.0F, 10.0F, 14.0F, new CubeDeformation(0.3F)), PartPose.offsetAndRotation(-3.0F, -0.8F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition leftshell1_r1 = leftshell.addOrReplaceChild("leftshell1_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-2.0F, -10.0F, -7.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -0.8F, 0.0F, 0.0F, 0.0F, -0.3927F));

		PartDefinition seagrass2 = leftshell.addOrReplaceChild("seagrass2", CubeListBuilder.create(), PartPose.offset(-12.0F, 0.0F, 0.0F));

		PartDefinition seagrass2_r1 = seagrass2.addOrReplaceChild("seagrass2_r1", CubeListBuilder.create().texOffs(40, 25).addBox(-2.0F, -10.0F, -8.0F, 5.0F, 10.0F, 14.0F, new CubeDeformation(0.1F)), PartPose.offsetAndRotation(8.0F, -0.8F, 1.0F, 0.0F, 0.0F, -0.3927F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}



	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		shell.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}


}
