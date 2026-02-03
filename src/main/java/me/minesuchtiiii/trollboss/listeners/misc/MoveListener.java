package me.minesuchtiiii.trollboss.listeners.misc;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollFlag;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

public class MoveListener implements Listener {

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Location from = e.getFrom();
        Location to = e.getTo();

        // 1. Performance Check:
        // Have the block coordinates even changed?
        // If X, Y, and Z in the block are the same, it's just movement WITHIN the block.
        // Since we want to allow that, we cancel early here.
        if (from.getBlockX() == to.getBlockX() &&
                from.getBlockY() == to.getBlockY() &&
                from.getBlockZ() == to.getBlockZ()) {
            return;
        }

        UUID uuid = e.getPlayer().getUniqueId();

        // Completely frozen (May not leave the block, not even jumping)
        // Checks X, Y, and Z. As soon as ANY block coordinate changes -> Cancel.
        if (TrollManager.hasFlag(uuid, TrollFlag.PREVENT_ALL_MOVEMENT)) {
            // Since we already checked above IF the block has changed,
            // we know here: Yes, it has changed. So prevent it.
            e.setCancelled(true);
            return;
        }

        // Only horizontal movement blocked (May not walk away, but can jump)
        if (TrollManager.hasFlag(uuid, TrollFlag.PREVENT_GROUND_MOVEMENT)) {

            // Here we need to check specifically: Has X or Z changed?
            // (Y change doesn't matter, as jumping is allowed)
            if (from.getBlockX() != to.getBlockX() || from.getBlockZ() != to.getBlockZ()) {
                e.setCancelled(true);
            }
        }
    }

}
