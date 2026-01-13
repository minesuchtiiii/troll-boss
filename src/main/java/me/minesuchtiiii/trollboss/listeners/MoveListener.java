package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import me.minesuchtiiii.trollboss.TrollBoss;

public class MoveListener implements Listener {
	private final TrollBoss plugin;

	public MoveListener(TrollBoss plugin) {
		this.plugin = plugin;
	}

	@EventHandler
	public void onMove(PlayerMoveEvent e) {
		final Player p = e.getPlayer();

		if (!(this.plugin.denyMove.contains(p.getUniqueId()) || this.plugin.skyTroll.contains(p.getUniqueId())
				|| this.plugin.moveWhileNoobed.contains(p.getUniqueId()))) {
			return;
		}
		final Location from = e.getFrom();
		final Location to = e.getTo();
		double x = Math.floor(from.getX());
		double z = Math.floor(from.getZ());
		if ((Math.floor(to.getX()) != x) || (Math.floor(to.getZ()) != z)) {
			x += 0.5D;
			z += 0.5D;
			e.getPlayer().teleport(new Location(from.getWorld(), x, from.getY(), z, from.getYaw(), from.getPitch()));
		}
	}
}
