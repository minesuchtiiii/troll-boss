package me.minesuchtiiii.trollboss.listeners;

import me.minesuchtiiii.trollboss.TrollBoss;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class MineListener implements Listener {

    private final TrollBoss plugin;

    public MineListener(TrollBoss plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        final Player p = e.getPlayer();

        if (this.plugin.nomine.contains(p.getUniqueId()) || this.plugin.skyTroll.contains(p.getUniqueId()) || this.plugin.isTrapped
                || this.plugin.moveWhileNoobed.contains(p.getUniqueId())) {
            e.setCancelled(true);
        }

    }

}
