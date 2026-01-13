package me.minesuchtiiii.trollboss.listeners.chat;

import me.minesuchtiiii.trollboss.TrollBoss;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

@SuppressWarnings("deprecation")
public class ChatListener implements Listener {

    private final TrollBoss plugin;

    public ChatListener(TrollBoss plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onChat(PlayerChatEvent e) {

        final Player p = e.getPlayer();

        if (this.plugin.garbageTroll.contains(p.getUniqueId())) {

            e.setFormat(e.getFormat());
            String msg = plugin.randomGarbageMessage();
            e.setMessage(msg);

        }

    }

}
