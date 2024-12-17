package fox.mods.classes.events;

import net.minecraft.world.entity.Entity;

public class LavaSlimeEntityDies {
    public static void execute(Entity entity) {
        if (entity == null)
            return;
        entity.getPersistentData().putBoolean("dead", true);
    }
}
