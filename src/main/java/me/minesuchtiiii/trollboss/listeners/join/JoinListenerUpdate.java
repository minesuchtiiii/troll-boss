package me.minesuchtiiii.trollboss.listeners.join;

import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.minesuchtiiii.trollboss.TrollBoss;

public class JoinListenerUpdate implements Listener {
	private final TrollBoss plugin;

	public JoinListenerUpdate(TrollBoss plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		final Player p = e.getPlayer();

		if (this.plugin.worked) {
			if (p.isOp()) {
				Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, () -> {

					p.sendMessage(StringManager.PREFIX + "§3An update of §cTroll §3has been downloaded successfully!");
					JoinListenerUpdate.this.plugin.worked = false;

				}, 40L);
			}
		}
	}
}
