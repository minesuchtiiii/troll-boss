package me.minesuchtiiii.trollboss.listeners.misc;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

public class MineListener implements Listener {

    private final TrollBoss plugin;

    public MineListener(TrollBoss plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        if (!isMineRestricted(e.getPlayer().getUniqueId())) return;

        e.setCancelled(true);
    }

    private boolean isMineRestricted(UUID uuid) {
        return this.plugin.isTrapped
                || TrollManager.isActive(uuid, TrollType.NOMINE)
                || TrollManager.isActive(uuid, TrollType.SKY)
                || TrollManager.isActive(uuid, TrollType.NOOB);
    }

}
