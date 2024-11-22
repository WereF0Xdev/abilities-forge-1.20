package fox.mods.classes.client.gui;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.GuiGraphics;

import java.util.HashMap;

import fox.mods.classes.world.inventory.SelectClassMenu;
import fox.mods.classes.network.SelectClassButtonMessage;
import fox.mods.classes.ClassesMod;

import com.mojang.blaze3d.systems.RenderSystem;

public class SelectClassScreen extends AbstractContainerScreen<SelectClassMenu> {
    private final static HashMap<String, Object> guistate = SelectClassMenu.guistate;
    private final Level world;
    private final int x, y, z;
    private final Player entity;
    ImageButton imagebutton_bad_omen;
    ImageButton imagebutton_glowing;

    public SelectClassScreen(SelectClassMenu container, Inventory inventory, Component text) {
        super(container, inventory, text);
        this.world = container.world;
        this.x = container.x;
        this.y = container.y;
        this.z = container.z;
        this.entity = container.entity;
        this.imageWidth = 426;
        this.imageHeight = 239;
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {
        this.renderBackground(guiGraphics);
        super.render(guiGraphics, mouseX, mouseY, partialTicks);
        this.renderTooltip(guiGraphics, mouseX, mouseY);
    }

    @Override
    protected void renderBg(GuiGraphics guiGraphics, float partialTicks, int gx, int gy) {
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableBlend();
    }

    @Override
    public boolean keyPressed(int key, int b, int c) {
        if (key == 256) {
            this.minecraft.player.closeContainer();
            return true;
        }
        return super.keyPressed(key, b, c);
    }

    @Override
    protected void renderLabels(GuiGraphics guiGraphics, int mouseX, int mouseY) {
    }

    @Override
    public void init() {
        super.init();
        imagebutton_bad_omen = new ImageButton(this.leftPos + 195, this.topPos + 39, 32, 32, 0, 0, 32, new ResourceLocation("classes:textures/screens/atlas/imagebutton_bad_omen.png"), 32, 64, e -> {
            if (true) {
                ClassesMod.PACKET_HANDLER.sendToServer(new SelectClassButtonMessage(0, x, y, z));
                SelectClassButtonMessage.handleButtonAction(entity, 0, x, y, z);
            }
        });
        guistate.put("button:imagebutton_bad_omen", imagebutton_bad_omen);
        this.addRenderableWidget(imagebutton_bad_omen);
        imagebutton_glowing = new ImageButton(this.leftPos + 267, this.topPos + 102, 32, 32, 0, 0, 32, new ResourceLocation("classes:textures/screens/atlas/imagebutton_glowing.png"), 32, 64, e -> {
            if (true) {
                ClassesMod.PACKET_HANDLER.sendToServer(new SelectClassButtonMessage(1, x, y, z));
                SelectClassButtonMessage.handleButtonAction(entity, 1, x, y, z);
            }
        });
        guistate.put("button:imagebutton_glowing", imagebutton_glowing);
        this.addRenderableWidget(imagebutton_glowing);
    }
}


