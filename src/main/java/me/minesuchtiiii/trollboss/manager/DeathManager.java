package me.minesuchtiiii.trollboss.manager;

import me.minesuchtiiii.trollboss.trolls.TrollType;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class DeathManager {

    private static final EnumMap<TrollType, Set<UUID>> deathTracker;

    static {
        deathTracker = new EnumMap<>(TrollType.class);

        for (TrollType type : TrollType.values()) {
            deathTracker.put(type, new HashSet<>());
        }
    }

    public static boolean hasDied(UUID uuid, TrollType type) {
        return deathTracker.get(type).contains(uuid);
    }

    public static boolean setDead(UUID uuid, TrollType type) {
        return deathTracker.get(type).add(uuid);
    }

    public static boolean removeDead(UUID uuid, TrollType type) {
        return deathTracker.get(type).remove(uuid);
    }

    public static void clear(UUID uuid) {
        for (Set<UUID> set : deathTracker.values()) {
            set.remove(uuid);
        }
    }

    public static void clear(TrollType type) {
        deathTracker.get(type).clear();
    }
}
