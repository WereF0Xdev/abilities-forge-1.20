package fox.mods.classes.main.test;

import fox.mods.classes.network.ClassesModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

public class DeleteMe {

    public static void getBlock(Player player) {
        player.displayClientMessage(Component.literal((player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).blockLevitatingType), true);
    }
}
