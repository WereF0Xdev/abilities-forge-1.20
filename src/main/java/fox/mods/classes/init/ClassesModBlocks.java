package fox.mods.classes.init;

import fox.mods.classes.block.ChampionsBerriesPlantBlock;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import fox.mods.classes.ClassesMod;

public class ClassesModBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, ClassesMod.MODID);
    public static final RegistryObject<Block> CHAMPIONS_BERRIES_PLANT = REGISTRY.register("champions_berries_plant", () -> new ChampionsBerriesPlantBlock());
}

