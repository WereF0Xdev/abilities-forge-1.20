package fox.mods.classes.utils;

import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

public class ParticlesUtils {

    public static void spawnColorTransitionCircleParticles(Player player, Level level, Vector3f startColor, Vector3f endColor, int particleCount, double radius) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        double playerX = player.getX();
        double playerY = player.getY() + 1.5;
        double playerZ = player.getZ();

        float particleSize = 1.0f;

        for (int i = 0; i < particleCount; i++) {
            double angle = 2 * Math.PI * i / particleCount;

            double x = playerX + radius * Math.cos(angle);
            double z = playerZ + radius * Math.sin(angle);

            DustColorTransitionOptions particleOptions = new DustColorTransitionOptions(startColor, endColor, particleSize);

            serverLevel.sendParticles(particleOptions, x, playerY, z, 1, 0, 0, 0, 0);
        }
    }

    public static void spawnCircleParticles(Player player, Level level, ParticleOptions particleType, int particleCount, double radius) {
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

            serverLevel.sendParticles(particleType, x, playerY, z, 1, 0, 0, 0, 0);
        }
    }
}
