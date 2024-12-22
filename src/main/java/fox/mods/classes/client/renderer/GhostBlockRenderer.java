package fox.mods.classes.client.renderer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

import fox.mods.classes.entity.GhostBlockEntity;
import fox.mods.classes.client.model.animations.ghost_blockAnimation;
import fox.mods.classes.client.model.Modelghost_block;

public class GhostBlockRenderer extends MobRenderer<GhostBlockEntity, Modelghost_block<GhostBlockEntity>> {
	public GhostBlockRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelghost_block.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(GhostBlockEntity entity) {
		return new ResourceLocation("classes:textures/entity/ghost_block_stone.png");
	}

	private static final class AnimatedModel extends Modelghost_block<GhostBlockEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<GhostBlockEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(GhostBlockEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, ghost_blockAnimation.rise, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(GhostBlockEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}
