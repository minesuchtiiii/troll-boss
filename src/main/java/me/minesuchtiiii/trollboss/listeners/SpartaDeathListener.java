package me.minesuchtiiii.trollboss.listeners;

import me.minesuchtiiii.trollboss.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class SpartaDeathListener implements Listener {

    private final Main plugin;

    public SpartaDeathListener(Main plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onDieSparta(PlayerDeathEvent e) {


        final Player p = e.getEntity();
        if (this.plugin.spartaTroll.contains(p.getUniqueId())) {

            e.setDeathMessage(null);
            this.plugin.diedOnSparta.remove(p.getUniqueId());
            this.plugin.spartaTroll.remove(p.getUniqueId());

        }

    }
}
