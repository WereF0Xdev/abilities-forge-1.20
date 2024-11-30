package fox.mods.classes.events;

import fox.mods.classes.utils.GuiUtils;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.chat.Component;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class PlayerOpensGui {

    @SubscribeEvent
    public static void onScreenOpen(ScreenEvent event) {

        Screen screen = event.getScreen();
        Minecraft mc = Minecraft.getInstance();
        LocalPlayer player = mc.player;

        if (player == null) {
            return;
        }
        if (GuiUtils.isVillager(screen)) {
            if (PlayerClassUtils.isPredator(player)) {
                player.displayClientMessage(Component.literal("§cPredators §fcan't trade!"), true);
                event.setCanceled(true);
            }
        }
    }
}