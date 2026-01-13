package me.minesuchtiiii.trollboss.items.visual;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public final class BlockShooterVisualItem {

    private static final Component NAME = MiniMessage.miniMessage().deserialize("<red>Projectile</red>");

    private BlockShooterVisualItem() {}

    public static ItemStack create() {
        ItemStack item = new ItemStack(Material.CLAY);
        ItemMeta meta = item.getItemMeta();

        meta.displayName(NAME);
        item.setItemMeta(meta);

        return item;
    }
}
