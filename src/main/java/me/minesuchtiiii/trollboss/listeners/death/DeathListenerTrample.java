package me.minesuchtiiii.trollboss.listeners.death;

import me.minesuchtiiii.trollboss.TrollBoss;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListenerTrample implements Listener {

    private final TrollBoss plugin;

    public DeathListenerTrample(TrollBoss plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeadFromTrample(PlayerDeathEvent e) {

        final Player p = e.getEntity();

        if (this.plugin.trampled.contains(p.getUniqueId())) {
            e.setDeathMessage(null);
            this.plugin.trampled.remove(p.getUniqueId());

            this.plugin.removeCows();

        }

    }

}
