package me.minesuchtiiii.trollboss.commands;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class TrolltutorialCommand implements CommandExecutor {

    private final TrollBoss plugin;

    public TrolltutorialCommand(TrollBoss plugin) {

        this.plugin = plugin;
    }

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
            handleTutorialStart(player);
            return true;
        }

        switch (args[0].toLowerCase()) {
            case "confirm" -> handleConfirm(player);
            case "reject" -> handleReject(player);
            case "stop" -> handleStop(player);
            default -> player.sendMessage(StringManager.PREFIX + "§eUse §7/trolltutorial [confirm | reject | stop]");
        }

        return true;
    }

    private void handleTutorialStart(Player player) {
        if (plugin.tutorialPlayers.contains(player.getUniqueId()) && plugin.canAccept.containsKey(player.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cYou can still §7confirm §eor §7reject§e!");
            return;
        }

        player.sendMessage(StringManager.PREFIX + "§eAre you sure you want to start the Troll-Tutorial?");
        player.sendMessage(StringManager.PREFIX + "§eTo §7confirm §etype §7/trolltutorial confirm §ein the next §730 seconds§e!");
        player.sendMessage(StringManager.PREFIX + "§eTo §7reject §etype §7/trolltutorial reject §eor ignore this.");

        plugin.tutorialPlayers.add(player.getUniqueId());
        plugin.canAccept.put(player.getUniqueId(), true);
        plugin.secondsToAccept.put(player.getUniqueId(), 30);

        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            int remainingTime = plugin.secondsToAccept.get(player.getUniqueId()) - 1;
            plugin.secondsToAccept.put(player.getUniqueId(), remainingTime);

            if (remainingTime <= 0) {
                plugin.tutorialPlayers.remove(player.getUniqueId());
                player.sendMessage(StringManager.PREFIX + "§cAborted tutorial!");
                plugin.canAccept.remove(player.getUniqueId());
                plugin.secondsToAccept.remove(player.getUniqueId());
                plugin.endTask(player);
            }
        }, 10L, 20L);

        plugin.taskID.put(player.getUniqueId(), taskId);
    }

    private void handleConfirm(Player player) {
        if (!plugin.tutorialPlayers.contains(player.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cNothing to confirm!");
            player.sendMessage(StringManager.PREFIX + "§cTo start the tutorial type §7/trolltutorial");
            return;
        }

        if (!plugin.canAccept.containsKey(player.getUniqueId()) || !plugin.canAccept.get(player.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cYou already started the tutorial!");
            return;
        }

        plugin.secondsToAccept.remove(player.getUniqueId());
        plugin.endTask(player);
        player.sendMessage(StringManager.PREFIX + "§aStarting Troll-Tutorial...");
        plugin.startTrollTutorial(player);
        plugin.canAccept.remove(player.getUniqueId());
    }

    private void handleReject(Player player) {
        if (!plugin.tutorialPlayers.contains(player.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cNothing to reject!");
            player.sendMessage(StringManager.PREFIX + "§cTo start the tutorial type §7/trolltutorial");
            return;
        }

        if (!plugin.canAccept.containsKey(player.getUniqueId()) || !plugin.canAccept.get(player.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cTutorial already running!");
            player.sendMessage(StringManager.PREFIX + "§cTo abort the tutorial type §7/trolltutorial stop");
            return;
        }

        plugin.tutorialPlayers.remove(player.getUniqueId());
        plugin.canAccept.remove(player.getUniqueId());
        plugin.secondsToAccept.remove(player.getUniqueId());
        plugin.endTask(player);
        player.sendMessage(StringManager.PREFIX + "§cRejected tutorial start!");
    }

    private void handleStop(Player player) {
        if (!plugin.tutorialPlayers.contains(player.getUniqueId()) || !plugin.tutorialNum.containsKey(player.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cThere's no tutorial to stop!");
            return;
        }

        plugin.tutorialPlayers.remove(player.getUniqueId());
        plugin.tutorialNum.remove(player.getUniqueId());
        plugin.checkIfIsInUsable(player);
        plugin.endTask(player);

        player.sendMessage("");
        player.sendMessage(StringManager.PREFIX + "§cTutorial stopped!");
        player.sendMessage("");
    }

}
