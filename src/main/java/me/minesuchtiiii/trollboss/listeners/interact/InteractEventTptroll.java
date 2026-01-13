package me.minesuchtiiii.trollboss.listeners.interact;

import me.minesuchtiiii.trollboss.items.TeleportTrollItem;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractEventTptroll implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if (!TeleportTrollItem.isTeleportTroll(e.getItem())) return;

        e.setCancelled(true);
        e.getPlayer().launchProjectile(Snowball.class);
    }
}
