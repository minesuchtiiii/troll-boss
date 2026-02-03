package me.minesuchtiiii.trollboss.listeners.trollhurt;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListenerBug implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (!TrollManager.deactivate(e.getPlayer().getUniqueId(), TrollType.HURT)) return;

        e.deathMessage(null);
    }
}
