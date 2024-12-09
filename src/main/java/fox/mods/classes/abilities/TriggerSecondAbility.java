package fox.mods.classes.abilities;

import fox.mods.classes.abilities.predator.RoarAbility;
import fox.mods.classes.abilities.spider.WebShotAbility;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.world.entity.player.Player;

public class TriggerSecondAbility {

    public static void execute(Player player) {

        if (PlayerClassUtils.isPredator(player)) {
            RoarAbility.trigger(player);
        } else if (PlayerClassUtils.isSpider(player)) {
            WebShotAbility.toggle(player);
        }
    }
}
