package fox.mods.classes.data.gui.abilities.spider.webshot;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.world.entity.player.Player;

public class DisplayWebShotAbilityBar {
    public static boolean empty(Player player) {
        if (player == null)
            return false;

        double webShotCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).webShotCooldown;

        return PlayerClassUtils.isSpider(player) && webShotCooldown < 30 && webShotCooldown > 25;
    }

    public static boolean file5(Player player) {
        if (player == null)
            return false;

        double webShotCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).webShotCooldown;

        return PlayerClassUtils.isSpider(player) && webShotCooldown <= 25 && webShotCooldown > 20;
    }

    public static boolean file10(Player player) {
        if (player == null)
            return false;

        double webShotCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).webShotCooldown;

        return PlayerClassUtils.isSpider(player) && webShotCooldown <= 20 && webShotCooldown > 15;
    }

    public static boolean file15(Player player) {
        if (player == null)
            return false;

        double webShotCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).webShotCooldown;

        return PlayerClassUtils.isSpider(player) && webShotCooldown <= 15 && webShotCooldown > 10;
    }

    public static boolean file20(Player player) {
        if (player == null)
            return false;

        double webShotCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).webShotCooldown;

        return PlayerClassUtils.isSpider(player) && webShotCooldown <= 10 && webShotCooldown > 5;
    }

    public static boolean file25(Player player) {
        if (player == null)
            return false;

        double webShotCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).webShotCooldown;

        return PlayerClassUtils.isSpider(player) && webShotCooldown <= 5;
    }

    public static boolean full(Player player) {
        if (player == null)
            return false;

        double webShotCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).webShotCooldown;

        return PlayerClassUtils.isSpider(player) && webShotCooldown == 30;
    }
}
