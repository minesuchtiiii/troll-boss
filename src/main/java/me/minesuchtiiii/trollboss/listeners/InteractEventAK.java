package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractEventAK implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerIronAxeClick(PlayerInteractEvent e) {
        final Player p = e.getPlayer();

        final ItemStack ironaxe = new ItemStack(Material.IRON_AXE);
        final ItemMeta meta = ironaxe.getItemMeta();
        meta.setDisplayName("§cAK-47");

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().equals(meta)) {
                if (p.hasPermission("troll.ak47")) {

                    p.launchProjectile(Arrow.class);
                    p.launchProjectile(Snowball.class);
                    p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);

                }
            }
        }
    }
}
