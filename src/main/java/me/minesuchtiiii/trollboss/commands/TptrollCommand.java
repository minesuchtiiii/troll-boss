package me.minesuchtiiii.trollboss.commands;

import me.minesuchtiiii.trollboss.main.Main;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class TptrollCommand implements CommandExecutor {

    private static final String ITEM_NAME = "§cTeleport troll item";
    private static final List<String> ITEM_LORE = List.of(
            "§7Rightclick to shoot snowballs",
            "§7which when they hit a player",
            "§7he will be teleported to you"
    );
    private static final long ITEM_DELAY_TICKS = 40L;
    private final Main plugin;

    public TptrollCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String cmdLabel, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringManager.NOPLAYER);
            return true;
        }

        if (!player.hasPermission("troll.tptroll")) {
            player.sendMessage(StringManager.NOPERM);
            return true;
        }

        if (args.length == 0) {
            giveTrollItemWithDelay(player, player);
            player.sendMessage(StringManager.PREFIX + "§7Here's the item for this troll!");
        } else if (args.length == 1) {
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null) {
                giveTrollItemWithDelay(player, target);
                player.sendMessage(StringManager.PREFIX + "§eYou gave the teleport troll item to §7" + target.getName() + "§e!");
                target.sendMessage(StringManager.PREFIX + "§7You're getting something special!");
            } else {
                plugin.notOnline(player, args[0]);
            }
        } else {
            player.sendMessage(StringManager.MUCHARGS);
        }

        return true;
    }

    private void giveTrollItemWithDelay(Player giver, Player receiver) {
        ItemStack item = createTrollItem();
        plugin.addTroll();
        plugin.addStats("Teleporttroll", giver);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> receiver.getInventory().addItem(item), ITEM_DELAY_TICKS);
    }

    private ItemStack createTrollItem() {
        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta meta = stick.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ITEM_NAME);
            meta.setLore(new ArrayList<>(ITEM_LORE));
            stick.setItemMeta(meta);
        }
        return stick;
    }
}