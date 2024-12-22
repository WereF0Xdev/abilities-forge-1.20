package fox.mods.classes.abilities.predator;

import fox.mods.classes.ClassesMod;
import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.ParticlesUtils;
import fox.mods.classes.utils.PlayerClassUtils;
import fox.mods.classes.utils.SoundUtils;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

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

                ParticlesUtils.spawnCircleParticles(player, player.level(), ParticleTypes.END_ROD , 40, 1);
                SoundUtils.playPlayer(player, "entity.firework_rocket.launch");

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
}
