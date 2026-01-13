package me.minesuchtiiii.trollboss.tabcomplete;

import me.minesuchtiiii.trollboss.TrollBoss;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Tc_Trolltutorial implements TabCompleter {

    private final TrollBoss plugin;

    public Tc_Trolltutorial(TrollBoss plugin) {

        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command cmd, @NotNull String cmdLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("trolltutorial")) {
            if (sender instanceof Player) {

                ArrayList<String> completions = new ArrayList<>();
                completions.add("confirm");
                completions.add("reject");
                completions.add("stop");

                return completions;

            }
        }

        return null;
    }

}
