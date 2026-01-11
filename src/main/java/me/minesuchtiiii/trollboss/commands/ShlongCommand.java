package me.minesuchtiiii.trollboss.commands;

import me.minesuchtiiii.trollboss.main.Main;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ShlongCommand implements CommandExecutor {
    private final Main plugin;

    public ShlongCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String cmdLabel, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringManager.NOPLAYER);
            return true;
        }

        if (!player.hasPermission("troll.shlong")) {
            player.sendMessage(StringManager.NOPERM);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(StringManager.PREFIX + "§eUse §7/shlong [player]");
            return true;
        }

        if (args.length > 1) {
            player.sendMessage(StringManager.MUCHARGS);
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            plugin.notOnline(player, args[0]);
            return true;
        }

        if (!plugin.canBeTrolled(target)) {
            player.sendMessage(StringManager.BYPASS);
            return true;
        }

        if (target.isDead()) {
            player.sendMessage(StringManager.FAILDEAD);
            return true;
        }

        if (plugin.isShlonged) {
            player.sendMessage(StringManager.PREFIX + "§cCan't do this right now!");
            return true;
        }

        processShlong(player, target);
        return true;
    }


    private void processShlong(Player player, Player target) {
        player.sendMessage(StringManager.PREFIX + "§eShlonging §7" + target.getName() + "§e!");
        target.sendMessage("§eEnjoy the view!");

        Location targetLocation = target.getLocation();
        plugin.oldShlongLocation.put(target.getName(), targetLocation);
        plugin.isShlonged = true;
        plugin.nomine.add(target.getUniqueId());
        plugin.addTroll();
        plugin.addStats("Shlong", player);
        plugin.buildShlong(targetLocation);

        Location shlongLocation = new Location(target.getWorld(), targetLocation.getBlockX() - 7, 200, targetLocation.getBlockZ());
        target.teleport(shlongLocation);

        plugin.teleportBackFromShlong(target);
    }
}