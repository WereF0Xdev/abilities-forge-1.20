package fox.mods.classes.utils;

import fox.mods.classes.abilities.predator.LeapAbility;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.joml.Vector3f;

public class ParticlesUtils {

    public static void spawnColorTransitionCircleParticles(Entity player, Level level, Vector3f startColor, Vector3f endColor, int particleCount, double radius) {
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

    public static void spawnCircleParticles(Entity player, Level level, ParticleOptions particleType, int particleCount, double radius) {
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

    public static void spawnSphereParticles(Entity player, Level level, ParticleOptions particleType, int particleCount, double radius) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        double playerX = player.getX();
        double playerY = player.getY() + 1;
        double playerZ = player.getZ();

        for (int i = 0; i < particleCount; i++) {
            double theta = Math.random() * 2 * Math.PI;
            double phi = Math.acos(2 * Math.random() - 1);

            double offsetX = radius * Math.sin(phi) * Math.cos(theta);
            double offsetY = radius * Math.cos(phi);
            double offsetZ = radius * Math.sin(phi) * Math.sin(theta);

            double x = playerX + offsetX;
            double y = playerY + offsetY;
            double z = playerZ + offsetZ;

            serverLevel.sendParticles(particleType, x, y, z, 1, 0, 0, 0, 0);
        }
    }

    public static void spawnBlockBreakParticles(Level level, BlockPos pos, BlockState blockState) {
        if (!level.isClientSide()) return;

        level.levelEvent(2001, pos, Block.getId(blockState));
    }

    public static void spawnSwirlParticles(Entity player, ParticleOptions particleType) {
        if (!(player.level() instanceof ServerLevel serverLevel)) {
            return;
        }

        double centerX = player.getX();
        double centerY = player.getY();
        double centerZ = player.getZ();

        double radius = 1.0;
        int heightSteps = 20;
        int particlesPerStep = 20;

        for (int step = 0; step < heightSteps; step++) {
            double height = centerY + (step / (double) heightSteps) * 2.0;

            for (int i = 0; i < particlesPerStep; i++) {
                double angle = 2 * Math.PI * i / particlesPerStep;

                double offsetX = radius * Math.cos(angle + (step * 0.1));
                double offsetZ = radius * Math.sin(angle + (step * 0.1));

                serverLevel.sendParticles(
                        particleType,
                        centerX + offsetX,
                        height,
                        centerZ + offsetZ,
                        1,
                        0.0, 0.0, 0.0,
                        0.0
                );
            }
        }
    }

    public static void spawnFallCircleParticles(Entity player, Level level, ParticleOptions particleType, int particleCount, double radius) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        double playerX = player.getX();
        double playerY = player.getY();
        double playerZ = player.getZ();

        for (int i = 0; i < particleCount; i++) {
            double angle = 2 * Math.PI * i / particleCount;

            double x = playerX + radius * Math.cos(angle);
            double z = playerZ + radius * Math.sin(angle);

            serverLevel.sendParticles(particleType, x, playerY, z, 1, 0, 0, 0, 0);
        }
    }
}
