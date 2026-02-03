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

        final ItemStack abductItem = GuiItem.createGuiItem(1, Material.CLOCK, "§eAbduct", "§7Lets aliens abduct the player.");
        final ItemStack anvilItem = GuiItem.createGuiItem(1, Material.ANVIL, "§eAnvil", "§7Drops an anvil on a player.");
        final ItemStack badappleItem = GuiItem.createGuiItem(1, Material.APPLE, "§eBadapple", "§7Give the player an", "§7apple. If he eats it he will die.");
        final ItemStack boltItem = GuiItem.createGuiItem(1, Material.FIRE_CHARGE, "§eBolt", "§7Strikes a lightning at", "§7the player's location.");
        final ItemStack boomItem = GuiItem.createGuiItem(1, Material.TNT, "§eBoom", "§7Creates an explosion at", "§7the player's location.");
        final ItemStack borderItem = GuiItem.createGuiItem(1, Material.DEAD_BUSH, "§eBorder", "§7Teleports a player to the world's border.");
        final ItemStack burnItem = GuiItem.createGuiItem(1, Material.LAVA_BUCKET, "§eBurn", "§7Sets the player on fire.");
        final ItemStack buryItem = GuiItem.createGuiItem(1, Material.DIRT, "§eBury", "§7Will bury the player underground.");
        final ItemStack closeItem = GuiItem.createGuiItem(1, Material.EMERALD, "§bClose the gui", "§7Closes the Troll-Gui");
        final ItemStack crashItem = GuiItem.createGuiItem(1, Material.STRING, "§eCrash", "§7Kicks the player from", "§7the server with a fake crash message.");
        final ItemStack creeperItem = GuiItem.createGuiItem(1, Material.CREEPER_SPAWN_EGG, "§eCreeper", "§7Spawns creepers at the player's location.");
        final ItemStack denymoveItem = GuiItem.createGuiItem(1, Material.LEATHER_BOOTS, "§eDenymove", "§7Won't let the player move.");
        final ItemStack dropinvItem = GuiItem.createGuiItem(1, Material.DROPPER, "§eDropinv", "§7Lets a player drop all of his items.");
        final ItemStack drugItem = GuiItem.createGuiItem(1, Material.WHEAT, "§eDrug", "§7Drugs a player.");
        final ItemStack fakedeopItem = GuiItem.createGuiItem(1, Material.GOLD_INGOT, "§eFakedeop", "§7Fake deop's a player.");
        final ItemStack fakeopItem = GuiItem.createGuiItem(1, Material.DIAMOND, "§eFakeop", "§7Fake op's a player.");
        final ItemStack fakerestartItem = GuiItem.createGuiItem(1, Material.BLAZE_POWDER, "§eFakerestart", "§7Fake restarts the server.");
        final ItemStack freefallItem = GuiItem.createGuiItem(1, Material.WHITE_WOOL, "§eFreefall", "§7Lets a player freefall", "§7from a certain height.");
        final ItemStack freezeItem = GuiItem.createGuiItem(1, Material.ICE, "§eFreeze", "§7Freezes a player.");
        final ItemStack garbageItem = GuiItem.createGuiItem(1, Material.WRITABLE_BOOK, "§eGarbage", "§7To change a player's chat messages to garbage.");
        final ItemStack gokillItem = GuiItem.createGuiItem(1, Material.SOUL_SAND, "§eGokill", "§7Kills a player after a certain period.");
        final ItemStack herobrineItem = GuiItem.createGuiItem(1, Material.GLOWSTONE_DUST, "§eHerobrine", "§7Sets a player to Herobrine.");
        final ItemStack hurtItem = GuiItem.createGuiItem(1, Material.REDSTONE, "§eHurt", "§7Hurts a player.");
        final ItemStack infectItem = GuiItem.createGuiItem(1, Material.POTION, "§eInfect", "§7Infects a player.");
        final ItemStack invtextItem = GuiItem.createGuiItem(2, Material.PAPER, "§eInvtext", "§7Adds a text to a player's inventory with items.");
        final ItemStack launchItem = GuiItem.createGuiItem(1, Material.FIREWORK_ROCKET, "§eLaunch", "§7Launchs a player.");
        final ItemStack muteItem = GuiItem.createGuiItem(1, Material.LEVER, "§eStfu", "§7To mute a player.");
        final ItemStack nomineItem = GuiItem.createGuiItem(1, Material.GRASS_BLOCK, "§eNomine", "§7Prevents a player from breaking blocks.");
        final ItemStack noobItem = GuiItem.createGuiItem(1, Material.VINE, "§eNoob", "§7Noobs a player.");
        final ItemStack popularItem = GuiItem.createGuiItem(1, Material.EXPERIENCE_BOTTLE, "§ePopular", "§7Teleports all players to the player.");
        final ItemStack popupItem = GuiItem.createGuiItem(1, Material.BOOK, "§ePopup", "§7Opens a player's inventory.");
        final ItemStack potatoItem = GuiItem.createGuiItem(1, Material.BAKED_POTATO, "§ePotatotroll", "§7Replaces every item in a player's", "§7inventory with a potato.");
        final ItemStack pumpkinheadItem = GuiItem.createGuiItem(1, Material.JACK_O_LANTERN, "§ePumpkinhead", "§7Sets the head of a player to a pumpkin.");
        final ItemStack pushItem = GuiItem.createGuiItem(1, Material.FEATHER, "§ePush", "§7Pushes a player.");
        final ItemStack squidrainItem = GuiItem.createGuiItem(1, Material.INK_SAC, "§eSquidrain", "§7Lets squids rain on a player.");
        final ItemStack randomteleportItem = GuiItem.createGuiItem(1, Material.ENDER_PEARL, "§eRandomteleport", "§7Teleports a player randomly.");
        final ItemStack runforrestItem = GuiItem.createGuiItem(1, Material.IRON_BOOTS, "§eRunforrest", "§7Keeps a player moving for a minute,", "§7otherwise it kills him.");
        final ItemStack schlongItem = GuiItem.createGuiItem(1, Material.PINK_TULIP, "§eSchlong", "§7Schlongs a player 8=D.");
        final ItemStack skyItem = GuiItem.createGuiItem(1, Material.GLASS, "§eSky", "§7Teleports a player to the sky.");
        final ItemStack spartaItem = GuiItem.createGuiItem(1, Material.ARROW, "§eSparta", "§7Shoots arrows at a player", "§7from different locations.");
        final ItemStack spamItem = GuiItem.createGuiItem(1, Material.OAK_SIGN, "§eSpam", "§7Spams a player.");
        final ItemStack spankItem = GuiItem.createGuiItem(1, Material.BONE, "§eSpank", "§7Spanks a player.");
        final ItemStack specialItem = GuiItem.createGuiItem(1, Material.CHEST, "§eSpecial", "§7To get the special.");
        final ItemStack starveItem = GuiItem.createGuiItem(1, Material.COOKED_CHICKEN, "§eStarve", "§7Starves a player.");
        final ItemStack trollbowsItem = GuiItem.createGuiItem(1, Material.BOW, "§eTrollbows", "§7Choose between 4 different trollbows.");
        final ItemStack trollkickItem = GuiItem.createGuiItem(1, Material.IRON_DOOR, "§eTrollkick", "§7To trollkick a player.");
        final ItemStack teleporttrollItem = GuiItem.createGuiItem(1, Material.FISHING_ROD, "§eTeleporttroll", "§7To get a special item, with which", "§7you can troll players.");
        final ItemStack trampleItem = GuiItem.createGuiItem(1, Material.COW_SPAWN_EGG, "§eTrample", "§7Lets cows trample on a player.");
        final ItemStack trapItem = GuiItem.createGuiItem(1, Material.BEDROCK, "§eTrap", "§7Traps a player.");
        final ItemStack turnItem = GuiItem.createGuiItem(1, Material.PAPER, "§eTurn", "§7Turns a player around.");
        final ItemStack voidItem = GuiItem.createGuiItem(1, Material.OBSIDIAN, "§eVoid", "§7Removes blocks under a player", "§7until he dies in the void.");
        final ItemStack webtrapItem = GuiItem.createGuiItem(1, Material.COBWEB, "§eWebtrap", "§7Traps a player in cobweb.");

        gui.setItem(0, badappleItem);
        gui.setItem(1, boltItem);
        gui.setItem(2, boomItem);
        gui.setItem(3, burnItem);
        gui.setItem(4, buryItem);
        gui.setItem(5, crashItem);
        gui.setItem(6, denymoveItem);
        gui.setItem(7, fakeopItem);
        gui.setItem(8, fakedeopItem);
        gui.setItem(9, fakerestartItem);
        gui.setItem(10, freefallItem);
        gui.setItem(11, freezeItem);
        gui.setItem(12, gokillItem);
        gui.setItem(13, herobrineItem);
        gui.setItem(14, hurtItem);
        gui.setItem(15, infectItem);
        gui.setItem(16, launchItem);
        gui.setItem(17, nomineItem);
        gui.setItem(18, potatoItem);
        gui.setItem(19, pumpkinheadItem);
        gui.setItem(20, pushItem);
        gui.setItem(21, randomteleportItem);
        gui.setItem(22, spamItem);
        gui.setItem(23, specialItem);
        gui.setItem(24, starveItem);
        gui.setItem(25, teleporttrollItem);
        gui.setItem(26, trapItem);
        gui.setItem(27, trollkickItem);
        gui.setItem(28, turnItem);
        gui.setItem(29, voidItem);
        gui.setItem(30, webtrapItem);
        gui.setItem(31, spankItem);
        gui.setItem(32, trampleItem);
        gui.setItem(33, muteItem);
        gui.setItem(34, popupItem);
        gui.setItem(35, skyItem);
        gui.setItem(36, abductItem);
        gui.setItem(37, popularItem);
        gui.setItem(38, creeperItem);
        gui.setItem(39, spartaItem);
        gui.setItem(40, drugItem);
        gui.setItem(41, squidrainItem);
        gui.setItem(42, dropinvItem);
        gui.setItem(43, garbageItem);
        gui.setItem(44, anvilItem);
        gui.setItem(45, invtextItem);
        gui.setItem(46, runforrestItem);
        gui.setItem(47, trollbowsItem);
        gui.setItem(48, borderItem);
        gui.setItem(49, noobItem);
        gui.setItem(50, schlongItem);

        gui.setItem(53, closeItem);

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
