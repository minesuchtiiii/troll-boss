package me.minesuchtiiii.trollboss.listeners.interact;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.items.BlockShooterItem;
import me.minesuchtiiii.trollboss.items.visual.BlockShooterVisualItem;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class InteractBlockShooterListener implements Listener {

    private final TrollBoss plugin;
    private final int removeDelaySeconds = 3;

    public InteractBlockShooterListener(TrollBoss plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerInteractBlockShooter(PlayerInteractEvent e) {

        Action action = e.getAction();
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) return;

        Player player = e.getPlayer();
        ItemStack hand = e.getItem();

        if (!BlockShooterItem.isBlockShooter(hand)) return;
        if (!player.hasPermission("troll.blockshooter")) return;

        Location playerLocation = player.getLocation();
        Item dropped = player.getWorld().dropItem(playerLocation, BlockShooterVisualItem.create());
        dropped.setVelocity(playerLocation.getDirection().multiply(4.5));

        Bukkit.getScheduler().runTaskLater(plugin, dropped::remove, removeDelaySeconds * 20L);
    }
}
