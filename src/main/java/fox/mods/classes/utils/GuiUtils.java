package fox.mods.classes.utils;

import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.MerchantScreen;

public class GuiUtils {

    public static boolean isVillager(Screen screen) {
        return screen instanceof MerchantScreen;
    }
}
