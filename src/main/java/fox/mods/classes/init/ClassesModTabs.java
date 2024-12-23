package fox.mods.classes.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;

import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.core.registries.Registries;

import fox.mods.classes.ClassesMod;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ClassesModTabs {
    public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ClassesMod.MODID);
    public static final RegistryObject<CreativeModeTab> CLASSES = REGISTRY.register("classes",
            () -> CreativeModeTab.builder().title(Component.translatable("item_group.classes.classes")).icon(() -> new ItemStack(ClassesModItems.CHAMPIONS_BERRIES.get())).displayItems((parameters, tabData) -> {
                        tabData.accept(ClassesModItems.CHAMPIONS_BERRIES.get());
                        tabData.accept(ClassesModBlocks.CHAMPIONS_BERRIES_PLANT.get().asItem());
                        tabData.accept(ClassesModItems.TOTEM_OF_REBIRTH.get());
                        tabData.accept(ClassesModBlocks.ANCIENT_IRON_VASE.get().asItem());
                        tabData.accept(ClassesModBlocks.ANCIENT_GOLD_VASE.get().asItem());
                        tabData.accept(ClassesModBlocks.ANCIENT_DIAMOND_VASE.get().asItem());
                    })

                    .build());


    @SubscribeEvent
    public static void buildTabContentsVanilla(BuildCreativeModeTabContentsEvent tabData) {
        if (tabData.getTabKey() == CreativeModeTabs.NATURAL_BLOCKS) {

            tabData.accept(ClassesModBlocks.CHAMPIONS_BERRIES_PLANT.get().asItem());

        }

        if (tabData.getTabKey() == CreativeModeTabs.FOOD_AND_DRINKS) {

            tabData.accept(ClassesModItems.CHAMPIONS_BERRIES.get());
        }

        if (tabData.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            tabData.accept(ClassesModBlocks.ANCIENT_IRON_VASE);
            tabData.accept(ClassesModBlocks.ANCIENT_GOLD_VASE);
            tabData.accept(ClassesModBlocks.ANCIENT_DIAMOND_VASE);
        }
    }
}

