package fox.mods.classes.data.gui.abilities.predator.roar;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.world.entity.player.Player;

public class DisplayRoarAbilityBarFile20 {
    public static boolean data(Player player) {
        if (player == null)
            return false;

        double roarCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).roarAbilityCooldown;

        return PlayerClassUtils.isPredator(player) && roarCooldown <= 10 && roarCooldown > 5;
    }
}
