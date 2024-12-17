package fox.mods.classes.init;

import fox.mods.classes.block.AncientDiamondVaseBlock;
import fox.mods.classes.block.AncientGoldVaseBlock;
import fox.mods.classes.block.AncientIronVaseBlock;
import fox.mods.classes.block.ChampionsBerriesPlantBlock;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

import fox.mods.classes.ClassesMod;

public class ClassesModBlocks {
    public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, ClassesMod.MODID);
    public static final RegistryObject<Block> CHAMPIONS_BERRIES_PLANT = REGISTRY.register("champions_berries_plant", () -> new ChampionsBerriesPlantBlock());
    public static final RegistryObject<Block> ANCIENT_IRON_VASE = REGISTRY.register("ancient_iron_vase", () -> new AncientIronVaseBlock());
    public static final RegistryObject<Block> ANCIENT_GOLD_VASE = REGISTRY.register("ancient_gold_vase", () -> new AncientGoldVaseBlock());
    public static final RegistryObject<Block> ANCIENT_DIAMOND_VASE = REGISTRY.register("ancient_diamond_vase", () -> new AncientDiamondVaseBlock());
}

