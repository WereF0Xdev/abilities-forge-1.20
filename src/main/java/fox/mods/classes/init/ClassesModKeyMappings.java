package fox.mods.classes.init;

import fox.mods.classes.network.MainAbilityMessage;
import fox.mods.classes.network.SecondAbilityMessage;
import org.lwjgl.glfw.GLFW;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.client.Minecraft;
import net.minecraft.client.KeyMapping;

import fox.mods.classes.ClassesMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = {Dist.CLIENT})
public class ClassesModKeyMappings {
    public static final KeyMapping MAIN_ABILITY = new KeyMapping("key.classes.main_ability", GLFW.GLFW_KEY_G, "key.categories.classes") {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                ClassesMod.PACKET_HANDLER.sendToServer(new MainAbilityMessage(0, 0));
                MainAbilityMessage.pressAction(Minecraft.getInstance().player, 0, 0);
            }
            isDownOld = isDown;
        }
    };
    public static final KeyMapping SECOND_ABILITY = new KeyMapping("key.classes.second_ability", GLFW.GLFW_KEY_Y, "key.categories.classes") {
        private boolean isDownOld = false;

        @Override
        public void setDown(boolean isDown) {
            super.setDown(isDown);
            if (isDownOld != isDown && isDown) {
                ClassesMod.PACKET_HANDLER.sendToServer(new SecondAbilityMessage(0, 0));
                SecondAbilityMessage.pressAction(Minecraft.getInstance().player, 0, 0);
            }
            isDownOld = isDown;
        }
    };

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(MAIN_ABILITY);
        event.register(SECOND_ABILITY);
    }

    @Mod.EventBusSubscriber({Dist.CLIENT})
    public static class KeyEventListener {
        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (Minecraft.getInstance().screen == null) {
                MAIN_ABILITY.consumeClick();
                SECOND_ABILITY.consumeClick();
            }
        }
    }
}



