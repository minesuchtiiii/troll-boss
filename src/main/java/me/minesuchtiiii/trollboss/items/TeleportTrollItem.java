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

import java.util.List;

public final class TeleportTrollItem {

    private static final MiniMessage MM = MiniMessage.miniMessage();

    public static final NamespacedKey TELEPORT_TROLL_KEY = new NamespacedKey(TrollBoss.getInstance(), "teleport_troll");

    private static final Component NAME =
            MM.deserialize("<red>Teleport troll item</red>");

    private static final List<Component> LORE = List.of(
            MM.deserialize("<gray>Right-click to shoot snowballs</gray>"),
            MM.deserialize("<gray>which when they hit a player</gray>"),
            MM.deserialize("<gray>he will be teleported to you</gray>")
    );

    private TeleportTrollItem() {}

    public static ItemStack create() {
        ItemStack item = new ItemStack(Material.STICK);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(NAME);
        meta.lore(LORE);
        meta.getPersistentDataContainer().set(TELEPORT_TROLL_KEY, PersistentDataType.BOOLEAN,true);

        item.setItemMeta(meta);
        return item;
    }

    public static boolean isTeleportTroll(ItemStack item) {
        if (item == null) return false;
        return isTeleportTroll(item.getItemMeta());
    }

    public static boolean isTeleportTroll(ItemMeta meta) {
        if (meta == null) return false;
        return isTeleportTroll(meta.getPersistentDataContainer());
    }

    public static boolean isTeleportTroll(PersistentDataContainer pdc) {
        return pdc.has(TELEPORT_TROLL_KEY, PersistentDataType.BOOLEAN);
    }
}
