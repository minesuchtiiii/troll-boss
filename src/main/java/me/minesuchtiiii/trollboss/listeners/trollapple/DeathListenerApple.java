package me.minesuchtiiii.trollboss.listeners.trollapple;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListenerApple implements Listener {

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        if (!TrollManager.deactivate(e.getPlayer().getUniqueId(), TrollType.BADAPPLE)) return;

        e.deathMessage(null);
    }
}
