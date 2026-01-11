package me.minesuchtiiii.trollboss.listeners;

import me.minesuchtiiii.trollboss.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListenerSky implements Listener {

    private final Main plugin;

    public DeathListenerSky(Main plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onDeathWhenSky(PlayerDeathEvent e) {

        final Player p = e.getEntity();
        this.plugin.skyTroll.remove(p.getUniqueId());

    }

}
