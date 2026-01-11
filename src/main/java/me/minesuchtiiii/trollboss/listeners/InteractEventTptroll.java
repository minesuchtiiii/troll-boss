package me.minesuchtiiii.trollboss.listeners;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InteractEventTptroll implements Listener {

	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		final Player p = e.getPlayer();

		final ItemStack stick = new ItemStack(Material.STICK);
		final ItemMeta stickmeta = stick.getItemMeta();
		stickmeta.setDisplayName("§cTeleport troll item");
		final ArrayList<String> lore = new ArrayList<>();
		lore.add("§7Rightclick to shoot snowballs");
		lore.add("§7which when they hit a player");
		lore.add("§7he will be teleported to you");
		stickmeta.setLore(lore);
		stick.setItemMeta(stickmeta);

		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getInventory().getItemInMainHand().isSimilar(stick)) {
				p.launchProjectile(Snowball.class);
			}
		}
	}
}
