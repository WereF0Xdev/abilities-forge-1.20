package fox.mods.classes.client.screens;

import com.mojang.blaze3d.vertex.PoseStack;
import fox.mods.classes.data.gui.abilities.predator.leap.*;
import fox.mods.classes.data.gui.abilities.predator.roar.*;
import fox.mods.classes.utils.PlayerClassUtils;

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
        int mainAbilityBarXPos = 21;
        int mainAbilityBarYPos = 59;
        int mainAbilityBarTextXPos = 24;
        int mainAbilityBarTextYPos = 65;
        int secondaryAbilityBarXPos = 21;
        int secondaryAbilityBarYPos = 52;
        int secondaryAbilityBarTextXPos = 24;
        int secondaryAbilityBarTextYPos = 48;
        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.enableBlend();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        if (true) {
            if (PlayerClassUtils.isPredator(entity)) {
                if (DisplayLeapAbilityBarEmpty.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_empty.png"), w / 2 + mainAbilityBarXPos, h - mainAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }
                if (DisplayLeapAbilityBarFull.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_ready.png"), w / 2 + mainAbilityBarXPos, h - mainAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }
                if (DisplayLeapAbilityBarFile5.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_cooldown_5.png"), w / 2 + mainAbilityBarXPos, h - mainAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }
                if (DisplayLeapAbilityBarFile10.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_cooldown_10.png"), w / 2 + mainAbilityBarXPos, h - mainAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }
                if (DisplayLeapAbilityBarFile15.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_cooldown_15.png"), w / 2 + mainAbilityBarXPos, h - mainAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }
                if (DisplayLeapAbilityBarFile20.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_cooldown_20.png"), w / 2 + mainAbilityBarXPos, h - mainAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }
                if (DisplayLeapAbilityBarFile25.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/leap_cooldown_25.png"), w / 2 + mainAbilityBarXPos, h - mainAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }

                if (DisplayRoarAbilityBarEmpty.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/roar_empty.png"), w / 2 + secondaryAbilityBarXPos, h - secondaryAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }
                if (DisplayRoarAbilityBarFull.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/roar_ready.png"), w / 2 + secondaryAbilityBarXPos, h - secondaryAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }
                if (DisplayRoarAbilityBarFile5.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/roar_cooldown_5.png"), w / 2 + secondaryAbilityBarXPos, h - secondaryAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }
                if (DisplayRoarAbilityBarFile10.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/roar_cooldown_10.png"), w / 2 + secondaryAbilityBarXPos, h - secondaryAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }
                if (DisplayRoarAbilityBarFile15.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/roar_cooldown_15.png"), w / 2 + secondaryAbilityBarXPos, h - secondaryAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }
                if (DisplayRoarAbilityBarFile20.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/roar_cooldown_20.png"), w / 2 + secondaryAbilityBarXPos, h - secondaryAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }
                if (DisplayRoarAbilityBarFile25.data(entity)) {
                    event.getGuiGraphics().blit(new ResourceLocation("classes:textures/screens/roar_cooldown_25.png"), w / 2 + secondaryAbilityBarXPos, h - secondaryAbilityBarYPos, 0, 0, 61, 5, 61, 5);
                }

                PoseStack poseStack = event.getGuiGraphics().pose();
                poseStack.pushPose();

                float scale = 0.75f;
                poseStack.scale(scale, scale, scale);

                int roarScaledX = (int)((w / 2 + secondaryAbilityBarTextXPos) / scale);
                int roarScaledY = (int)((h - secondaryAbilityBarTextYPos) / scale);

                String roarText = "ʀᴏᴀʀ";

                int textWidth = Minecraft.getInstance().font.width(roarText);

                int startColor = 0xFF8B0000;
                int endColor = 0xFFCC0000;
                int length = roarText.length();

                for (int i = 0; i < length; i++) {
                    char character = roarText.charAt(i);

                    float ratio = (float) i / (length - 1);
                    int r = (int) ((1 - ratio) * ((startColor >> 16) & 0xFF) + ratio * ((endColor >> 16) & 0xFF));
                    int g = (int) ((1 - ratio) * ((startColor >> 8) & 0xFF) + ratio * ((endColor >> 8) & 0xFF));
                    int b = (int) ((1 - ratio) * (startColor & 0xFF) + ratio * (endColor & 0xFF));
                    int color = (0xFF << 24) | (r << 16) | (g << 8) | b;

                    event.getGuiGraphics().drawString(
                            Minecraft.getInstance().font,
                            String.valueOf(character),
                            roarScaledX + Minecraft.getInstance().font.width(roarText.substring(0, i)),
                            roarScaledY,
                            color,
                            false
                    );
                }

                int leapScaledX = (int)((w / 2 + mainAbilityBarTextXPos) / scale);
                int leapScaledY = (int)((h - mainAbilityBarTextYPos) / scale);

                String leapText = "ʟᴇᴀᴘ";

                for (int i = 0; i < leapText.length(); i++) {
                    char character = leapText.charAt(i);

                    float ratio = (float) i / (leapText.length() - 1);
                    int r = (int) ((1 - ratio) * ((startColor >> 16) & 0xFF) + ratio * ((endColor >> 16) & 0xFF));
                    int g = (int) ((1 - ratio) * ((startColor >> 8) & 0xFF) + ratio * ((endColor >> 8) & 0xFF));
                    int b = (int) ((1 - ratio) * (startColor & 0xFF) + ratio * (endColor & 0xFF));
                    int color = (0xFF << 24) | (r << 16) | (g << 8) | b;

                    event.getGuiGraphics().drawString(
                            Minecraft.getInstance().font,
                            String.valueOf(character),
                            leapScaledX + Minecraft.getInstance().font.width(leapText.substring(0, i)),
                            leapScaledY,
                            color,
                            false
                    );
                }

                poseStack.popPose();


            }
        }
        RenderSystem.depthMask(true);
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableDepthTest();
        RenderSystem.disableBlend();
        RenderSystem.setShaderColor(1, 1, 1, 1);
    }
}

