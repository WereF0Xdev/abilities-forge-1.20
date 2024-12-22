package fox.mods.classes.events;

import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Vector3f;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class EntityTakesPoisonDamage {
    @SubscribeEvent
    public static void onEntityAttacked(LivingAttackEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event, event.getEntity(), event.getSource());
        }
    }

    public static void execute(Entity entity, DamageSource damagesource) {
        execute(null, entity, damagesource);
    }

    private static void execute(@Nullable Event event, Entity entity, DamageSource damagesource) {
        if (damagesource == null)
            return;
        if (entity instanceof Player player && PlayerClassUtils.isSpider(player) && damagesource.is(DamageTypes.MAGIC)) {
            if (event != null && event.isCancelable()) {
                event.setCanceled(true);
            } else if (event != null && event.hasResult()) {
                event.setResult(Event.Result.DENY);
            }
            spawnCircleParticles(player, player.level(), 20, 1);
            playSound(player);
        }
    }

    private static void spawnCircleParticles(Player player, Level level, int particleCount, double radius) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        double playerX = player.getX();
        double playerY = player.getY() + 1.5;
        double playerZ = player.getZ();

        for (int i = 0; i < particleCount; i++) {
            double angle = 2 * Math.PI * i / particleCount;

            double x = playerX + radius * Math.cos(angle);
            double z = playerZ + radius * Math.sin(angle);

            serverLevel.sendParticles(ParticleTypes.GLOW, x, playerY, z, 1, 0, 0, 0, 0);
        }
    }

    private static void playSound(Player player) {
        Level _level = player.level();
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        if (!_level.isClientSide()) {
            _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.beacon.ambient")), SoundSource.NEUTRAL, 20, 1);
        } else {
            _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.beacon.ambient")), SoundSource.NEUTRAL, 20, 1, false);
        }
    }
}


