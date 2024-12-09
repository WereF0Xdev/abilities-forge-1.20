package fox.mods.classes.main;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

public class PlaceCobwebSquare {
    public static void execute(Level level, double x, double y, double z) {
        if (!level.isClientSide()) {
            BlockState cobweb = Blocks.COBWEB.defaultBlockState();
            int centerX = (int) Math.floor(x);
            int centerY = (int) Math.floor(y);
            int centerZ = (int) Math.floor(z);

            for (int offsetX = -1; offsetX <= 1; offsetX++) {
                for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
                    BlockPos pos = new BlockPos(centerX + offsetX, centerY, centerZ + offsetZ);
                    level.setBlock(pos, cobweb, 3);
                }
            }
        }
    }
}
