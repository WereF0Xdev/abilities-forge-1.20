package fox.mods.classes.events;

import fox.mods.classes.utils.ParticlesUtils;
import fox.mods.classes.utils.PlayerClassUtils;
import fox.mods.classes.utils.SoundUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.Level;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.living.LivingFallEvent;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;


@Mod.EventBusSubscriber
public class PlayerFalls {
    @SubscribeEvent
    public static void onEntityFall(LivingFallEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event, event.getEntity(), event.getDistance());
        }
    }

    public static void execute(Entity entity, double distance) {
        execute(null, entity, distance);
    }

    private static void execute(@Nullable Event event, Entity entity, double distance) {
        if (entity == null)
            return;
        if (entity instanceof Player player) {
            Level level = player.level();
            double x = player.getX();
            double y = player.getY();
            double z = player.getZ();
            if (distance >= 2.5) {
                if (PlayerClassUtils.isEagle(player) || PlayerClassUtils.isGhost(player)) {
                    if (event != null && event.isCancelable()) {
                        event.setCanceled(true);
                    } else if (event != null && event.hasResult()) {
                        event.setResult(Event.Result.DENY);
                    }
                    ParticlesUtils.spawnFallCircleParticles(player, level, ParticleTypes.CLOUD, 30, 1);
                    SoundUtils.playPlayer(player, "entity.player.big_fall");
                }
            }
        }
    }
}

