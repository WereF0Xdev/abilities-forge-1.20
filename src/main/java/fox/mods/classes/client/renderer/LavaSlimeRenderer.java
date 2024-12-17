package fox.mods.classes.client.renderer;

import fox.mods.classes.client.model.Modellava_slime;
import fox.mods.classes.client.model.animations.lava_slimeAnimation;
import fox.mods.classes.data.animations.lava_slime.DisplayLavaSlimeAnimations;
import fox.mods.classes.entity.LavaSlimeEntity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.HierarchicalModel;

public class LavaSlimeRenderer extends MobRenderer<LavaSlimeEntity, Modellava_slime<LavaSlimeEntity>> {
    public LavaSlimeRenderer(EntityRendererProvider.Context context) {
        super(context, new AnimatedModel(context.bakeLayer(Modellava_slime.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(LavaSlimeEntity entity) {
        return new ResourceLocation("classes:textures/entity/lava_slime.png");
    }

    private static final class AnimatedModel extends Modellava_slime<LavaSlimeEntity> {
        private final ModelPart root;
        private final HierarchicalModel animator = new HierarchicalModel<LavaSlimeEntity>() {
            @Override
            public ModelPart root() {
                return root;
            }

            @Override
            public void setupAnim(LavaSlimeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
                this.root().getAllParts().forEach(ModelPart::resetPose);
                if (DisplayLavaSlimeAnimations.idle(entity))
                    this.animateWalk(lava_slimeAnimation.walking, limbSwing, limbSwingAmount, 1f, 1f);
                this.animate(entity.animationState1, lava_slimeAnimation.attacking, ageInTicks, 1f);
                this.animate(entity.animationState2, lava_slimeAnimation.Dying, ageInTicks, 1f);
            }
        };

        public AnimatedModel(ModelPart root) {
            super(root);
            this.root = root;
        }

        @Override
        public void setupAnim(LavaSlimeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
            animator.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
            super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        }
    }
}

