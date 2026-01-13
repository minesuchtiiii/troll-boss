package me.minesuchtiiii.trollboss.listeners.anvil;

import me.minesuchtiiii.trollboss.TrollBoss;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.persistence.PersistentDataType;

public class AnvilBlockLandListener implements Listener {

    private final TrollBoss plugin;

    public AnvilBlockLandListener(TrollBoss plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onFallingAnvilLands(EntityChangeBlockEvent e) {

        if (e.getEntity() instanceof FallingBlock anvil) {

            if (!anvil.getPersistentDataContainer().isEmpty() && anvil.getPersistentDataContainer().has(plugin.getKey(), PersistentDataType.STRING)) {
                if ("falling-anvil".equals(anvil.getPersistentDataContainer().get(plugin.getKey(), PersistentDataType.STRING))) {

                    e.setCancelled(true);
                    anvil.remove();
                    e.getBlock().setType(Material.AIR);

                }

            }

        }

    }

}
