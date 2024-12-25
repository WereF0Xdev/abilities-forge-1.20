package fox.mods.classes.main.deprecated;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.ParticlesUtils;
import fox.mods.classes.utils.SoundUtils;
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
        ParticlesUtils.spawnColorTransitionCircleParticles(player, player.level(), new Vector3f(1.0f, 1.0f, 1.0f), new Vector3f(0.0f, 0.0f, 0.0f), 20, 2);
        SoundUtils.playPlayer(player, "block.beacon.deactivate");
    }
}
