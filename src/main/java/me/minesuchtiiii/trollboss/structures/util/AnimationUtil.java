package me.minesuchtiiii.trollboss.structures.util;

import me.minesuchtiiii.trollboss.TrollBoss;
import org.bukkit.Bukkit;

public final class AnimationUtil {

    private AnimationUtil() {}

    public static void later(Runnable task, long delay) {
        Bukkit.getScheduler().runTaskLater(TrollBoss.getInstance(), task, delay);
    }
}
