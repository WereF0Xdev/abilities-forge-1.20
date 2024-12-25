package fox.mods.classes.main.test;

import fox.mods.classes.classes.select.SelectClass;
import net.minecraft.world.entity.player.Player;

public class SelectGiant {
    public static void select(Player player) {
        SelectClass.giant(player, true);
    }
}
