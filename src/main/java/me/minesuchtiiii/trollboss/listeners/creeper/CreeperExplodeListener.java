package me.minesuchtiiii.trollboss.listeners.creeper;

import me.minesuchtiiii.trollboss.TrollBoss;
import org.bukkit.entity.Creeper;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;

public class CreeperExplodeListener implements Listener {

    private final TrollBoss plugin;

    public CreeperExplodeListener(TrollBoss plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onCreeperExplode(EntityExplodeEvent e) {

        if (!(e.getEntity() instanceof final Creeper c)) {
            return;
        }
        if (c.getCustomName() != null) {
            if (c.getCustomName().contains("Angry Creeper") || c.getCustomName().contains("Wütender Creeper")) {

                if (this.plugin.creepers > 0) {

                    e.blockList().clear();
                    this.plugin.creepers--;

                } else if (this.plugin.creepers == 0) {

                    this.plugin.creep = false;

                }

            }

        }

    }

    @EventHandler
    public void onCreeperExplodeBow(EntityExplodeEvent e) {

        if (!(e.getEntity() instanceof final Creeper c)) {
            return;
        }
        if (c.getCustomName() != null) {
            if ("§aAngry Creeper".equals(c.getCustomName()) || "§bAngry Creeper".equals(c.getCustomName())
                    || "§cAngry Creeper".equals(c.getCustomName()) || "§dAngry Creeper".equals(c.getCustomName())
                    || "§eAngry Creeper".equals(c.getCustomName()) || "§fAngry Creeper".equals(c.getCustomName())
                    || "§1Angry Creeper".equals(c.getCustomName()) || "§2Angry Creeper".equals(c.getCustomName())
                    || "§3Angry Creeper".equals(c.getCustomName()) || "§4Angry Creeper".equals(c.getCustomName())
                    || "§5Angry Creeper".equals(c.getCustomName()) || "§6Angry Creeper".equals(c.getCustomName())
                    || "§7Angry Creeper".equals(c.getCustomName()) || "§8Angry Creeper".equals(c.getCustomName())
                    || "§9Angry Creeper".equals(c.getCustomName()) || "§oAngry Creeper".equals(c.getCustomName())
                    || "§kAngry Creeper".equals(c.getCustomName()) || "§lAngry Creeper".equals(c.getCustomName())
                    || "§mAngry Creeper".equals(c.getCustomName()) || "§nAngry Creeper".equals(c.getCustomName()) ||

                    "§aW§tender Creeper".equals(c.getCustomName()) || "§bWütender Creeper".equals(c.getCustomName())
                    || "§cWütender Creeper".equals(c.getCustomName()) || "§dWütender Creeper".equals(c.getCustomName())
                    || "§eWütender Creeper".equals(c.getCustomName()) || "§fWütender Creeper".equals(c.getCustomName())
                    || "§1Wütender Creeper".equals(c.getCustomName()) || "§2Wütender Creeper".equals(c.getCustomName())
                    || "§3Wütender Creeper".equals(c.getCustomName()) || "§4Wütender Creeper".equals(c.getCustomName())
                    || "§5Wütender Creeper".equals(c.getCustomName()) || "§6Wütender Creeper".equals(c.getCustomName())
                    || "§7Wütender Creeper".equals(c.getCustomName()) || "§8Wütender Creeper".equals(c.getCustomName())
                    || "§9Wütender Creeper".equals(c.getCustomName()) || "§oWütender Creeper".equals(c.getCustomName())
                    || "§kWütender Creeper".equals(c.getCustomName()) || "§lWütender Creeper".equals(c.getCustomName())
                    || "§mWütender Creeper".equals(c.getCustomName())
                    || "§nWütender Creeper".equals(c.getCustomName())) {

                if (this.plugin.bowCreepers > 0) {

                    e.blockList().clear();
                    this.plugin.bowCreepers--;

                }

            }

        }

    }

}
