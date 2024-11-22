package fox.mods.classes;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;

import fox.mods.classes.utils.PlayerClassUtils;

public class Test {
    public Test(Player player) {
        if (!player.level().isClientSide) {
            player.displayClientMessage(Component.literal("" + PlayerClassUtils.getPlayerClass(player).getDisplayName()), false);
        }
    }
}
