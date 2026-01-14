package me.minesuchtiiii.trollboss.items.keys;

import me.minesuchtiiii.trollboss.TrollBoss;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class AnvilKey {

    public static final NamespacedKey ANVIL_KEY = new NamespacedKey(TrollBoss.getInstance(), "anvil_troll");

    public static boolean isAnvilTroll(FallingBlock fallingBlock) {
        PersistentDataContainer pdc = fallingBlock.getPersistentDataContainer();
        return pdc.has(ANVIL_KEY, PersistentDataType.BOOLEAN);
    }

    public static boolean isAnvilTroll(Entity entity) {
        if (entity == null) return false;

        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        return pdc.has(ANVIL_KEY, PersistentDataType.BOOLEAN);
    }

    public static void setAnvilTroll(FallingBlock fallingBlock) {
        fallingBlock.getPersistentDataContainer().set(AnvilKey.ANVIL_KEY, PersistentDataType.BOOLEAN, true);
    }
}
