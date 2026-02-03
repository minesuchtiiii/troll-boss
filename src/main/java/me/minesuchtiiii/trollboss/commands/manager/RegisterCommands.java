package me.minesuchtiiii.trollboss.commands.manager;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.commands.*;
import me.minesuchtiiii.trollboss.tabcomplete.TrollTabCompleter;
import me.minesuchtiiii.trollboss.tabcomplete.TrollOpTabCompleter;
import me.minesuchtiiii.trollboss.tabcomplete.TrollTutorialTabCompleter;
import org.bukkit.command.PluginCommand;

import javax.annotation.Nullable;

public class RegisterCommands {

    private RegisterCommands() {}

    public static void register(TrollBoss plugin) {

        requireCommand("troll").setExecutor(new TrollCommand(plugin));
        requireCommand("troll").setTabCompleter(new TrollTabCompleter());
        requireCommand("burn").setExecutor(new BurnCommand(plugin));
        requireCommand("freeze").setExecutor(new FreezeCommand(plugin));
        requireCommand("bolt").setExecutor(new BoltCommand(plugin));
        requireCommand("special").setExecutor(new SpecialCommand(plugin));
        requireCommand("boom").setExecutor(new BoomCommand(plugin));
        requireCommand("push").setExecutor(new PushCommand(plugin));
        requireCommand("fakeop").setExecutor(new FakeopCommand(plugin));
        requireCommand("fakedeop").setExecutor(new FakedeopCommand(plugin));
        requireCommand("launch").setExecutor(new LaunchCommand(plugin));
        requireCommand("spam").setExecutor(new SpamCommand(plugin));
        requireCommand("gokill").setExecutor(new GokillCommand(plugin));
        requireCommand("switch").setExecutor(new SwitchCommand(plugin));
        requireCommand("trollkick").setExecutor(new TrollkickCommand(plugin));
        requireCommand("badapple").setExecutor(new BadappleCommand(plugin));
        requireCommand("denymove").setExecutor(new DenymoveCommand(plugin));
        requireCommand("potatotroll").setExecutor(new PotatotrollCommand(plugin));
        requireCommand("trap").setExecutor(new TrapCommand(plugin));
        requireCommand("tptroll").setExecutor(new TptrollCommand(plugin));
        requireCommand("infect").setExecutor(new InfectCommand(plugin));
        requireCommand("herobrine").setExecutor(new HerobrineCommand(plugin));
        requireCommand("fakerestart").setExecutor(new FakeRestartCommand(plugin));
        requireCommand("turn").setExecutor(new TurnCommand(plugin));
        requireCommand("starve").setExecutor(new StarveCommand(plugin));
        requireCommand("hurt").setExecutor(new HurtCommand(plugin));
        requireCommand("void").setExecutor(new VoidCommand(plugin));
        requireCommand("pumpkinhead").setExecutor(new PumpkinheadCommand(plugin));
        requireCommand("bury").setExecutor(new BuryCommand(plugin));
        requireCommand("nomine").setExecutor(new NomineCommand(plugin));
        requireCommand("randomtp").setExecutor(new RandomTpCommand(plugin));
        requireCommand("crash").setExecutor(new CrashCommand(plugin));
        requireCommand("freefall").setExecutor(new FreefallCommand(plugin));
        requireCommand("webtrap").setExecutor(new WebtrapCommand(plugin));
        requireCommand("spank").setExecutor(new SpankCommand(plugin));
        requireCommand("trample").setExecutor(new TrampleCommand(plugin));
        requireCommand("trollop").setExecutor(new TrollopCommand(plugin));
        requireCommand("trollop").setTabCompleter(new TrollOpTabCompleter());
        requireCommand("stfu").setExecutor(new StfuCommand(plugin));
        requireCommand("popup").setExecutor(new PopupCommand(plugin));
        requireCommand("sky").setExecutor(new SkyCommand(plugin));
        requireCommand("abduct").setExecutor(new AbductCommand(plugin));
        requireCommand("popular").setExecutor(new PopularCommand(plugin));
        requireCommand("creeper").setExecutor(new CreeperCommand(plugin));
        requireCommand("sparta").setExecutor(new SpartaCommand(plugin));
        requireCommand("trollbows").setExecutor(new BowsCommand(plugin));
        requireCommand("drug").setExecutor(new DrugCommand(plugin));
        requireCommand("squidrain").setExecutor(new SquidrainCommand(plugin));
        requireCommand("trolltutorial").setExecutor(new TrolltutorialCommand());
        requireCommand("trolltutorial").setTabCompleter(new TrollTutorialTabCompleter());
        requireCommand("dropinv").setExecutor(new DropinvCommand(plugin));
        requireCommand("garbage").setExecutor(new GarbageCommand(plugin));
        requireCommand("anvil").setExecutor(new AnvilCommand(plugin));
        requireCommand("invtext").setExecutor(new InvtextCommand(plugin));
        requireCommand("runforrest").setExecutor(new RunforrestCommand(plugin));
        requireCommand("border").setExecutor(new BorderCommand(plugin));
        requireCommand("noob").setExecutor(new NoobCommand(plugin));
        requireCommand("randomtroll").setExecutor(new RandomtrollCommand(plugin));
        requireCommand("schlong").setExecutor(new SchlongCommand(plugin));

    }

    private static PluginCommand requireCommand(String name) {
        final @Nullable PluginCommand command = TrollBoss.getInstance().getCommand(name);

        if (command == null) {
            throw new IllegalStateException("Command '" + name + "' is not defined in plugin.yml");
        }

        return command;
    }

}
