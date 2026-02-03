package me.minesuchtiiii.trollboss.trolls;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.manager.TrollManager;
import me.minesuchtiiii.trollboss.utils.Util;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class SpamManager {
    private static final List<String> spams = addSpamStuff();
    private static final HashMap<UUID, Integer> spamTask = new HashMap<>();

    public static void spamPlayer(Player target, int amount) {
        UUID uuid = target.getUniqueId();
        TrollManager.activate(uuid, TrollType.SPAM);
        final int[] number = {amount};

        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(TrollBoss.getInstance(), () -> {
            if (number[0] > 0) {
                target.sendMessage(Util.getRandomColor() + getRandomSpam());
                number[0]--;
            }
            if (number[0] == 0) {
                TrollManager.deactivate(uuid, TrollType.SPAM);
                Bukkit.getScheduler().cancelTask(spamTask.get(uuid));
                spamTask.remove(uuid);
            }
        }, 0L, 20L);

        spamTask.put(uuid, task);
    }

    private static List<String> addSpamStuff() {
        final ArrayList<String> spams = new ArrayList<>();

        spams.add("Hello, world!");
        spams.add("You're being trolled!");
        spams.add("Prepare for epic spam!");
        spams.add("Oops, did I do that?");
        spams.add("Time to rage!");
        spams.add("Keep calm and carry on.");
        spams.add("This is not a drill.");
        spams.add("Surprise, surprise!");
        spams.add("You just got pranked!");
        spams.add("I come in peace... or do I?");
        spams.add("Beware of the troll!");
        spams.add("Is it Friday yet?");
        spams.add("Chill out and enjoy.");
        spams.add("Don't feed the trolls!");
        spams.add("Enjoy your day!");
        spams.add("Buckle up, buttercup!");
        spams.add("No comment.");
        spams.add("Who let the trolls out?");
        spams.add("Haha, gotcha!");
        spams.add("This is your daily dose of randomness.");
        spams.add("Randomness is the spice of life.");
        spams.add("Smile, it's contagious!");
        spams.add("I speak fluent nonsense.");
        spams.add("Expect the unexpected.");
        spams.add("Tick tock, time's up!");
        spams.add("Unleash the spam!");
        spams.add("Trolls will be trolls.");
        spams.add("Why so serious?");
        spams.add("Carpe diem!");
        spams.add("All your base are belong to us.");
        spams.add("Nyan nyan nyan.");
        spams.add("This is getting out of hand.");
        spams.add("Can you handle the truth?");
        spams.add("The cake is a lie!");
        spams.add("Keep your friends close, enemies closer.");
        spams.add("I solemnly swear I'm up to no good.");
        spams.add("Wubba lubba dub dub!");
        spams.add("May the force be with you.");
        spams.add("You're awesome!");
        spams.add("Hello, dark knight.");
        spams.add("Don't stop believing.");
        spams.add("We are the champions!");
        spams.add("Another one bites the dust.");
        spams.add("I'm in me mum's car, vroom vroom!");
        spams.add("Fortune favors the bold.");
        spams.add("Stay weird!");
        spams.add("And now, a random fact: Honey never spoils.");
        spams.add("Just when you thought it was safe...");
        spams.add("This message is brought to you by random chance.");
        spams.add("Did you know? Bananas are berries!");

        return List.copyOf(spams);
    }

    private static String getRandomSpam() {
        return spams.get((int) (Math.random() * spams.size()));
    }

    public static List<String> getSpams() {
        return spams;
    }

}
