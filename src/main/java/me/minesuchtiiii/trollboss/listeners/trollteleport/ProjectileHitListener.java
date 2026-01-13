package me.minesuchtiiii.trollboss.listeners.trollteleport;

import me.minesuchtiiii.trollboss.items.TeleportTrollItem;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;

public class ProjectileHitListener implements Listener {

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getCause() != EntityDamageEvent.DamageCause.PROJECTILE) return;

        if (!(e.getEntity() instanceof final Player damagedPlayer)) return;
        if (!(e.getDamager() instanceof final Snowball snowball)) return;
        if (!(snowball.getShooter() instanceof final Player shooterPlayer)) return;

        ItemStack hand = shooterPlayer.getInventory().getItemInMainHand();
        if (!TeleportTrollItem.isTeleportTroll(hand)) return;

        final Location loc = shooterPlayer.getLocation().clone().add(0.0D, 1.0D, 0.0D);
        e.setDamage(0.0D);
        damagedPlayer.setSneaking(true);
        damagedPlayer.teleport(loc);
        damagedPlayer.setSneaking(false);
    }
}
