package fox.mods.classes.init;

import fox.mods.classes.client.renderer.GhostBlockProjectileRenderer;
import fox.mods.classes.client.renderer.GhostBlockRenderer;
import fox.mods.classes.client.renderer.ghostblockvariations.cobblestone.GhostCobblestoneBlockRenderer;
import fox.mods.classes.client.renderer.ghostblockvariations.cobblestone.projectile.GhostCobblestoneBlockProjectileRenderer;
import fox.mods.classes.client.renderer.ghostblockvariations.grass.GhostGrassBlockRenderer;
import fox.mods.classes.client.renderer.ghostblockvariations.grass.projectile.GhostGrassBlockProjectileRenderer;
import fox.mods.classes.entity.ghostblockvariants.cobblestone.GhostCobblestoneBlockEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;
import fox.mods.classes.client.renderer.LavaSlimeRenderer;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClassesModEntityRenderers {
    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(ClassesModEntities.WEB_SHOT.get(), ThrownItemRenderer::new);
        event.registerEntityRenderer(ClassesModEntities.LAVA_SLIME.get(), LavaSlimeRenderer::new);
        event.registerEntityRenderer(ClassesModEntities.GHOST_BLOCK.get(), GhostBlockRenderer::new);
        event.registerEntityRenderer(ClassesModEntities.GHOST_GRASS_BLOCK.get(), GhostGrassBlockRenderer::new);
        event.registerEntityRenderer(ClassesModEntities.GHOST_COBBLESTONE_BLOCK.get(), GhostCobblestoneBlockRenderer::new);
        event.registerEntityRenderer(ClassesModEntities.GHOST_BLOCK_PROJECTILE.get(), GhostBlockProjectileRenderer::new);
        event.registerEntityRenderer(ClassesModEntities.GHOST_GRASS_BLOCK_PROJECTILE.get(), GhostGrassBlockProjectileRenderer::new);
        event.registerEntityRenderer(ClassesModEntities.GHOST_COBBLESTONE_BLOCK_PROJECTILE.get(), GhostCobblestoneBlockProjectileRenderer::new);
    }
}

