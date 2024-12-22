package fox.mods.classes.init;

import fox.mods.classes.client.renderer.GhostBlockRenderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
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
    }
}

