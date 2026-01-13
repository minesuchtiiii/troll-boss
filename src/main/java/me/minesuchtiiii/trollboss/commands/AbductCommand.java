package me.minesuchtiiii.trollboss.commands;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.utils.StringManager;
import me.minesuchtiiii.trollboss.utils.Ufo;
import org.bukkit.*;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class AbductCommand implements CommandExecutor {

    private static final int ABDUCTION_DURATION = 85;
    private final TrollBoss plugin;
    private int task;

    public AbductCommand(TrollBoss plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String cmdLabel, String[] args) {
        if (!(sender instanceof Player player)) {
            sender.sendMessage(StringManager.NOPLAYER);
            return true;
        }

        if (!player.hasPermission("troll.abduct")) {
            player.sendMessage(StringManager.NOPERM);
            return true;
        }

        if (args.length == 0) {
            player.sendMessage(StringManager.PREFIX + "§eUse §7/abduct [player]");
            return true;
        }

        if (args.length > 1) {
            player.sendMessage(StringManager.MUCHARGS);
            return true;
        }

        Player target = Bukkit.getPlayer(args[0]);
        if (target == null) {
            plugin.notOnline(player, args[0]);
            return true;
        }

        if (!plugin.canBeTrolled(target)) {
            player.sendMessage(StringManager.BYPASS);
            return true;
        }

        if (target.isDead()) {
            player.sendMessage(StringManager.FAILDEAD);
            return true;
        }

        if (plugin.playersBeingAbducted.contains(target.getUniqueId())) {
            player.sendMessage(StringManager.PREFIX + "§cCan't do this right now!");
            return true;
        }

        performAbduction(player, target);
        return true;
    }

    private void performAbduction(Player player, Player target) {
        plugin.nomine.add(target.getUniqueId());
        plugin.denyMove.add(target.getUniqueId());

        final Location location = target.getLocation();
        Ufo ufo = new Ufo(target, plugin);
        plugin.playersBeingAbducted.add(target.getUniqueId());
        plugin.abductedCachedLocations.put(target.getUniqueId(), location);

        plugin.addTroll();
        plugin.addStats("Abduct", player);

        player.sendMessage(StringManager.PREFIX + "§eAbducting §7" + target.getName() + "§e!");
        target.sendMessage("§eYou're being abducted by aliens!");

        createAbductionTask(target, ufo);
    }

    private void createAbductionTask(Player target, Ufo ufo) {
        int[] startY = {(int) target.getLocation().getY()};
        int goalY = startY[0] + 67;
        PotionEffect levitation = new PotionEffect(PotionEffectType.LEVITATION, 60 * 20, 10);
        target.addPotionEffect(levitation);
        task = Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, () -> {
            startY[0] = (int) target.getLocation().getY() + 4;
            TrollBoss.spawnAbductParticlesWide(target, startY[0], goalY + 8);
            target.spawnParticle(Objects.requireNonNull(Registry.PARTICLE_TYPE.get(NamespacedKey.minecraft("elder_guardian"))), target.getLocation(), 1, null);
            target.playSound(target.getLocation(), Sound.ENTITY_ELDER_GUARDIAN_CURSE, (float) 0.67, 1);

            if (target.getLocation().getY() >= goalY) {
                Bukkit.getScheduler().cancelTask(task);
                target.clearActivePotionEffects();
                target.teleport(target.getLocation().add(0, 7.5, 0));
                plugin.denyMove.remove(target.getUniqueId());
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    target.setHealth(20.0);
                    ufo.teleportBackFromUfo(target);
                }, 150L);

            }
        }, 1L, 20L);
    }
}
