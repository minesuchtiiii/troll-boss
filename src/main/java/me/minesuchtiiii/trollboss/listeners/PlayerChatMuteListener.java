package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import me.minesuchtiiii.trollboss.main.Main;

@SuppressWarnings("deprecation")
public class PlayerChatMuteListener implements Listener {

	private final Main plugin;

	public PlayerChatMuteListener(Main plugin) {

		this.plugin = plugin;

	}

	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {

		if (this.plugin.muted.contains(e.getPlayer().getUniqueId())) {

			e.setCancelled(true);

		}
	}

}
