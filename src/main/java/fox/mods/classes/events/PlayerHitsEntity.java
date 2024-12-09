package fox.mods.classes.events;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingAttackEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerHitsEntity {
    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event, event.getEntity(), event.getSource().getEntity());
        }
    }

    public static void execute(Entity entity, Entity sourceentity) {
        execute(null, entity, sourceentity);
    }

    private static void execute(@Nullable Event event, Entity entity, Entity sourceentity) {
        if (entity == null || sourceentity == null)
            return;
        if (sourceentity instanceof Player player && PlayerClassUtils.isSpider(player)) {
            boolean spiderRampage = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).rampage;
            if (spiderRampage) {
                if (!entity.level().isClientSide) {
                    BlockPos entityPos = entity.blockPosition();
                    BlockState cobwebState = Blocks.COBWEB.defaultBlockState();

                    if (entity.level().getBlockState(entityPos).isAir()) {
                        entity.level().setBlock(entityPos, cobwebState, 3);
                    }
                }
            }
        }
    }
}

