package fox.mods.classes.abilities.ghost;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Vector3f;

public class PhasingAbility {

    public static boolean isToggled(Player player) {
        boolean abilityState = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).noClip;
        return abilityState;
    }

    public static boolean isInCooldown(Player player) {
        boolean abilityInCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).phasingInCooldown;
        return abilityInCooldown;
    }

    public static void displayCooldownMessage(Player player) {
        double abilityCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).phasingCooldown;
        player.displayClientMessage(Component.literal(PlayerClassUtils.getPlayerClass(player).getDisplayColor() + "Phasing §fis charging! §7(" + (new java.text.DecimalFormat("##").format(abilityCooldown)) + "s)"), true);
    }

    public static void toggleAbility(Player player) {
        {
            boolean _setval = true;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.noClip = _setval;
                capability.syncPlayerVariables(player);
            });
        }

        {
            boolean _setval = true;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.phasingInCooldown = _setval;
                capability.syncPlayerVariables(player);
            });
        }
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
