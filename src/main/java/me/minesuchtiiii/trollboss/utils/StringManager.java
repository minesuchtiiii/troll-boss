package me.minesuchtiiii.trollboss.utils;

/**
 * The {@code StringManager} class serves as a centralized container for constant string values
 * used throughout the application. These constants mostly represent pre-formatted messages for
 * consistent in-game communication and error handling.
 *
 * The primary purpose of this class is to streamline the management of string literals by
 * avoiding redundancy and providing a single source of truth for commonly used messages.
 *
 * This class cannot be instantiated as it only contains static final fields.
 *
 * Constants provided by this class include:
 * - Prefix for messages
 * - Error messages for permission issues, invalid usage, or restricted actions
 * - Informational messages intended for in-game warnings or instructions
 */
public class StringManager {

    public static final String PREFIX = "§7[§cTrollBoss§7] ";
    public static final String NOPERM = "§cYou don't have permissions!";
    public static final String MUCHARGS = PREFIX + "§cToo many arguments!";
    public static final String NOPLAYER = PREFIX + "§cYou have to be a player!";
    public static final String BYPASS = PREFIX + "§cCan't troll that player!";
    public static final String FAILDEAD = PREFIX + "§cCan't perform this command because the target is dead.";
    public static final String ENGLISH_WARNING_MSG = "§7[§4INFO§7] §cYou have §6%d seconds §cto start moving!";
    public static final String ENGLISH_WARNING_FINAL = "§7[§4INFO§7] §c§lMOVE!";
    public static final String ENGLISH_INITIAL_MSG = "§7[§4INFO§7] §cYou have §610 seconds §cto start moving, you are not allowed to stop moving for §6%s seconds§c!";
    public static final String ENGLISH_DEATH_MSG = "§7[§4INFO§7] §cIf you stop moving in that time you will die and lose your whole inventory!";

}
