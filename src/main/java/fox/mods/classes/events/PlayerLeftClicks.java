package fox.mods.classes.events;

import fox.mods.classes.ClassesMod;
import fox.mods.classes.entity.GhostBlockEntity;
import fox.mods.classes.entity.GhostBlockProjectileEntity;
import fox.mods.classes.entity.ghostblockvariants.cobblestone.GhostCobblestoneBlockEntity;
import fox.mods.classes.entity.ghostblockvariants.cobblestone.projectile.GhostCobblestoneBlockProjectileEntity;
import fox.mods.classes.entity.ghostblockvariants.grass.GhostGrassBlockEntity;
import fox.mods.classes.entity.ghostblockvariants.grass.projectile.GhostGrassBlockProjectileEntity;
import fox.mods.classes.init.ClassesModEntities;
import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.SpectralLaunchAbilityUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.network.FriendlyByteBuf;

import javax.annotation.Nullable;

import java.util.Comparator;
import java.util.function.Supplier;

@Mod.EventBusSubscriber(value = {Dist.CLIENT})
public class PlayerLeftClicks {
    @SubscribeEvent
    public static void onLeftClick(PlayerInteractEvent.LeftClickEmpty event) {
        ClassesMod.PACKET_HANDLER.sendToServer(new PlayerLeftClicksMessage());
        execute(event.getEntity());
    }

    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class PlayerLeftClicksMessage {
        public PlayerLeftClicksMessage() {
        }

        public PlayerLeftClicksMessage(FriendlyByteBuf buffer) {
        }

        public static void buffer(PlayerLeftClicksMessage message, FriendlyByteBuf buffer) {
        }

        public static void handler(PlayerLeftClicksMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> {
                if (!context.getSender().level().hasChunkAt(context.getSender().blockPosition()))
                    return;
                execute(context.getSender());
            });
            context.setPacketHandled(true);
        }

