package me.minesuchtiiii.trollboss.items;

import me.minesuchtiiii.trollboss.TrollBoss;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public final class BlockShooterItem {

    public static final NamespacedKey BLOCK_SHOOTER_KEY = new NamespacedKey(TrollBoss.getInstance(), "block_shooter");
    public static final String NAME_STRING = "§cBlock Shooter";
    private static final Component NAME = MiniMessage.miniMessage().deserialize("<red>Block Shooter</red>");

    public static ItemStack create() {
        ItemStack item = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(NAME);
        meta.getPersistentDataContainer().set(BLOCK_SHOOTER_KEY, PersistentDataType.BOOLEAN,true);

        item.setItemMeta(meta);
        return item;
    }

    public static boolean isBlockShooter(ItemStack item) {
        if (item == null) return false;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        return isBlockShooter(meta.getPersistentDataContainer());
    }

    public static boolean isBlockShooter(PersistentDataContainer pdc) {
        return pdc.has(BLOCK_SHOOTER_KEY, PersistentDataType.BOOLEAN);
    }
}
