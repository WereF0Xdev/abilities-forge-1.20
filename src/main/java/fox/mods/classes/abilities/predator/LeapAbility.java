package fox.mods.classes.abilities.predator;

import fox.mods.classes.ClassesMod;
import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import static fox.mods.classes.ClassesMod.LEAP_MULTIPLIER;

public class LeapAbility {

    public static void trigger(Player player) {

        if (player == null) {
            return;
        }

        double forwardMotion = LEAP_MULTIPLIER;
        float yaw = player.getYHeadRot();
        double motionX = -Math.sin(Math.toRadians(yaw)) * forwardMotion;
        double motionZ = Math.cos(Math.toRadians(yaw)) * forwardMotion;

        double leapCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).leapAbilityCooldown;
        boolean leapInCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).leapAbilityInCooldown;

        if (PlayerClassUtils.isPredator(player)) {
            if (!leapInCooldown) {

                player.getAbilities().invulnerable = true;
                player.onUpdateAbilities();

                ClassesMod.queueServerWork(30, () -> {
                        player.getAbilities().invulnerable = false;
                        player.onUpdateAbilities();
                });

                spawnCircleParticles(player, player.level(), 40, 1);
                playSound(player);

                player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 100, 1));

                player.setDeltaMovement(new Vec3(motionX, 0, motionZ));

                cooldownLeap(player);

            } else {
                player.displayClientMessage(Component.literal("§cLeap §fis charging! §7(" + (new java.text.DecimalFormat("##").format(leapCooldown)) + "s)"), true);
            }
        }
    }
    private static void cooldownLeap(Player player) {
        {
            boolean _setval = true;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.leapAbilityInCooldown = _setval;
                capability.syncPlayerVariables(player);
            });
        }
    }

    public static void spawnCircleParticles(Player player, Level level, int particleCount, double radius) {
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

            serverLevel.sendParticles(ParticleTypes.END_ROD, x, playerY, z, 1, 0, 0, 0, 0);
        }
    }

    public static void playSound(Player player) {
        Level _level = player.level();
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        if (!_level.isClientSide()) {
            _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.firework_rocket.launch")), SoundSource.NEUTRAL, 20, 1);
        } else {
            _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.firework_rocket.launch")), SoundSource.NEUTRAL, 20, 1, false);
        }
    }
}
