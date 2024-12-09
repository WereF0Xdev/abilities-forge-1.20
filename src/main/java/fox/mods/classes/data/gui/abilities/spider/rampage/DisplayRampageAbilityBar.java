package fox.mods.classes.data.gui.abilities.spider.rampage;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.world.entity.player.Player;

public class DisplayRampageAbilityBar {

    public static boolean empty(Player player) {
        if (player == null)
            return false;

        double rampageCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).rampageCooldown;

        return PlayerClassUtils.isSpider(player) && rampageCooldown < 60 && rampageCooldown > 25;
    }

    public static boolean file5(Player player) {
        if (player == null)
            return false;

        double rampageCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).rampageCooldown;

        return PlayerClassUtils.isSpider(player) && rampageCooldown <= 25 && rampageCooldown > 20;
    }

    public static boolean file10(Player player) {
        if (player == null)
            return false;

        double rampageCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).rampageCooldown;

        return PlayerClassUtils.isSpider(player) && rampageCooldown <= 20 && rampageCooldown > 15;
    }

    public static boolean file15(Player player) {
        if (player == null)
            return false;

        double rampageCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).rampageCooldown;

        return PlayerClassUtils.isSpider(player) && rampageCooldown <= 15 && rampageCooldown > 10;
    }

    public static boolean file20(Player player) {
        if (player == null)
            return false;

        double rampageCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).rampageCooldown;

        return PlayerClassUtils.isSpider(player) && rampageCooldown <= 10 && rampageCooldown > 5;
    }

    public static boolean file25(Player player) {
        if (player == null)
            return false;

        double rampageCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).rampageCooldown;

        return PlayerClassUtils.isSpider(player) && rampageCooldown <= 5;
    }

    public static boolean full(Player player) {
        if (player == null)
            return false;

        double rampageCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).rampageCooldown;

        return PlayerClassUtils.isSpider(player) && rampageCooldown == 60;
    }
}
