package me.minesuchtiiii.trollboss.manager;

import me.minesuchtiiii.trollboss.trolls.TrollFlag;
import me.minesuchtiiii.trollboss.trolls.TrollType;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TrollManager {

    private static final EnumMap<TrollType, Set<UUID>> activeTrolls;
    private static final Map<UUID, Map<TrollType, Set<TrollFlag>>> suppressedFlags;

    static {
        activeTrolls = new EnumMap<>(TrollType.class);
        suppressedFlags = new ConcurrentHashMap<>();

        for (TrollType type : TrollType.values()) {
            activeTrolls.put(type, ConcurrentHashMap.newKeySet());
        }
    }

    private TrollManager() {
    }

    /**
     * Determines if a specific {@link TrollFlag} is active for the given player, identified by their {@link UUID}.
     * The method checks if the {@link TrollFlag} is associated with any active {@link TrollType} for the player.
     * Additionally, it verifies if the flag is not suppressed for the player and troll type combination.
     * If any active troll type has the flag, and it is not suppressed, the method will return {@code true}.
     * Otherwise, it will return {@code false}.
     *
     * @param uuid the {@code UUID} of the player whose active status for the flag is being checked
     * @param flag the {@code TrollFlag} to be checked for activity
     * @return {@code true} if the specified flag is active for the player and not suppressed;
     *         {@code false} otherwise
     */
    public static boolean hasFlag(UUID uuid, TrollFlag flag) {

        for (TrollType type : TrollType.values()) {

            // First, check if the troll type actually has the flag.
            // This is faster than searching for the UUID in the map first.
            if (type.hasFlag(flag)) {

                // If the troll has the flag, we check if the player has this troll active
                if (isActive(uuid, type)) {

                    if (isFlagSuppressed(uuid, type, flag)) {
                        continue; // If yes, we ignore this troll and keep searching
                    }

                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Adds a specified {@link TrollFlag} to the suppressed flags for a given
     * player identified by their {@link UUID} and associated {@link TrollType}.
     * If the player or troll type has no suppressed flags initialized,
     * the required structures will be created.
     *
     * @param uuid the {@code UUID} of the player for whom the flag will be suppressed
     * @param type the {@code TrollType} associated with the player
     * @param flag the {@code TrollFlag} to be suppressed
     */
    public static void suppressFlag(UUID uuid, TrollType type, TrollFlag flag) {
        suppressedFlags.computeIfAbsent(uuid, k -> new ConcurrentHashMap<>())
                .computeIfAbsent(type, k -> Collections.synchronizedSet(EnumSet.noneOf(TrollFlag.class)))
                .add(flag);
    }

    /**
     * Checks if the specified {@link TrollFlag} is suppressed for a player, identified by their
     * {@link UUID} and associated {@link TrollType}.
     *
     * @param uuid the {@code UUID} of the player whose suppressed flags are being checked
     * @param type the {@code TrollType} associated with the player
     * @param flag the {@code TrollFlag} to check for suppression
     * @return {@code true} if the specified flag is suppressed for the player and troll type,
     *         otherwise {@code false}
     */
    private static boolean isFlagSuppressed(UUID uuid, TrollType type, TrollFlag flag) {
        if (!suppressedFlags.containsKey(uuid)) return false;
        Map<TrollType, Set<TrollFlag>> playerFlags = suppressedFlags.get(uuid);

        if (playerFlags == null || !playerFlags.containsKey(type)) return false;

        return playerFlags.get(type).contains(flag);
    }

    public static boolean isActive(UUID uuid, TrollType type) {
        return activeTrolls.get(type).contains(uuid);
    }

    public static boolean activate(UUID uuid, TrollType type) {
        return activeTrolls.get(type).add(uuid);
    }

    public static boolean deactivate(UUID uuid, TrollType type) {
        removeSuppressedFlags(uuid, type);
        return activeTrolls.get(type).remove(uuid);
    }

    public static void clear(UUID uuid) {
        for (Set<UUID> set : activeTrolls.values()) {
            set.remove(uuid);
        }
        suppressedFlags.remove(uuid);
    }

    public static void clear(TrollType trollType) {
        activeTrolls.get(trollType).clear();
        suppressedFlags.values().forEach(map -> map.remove(trollType));
    }

    /**
     * Removes all suppressed flags associated with the specified {@code TrollType}
     * for a given player identified by their {@code UUID}.
     *
     * @param uuid the UUID of the player whose suppressed flags are to be removed
     * @param type the TrollType whose associated suppressed flags are to be removed
     */
    private static void removeSuppressedFlags(UUID uuid, TrollType type) {
        if (suppressedFlags.containsKey(uuid)) {
            Map<TrollType, Set<TrollFlag>> map = suppressedFlags.get(uuid);
            if (map != null) {
                map.remove(type);
                if (map.isEmpty()) {
                    suppressedFlags.remove(uuid);
                }
            }
        }
    }

}
