package me.minesuchtiiii.trollboss.listeners.chat;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class ChatListener implements Listener {

    private final TrollBoss plugin;

    public ChatListener(TrollBoss plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {
        if (!(TrollManager.isActive(e.getPlayer().getUniqueId(), TrollType.GARBAGE))) return;

        e.setFormat(e.getFormat());
        String msg = plugin.randomGarbageMessage();
        e.setMessage(msg);
    }

}
