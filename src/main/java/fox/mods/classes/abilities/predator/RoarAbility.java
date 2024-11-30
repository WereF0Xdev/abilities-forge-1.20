package fox.mods.classes.abilities.predator;

import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Comparator;
import java.util.List;

public class RoarAbility {
    public static void trigger(Player player) {

        if (player == null) {
            return;
        }

        LevelAccessor world = player.level();
        double x = player.getX();
        double y = player.getY();
        double z = player.getZ();

        double roarCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).roarAbilityCooldown;
        boolean roarInCooldown = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).roarAbilityInCooldown;

        if (PlayerClassUtils.isPredator(player)) {
            if (!roarInCooldown) {
                if (world instanceof Level _level) {
                    if (!_level.isClientSide()) {
                        _level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.warden.roar")), SoundSource.NEUTRAL, 20, 1);
                    } else {
                        _level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.warden.roar")), SoundSource.NEUTRAL, 20, 1, false);
                    }
                }
                player.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 40, 0, false, false));
                {
                    final Vec3 _center = new Vec3(x, y, z);
                    List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(12 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).toList();
                    for (Entity entityiterator : _entfound) {
                        if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
                            _entity.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 1, true, true));
                        if (entityiterator instanceof LivingEntity _entity && !_entity.level().isClientSide())
                            _entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1, true, true));
                    }
                }
                cooldownRoar(player);
                clearPlayerRoarEffects(player);
            } else {
                player.displayClientMessage(Component.literal("§cRoar §fis charging! §7(" + (new java.text.DecimalFormat("##").format(roarCooldown)) + "s)"), true);
            }
        }
    }

    private static void cooldownRoar(Player player) {
        {
            boolean _setval = true;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.roarAbilityInCooldown = _setval;
                capability.syncPlayerVariables(player);
            });
        }
    }

    private static void clearPlayerRoarEffects(Player player) {
        player.removeEffect(MobEffects.MOVEMENT_SLOWDOWN);
        player.removeEffect(MobEffects.BLINDNESS);
    }
}
