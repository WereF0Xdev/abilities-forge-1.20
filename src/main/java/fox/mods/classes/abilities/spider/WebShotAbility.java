package fox.mods.classes.abilities.spider;

// import fox.mods.classes.entity.WebShotEntity;
import fox.mods.classes.entity.WebShotEntity;
import fox.mods.classes.network.ClassesModVariables;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

public class WebShotAbility {
    public static void toggle(Player player) {
        if (!isInCooldown(player)) {
            toggleAbility(player);
        } else {
            displayCooldownMessage(player);
        }
    }

    private static void toggleAbility(Player player) {
        {
            boolean _setval = true;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.webShotInCooldown = _setval;
                capability.syncPlayerVariables(player);
            });
        }
        WebShotEntity.shoot(player.level(), player, RandomSource.create());
    }

    private static void displayCooldownMessage(Player player) {
        double abilityCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).webShotCooldown;
        player.displayClientMessage(Component.literal("§cWebShot §fis charging! §7(" + (new java.text.DecimalFormat("##").format(abilityCooldown)) + "s)"), true);
    }

    private static boolean isInCooldown(Player player) {
        boolean abilityInCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).webShotInCooldown;
        return abilityInCooldown;
    }
}