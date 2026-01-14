package me.minesuchtiiii.trollboss.listeners.trollapple;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.trolls.TrollType;
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
            if (!(p.isOp() && p.hasPermission("troll.badapple.bypass"))) {
                    executeBadAppleTroll(p);
            } else if (plugin.getConfig().getBoolean("Troll-Operators")) {
                    executeBadAppleTroll(p);
            }
        }
    }

    private void executeBadAppleTroll(Player target) {
        TrollManager.activate(target.getUniqueId(), TrollType.BADAPPLE);
        target.sendMessage("§cOh no! Looks like this was a bad apple, bye :(");

        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> hurtPlayer(target), 60L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> hurtPlayer(target), 80L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> hurtPlayer(target), 100L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> hurtPlayer(target), 120L);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            target.setHealth(0.0D);
            TrollManager.deactivate(target.getUniqueId(), TrollType.BADAPPLE);
        }, 140L);
    }

    private void hurtPlayer(Player target) {
        target.playHurtAnimation(180);
        target.playSound(target.getLocation(), Sound.ENTITY_PLAYER_HURT, 1f, 1f);
    }

}
