package fox.mods.classes.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class BlockUtils {

    public static boolean isBed(Level level, BlockState blockState) {
        return blockState.getBlock() instanceof BedBlock;
    }

    public static final List<Block> UNCLIMBABLE_BLOCKS = List.of(
            Blocks.OBSIDIAN,
            Blocks.ICE,
            Blocks.GLASS,
            Blocks.GLASS_PANE,
            Blocks.COBWEB,
            Blocks.FURNACE,
            Blocks.CRAFTING_TABLE,
            Blocks.SMITHING_TABLE,
            Blocks.ANVIL,
            Blocks.BEEHIVE,
            Blocks.BARREL,
            Blocks.BARRIER,
            Blocks.CHEST,
            Blocks.TRAPPED_CHEST,
            Blocks.ENDER_CHEST,
            Blocks.AIR,
            Blocks.CAVE_AIR,
            Blocks.LADDER,
            Blocks.WATER,
            Blocks.LAVA,
            Blocks.GRASS,
            Blocks.TALL_GRASS
    );

    public static boolean isUnclimbable(BlockState blockState) {
        return UNCLIMBABLE_BLOCKS.contains(blockState.getBlock()) || blockState.isStickyBlock() || blockState.isAir();
    }
}
