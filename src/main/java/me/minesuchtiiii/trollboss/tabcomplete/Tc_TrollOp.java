package me.minesuchtiiii.trollboss.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class Tc_TrollOp implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command cmd, @NotNull String cmdLabel, String[] args) {

        if (cmd.getName().equalsIgnoreCase("trollop")) {
            if (sender instanceof Player) {

                ArrayList<String> completions = new ArrayList<>();
                completions.add("true");
                completions.add("false");
                completions.add("status");

                return completions;

            }
        }

        return null;
    }

}
