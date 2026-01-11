package me.minesuchtiiii.trollboss.commands;

import me.minesuchtiiii.trollboss.main.Main;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TrollkickCommand implements CommandExecutor {
    private static final String KICK_MESSAGE = "§cOops, looks like you lost the connection to the server, try to rejoin!";
    private final Main plugin;

    public TrollkickCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String cmdLabel, String[] arguments) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringManager.NOPLAYER);
            return true;
        }

        if (!player.hasPermission("troll.trollkick")) {
            player.sendMessage(StringManager.NOPERM);
            return true;
        }

        if (arguments.length == 0) {
            player.sendMessage(StringManager.PREFIX + "§eUse §7/trollkick [player]");
            return true;
        }

        if (arguments.length > 1) {
            player.sendMessage(StringManager.MUCHARGS);
            return true;
        }

        Player target = Bukkit.getPlayer(arguments[0]);

        if (target == null) {
            plugin.notOnline(player, arguments[0]);
            return true;
        }

        if (!plugin.canBeTrolled(target)) {
            player.sendMessage(StringManager.BYPASS);
            return true;
        }

        handleKick(player, target);
        return true;
    }

    private void handleKick(Player player, Player target) {

        plugin.addTroll();
        plugin.addStats("Trollkick", player);
        plugin.kicked.add(target.getUniqueId());
        target.kickPlayer(KICK_MESSAGE);
        plugin.kicked.remove(target.getUniqueId());
    }
}