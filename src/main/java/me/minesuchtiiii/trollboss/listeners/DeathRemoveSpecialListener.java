package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DeathRemoveSpecialListener implements Listener {

    @EventHandler
    public void onDeathCheckSpecials(PlayerDeathEvent e) {

        final ItemStack ironaxe = new ItemStack(Material.IRON_AXE);
        final ItemMeta meta = ironaxe.getItemMeta();
        meta.setDisplayName("§cAK-47");
        ironaxe.setItemMeta(meta);
        final ItemStack blaze = new ItemStack(Material.BLAZE_ROD);
        final ItemMeta blazemeta = blaze.getItemMeta();
        blazemeta.setDisplayName("§cBlock Shooter");
        blaze.setItemMeta(blazemeta);
        e.getDrops().remove(ironaxe);
        e.getDrops().remove(blaze);
    }

}
