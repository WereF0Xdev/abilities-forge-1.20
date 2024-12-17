package fox.mods.classes.client.model;

import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.EntityModel;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports
public class Modellava_slime<T extends Entity> extends EntityModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in
    // the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation("classes", "lava_slime"), "main");
    public final ModelPart lava_slime;
    public final ModelPart body;
    public final ModelPart teeth;
    public final ModelPart jaw;
    public final ModelPart teeth2;
    public final ModelPart eyes;

    public Modellava_slime(ModelPart root) {
        this.lava_slime = root.getChild("lava_slime");
        this.body = this.lava_slime.getChild("body");
        this.teeth = this.body.getChild("teeth");
        this.jaw = this.lava_slime.getChild("jaw");
        this.teeth2 = this.jaw.getChild("teeth2");
        this.eyes = this.jaw.getChild("eyes");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();
        PartDefinition lava_slime = partdefinition.addOrReplaceChild("lava_slime", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));
        PartDefinition body = lava_slime.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -9.0F, -6.0F, 12.0F, 9.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition teeth = body.addOrReplaceChild("teeth", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
        PartDefinition cube_r1 = teeth.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(8, 39).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, -10.0F, -5.0F, 0.6981F, 0.0F, 0.0F));
        PartDefinition cube_r2 = teeth.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(32, 39).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, -10.0F, 5.0F, -0.6981F, 0.0F, 0.0F));
        PartDefinition cube_r3 = teeth.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(40, 39).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, -10.0F, 3.0F, 0.6981F, 0.0F, 0.0F));
        PartDefinition cube_r4 = teeth.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(16, 39).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, -10.0F, 1.0F, -0.6981F, 0.0F, 0.0F));
        PartDefinition cube_r5 = teeth.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(24, 39).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, -10.0F, -1.0F, 0.6981F, 0.0F, 0.0F));
        PartDefinition cube_r6 = teeth.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(0, 39).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(4.0F, -10.0F, -3.0F, -0.6981F, 0.0F, 0.0F));
        PartDefinition jaw = lava_slime.addOrReplaceChild("jaw", CubeListBuilder.create(), PartPose.offset(-6.0F, -9.0F, 0.0F));
        PartDefinition cube_r7 = jaw.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(0, 21).addBox(0.0F, -6.0F, -6.0F, 12.0F, 6.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.7854F));
        PartDefinition teeth2 = jaw.addOrReplaceChild("teeth2", CubeListBuilder.create(), PartPose.offsetAndRotation(6.0F, -16.0F, 0.0F, 3.1416F, 0.0F, 0.0F));
        PartDefinition cube_r8 = teeth2.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(40, 42).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -9.0F, -5.0F, 0.6981F, 0.0F, 0.7854F));
        PartDefinition cube_r9 = teeth2.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(32, 42).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -9.0F, 5.0F, -0.6981F, 0.0F, 0.7854F));
        PartDefinition cube_r10 = teeth2.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(24, 42).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -9.0F, 3.0F, 0.6981F, 0.0F, 0.7854F));
        PartDefinition cube_r11 = teeth2.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(16, 42).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -9.0F, 1.0F, -0.6981F, 0.0F, 0.7854F));
        PartDefinition cube_r12 = teeth2.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(8, 42).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -9.0F, -1.0F, 0.6981F, 0.0F, 0.7854F));
        PartDefinition cube_r13 = teeth2.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(0, 42).addBox(-1.0F, 0.0F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -9.0F, -3.0F, -0.6981F, 0.0F, 0.7854F));
        PartDefinition eyes = jaw.addOrReplaceChild("eyes", CubeListBuilder.create(), PartPose.offset(6.0F, 9.0F, 0.0F));
        PartDefinition cube_r14 = eyes.addOrReplaceChild("cube_r14",
                CubeListBuilder.create().texOffs(6, 45).addBox(-1.0F, -2.0F, -1.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)).texOffs(0, 45).addBox(-1.0F, -2.0F, -7.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)),
                PartPose.offsetAndRotation(2.0F, -19.0F, 3.0F, 0.0F, 0.0F, -0.7854F));
        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        lava_slime.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}

