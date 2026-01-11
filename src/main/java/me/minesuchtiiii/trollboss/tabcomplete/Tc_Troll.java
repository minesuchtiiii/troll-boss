package me.minesuchtiiii.trollboss.tabcomplete;

import me.minesuchtiiii.trollboss.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Tc_Troll implements TabCompleter {

    private final Main plugin;

    public Tc_Troll(Main plugin) {

        this.plugin = plugin;
    }

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command cmd, @NotNull String cmdLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("troll")) {
            if (args.length == 1) {
                if (sender instanceof Player) {

                    ArrayList<String> completions = new ArrayList<>();
                    completions.add("statistics");
                    completions.add("help");

                    return completions;

                }
            } else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("help")) {

                    ArrayList<String> completions = new ArrayList<>();

                    for (int i = 1; i <= this.plugin.max; i++) {

                        completions.add(String.valueOf(i));

                    }

                    return completions;

                }

            }
        }

        return null;
    }

}
