package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.minesuchtiiii.trollboss.main.Main;

public class DeathListenerAnvil implements Listener {

	private final Main plugin;

	public DeathListenerAnvil(Main plugin) {

		this.plugin = plugin;
	}

	@EventHandler
	public void onDeathByAnvil(PlayerDeathEvent e) {

		final Player p = e.getEntity();
		if (this.plugin.anvilDeath.contains(p.getUniqueId())) {

			e.setDeathMessage(null);

		}

	}

}
