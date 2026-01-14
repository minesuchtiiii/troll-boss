package me.minesuchtiiii.trollboss.manager;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;

public class TutorialManager {
    private static final HashSet<UUID> tutorialPlayers = new HashSet<>();
    private static final HashSet<UUID> usable = new HashSet<>();
    private static final HashMap<UUID, Integer> tutorialNum = new HashMap<>();
    private static final HashMap<UUID, Integer> taskID = new HashMap<>();
    private static final HashMap<UUID, Boolean> canAccept = new HashMap<>();
    private static final HashMap<UUID, Integer> secondsToAccept = new HashMap<>();
    private static int tutorialTask;

    private TutorialManager() {}

    public static void startTrollTutorial(Player p) {
        tutorialNum.put(p.getUniqueId(), 0);

        tutorialTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(TrollBoss.getInstance(), () -> {
            tutorialNum.put(p.getUniqueId(), tutorialNum.get(p.getUniqueId()) + 1);
            if (tutorialNum.get(p.getUniqueId()) == 1) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6In this tutorial you will learn everything you need to know about this plugin!");
            } else if (tutorialNum.get(p.getUniqueId()) == 10) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Let's start with the basic command of this plugin: §4/troll");
            } else if (tutorialNum.get(p.getUniqueId()) == 15) {
                p.sendMessage("");
                p.sendMessage(
                        StringManager.PREFIX + "§6When typing §4/troll §6an inventory opens, where you can see heads");
                p.sendMessage(StringManager.PREFIX + "§6of players (if there are any online)");
            } else if (tutorialNum.get(p.getUniqueId()) == 23) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6The inventory looks like this:");
                usable.add(p.getUniqueId());
            } else if (tutorialNum.get(p.getUniqueId()) == 27) {
                GuiManager.openTrollInv(p);
            } else if (tutorialNum.get(p.getUniqueId()) == 31) {
                p.sendMessage("");
                p.getOpenInventory().close();
                usable.remove(p.getUniqueId());
                p.sendMessage(StringManager.PREFIX + "§6You can choose a player by clicking on his head");
            } else if (tutorialNum.get(p.getUniqueId()) == 36) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6After you clicked on his head another inventory will be opened");
                p.sendMessage(StringManager.PREFIX + "§6The Troll-GUI!");
            } else if (tutorialNum.get(p.getUniqueId()) == 43) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6The Troll-GUI looks like this:");
                usable.add(p.getUniqueId());
            } else if (tutorialNum.get(p.getUniqueId()) == 47) {
                GuiManager.openGui(p);
            } else if (tutorialNum.get(p.getUniqueId()) == 51) {
                p.sendMessage("");
                p.getOpenInventory().close();
                usable.remove(p.getUniqueId());
                p.sendMessage(StringManager.PREFIX + "§6In the Troll-GUI you can see a lot of different items");
                p.sendMessage(StringManager.PREFIX + "§6Each item starts a different troll for the player you selected before");
            } else if (tutorialNum.get(p.getUniqueId()) == 58) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6If you want to speed things up a bit you can use the command §4/troll [player]");
                p.sendMessage(StringManager.PREFIX + "§6With this command the Troll-GUI opens immediately, the selected player is the");
                p.sendMessage(StringManager.PREFIX + "§6argument after the command");
            } else if (tutorialNum.get(p.getUniqueId()) == 69) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6If you want to speed up things even more you can just type the command");
                p.sendMessage(StringManager.PREFIX + "§6To get a list of all commands of this plugin type §4/troll help [1-5]");
                p.sendMessage(StringManager.PREFIX + "§6There are 6 help pages, so for example you can type §4/troll help 3");
            } else if (tutorialNum.get(p.getUniqueId()) == 82) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6So how about bypassing trolls?");
            } else if (tutorialNum.get(p.getUniqueId()) == 85) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Users with the permission §4troll.bypass §6can bypass every troll");
                p.sendMessage(StringManager.PREFIX + "§6They are just not trollable!");
            } else if (tutorialNum.get(p.getUniqueId()) == 90) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6But wait... operators have all permission, this means they also have the bypass permission");
                p.sendMessage(StringManager.PREFIX + "§6Does this mean that I cannot troll operators?");
            } else if (tutorialNum.get(p.getUniqueId()) == 98) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6The answer is: §4You can troll operators!");
                p.sendMessage(StringManager.PREFIX + "§6With the command §4/trollop [true | false] §6you can decide");
                p.sendMessage(StringManager.PREFIX + "§6whether operators can be trolled or not, isn't that great? :)");
            } else if (tutorialNum.get(p.getUniqueId()) == 110) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6You might ask yourself \"How server friendly is this plugin?\"");
                p.sendMessage(StringManager.PREFIX + "§6Do the trolls destroy the world and some stuff I built?");
            } else if (tutorialNum.get(p.getUniqueId()) == 118) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6The answer is: §4No!");
                p.sendMessage(StringManager.PREFIX + "§6The plugin is very server friendly, it doesn't destroy any");
                p.sendMessage(StringManager.PREFIX + "§6blocks at all, and if in a command blocks get placed they will");
                p.sendMessage(StringManager.PREFIX + "§6be removed again and the old blocks at the locations will be reset!");
                p.sendMessage(StringManager.PREFIX + "§6So don't worry about this! :)");
            } else if (tutorialNum.get(p.getUniqueId()) == 135) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Alright, but is there a way to see how many times I used a certain troll?");
            } else if (tutorialNum.get(p.getUniqueId()) == 139) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6The answer is: §4Yes!");
                p.sendMessage(StringManager.PREFIX + "§6With the command §4/troll statistics §6you get statistics about every");
                p.sendMessage(StringManager.PREFIX + "§6single troll command you used!");
            } else if (tutorialNum.get(p.getUniqueId()) == 147) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Is there anything else I should know?");
            } else if (tutorialNum.get(p.getUniqueId()) == 150) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Yes! There are some special commands, like");
                p.sendMessage(StringManager.PREFIX + "§4/special [1-2] §6or §4/trollbows");
            } else if (tutorialNum.get(p.getUniqueId()) == 154) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6What this commands do? Well, just try them out! :)");
            } else if (tutorialNum.get(p.getUniqueId()) == 158) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6That's it for now, this tutorial might get edited in the future");
            } else if (tutorialNum.get(p.getUniqueId()) == 165) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§4Important! §6If you find any bugs please report them on the site");
                p.sendMessage(StringManager.PREFIX + "§6you downloaded this plugin, this helps to improve it!");
            } else if (tutorialNum.get(p.getUniqueId()) == 170) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Finishing tutorial...");
            } else if (tutorialNum.get(p.getUniqueId()) == 172) {
                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Tutorial finished!");
                p.sendMessage("");
                tutorialNum.remove(p.getUniqueId());
                usable.remove(p.getUniqueId());
                Bukkit.getScheduler().cancelTask(tutorialTask);
            }

        }, 50L, 20L);

        taskID.put(p.getUniqueId(), tutorialTask);

    }

    public static void endTask(Player p) {
        if (!taskID.containsKey(p.getUniqueId())) {
            return;
        }
        final int tid = taskID.get(p.getUniqueId());
        Bukkit.getScheduler().cancelTask(tid);
        taskID.remove(p.getUniqueId());
    }

    public static void handleTutorialStart(Player player) {
        if (tutorialPlayers.contains(player.getUniqueId()) && canAccept.containsKey(player.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cYou can still §7confirm §eor §7reject§e!");
            return;
        }

        player.sendMessage(StringManager.PREFIX + "§eAre you sure you want to start the Troll-Tutorial?");
        player.sendMessage(StringManager.PREFIX + "§eTo §7confirm §etype §7/trolltutorial confirm §ein the next §730 seconds§e!");
        player.sendMessage(StringManager.PREFIX + "§eTo §7reject §etype §7/trolltutorial reject §eor ignore this.");

        tutorialPlayers.add(player.getUniqueId());
        canAccept.put(player.getUniqueId(), true);
        secondsToAccept.put(player.getUniqueId(), 30);

        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(TrollBoss.getInstance(), () -> {
            int remainingTime = secondsToAccept.get(player.getUniqueId()) - 1;
            secondsToAccept.put(player.getUniqueId(), remainingTime);

            if (remainingTime <= 0) {
                tutorialPlayers.remove(player.getUniqueId());
                player.sendMessage(StringManager.PREFIX + "§cAborted tutorial!");
                canAccept.remove(player.getUniqueId());
                secondsToAccept.remove(player.getUniqueId());
                endTask(player);
            }
        }, 10L, 20L);

        taskID.put(player.getUniqueId(), taskId);
    }

    public static void handleConfirm(Player player) {
        if (!tutorialPlayers.contains(player.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cNothing to confirm!");
            player.sendMessage(StringManager.PREFIX + "§cTo start the tutorial type §7/trolltutorial");
            return;
        }

        if (!canAccept.containsKey(player.getUniqueId()) || !canAccept.get(player.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cYou already started the tutorial!");
            return;
        }

        secondsToAccept.remove(player.getUniqueId());
        endTask(player);
        player.sendMessage(StringManager.PREFIX + "§aStarting Troll-Tutorial...");
        startTrollTutorial(player);
        canAccept.remove(player.getUniqueId());
    }

    public static void handleReject(Player player) {
        if (!tutorialPlayers.contains(player.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cNothing to reject!");
            player.sendMessage(StringManager.PREFIX + "§cTo start the tutorial type §7/trolltutorial");
            return;
        }

        if (!canAccept.containsKey(player.getUniqueId()) || !canAccept.get(player.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cTutorial already running!");
            player.sendMessage(StringManager.PREFIX + "§cTo abort the tutorial type §7/trolltutorial stop");
            return;
        }

        tutorialPlayers.remove(player.getUniqueId());
        canAccept.remove(player.getUniqueId());
        secondsToAccept.remove(player.getUniqueId());
        endTask(player);
        player.sendMessage(StringManager.PREFIX + "§cRejected tutorial start!");
    }

    public static void handleStop(Player player) {
        if (!tutorialPlayers.contains(player.getUniqueId()) || !tutorialNum.containsKey(player.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cThere's no tutorial to stop!");
            return;
        }

        tutorialPlayers.remove(player.getUniqueId());
        tutorialNum.remove(player.getUniqueId());
        checkIfIsInUsable(player);
        endTask(player);

        player.sendMessage("");
        player.sendMessage(StringManager.PREFIX + "§cTutorial stopped!");
        player.sendMessage("");
    }

    public static void checkIfIsInUsable(Player p) {
        usable.remove(p.getUniqueId());
    }

    public static boolean isUsable(Player p) {
        return usable.contains(p.getUniqueId());
    }

}
