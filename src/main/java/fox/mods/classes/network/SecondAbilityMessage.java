package fox.mods.classes.network;

import fox.mods.classes.abilities.TriggerSecondAbility;
import fox.mods.classes.abilities.predator.RoarAbility;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;

import java.util.function.Supplier;

import fox.mods.classes.ClassesMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class SecondAbilityMessage {
    int type, pressedms;

    public SecondAbilityMessage(int type, int pressedms) {
        this.type = type;
        this.pressedms = pressedms;
    }

    public SecondAbilityMessage(FriendlyByteBuf buffer) {
        this.type = buffer.readInt();
        this.pressedms = buffer.readInt();
    }

    public static void buffer(SecondAbilityMessage message, FriendlyByteBuf buffer) {
        buffer.writeInt(message.type);
        buffer.writeInt(message.pressedms);
    }

    public static void handler(SecondAbilityMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            pressAction(context.getSender(), message.type, message.pressedms);
        });
        context.setPacketHandled(true);
    }

    public static void pressAction(Player entity, int type, int pressedms) {
        Level world = entity.level();
        double x = entity.getX();
        double y = entity.getY();
        double z = entity.getZ();
        if (!world.hasChunkAt(entity.blockPosition()))
            return;
        if (type == 0) {

            TriggerSecondAbility.execute(entity);
        }
    }

    @SubscribeEvent
    public static void registerMessage(FMLCommonSetupEvent event) {
        ClassesMod.addNetworkMessage(SecondAbilityMessage.class, SecondAbilityMessage::buffer, SecondAbilityMessage::new, SecondAbilityMessage::handler);
    }
}

