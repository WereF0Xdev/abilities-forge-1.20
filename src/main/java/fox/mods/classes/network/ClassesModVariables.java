package fox.mods.classes.network;

import fox.mods.classes.classes.PlayerClass;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.network.PacketDistributor;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.nbt.Tag;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.Direction;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

import fox.mods.classes.ClassesMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClassesModVariables {
    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        ClassesMod.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new, PlayerVariablesSyncMessage::handler);
    }

    @SubscribeEvent
    public static void init(RegisterCapabilitiesEvent event) {
        event.register(PlayerVariables.class);
    }

    @Mod.EventBusSubscriber
    public static class EventBusVariableHandlers {
        @SubscribeEvent
        public static void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
            if (!event.getEntity().level().isClientSide())
                ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
        }

        @SubscribeEvent
        public static void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
            if (!event.getEntity().level().isClientSide())
                ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
        }

        @SubscribeEvent
        public static void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
            if (!event.getEntity().level().isClientSide())
                ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables())).syncPlayerVariables(event.getEntity());
        }

        @SubscribeEvent
        public static void clonePlayer(PlayerEvent.Clone event) {
            event.getOriginal().revive();
            PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
            PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
            clone.classType = original.classType;
            clone.leapAbilityCooldown = original.leapAbilityCooldown;
            clone.leapAbilityInCooldown = original.leapAbilityInCooldown;
            clone.realTimeTicks = original.realTimeTicks;
            clone.roarAbilityInCooldown = original.roarAbilityInCooldown;
            clone.roarAbilityCooldown = original.roarAbilityCooldown;
            clone.rampage = original.rampage;
            clone.rampageInCooldown = original.rampageInCooldown;
            clone.rampageCooldown = original.rampageCooldown;
            clone.webShotInCooldown = original.webShotInCooldown;
            clone.webShotCooldown = original.webShotCooldown;
            clone.gliding = original.gliding;
            clone.flightInCooldown = original.flightInCooldown;
            clone.flightCooldown = original.flightCooldown;
            clone.chestplateSlotItem = original.chestplateSlotItem;
            clone.noClip = original.noClip;
            clone.phasingInCooldown = original.phasingInCooldown;
            clone.phasingCooldown = original.phasingCooldown;
            clone.usedTotemOfRebirth = original.usedTotemOfRebirth;
            clone.totemOfRebirthUsages = original.totemOfRebirthUsages;
            if (!event.isWasDeath()) {
            }
        }
    }

    public static final Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerVariables>() {
    });

    @Mod.EventBusSubscriber
    private static class PlayerVariablesProvider implements ICapabilitySerializable<Tag> {
        @SubscribeEvent
        public static void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
            if (event.getObject() instanceof Player && !(event.getObject() instanceof FakePlayer))
                event.addCapability(new ResourceLocation("classes", "player_variables"), new PlayerVariablesProvider());
        }

        private final PlayerVariables playerVariables = new PlayerVariables();
        private final LazyOptional<PlayerVariables> instance = LazyOptional.of(() -> playerVariables);

        @Override
        public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
            return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
        }

        @Override
        public Tag serializeNBT() {
            return playerVariables.writeNBT();
        }

        @Override
        public void deserializeNBT(Tag nbt) {
            playerVariables.readNBT(nbt);
        }
    }

    public static class PlayerVariables {
        public PlayerClass classType = PlayerClass.HUMAN;
        public boolean leapAbilityInCooldown = false;
        public double leapAbilityCooldown = 30.0;
        public double realTimeTicks = 0.0415;
        public boolean roarAbilityInCooldown = false;
        public double roarAbilityCooldown = 30.0;
        public boolean rampage = false;
        public boolean rampageInCooldown = false;
        public double rampageCooldown = 60.0;
        public boolean webShotInCooldown = false;
        public double webShotCooldown = 30.0;
        public boolean gliding = false;
        public boolean flightInCooldown = false;
        public double flightCooldown = 40.0;
        public ItemStack chestplateSlotItem = ItemStack.EMPTY;
        public boolean noClip = false;
        public boolean phasingInCooldown = false;
        public double phasingCooldown = 60.0;
        public boolean usedTotemOfRebirth = false;
        public int totemOfRebirthUsages = 0;

        public void syncPlayerVariables(Entity entity) {
            if (entity instanceof ServerPlayer serverPlayer)
                ClassesMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> serverPlayer), new PlayerVariablesSyncMessage(this));
        }

        public Tag writeNBT() {
            CompoundTag nbt = new CompoundTag();
            nbt.putString("classType", classType.name());
            nbt.putDouble("leapAbilityCooldown", leapAbilityCooldown);
            nbt.putBoolean("leapAbilityInCooldown", leapAbilityInCooldown);
            nbt.putDouble("realTimeTicks", realTimeTicks);
            nbt.putBoolean("roarAbilityInCooldown", roarAbilityInCooldown);
            nbt.putDouble("roarAbilityCooldown", roarAbilityCooldown);
            nbt.putBoolean("rampage", rampage);
            nbt.putBoolean("rampageInCooldown", rampageInCooldown);
            nbt.putDouble("rampageCooldown", rampageCooldown);
            nbt.putBoolean("webShotInCooldown", webShotInCooldown);
            nbt.putDouble("webShotCooldown", webShotCooldown);
            nbt.putBoolean("gliding", gliding);
            nbt.putBoolean("flightInCooldown", flightInCooldown);
            nbt.putDouble("flightCooldown", flightCooldown);
            nbt.put("chestplateSlotItem", chestplateSlotItem.save(new CompoundTag()));
            nbt.putBoolean("noClip", noClip);
            nbt.putBoolean("phasingInCooldown", phasingInCooldown);
            nbt.putDouble("phasingCooldown", phasingCooldown);
            nbt.putBoolean("usedTotemOfRebirth", usedTotemOfRebirth);
            nbt.putInt("totemOfRebirthUsages", totemOfRebirthUsages);
            return nbt;
        }

        public void readNBT(Tag tag) {
            CompoundTag nbt = (CompoundTag) tag;
            classType = PlayerClass.fromString(nbt.getString("classType"));
            leapAbilityCooldown = nbt.getDouble("leapAbilityCooldown");
            leapAbilityInCooldown = nbt.getBoolean("leapAbilityInCooldown");
            realTimeTicks = nbt.getDouble("realTimeTicks");
            roarAbilityCooldown = nbt.getDouble("roarAbilityCooldown");
            roarAbilityInCooldown = nbt.getBoolean("roarAbilityInCooldown");
            rampage = nbt.getBoolean("rampage");
            rampageInCooldown = nbt.getBoolean("rampageInCooldown");
            rampageCooldown = nbt.getDouble("rampageCooldown");
            webShotInCooldown = nbt.getBoolean("webShotInCooldown");
            webShotCooldown = nbt.getDouble("webShotCooldown");
            gliding = nbt.getBoolean("gliding");
            flightInCooldown = nbt.getBoolean("flightInCooldown");
            flightCooldown = nbt.getDouble("flightCooldown");
            chestplateSlotItem = ItemStack.of(nbt.getCompound("chestplateSlotItem"));
            noClip = nbt.getBoolean("noClip");
            phasingInCooldown = nbt.getBoolean("phasingInCooldown");
            phasingCooldown = nbt.getDouble("phasingCooldown");
            usedTotemOfRebirth = nbt.getBoolean("usedTotemOfRebirth");
            totemOfRebirthUsages = nbt.getInt("totemOfRebirthUsages");
        }
    }

    public static class PlayerVariablesSyncMessage {
        private final PlayerVariables data;

        public PlayerVariablesSyncMessage(FriendlyByteBuf buffer) {
            this.data = new PlayerVariables();
            CompoundTag tag = buffer.readNbt();
            if (tag != null) {
                this.data.readNBT(tag);
            }
        }

        public PlayerVariablesSyncMessage(PlayerVariables data) {
            this.data = data;
        }

        public static void buffer(PlayerVariablesSyncMessage message, FriendlyByteBuf buffer) {
            buffer.writeNbt((CompoundTag) message.data.writeNBT());
        }

        public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
            NetworkEvent.Context context = contextSupplier.get();
            context.enqueueWork(() -> {
                if (!context.getDirection().getReceptionSide().isServer()) {
                    PlayerVariables variables = Minecraft.getInstance().player
                            .getCapability(PLAYER_VARIABLES_CAPABILITY, null)
                            .orElse(new PlayerVariables());
                    variables.classType = message.data.classType;
                    variables.leapAbilityCooldown = message.data.leapAbilityCooldown;
                    variables.leapAbilityInCooldown = message.data.leapAbilityInCooldown;
                    variables.realTimeTicks = message.data.realTimeTicks;
                    variables.roarAbilityCooldown = message.data.roarAbilityCooldown;
                    variables.roarAbilityInCooldown = message.data.roarAbilityInCooldown;
                    variables.rampage = message.data.rampage;
                    variables.rampageInCooldown = message.data.rampageInCooldown;
                    variables.rampageCooldown = message.data.rampageCooldown;
                    variables.webShotInCooldown = message.data.webShotInCooldown;
                    variables.webShotCooldown = message.data.webShotCooldown;
                    variables.gliding = message.data.gliding;
                    variables.flightInCooldown = message.data.flightInCooldown;
                    variables.flightCooldown = message.data.flightCooldown;
                    variables.chestplateSlotItem = message.data.chestplateSlotItem;
                    variables.noClip = message.data.noClip;
                    variables.phasingInCooldown = message.data.phasingInCooldown;
                    variables.phasingCooldown = message.data.phasingCooldown;
                    variables.usedTotemOfRebirth = message.data.usedTotemOfRebirth;
                    variables.totemOfRebirthUsages = message.data.totemOfRebirthUsages;
                }
            });
            context.setPacketHandled(true);
        }
    }
}

