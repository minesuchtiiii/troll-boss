package me.minesuchtiiii.trollboss.commands;

import me.minesuchtiiii.trollboss.main.Main;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class BowsCommand implements CommandExecutor {
    private final Main plugin;

    public BowsCommand(Main plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringManager.NOPLAYER);
            return true;
        }

        if (!(player.hasPermission("troll.trollbows"))) {
            player.sendMessage(StringManager.NOPERM);
            return true;
        }

        if (args.length != 0) {
            player.sendMessage(StringManager.MUCHARGS);
            return true;
        }
        
        this.plugin.openBowWindow(player);
        this.plugin.addTroll();
        this.plugin.addStats("Trollbows", player);

        return true;
    }

}