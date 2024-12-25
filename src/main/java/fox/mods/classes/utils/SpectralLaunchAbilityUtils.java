package fox.mods.classes.utils;

public class SpectralLaunchAbilityUtils {

    public static boolean containsGhostStoneBlocks(String blockLevitatingType) {
        boolean bool = false;
        if (blockLevitatingType.contains("stone") ||
                blockLevitatingType.contains("stone_bricks") ||
                blockLevitatingType.contains("infested_stone") ||
                blockLevitatingType.contains("infested_stone_bricks")) {
            bool = true;
        }
        return bool;
    }

    public static boolean containsGhostGrassBlocks(String blockLevitatingType) {
        boolean bool = false;
        if (blockLevitatingType.contains("dirt") ||
                blockLevitatingType.contains("grass_block") ||
                blockLevitatingType.contains("coarse_dirt") ||
                blockLevitatingType.contains("dirt_path")) {
            bool = true;
        }
        return bool;
    }

    public static boolean containsGhostCobblestoneBlocks(String blockLevitatingType) {
        boolean bool = false;
        if (blockLevitatingType.contains("cobblestone") ||
                blockLevitatingType.contains("infested_cobblestone")) {
            bool = true;
        }
        return bool;
    }
}
