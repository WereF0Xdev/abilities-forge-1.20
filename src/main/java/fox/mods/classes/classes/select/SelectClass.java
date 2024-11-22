package fox.mods.classes.classes.select;

import fox.mods.classes.classes.PlayerClass;
import fox.mods.classes.utils.PlayerClassUtils;
import fox.mods.classes.utils.ScreensUtils;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.DamageSources;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;

public class SelectClass {
    public static void predator(Player player) {
        PlayerClass playerClass = PlayerClass.PREDATOR;
        select(player, playerClass);
        addValueToAttribute(player, Attributes.ATTACK_DAMAGE, 1.5);
        removeValueFromAttribute(player, Attributes.MAX_HEALTH, 4);
        updateHealth(player);
        player.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 999999999, 1, false, false));
    }

        public static void ghost(Player player) {
        PlayerClass playerClass = PlayerClass.GHOST;
        select(player, playerClass);
    }

    private static void select(Player player, PlayerClass playerClass) {
        PlayerClassUtils.setPlayerClass(player, playerClass);
        ScreensUtils.close(player);
        showMessage(player, playerClass);
    }

    private static void addValueToAttribute(Player player, Attribute attribute, double value) {
        if (player.getAttributes().hasAttribute(attribute))
            player.getAttribute(attribute)
                    .setBaseValue(((player.getAttributes().hasAttribute(attribute) ? player.getAttribute(attribute).getBaseValue() : 0) + value));
    }

    private static void removeValueFromAttribute(Player player, Attribute attribute, double value) {
        if (player.getAttributes().hasAttribute(attribute))
            player.getAttribute(attribute)
                    .setBaseValue(((player.getAttributes().hasAttribute(attribute) ? player.getAttribute(attribute).getBaseValue() : 0) - value));
    }

    public static void updateHealth(Player player) {
        LevelAccessor world = player.level();
        player.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(DamageTypes.GENERIC)), 1);
    }

    private static void showMessage(Player player, PlayerClass playerClass) {
        if (!player.level().isClientSide) {
            player.displayClientMessage(Component.literal("§eYou are now a " + playerClass.getDisplayColor() + playerClass.getDisplayName()), true);
            player.displayClientMessage(Component.literal("§eYou selected the Class " + playerClass.getDisplayColor() + playerClass.getDisplayName()), false);
        }
    }
}
