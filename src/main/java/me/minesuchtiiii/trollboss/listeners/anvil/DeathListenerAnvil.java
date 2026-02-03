package me.minesuchtiiii.trollboss.listeners.anvil;

import me.minesuchtiiii.trollboss.items.keys.AnvilKey;
import org.bukkit.damage.DamageSource;
import org.bukkit.damage.DamageType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

// Stable from 1.21.7+
@SuppressWarnings("UnstableApiUsage")
public class DeathListenerAnvil implements Listener {

	@EventHandler
	public void onDeathByAnvil(PlayerDeathEvent e) {
        DamageSource damageSource = e.getDamageSource();
        if (damageSource.getDamageType() != DamageType.FALLING_ANVIL) return;
        if (!AnvilKey.isAnvilTroll(damageSource.getCausingEntity())) return;

        e.deathMessage(null);
	}

}
