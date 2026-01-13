package me.minesuchtiiii.trollboss.listeners;

import me.minesuchtiiii.trollboss.TrollBoss;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class InteractEventApple implements Listener {
    private final TrollBoss plugin;

    public InteractEventApple(TrollBoss plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onConsumeApple(PlayerItemConsumeEvent e) {
        final Player p = e.getPlayer();

        final ItemStack apple = new ItemStack(Material.APPLE);
        final ItemMeta meta = apple.getItemMeta();
        meta.setDisplayName("§c§lSpecial apple");

        final ArrayList<String> lore1 = new ArrayList<>();
        lore1.add("§cIf you eat this apple you become Chuck Norris");
        meta.setLore(lore1);
        apple.setItemMeta(meta);

        if (e.getItem().equals(apple)) {
            if (!(p.isOp())) {
                if (!(p.hasPermission("troll.badapple.bypass"))) {

                    plugin.dead.add(p.getUniqueId());
                    p.sendMessage("§cOh no! Looks like this was a bad apple, bye :(");

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> hurtPlayer(p), 60L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> hurtPlayer(p), 80L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> hurtPlayer(p), 100L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> hurtPlayer(p), 120L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        p.setHealth(0.0D);
                        InteractEventApple.this.plugin.dead.remove(p.getUniqueId());
                    }, 140L);

                }
            } else {
                if (plugin.getConfig().getBoolean("Troll-Operators")) {

                    plugin.dead.add(p.getUniqueId());
                    p.sendMessage("§cOh no! Looks like this was a bad apple, bye :(");

                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> hurtPlayer(p), 60L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> hurtPlayer(p), 80L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> hurtPlayer(p), 100L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> hurtPlayer(p), 120L);
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        p.setHealth(0.0D);
                        InteractEventApple.this.plugin.dead.remove(p.getUniqueId());
                    }, 140L);

                }
            }
        }
    }

    private void hurtPlayer(Player player) {

        player.playHurtAnimation(180);
        player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_HURT, 1f, 1f);

    }

}
