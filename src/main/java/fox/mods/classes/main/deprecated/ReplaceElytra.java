package fox.mods.classes.main.deprecated;

import fox.mods.classes.network.ClassesModVariables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.DustColorTransitionOptions;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import org.joml.Vector3f;

public class ReplaceElytra {
    public static void execute(Player player) {
        {
            ItemStack itemStack = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).chestplateSlotItem;
            player.getInventory().armor.set(2, itemStack);
            player.getInventory().setChanged();
        }
        spawnCircleParticles(player, player.level(), 20, 2);
        playSound(player);
    }

    public static void spawnCircleParticles(Player player, Level level, int particleCount, double radius) {
        if (!(level instanceof ServerLevel serverLevel)) {
            return;
        }

        double playerX = player.getX();
        double playerY = player.getY() + 1.5;
        double playerZ = player.getZ();

        Vector3f startColor = new Vector3f(1.0f, 1.0f, 1.0f);
        Vector3f endColor = new Vector3f(0.0f, 0.0f, 0.0f);
        float particleSize = 1.0f;

        for (int i = 0; i < particleCount; i++) {
            double angle = 2 * Math.PI * i / particleCount;

            double x = playerX + radius * Math.cos(angle);
            double z = playerZ + radius * Math.sin(angle);

            DustColorTransitionOptions particleOptions = new DustColorTransitionOptions(startColor, endColor, particleSize);

            serverLevel.sendParticles(particleOptions, x, playerY, z, 1, 0, 0, 0, 0);
        }
    }

    public static void playSound(Player player) {
        Level _level = player.level();
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        if (!_level.isClientSide()) {
            _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.beacon.deactivate")), SoundSource.NEUTRAL, 20, 1);
        } else {
            _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.beacon.deactivate")), SoundSource.NEUTRAL, 20, 1, false);
        }
    }
}
