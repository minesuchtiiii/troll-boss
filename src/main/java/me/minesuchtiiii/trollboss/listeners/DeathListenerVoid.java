package me.minesuchtiiii.trollboss.listeners;

import me.minesuchtiiii.trollboss.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListenerVoid implements Listener {
    private final Main plugin;

    public DeathListenerVoid(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeathByVoid(PlayerDeathEvent e) {

        final Player p = e.getEntity();
        if (this.plugin.voidDead.contains(p.getUniqueId())) {
            e.setDeathMessage(null);
            this.plugin.voidDead.remove(p.getUniqueId());
        }
    }
}
