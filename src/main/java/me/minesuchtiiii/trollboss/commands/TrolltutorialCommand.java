package me.minesuchtiiii.trollboss.commands;

import me.minesuchtiiii.trollboss.manager.trolltutorial.TutorialManager;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TrolltutorialCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String cmdLabel, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringManager.NOPLAYER);
            return true;
        }

        if (!player.hasPermission("troll.tutorial")) {
            player.sendMessage(StringManager.NOPERM);
            return true;
        }

        if (args.length == 0) {
            TutorialManager.handleTutorialStart(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "confirm" -> TutorialManager.handleConfirm(player);
            case "reject" -> TutorialManager.handleReject(player);
            case "stop" -> TutorialManager.handleStop(player);
            default -> player.sendMessage(StringManager.PREFIX + "§eUse §7/trolltutorial [confirm | reject | stop]");
        }

        return true;
    }
}