        @SubscribeEvent
        public static void registerMessage(FMLCommonSetupEvent event) {
            ClassesMod.addNetworkMessage(PlayerLeftClicksMessage.class, PlayerLeftClicksMessage::buffer, PlayerLeftClicksMessage::new, PlayerLeftClicksMessage::handler);
        }
    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null)
            return;
        if (entity instanceof Player _player) {
            boolean spectralLaunch = (_player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).spectralLaunch;
            boolean blockLevitating = (_player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).blockLevitating;
            double blockLevitatingX = (_player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).blockLevitatingX;
            double blockLevitatingY = (_player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).blockLevitatingY;
            double blockLevitatingZ = (_player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).blockLevitatingZ;
            String blockLevitatingType = (_player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).blockLevitatingType;
            if (spectralLaunch && blockLevitating) {

                Vec3 lookDirection = _player.getViewVector(1.0F);

                if (_player.level() instanceof ServerLevel serverLevel) {
                    if (SpectralLaunchAbilityUtils.containsGhostStoneBlocks(blockLevitatingType)) {
                        GhostBlockProjectileEntity projectile = ClassesModEntities.GHOST_BLOCK_PROJECTILE.get()
                                .create(serverLevel);

                        shootGhostBlockProjectile(projectile, blockLevitatingX, blockLevitatingY, blockLevitatingZ, lookDirection, _player, serverLevel);
                    } else if (SpectralLaunchAbilityUtils.containsGhostGrassBlocks(blockLevitatingType)) {
                        GhostGrassBlockProjectileEntity projectile = ClassesModEntities.GHOST_GRASS_BLOCK_PROJECTILE.get()
                                .create(serverLevel);

                        shootGhostGrassBlockProjectile(projectile, blockLevitatingX, blockLevitatingY, blockLevitatingZ, lookDirection, _player, serverLevel);
                    } else if (SpectralLaunchAbilityUtils.containsGhostCobblestoneBlocks(blockLevitatingType)) {
                        GhostCobblestoneBlockProjectileEntity projectile = ClassesModEntities.GHOST_COBBLESTONE_BLOCK_PROJECTILE.get()
                                .create(serverLevel);

                        shootGhostCobblestoneBlockProjectile(projectile, blockLevitatingX, blockLevitatingY, blockLevitatingZ, lookDirection, _player, serverLevel);
                    }
                }
            }
        }
    }

    private static void shootGhostBlockProjectile(GhostBlockProjectileEntity projectile, double blockLevitatingX, double blockLevitatingY, double blockLevitatingZ, Vec3 lookDirection, Player _player, ServerLevel serverLevel) {
        if (projectile != null) {
            projectile.moveTo(blockLevitatingX, blockLevitatingY + 1.5, blockLevitatingZ, _player.getYRot(), _player.getXRot());
            projectile.shoot(lookDirection.x, lookDirection.y, lookDirection.z, 3F, 0.0F);
            serverLevel.addFreshEntity(projectile);

            clearLevitatingBlock(_player);

            if (!_player.level().isClientSide) {
                ((Entity) _player.level().getEntitiesOfClass(GhostBlockEntity.class, AABB.ofSize(new Vec3(blockLevitatingX, blockLevitatingY, blockLevitatingZ), 4, 4, 4), e -> true).stream().sorted(new Object() {
                    Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                        return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                    }
                }.compareDistOf(blockLevitatingX, blockLevitatingY, blockLevitatingZ)).findFirst().orElse(null)).discard();
            }
        }
    }

    private static void shootGhostGrassBlockProjectile(GhostGrassBlockProjectileEntity projectile, double blockLevitatingX, double blockLevitatingY, double blockLevitatingZ, Vec3 lookDirection, Player _player, ServerLevel serverLevel) {
        if (projectile != null) {
            projectile.moveTo(blockLevitatingX, blockLevitatingY + 1.5, blockLevitatingZ, _player.getYRot(), _player.getXRot());
            projectile.shoot(lookDirection.x, lookDirection.y, lookDirection.z, 3F, 0.0F);
            serverLevel.addFreshEntity(projectile);

            clearLevitatingBlock(_player);

            if (!_player.level().isClientSide) {
                ((Entity) _player.level().getEntitiesOfClass(GhostGrassBlockEntity.class, AABB.ofSize(new Vec3(blockLevitatingX, blockLevitatingY, blockLevitatingZ), 4, 4, 4), e -> true).stream().sorted(new Object() {
                    Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                        return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                    }
                }.compareDistOf(blockLevitatingX, blockLevitatingY, blockLevitatingZ)).findFirst().orElse(null)).discard();
            }
        }
    }

    private static void shootGhostCobblestoneBlockProjectile(GhostCobblestoneBlockProjectileEntity projectile, double blockLevitatingX, double blockLevitatingY, double blockLevitatingZ, Vec3 lookDirection, Player _player, ServerLevel serverLevel) {
        if (projectile != null) {
            projectile.moveTo(blockLevitatingX, blockLevitatingY + 1.5, blockLevitatingZ, _player.getYRot(), _player.getXRot());
            projectile.shoot(lookDirection.x, lookDirection.y, lookDirection.z, 3F, 0.0F);
            serverLevel.addFreshEntity(projectile);

            clearLevitatingBlock(_player);

            if (!_player.level().isClientSide) {
                ((Entity) _player.level().getEntitiesOfClass(GhostCobblestoneBlockEntity.class, AABB.ofSize(new Vec3(blockLevitatingX, blockLevitatingY, blockLevitatingZ), 4, 4, 4), e -> true).stream().sorted(new Object() {
                    Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
                        return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
                    }
                }.compareDistOf(blockLevitatingX, blockLevitatingY, blockLevitatingZ)).findFirst().orElse(null)).discard();
            }
        }
    }

    private static void clearLevitatingBlock(Player _player) {
        {
            boolean _setval = false;
            _player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.blockLevitating = _setval;
                capability.syncPlayerVariables(_player);
            });
        }
    }
}

