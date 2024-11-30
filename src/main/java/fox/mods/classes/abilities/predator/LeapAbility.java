package fox.mods.classes.abilities.predator;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
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
