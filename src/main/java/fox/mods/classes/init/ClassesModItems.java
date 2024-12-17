package fox.mods.classes.init;

import fox.mods.classes.item.EmptyTotemOfRebirthItem;
import fox.mods.classes.item.TotemOfRebirthItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

import fox.mods.classes.item.ChampionsBerriesItem;
import fox.mods.classes.ClassesMod;


public class ClassesModItems {
    public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, ClassesMod.MODID);
    public static final RegistryObject<Item> CHAMPIONS_BERRIES = REGISTRY.register("champions_berries", () -> new ChampionsBerriesItem());
    public static final RegistryObject<Item> CHAMPIONS_BERRIES_PLANT = block(ClassesModBlocks.CHAMPIONS_BERRIES_PLANT);
    public static final RegistryObject<Item> TOTEM_OF_REBIRTH = REGISTRY.register("totem_of_rebirth", () -> new TotemOfRebirthItem());
    public static final RegistryObject<Item> EMPTY_TOTEM_OF_REBIRTH = REGISTRY.register("empty_totem_of_rebirth", () -> new EmptyTotemOfRebirthItem());
    public static final RegistryObject<Item> ANCIENT_IRON_VASE = block(ClassesModBlocks.ANCIENT_IRON_VASE);
    public static final RegistryObject<Item> ANCIENT_GOLD_VASE = block(ClassesModBlocks.ANCIENT_GOLD_VASE);
    public static final RegistryObject<Item> ANCIENT_DIAMOND_VASE = block(ClassesModBlocks.ANCIENT_DIAMOND_VASE);


    private static RegistryObject<Item> block(RegistryObject<Block> block) {
        return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
    }
}
