package me.minesuchtiiii.trollboss.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import me.minesuchtiiii.trollboss.TrollBoss;

public class JoinListenerWhenCrashed implements Listener {

    private final TrollBoss plugin;

    public JoinListenerWhenCrashed(TrollBoss plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onJoinWhenCrashed(PlayerLoginEvent e) {
        final Player p = e.getPlayer();

        if (this.plugin.crashed.contains(p.getUniqueId())) {

            e.disallow(Result.KICK_OTHER, "§cInternal exception: java.net.SocketException: Connection reset. Restart your game.");

        }

    }

}
