package fox.mods.classes.abilities.spider;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.ParticlesUtils;
import fox.mods.classes.utils.SoundUtils;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.joml.Vector3f;

public class RampageAbility {

    public static void toggle(Player player) {
        if (!isInCooldown(player)) {
            if (!isToggled(player)) {
                toggleAbility(player);
            }
        } else {
            displayCooldownMessage(player);
        }
    }

    private static boolean isToggled(Player player) {
        boolean abilityState = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).rampage;
        return abilityState;
    }

    private static void toggleAbility(Player player) {
        {
            boolean _setval = true;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.rampage = _setval;
                capability.syncPlayerVariables(player);
            });
        }

        {
            boolean _setval = true;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.rampageInCooldown = _setval;
                capability.syncPlayerVariables(player);
            });
        }

        SoundUtils.playPlayer(player, "entity.wither.spawn");
        ParticlesUtils.spawnColorTransitionCircleParticles(player, player.level(), new Vector3f(1.0f, 0.0f, 0.0f), new Vector3f(1.0f, 1.0f, 1.0f), 30, 2);
    }

    private static boolean isInCooldown(Player player) {
        boolean abilityInCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).rampageInCooldown;
        return abilityInCooldown;
    }

    private static void displayCooldownMessage(Player player) {
        double abilityCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).rampageCooldown;
        player.displayClientMessage(Component.literal("§cRampage §fis charging! §7(" + (new java.text.DecimalFormat("##").format(abilityCooldown)) + "s)"), true);
    }
}
