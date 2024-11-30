package fox.mods.classes.client.screens;

import fox.mods.classes.data.gui.abilities.predator.leap.*;
import org.checkerframework.checker.units.qual.h;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class CooldownOverlay {
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void eventHandler(RenderGuiEvent.Pre event) {
        int w = event.getWindow().getGuiScaledWidth();
        int h = event.getWindow().getGuiScaledHeight();
        Level world = null;
        double x = 0;
        double y = 0;
        double z = 0;
        Player entity = Minecraft.getInstance().player;
        if (entity != null) {
            world = entity.level();
            x = entity.getX();
            y = entity.getY();
            z = entity.getZ();
        }
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        if (true) {
            if (DisplayLeapAbilityBarEmpty.data(entity)) {
                event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_empty.png"), w / 2 + 27, h - 52, 0, 0, 61, 5, 61, 5);
            }
            if (DisplayLeapAbilityBarFull.data(entity)) {
                event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_ready.png"), w / 2 + 27, h - 52, 0, 0, 61, 5, 61, 5);
            }
            if (DisplayLeapAbilityBarFile5.data(entity)) {
                event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_cooldown_5.png"), w / 2 + 27, h - 52, 0, 0, 61, 5, 61, 5);
            }
            if (DisplayLeapAbilityBarFile10.data(entity)) {
                event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_cooldown_10.png"), w / 2 + 27, h - 52, 0, 0, 61, 5, 61, 5);
            }
            if (DisplayLeapAbilityBarFile15.data(entity)) {
                event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_cooldown_15.png"), w / 2 + 27, h - 52, 0, 0, 61, 5, 61, 5);
            }
            if (DisplayLeapAbilityBarFile20.data(entity)) {
                event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_cooldown_20.png"), w / 2 + 27, h - 52, 0, 0, 61, 5, 61, 5);
            }
            if (DisplayLeapAbilityBarFile25.data(entity)) {
                event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_cooldown_25.png"), w / 2 + 27, h - 52, 0, 0, 61, 5, 61, 5);
            }
        }
        RenderSystem.depthMask(true);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }
}

