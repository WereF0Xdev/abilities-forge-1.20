package fox.mods.classes.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BedBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;

import java.util.List;

public class BlockUtils {

    public static boolean isBed(Level level, BlockState blockState) {
        return blockState.getBlock() instanceof BedBlock;
    }

    public static boolean isFlower(Level level, BlockState blockState) {
        return blockState.getBlock() instanceof FlowerBlock;
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
            Blocks.TALL_GRASS,
            Blocks.SNOW,
            Blocks.POWDER_SNOW,
            Blocks.DIRT_PATH
    );

    public static final List<Block> ALIVEABLE_BLOCKS = List.of(
            Blocks.STONE,
            Blocks.GRASS_BLOCK,
            Blocks.DIRT,
            Blocks.DIRT_PATH,
            Blocks.COARSE_DIRT,
            Blocks.STONE_BRICKS,
            Blocks.INFESTED_STONE,
            Blocks.INFESTED_STONE_BRICKS,
            Blocks.COBBLESTONE,
            Blocks.INFESTED_COBBLESTONE
    );

    public static boolean isUnclimbable(Player player, BlockState blockState) {
        return UNCLIMBABLE_BLOCKS.contains(blockState.getBlock()) || blockState.isStickyBlock() || blockState.isAir() || isFlower(player.level(), blockState);
    }

    public static boolean isAlivable(Player player, BlockState blockState) {
        return ALIVEABLE_BLOCKS.contains(blockState.getBlock());
    }

    public static final List<Block> GHOST_STONE_BLOCKS = List.of(
            Blocks.STONE,
            Blocks.STONE_BRICKS,
            Blocks.INFESTED_STONE,
            Blocks.INFESTED_STONE_BRICKS
    );

    public static final List<Block> GHOST_GRASS_BLOCKS = List.of(
            Blocks.GRASS_BLOCK,
            Blocks.DIRT,
            Blocks.DIRT_PATH,
            Blocks.COARSE_DIRT
    );

    public static final List<Block> GHOST_COBBLESTONE_BLOCKS = List.of(
            Blocks.COBBLESTONE,
            Blocks.INFESTED_COBBLESTONE
    );

    public static boolean isGhostStoneBlock(BlockState blockState) {
        return GHOST_STONE_BLOCKS.contains(blockState.getBlock());
    }

    public static boolean isGhostGrassBlock(BlockState blockState) {
        return GHOST_GRASS_BLOCKS.contains(blockState.getBlock());
    }

    public static boolean isGhostCobblestoneBlock(BlockState blockState) {
        return GHOST_COBBLESTONE_BLOCKS.contains(blockState.getBlock());
    }
}
