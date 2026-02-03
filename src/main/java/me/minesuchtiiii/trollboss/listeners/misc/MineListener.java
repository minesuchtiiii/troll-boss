package me.minesuchtiiii.trollboss.listeners.misc;

import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollFlag;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.UUID;

public class MineListener implements Listener {

    @EventHandler
    public void onMine(BlockBreakEvent e) {
        if (!isMineRestricted(e.getPlayer().getUniqueId())) return;

        e.setCancelled(true);
    }

    private boolean isMineRestricted(UUID uuid) {
        return TrollManager.hasFlag(uuid, TrollFlag.PREVENT_MINING);
    }

}
