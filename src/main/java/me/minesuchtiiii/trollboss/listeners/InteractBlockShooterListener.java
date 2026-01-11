package me.minesuchtiiii.trollboss.listeners;

import me.minesuchtiiii.trollboss.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractBlockShooterListener implements Listener {

    private final Main plugin;
    int remove = 3;

    public InteractBlockShooterListener(Main plugin) {
        this.plugin = plugin;
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onPlayerInteractBlockShooter(PlayerInteractEvent e) {

        final Player p = e.getPlayer();
        final ItemStack blaze = new ItemStack(Material.BLAZE_ROD);
        final ItemMeta meta = blaze.getItemMeta();
        meta.setDisplayName("§cBlock Shooter");

        if (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.RIGHT_CLICK_AIR) {
            if (p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().equals(meta)) {
                if (p.hasPermission("troll.blockshooter")) {

                    final int random = this.plugin.createRandom(0, 15);
                    final ItemStack clay = new ItemStack(Material.CLAY, 1, (byte) random); //
                    final ItemMeta claymeta = clay.getItemMeta();
                    claymeta.setDisplayName("§cProjectile");
                    clay.setItemMeta(claymeta);

                    final Item block = p.getWorld().dropItem(p.getLocation(), clay);
                    block.setVelocity(p.getLocation().getDirection().multiply(4.5));

                    Bukkit.getScheduler().scheduleSyncDelayedTask(this.plugin, block::remove, remove * 20L);

                }
            }
        }
    }
}
