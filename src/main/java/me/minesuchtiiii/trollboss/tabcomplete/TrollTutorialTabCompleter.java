package me.minesuchtiiii.trollboss.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrollTutorialTabCompleter implements TabCompleter {

    private static final List<String> SUBCOMMANDS = List.of("confirm", "reject", "stop");

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command cmd, @NotNull String cmdLabel, String[] args) {

        if (!cmd.getName().equalsIgnoreCase("trolltutorial") || args.length != 1) {
            return Collections.emptyList();
        }

        final List<String> completions = new ArrayList<>();
        StringUtil.copyPartialMatches(args[0], SUBCOMMANDS, completions);

        Collections.sort(completions);

        return completions;
    }
}
