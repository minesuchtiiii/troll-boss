package me.minesuchtiiii.trollboss.listeners.interact;

import me.minesuchtiiii.trollboss.items.AK47Item;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractEventAK implements Listener {

    @EventHandler
    public void onPlayerIronAxeClick(PlayerInteractEvent e) {
        if (e.getAction() != Action.RIGHT_CLICK_AIR && e.getAction() != Action.RIGHT_CLICK_BLOCK) return;

        if (!AK47Item.isAK47(e.getItem())) return;

        Player p = e.getPlayer();
        if (!p.hasPermission("troll.ak47")) return;

        p.launchProjectile(Arrow.class);
        p.launchProjectile(Snowball.class);
        p.playSound(p.getLocation(), Sound.ENTITY_CHICKEN_EGG, 1.0F, 1.0F);
    }
}
