package me.minesuchtiiii.trollboss.listeners.chat;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class PlayerChatMuteListener implements Listener {

	@EventHandler
	public void onPlayerChat(PlayerChatEvent e) {
        if (!TrollManager.isActive(e.getPlayer().getUniqueId(), TrollType.STFU)) return;

        e.setCancelled(true);
	}

}
