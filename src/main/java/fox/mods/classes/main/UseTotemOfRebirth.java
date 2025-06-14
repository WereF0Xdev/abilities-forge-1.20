package fox.mods.classes.main;

import fox.mods.classes.ClassesMod;
import fox.mods.classes.init.ClassesModItems;
import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.AdvancementUtils;
import fox.mods.classes.utils.ParticlesUtils;
import fox.mods.classes.utils.SoundUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.ForgeRegistries;

public class UseTotemOfRebirth {

    public static void full(Player player) {
        {
            boolean _setval = true;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.usedTotemOfRebirth = _setval;
                capability.syncPlayerVariables(player);
            });
        }
        player.displayClientMessage(Component.literal("§dYou will now change Class upon death."), true);
        ItemStack _setstack = new ItemStack(ClassesModItems.EMPTY_TOTEM_OF_REBIRTH.get()).copy();
        _setstack.setCount(1);
        player.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
        player.getInventory().setChanged();
        setDurability(player);
        cooldownItem(player);
        SoundUtils.playPlayer(player, "item.totem.use");
        ParticlesUtils.spawnCircleParticles(player, player.level(), ParticleTypes.TOTEM_OF_UNDYING, 15, 1);
        if (player.level().isClientSide())
            Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ClassesModItems.TOTEM_OF_REBIRTH.get()));
    }

    public static void empty(Player player) {
        {
            boolean _setval = false;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.usedTotemOfRebirth = _setval;
                capability.syncPlayerVariables(player);
            });
        }
        player.displayClientMessage(Component.literal("§dYou will no longer change Class upon death."), true);
        ItemStack _setstack = new ItemStack(ClassesModItems.TOTEM_OF_REBIRTH.get()).copy();
        _setstack.setCount(1);
        player.setItemInHand(InteractionHand.MAIN_HAND, _setstack);
        player.getInventory().setChanged();
        setDurability(player);
        cooldownItem(player);
        SoundUtils.playPlayer(player, "item.totem.use");
        ParticlesUtils.spawnCircleParticles(player, player.level(), ParticleTypes.TOTEM_OF_UNDYING, 15, 1);
        if (player.level().isClientSide())
            Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ClassesModItems.EMPTY_TOTEM_OF_REBIRTH.get()));
    }

    private static int getDurability(Player player) {
        return (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).totemOfRebirthUsages;
    }

    private static ItemStack getMainHand(Player player) {
        return player.getMainHandItem();
    }

    private static void setDurability(Player player) {
        ItemStack itemstack = getMainHand(player);
        {
            ItemStack _ist = itemstack;
            if (_ist.hurt(getDurability(player) + 1, RandomSource.create(), null)) {
                _ist.shrink(1);
                _ist.setDamageValue(0);
            }
        }
        {
            int _setval = getDurability(player) + 1;
            player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                capability.totemOfRebirthUsages = _setval;
                capability.syncPlayerVariables(player);
            });
        }
        if (getDurability(player) >= 3) {
            {
                int _setval = 0;
                player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                    capability.totemOfRebirthUsages = _setval;
                    capability.syncPlayerVariables(player);
                });
            }
        }
    }

    private static void cooldownItem(Player player) {
        player.getCooldowns().addCooldown(getMainHand(player).getItem(), 20);
    }
}
