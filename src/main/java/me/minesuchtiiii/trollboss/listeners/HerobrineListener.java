package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.minesuchtiiii.trollboss.main.Main;

public class HerobrineListener implements Listener {
    private final Main plugin;

    public HerobrineListener(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final Player p = e.getPlayer();

        if (this.plugin.herobrine.contains(p.getUniqueId())) {
            this.plugin.unsetHerobrine(p);

        }
    }
}
