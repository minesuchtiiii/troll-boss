package me.minesuchtiiii.trollboss.listeners.death;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListenerSky implements Listener {
    @EventHandler
    public void onDeathWhenSky(PlayerDeathEvent e) {
        TrollManager.deactivate(e.getPlayer().getUniqueId(), TrollType.SKY);
    }

}
