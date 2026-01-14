package me.minesuchtiiii.trollboss.listeners.death;

import me.minesuchtiiii.trollboss.manager.DeathManager;
import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.UUID;

public class SpartaDeathListener implements Listener {

    @EventHandler
    public void onDieSparta(PlayerDeathEvent e) {
        final UUID uuid = e.getPlayer().getUniqueId();

        if (!(TrollManager.deactivate(uuid, TrollType.SPARTA))) return;

        DeathManager.removeDead(uuid, TrollType.SPARTA);
        e.deathMessage(null);
    }
}
