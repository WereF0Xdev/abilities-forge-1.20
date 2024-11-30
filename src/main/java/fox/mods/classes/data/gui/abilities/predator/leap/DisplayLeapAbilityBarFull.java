package fox.mods.classes.data.gui.abilities.predator.leap;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.world.entity.player.Player;

public class DisplayLeapAbilityBarFull {
    public static boolean data(Player player) {
        if (player == null)
            return false;

        double leapCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).leapAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && leapCooldown == 30;
    }
}
