package me.minesuchtiiii.trollboss.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;

public class Util {
    public static List<String> colors = List.of("§a", "§b", "§c", "§d", "§e", "§f", "§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9", "§o", "§k", "§m", "§n", "§l");

    public static String getRandomColor() {
        return colors.get(getRandomColorIndex());
    }

    public static int getRandomColorIndex() {
        return (int) (Math.random() * colors.size());
    }

    public static ItemStack getHead(Player p) {
        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setOwningPlayer(Bukkit.getPlayer(p.getName()));
        item.setItemMeta(skull);

        return item;
    }
}
