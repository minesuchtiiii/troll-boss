package me.minesuchtiiii.trollboss.listeners.trollherobrine;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.minesuchtiiii.trollboss.TrollBoss;

public class HerobrineMoveListener implements Listener {
	private final TrollBoss plugin;

	public HerobrineMoveListener(TrollBoss plugin) {
		this.plugin = plugin;
	}

	@SuppressWarnings("deprecation")
	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		final Player p = e.getPlayer();

		if (TrollManager.isActive(p.getUniqueId(), TrollType.HEROBRINE)) {

			Bukkit.getOnlinePlayers().forEach(all -> all.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 0));
		}
	}
}
