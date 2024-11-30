package fox.mods.classes.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.state.BlockState;

public class BlockUtils {
    public static boolean isBed(Level level, BlockState blockState) {
        return blockState.getBlock() instanceof BedBlock;
    }
}
