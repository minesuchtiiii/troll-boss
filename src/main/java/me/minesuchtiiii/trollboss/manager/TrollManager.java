package me.minesuchtiiii.trollboss.manager;

import me.minesuchtiiii.trollboss.trolls.TrollType;

import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class TrollManager {
    private static final EnumMap<TrollType, Set<UUID>> activeTrolls;

    private TrollManager() {}

    static {
        activeTrolls = new EnumMap<>(TrollType.class);

        for (TrollType type : TrollType.values()) {
            activeTrolls.put(type, ConcurrentHashMap.newKeySet());
        }
    }

    public static boolean isActive(UUID uuid, TrollType type) {
        return activeTrolls.get(type).contains(uuid);
    }

    public static boolean activate(UUID uuid, TrollType type) {
        return activeTrolls.get(type).add(uuid);
    }

    public static boolean deactivate(UUID uuid, TrollType type) {
        return activeTrolls.get(type).remove(uuid);
    }

    public static void clear(UUID uuid) {
        for (Set<UUID> set : activeTrolls.values()) {
            set.remove(uuid);
        }
    }

    public static void clear(TrollType trollType) {
        activeTrolls.get(trollType).clear();
    }

}
