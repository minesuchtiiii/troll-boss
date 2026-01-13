package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.minesuchtiiii.trollboss.TrollBoss;

public class QuitListener implements Listener {
	private final TrollBoss plugin;

	public QuitListener(TrollBoss plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		final Player p = e.getPlayer();

		if (this.plugin.kicked.contains(p.getUniqueId())) {
			e.setQuitMessage(null);
		}
	}
}
