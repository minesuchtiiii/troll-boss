package me.minesuchtiiii.trollboss.listeners.trollcrash;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

public class JoinListenerWhenCrashed implements Listener {
    private static final Component KICK_MESSAGE = MiniMessage.miniMessage().deserialize("<red>Internal exception: java.net.SocketException: Connection reset. Restart your game.</red>");

    @EventHandler
    public void onJoinWhenCrashed(PlayerLoginEvent e) {
        if (!TrollManager.isActive(e.getPlayer().getUniqueId(), TrollType.CRASH)) return;

        e.disallow(Result.KICK_OTHER, KICK_MESSAGE);
    }

}
