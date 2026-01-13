package me.minesuchtiiii.trollboss.commands;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TrollCommand implements CommandExecutor {
    private static final String PERMISSION_TROLL = "troll.troll";
    private static final String PERMISSION_TROLL_HELP = "troll.trollhelp";
    private static final String PERMISSION_STATISTICS = "troll.statistics";
    private static final String PERMISSION_GUI = "troll.gui";

    private final TrollBoss plugin;

    public TrollCommand(TrollBoss plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String cmdLabel, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringManager.NOPLAYER);
            return true;
        }

        if (!player.hasPermission(PERMISSION_TROLL)) {
            player.sendMessage(StringManager.NOPERM);
            return true;
        }

        if (args.length == 0) {
            openTrollInventory(player);
            return true;
        }

        if (args.length == 1) {
            if ("statistics".equalsIgnoreCase(args[0])) {
                openStatistics(player);
                return true;
            } else if ("help".equalsIgnoreCase(args[0])) {
                handleHelpCommand(player, args);
                return true;
            }

            openTargetGui(player, args[0]);
            return true;
        }

        if (args.length == 2) {
            if ("help".equalsIgnoreCase(args[0])) {
                handleHelpCommand(player, args);
                return true;
            }
        }

        player.sendMessage(StringManager.MUCHARGS);
        return true;
    }

    private void openTrollInventory(Player player) {
        plugin.openTrollInv(player);
        player.sendMessage(StringManager.PREFIX + "§eFor more commands use §7/troll help §c[page]");
    }

    private void handleHelpCommand(Player player, String[] args) {
        if (!player.hasPermission(PERMISSION_TROLL_HELP)) {
            player.sendMessage(StringManager.NOPERM);
            return;
        }

        int page = 1;
        if (args.length == 2 && plugin.isInt(args[1])) {
            page = Integer.parseInt(args[1]);
        } else if (args.length == 2 && !plugin.isInt(args[1])) {
            player.sendMessage(StringManager.PREFIX + "§cError! §e" + args[1] + " §cis not a number!");
            return;
        }

        plugin.sendHelp(player, page);
    }

    private void openStatistics(Player player) {
        if (player.hasPermission(PERMISSION_STATISTICS)) {
            plugin.openStatisticsInv(player);
        } else {
            player.sendMessage(StringManager.NOPERM);
        }
    }

    private void openTargetGui(Player player, String targetName) {
        Player target = Bukkit.getPlayer(targetName);
        if (target == null) {
            plugin.notOnline(player, targetName);
            return;
        }

        if (plugin.canBeTrolled(target)) {
            if (player.hasPermission(PERMISSION_GUI)) {
                plugin.openGui(player);
                plugin.trolling.put(player.getUniqueId(), target.getUniqueId());
            } else {
                player.sendMessage(StringManager.NOPERM);
            }
        } else {
            player.sendMessage(StringManager.BYPASS);
        }
    }
}
