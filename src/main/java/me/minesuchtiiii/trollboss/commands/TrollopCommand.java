package me.minesuchtiiii.trollboss.commands;

import me.minesuchtiiii.trollboss.main.Main;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TrollopCommand implements CommandExecutor {
    private static final String TROLL_OPERATORS_KEY = "Troll-Operators";
    private static final String USAGE_MESSAGE = StringManager.PREFIX + "§eUse §7/trollop [true | false | status]";

    private final Main plugin;

    public TrollopCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] arguments) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringManager.NOPLAYER);
            return true;
        }

        if (!player.hasPermission("troll.trollopsettings")) {
            player.sendMessage(StringManager.NOPERM);
            return true;
        }

        if (arguments.length == 0) {
            player.sendMessage(USAGE_MESSAGE);
            return true;
        }

        handleCommand(player, arguments[0].toLowerCase());
        return true;
    }

    private void handleCommand(Player player, String argument) {
        switch (argument) {
            case "true" -> enableTrollOperators(player);
            case "false" -> disableTrollOperators(player);
            case "status" -> sendTrollOperatorsStatus(player);
            default -> player.sendMessage(USAGE_MESSAGE);
        }
    }

    private void enableTrollOperators(Player player) {
        if (!plugin.getConfig().getBoolean(TROLL_OPERATORS_KEY)) {
            plugin.getConfig().set(TROLL_OPERATORS_KEY, true);
            plugin.saveConfig();
            player.sendMessage(StringManager.PREFIX + "§aApplying changes...");
        } else {
            player.sendMessage(StringManager.PREFIX + "§eOperators §acan §ebe trolled §aalready§e!");
        }
    }

    private void disableTrollOperators(Player player) {
        if (plugin.getConfig().getBoolean(TROLL_OPERATORS_KEY)) {
            plugin.getConfig().set(TROLL_OPERATORS_KEY, false);
            plugin.saveConfig();
            player.sendMessage(StringManager.PREFIX + "§aApplying changes...");
        } else {
            player.sendMessage(StringManager.PREFIX + "§eOperators §ccannot §ebe trolled §calready§e!");
        }
    }

    private void sendTrollOperatorsStatus(Player player) {
        boolean canTrollOperators = plugin.getConfig().getBoolean(TROLL_OPERATORS_KEY);
        if (canTrollOperators) {
            player.sendMessage(StringManager.PREFIX + "§eOperators §acan §ebe trolled!");
            player.sendMessage(StringManager.PREFIX + "§eChange this setting with §7/trollop false");
        } else {
            player.sendMessage(StringManager.PREFIX + "§eOperators §ccannot §ebe trolled!");
            player.sendMessage(StringManager.PREFIX + "§eChange this setting with §7/trollop true");
        }
    }
}