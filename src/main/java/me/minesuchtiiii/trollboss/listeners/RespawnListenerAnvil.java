package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.minesuchtiiii.trollboss.TrollBoss;

public class RespawnListenerAnvil implements Listener {

	private final TrollBoss plugin;

	public RespawnListenerAnvil(TrollBoss plugin) {

		this.plugin = plugin;
	}

	@EventHandler
	public void onRespawnWhenDeathByAnvil(PlayerRespawnEvent e) {

		final Player p = e.getPlayer();

		this.plugin.anvilDeath.remove(p.getUniqueId());

	}

}
