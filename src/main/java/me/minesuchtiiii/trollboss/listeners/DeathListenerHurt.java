
package me.minesuchtiiii.trollboss.listeners;

import me.minesuchtiiii.trollboss.main.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListenerHurt implements Listener {
    private final Main plugin;

    public DeathListenerHurt(Main plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onDeathCauseHurtCommand(PlayerDeathEvent e) {

        final Player p = e.getEntity();
        if (this.plugin.deadHurt.contains(p.getUniqueId())) {
            e.setDeathMessage(null);
            this.plugin.deadHurt.remove(p.getUniqueId());
        }
    }

    @EventHandler
    public void onDeathCauseHurtCommand2(PlayerDeathEvent e) {

        final Player p = e.getEntity();
        if (this.plugin.deadMessage.contains(p.getUniqueId())) {
            e.setDeathMessage(null);
            this.plugin.deadMessage.remove(p.getUniqueId());
        }
    }

}
