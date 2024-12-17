package fox.mods.classes.data.animations.lava_slime;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;

public class DisplayLavaSlimeAnimations {

    public static boolean dying(Entity entity) {
        if (!entityExists(entity))
            return false;
        return entity.getPersistentData().getBoolean("dead");
    }

    public static boolean attacking(Entity entity) {
        if (!entityExists(entity))
            return false;
        return entity instanceof Mob _mobEnt0 && _mobEnt0.isAggressive();
    }

    public static boolean idle(Entity entity) {
        if (!entityExists(entity))
            return false;
        return !dying(entity) && !attacking(entity);
    }

    private static boolean entityExists(Entity entity) {
        return entity != null;
    }
}
