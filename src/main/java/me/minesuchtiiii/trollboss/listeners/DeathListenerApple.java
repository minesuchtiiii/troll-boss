package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.minesuchtiiii.trollboss.main.Main;

public class DeathListenerApple implements Listener {
    private final Main plugin;

    public DeathListenerApple(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {

        final Player p = e.getEntity();
        if (this.plugin.dead.contains(p.getUniqueId())) {
            e.setDeathMessage(null);
            this.plugin.dead.remove(p.getUniqueId());
        }
    }
}
