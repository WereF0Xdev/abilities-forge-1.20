package fox.mods.classes.abilities.ghost;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.ParticlesUtils;
import fox.mods.classes.utils.PlayerClassUtils;
import fox.mods.classes.utils.SoundUtils;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

public class SpectralLaunchAbility {

    private static boolean isToggled(Player player) {
        boolean abilityState = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).spectralLaunch;
        return abilityState;
    }

    private static boolean isInCooldown(Player player) {
        boolean abilityInCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).spectralLaunchInCooldown;
        return abilityInCooldown;
    }

    private static void displayCooldownMessage(Player player) {
        double abilityCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).spectralLaunchCooldown;
        player.displayClientMessage(Component.literal(PlayerClassUtils.getPlayerClass(player).getDisplayColor() + "Spectral Launch §fis charging! §7(" + (new java.text.DecimalFormat("##").format(abilityCooldown)) + "s)"), true);
    }

    private static void toggleAbility(Player player) {
        {
            boolean _setval = true;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.spectralLaunch = _setval;
                capability.syncPlayerVariables(player);
            });
        }

        {
            boolean _setval = true;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.spectralLaunchInCooldown = _setval;
                capability.syncPlayerVariables(player);
            });
        }
        ParticlesUtils.spawnColorTransitionCircleParticles(player, player.level(), new Vector3f(0.5f, 0.5f, 0.5f), new Vector3f(1.0f, 0.84f, 0.0f), 60, 2);
        SoundUtils.playPlayer(player, "block.beacon.activate");
    }

    public static void toggle(Player player) {
        if (!isInCooldown(player)) {
            if (!isToggled(player)) {
                toggleAbility(player);
            }
        } else {
            displayCooldownMessage(player);
        }
    }
}
