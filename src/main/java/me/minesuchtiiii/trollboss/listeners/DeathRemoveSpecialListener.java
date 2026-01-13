package me.minesuchtiiii.trollboss.listeners;

import me.minesuchtiiii.trollboss.items.AK47Item;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DeathRemoveSpecialListener implements Listener {

    @EventHandler
    public void onDeathCheckSpecials(PlayerDeathEvent e) {
        final ItemStack blaze = new ItemStack(Material.BLAZE_ROD);
        final ItemMeta blazemeta = blaze.getItemMeta();
        blazemeta.setDisplayName("§cBlock Shooter");
        blaze.setItemMeta(blazemeta);
        e.getDrops().removeIf(AK47Item::isAK47);
        e.getDrops().remove(blaze);
    }

}
