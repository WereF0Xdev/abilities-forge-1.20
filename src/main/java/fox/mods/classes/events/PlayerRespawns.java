package fox.mods.classes.events;

import fox.mods.classes.classes.select.SelectClass;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerRespawns {
    @SubscribeEvent
    public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
        execute(event, event.getEntity());
    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof Player _player) {
            if (PlayerClassUtils.isPredator(_player)) {
                SelectClass.predator(_player, false);
            } else if (PlayerClassUtils.isGhost(_player)) {
                SelectClass.ghost(_player, false);
            }
        }
    }
}

