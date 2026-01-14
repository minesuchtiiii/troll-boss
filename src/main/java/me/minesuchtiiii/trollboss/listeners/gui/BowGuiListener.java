package me.minesuchtiiii.trollboss.listeners.gui;

import me.minesuchtiiii.trollboss.TrollBoss;
import me.minesuchtiiii.trollboss.manager.GuiManager;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class BowGuiListener implements Listener {

    private final TrollBoss plugin;

    public BowGuiListener(TrollBoss plugin) {

        this.plugin = plugin;
    }

    @EventHandler
    public void onInteractInBowGui(InventoryClickEvent e) {

        if (e.getWhoClicked() instanceof Player p) {
            if ("§cChoose a Troll-Bow".equals(e.getView().getTitle())) {

                e.setCancelled(true);

                if (e.getCurrentItem() != null) {
                    if (e.getCurrentItem().getType() == Material.BOW) {
                        if (e.getCurrentItem().hasItemMeta() && "§eBolt Bow".equals(e.getCurrentItem().getItemMeta().getDisplayName())) {

                            final ItemStack b1 = new ItemStack(Material.BOW);
                            final ItemMeta b1meta = b1.getItemMeta();
                            b1meta.setDisplayName("§eBolt Bow");
                            b1meta.addEnchant(Enchantment.INFINITY, 1, true);
                            b1meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            final ArrayList<String> b1metalore = new ArrayList<>();
                            b1metalore.add("§7Strikes a lightning at the arrows location.");
                            b1meta.setLore(b1metalore);
                            b1.setItemMeta(b1meta);

                            p.getInventory().addItem(b1);
                            this.plugin.closeGui(p);
                            p.sendMessage(StringManager.PREFIX + "§eHave fun with the §7Bolt Bow§e!");
                            this.plugin.addTroll();
                            this.plugin.addBowStats("Bolt");

                        } else if (e.getCurrentItem().hasItemMeta() && "§eBoom Bow".equals(e.getCurrentItem().getItemMeta().getDisplayName())) {

                            final ItemStack b2 = new ItemStack(Material.BOW);
                            final ItemMeta b2meta = b2.getItemMeta();
                            b2meta.setDisplayName("§eBoom Bow");
                            b2meta.addEnchant(Enchantment.INFINITY, 1, true);
                            b2meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            final ArrayList<String> b2metalore = new ArrayList<>();
                            b2metalore.add("§7Creates an explosion at the arrows location.");
                            b2meta.setLore(b2metalore);
                            b2.setItemMeta(b2meta);

                            p.getInventory().addItem(b2);
                            this.plugin.closeGui(p);
                            p.sendMessage(StringManager.PREFIX + "§eHave fun with the §7Boom Bow§e!");
                            this.plugin.addTroll();
                            this.plugin.addBowStats("Boom");

                        } else if (e.getCurrentItem().hasItemMeta() && "§eCreeper Bow".equals(e.getCurrentItem().getItemMeta().getDisplayName())) {

                            final ItemStack b3 = new ItemStack(Material.BOW);
                            final ItemMeta b3meta = b3.getItemMeta();
                            b3meta.setDisplayName("§eCreeper Bow");
                            b3meta.addEnchant(Enchantment.INFINITY, 1, true);
                            b3meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            final ArrayList<String> b3metalore = new ArrayList<>();
                            b3metalore.add("§7Spawns a creeper at the arrows location.");
                            b3meta.setLore(b3metalore);
                            b3.setItemMeta(b3meta);

                            p.getInventory().addItem(b3);
                            this.plugin.closeGui(p);
                            p.sendMessage(StringManager.PREFIX + "§eHave fun with the §7Creeper Bow§e!");
                            this.plugin.addTroll();
                            this.plugin.addBowStats("Creeper");

                        } else if (e.getCurrentItem().hasItemMeta() && "§ePull Bow".equals(e.getCurrentItem().getItemMeta().getDisplayName())) {

                            final ItemStack b4 = new ItemStack(Material.BOW);
                            final ItemMeta b4meta = b4.getItemMeta();
                            b4meta.setDisplayName("§ePull Bow");
                            b4meta.addEnchant(Enchantment.INFINITY, 1, true);
                            b4meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            final ArrayList<String> b4metalore = new ArrayList<>();
                            b4metalore.add("§7Pulls the hit entity into your direction.");
                            b4meta.setLore(b4metalore);
                            b4.setItemMeta(b4meta);

                            p.getInventory().addItem(b4);
                            this.plugin.closeGui(p);
                            p.sendMessage(StringManager.PREFIX + "§eHave fun with the §7Pull Bow§e!");
                            this.plugin.addTroll();
                            this.plugin.addBowStats("Pull");

                        } else if (e.getCurrentItem().hasItemMeta() && "§eGet all bows at once".equals(e.getCurrentItem().getItemMeta().getDisplayName())) {

                            final ItemStack b1 = new ItemStack(Material.BOW);
                            final ItemMeta b1meta = b1.getItemMeta();
                            b1meta.setDisplayName("§eBolt Bow");
                            b1meta.addEnchant(Enchantment.INFINITY, 1, true);
                            b1meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            final ArrayList<String> b1metalore = new ArrayList<>();
                            b1metalore.add("§7Strikes a lightning at the arrows location.");
                            b1meta.setLore(b1metalore);
                            b1.setItemMeta(b1meta);

                            final ItemStack b2 = new ItemStack(Material.BOW);
                            final ItemMeta b2meta = b2.getItemMeta();
                            b2meta.setDisplayName("§eBoom Bow");
                            b2meta.addEnchant(Enchantment.INFINITY, 1, true);
                            b2meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            final ArrayList<String> b2metalore = new ArrayList<>();
                            b2metalore.add("§7Creates an explosion at the arrows location.");
                            b2meta.setLore(b2metalore);
                            b2.setItemMeta(b2meta);

                            final ItemStack b3 = new ItemStack(Material.BOW);
                            final ItemMeta b3meta = b3.getItemMeta();
                            b3meta.setDisplayName("§eCreeper Bow");
                            b3meta.addEnchant(Enchantment.INFINITY, 1, true);
                            b3meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            final ArrayList<String> b3metalore = new ArrayList<>();
                            b3metalore.add("§7Spawns a creeper at the arrows location.");
                            b3meta.setLore(b3metalore);
                            b3.setItemMeta(b3meta);

                            final ItemStack b4 = new ItemStack(Material.BOW);
                            final ItemMeta b4meta = b4.getItemMeta();
                            b4meta.setDisplayName("§ePull Bow");
                            b4meta.addEnchant(Enchantment.INFINITY, 1, true);
                            b4meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                            final ArrayList<String> b4metalore = new ArrayList<>();
                            b4metalore.add("§7Pulls the hit entity into your direction.");
                            b4meta.setLore(b4metalore);
                            b4.setItemMeta(b4meta);

                            p.getInventory().addItem(b1);
                            p.getInventory().addItem(b2);
                            p.getInventory().addItem(b3);
                            p.getInventory().addItem(b4);
                            this.plugin.closeGui(p);
                            p.sendMessage(StringManager.PREFIX + "§eHave fun with those bows!");
                            this.plugin.addTroll();
                            this.plugin.addTroll();
                            this.plugin.addTroll();
                            this.plugin.addTroll();
                            this.plugin.addBowStats("Bolt");
                            this.plugin.addBowStats("Boom");
                            this.plugin.addBowStats("Creeper");
                            this.plugin.addBowStats("Pull");

                        }

                    } else if (e.getCurrentItem().getType() == Material.IRON_DOOR) {

                        p.getOpenInventory().close();
                        GuiManager.openGui(p);

                    } else if (e.getCurrentItem().getType() == Material.GLASS_PANE) {

                        e.setCancelled(true);

                    }
                }

            }
        }
    }
}
