package fox.mods.classes.item;

import fox.mods.classes.ClassesMod;
import fox.mods.classes.network.ClassesModVariables;
import fox.mods.classes.utils.SoundUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;

import static fox.mods.classes.utils.ParticlesUtils.spawnSwirlParticles;

public class ChampionsBerriesItem extends Item {
    public ChampionsBerriesItem() {
        super(new Item.Properties().stacksTo(48).rarity(Rarity.RARE).food((new FoodProperties.Builder()).nutrition(4).saturationMod(0.3f).alwaysEat().build()));
    }


    @Override
    public ItemStack finishUsingItem(ItemStack itemstack, Level world, LivingEntity entity) {
        ItemStack retval = super.finishUsingItem(itemstack, world, entity);
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        consume(entity);
        return retval;
    }

    private static void consume(LivingEntity entity) {
        if (entity instanceof Player player) {
            double realTimeTicks = (player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new ClassesModVariables.PlayerVariables())).realTimeTicks;
            if (realTimeTicks == 0.0415) {
                {
                    double _setval = realTimeTicks * 2;
                    player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                        capability.realTimeTicks = _setval;
                        capability.syncPlayerVariables(player);
                    });
                }
                ClassesMod.queueServerWork(30 * 5, () -> {
                    {
                        double _setval = 0.0415;
                        player.getCapability(ClassesModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
                            capability.realTimeTicks = _setval;
                            capability.syncPlayerVariables(player);
                        });
                    }
                });
                spawnSwirlParticles(player, ParticleTypes.ELECTRIC_SPARK);
                SoundUtils.playPlayer(player, "block.amethyst_block.place");
            }

        }
    }
}

