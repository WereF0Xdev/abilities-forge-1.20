package fox.mods.classes.events;

import fox.mods.classes.ClassesMod;
import fox.mods.classes.entity.LavaSlimeEntity;
import fox.mods.classes.init.ClassesModEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class LavaSlimeEntityDies {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        entity.getPersistentData().putBoolean("dead", true);

        double chance = 0;

        if (entity.level().getDifficulty() == Difficulty.EASY) {
            chance = ClassesMod.EASY_DIFFICULTY_LAVA_SLIME_DUPE_CHANCE;
        } else if (entity.level().getDifficulty() == Difficulty.NORMAL) {
            chance = ClassesMod.NORMAL_DIFFICULTY_LAVA_SLIME_DUPE_CHANCE;
        } else if (entity.level().getDifficulty() == Difficulty.HARD) {
            chance = ClassesMod.HARD_DIFFICULTY_LAVA_SLIME_DUPE_CHANCE;
        }

        if (!entity.level().isClientSide && entity.level() instanceof ServerLevel serverLevel && Math.random() < chance) {
            LavaSlimeEntity newLavaSlime = ClassesModEntities.LAVA_SLIME.get().create(serverLevel);
            if (newLavaSlime != null) {
                BlockPos pos = entity.blockPosition();
                newLavaSlime.moveTo(pos.getX(), pos.getY(), pos.getZ(), entity.getYRot(), entity.getXRot());
                serverLevel.addFreshEntity(newLavaSlime);
                spawnCircleParticles(newLavaSlime, newLavaSlime.level(), 15, 1);
                playSound(newLavaSlime);
            }
        }
    }

    private static void playSound(Entity entity) {
        Level _level = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (!_level.isClientSide()) {
            _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.magma_cube.jump")), SoundSource.NEUTRAL, 20, 1);
        } else {
            _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.magma_cube.jump")), SoundSource.NEUTRAL, 20, 1, false);
        }
    }

    private static void spawnCircleParticles(Entity entity, Level level, int particleCount, double radius) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        double entityX = entity.getX();
        double entityY = entity.getY() + 1.5;
        double entityZ = entity.getZ();

        for (int i = 0; i < particleCount; i++) {
            double angle = 2 * Math.PI * i / particleCount;

            double x = entityX + radius * Math.cos(angle);
            double z = entityY + radius * Math.sin(angle);

            serverLevel.sendParticles(ParticleTypes.LAVA, x, entityY, z, 1, 0, 0, 0, 0);
        }
    }
}
