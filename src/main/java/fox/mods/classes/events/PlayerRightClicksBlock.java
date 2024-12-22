package fox.mods.classes.events;

import fox.mods.classes.init.ClassesModEntities;
import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.BlockUtils;
import fox.mods.classes.utils.PlayerClassUtils;
import fox.mods.classes.utils.SoundUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.ForgeRegistries;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerRightClicksBlock {
    @SubscribeEvent
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        if (event.getHand() != event.getEntity().getUsedItemHand())
            return;
        execute(event, event.getLevel().getBlockState(event.getPos()), event.getEntity(), event.getPos());
    }

    public static void execute(BlockState blockstate, Player entity, BlockPos pos) {
        execute(null, blockstate, entity, pos);
    }

    private static void execute(@Nullable Event event, BlockState blockstate, Player entity, BlockPos pos) {
        if (entity == null)
            return;
        Level level = entity.level();
        if (BlockUtils.isBed(level, blockstate)) {
            if (level.isNight()) {
                if (PlayerClassUtils.isPredator(entity)) {
                    if (!entity.level().isClientSide) {
                        entity.displayClientMessage(Component.literal("§cPredators §fcan't sleep!"), true);
                    }

                    level.destroyBlock(pos, true, entity);

                    if (event != null && event.isCancelable()) {
                        event.setCanceled(true);
                    } else if (event != null && event.hasResult()) {
                        event.setResult(Event.Result.DENY);
                    }
                }
            }
        }
        if (PlayerClassUtils.isGhost(entity)) {
            boolean spectralLaunch = (entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).spectralLaunch;
            if (spectralLaunch && !entity.isShiftKeyDown()) {
                if (BlockUtils.isAlivable(entity, blockstate)) {
                    level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
                    spawnBlockBreakParticles(level, pos, blockstate);
                    SoundUtils.playPlayer(entity, "entity.warden.emerge");
                    if (level instanceof ServerLevel _level) {
                        Entity entityToSpawn = ClassesModEntities.GHOST_BLOCK.get().spawn(_level, pos, MobSpawnType.MOB_SUMMONED);
                        if (entityToSpawn != null) {
                            entityToSpawn.setYRot(entity.getYRot());
                            entityToSpawn.setYBodyRot(entity.getYRot());
                            entityToSpawn.setYHeadRot(entity.getYRot());
                            entityToSpawn.setXRot(entity.getXRot());
                        }
                    }
                }
            }
        }
    }

    private static void spawnBlockBreakParticles(Level level, BlockPos pos, BlockState blockState) {
        if (!level.isClientSide()) return;

        level.levelEvent(2001, pos, Block.getId(blockState));
    }
}




