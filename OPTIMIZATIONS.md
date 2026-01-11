# TrollBoss Optimization Analysis

This document outlines key areas for optimization and refactoring in the TrollBoss plugin, focusing on architecture, performance, memory management, and modern development practices for Spigot/Paper.

## 1. Architectural Improvements

### God Class Refactoring
**Issue:** The `Main` class acts as a "God Class," managing over 3100 lines of code, dozens of public state fields, and varied logic from statistics to GUI creation.
**Suggestion:** 
- Implement a **Manager Pattern**. Create separate manager classes (e.g., `TrollManager`, `DataManager`, `GuiManager`, `TutorialManager`).
- Use **Dependency Injection** to pass only necessary managers to commands and listeners, rather than the entire `Main` instance.
- Define a `TrollPlayer` data object to store all state related to a specific player in one place, rather than across 50+ different maps.

### Command & GUI Logic
**Issue:** The `GuiListener` uses `player.performCommand(command)` to execute trolls. This is inefficient, fragile, and bypasses direct logic calls.
**Suggestion:**
- Create an internal API for executing trolls that can be called directly by both commands and GUIs.
- Use a modern command framework (like ACF or Cloud) or at least a cleaner command pattern to reduce boilerplate.
- Identification of GUIs should rely on `PersistentDataContainer` or custom `InventoryHolder` rather than matching color-coded titles.

---

## 2. Asynchronous Execution & Threading

### Synchronous File I/O
**Issue:** `saveStats()` and `saveConfig()` are called frequently on the main thread (e.g., every time a troll is used). This can cause significant server lag spikes.
**Suggestion:**
- Perform all file I/O asynchronously. Use `Bukkit.getScheduler().runTaskAsynchronously` for saving data.
- Batch updates to statistics rather than saving on every single change.

### Heavy Main-Thread Operations
**Issue:** Building the UFO (setting hundreds of blocks) and complex particle loops are performed entirely on the main thread.
**Suggestion:**
- Use the **Paper Block Lib** or iterate through block changes over multiple ticks to prevent lag spikes.
- Offload complex particle calculations (like the ones in `spawnAbductParticlesWide`) to an asynchronous task, then spawn the particles back on the main thread (or use the async-safe particle API if available).

### Task Management
**Issue:** Task IDs are often stored in single fields (e.g., `private int task` in `AbductCommand`), causing race conditions if multiple players trigger the same action.
**Suggestion:**
- Use `BukkitRunnable` which provides built-in `cancel()` methods.
- Store active tasks in a `Map<UUID, BukkitTask>` to ensure each player's task is managed independently.
- Use `ThreadLocalRandom.current()` instead of creating new `Random` instances repeatedly in tasks or loops.

---

## 3. Memory Usage & Object Lifecycle

### Massive Memory Leaks
**Issue:** The `QuitListener` does not clear player data from the numerous maps and lists in `Main`. Data for every player ever trolled remains in memory until the server restarts.
**Suggestion:**
- Implement a comprehensive cleanup in `PlayerQuitEvent`.
- **Better yet:** Store player data in a single `TrollPlayer` object and remove that object from a central map on quit.

### Redundant Object Creation
**Issue:** GUIs create dozens of new `ItemStack` objects every time they are opened.
**Suggestion:**
- Cache `ItemStack` constants for GUI items.
- Reuse `Inventory` instances where appropriate or use a GUI library that handles caching.

### Public Field Access
**Issue:** Almost all state in `Main` is stored in `public` fields, leading to "spaghetti code" where any class can modify any state.
**Suggestion:**
- Encapsulate state with private fields and provide controlled access through methods.

---

## 4. General Code Quality

### Hardcoded Messages & Boilerplate
**Issue:** Large methods like `sendHelp` and `openGui` contain hundreds of lines of hardcoded strings and repeated logic.
**Suggestion:**
- Externalize all messages to a `messages.yml` file.
- Use a configuration-driven approach for help pages and GUI items. This makes the plugin easier to translate and customize without recompiling.

### Modern API Usage
**Issue:** The plugin uses legacy `§` color codes and outdated scheduling methods despite targeting Paper 1.21.
**Suggestion:**
- Switch to the **Adventure API** (`Component`) for all text and messages.
- Replace legacy `scheduleSyncRepeatingTask` with `BukkitRunnable` or the newer `ScheduledTask` API in Paper.
- Use `ChatColor` or MiniMessage for color formatting.

### Listener Consolidation
**Issue:** There are dozens of tiny listener classes (e.g., `DeathListenerApple`, `DeathListenerHurt`).
**Suggestion:**
- Merge related listeners (e.g., all `PlayerDeathEvent` listeners) into a single class using a clean `switch` or strategy pattern to handle different troll types.

---

## 5. Concrete Examples

### Instead of:
```java
// In Main.java
public ArrayList<UUID> frozen = new ArrayList<>();
// In QuitListener.java
public void onQuit(PlayerQuitEvent e) {
    // missing cleanup
}
```

### Try:
```java
public class TrollPlayer {
    private final UUID uuid;
    private boolean frozen;
    // ... other states
    
    public void cleanup() {
        // cancel tasks, etc.
    }
}

// In DataManager.java
private final Map<UUID, TrollPlayer> playerData = new HashMap<>();

public void removePlayer(UUID uuid) {
    TrollPlayer data = playerData.remove(uuid);
    if (data != null) data.cleanup();
}
```

### Instead of synchronous save:
```java
public void addStats(String cmd) {
    statscfg.set("Troll." + cmd, getStats(cmd) + 1);
    saveStats(); // SLOW
}
```

### Try asynchronous save:
```java
public void addStats(String cmd) {
    CompletableFuture.runAsync(() -> {
        synchronized(statscfg) {
            statscfg.set("Troll." + cmd, getStats(cmd) + 1);
            statscfg.save(statsFile);
        }
    });
}
```
