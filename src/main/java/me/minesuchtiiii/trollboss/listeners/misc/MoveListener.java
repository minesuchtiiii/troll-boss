package me.minesuchtiiii.trollboss.listeners.misc;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import java.util.UUID;

public class MoveListener implements Listener {

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		if (!isMovementRestricted(e.getPlayer().getUniqueId())) return;

        Location from = e.getFrom();
        Location to = e.getTo();

        // Only cancel if the player actually tries to change block
        if (isChangingBlock(from, to)) {
            e.setCancelled(true);
        }
    }

    private boolean isMovementRestricted(UUID uuid) {
        return TrollManager.isActive(uuid, TrollType.DENYMOVE)
                || TrollManager.isActive(uuid, TrollType.SKY)
                || TrollManager.isActive(uuid, TrollType.NOOB);
    }

    private boolean isChangingBlock(Location from, Location to) {
        return from.getBlockX() != to.getBlockX()
                || from.getBlockZ() != to.getBlockZ()
                || from.getBlockY() != to.getBlockY();
    }

}
