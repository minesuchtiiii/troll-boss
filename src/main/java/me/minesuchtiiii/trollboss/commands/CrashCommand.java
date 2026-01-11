package me.minesuchtiiii.trollboss.commands;

import me.minesuchtiiii.trollboss.main.Main;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class CrashCommand implements CommandExecutor {
    private static final String KICK_MESSAGE = "§cInternal exception: java.net.SocketException: Connection reset. Restart your game.";
    private final Main plugin;

    public CrashCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String cmdLabel, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringManager.NOPLAYER);
            return true;
        }

        if (!player.hasPermission("troll.crash")) {
            player.sendMessage(StringManager.NOPERM);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(StringManager.PREFIX + "§eUse §7/crash [player]");
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("all")) {
                crashAllPlayers(player);
            } else {
                crashSinglePlayer(player, args[0]);
            }
        } else {
            player.sendMessage(StringManager.MUCHARGS);
        }

        return true;
    }

    private void crashAllPlayers(Player executor) {
        for (Player target : Bukkit.getOnlinePlayers()) {
            if (target.equals(executor)) {
                continue;
            }

            crashPlayerIfAllowed(executor, target);
        }
        plugin.addTroll();
        plugin.addStats("Crash", executor);
        executor.sendMessage(StringManager.PREFIX + "§eCrashed everyone, except players who can bypass it!");
    }

    private void crashSinglePlayer(Player executor, String targetName) {
        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            plugin.notOnline(executor, targetName);
            return;
        }

        crashPlayerIfAllowed(executor, target);
    }

    private void crashPlayerIfAllowed(Player executor, Player target) {
        if (!plugin.canBeTrolled(target)) {
            executor.sendMessage(StringManager.BYPASS);
            return;
        }

        crashPlayer(target);
        plugin.addTroll();
        plugin.addStats("Crash", executor);
        executor.sendMessage(StringManager.PREFIX + "§eCrashed player §7" + target.getName() + "§e!");
    }

    private void crashPlayer(Player target) {
        plugin.crashed.add(target.getUniqueId());
        target.kickPlayer(KICK_MESSAGE);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> plugin.crashed.remove(target.getUniqueId()), 15 * 20L);
    }
}