package me.minesuchtiiii.trollboss.manager;

import me.minesuchtiiii.trollboss.utils.GuiItem;
import me.minesuchtiiii.trollboss.utils.StringManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.List;
import java.util.stream.Collectors;

public class GuiManager {

    public static void openGui(Player p) {
        final Inventory gui = Bukkit.createInventory(null, 54, "§cTroll-Gui");

        final ItemStack close = GuiItem.createGuiItem(1, Material.EMERALD, "§bClose the gui", "§7Closes the Troll-Gui");
        final ItemStack badapple = GuiItem.createGuiItem(1, Material.APPLE, "§eBadapple", "§7Give the player an", "§7apple. If he eats it he will die.");
        final ItemStack bolt = GuiItem.createGuiItem(1, Material.FIRE_CHARGE, "§eBolt", "§7Strikes a lightning at", "§7the player's location.");
        final ItemStack boom = GuiItem.createGuiItem(1, Material.TNT, "§eBoom", "§7Creates an explosion at", "§7the player's location.");
        final ItemStack burn = GuiItem.createGuiItem(1, Material.LAVA_BUCKET, "§eBurn", "§7Sets the player on fire.");
        final ItemStack bury = GuiItem.createGuiItem(1, Material.DIRT, "§eBury", "§7Will bury the player underground.");
        final ItemStack crash = GuiItem.createGuiItem(1, Material.STRING, "§eCrash", "§7Kicks the player from", "§7the server with a fake crash message.");
        final ItemStack denymove = GuiItem.createGuiItem(1, Material.LEATHER_BOOTS, "§eDenymove", "§7Won't let the player move.");
        final ItemStack fakeop = GuiItem.createGuiItem(1, Material.DIAMOND, "§eFakeop", "§7Fake op's a player.");
        final ItemStack fakedeop = GuiItem.createGuiItem(1, Material.GOLD_INGOT, "§eFakedeop", "§7Fake deop's a player.");
        final ItemStack fakerestart = GuiItem.createGuiItem(1, Material.BLAZE_POWDER, "§eFakerestart", "§7Fake restarts the server.");
        final ItemStack freefall = GuiItem.createGuiItem(1, Material.WHITE_WOOL, "§eFreefall", "§7Lets a player freefall", "§7from a certain height.");
        final ItemStack freeze = GuiItem.createGuiItem(1, Material.ICE, "§eFreeze", "§7Freezes a player.");
        final ItemStack gokill = GuiItem.createGuiItem(1, Material.SOUL_SAND, "§eGokill", "§7Kills a player after a certain period.");
        final ItemStack hero = GuiItem.createGuiItem(1, Material.GLOWSTONE_DUST, "§eHerobrine", "§7Sets a player to Herobrine.");
        final ItemStack hurt = GuiItem.createGuiItem(1, Material.REDSTONE, "§eHurt", "§7Hurts a player.");
        final ItemStack infect = GuiItem.createGuiItem(1, Material.POTION, "§eInfect", "§7Infects a player.");
        final ItemStack launch = GuiItem.createGuiItem(1, Material.FIREWORK_ROCKET, "§eLaunch", "§7Launchs a player.");
        final ItemStack nomine = GuiItem.createGuiItem(1, Material.GRASS_BLOCK, "§eNomine", "§7Prevents a player from breaking blocks.");
        final ItemStack potato = GuiItem.createGuiItem(1, Material.BAKED_POTATO, "§ePotatotroll", "§7Replaces every item in a player's", "§7inventory with a potato.");
        final ItemStack pump = GuiItem.createGuiItem(1, Material.JACK_O_LANTERN, "§ePumpkinhead", "§7Sets the head of a player to a pumpkin.");
        final ItemStack push = GuiItem.createGuiItem(1, Material.FEATHER, "§ePush", "§7Pushes a player.");
        final ItemStack rnd = GuiItem.createGuiItem(1, Material.ENDER_PEARL, "§eRandomteleport", "§7Teleports a player randomly.");
        final ItemStack spam = GuiItem.createGuiItem(1, Material.OAK_SIGN, "§eSpam", "§7Spams a player.");
        final ItemStack special = GuiItem.createGuiItem(1, Material.CHEST, "§eSpecial", "§7To get the special.");
        final ItemStack starve = GuiItem.createGuiItem(1, Material.COOKED_CHICKEN, "§eStarve", "§7Starves a player.");
        final ItemStack tpt = GuiItem.createGuiItem(1, Material.FISHING_ROD, "§eTeleporttroll", "§7To get a special item, with which", "§7you can troll players.");
        final ItemStack trap = GuiItem.createGuiItem(1, Material.BEDROCK, "§eTrap", "§7Traps a player.");
        final ItemStack tkick = GuiItem.createGuiItem(1, Material.IRON_DOOR, "§eTrollkick", "§7To trollkick a player.");
        final ItemStack turn = GuiItem.createGuiItem(1, Material.PAPER, "§eTurn", "§7Turns a player around.");
        final ItemStack voidd = GuiItem.createGuiItem(1, Material.OBSIDIAN, "§eVoid", "§7Removes blocks under a player", "§7until he dies in the void.");
        final ItemStack web = GuiItem.createGuiItem(1, Material.COBWEB, "§eWebtrap", "§7Traps a player in cobweb.");
        final ItemStack spank = GuiItem.createGuiItem(1, Material.BONE, "§eSpank", "§7Spanks a player.");
        final ItemStack trample = GuiItem.createGuiItem(1, Material.COW_SPAWN_EGG, "§eTrample", "§7Lets cows trample on a player.");
        final ItemStack mute = GuiItem.createGuiItem(1, Material.LEVER, "§eStfu", "§7To mute a player.");
        final ItemStack popup = GuiItem.createGuiItem(1, Material.BOOK, "§ePopup", "§7Opens a player's inventory.");
        final ItemStack sky = GuiItem.createGuiItem(1, Material.GLASS, "§eSky", "§7Teleports a player to the sky.");
        final ItemStack ab = GuiItem.createGuiItem(1, Material.CLOCK, "§eAbduct", "§7Lets aliens abduct the player.");
        final ItemStack pop = GuiItem.createGuiItem(1, Material.EXPERIENCE_BOTTLE, "§ePopular", "§7Teleports all players to the player.");
        final ItemStack crp = GuiItem.createGuiItem(1, Material.CREEPER_SPAWN_EGG, "§eCreeper", "§7Spawns creepers at the player's location.");
        final ItemStack sp = GuiItem.createGuiItem(1, Material.ARROW, "§eSparta", "§7Shoots arrows at a player", "§7from different locations.");
        final ItemStack drug = GuiItem.createGuiItem(1, Material.WHEAT, "§eDrug", "§7Drugs a player.");
        final ItemStack rain = GuiItem.createGuiItem(1, Material.INK_SAC, "§eSquidrain", "§7Lets squids rain on a player.");
        final ItemStack dropinv = GuiItem.createGuiItem(1, Material.DROPPER, "§eDropinv", "§7Lets a player drop all of his items.");
        final ItemStack garbage = GuiItem.createGuiItem(1, Material.WRITABLE_BOOK, "§eGarbage", "§7To change a player's chat messages to garbage.");
        final ItemStack anvil = GuiItem.createGuiItem(1, Material.ANVIL, "§eAnvil", "§7Drops an anvil on a player.");
        final ItemStack invtext = GuiItem.createGuiItem(2, Material.PAPER, "§eInvtext", "§7Adds a text to a player's inventory with items.");
        final ItemStack rnfrst = GuiItem.createGuiItem(1, Material.IRON_BOOTS, "§eRunforrest", "§7Keeps a player moving for a minute,", "§7otherwise it kills him.");
        final ItemStack tbows = GuiItem.createGuiItem(1, Material.BOW, "§eTrollbows", "§7Choose between 4 different trollbows.");
        final ItemStack border = GuiItem.createGuiItem(1, Material.DEAD_BUSH, "§eBorder", "§7Teleports a player to the world's border.");
        final ItemStack noob = GuiItem.createGuiItem(1, Material.VINE, "§eNoob", "§7Noobs a player.");
        final ItemStack schlong = GuiItem.createGuiItem(1, Material.PINK_TULIP, "§eSchlong", "§7Shclongs a player 8=D.");

        gui.setItem(0, badapple);
        gui.setItem(1, bolt);
        gui.setItem(2, boom);
        gui.setItem(3, burn);
        gui.setItem(4, bury);
        gui.setItem(5, crash);
        gui.setItem(6, denymove);
        gui.setItem(7, fakeop);
        gui.setItem(8, fakedeop);
        gui.setItem(9, fakerestart);
        gui.setItem(10, freefall);
        gui.setItem(11, freeze);
        gui.setItem(12, gokill);
        gui.setItem(13, hero);
        gui.setItem(14, hurt);
        gui.setItem(15, infect);
        gui.setItem(16, launch);
        gui.setItem(17, nomine);
        gui.setItem(18, potato);
        gui.setItem(19, pump);
        gui.setItem(20, push);
        gui.setItem(21, rnd);
        gui.setItem(22, spam);
        gui.setItem(23, special);
        gui.setItem(24, starve);
        gui.setItem(25, tpt);
        gui.setItem(26, trap);
        gui.setItem(27, tkick);
        gui.setItem(28, turn);
        gui.setItem(29, voidd);
        gui.setItem(30, web);
        gui.setItem(31, spank);
        gui.setItem(32, trample);
        gui.setItem(33, mute);
        gui.setItem(34, popup);
        gui.setItem(35, sky);
        gui.setItem(36, ab);
        gui.setItem(37, pop);
        gui.setItem(38, crp);
        gui.setItem(39, sp);
        gui.setItem(40, drug);
        gui.setItem(41, rain);
        gui.setItem(42, dropinv);
        gui.setItem(43, garbage);
        gui.setItem(44, anvil);
        gui.setItem(45, invtext);
        gui.setItem(46, rnfrst);
        gui.setItem(47, tbows);
        gui.setItem(48, border);
        gui.setItem(49, noob);
        gui.setItem(50, schlong);

        gui.setItem(53, close);

        p.openInventory(gui);
    }

    public static void openTrollInv(Player viewer) {

        List<Player> targets = Bukkit.getOnlinePlayers().stream()
                .filter(p -> !p.getUniqueId().equals(viewer.getUniqueId()))
                .collect(Collectors.toList());

        if (targets.isEmpty()) {
            viewer.sendMessage(StringManager.PREFIX + "§cThere are no players online that you could troll.");
            return;
        }

        int slots = (targets.size() + 8) / 9 * 9;

        if (slots > 54) {
            slots = 54;
        }

        final Inventory inv = Bukkit.createInventory(null, slots, "§cTroll a player");

        for (Player target : targets) {

            if (inv.firstEmpty() == -1) break;

            ItemStack head = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta meta = (SkullMeta) head.getItemMeta();

            if (meta != null) {
                meta.setOwningPlayer(target);
                meta.setDisplayName("§e" + target.getName());
                head.setItemMeta(meta);
            }

            inv.addItem(head);
        }

        viewer.openInventory(inv);

    }

}
