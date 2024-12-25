package fox.mods.classes.init;

import fox.mods.classes.entity.GhostBlockEntity;
import fox.mods.classes.entity.GhostBlockProjectileEntity;
import fox.mods.classes.entity.LavaSlimeEntity;
import fox.mods.classes.entity.WebShotEntity;
import fox.mods.classes.entity.ghostblockvariants.cobblestone.GhostCobblestoneBlockEntity;
import fox.mods.classes.entity.ghostblockvariants.cobblestone.projectile.GhostCobblestoneBlockProjectileEntity;
import fox.mods.classes.entity.ghostblockvariants.grass.GhostGrassBlockEntity;
import fox.mods.classes.entity.ghostblockvariants.grass.projectile.GhostGrassBlockProjectileEntity;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
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


    public static final RegistryObject<EntityType<GhostGrassBlockEntity>> GHOST_GRASS_BLOCK = register("ghost_grass_block", EntityType.Builder.<GhostGrassBlockEntity>of(GhostGrassBlockEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
            .setUpdateInterval(3).setCustomClientFactory(GhostGrassBlockEntity::new).fireImmune().sized(0.6f, 1.8f));

    public static final RegistryObject<EntityType<GhostCobblestoneBlockEntity>> GHOST_COBBLESTONE_BLOCK = register("ghost_cobblestone_block", EntityType.Builder.<GhostCobblestoneBlockEntity>of(GhostCobblestoneBlockEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
            .setUpdateInterval(3).setCustomClientFactory(GhostCobblestoneBlockEntity::new).fireImmune().sized(0.6f, 1.8f));


    public static final RegistryObject<EntityType<WebShotEntity>> WEB_SHOT = register("web_shot",
            EntityType.Builder.<WebShotEntity>of(WebShotEntity::new, MobCategory.MISC).setCustomClientFactory(WebShotEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));


    public static final RegistryObject<EntityType<GhostBlockProjectileEntity>> GHOST_BLOCK_PROJECTILE = register("ghost_block_projectile", EntityType.Builder.<GhostBlockProjectileEntity>of(GhostBlockProjectileEntity::new, MobCategory.MISC)
            .setCustomClientFactory(GhostBlockProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

    public static final RegistryObject<EntityType<GhostGrassBlockProjectileEntity>> GHOST_GRASS_BLOCK_PROJECTILE = register("ghost_grass_block_projectile", EntityType.Builder.<GhostGrassBlockProjectileEntity>of(GhostGrassBlockProjectileEntity::new, MobCategory.MISC)
            .setCustomClientFactory(GhostGrassBlockProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));

    public static final RegistryObject<EntityType<GhostCobblestoneBlockProjectileEntity>> GHOST_COBBLESTONE_BLOCK_PROJECTILE = register("ghost_cobblestone_block_projectile", EntityType.Builder.<GhostCobblestoneBlockProjectileEntity>of(GhostCobblestoneBlockProjectileEntity::new, MobCategory.MISC)
            .setCustomClientFactory(GhostCobblestoneBlockProjectileEntity::new).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(1).sized(0.5f, 0.5f));


    private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
        return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
    }

    @SubscribeEvent
    public static void init(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            LavaSlimeEntity.init();
            GhostBlockEntity.init();
            GhostGrassBlockEntity.init();
            GhostCobblestoneBlockEntity.init();
        });
    }

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(LAVA_SLIME.get(), LavaSlimeEntity.createAttributes().build());
        event.put(GHOST_BLOCK.get(), GhostBlockEntity.createAttributes().build());
        event.put(GHOST_GRASS_BLOCK.get(), GhostGrassBlockEntity.createAttributes().build());
        event.put(GHOST_COBBLESTONE_BLOCK.get(), GhostGrassBlockEntity.createAttributes().build());
    }
}

