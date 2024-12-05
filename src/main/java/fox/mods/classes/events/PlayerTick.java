package fox.mods.classes.events;

import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biomes;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

import fox.mods.classes.network.ClassesModVariables;

@Mod.EventBusSubscriber
public class PlayerTick {
    @SubscribeEvent
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            execute(event, event.player);
        }
    }

    public static void execute(Entity entity) {
        execute(null, entity);
    }

    private static void execute(@Nullable Event event, Entity entity) {
        if (entity == null)
            return;

        double realTimeTicks = (entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).realTimeTicks;

        boolean leapInCooldown = (entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).leapAbilityInCooldown;
        double leapCooldown = (entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).leapAbilityCooldown;

        boolean roarInCooldown = (entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).roarAbilityInCooldown;
        double roarCooldown = (entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).roarAbilityCooldown;

        boolean spiderRampageInCooldown = (entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).spiderRampageInCooldown;
        double spiderRampageCooldown = (entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).spiderRampageCooldown;

        LevelAccessor world = entity.level();

        if (world.getLevelData().isRaining() && entity.level().canSeeSky(entity.blockPosition()) || entity.isInWater()) {
            if (!(entity.level().getBiome(entity.blockPosition()) == Biomes.DESERT) || !(entity.level().getBiome(entity.blockPosition()) == Biomes.SAVANNA)) {
                if (entity instanceof Player player && PlayerClassUtils.isSpider(player)) {
                    player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 40, 0));
                }
            }
        }

        if (leapInCooldown) {
            {
                double _setval = leapCooldown - realTimeTicks;
                entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.leapAbilityCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
        }
        if (leapCooldown <= 0) {
            {
                boolean _setval = false;
                entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.leapAbilityInCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
            {
                double _setval = 30;
                entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.leapAbilityCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
        }


        if (roarInCooldown) {
            {
                double _setval = roarCooldown - realTimeTicks;
                entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.roarAbilityCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
        }
        if (roarCooldown <= 0) {
            {
                boolean _setval = false;
                entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.roarAbilityInCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
            {
                double _setval = 30;
                entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.roarAbilityCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
        }

        if (spiderRampageInCooldown) {
            {
                double _setval = spiderRampageCooldown - realTimeTicks;
                entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.spiderRampageCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
        }
        if (spiderRampageCooldown <= 0) {
            {
                boolean _setval = false;
                entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.spiderRampageInCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
            {
                double _setval = 60;
                entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.spiderRampageCooldown = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
        }
        if (spiderRampageCooldown <= 30 && spiderRampageCooldown > 28) {
            {
                boolean _setval = false;
                entity.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.spiderRampage = _setval;
                    capability.syncPlayerVariables(entity);
                });
            }
        }
    }
}

