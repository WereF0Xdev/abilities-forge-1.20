package fox.mods.classes.init;

import fox.mods.classes.entity.GhostBlockEntity;
import fox.mods.classes.entity.LavaSlimeEntity;
import fox.mods.classes.entity.WebShotEntity;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;

import fox.mods.classes.ClassesMod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClassesModEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, ClassesMod.MODID);
    public static final RegistryObject<EntityType<GhostBlockEntity>> GHOST_BLOCK = register("ghost_block", EntityType.Builder.<GhostBlockEntity>of(GhostBlockEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
            .setUpdateInterval(3).setCustomClientFactory(GhostBlockEntity::new).fireImmune().sized(0.6f, 1.8f));
    public static final RegistryObject<EntityType<LavaSlimeEntity>> LAVA_SLIME = register("lava_slime",
            EntityType.Builder.<LavaSlimeEntity>of(LavaSlimeEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3).setCustomClientFactory(LavaSlimeEntity::new)

                    .sized(0.6f, 1.8f));

    public static final RegistryObject<EntityType<WebShotEntity>> WEB_SHOT = register("web_shot",
            EntityType.Builder.<WebShotEntity>of(WebShotEntity::new, MobCategory.MISC).setCustomClientFactory(WebShotEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));


    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LavaSlimeEntity.init();
            GhostBlockEntity.init();
        });
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(LAVA_SLIME.get(), LavaSlimeEntity.createAttributes().build());
        event.put(GHOST_BLOCK.get(), GhostBlockEntity.createAttributes().build());
    }
}

