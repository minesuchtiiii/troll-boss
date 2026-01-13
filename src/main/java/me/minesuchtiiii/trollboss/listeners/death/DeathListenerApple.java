package me.minesuchtiiii.trollboss.listeners.death;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import me.minesuchtiiii.trollboss.TrollBoss;

public class DeathListenerApple implements Listener {
    private final TrollBoss plugin;

    public DeathListenerApple(TrollBoss plugin) {
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
