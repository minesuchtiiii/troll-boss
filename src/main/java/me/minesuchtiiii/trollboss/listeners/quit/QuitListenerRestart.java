package me.minesuchtiiii.trollboss.listeners.quit;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListenerRestart implements Listener {
	@EventHandler
	public void onQuitWhenRestarting(PlayerQuitEvent e) {
        if (!(TrollManager.isActive(e.getPlayer().getUniqueId(), TrollType.FAKERESTART))) return;

        e.quitMessage(null);
	}
}
