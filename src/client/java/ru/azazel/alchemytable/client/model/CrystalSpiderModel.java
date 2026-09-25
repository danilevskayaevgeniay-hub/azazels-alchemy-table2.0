// Made with Blockbench 5.1.6
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
package ru.azazel.alchemytable.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import ru.azazel.alchemytable.AzazelSAlchemyTable;
import ru.azazel.alchemytable.entity.CrystalSpider;

public class CrystalSpiderModel extends EntityModel<CrystalSpider> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new AzazelSAlchemyTable.id("crystal_spider"), "main");
	private final ModelPart bb_main;

	public CrystalSpiderModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
	}

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition bb_main = partdefinition.addOrReplaceChild("bb_main", CubeListBuilder.create().texOffs(32, 20).addBox(-5.0F, -10.0F, -7.0F, 10.0F, 6.0F, 7.0F, new CubeDeformation(0.0F))
		.texOffs(0, 20).addBox(-4.0F, -11.0F, -15.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
		.texOffs(0, 0).addBox(-6.0F, -11.0F, 0.0F, 12.0F, 8.0F, 12.0F, new CubeDeformation(0.0F))
		.texOffs(36, -5).addBox(0.0F, -14.0F, -13.0F, 0.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition cube_r1 = bb_main.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(35, 7).addBox(-1.0F, 0.0F, -4.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(7.0F, -7.0F, 8.0F, 0.0F, 0.0F, -0.2618F));

		PartDefinition cube_r2 = bb_main.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(31, 7).addBox(-1.0F, 0.0F, -4.0F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-7.0F, -7.0F, 8.0F, 0.0F, 0.0F, 0.2618F));

		PartDefinition cube_r3 = bb_main.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(-5, -5).addBox(-1.0F, -3.0F, -1.0F, 0.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -11.0F, 3.0F, 0.0F, 0.0F, 0.1309F));

		PartDefinition cube_r4 = bb_main.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(-5, -5).addBox(-1.0F, -3.0F, -1.0F, 0.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, -11.0F, 3.0F, 0.0F, 0.0F, -0.1309F));

		PartDefinition cube_r5 = bb_main.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(40, 47).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -3.0F, -8.25F, -0.5236F, 0.0F, 1.0472F));

		PartDefinition cube_r6 = bb_main.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(32, 47).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -3.0F, 3.0F, 0.5236F, 0.0F, 1.0472F));

		PartDefinition cube_r7 = bb_main.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(40, 33).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -2.5F, -0.75F, 0.1745F, 0.0F, 1.0472F));

		PartDefinition cube_r8 = bb_main.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(24, 36).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -2.5F, -4.5F, -0.1745F, 0.0F, 1.0472F));

		PartDefinition cube_r9 = bb_main.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(16, 36).addBox(0.25F, -3.0F, 0.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.25F, -3.75F, -0.25F, 0.5236F, 0.0F, -1.0472F));

		PartDefinition cube_r10 = bb_main.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(8, 36).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.25F, -5.0F, -6.0F, -0.5236F, 0.0F, -1.0472F));

		PartDefinition cube_r11 = bb_main.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, -4.75F, -4.0F, -0.1745F, 0.0F, -1.0472F));

		PartDefinition cube_r12 = bb_main.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(32, 33).addBox(-0.75F, -3.0F, 0.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, -4.5F, -2.5F, 0.1745F, 0.0F, -1.0472F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(CrystalSpider entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bb_main.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
