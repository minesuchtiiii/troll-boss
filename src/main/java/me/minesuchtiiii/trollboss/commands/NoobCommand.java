package me.minesuchtiiii.trollboss.commands;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class NoobCommand implements CommandExecutor {

    private final TrollBoss plugin;

    public NoobCommand(TrollBoss plugin) {

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String cmdLabel, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringManager.NOPLAYER);
            return true;
        }

        if (!player.hasPermission("troll.noob")) {
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(StringManager.PREFIX + "§eUse §7/noob [player]");
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

        handleNoobCommand(player, target);
        return true;
    }

    private void handleNoobCommand(Player sender, Player target) {
        if (target.isDead()) {
            sender.sendMessage(StringManager.FAILDEAD);
            return;
        }

        if (!plugin.canNoob) {
            sender.sendMessage(StringManager.PREFIX + "§cCan't do this right now!");
            return;
        }

        if (!plugin.canBeTrolled(target)) {
            sender.sendMessage(StringManager.BYPASS);
            return;
        }

        executeNoobAction(sender, target);
    }

    private void executeNoobAction(Player sender, Player target) {
        sender.sendMessage(StringManager.PREFIX + "§eOfficially noobing §7" + target.getName() + "§e!");
        plugin.addTroll();
        plugin.addStats("Noob", sender);
        plugin.moveWhileNoobed.add(target.getUniqueId());
        plugin.canNoob = false;
        plugin.beforeNoob.put(target.getName(), target.getLocation());
        plugin.noobIt(target);
    }

}
