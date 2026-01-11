package me.minesuchtiiii.trollboss.listeners;

import me.minesuchtiiii.trollboss.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListenerBug implements Listener {
    private final Main plugin;

    public DeathListenerBug(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        final Player p = e.getEntity();
        plugin.hurt.remove(p.getUniqueId());
    }
}
