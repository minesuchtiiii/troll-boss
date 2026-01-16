package me.minesuchtiiii.trollboss.manager.trolltutorial;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.manager.GuiManager;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class TutorialSession {

    // The timeline stores what happens at which "tick" (second).
    // We use Consumer<TutorialSession> so that we have access to the player AND internal flags (like interactionAllowed).
    private static final Map<Integer, Consumer<TutorialSession>> TIMELINE = new HashMap<>();

    static {
        TIMELINE.put(1, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6In this tutorial you will learn everything you need to know about this plugin!");
        });

        TIMELINE.put(10, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6Let's start with the basic command of this plugin: §4/troll");
        });

        TIMELINE.put(15, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6When typing §4/troll §6an inventory opens, where you can see heads");
            s.player.sendMessage(StringManager.PREFIX + "§6of players (if there are any online)");
        });

        TIMELINE.put(23, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6The inventory looks like this:");
            s.setInteractionAllowed(true); // Replaces usable.add
        });

        TIMELINE.put(27, s -> GuiManager.openTrollInv(s.player));

        TIMELINE.put(31, s -> {
            s.player.sendMessage("");
            s.player.closeInventory();
            s.setInteractionAllowed(false); // Replaces usable.remove
            s.player.sendMessage(StringManager.PREFIX + "§6You can choose a player by clicking on his head");
        });

        TIMELINE.put(36, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6After you clicked on his head another inventory will be opened");
            s.player.sendMessage(StringManager.PREFIX + "§6The Troll-GUI!");
        });

        TIMELINE.put(43, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6The Troll-GUI looks like this:");
            s.setInteractionAllowed(true);
        });

        TIMELINE.put(47, s -> GuiManager.openGui(s.player));

        TIMELINE.put(51, s -> {
            s.player.sendMessage("");
            s.player.closeInventory();
            s.setInteractionAllowed(false);
            s.player.sendMessage(StringManager.PREFIX + "§6In the Troll-GUI you can see a lot of different items");
            s.player.sendMessage(StringManager.PREFIX + "§6Each item starts a different troll for the player you selected before");
        });

        TIMELINE.put(58, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6If you want to speed things up a bit you can use the command §4/troll [player]");
            s.player.sendMessage(StringManager.PREFIX + "§6With this command the Troll-GUI opens immediately, the selected player is the");
            s.player.sendMessage(StringManager.PREFIX + "§6argument after the command");
        });

        TIMELINE.put(69, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6If you want to speed up things even more you can just type the command");
            s.player.sendMessage(StringManager.PREFIX + "§6To get a list of all commands of this plugin type §4/troll help [1-6]");
            s.player.sendMessage(StringManager.PREFIX + "§6There are 6 help pages, so for example you can type §4/troll help 3");
        });

        TIMELINE.put(82, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6So how about bypassing trolls?");
        });

        TIMELINE.put(85, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6Users with the permission §4troll.bypass §6can bypass every troll");
            s.player.sendMessage(StringManager.PREFIX + "§6They are just not trollable!");
        });

        TIMELINE.put(90, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6But wait... operators have all permission, this means they also have the bypass permission");
            s.player.sendMessage(StringManager.PREFIX + "§6Does this mean that I cannot troll operators?");
        });

        TIMELINE.put(98, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6The answer is: §4You can troll operators!");
            s.player.sendMessage(StringManager.PREFIX + "§6With the command §4/trollop [true | false] §6you can decide");
            s.player.sendMessage(StringManager.PREFIX + "§6whether operators can be trolled or not, isn't that great? :)");
        });

        TIMELINE.put(110, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6You might ask yourself \"How server friendly is this plugin?\"");
            s.player.sendMessage(StringManager.PREFIX + "§6Do the trolls destroy the world and some stuff I built?");
        });

        TIMELINE.put(118, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6The answer is: §4No!");
            s.player.sendMessage(StringManager.PREFIX + "§6The plugin is very server friendly, it doesn't destroy any");
            s.player.sendMessage(StringManager.PREFIX + "§6blocks at all, and if in a command blocks get placed they will");
            s.player.sendMessage(StringManager.PREFIX + "§6be removed again and the old blocks at the locations will be reset!");
            s.player.sendMessage(StringManager.PREFIX + "§6So don't worry about this! :)");
        });

        TIMELINE.put(135, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6Alright, but is there a way to see how many times I used a certain troll?");
        });

        TIMELINE.put(139, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6The answer is: §4Yes!");
            s.player.sendMessage(StringManager.PREFIX + "§6With the command §4/troll statistics §6you get statistics about every");
            s.player.sendMessage(StringManager.PREFIX + "§6single troll command you used!");
        });

        TIMELINE.put(147, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6Is there anything else I should know?");
        });

        TIMELINE.put(150, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6Yes! There are some special commands, like");
            s.player.sendMessage(StringManager.PREFIX + "§4/special [1-2] §6or §4/trollbows");
        });

        TIMELINE.put(154, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6What this commands do? Well, just try them out! :)");
        });

        TIMELINE.put(158, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6That's it for now, this tutorial might get edited in the future");
        });

        TIMELINE.put(165, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§4Important! §6If you find any bugs please report them on the site");
            s.player.sendMessage(StringManager.PREFIX + "§6you downloaded this plugin, this helps to improve it!");
        });

        TIMELINE.put(170, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6Finishing tutorial...");
        });

        TIMELINE.put(172, s -> {
            s.player.sendMessage("");
            s.player.sendMessage(StringManager.PREFIX + "§6Tutorial finished!");
            s.player.sendMessage("");
            TutorialManager.stopTutorial(s.player);
        });
    }

    private final Player player;
    private int taskId = -1;
    private int currentTick = 0;
    private boolean isConfirming = false;
    private boolean interactionAllowed = false;

    public TutorialSession(Player player) {
        this.player = player;
    }

    public void startConfirmationPhase() {
        if (isConfirming || taskId != -1) return;

        this.isConfirming = true;

        player.sendMessage(StringManager.PREFIX + "§eAre you sure you want to start the Troll-Tutorial?");
        player.sendMessage(StringManager.PREFIX + "§eTo §7confirm §etype §7/trolltutorial confirm §ein the next §730 seconds§e!");
        player.sendMessage(StringManager.PREFIX + "§eTo §7reject §etype §7/trolltutorial reject §eor ignore this.");

        this.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(TrollBoss.getInstance(), new Runnable() {
            int secondsLeft = 30;

            @Override
            public void run() {
                secondsLeft--;
                if (secondsLeft <= 0) {
                    player.sendMessage(StringManager.PREFIX + "§cAborted tutorial!");
                    TutorialManager.stopTutorial(player);
                }
            }
        }, 0L, 20L);
    }

    public void confirmAndStart() {
        cancelTask();
        this.isConfirming = false;

        player.sendMessage(StringManager.PREFIX + "§aStarting Troll-Tutorial...");

        this.taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(TrollBoss.getInstance(), () -> {
            currentTick++;

            // Check if an entry exists in the timeline for the current second
            if (TIMELINE.containsKey(currentTick)) {
                TIMELINE.get(currentTick).accept(this);
            }

        }, 0L, 20L);
    }

    public void stop() {
        cancelTask();

        // Close inventory if the player was in "usable" mode
        if (interactionAllowed) {
            player.closeInventory();
        }

        // Reset flags
        this.interactionAllowed = false;
        this.isConfirming = false;
    }

    private void cancelTask() {
        if (taskId != -1) {
            Bukkit.getScheduler().cancelTask(taskId);
            taskId = -1;
        }
    }

    public void closeInventory() {
        player.closeInventory();
    }

    public boolean isConfirming() {
        return isConfirming;
    }

    public boolean isInteractionAllowed() {
        return interactionAllowed;
    }

    public void setInteractionAllowed(boolean allowed) {
        this.interactionAllowed = allowed;
    }

    public Player getPlayer() {
        return player;
    }
}