package me.minesuchtiiii.trollboss.listeners.death;

import me.minesuchtiiii.trollboss.items.AK47Item;
import me.minesuchtiiii.trollboss.items.BlockShooterItem;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.persistence.PersistentDataContainer;

public class DeathRemoveSpecialListener implements Listener {

    @EventHandler
    public void onDeathCheckSpecials(PlayerDeathEvent e) {
        e.getDrops().removeIf(item -> {
            final PersistentDataContainer pdc = item.getItemMeta().getPersistentDataContainer();
            return AK47Item.isAK47(pdc) || BlockShooterItem.isBlockShooter(pdc);
        });
    }

}
