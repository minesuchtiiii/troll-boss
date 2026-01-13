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

public final class AK47Item {

    private static final MiniMessage MM = MiniMessage.miniMessage();
    public static final NamespacedKey AK47_KEY = new NamespacedKey(TrollBoss.getInstance(), "ak47");
    public static final String NAME_STRING = "§cAK-47";
    private static final Component NAME = MM.deserialize("<red>AK-47</red>");

    public static ItemStack create() {
        ItemStack item = new ItemStack(Material.IRON_AXE);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(NAME);
        meta.getPersistentDataContainer().set(AK47_KEY, PersistentDataType.BOOLEAN, true);

        item.setItemMeta(meta);
        return item;
    }

    public static boolean isAK47(ItemStack item) {
        if (item == null) return false;

        ItemMeta meta = item.getItemMeta();
        if (meta == null) return false;

        return isAK47(meta.getPersistentDataContainer());
    }

    public static boolean isAK47(PersistentDataContainer pdc) {
        return pdc.has(AK47_KEY, PersistentDataType.BOOLEAN);
    }
}
