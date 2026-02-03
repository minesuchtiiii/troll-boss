package me.minesuchtiiii.trollboss.listeners.misc;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

public class DenyPickupListener implements Listener {

	@EventHandler
	public void onPickupWhenTrolled(EntityPickupItemEvent e) {
        if (!(e.getEntity() instanceof Player player)) return;
        if (!TrollManager.isActive(player.getUniqueId(), TrollType.DROPINV)) return;

        e.setCancelled(true);
	}

}
