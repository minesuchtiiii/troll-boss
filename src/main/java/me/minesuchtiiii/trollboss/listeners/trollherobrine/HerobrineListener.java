package me.minesuchtiiii.trollboss.listeners.trollherobrine;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import me.minesuchtiiii.trollboss.TrollBoss;

public class HerobrineListener implements Listener {
    private final TrollBoss plugin;

    public HerobrineListener(TrollBoss plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        final Player p = e.getPlayer();

        if (TrollManager.isActive(p.getUniqueId(), TrollType.HEROBRINE)) {
            this.plugin.unsetHerobrine(p);

        }
    }
}
