package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ProjectileHitListener implements Listener {

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        final Entity ent = e.getEntity();
        final Entity dam = e.getDamager();

        final ItemStack stick = new ItemStack(Material.STICK);
        final ItemMeta stickmeta = stick.getItemMeta();
        stickmeta.setDisplayName("§cTeleport troll item");
        final ArrayList<String> lore = new ArrayList<>();
        lore.add("§7Rightclick to shoot snowballs");
        lore.add("§7which when they hit a player");
        lore.add("§7he will be teleported to you");
        stickmeta.setLore(lore);
        stick.setItemMeta(stickmeta);

        if (ent instanceof Player && dam instanceof Snowball) {
            if (e.getCause() == EntityDamageEvent.DamageCause.PROJECTILE) {
                final Player p = (Player) e.getEntity();
                final Snowball s = (Snowball) dam;

                if (s.getShooter() instanceof final Player pl) {
                    if (pl.getItemInHand().equals(stick)) {

                        final Location loc = pl.getLocation().add(0.0D, 1.0D, 0.0D);
                        e.setDamage(0.0D);
                        p.setSneaking(true);
                        p.teleport(loc);
                        p.setSneaking(false);

                    }
                }
            }
        }
    }
}
