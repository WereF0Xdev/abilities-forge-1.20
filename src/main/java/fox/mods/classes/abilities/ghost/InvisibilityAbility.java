package fox.mods.classes.abilities.ghost;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.client.renderer.EffectInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class InvisibilityAbility {

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
        activateInvisibility(player);
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

    public static void activateInvisibility(Player player) {



        setArmorInvisible(player);

        player.setInvisible(true);

        // set variable invis to visible again after 30 secs
    }

    public static void setArmorInvisible(Player player) {
        for (int i = 0; i < 4; i++) {
            ItemStack armorStack = player.getInventory().armor.get(i);
            if (!armorStack.isEmpty()) {
                armorStack.getOrCreateTag().putBoolean("Invisible", true);
            }
        }
    }


}
