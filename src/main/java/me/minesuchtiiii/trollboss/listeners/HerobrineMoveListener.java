package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.minesuchtiiii.trollboss.main.Main;

public class HerobrineMoveListener implements Listener {
	private final Main plugin;

	public HerobrineMoveListener(Main plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		final Player p = e.getPlayer();

		if (this.plugin.herobrine.contains(p.getUniqueId())) {

			Bukkit.getOnlinePlayers().forEach(all -> all.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 0));
		}
	}
}
