package fox.mods.classes.utils;

import net.minecraft.world.entity.player.Player;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class WallClimbAbilityUtils {

    private final Player player;

    public WallClimbAbilityUtils(Player player) {
        this.player = player;
    }

    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        Player player = event.player;

        if (PlayerClassUtils.isSpider(player) && canClimb(player)) {
            climbWall(player);
        }
    }

    private static boolean canClimb(Player player) {
        BlockPos playerPos = player.blockPosition();
        for (BlockPos offset : new BlockPos[]{
                playerPos.north(),
                playerPos.south(),
                playerPos.east(),
                playerPos.west()}) {

            BlockState state = player.level().getBlockState(offset);
            if (!BlockUtils.isUnclimbable(player, state)) {
                return true;
            }
        }
        return false;
    }

    private static void climbWall(Player player) {
        if (player.isCrouching()) return;

        if (player.zza > 0) {
            player.setDeltaMovement(player.getDeltaMovement().x, 0.2, player.getDeltaMovement().z);
        }
    }
}

