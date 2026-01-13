package me.minesuchtiiii.trollboss.listeners.projectiles;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.inventory.ItemStack;

@SuppressWarnings("deprecation")
public class EntityDamageByBlockShooterListener implements Listener {

    @EventHandler
    public void onHit(PlayerPickupItemEvent e) {
        final Player p = e.getPlayer();
        final ItemStack i = e.getItem().getItemStack();

        final boolean condition = i.getType() == Material.CLAY && i.getItemMeta().hasDisplayName();
        if (condition) {
            if ("§cProjectile".equals(i.getItemMeta().getDisplayName())) {

                final Item item = e.getItem();

                if (item.getVelocity().length() < 1) {

                    e.setCancelled(true);
                    p.damage(2);

                }
            }
        }
    }
}
