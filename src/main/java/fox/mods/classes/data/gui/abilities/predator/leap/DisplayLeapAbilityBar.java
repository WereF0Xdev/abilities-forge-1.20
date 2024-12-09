package fox.mods.classes.data.gui.abilities.predator.leap;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.world.entity.player.Player;

public class DisplayLeapAbilityBar {
    public static boolean empty(Player player) {
        if (player == null)
            return false;

        double leapCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).leapAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && leapCooldown < 30 && leapCooldown > 25;
    }

    public static boolean file5(Player player) {
        if (player == null)
            return false;

        double leapCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).leapAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && leapCooldown <= 25 && leapCooldown > 20;
    }

    public static boolean file10(Player player) {
        if (player == null)
            return false;

        double leapCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).leapAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && leapCooldown <= 20 && leapCooldown > 15;
    }

    public static boolean file15(Player player) {
        if (player == null)
            return false;

        double leapCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).leapAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && leapCooldown <= 15 && leapCooldown > 10;
    }

    public static boolean file20(Player player) {
        if (player == null)
            return false;

        double leapCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).leapAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && leapCooldown <= 10 && leapCooldown > 5;
    }

    public static boolean file25(Player player) {
        if (player == null)
            return false;

        double leapCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).leapAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && leapCooldown <= 5;
    }

    public static boolean full(Player player) {
        if (player == null)
            return false;

        double leapCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).leapAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && leapCooldown == 30;
    }
}
