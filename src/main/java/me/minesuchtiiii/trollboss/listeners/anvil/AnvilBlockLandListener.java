package me.minesuchtiiii.trollboss.listeners.anvil;

import me.minesuchtiiii.trollboss.items.keys.AnvilKey;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;

public class AnvilBlockLandListener implements Listener {

    @EventHandler
    public void onFallingAnvilLands(EntityChangeBlockEvent e) {
        if (!(e.getEntity() instanceof FallingBlock anvil)) return;
        if (!AnvilKey.isAnvilTroll(anvil)) return;

        e.setCancelled(true);
        anvil.remove();
        e.getBlock().setType(Material.AIR);
    }

}
