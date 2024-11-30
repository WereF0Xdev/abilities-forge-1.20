package fox.mods.classes.abilities;

import fox.mods.classes.abilities.predator.LeapAbility;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class TriggerMainAbility {

    public static void execute(Player player) {

        if (PlayerClassUtils.isPredator(player)) {
            LeapAbility.trigger(player);
        } else if (PlayerClassUtils.isGhost(player)) {
            player.displayClientMessage(Component.literal("Work in progress."), true);
        }
    }
}
