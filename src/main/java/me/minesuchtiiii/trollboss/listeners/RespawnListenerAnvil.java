package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import me.minesuchtiiii.trollboss.main.Main;

public class RespawnListenerAnvil implements Listener {

	private final Main plugin;

	public RespawnListenerAnvil(Main plugin) {

		this.plugin = plugin;
	}

	@EventHandler
	public void onRespawnWhenDeathByAnvil(PlayerRespawnEvent e) {

		final Player p = e.getPlayer();

		this.plugin.anvilDeath.remove(p.getUniqueId());

	}

}
