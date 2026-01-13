package me.minesuchtiiii.trollboss.listeners.chat;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import me.minesuchtiiii.trollboss.TrollBoss;

@SuppressWarnings("deprecation")
public class PlayerChatMuteListener implements Listener {

	private final TrollBoss plugin;

	public PlayerChatMuteListener(TrollBoss plugin) {

		this.plugin = plugin;

	}

	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {

		if (this.plugin.muted.contains(e.getPlayer().getUniqueId())) {

			e.setCancelled(true);

		}
	}

}
