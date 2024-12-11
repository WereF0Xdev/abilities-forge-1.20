package fox.mods.classes.utils;

import fox.mods.classes.classes.PlayerClass;
import fox.mods.classes.network.ClassesModVariables;
import net.minecraft.world.entity.player.Player;

public class PlayerClassUtils {

    public static PlayerClass getPlayerClass(Player player) {
        return player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .map(playerVariables -> playerVariables.classType)
                .orElse(PlayerClass.HUMAN);
    }

    public static void setPlayerClass(Player player, PlayerClass playerClass) {
        {
            PlayerClass _setval = playerClass;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.classType = _setval;
                capability.syncPlayerVariables(player);
            });
        }
    }

    public static boolean isPredator(Player player) {
        boolean bool = false;
        PlayerClass playerClass = player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .map(playerVariables -> playerVariables.classType)
                .orElse(PlayerClass.HUMAN);

        if (playerClass == PlayerClass.PREDATOR) {
            bool = true;
        }
        return bool;
    }

    public static boolean isEagle(Player player) {
        boolean bool = false;
        PlayerClass playerClass = player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .map(playerVariables -> playerVariables.classType)
                .orElse(PlayerClass.HUMAN);

        if (playerClass == PlayerClass.EAGLE) {
            bool = true;
        }
        return bool;
    }

    public static boolean isGhost(Player player) {
        boolean bool = false;
        PlayerClass playerClass = player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .map(playerVariables -> playerVariables.classType)
                .orElse(PlayerClass.HUMAN);

        if (playerClass == PlayerClass.GHOST) {
            bool = true;
        }
        return bool;
    }

    public static boolean isSpider(Player player) {
        boolean bool = false;
        PlayerClass playerClass = player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null)
                .map(playerVariables -> playerVariables.classType)
                .orElse(PlayerClass.HUMAN);

        if (playerClass == PlayerClass.SPIDER) {
            bool = true;
        }
        return bool;
    }

}
