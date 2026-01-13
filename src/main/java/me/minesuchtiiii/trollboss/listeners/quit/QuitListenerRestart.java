package me.minesuchtiiii.trollboss.listeners.quit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.minesuchtiiii.trollboss.TrollBoss;

public class QuitListenerRestart implements Listener {
	private final TrollBoss plugin;

	public QuitListenerRestart(TrollBoss plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onQuitWhenRestarting(PlayerQuitEvent e) {
		final Player p = e.getPlayer();

		if (this.plugin.res.contains(p.getUniqueId())) {
			e.quitMessage(null);
		}
	}
}
