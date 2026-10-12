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
import net.minecraft.util.Mth;

public class CrystalSpiderModel extends EntityModel<CrystalSpider> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(AzazelSAlchemyTable.id("crystal_spider"), "main");
	private final ModelPart bb_main;
	private final ModelPart leg1;
	private final ModelPart leg2;

	public CrystalSpiderModel(ModelPart root) {
		this.bb_main = root.getChild("bb_main");
		this.leg1 = this.bb_main.getChild("leg1"); // НОВОЕ: теперь двигаем отдельную кость у корпуса
		this.leg2 = this.bb_main.getChild("leg2");
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

		// НОВОЕ: создаём отдельную "кость" первой лапы.
		// Её pivot находится возле корпуса, поэтому лапа вращается от места крепления,
		// а не выворачивается вокруг собственной середины.
		PartDefinition leg1 = bb_main.addOrReplaceChild(
				"leg1",
				CubeListBuilder.create(),
				PartPose.offset(-4.0F, -6.5F, -4.25F)
		);

		// НОВОЕ: старую геометрию cube_r5 кладём ВНУТРЬ кости leg1.
		// Размер, UV и исходные углы лапы сохраняются.
		// Координаты теперь считаются относительно leg1:
		// (-4, -6.5, -4.25) + (-6, 3.5, -4) = (-10, -3, -8.25),
		// то есть визуально лапа остаётся на прежнем месте.
		leg1.addOrReplaceChild(
				"cube_r5",
				CubeListBuilder.create()
						.texOffs(40, 47)
						.addBox(
								-1.0F,
								-8.0F,
								-1.0F,
								2.0F,
								12.0F,
								2.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
						-6.0F,
						3.5F,
						-4.0F,
						-0.5236F,
						0.0F,
						1.0472F
				)
		);
		PartDefinition leg2 = bb_main.addOrReplaceChild(
				"leg2",
				CubeListBuilder.create(),
				PartPose.offset(4.0F, -6.5F, -4.25F)
		);
		leg2.addOrReplaceChild(
				"cube_r10",
				CubeListBuilder.create()
						.texOffs(8, 36)
						.addBox(
								-1.0F,
								-3.0F,
								-1.0F,
								2.0F,
								12.0F,
								2.0F,
								new CubeDeformation(0.0F)
						),
				PartPose.offsetAndRotation(
    				2.25F,
        			1.5F,
        			-1.75F,
        			-0.5236F,
        			0.0F,
        			-1.0472F
				)
		);

		PartDefinition cube_r6 = bb_main.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(32, 47).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-10.0F, -3.0F, 3.0F, 0.5236F, 0.0F, 1.0472F));

		PartDefinition cube_r7 = bb_main.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(40, 33).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -2.5F, -0.75F, 0.1745F, 0.0F, 1.0472F));

		PartDefinition cube_r8 = bb_main.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(24, 36).addBox(-1.0F, -8.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-11.0F, -2.5F, -4.5F, -0.1745F, 0.0F, 1.0472F));

		PartDefinition cube_r9 = bb_main.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(16, 36).addBox(0.25F, -3.0F, 0.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.25F, -3.75F, -0.25F, 0.5236F, 0.0F, -1.0472F));

		PartDefinition cube_r11 = bb_main.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(0, 36).addBox(-1.0F, -3.0F, -1.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, -4.75F, -4.0F, -0.1745F, 0.0F, -1.0472F));

		PartDefinition cube_r12 = bb_main.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(32, 33).addBox(-0.75F, -3.0F, 0.0F, 2.0F, 12.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(6.75F, -4.5F, -2.5F, 0.1745F, 0.0F, -1.0472F));

		return LayerDefinition.create(meshdefinition, 128, 128);
	}

	@Override
	public void setupAnim(
			CrystalSpider entity,
			float limbSwing,
			float limbSwingAmount,
			float ageInTicks,
			float netHeadYaw,
			float headPitch
	) {

		// НОВОЕ:
		// Раньше использовали ageInTicks, поэтому лапа двигалась ВСЕГДА.
		// Теперь используем limbSwing и limbSwingAmount:
		// паук стоит -> limbSwingAmount около 0 -> движение исчезает;
		// паук идёт -> значение растёт -> лапа начинает двигаться.
		float legMovement =
				Mth.cos(
						limbSwing
								* 0.6662F
								* 2.0F
				)
						* 0.8F
						* limbSwingAmount;

		// НОВОЕ:
		// Вращаем leg1 - кость возле корпуса.
		// Сам cube_r5 лежит внутри неё и сохраняет исходные углы Blockbench.
		this.leg1.xRot = legMovement;
		this.leg2.xRot = -legMovement;
	}

	@Override
	public void renderToBuffer(
        PoseStack poseStack,
        VertexConsumer vertexConsumer,
        int packedLight,
        int packedOverlay,
        int color
	) {
    bb_main.render(
            poseStack,
            vertexConsumer,
            packedLight,
            packedOverlay,
            color
    );
	}
}
