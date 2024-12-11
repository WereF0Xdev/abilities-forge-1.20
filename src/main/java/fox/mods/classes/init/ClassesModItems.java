package fox.mods.classes.init;

import fox.mods.classes.item.ChampionsBerriesItem;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.item.Item;

import fox.mods.classes.ClassesMod;

public class ClassesModItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ClassesMod.MODID);
    public static final RegistryObject<Item> CHAMPIONS_BERRIES = REGISTRY.register("champions_berries", () -> new ChampionsBerriesItem());
}
