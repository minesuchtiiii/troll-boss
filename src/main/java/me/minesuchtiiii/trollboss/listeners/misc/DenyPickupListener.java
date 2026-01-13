package me.minesuchtiiii.trollboss.listeners.misc;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

import me.minesuchtiiii.trollboss.TrollBoss;

@SuppressWarnings("deprecation")
public class DenyPickupListener implements Listener {

	private final TrollBoss plugin;

	public DenyPickupListener(TrollBoss plugin) {

		this.plugin = plugin;

	}

	@EventHandler
	public void onPickupWhenTrolled(PlayerPickupItemEvent e) {

		final Player p = e.getPlayer();

		if (this.plugin.denyPickup.contains(p.getUniqueId())) {

			e.setCancelled(true);

		}

	}

}
