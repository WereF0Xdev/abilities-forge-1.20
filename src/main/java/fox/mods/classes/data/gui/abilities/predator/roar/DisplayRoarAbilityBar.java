package fox.mods.classes.data.gui.abilities.predator.roar;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.world.entity.player.Player;

public class DisplayRoarAbilityBar {

    public static boolean empty(Player player) {
        if (player == null)
            return false;

        double roarCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).roarAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && roarCooldown < 30 && roarCooldown > 25;
    }

    public static boolean file5(Player player) {
        if (player == null)
            return false;

        double roarCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).roarAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && roarCooldown <= 25 && roarCooldown > 20;
    }

    public static boolean file10(Player player) {
        if (player == null)
            return false;

        double roarCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).roarAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && roarCooldown <= 20 && roarCooldown > 15;
    }

    public static boolean file15(Player player) {
        if (player == null)
            return false;

        double roarCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).roarAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && roarCooldown <= 15 && roarCooldown > 10;
    }

    public static boolean file20(Player player) {
        if (player == null)
            return false;

        double roarCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).roarAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && roarCooldown <= 10 && roarCooldown > 5;
    }

    public static boolean file25(Player player) {
        if (player == null)
            return false;

        double roarCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).roarAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && roarCooldown <= 5;
    }

    public static boolean full(Player player) {
        if (player == null)
            return false;

        double roarCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).roarAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && roarCooldown == 30;
    }
}
