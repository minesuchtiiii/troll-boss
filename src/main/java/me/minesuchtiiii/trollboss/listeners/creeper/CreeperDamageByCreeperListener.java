package me.minesuchtiiii.trollboss.listeners.creeper;

import me.minesuchtiiii.trollboss.TrollBoss;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class CreeperDamageByCreeperListener implements Listener {

    private final TrollBoss plugin;

    public CreeperDamageByCreeperListener(TrollBoss plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onCreeperDamageByCreeper(EntityDamageByEntityEvent e) {

        final boolean condition = e.getEntity() instanceof Creeper && e.getDamager() instanceof Creeper
                && (this.plugin.creepers > 0 || this.plugin.bowCreepers > 0);
        if (condition) {

            e.setCancelled(true);

        }

    }

}
