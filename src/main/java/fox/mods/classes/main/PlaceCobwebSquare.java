package fox.mods.classes.main;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Set;

public class PlaceCobwebSquare {
    public static void execute(Level level, double x, double y, double z) {
        if (!level.isClientSide()) {
            BlockState cobweb = Blocks.COBWEB.defaultBlockState();
            int centerX = (int) Math.floor(x);
            int centerY = (int) Math.floor(y);
            int centerZ = (int) Math.floor(z);

            Set<Block> unbreakableBlocks = Set.of(
                    Blocks.BEDROCK,
                    Blocks.END_PORTAL_FRAME,
                    Blocks.END_PORTAL,
                    Blocks.NETHER_PORTAL,
                    Blocks.COMMAND_BLOCK,
                    Blocks.SPAWNER,
                    Blocks.RESPAWN_ANCHOR,
                    Blocks.CHAIN_COMMAND_BLOCK,
                    Blocks.REPEATING_COMMAND_BLOCK,
                    Blocks.BARRIER,
                    Blocks.STRUCTURE_BLOCK,
                    Blocks.JIGSAW,
                    Blocks.DRAGON_EGG,
                    Blocks.END_GATEWAY
            );

            for (int offsetX = -1; offsetX <= 1; offsetX++) {
                for (int offsetZ = -1; offsetZ <= 1; offsetZ++) {
                    BlockPos pos = new BlockPos(centerX + offsetX, centerY, centerZ + offsetZ);
                    BlockState currentState = level.getBlockState(pos);

                    if (unbreakableBlocks.contains(currentState.getBlock())) {
                        continue;
                    }

                    level.setBlock(pos, cobweb, 3);
                }
            }
        }
    }
}