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
}