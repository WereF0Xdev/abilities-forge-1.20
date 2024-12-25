package fox.mods.classes.events;

import fox.mods.classes.ClassesMod;
import fox.mods.classes.entity.LavaSlimeEntity;
import fox.mods.classes.init.ClassesModEntities;
import fox.mods.classes.utils.ParticlesUtils;
import fox.mods.classes.utils.SoundUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Level;

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
                ParticlesUtils.spawnCircleParticles(newLavaSlime, newLavaSlime.level(), ParticleTypes.LAVA, 15, 1);
                SoundUtils.playEntity(newLavaSlime, "entity.magma_cube.jump");
            }
        }
    }
}
