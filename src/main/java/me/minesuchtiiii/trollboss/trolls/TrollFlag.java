package me.minesuchtiiii.trollboss.trolls;

public enum TrollFlag {

    // Prevents movement in any direction (x, y, z)
    PREVENT_ALL_MOVEMENT,

    // Prevents movement on ground (x, y), player can still change y-level
    PREVENT_GROUND_MOVEMENT,

    // PRevents player from breaking blocks
    PREVENT_MINING,

    // Prevents player from chatting
    PREVENT_CHAT,

    // Prevents player from dying
    INVULNERABLE

}
