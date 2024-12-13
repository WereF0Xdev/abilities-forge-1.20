package fox.mods.classes.events;

import fox.mods.classes.ClassesMod;
import fox.mods.classes.classes.select.SelectClass;
import fox.mods.classes.init.ClassesModItems;
import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import fox.mods.classes.utils.ScreensUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.LevelAccessor;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerRespawns {
    @SubscribeEvent
    public static void onPlayerRespawned(PlayerEvent.PlayerRespawnEvent event) {
        execute(event, event.getEntity().level(), event.getEntity(), event.isEndConquered());
    }

    public static void execute(LevelAccessor world, Entity entity, boolean endconquered) {
        execute(null, world, entity, endconquered);
    }

    private static void execute(@Nullable Event event, LevelAccessor world, Entity entity, boolean endconquered) {
        if (entity == null)
            return;
        if (!endconquered) {
            if (entity instanceof Player player) {
                if (!usedTotem(player)) {
                    assingClass(player);
                } else {
                    ClassesMod.queueServerWork(20, () -> {
                        ScreensUtils.open(ClassesMod.SELECT_CLASSES_STRING_NAME, player.level(), player.getX(), player.getY(), player.getZ(), player);
                        {
                            boolean _setval = false;
                            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                                capability.usedTotemOfRebirth = _setval;
                                capability.syncPlayerVariables(player);
                            });
                        }
                    });
                }
            }
        }
        if (entity instanceof Player player) {
            resetRealTimeTicks(player);
        }
    }

    private static void assingClass(Player _player) {
        if (PlayerClassUtils.isPredator(_player)) {
            SelectClass.predator(_player, false);
        } else if (PlayerClassUtils.isGhost(_player)) {
            SelectClass.ghost(_player, false);
        } else if (PlayerClassUtils.isSpider(_player)) {
            SelectClass.spider(_player, false);
        }
    }

    private static void resetRealTimeTicks(Player player) {
        {
            double _setval = 0.0415;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.realTimeTicks = _setval;
                capability.syncPlayerVariables(player);
            });
        }
    }

    private static boolean usedTotem(Player player) {
        return (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).usedTotemOfRebirth;
    }

}

