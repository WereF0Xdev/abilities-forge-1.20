package fox.mods.classes.client.renderer.ghostblockvariations.cobblestone;

import fox.mods.classes.client.model.Modelghost_block;
import fox.mods.classes.client.model.animations.ghost_blockAnimation;
import fox.mods.classes.entity.ghostblockvariants.cobblestone.GhostCobblestoneBlockEntity;
import fox.mods.classes.entity.ghostblockvariants.grass.GhostGrassBlockEntity;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GhostCobblestoneBlockRenderer extends MobRenderer<GhostCobblestoneBlockEntity, Modelghost_block<GhostCobblestoneBlockEntity>> {
	public GhostCobblestoneBlockRenderer(EntityRendererProvider.Context context) {
		super(context, new AnimatedModel(context.bakeLayer(Modelghost_block.LAYER_LOCATION)), 0.5f);
	}

	@Override
	public ResourceLocation getTextureLocation(GhostCobblestoneBlockEntity entity) {
		return new ResourceLocation("classes:textures/entity/ghost_block_cobble.png");
	}

	private static final class AnimatedModel extends Modelghost_block<GhostCobblestoneBlockEntity> {
		private final ModelPart root;
		private final HierarchicalModel animator = new HierarchicalModel<GhostCobblestoneBlockEntity>() {
			@Override
			public ModelPart root() {
				return root;
			}

			@Override
			public void setupAnim(GhostCobblestoneBlockEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
				this.root().getAllParts().forEach(ModelPart::resetPose);
				this.animate(entity.animationState0, ghost_blockAnimation.rise, ageInTicks, 1f);
			}
		};

		public AnimatedModel(ModelPart root) {
			super(root);
			this.root = root;
		}

		@Override
		public void setupAnim(GhostCobblestoneBlockEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
			animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
			super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		}
	}
}
