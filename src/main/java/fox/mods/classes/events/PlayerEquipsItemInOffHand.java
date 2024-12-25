package fox.mods.classes.events;

import fox.mods.classes.utils.PlayerClassUtils;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.entity.living.LivingEquipmentChangeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class PlayerEquipsItemInOffHand {
    @SubscribeEvent
    public static void LivingEquipmentChangeEvent(LivingEquipmentChangeEvent event) {
        if (event != null && event.getEntity() != null) {
            execute(event, event.getEntity(), event.getSlot(), event.getTo());
        }
    }

    public static void execute(Entity entity, EquipmentSlot slot, ItemStack itemStack) {
        execute(null, entity, slot, itemStack);
    }

    private static void execute(@Nullable Event event, Entity entity, EquipmentSlot slot, ItemStack itemStack) {
        if (entity == null)
            return;
        if (entity instanceof Player player) {
            if (!slot.isArmor()) {
                if (PlayerClassUtils.isGhost(player) || PlayerClassUtils.isGiant(player)) {

                    player.setItemSlot(slot, ItemStack.EMPTY);

                    boolean added = player.getInventory().add(itemStack);

                    if (!added && !player.level().isClientSide) {
                        player.drop(itemStack, false);
                    }
                }
            }
        }
    }
}
