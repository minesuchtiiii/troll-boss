package me.minesuchtiiii.trollboss.listeners.death;

import me.minesuchtiiii.trollboss.TrollBoss;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListenerSky implements Listener {

    private final TrollBoss plugin;

    public DeathListenerSky(TrollBoss plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onDeathWhenSky(PlayerDeathEvent e) {

        final Player p = e.getEntity();
        this.plugin.skyTroll.remove(p.getUniqueId());

    }

}
