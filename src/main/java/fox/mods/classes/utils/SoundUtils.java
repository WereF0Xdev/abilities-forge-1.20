package fox.mods.classes.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

public class SoundUtils {

    public static void playPlayer(Player player, String sound) {
        Level _level = player.level();
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();
        if (!_level.isClientSide()) {
            _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(sound)), SoundSource.NEUTRAL, 20, 1);
        } else {
            _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(sound)), SoundSource.NEUTRAL, 20, 1, false);
        }
    }

    public static void playEntity(Entity entity, String sound) {
        Level _level = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (!_level.isClientSide()) {
            _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(sound)), SoundSource.NEUTRAL, 20, 1);
        } else {
            _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation(sound)), SoundSource.NEUTRAL, 20, 1, false);
        }
    }
}
