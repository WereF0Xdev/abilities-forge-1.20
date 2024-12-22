package fox.mods.classes.abilities.eagle.deprecated;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.ParticlesUtils;
import fox.mods.classes.utils.SoundUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Vector3f;

public class FlightAbility {

    public static void toggle(Player player) {
        if (!isInCooldown(player)) {
            if (!isToggled(player)) {
                toggleAbility(player);
            }
        } else {
            displayCooldownMessage(player);
        }
    }

    private static void toggleAbility(Player player) {
        {
            boolean _setval = true;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.gliding = _setval;
                capability.syncPlayerVariables(player);
            });
        }

        {
            boolean _setval = true;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.flightInCooldown = _setval;
                capability.syncPlayerVariables(player);
            });
        }

        {
            ItemStack chestplateSlotItem = player.getItemBySlot(EquipmentSlot.CHEST);
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.chestplateSlotItem = chestplateSlotItem;
                capability.syncPlayerVariables(player);
            });
        }
        {
            player.getInventory().armor.set(2, new ItemStack(Items.ELYTRA));
            player.getInventory().setChanged();
        }

        float yaw = player.getYRot();
        float pitch = player.getXRot();

        double yawRad = Math.toRadians(-yaw);
        double pitchRad = Math.toRadians(-pitch);

        double x = -Math.sin(yawRad) * Math.cos(pitchRad);
        double y = -Math.sin(pitchRad);
        double z = Math.cos(yawRad) * Math.cos(pitchRad);

        double speed = 2.0;
        Vec3 velocity = new Vec3(x * speed, y * speed, z * speed);

        ParticlesUtils.spawnColorTransitionCircleParticles(player, player.level(), new Vector3f(0.0f, 0.0f, 0.0f), new Vector3f(1.0f, 1.0f, 1.0f), 20, 2);
        SoundUtils.playPlayer(player, "block.beacon.activate");
        player.setDeltaMovement(velocity);
    }

    private static boolean isToggled(Player player) {
        boolean abilityState = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).gliding;
        return abilityState;
    }

    private static boolean isInCooldown(Player player) {
        boolean abilityInCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).flightInCooldown;
        return abilityInCooldown;
    }

    private static void displayCooldownMessage(Player player) {
        double abilityCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).flightCooldown;
        player.displayClientMessage(Component.literal("§fFlight §fis charging! §7(" + (new java.text.DecimalFormat("##").format(abilityCooldown)) + "s)"), true);
    }
}
