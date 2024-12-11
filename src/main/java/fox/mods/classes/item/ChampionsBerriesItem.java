package fox.mods.classes.item;

import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.Item;
import net.minecraft.world.food.FoodProperties;

public class ChampionsBerriesItem extends Item {
    public ChampionsBerriesItem() {
        super(new Item.Properties().stacksTo(48).rarity(Rarity.RARE).food((new FoodProperties.Builder()).nutrition(4).saturationMod(0.3f).alwaysEat().build()));
    }
}

