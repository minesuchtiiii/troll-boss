package me.minesuchtiiii.trollboss.tabcomplete;

import me.minesuchtiiii.trollboss.TrollBoss;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.util.StringUtil;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TrollTabCompleter implements TabCompleter {

    @Override
    public List<String> onTabComplete(@NotNull CommandSender sender, Command cmd, @NotNull String cmdLabel, String[] args) {
        List<String> completions = new ArrayList<>();

        if (!cmd.getName().equalsIgnoreCase("troll")) {
            return Collections.emptyList();
        }

        if (args.length == 1) {

            if (sender instanceof Player) {
                List<String> options = List.of("statistics", "help");
                StringUtil.copyPartialMatches(args[0], options, completions);
            }
        }

        else if (args.length == 2 && args[0].equalsIgnoreCase("help")) {
            List<String> pages = new ArrayList<>();
            for (int i = 1; i <= TrollBoss.HELP_PAGES; i++) {
                pages.add(String.valueOf(i));
            }
            StringUtil.copyPartialMatches(args[1], pages, completions);
        }

        Collections.sort(completions);

        return completions;
    }

}
