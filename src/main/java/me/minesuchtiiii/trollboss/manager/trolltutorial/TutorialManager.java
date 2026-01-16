package me.minesuchtiiii.trollboss.manager.trolltutorial;

import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class TutorialManager {

    private static final Map<UUID, TutorialSession> sessions = new HashMap<>();

    private TutorialManager() {
    }

    public static void handleTutorialStart(Player player) {
        if (sessions.containsKey(player.getUniqueId())) {
            TutorialSession session = sessions.get(player.getUniqueId());
            if (session.isConfirming()) {
                player.sendMessage(StringManager.PREFIX + "§cPlease confirm or reject first!");
            } else {
                player.sendMessage(StringManager.PREFIX + "§cTutorial already running!");
            }
            return;
        }

        TutorialSession session = new TutorialSession(player);
        sessions.put(player.getUniqueId(), session);
        session.startConfirmationPhase();
    }

    public static void handleReject(Player player) {
        TutorialSession session = sessions.get(player.getUniqueId());

        if (session == null || !session.isConfirming()) {
            player.sendMessage(StringManager.PREFIX + "§cNothing to reject! To start type §7/trolltutorial");
            return;
        }

        session.stop();
        sessions.remove(player.getUniqueId());
        player.sendMessage(StringManager.PREFIX + "§cRejected tutorial start!");
    }

    public static void handleStop(Player player) {
        TutorialSession session = sessions.get(player.getUniqueId());

        if (session == null) {
            player.sendMessage(StringManager.PREFIX + "§cThere's no tutorial to stop!");
            return;
        }

        if (session.isConfirming()) {
            player.sendMessage(StringManager.PREFIX + "§cYou haven't started yet. Use §7/trolltutorial reject §cto cancel.");
            return;
        }

        session.stop();
        sessions.remove(player.getUniqueId());
        player.sendMessage("");
        player.sendMessage(StringManager.PREFIX + "§cTutorial stopped!");
        player.sendMessage("");
    }

    public static void handleConfirm(Player player) {
        TutorialSession session = sessions.get(player.getUniqueId());

        if (session == null || !session.isConfirming()) {
            player.sendMessage(StringManager.PREFIX + "§cNothing to confirm.");
            return;
        }

        session.confirmAndStart();
    }

    public static void stopTutorial(Player player) {
        if (sessions.containsKey(player.getUniqueId())) {
            sessions.get(player.getUniqueId()).stop();
            sessions.remove(player.getUniqueId());
        }
    }

    public static boolean isUsable(Player p) {
        if (sessions.containsKey(p.getUniqueId())) {
            return sessions.get(p.getUniqueId()).isInteractionAllowed();
        }
        return false;
    }

}