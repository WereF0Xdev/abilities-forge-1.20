package fox.mods.classes.client.renderer;

import fox.mods.classes.client.model.Modelghost_block;
import fox.mods.classes.entity.GhostBlockProjectileEntity;
import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class GhostBlockProjectileRenderer extends EntityRenderer<GhostBlockProjectileEntity> {
    private static final ResourceLocation texture = new ResourceLocation("classes:textures/entity/ghost_block_stone.png");
    private final Modelghost_block model;

    public GhostBlockProjectileRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new Modelghost_block(context.bakeLayer(Modelghost_block.LAYER_LOCATION));
    }

    @Override
    public void render(GhostBlockProjectileEntity entityIn, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource bufferIn, int packedLightIn) {
        VertexConsumer vb = bufferIn.getBuffer(RenderType.entityCutout(this.getTextureLocation(entityIn)));
        poseStack.pushPose();
        poseStack.mulPose(Axis.YP.rotationDegrees(Mth.lerp(partialTicks, entityIn.yRotO, entityIn.getYRot()) - 90));
        poseStack.mulPose(Axis.ZP.rotationDegrees(90 + Mth.lerp(partialTicks, entityIn.xRotO, entityIn.getXRot())));
        model.renderToBuffer(poseStack, vb, packedLightIn, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
        poseStack.popPose();
        super.render(entityIn, entityYaw, partialTicks, poseStack, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(GhostBlockProjectileEntity entity) {
        return texture;
    }
}

