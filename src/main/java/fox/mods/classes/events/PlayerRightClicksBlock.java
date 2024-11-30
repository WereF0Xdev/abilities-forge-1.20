package fox.mods.classes.events;

import fox.mods.classes.utils.BlockUtils;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.block.state.BlockState;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerRightClicksBlock {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand() != event.getEntity().getUsedItemHand())
            return;
        execute(event, event.getLevel().getBlockState(event.getPos()), event.getEntity(), event.getPos());
    }

    public static void execute(BlockState blockstate, Player entity, BlockPos pos) {
        execute(null, blockstate, entity, pos);
    }

    private static void execute(@Nullable Event event, BlockState blockstate, Player entity, BlockPos pos) {
        if (entity == null)
            return;
        Level level = entity.level();
        if (BlockUtils.isBed(level, blockstate)) {
            if (level.isNight()) {
                if (PlayerClassUtils.isPredator(entity)) {
                    if (!entity.level().isClientSide) {
                        entity.displayClientMessage(Component.literal("§cPredators §fcan't sleep!"), true);
                    }

                    level.destroyBlock(pos, true, entity);

                    if (event != null && event.isCancelable()) {
                        event.setCanceled(true);
                    } else if (event != null && event.hasResult()) {
                        event.setResult(Event.Result.DENY);
                    }
                }
            }
        }
    }
}




