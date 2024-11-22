package fox.mods.classes.init;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

import fox.mods.classes.world.inventory.SelectClassMenu;
import fox.mods.classes.ClassesMod;

public class ClassesModMenus {
    public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ClassesMod.MODID);
    public static final RegistryObject<MenuType<SelectClassMenu>> SELECT_CLASS = REGISTRY.register("select_class", () -> IForgeMenuType.create(SelectClassMenu::new));
}

