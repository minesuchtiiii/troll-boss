package me.minesuchtiiii.trollboss;

import me.minesuchtiiii.trollboss.commands.*;
import me.minesuchtiiii.trollboss.listeners.anvil.AnvilBlockLandListener;
import me.minesuchtiiii.trollboss.listeners.anvil.DeathListenerAnvil;
import me.minesuchtiiii.trollboss.listeners.trollapple.DeathListenerApple;
import me.minesuchtiiii.trollboss.listeners.chat.ChatListener;
import me.minesuchtiiii.trollboss.listeners.chat.PlayerChatMuteListener;
import me.minesuchtiiii.trollboss.listeners.trollcreeper.CreeperDamageByCreeperListener;
import me.minesuchtiiii.trollboss.listeners.trollcreeper.CreeperExplodeListener;
import me.minesuchtiiii.trollboss.listeners.death.*;
import me.minesuchtiiii.trollboss.listeners.gui.BowGuiListener;
import me.minesuchtiiii.trollboss.listeners.gui.GuiListener;
import me.minesuchtiiii.trollboss.listeners.gui.StatisticsGuiListener;
import me.minesuchtiiii.trollboss.listeners.gui.TrollInvListener;
import me.minesuchtiiii.trollboss.listeners.trollherobrine.HerobrineListener;
import me.minesuchtiiii.trollboss.listeners.trollherobrine.HerobrineMoveListener;
import me.minesuchtiiii.trollboss.listeners.interact.InteractBlockShooterListener;
import me.minesuchtiiii.trollboss.listeners.interact.InteractEventAK;
import me.minesuchtiiii.trollboss.listeners.trollapple.InteractEventApple;
import me.minesuchtiiii.trollboss.listeners.trollteleport.InteractEventTptroll;
import me.minesuchtiiii.trollboss.listeners.join.JoinListenerUpdate;
import me.minesuchtiiii.trollboss.listeners.join.JoinListenerWhenCrashed;
import me.minesuchtiiii.trollboss.listeners.misc.DenyPickupListener;
import me.minesuchtiiii.trollboss.listeners.misc.MineListener;
import me.minesuchtiiii.trollboss.listeners.misc.MoveListener;
import me.minesuchtiiii.trollboss.listeners.projectiles.EntityDamageByBlockShooterListener;
import me.minesuchtiiii.trollboss.listeners.projectiles.ProjectileBowsHitListener;
import me.minesuchtiiii.trollboss.listeners.trollteleport.ProjectileHitListener;
import me.minesuchtiiii.trollboss.listeners.projectiles.PullBowListener;
import me.minesuchtiiii.trollboss.listeners.quit.QuitListener;
import me.minesuchtiiii.trollboss.listeners.quit.QuitListenerRestart;
import me.minesuchtiiii.trollboss.tabcomplete.Tc_Troll;
import me.minesuchtiiii.trollboss.tabcomplete.Tc_TrollOp;
import me.minesuchtiiii.trollboss.tabcomplete.Tc_Trolltutorial;
import me.minesuchtiiii.trollboss.utils.StringManager;
import me.minesuchtiiii.trollboss.utils.UpdateChecker;
import org.bstats.bukkit.Metrics;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

// This thing is a god class and needs to be refactored heavily :/
public class TrollBoss extends JavaPlugin {

    private static final int METRICS_ID = 15941;
    public final Map<UUID, List<Location>> ufoBlockLocations = new HashMap<>();
    public final int max = 6;
    private final ArrayList<Entity> cows = new ArrayList<>();
    private final ArrayList<Location> glassloc = new ArrayList<>();
    private final ArrayList<String> spams = new ArrayList<>();
    private final File gbmsgs = new File(getDataFolder() + "/GarbageMessages.yml");
    private final File stats = new File(getDataFolder() + "/stats.yml");
    private final FileConfiguration gbmsgscfg = YamlConfiguration.loadConfiguration(gbmsgs);
    private final FileConfiguration statscfg = YamlConfiguration.loadConfiguration(stats);
    private final HashMap<String, Integer> fiveSecondTimerTask = new HashMap<>();
    private final HashMap<String, Integer> sixtySecondTimerTask = new HashMap<>();
    private final HashMap<String, Integer> tasks2 = new HashMap<>();
    private final HashMap<String, Location> rfloc = new HashMap<>();
    private final HashMap<UUID, Integer> spamTask = new HashMap<>();
    private final HashMap<UUID, Inventory> invstores = new HashMap<>();
    public ArrayList<Integer> potatoTroll = new ArrayList<>();
    public ArrayList<String> color = new ArrayList<>();
    public ArrayList<UUID> booming = new ArrayList<>();
    public ArrayList<UUID> bury = new ArrayList<>();
    public ArrayList<UUID> canAnvil = new ArrayList<>();
    public ArrayList<UUID> crashed = new ArrayList<>();
    public ArrayList<UUID> dead = new ArrayList<>();
    public ArrayList<UUID> deadHurt = new ArrayList<>();
    public ArrayList<UUID> deadMessage = new ArrayList<>();
    public ArrayList<UUID> denyMove = new ArrayList<>();
    public ArrayList<UUID> denyPickup = new ArrayList<>();
    public ArrayList<UUID> diedOnSparta = new ArrayList<>();
    public ArrayList<UUID> freefall = new ArrayList<>();
    public ArrayList<UUID> garbageTroll = new ArrayList<>();
    public ArrayList<UUID> goKill = new ArrayList<>();
    public ArrayList<UUID> herobrine = new ArrayList<>();
    public ArrayList<UUID> hunger = new ArrayList<>();
    public ArrayList<UUID> hurt = new ArrayList<>();
    public ArrayList<UUID> kicked = new ArrayList<>();
    public ArrayList<UUID> moveWhileNoobed = new ArrayList<>();
    public ArrayList<UUID> muted = new ArrayList<>();
    public ArrayList<UUID> nomine = new ArrayList<>();
    public ArrayList<UUID> playersBeingAbducted = new ArrayList<>();
    public ArrayList<UUID> randomtp = new ArrayList<>();
    public ArrayList<UUID> res = new ArrayList<>();
    public ArrayList<UUID> runIt = new ArrayList<>();
    public ArrayList<UUID> skyTroll = new ArrayList<>();
    public ArrayList<UUID> spartaTroll = new ArrayList<>();
    public ArrayList<UUID> squidRaining = new ArrayList<>();
    public ArrayList<UUID> trampled = new ArrayList<>();
    public ArrayList<UUID> tutorialPlayers = new ArrayList<>();
    public ArrayList<UUID> usable = new ArrayList<>();
    public ArrayList<UUID> voidDead = new ArrayList<>();
    public ArrayList<UUID> webtrap = new ArrayList<>();
    public HashMap<Integer, Location> altblockloc = new HashMap<>();
    public HashMap<Integer, Location> block = new HashMap<>();
    public HashMap<Integer, Location> blockloc = new HashMap<>();
    public HashMap<Integer, Location> blocks = new HashMap<>();
    public HashMap<Integer, Location> oldBlocksLocation = new HashMap<>();
    public HashMap<Integer, Material> blockmat = new HashMap<>();
    public HashMap<Integer, Material> numbersmat = new HashMap<>();
    public HashMap<Integer, Material> zahlmat = new HashMap<>();
    public HashMap<Integer, String> randomTrolls = new HashMap<>();
    public HashMap<String, Boolean> rf = new HashMap<>();
    public HashMap<String, Double> yloc = new HashMap<>();
    public HashMap<String, Float> pitch = new HashMap<>();
    public HashMap<String, Float> yaw = new HashMap<>();
    public HashMap<String, Integer> rftime = new HashMap<>();
    public HashMap<String, Integer> warnTime = new HashMap<>();
    public HashMap<String, Location> beforeNoob = new HashMap<>();
    public HashMap<String, Location> oldShlongLocation = new HashMap<>();
    public HashMap<String, Location> skymap = new HashMap<>();
    public HashMap<String, String> rfmsg = new HashMap<>();
    public HashMap<UUID, Boolean> canAccept = new HashMap<>();
    public HashMap<UUID, Integer> secondsToAccept = new HashMap<>();
    public HashMap<UUID, Integer> spartaArrows = new HashMap<>();
    public HashMap<UUID, Integer> taskID = new HashMap<>();
    public HashMap<UUID, Integer> tutorialNum = new HashMap<>();
    public HashMap<UUID, Location> abductedCachedLocations = new HashMap<>();
    public HashMap<UUID, UUID> trolling = new HashMap<>();
    public HashSet<UUID> spammedPlayers = new HashSet<>();
    public boolean c;
    public boolean canNoob = true;
    public boolean creep;
    public boolean isRestarting = false;
    public boolean isShlonged = false;
    public boolean isTrapped;
    public boolean isVoid = false;
    public boolean worked = false;
    public int bowCreepers = 0;
    public int creepers = 0;
    public int lvl = 0;
    public int spartaTask;
    public int time = 14;
    public int trollBuffer = 0;
    private String version;
    private boolean update;
    private int tutorialTask;
    private static TrollBoss INSTANCE;

    public static TrollBoss getInstance() {
        return INSTANCE;
    }

    private static ItemStack getHead(Player p) {

        ItemStack item = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skull = (SkullMeta) item.getItemMeta();
        skull.setOwningPlayer(Bukkit.getPlayer(p.getName()));
        item.setItemMeta(skull);

        return item;
    }

    public static void spawnAbductParticlesWide(Player p, int lower, int upper) {

        final int points = 27;
        final double size = 5;

        Bukkit.getOnlinePlayers().forEach(all -> {

            for (double k = lower; k < upper; k += 3) {

                for (int i = 0; i < 360; i += 360 / points) {
                    final double angle = (i * Math.PI / 180);
                    final double x = size * Math.cos(angle);
                    final double z = size * Math.sin(angle);

                    final Location locNew = p.getLocation().add(x, 0, z);
                    locNew.setY(k);

                    double offY = new Random().nextDouble(0, 0.33);

                    all.getWorld().spawnParticle(Particle.SOUL_FIRE_FLAME, locNew, 3, 0, offY, 0, 0.033, null, true);
                    all.getWorld().spawnParticle(Particle.FLAME, locNew, 3, 0, offY, 0, 0.033, null, true);

                }

            }
        });

    }

    @Override
    public void onDisable() {

        Bukkit.getServer().getScheduler().cancelTasks(this);

        saveTrolls();
        unsetHerobrines();

    }

    @Override
    public void onEnable() {
        INSTANCE = this;
        version = Bukkit.getBukkitVersion();

        registerEvents();
        registerCommands();
        saveDefaultConfigFile();

        update = getConfig().getBoolean("Auto-Update");

        checkForUpdate();
        addRandomTrolls();
        addSpamStuff();
        check4File();
        check4otherFile();

        new Metrics(this, METRICS_ID);

    }

    private void registerEvents() {

        final PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new QuitListener(this), this);
        pm.registerEvents(new MoveListener(this), this);
        pm.registerEvents(new InteractEventAK(), this);
        pm.registerEvents(new InteractEventApple(this), this);
        pm.registerEvents(new DeathListenerApple(this), this);
        pm.registerEvents(new InteractEventTptroll(), this);
        pm.registerEvents(new ProjectileHitListener(), this);
        pm.registerEvents(new JoinListenerUpdate(this), this);
        pm.registerEvents(new HerobrineListener(this), this);
        pm.registerEvents(new HerobrineMoveListener(this), this);
        pm.registerEvents(new QuitListenerRestart(this), this);
        pm.registerEvents(new DeathListenerHurt(this), this);
        pm.registerEvents(new DeathListenerBug(this), this);
        pm.registerEvents(new DeathListenerVoid(this), this);
        pm.registerEvents(new MineListener(this), this);
        pm.registerEvents(new InteractBlockShooterListener(this), this);
        pm.registerEvents(new EntityDamageByBlockShooterListener(), this);
        pm.registerEvents(new DeathRemoveSpecialListener(), this);
        pm.registerEvents(new GuiListener(this), this);
        pm.registerEvents(new JoinListenerWhenCrashed(this), this);
        pm.registerEvents(new DeathListenerTrample(this), this);
        pm.registerEvents(new PlayerChatMuteListener(this), this);
        pm.registerEvents(new TrollInvListener(this), this);
        pm.registerEvents(new CreeperExplodeListener(this), this);
        pm.registerEvents(new SpartaDeathListener(this), this);
        pm.registerEvents(new DeathListenerSky(this), this);
        pm.registerEvents(new BowGuiListener(this), this);
        pm.registerEvents(new ProjectileBowsHitListener(this), this);
        pm.registerEvents(new CreeperDamageByCreeperListener(this), this);
        pm.registerEvents(new DenyPickupListener(this), this);
        pm.registerEvents(new ChatListener(this), this);
        pm.registerEvents(new DeathListenerAnvil(), this);
        pm.registerEvents(new PullBowListener(this), this);
        pm.registerEvents(new StatisticsGuiListener(this), this);
        pm.registerEvents(new AnvilBlockLandListener(), this);

    }

    private void saveDefaultConfigFile() {

        getConfig().addDefault("Auto-Update", true);
        getConfig().addDefault("Troll-Operators", true);
        getConfig().addDefault("Trolls", 0);

        int version = Integer.parseInt(getServer().getMinecraftVersion().split("\\.")[1]);
        int subVersion = Integer.parseInt(getServer().getMinecraftVersion().split("\\.")[2]);

        if (version >= 19 || version == 18 && subVersion >= 1) {

            getConfig().options().setHeader(List.of("Some options you can edit"));

            getConfig().options().parseComments(true);

            getConfig().setComments("Trolls", List.of("Used for the statistics, should not be touched"));
            getConfig().setComments("Troll-Operators", List.of("Define if operators can be trolled or not", "default: true"));
            getConfig().setComments("Auto-Update", List.of("Define if the plugin should automatically update when a new version is available", "default: true"));

        } else if (getServer().getMinecraftVersion().contains("1.18-") || version <= 17 && version >= 14) {

            getConfig().options().setHeader(List.of("Some options you can edit"));

        }

        getConfig().options().copyDefaults(true);
        saveDefaultConfig();
        reloadConfig();
        saveConfig();

    }

    private void registerCommands() {

        getCommand("troll").setExecutor(new TrollCommand(this));
        getCommand("troll").setTabCompleter(new Tc_Troll(this));
        getCommand("burn").setExecutor(new BurnCommand(this));
        getCommand("freeze").setExecutor(new FreezeCommand(this));
        getCommand("bolt").setExecutor(new BoltCommand(this));
        getCommand("special").setExecutor(new SpecialCommand(this));
        getCommand("boom").setExecutor(new BoomCommand(this));
        getCommand("push").setExecutor(new PushCommand(this));
        getCommand("fakeop").setExecutor(new FakeopCommand(this));
        getCommand("fakedeop").setExecutor(new FakedeopCommand(this));
        getCommand("launch").setExecutor(new LaunchCommand(this));
        getCommand("spam").setExecutor(new SpamCommand(this));
        getCommand("gokill").setExecutor(new GokillCommand(this));
        getCommand("switch").setExecutor(new SwitchCommand(this));
        getCommand("trollkick").setExecutor(new TrollkickCommand(this));
        getCommand("badapple").setExecutor(new BadappleCommand(this));
        getCommand("denymove").setExecutor(new DenymoveCommand(this));
        getCommand("potatotroll").setExecutor(new PotatotrollCommand(this));
        getCommand("trap").setExecutor(new TrapCommand(this));
        getCommand("tptroll").setExecutor(new TptrollCommand(this));
        getCommand("infect").setExecutor(new InfectCommand(this));
        getCommand("herobrine").setExecutor(new HerobrineCommand(this));
        getCommand("fakerestart").setExecutor(new FakeRestartCommand(this));
        getCommand("turn").setExecutor(new TurnCommand(this));
        getCommand("starve").setExecutor(new StarveCommand(this));
        getCommand("hurt").setExecutor(new HurtCommand(this));
        getCommand("void").setExecutor(new VoidCommand(this));
        getCommand("pumpkinhead").setExecutor(new PumpkinheadCommand(this));
        getCommand("bury").setExecutor(new BuryCommand(this));
        getCommand("nomine").setExecutor(new NomineCommand(this));
        getCommand("randomtp").setExecutor(new RandomTpCommand(this));
        getCommand("crash").setExecutor(new CrashCommand(this));
        getCommand("freefall").setExecutor(new FreefallCommand(this));
        getCommand("webtrap").setExecutor(new WebtrapCommand(this));
        getCommand("spank").setExecutor(new SpankCommand(this));
        getCommand("trample").setExecutor(new TrampleCommand(this));
        getCommand("trollop").setExecutor(new TrollopCommand(this));
        getCommand("trollop").setTabCompleter(new Tc_TrollOp());
        getCommand("stfu").setExecutor(new StfuCommand(this));
        getCommand("popup").setExecutor(new PopupCommand(this));
        getCommand("sky").setExecutor(new SkyCommand(this));
        getCommand("abduct").setExecutor(new AbductCommand(this));
        getCommand("popular").setExecutor(new PopularCommand(this));
        getCommand("creeper").setExecutor(new CreeperCommand(this));
        getCommand("sparta").setExecutor(new SpartaCommand(this));
        getCommand("trollbows").setExecutor(new BowsCommand(this));
        getCommand("drug").setExecutor(new DrugCommand(this));
        getCommand("squidrain").setExecutor(new SquidrainCommand(this));
        getCommand("trolltutorial").setExecutor(new TrolltutorialCommand(this));
        getCommand("trolltutorial").setTabCompleter(new Tc_Trolltutorial(this));
        getCommand("dropinv").setExecutor(new DropinvCommand(this));
        getCommand("garbage").setExecutor(new GarbageCommand(this));
        getCommand("anvil").setExecutor(new AnvilCommand(this));
        getCommand("invtext").setExecutor(new InvtextCommand(this));
        getCommand("runforrest").setExecutor(new RunforrestCommand(this));
        getCommand("border").setExecutor(new BorderCommand(this));
        getCommand("noob").setExecutor(new NoobCommand(this));
        getCommand("randomtroll").setExecutor(new RandomtrollCommand(this));
        getCommand("shlong").setExecutor(new ShlongCommand(this));

    }

    private void unsetHerobrines() {

        Bukkit.getOnlinePlayers().forEach(this::unsetHerobrine);

    }

    private void addSpamStuff() {

        color.addAll(List.of("§a", "§b", "§c", "§d", "§e", "§f", "§1", "§2", "§3", "§4", "§5", "§6", "§7", "§8", "§9", "§o", "§k", "§m", "§n", "§l"));

        spams.add("Hello, world!");
        spams.add("You're being trolled!");
        spams.add("Prepare for epic spam!");
        spams.add("Oops, did I do that?");
        spams.add("Time to rage!");
        spams.add("Keep calm and carry on.");
        spams.add("This is not a drill.");
        spams.add("Surprise, surprise!");
        spams.add("You just got pranked!");
        spams.add("I come in peace... or do I?");
        spams.add("Beware of the troll!");
        spams.add("Is it Friday yet?");
        spams.add("Chill out and enjoy.");
        spams.add("Don't feed the trolls!");
        spams.add("Enjoy your day!");
        spams.add("Buckle up, buttercup!");
        spams.add("No comment.");
        spams.add("Who let the trolls out?");
        spams.add("Haha, gotcha!");
        spams.add("This is your daily dose of randomness.");
        spams.add("Randomness is the spice of life.");
        spams.add("Smile, it's contagious!");
        spams.add("I speak fluent nonsense.");
        spams.add("Expect the unexpected.");
        spams.add("Tick tock, time's up!");
        spams.add("Unleash the spam!");
        spams.add("Trolls will be trolls.");
        spams.add("Why so serious?");
        spams.add("Carpe diem!");
        spams.add("All your base are belong to us.");
        spams.add("Nyan nyan nyan.");
        spams.add("This is getting out of hand.");
        spams.add("Can you handle the truth?");
        spams.add("The cake is a lie!");
        spams.add("Keep your friends close, enemies closer.");
        spams.add("I solemnly swear I'm up to no good.");
        spams.add("Wubba lubba dub dub!");
        spams.add("May the force be with you.");
        spams.add("You're awesome!");
        spams.add("Hello, dark knight.");
        spams.add("Don't stop believing.");
        spams.add("We are the champions!");
        spams.add("Another one bites the dust.");
        spams.add("I'm in me mum's car, vroom vroom!");
        spams.add("Fortune favors the bold.");
        spams.add("Stay weird!");
        spams.add("And now, a random fact: Honey never spoils.");
        spams.add("Just when you thought it was safe...");
        spams.add("This message is brought to you by random chance.");
        spams.add("Did you know? Bananas are berries!");

    }

    private void checkForUpdate() {

        new UpdateChecker(this, 47423).getVersion(version -> {
            if (getPluginMeta().getVersion().equals(version)) {
                getLogger().info("You are using the latest version of " + getPluginMeta().getName() + "!");
            } else {
                getLogger().info("There is a new update available!");
                getLogger().info("Your version: v" + getPluginMeta().getVersion());
                getLogger().info("Latest version: " + version);
                getLogger().info("Download it at: https://www.spigotmc.org/resources/trollboss.47423/");
            }
        });

    }

    public void notOnline(Player p, String name) {

        p.sendMessage(StringManager.PREFIX + "§ePlayer §7" + name + " §eis not online!");
    }

    public void spamPlayer(Player target, int amount) {

        spammedPlayers.add(target.getUniqueId());
        final int[] number = {amount};

        int task = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {

            if (number[0] > 0) {
                final Random r = new Random();
                final int i = r.nextInt(this.spams.size());

                final Random x = new Random();
                final int a = x.nextInt(this.color.size());

                target.sendMessage(this.color.get(a) + this.spams.get(i));

                number[0]--;

            }
            if (number[0] == 0) {
                spammedPlayers.remove(target.getUniqueId());
                Bukkit.getScheduler().cancelTask(spamTask.get(target.getUniqueId()));
                spamTask.remove(target.getUniqueId());
            }


        }, 0L, 20L);

        spamTask.put(target.getUniqueId(), task);

    }

    public void sendHelp(Player p, int i) {

        if ((i > 0) && (i < max + 1)) {
            if (i == 1) {

                p.sendMessage("§7§l|§e§l==============§7§l| §r§cHelp page §4" + i +
                        "§c/§4" + max + "§7§l |§e§l==============§7§l|");
                p.sendMessage("§a * §7[§cTrollBoss§7] §7§l§oHere's a list of all available commands:");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/troll");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/troll help §c[page]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/troll §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/troll statistics");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/trollop §7[true / false / status]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/trolltutorial <confirm / reject / stop>");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/burn §7[player / all]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/freeze §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/bolt §7[player / all]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/special §c[number] §7[player]");
                p.sendMessage("§a * §cType §4/troll help " + (i + 1) + " §cfor the next page");
                p.sendMessage("§7§l|§e§l===============§7§l| §r§cVersion §4" +
                        getPluginMeta().getVersion() + "§7§l |§e§l==============§7§l|");

            } else if (i == 2) {

                p.sendMessage("§7§l|§e§l==============§7§l| §r§cHelp page §4" + i +
                        "§c/§4" + max + "§7§l |§e§l==============§7§l|");
                p.sendMessage("§a * §7[§cTrollBoss§7] §7§l§oHere's a list of all available commands:");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/launch §7[player / all]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/fakeop §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/fakedeop §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/spam §7[player] §c[amount]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/trollkick §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/badapple §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/boom §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/push §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/gokill §7[player] §c[delay]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/switch §7[player] [player]");
                p.sendMessage("§a * §cType §4/troll help " + (i + 1) + " §cfor the next page");
                p.sendMessage("§7§l|§e§l===============§7§l| §r§cVersion §4" +
                        getPluginMeta().getVersion() + "§7§l |§e§l==============§7§l|");
            } else if (i == 3) {

                p.sendMessage("§7§l|§e§l==============§7§l| §r§cHelp page §4" + i +
                        "§c/§4" + max + "§7§l |§e§l==============§7§l|");
                p.sendMessage("§a * §7[§cTrollBoss§7] §7§l§oHere's a list of all available commands:");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/denymove §7[player / all] §c[delay]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/potatotroll §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/trap §7[player] §c[delay]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/tptroll §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/infect §7[player] §c[time]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/herobrine §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/fakerestart §c[time]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/turn §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/starve §7[player] §c[count]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/hurt §7[player] §c[count]");
                p.sendMessage("§a * §cType §4/troll help " + (i + 1) + " §cfor the next page");
                p.sendMessage("§7§l|§e§l===============§7§l| §r§cVersion §4" +
                        getPluginMeta().getVersion() + "§7§l |§e§l==============§7§l|");

            } else if (i == 4) {

                p.sendMessage("§7§l|§e§l==============§7§l| §r§cHelp page §4" + i +
                        "§c/§4" + max + "§7§l |§e§l==============§7§l|");
                p.sendMessage("§a * §7[§cTrollBoss§7] §7§l§oHere's a list of all available commands:");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/void §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/pumpkinhead §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/bury §7[player] §c[time]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/nomine §7[player] §c[time]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/randomtp §7[player] §c[count]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/crash §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/freefall §7[player] §c[high]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/webtrap §7[player] §c[time]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/spank §7[player / all]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/trample §7[player] §c[amount]");
                p.sendMessage("§a * §cType §4/troll help " + (i + 1) + " §cfor the next page");
                p.sendMessage("§7§l|§e§l===============§7§l| §r§cVersion §4" +
                        getPluginMeta().getVersion() + "§7§l |§e§l==============§7§l|");

            } else if (i == 5) {

                p.sendMessage("§7§l|§e§l==============§7§l| §r§cHelp page §4" + i +
                        "§c/§4" + max + "§7§l |§e§l==============§7§l|");
                p.sendMessage("§a * §7[§cTrollBoss§7] §7§l§oHere's a list of all available commands:");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/stfu §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/popup §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/sky §7[player] §c[time]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/abduct §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/popular §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/creeper §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/sparta §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/trollbows");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/drug §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/squidrain §7[player] §c[number]");
                p.sendMessage("§a * §cType §4/troll help " + (i + 1) + " §cfor the next page");
                p.sendMessage("§7§l|§e§l===============§7§l| §r§cVersion §4" +
                        getPluginMeta().getVersion() + "§7§l |§e§l==============§7§l|");

            } else {

                p.sendMessage("§7§l|§e§l==============§7§l| §r§cHelp page §4" + i +
                        "§c/§4" + max + "§7§l |§e§l==============§7§l|");
                p.sendMessage("§a * §7[§cTrollBoss§7] §7§l§oHere's a list of all available commands:");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/dropinv §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/garbage §7[player] §c[on | off]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/anvil §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/invtext §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/runforrest §7[player] [time]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/border §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/noob §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/randomtroll §7[player]");
                p.sendMessage("§a * §7[§cTrollBoss§7] §e/shlong §7[player]");
                p.sendMessage("§a * §cType §4/troll help " + (i - 1) + " §cto get to the previous page");
                p.sendMessage("§7§l|§e§l===============§7§l| §r§cVersion §4" +
                        getPluginMeta().getVersion() + "§7§l |§e§l==============§7§l|");

            }

        } else {
            p.sendMessage(StringManager.PREFIX + "§cCan't find help page §4" + i + "§c!");
        }

    }

    public void setHerobrine(Player p) {

        herobrine.add(p.getUniqueId());

        Bukkit.getOnlinePlayers().forEach(all -> all.hidePlayer(this, p));

    }

    public void unsetHerobrine(Player p) {

        herobrine.remove(p.getUniqueId());

        Bukkit.getOnlinePlayers().forEach(all -> all.showPlayer(this, p));
    }

    public boolean isInt(String s) {

        try {
            Integer.parseInt(s);
        } catch (NumberFormatException e) {
            e.printStackTrace();

            return false;
        }
        return true;
    }

    public void restartMessage(int i) {

        Bukkit.broadcastMessage("§7§l[§c§lServer§7§l] §r§6Server will be restarting in §4" + i + " §6seconds!");

    }

    public void kickSchedu(final Player p) {

        Bukkit.getScheduler().runTaskLater(this, () -> {

            List<Player> playersToKick = Bukkit.getOnlinePlayers().stream()
                    .filter(all -> !all.getUniqueId().equals(p.getUniqueId()))
                    .collect(Collectors.toList());

            for (Player all : playersToKick) {
                TrollBoss.this.res.add(all.getUniqueId());
                all.kickPlayer("§cServer restarting...");
            }

            TrollBoss.this.res.clear();

        }, 30L);

    }

    private void addRandomTrolls() {

        randomTrolls.put(1, "badapple");
        randomTrolls.put(2, "bolt");
        randomTrolls.put(3, "boom");
        randomTrolls.put(4, "burn");
        randomTrolls.put(5, "bury");

        randomTrolls.put(6, "crash");
        randomTrolls.put(7, "denymove");
        randomTrolls.put(8, "fakeop");
        randomTrolls.put(9, "fakedeop");
        randomTrolls.put(10, "fakerestart");

        randomTrolls.put(11, "freefall");
        randomTrolls.put(12, "freeze");
        randomTrolls.put(13, "gokill");
        randomTrolls.put(14, "herobrine");
        randomTrolls.put(15, "hurt");

        randomTrolls.put(16, "infect");
        randomTrolls.put(17, "launch");
        randomTrolls.put(18, "nomine");
        randomTrolls.put(19, "potatotroll");
        randomTrolls.put(20, "pumpkinhead");

        randomTrolls.put(21, "push");
        randomTrolls.put(22, "randomtp");
        randomTrolls.put(23, "spam");
        randomTrolls.put(24, "starve");
        randomTrolls.put(25, "trap");

        randomTrolls.put(26, "trollkick");
        randomTrolls.put(27, "turn");
        randomTrolls.put(28, "void");
        randomTrolls.put(29, "webtrap");
        randomTrolls.put(30, "spank");

        randomTrolls.put(31, "trample");
        randomTrolls.put(32, "stfu");
        randomTrolls.put(33, "popup");
        randomTrolls.put(34, "sky");
        randomTrolls.put(35, "abduct");

        randomTrolls.put(36, "popular");
        randomTrolls.put(37, "creeper");
        randomTrolls.put(38, "sparta");
        randomTrolls.put(39, "drug");
        randomTrolls.put(40, "squidrain");

        randomTrolls.put(41, "dropinv");
        randomTrolls.put(42, "anvil");
        randomTrolls.put(43, "invtext");
        randomTrolls.put(44, "runforrest");

        randomTrolls.put(45, "border");
        randomTrolls.put(46, "noob");
        randomTrolls.put(47, "shlong");

    }

    public boolean canBeTrolled(Player p) {

        if (p.isOp() && !getConfig().getBoolean("Troll-Operators")) {
            return false;
        }

        if (!p.isOp() && p.hasPermission("troll.bypass")) {
            return false;
        }
        return true;

    }

    public int createRandom(int lower, int upper) {

        final Random r = new Random();

        return r.nextInt((upper - lower) + 1) + lower;
    }

    public ItemStack createGuiItem(int amount, Material mat, String displayName, String... lore) {

        ItemStack itemStack = new ItemStack(mat, amount);
        ItemMeta itemStackMeta = itemStack.getItemMeta();
        ArrayList<String> metaLore = new ArrayList<>();
        metaLore.add("");
        metaLore.addAll(Arrays.asList(lore));
        itemStackMeta.setLore(metaLore);
        itemStackMeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        itemStackMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemStackMeta);

        return itemStack;
    }

    public void openGui(Player p) {

        final Inventory gui = Bukkit.createInventory(null, 54, "§cTroll-Gui");

        final ItemStack close = createGuiItem(1, Material.EMERALD, "§bClose the gui", "§7Closes the Troll-Gui");
        final ItemStack badapple = createGuiItem(1, Material.APPLE, "§eBadapple", "§7Give the player an", "§7apple. If he eats it he will die.");
        final ItemStack bolt = createGuiItem(1, Material.FIRE_CHARGE, "§eBolt", "§7Strikes a lightning at", "§7the player's location.");
        final ItemStack boom = createGuiItem(1, Material.TNT, "§eBoom", "§7Creates an explosion at", "§7the player's location.");
        final ItemStack burn = createGuiItem(1, Material.LAVA_BUCKET, "§eBurn", "§7Sets the player on fire.");
        final ItemStack bury = createGuiItem(1, Material.DIRT, "§eBury", "§7Will bury the player underground.");
        final ItemStack crash = createGuiItem(1, Material.STRING, "§eCrash", "§7Kicks the player from", "§7the server with a fake crash message.");
        final ItemStack denymove = createGuiItem(1, Material.LEATHER_BOOTS, "§eDenymove", "§7Won't let the player move.");
        final ItemStack fakeop = createGuiItem(1, Material.DIAMOND, "§eFakeop", "§7Fake op's a player.");
        final ItemStack fakedeop = createGuiItem(1, Material.GOLD_INGOT, "§eFakedeop", "§7Fake deop's a player.");
        final ItemStack fakerestart = createGuiItem(1, Material.BLAZE_POWDER, "§eFakerestart", "§7Fake restarts the server.");
        final ItemStack freefall = createGuiItem(1, Material.WHITE_WOOL, "§eFreefall", "§7Lets a player freefall", "§7from a certain height.");
        final ItemStack freeze = createGuiItem(1, Material.ICE, "§eFreeze", "§7Freezes a player.");
        final ItemStack gokill = createGuiItem(1, Material.SOUL_SAND, "§eGokill", "§7Kills a player after a certain period.");
        final ItemStack hero = createGuiItem(1, Material.GLOWSTONE_DUST, "§eHerobrine", "§7Sets a player to Herobrine.");
        final ItemStack hurt = createGuiItem(1, Material.REDSTONE, "§eHurt", "§7Hurts a player.");
        final ItemStack infect = createGuiItem(1, Material.POTION, "§eInfect", "§7Infects a player.");
        final ItemStack launch = createGuiItem(1, Material.FIREWORK_ROCKET, "§eLaunch", "§7Launchs a player.");
        final ItemStack nomine = createGuiItem(1, Material.GRASS_BLOCK, "§eNomine", "§7Prevents a player from breaking blocks.");
        final ItemStack potato = createGuiItem(1, Material.BAKED_POTATO, "§ePotatotroll", "§7Replaces every item in a player's", "§7inventory with a potato.");
        final ItemStack pump = createGuiItem(1, Material.JACK_O_LANTERN, "§ePumpkinhead", "§7Sets the head of a player to a pumpkin.");
        final ItemStack push = createGuiItem(1, Material.FEATHER, "§ePush", "§7Pushes a player.");
        final ItemStack rnd = createGuiItem(1, Material.ENDER_PEARL, "§eRandomteleport", "§7Teleports a player randomly.");
        final ItemStack spam = createGuiItem(1, Material.OAK_SIGN, "§eSpam", "§7Spams a player.");
        final ItemStack special = createGuiItem(1, Material.CHEST, "§eSpecial", "§7To get the special.");
        final ItemStack starve = createGuiItem(1, Material.COOKED_CHICKEN, "§eStarve", "§7Starves a player.");
        final ItemStack tpt = createGuiItem(1, Material.FISHING_ROD, "§eTeleporttroll", "§7To get a special item, with which", "§7you can troll players.");
        final ItemStack trap = createGuiItem(1, Material.BEDROCK, "§eTrap", "§7Traps a player.");
        final ItemStack tkick = createGuiItem(1, Material.IRON_DOOR, "§eTrollkick", "§7To trollkick a player.");
        final ItemStack turn = createGuiItem(1, Material.PAPER, "§eTurn", "§7Turns a player around.");
        final ItemStack voidd = createGuiItem(1, Material.OBSIDIAN, "§eVoid", "§7Removes blocks under a player", "§7until he dies in the void.");
        final ItemStack web = createGuiItem(1, Material.COBWEB, "§eWebtrap", "§7Traps a player in cobweb.");
        final ItemStack spank = createGuiItem(1, Material.BONE, "§eSpank", "§7Spanks a player.");
        final ItemStack trample = createGuiItem(1, Material.COW_SPAWN_EGG, "§eTrample", "§7Lets cows trample on a player.");
        final ItemStack mute = createGuiItem(1, Material.LEVER, "§eStfu", "§7To mute a player.");
        final ItemStack popup = createGuiItem(1, Material.BOOK, "§ePopup", "§7Opens a player's inventory.");
        final ItemStack sky = createGuiItem(1, Material.GLASS, "§eSky", "§7Teleports a player to the sky.");
        final ItemStack ab = createGuiItem(1, Material.CLOCK, "§eAbduct", "§7Lets aliens abduct the player.");
        final ItemStack pop = createGuiItem(1, Material.EXPERIENCE_BOTTLE, "§ePopular", "§7Teleports all players to the player.");
        final ItemStack crp = createGuiItem(1, Material.CREEPER_SPAWN_EGG, "§eCreeper", "§7Spawns creepers at the player's location.");
        final ItemStack sp = createGuiItem(1, Material.ARROW, "§eSparta", "§7Shoots arrows at a player", "§7from different locations.");
        final ItemStack drug = createGuiItem(1, Material.WHEAT, "§eDrug", "§7Drugs a player.");
        final ItemStack rain = createGuiItem(1, Material.INK_SAC, "§eSquidrain", "§7Lets squids rain on a player.");
        final ItemStack dropinv = createGuiItem(1, Material.DROPPER, "§eDropinv", "§7Lets a player drop all of his items.");
        final ItemStack garbage = createGuiItem(1, Material.WRITABLE_BOOK, "§eGarbage", "§7To change a player's chat messages to garbage.");
        final ItemStack anvil = createGuiItem(1, Material.ANVIL, "§eAnvil", "§7Drops an anvil on a player.");
        final ItemStack invtext = createGuiItem(2, Material.PAPER, "§eInvtext", "§7Adds a text to a player's inventory with items.");
        final ItemStack rnfrst = createGuiItem(1, Material.IRON_BOOTS, "§eRunforrest", "§7Keeps a player moving for a minute,", "§7otherwise it kills him.");
        final ItemStack tbows = createGuiItem(1, Material.BOW, "§eTrollbows", "§7Choose between 4 different trollbows.");
        final ItemStack border = createGuiItem(1, Material.DEAD_BUSH, "§eBorder", "§7Teleports a player to the world's border.");
        final ItemStack noob = createGuiItem(1, Material.VINE, "§eNoob", "§7Noobs a player.");
        final ItemStack shlong = createGuiItem(1, Material.PINK_TULIP, "§eShlong", "§7Shlongs a player 8=D.");

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
        gui.setItem(50, shlong);

        gui.setItem(53, close);

        p.openInventory(gui);

    }

    public void closeGui(Player p) {

        p.getOpenInventory().close();
        p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);

    }

    public void openChoseWindow(Player p) {

        final Inventory inv = Bukkit.createInventory(null, 9, "§cChoose the special");

        final ItemStack one = createGuiItem(1, Material.EMERALD, "§7#1 §eAk-47", "§7To get the AK-47.");
        final ItemStack two = createGuiItem(2, Material.EMERALD, "§7#2 §eBlock Shooter", "§7To get the Block Shooter.");
        final ItemStack back = createGuiItem(1, Material.IRON_DOOR, "§bReturn to the Troll-Gui", "§7To return to the Troll-Gui.");

        final ItemStack none = new ItemStack(Material.GLASS_PANE, 1);
        final ItemMeta nonemeta = none.getItemMeta();
        nonemeta.setDisplayName("§5");
        none.setItemMeta(nonemeta);

        inv.setItem(0, one);
        inv.setItem(1, two);
        inv.setItem(8, back);

        inv.setItem(2, none);
        inv.setItem(3, none);
        inv.setItem(4, none);
        inv.setItem(5, none);
        inv.setItem(6, none);
        inv.setItem(7, none);

        p.openInventory(inv);

    }

    public void openBowWindow(Player p) {

        final Inventory inv = Bukkit.createInventory(null, 9, "§cChoose a Troll-Bow");

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

        final ItemStack b5 = new ItemStack(Material.BOW);
        final ItemMeta b5meta = b5.getItemMeta();
        b5meta.setDisplayName("§eGet all bows at once");
        b5meta.addEnchant(Enchantment.INFINITY, 1, true);
        b5meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        final ArrayList<String> b5metalore = new ArrayList<>();
        b5metalore.add("§7Adds every Troll-Bow to your inventory.");
        b5meta.setLore(b5metalore);
        b5.setItemMeta(b5meta);

        final ItemStack back = new ItemStack(Material.IRON_DOOR);
        final ItemMeta backmeta = back.getItemMeta();
        backmeta.setDisplayName("§bReturn to the Troll-Gui");
        final ArrayList<String> backmetalore = new ArrayList<>();
        backmetalore.add("§7To return to the Troll-Gui.");
        backmeta.setLore(backmetalore);
        back.setItemMeta(backmeta);

        final ItemStack none = new ItemStack(Material.GLASS_PANE, 1);
        final ItemMeta nonemeta = none.getItemMeta();
        nonemeta.setDisplayName("§5");
        none.setItemMeta(nonemeta);

        inv.setItem(0, b1);
        inv.setItem(1, b2);
        inv.setItem(2, b3);
        inv.setItem(3, b4);
        inv.setItem(7, b5);
        inv.setItem(8, back);

        inv.setItem(4, none);
        inv.setItem(5, none);
        inv.setItem(6, none);

        p.openInventory(inv);

    }

    public boolean isInventoryEmpty(Player p) {

        for (ItemStack item : p.getInventory().getContents()) {

            if (item != null) {

                return false;

            }

        }

        return true;

    }

    @SuppressWarnings("deprecation")
    public void spawnCow(Player p) {

        final Random r = new Random();
        final int ran = r.nextInt(this.color.size());

        final Location ploc = p.getLocation();

        final Silverfish fish = (Silverfish) p.getWorld().spawnEntity(p.getLocation(), EntityType.SILVERFISH);
        fish.addPotionEffects(List.of(new PotionEffect(PotionEffectType.INVISIBILITY, 10000000, 3),
                new PotionEffect(PotionEffectType.SPEED, 10000000, 3),
                new PotionEffect(Objects.requireNonNull(Registry.EFFECT.get(NamespacedKey.minecraft("strength"))), 10000000, 3)));
        fish.setAggressive(true);
        fish.setTarget(p);

        final Cow cow = (Cow) p.getWorld().spawnEntity(ploc, EntityType.COW);

        final Silverfish fish2 = (Silverfish) p.getWorld().spawnEntity(p.getLocation(), EntityType.SILVERFISH);
        fish2.addPotionEffects(List.of(new PotionEffect(PotionEffectType.INVISIBILITY, 10000000, 3),
                new PotionEffect(PotionEffectType.SPEED, 10000000, 3),
                new PotionEffect(Objects.requireNonNull(Registry.EFFECT.get(NamespacedKey.minecraft("strength"))), 10000000, 3)));
        fish2.setAggressive(true);
        fish2.setTarget(p);

        fish2.setCustomName(this.color.get(ran) + "Mad Cow");
        fish2.setCustomNameVisible(false);

        cow.addPassenger(fish2);
        fish.addPassenger(cow);

        cows.add(cow);
        cows.add(fish);
        cows.add(fish2);

    }

    public void removeCows() {

        cows.forEach(Entity::remove);
        cows.clear();

    }

    public int getTrolls() {

        if (getConfig().getString("Trolls") != null) {

            return getConfig().getInt("Trolls");

        } else {
            return 0;
        }
    }

    public void addTroll() {

        trollBuffer++;

    }

    private int total() {

        return trollBuffer + getTrolls();

    }

    private void saveTrolls() {

        if (getConfig().getString("Trolls") != null) {

            getConfig().set("Trolls", total());
            this.saveConfig();
            this.reloadConfig();
            trollBuffer = 0;

        }

    }

    public void openTrollInv(Player viewer) {

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

    public void getAllTo(Player trgt, Player ignore) {

        final Location loc = trgt.getLocation();

        Bukkit.getServer().getOnlinePlayers().stream().filter(all -> !all.getName().equals(ignore.getName()))
                .forEach(all -> all.teleport(loc));

    }

    public void spawnCreepers(Player player, Location location, int amount) {
        spawnCreeperWithName(player, location, amount, "Angry Creeper");
    }

    private void spawnCreeperWithName(Player player, Location location, int amount, String creeperName) {
        World world = player.getWorld();
        Random random = new Random();
        int randomIndex = random.nextInt(this.color.size());
        String customName = this.color.get(randomIndex) + creeperName;

        for (int i = 0; i < amount; i++) {
            Creeper creeper = (Creeper) world.spawnEntity(location, EntityType.CREEPER);
            creeper.setCustomName(customName);
            creeper.setCustomNameVisible(true);
            creeper.setTarget(player);
            creeper.setPowered(true);
            creeper.damage(1.0D, player);
            creeper.setRemoveWhenFarAway(true);
            creeper.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2, 99999999));
        }
    }

    public void spawnCreeperForBow(Location location, Player player) {
        spawnCreeper(location, player, "Angry Creeper");
    }

    private void spawnCreeper(Location location, Player player, String creeperName) {
        World world = player.getWorld();
        Creeper creeper = (Creeper) world.spawnEntity(location, EntityType.CREEPER);
        Random random = new Random();
        int randomIndex = random.nextInt(this.color.size());

        creeper.setCustomName(this.color.get(randomIndex) + creeperName);
        creeper.setCustomNameVisible(true);
        creeper.setPowered(true);
        creeper.setRemoveWhenFarAway(true);
        creeper.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 2, Integer.MAX_VALUE));
    }


    public int getRandom(int lower, int upper) {

        final Random r = new Random();

        return r.nextInt((upper - lower) + 1) + lower;

    }

    private void saveStats() {

        try {

            statscfg.save(stats);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void savegbmsgs() {

        try {

            gbmsgscfg.save(gbmsgs);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void check4File() {

        if (!(stats.exists())) {

            try {

                stats.createNewFile();
                addStatsDefaults();
                getLogger().info("Created stats file.");
                saveStats();
                getLogger().info("Saved stats file.");

            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {

            saveStats();

        }

    }

    private void check4otherFile() {

        if (!(gbmsgs.exists())) {

            try {

                gbmsgs.createNewFile();
                addGarbageDefaults();
                getLogger().info("Created GarbageMessages file.");
                savegbmsgs();
                getLogger().info("Saved GarbageMessages file.");

            } catch (IOException e) {

                e.printStackTrace();

            }

        } else {

            savegbmsgs();

        }

    }

    @SuppressWarnings("deprecation")
    private void addGarbageDefaults() {

        gbmsgscfg.options().header("You can add up to 100 messages if you want");

        gbmsgscfg.addDefault("Messages.1", "I am such an idiot, unbelievable!");
        gbmsgscfg.addDefault("Messages.2", "I just hate humans");
        gbmsgscfg.addDefault("Messages.3", "Can you all please be quiet? I have to meditate!");
        gbmsgscfg.addDefault("Messages.4", "My mother should have aborted me..");
        gbmsgscfg.addDefault("Messages.5", "Hey girls, want to meet? ;)");
        gbmsgscfg.addDefault("Messages.6", "A doctor tells a woman she can no longer touch anything alcoholic. So she gets a divorce.");
        gbmsgscfg.addDefault("Messages.7", "What's the difference between men and pigs? Pigs don't turn into men when they drink.");
        gbmsgscfg.addDefault("Messages.8", "I have noticed that everyone who is for abortion, has already been born.");
        gbmsgscfg.addDefault("Messages.9", "The best mathematical equation I have ever seen: 1 cross + 3 nails = 4 given.");
        gbmsgscfg.addDefault("Messages.10", "We can't help everyone, but everyone can help someone.");
        gbmsgscfg.addDefault("Messages.11", "Ok, I admit that I have a foot fetish..");
        gbmsgscfg.addDefault("Messages.12", "I still play with barbies");
        gbmsgscfg.addDefault("Messages.13", "Sometimes I just cry without a reason :(");
        gbmsgscfg.addDefault("Messages.14", "Don't tell anyone.. but my aunt is very sexy *_*");
        gbmsgscfg.addDefault("Messages.15", "My favorite color is toast");
        gbmsgscfg.addDefault("Messages.16", "Don't tell anyone.. but my girlfriend ain't a girl.....");
        gbmsgscfg.addDefault("Messages.17", "Who wants to fight me? Come on, don't be shy!!");
        gbmsgscfg.addDefault("Messages.18", "I am 40 years old and from somalia");
        gbmsgscfg.addDefault("Messages.19", "I am naked at the moment");
        gbmsgscfg.addDefault("Messages.20", "Can someone kill me please?");
        gbmsgscfg.addDefault("Messages.21", "I love pet wussies");
        gbmsgscfg.addDefault("Messages.22", "Call me please I'm desperate");
        gbmsgscfg.addDefault("Messages.23", "The future, the present and the past walked into a bar. Things got a little tense..");
        gbmsgscfg.addDefault("Messages.24", "My doctors office has two doctors on call at all times. Is that considered a pair a docs?");
        gbmsgscfg.addDefault("Messages.25", "Q: What do you call the security outside of a Samsung Store? A: Guardians of the Galaxy.");
        gbmsgscfg.addDefault("Messages.26", "This message shouldn't exist...");

        gbmsgscfg.options().copyDefaults(true);

    }

    private int getAmountOfGarbageMessages() {

        int amount = 0;

        for (int i = 0; i < 100; i++) {

            if (this.gbmsgscfg.get("Messages." + i) != null) {

                amount++;

            } else {

                amount += 0;

            }

        }

        return amount;

    }

    private void addStatsDefaults() {

        statscfg.addDefault("Troll.Burn", 0);
        statscfg.addDefault("Troll.Freeze", 0);
        statscfg.addDefault("Troll.Bolt", 0);
        statscfg.addDefault("Troll.Special", 0);
        statscfg.addDefault("Troll.Boom", 0);
        statscfg.addDefault("Troll.Push", 0);
        statscfg.addDefault("Troll.Fakeop", 0);
        statscfg.addDefault("Troll.Fakedeop", 0);
        statscfg.addDefault("Troll.Launch", 0);
        statscfg.addDefault("Troll.Spam", 0);
        statscfg.addDefault("Troll.Gokill", 0);
        statscfg.addDefault("Troll.Switch", 0);
        statscfg.addDefault("Troll.Trollkick", 0);
        statscfg.addDefault("Troll.Badapple", 0);
        statscfg.addDefault("Troll.Potatotroll", 0);
        statscfg.addDefault("Troll.Trap", 0);
        statscfg.addDefault("Troll.Teleporttroll", 0);
        statscfg.addDefault("Troll.Infect", 0);
        statscfg.addDefault("Troll.Herobrine", 0);
        statscfg.addDefault("Troll.Fakerestart", 0);
        statscfg.addDefault("Troll.Turn", 0);
        statscfg.addDefault("Troll.Starve", 0);
        statscfg.addDefault("Troll.Hurt", 0);
        statscfg.addDefault("Troll.Void", 0);
        statscfg.addDefault("Troll.Pumpkinhead", 0);
        statscfg.addDefault("Troll.Bury", 0);
        statscfg.addDefault("Troll.Nomine", 0);
        statscfg.addDefault("Troll.Randomteleport", 0);
        statscfg.addDefault("Troll.Crash", 0);
        statscfg.addDefault("Troll.Freefall", 0);
        statscfg.addDefault("Troll.Webtrap", 0);
        statscfg.addDefault("Troll.Spank", 0);
        statscfg.addDefault("Troll.Trample", 0);
        statscfg.addDefault("Troll.Stfu", 0);
        statscfg.addDefault("Troll.Popup", 0);
        statscfg.addDefault("Troll.Sky", 0);
        statscfg.addDefault("Troll.Abduct", 0);
        statscfg.addDefault("Troll.Popular", 0);
        statscfg.addDefault("Troll.Creeper", 0);
        statscfg.addDefault("Troll.Sparta", 0);
        statscfg.addDefault("Troll.Trollbows", 0);
        statscfg.addDefault("Troll.Drug", 0);
        statscfg.addDefault("Troll.Squidrain", 0);
        statscfg.addDefault("Troll.Dropinv", 0);
        statscfg.addDefault("Troll.Garbage", 0);
        statscfg.addDefault("Troll.Anvil", 0);
        statscfg.addDefault("Troll.Invtext", 0);
        statscfg.addDefault("Troll.Runforrest", 0);
        statscfg.addDefault("Troll.Border", 0);
        statscfg.addDefault("Troll.Noob", 0);
        statscfg.addDefault("Troll.Randomtroll", 0);
        statscfg.addDefault("Troll.Shlong", 0);
        statscfg.addDefault("Troll.Denymove", 0);

        statscfg.addDefault("Troll.Bows.Bolt", 0);
        statscfg.addDefault("Troll.Bows.Boom", 0);
        statscfg.addDefault("Troll.Bows.Creeper", 0);
        statscfg.addDefault("Troll.Bows.Pull", 0);


        statscfg.addDefault("LastUsed.Burn", "Nobody");
        statscfg.addDefault("LastUsed.Freeze", "Nobody");
        statscfg.addDefault("LastUsed.Bolt", "Nobody");
        statscfg.addDefault("LastUsed.Special", "Nobody");
        statscfg.addDefault("LastUsed.Boom", "Nobody");
        statscfg.addDefault("LastUsed.Push", "Nobody");
        statscfg.addDefault("LastUsed.Fakeop", "Nobody");
        statscfg.addDefault("LastUsed.Fakedeop", "Nobody");
        statscfg.addDefault("LastUsed.Launch", "Nobody");
        statscfg.addDefault("LastUsed.Spam", "Nobody");
        statscfg.addDefault("LastUsed.Gokill", "Nobody");
        statscfg.addDefault("LastUsed.Switch", "Nobody");
        statscfg.addDefault("LastUsed.Trollkick", "Nobody");
        statscfg.addDefault("LastUsed.Badapple", "Nobody");
        statscfg.addDefault("LastUsed.Potatotroll", "Nobody");
        statscfg.addDefault("LastUsed.Trap", "Nobody");
        statscfg.addDefault("LastUsed.Tptroll", "Nobody");
        statscfg.addDefault("LastUsed.Infect", "Nobody");
        statscfg.addDefault("LastUsed.Herobrine", "Nobody");
        statscfg.addDefault("LastUsed.Fakerestart", "Nobody");
        statscfg.addDefault("LastUsed.Turn", "Nobody");
        statscfg.addDefault("LastUsed.Starve", "Nobody");
        statscfg.addDefault("LastUsed.Teleporttroll", "Nobody");
        statscfg.addDefault("LastUsed.Hurt", "Nobody");
        statscfg.addDefault("LastUsed.Void", "Nobody");
        statscfg.addDefault("LastUsed.Pumpkinhead", "Nobody");
        statscfg.addDefault("LastUsed.Bury", "Nobody");
        statscfg.addDefault("LastUsed.Nomine", "Nobody");
        statscfg.addDefault("LastUsed.Randomteleport", "Nobody");
        statscfg.addDefault("LastUsed.Crash", "Nobody");
        statscfg.addDefault("LastUsed.Freefall", "Nobody");
        statscfg.addDefault("LastUsed.Webtrap", "Nobody");
        statscfg.addDefault("LastUsed.Spank", "Nobody");
        statscfg.addDefault("LastUsed.Trample", "Nobody");
        statscfg.addDefault("LastUsed.Stfu", "Nobody");
        statscfg.addDefault("LastUsed.Popup", "Nobody");
        statscfg.addDefault("LastUsed.Sky", "Nobody");
        statscfg.addDefault("LastUsed.Abduct", "Nobody");
        statscfg.addDefault("LastUsed.Popular", "Nobody");
        statscfg.addDefault("LastUsed.Creeper", "Nobody");
        statscfg.addDefault("LastUsed.Sparta", "Nobody");
        statscfg.addDefault("LastUsed.Trollbows", "Nobody");
        statscfg.addDefault("LastUsed.Drug", "Nobody");
        statscfg.addDefault("LastUsed.Squidrain", "Nobody");
        statscfg.addDefault("LastUsed.Dropinv", "Nobody");
        statscfg.addDefault("LastUsed.Garbage", "Nobody");
        statscfg.addDefault("LastUsed.Anvil", "Nobody");
        statscfg.addDefault("LastUsed.Invtext", "Nobody");
        statscfg.addDefault("LastUsed.Runforrest", "Nobody");
        statscfg.addDefault("LastUsed.Border", "Nobody");
        statscfg.addDefault("LastUsed.Noob", "Nobody");
        statscfg.addDefault("LastUsed.Randomtroll", "Nobody");
        statscfg.addDefault("LastUsed.Shlong", "Nobody");
        statscfg.addDefault("LastUsed.Denymove", "Nobody");

        statscfg.options().copyDefaults(true);

    }

    public void updateLastUsedUser(Player p, String troll) {

        statscfg.set("LastUsed." + troll, p.getName());
        saveStats();

    }

    private String getLastUsedUser(String troll) {
        return statscfg.getString("LastUsed." + troll);
    }

    private int getStats(String cmd) {

        return statscfg.getInt("Troll." + cmd);

    }

    public void addStats(String cmd, Player lastused) {

        statscfg.set("Troll." + cmd, getStats(cmd) + 1);
        saveStats();
        updateLastUsedUser(lastused, cmd);

    }

    private int getBowStats(String bow) {

        return statscfg.getInt("Troll.Bows." + bow);

    }

    public int getAllBowStats() {

        final int bow1 = statscfg.getInt("Troll.Bows.Bolt");
        final int bow2 = statscfg.getInt("Troll.Bows.Boom");
        final int bow3 = statscfg.getInt("Troll.Bows.Creeper");
        final int bow4 = statscfg.getInt("Troll.Bows.Pull");

        return bow1 + bow2 + bow3 + bow4;

    }

    public void addBowStats(String bow) {

        statscfg.set("Troll.Bows." + bow, getBowStats(bow) + 1);
        saveStats();

    }

    public void startTrollTutorial(Player p) {

        this.tutorialNum.put(p.getUniqueId(), 0);

        tutorialTask = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {

            tutorialNum.put(p.getUniqueId(), tutorialNum.get(p.getUniqueId()) + 1);

            if (tutorialNum.get(p.getUniqueId()) == 1) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6In this tutorial you will learn everything you need to know about this plugin!");

            } else if (tutorialNum.get(p.getUniqueId()) == 10) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Let's start with the basic command of this plugin: §4/troll");

            } else if (tutorialNum.get(p.getUniqueId()) == 15) {

                p.sendMessage("");
                p.sendMessage(
                        StringManager.PREFIX + "§6When typing §4/troll §6an inventory opens, where you can see heads");
                p.sendMessage(StringManager.PREFIX + "§6of players (if there are any online)");

            } else if (tutorialNum.get(p.getUniqueId()) == 23) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6The inventory looks like this:");
                this.usable.add(p.getUniqueId());

            } else if (tutorialNum.get(p.getUniqueId()) == 27) {

                this.openTrollInv(p);

            } else if (tutorialNum.get(p.getUniqueId()) == 31) {

                p.sendMessage("");
                p.getOpenInventory().close();
                this.usable.remove(p.getUniqueId());
                p.sendMessage(StringManager.PREFIX + "§6You can choose a player by clicking on his head");

            } else if (tutorialNum.get(p.getUniqueId()) == 36) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6After you clicked on his head another inventory will be opened");
                p.sendMessage(StringManager.PREFIX + "§6The Troll-GUI!");

            } else if (tutorialNum.get(p.getUniqueId()) == 43) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6The Troll-GUI looks like this:");
                this.usable.add(p.getUniqueId());

            } else if (tutorialNum.get(p.getUniqueId()) == 47) {

                this.openGui(p);

            } else if (tutorialNum.get(p.getUniqueId()) == 51) {

                p.sendMessage("");
                p.getOpenInventory().close();
                this.usable.remove(p.getUniqueId());
                p.sendMessage(StringManager.PREFIX + "§6In the Troll-GUI you can see a lot of different items");
                p.sendMessage(StringManager.PREFIX + "§6Each item starts a different troll for the player you selected before");

            } else if (tutorialNum.get(p.getUniqueId()) == 58) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6If you want to speed things up a bit you can use the command §4/troll [player]");
                p.sendMessage(StringManager.PREFIX + "§6With this command the Troll-GUI opens immediately, the selected player is the");
                p.sendMessage(StringManager.PREFIX + "§6argument after the command");

            } else if (tutorialNum.get(p.getUniqueId()) == 69) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6If you want to speed up things even more you can just type the command");
                p.sendMessage(StringManager.PREFIX + "§6To get a list of all commands of this plugin type §4/troll help [1-5]");
                p.sendMessage(StringManager.PREFIX + "§6There are 6 help pages, so for example you can type §4/troll help 3");

            } else if (tutorialNum.get(p.getUniqueId()) == 82) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6So how about bypassing trolls?");

            } else if (tutorialNum.get(p.getUniqueId()) == 85) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Users with the permission §4troll.bypass §6can bypass every troll");
                p.sendMessage(StringManager.PREFIX + "§6They are just not trollable!");

            } else if (tutorialNum.get(p.getUniqueId()) == 90) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6But wait... operators have all permission, this means they also have the bypass permission");
                p.sendMessage(StringManager.PREFIX + "§6Does this mean that I cannot troll operators?");

            } else if (tutorialNum.get(p.getUniqueId()) == 98) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6The answer is: §4You can troll operators!");
                p.sendMessage(StringManager.PREFIX + "§6With the command §4/trollop [true | false] §6you can decide");
                p.sendMessage(StringManager.PREFIX + "§6whether operators can be trolled or not, isn't that great? :)");

            } else if (tutorialNum.get(p.getUniqueId()) == 110) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6You might ask yourself \"How server friendly is this plugin?\"");
                p.sendMessage(StringManager.PREFIX + "§6Do the trolls destroy the world and some stuff I built?");

            } else if (tutorialNum.get(p.getUniqueId()) == 118) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6The answer is: §4No!");
                p.sendMessage(StringManager.PREFIX + "§6The plugin is very server friendly, it doesn't destroy any");
                p.sendMessage(StringManager.PREFIX + "§6blocks at all, and if in a command blocks get placed they will");
                p.sendMessage(StringManager.PREFIX + "§6be removed again and the old blocks at the locations will be reset!");
                p.sendMessage(StringManager.PREFIX + "§6So don't worry about this! :)");

            } else if (tutorialNum.get(p.getUniqueId()) == 135) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Alright, but is there a way to see how many times I used a certain troll?");

            } else if (tutorialNum.get(p.getUniqueId()) == 139) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6The answer is: §4Yes!");
                p.sendMessage(StringManager.PREFIX + "§6With the command §4/troll statistics §6you get statistics about every");
                p.sendMessage(StringManager.PREFIX + "§6single troll command you used!");

            } else if (tutorialNum.get(p.getUniqueId()) == 147) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Is there anything else I should know?");

            } else if (tutorialNum.get(p.getUniqueId()) == 150) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Yes! There are some special commands, like");
                p.sendMessage(StringManager.PREFIX + "§4/special [1-2] §6or §4/trollbows");

            } else if (tutorialNum.get(p.getUniqueId()) == 154) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6What this commands do? Well, just try them out! :)");

            } else if (tutorialNum.get(p.getUniqueId()) == 158) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6That's it for now, this tutorial might get edited in the future");

            } else if (tutorialNum.get(p.getUniqueId()) == 165) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§4Important! §6If you find any bugs please report them on the site");
                p.sendMessage(StringManager.PREFIX + "§6you downloaded this plugin, this helps to improve it!");

            } else if (tutorialNum.get(p.getUniqueId()) == 170) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Finishing tutorial...");

            } else if (tutorialNum.get(p.getUniqueId()) == 172) {

                p.sendMessage("");
                p.sendMessage(StringManager.PREFIX + "§6Tutorial finished!");
                p.sendMessage("");
                tutorialNum.remove(p.getUniqueId());
                this.usable.remove(p.getUniqueId());
                Bukkit.getScheduler().cancelTask(tutorialTask);

            }

        }, 50L, 20L);

        taskID.put(p.getUniqueId(), tutorialTask);

    }

    public void dropArmor(Player p) {

        final Location loc = p.getLocation().clone();

        for (ItemStack clothes : p.getEquipment().getArmorContents()) {
            if (clothes != null) {

                loc.getWorld().dropItemNaturally(loc, clothes.clone());

            }

        }

        p.getEquipment().clear();
        p.updateInventory();

    }

    public void dropItems(Player p) {

        final Location loc = p.getLocation().clone();
        final Inventory inv = p.getInventory();

        for (ItemStack stuff : inv.getContents()) {

            if (stuff != null) {

                loc.getWorld().dropItemNaturally(loc, stuff.clone());

            }

        }

        p.getInventory().clear();
        p.updateInventory();

    }

    /**
     * Generates a random garbage message by selecting an index from the available garbage messages
     * configuration. If no message is found for the selected index, a default error message is returned.
     *
     * @return a random garbage message as a {@code String}, or a default error message if none is found
     */
    public String randomGarbageMessage() {
        Random randomGenerator = new Random();
        int randomMessageIndex = randomGenerator.nextInt(getAmountOfGarbageMessages()) + 1;

        return getGarbageMessage(randomMessageIndex)
                .orElse("There's an error in the GarbageMessages file. Please tell the author of the plugin!");
    }


    /**
     * Retrieves a garbage message from a configuration file based on the provided index.
     *
     * @param index the index of the garbage message to retrieve from the configuration
     * @return an {@code Optional<String>} containing the garbage message if it exists,
     * or an empty {@code Optional} if no message is found for the specified index
     */
    private Optional<String> getGarbageMessage(int index) {
        String garbageMessage = this.gbmsgscfg.getString("Messages." + index);
        return Optional.ofNullable(garbageMessage);
    }


    /**
     * Launches a player upward by modifying their velocity.
     *
     * @param p the player to be launched; their vertical velocity will be increased.
     */
    public void launchPlayer(Player p) {

        p.setVelocity(p.getVelocity().setY(3));

    }

    public void removeFromRunIt(Player p) {

        if (this.runIt.contains(p.getUniqueId())) {

            Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> TrollBoss.this.runIt.remove(p.getUniqueId()), 80L);

        }

    }

    private void addInventoryTextItem(Player p, int inventorySlot, String text, int amount) {

        final Random x = new Random();
        final int a = x.nextInt(TrollBoss.this.color.size());

        final ItemStack dirt1 = new ItemStack(Material.DIRT, amount);
        final ItemMeta dirt1meta = dirt1.getItemMeta();
        dirt1meta.setDisplayName(this.color.get(a) + text);
        dirt1.setItemMeta(dirt1meta);

        p.getInventory().setItem(inventorySlot, dirt1);

    }

    public void addAllInventoryTextItems(Player p) {

        p.getInventory().clear();

        // Line 1
        addInventoryTextItem(p, 9, "Hello,", 1);
        addInventoryTextItem(p, 10, "I", 2);
        addInventoryTextItem(p, 11, "hope", 3);
        addInventoryTextItem(p, 12, "you", 4);
        addInventoryTextItem(p, 13, "can", 5);
        addInventoryTextItem(p, 14, "need", 6);
        addInventoryTextItem(p, 15, "some", 7);
        addInventoryTextItem(p, 16, "fresh", 8);
        addInventoryTextItem(p, 17, "dirt..", 9);
        // Line 2
        addInventoryTextItem(p, 18, "if", 10);
        addInventoryTextItem(p, 19, "not...", 11);
        addInventoryTextItem(p, 20, "well", 12);
        addInventoryTextItem(p, 21, "it's", 13);
        addInventoryTextItem(p, 22, "too", 14);
        addInventoryTextItem(p, 23, "late", 15);
        addInventoryTextItem(p, 24, "anyway..", 16);
        addInventoryTextItem(p, 25, "old", 17);
        addInventoryTextItem(p, 26, "items", 18);
        // Line 3
        addInventoryTextItem(p, 27, "are", 19);
        addInventoryTextItem(p, 28, "gone", 20);
        addInventoryTextItem(p, 29, "for", 21);
        addInventoryTextItem(p, 30, "ever..", 22);
        addInventoryTextItem(p, 31, "hehe", 23);
        addInventoryTextItem(p, 32, "see", 24);
        addInventoryTextItem(p, 33, "you..", 25);
        addInventoryTextItem(p, 34, "have", 26);
        addInventoryTextItem(p, 35, "fun!", 27);

    }


    /**
     * Cancels a scheduled task associated with the specified player and removes it from the task map.
     *
     * @param task a {@code HashMap<String, Integer>} mapping player names to their respective task IDs
     * @param p    the {@code Player} whose task is to be canceled
     */
    private void cancelTask(HashMap<String, Integer> task, Player p) {

        if (!task.containsKey(p.getName())) {
            return;
        }
        final int tid = task.get(p.getName());
        Bukkit.getScheduler().cancelTask(tid);
        task.remove(p.getName());

    }

    private void editRFTime(Player p) {

        if (this.rftime.containsKey(p.getName())) {

            this.rftime.replace(p.getName(), this.rftime.get(p.getName()) - 1);

        }

    }

    private int getRFTime(Player p) {

        return this.rftime.getOrDefault(p.getName(), 0);

    }

    private void editWarnTime(Player p) {

        if (this.warnTime.containsKey(p.getName())) {

            this.warnTime.replace(p.getName(), this.warnTime.get(p.getName()) - 1);

        }

    }

    private int getWarnTime(Player p) {

        return this.warnTime.getOrDefault(p.getName(), 0);
    }

    public void start5SecRunTimer(Player player) {

        this.warnTime.put(player.getName(), 11);
        sendInitialMessages(player);

        int taskId = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> handleWarningTimer(player), 0L, 20L);
        fiveSecondTimerTask.put(player.getName(), taskId);
    }

    private void sendInitialMessages(Player player) {
        player.sendMessage(String.format(StringManager.ENGLISH_INITIAL_MSG, getRFTime(player)));
        player.sendMessage(StringManager.ENGLISH_DEATH_MSG);

    }

    private void handleWarningTimer(Player player) {
        editWarnTime(player);
        int warnTime = getWarnTime(player);

        if (warnTime > 0) {
            player.sendMessage(String.format(StringManager.ENGLISH_WARNING_MSG, warnTime));
        } else {
            player.sendMessage(StringManager.ENGLISH_WARNING_FINAL);

            this.warnTime.remove(player.getName());
            this.rf.put(player.getName(), true);
            start60SekRunTimer(player);
            cancelTask(fiveSecondTimerTask, player);
        }
    }


    private void check4movement(Player p) {

        if (!rf.containsKey(p.getName())) {
            return;
        }
        final int checki = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {

            if (TrollBoss.this.rfloc.containsKey(p.getName())) {
                if (TrollBoss.this.rfloc.get(p.getName()).equals(p.getLocation())) {

                    p.sendMessage("§7[§4INFO§7] §cAll you had to do was to damn move CJ!");

                    TrollBoss.this.rf.replace(p.getName(), false);
                    TrollBoss.this.deadMessage.add(p.getUniqueId());
                    TrollBoss.this.cancelTask(tasks2, p);

                    final String trollername = TrollBoss.this.rfmsg.get(p.getName());
                    final Player troller = Bukkit.getPlayer(trollername);

                    if (troller != null) {

                        troller.sendMessage(StringManager.PREFIX + "§eSucessfully trolled §7" + p.getName() + "§e!");
                        TrollBoss.this.rfmsg.remove(p.getName());

                    }

                } else {

                    rf.replace(p.getName(), true);

                }

            }
        }, 40L, 20L);
        tasks2.put(p.getName(), checki);

    }

    private void start60SekRunTimer(Player p) {

        rfloc.put(p.getName(), p.getLocation());
        check4movement(p);

        final int m2 = Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {

            if (getRFTime(p) > 0 && rf.get(p.getName())) {

                editRFTime(p);
                rfloc.put(p.getName(), p.getLocation());

                p.setLevel(getRFTime(p));

            } else if (getRFTime(p) > 0 && !rf.get(p.getName())) {

                rftime.remove(p.getName());
                rf.remove(p.getName());
                rfloc.remove(p.getName());
                p.setLevel(0);
                p.setHealth(0D);
                cancelTask(sixtySecondTimerTask, p);

            } else if (getRFTime(p) == 0 && rf.get(p.getName())) {

                rf.remove(p.getName());
                rftime.remove(p.getName());
                rfloc.remove(p.getName());
                p.setLevel(0);
                cancelTask(sixtySecondTimerTask, p);

                final String targetname = rfmsg.get(p.getName());
                final Player troller = Bukkit.getPlayer(targetname);

                p.sendMessage("§7[§4INFO§7] §aYou survived!");

                if (troller != null) {

                    troller.sendMessage(StringManager.PREFIX + "§ePlayer §7" + p.getName() + " §esurvived the troll!");
                    rfmsg.remove(p.getName());

                }

            }

        }, 1L, 20L);

        sixtySecondTimerTask.put(p.getName(), m2);

    }

    public void teleportToBorder(Player p) {

        final Location ploc = p.getLocation();

        final int x = 29999983;
        final int z = 29999983;
        final int y = ploc.getWorld().getHighestBlockYAt(x, z);

        final Location bloc = new Location(p.getWorld(), x, y, z, p.getLocation().getPitch(), p.getLocation().getYaw());
        p.teleport(bloc);

    }

    public void noobIt(Player p) {

        final Location ploc1 = p.getLocation();
        final Location to = new Location(p.getWorld(), ploc1.getX(), 220, ploc1.getZ());
        to.setYaw(180);
        final Location under = new Location(p.getWorld(), ploc1.getX(), 219, ploc1.getZ());
        glassloc.add(under);

        ploc1.getWorld().getBlockAt(under).setType(Material.GLASS);
        p.teleport(to);
        final Location ploc = p.getLocation();

        // CREATE

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {

            // N
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 0, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 1, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 2, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 3, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 4, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 5, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 6, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 0, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 1, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 2, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 3, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 4, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 5, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 6, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-10, 5, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-9, 2, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-9, 3, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-9, 4, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-8, 1, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 0, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 1, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 2, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 3, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 4, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 5, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 6, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 0, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 1, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 2, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 3, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 4, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 5, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 6, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-10, 5, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-9, 2, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-9, 3, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-9, 4, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-8, 1, -34)).setType(Material.GLOWSTONE);

        }, 40L);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {

            // O 1
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 1, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 2, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 3, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 4, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 5, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-4, 0, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-3, 0, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-2, 0, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-4, 6, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-3, 6, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-2, 6, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 1, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 2, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 3, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 4, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 5, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 1, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 2, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 3, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 4, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 5, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-4, 0, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-3, 0, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-2, 0, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-4, 6, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-3, 6, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-2, 6, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 1, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 2, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 3, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 4, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 5, -34)).setType(Material.GLOWSTONE);

        }, 80L);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {

            // O 2
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 1, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 2, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 3, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 4, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 5, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(2, 0, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(3, 0, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(4, 0, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(2, 6, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(3, 6, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(4, 6, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 1, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 2, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 3, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 4, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 5, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 1, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 2, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 3, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 4, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 5, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(2, 0, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(3, 0, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(4, 0, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(2, 6, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(3, 6, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(4, 6, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 1, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 2, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 3, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 4, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 5, -34)).setType(Material.GLOWSTONE);

        }, 120L);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {

            // B
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 0, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 1, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 2, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 3, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 4, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 5, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 6, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(8, 0, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(9, 0, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(10, 0, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(8, 6, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(9, 6, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(10, 6, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(8, 3, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(9, 3, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(10, 3, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 1, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 2, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 4, -33)).setType(Material.QUARTZ_BLOCK);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 5, -33)).setType(Material.QUARTZ_BLOCK);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 0, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 1, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 2, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 3, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 4, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 5, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 6, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(8, 0, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(9, 0, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(10, 0, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(8, 6, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(9, 6, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(10, 6, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(8, 3, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(9, 3, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(10, 3, -34)).setType(Material.GLOWSTONE);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 1, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 2, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 4, -34)).setType(Material.GLOWSTONE);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 5, -34)).setType(Material.GLOWSTONE);

        }, 160L);

        // REMOVE

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {

            // N
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 0, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 1, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 2, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 3, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 4, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 5, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 6, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 0, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 1, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 2, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 3, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 4, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 5, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 6, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-10, 5, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-9, 2, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-9, 3, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-9, 4, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-8, 1, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 0, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 1, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 2, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 3, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 4, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 5, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-11, 6, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 0, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 1, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 2, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 3, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 4, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 5, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-7, 6, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-10, 5, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-9, 2, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-9, 3, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-9, 4, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-8, 1, -34)).setType(Material.AIR);

        }, 240L);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {

            // O 1
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 1, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 2, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 3, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 4, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 5, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-4, 0, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-3, 0, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-2, 0, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-4, 6, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-3, 6, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-2, 6, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 1, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 2, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 3, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 4, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 5, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 1, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 2, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 3, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 4, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-5, 5, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-4, 0, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-3, 0, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-2, 0, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-4, 6, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-3, 6, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-2, 6, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 1, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 2, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 3, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 4, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(-1, 5, -34)).setType(Material.AIR);

        }, 250L);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {

            // O 2
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 1, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 2, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 3, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 4, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 5, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(2, 0, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(3, 0, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(4, 0, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(2, 6, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(3, 6, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(4, 6, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 1, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 2, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 3, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 4, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 5, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 1, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 2, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 3, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 4, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(1, 5, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(2, 0, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(3, 0, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(4, 0, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(2, 6, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(3, 6, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(4, 6, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 1, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 2, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 3, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 4, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(5, 5, -34)).setType(Material.AIR);

        }, 260L);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {

            // B
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 0, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 1, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 2, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 3, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 4, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 5, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 6, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(8, 0, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(9, 0, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(10, 0, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(8, 6, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(9, 6, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(10, 6, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(8, 3, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(9, 3, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(10, 3, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 1, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 2, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 4, -33)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 5, -33)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 0, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 1, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 2, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 3, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 4, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 5, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(7, 6, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(8, 0, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(9, 0, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(10, 0, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(8, 6, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(9, 6, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(10, 6, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(8, 3, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(9, 3, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(10, 3, -34)).setType(Material.AIR);

            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 1, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 2, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 4, -34)).setType(Material.AIR);
            p.getLocation().getWorld().getBlockAt(ploc.clone().add(11, 5, -34)).setType(Material.AIR);

        }, 270L);

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {

            p.teleport(beforeNoob.get(p.getName()));
            beforeNoob.remove(p.getName());
            p.getLocation().getWorld().getBlockAt(glassloc.get(0)).setType(Material.AIR);
            glassloc.clear();
            canNoob = true;
            moveWhileNoobed.remove(p.getUniqueId());

        }, 280L);

    }


    public void buildShlong(Location loc) {

        // Optimization: Probably should create a Map<UUID, Location> and insert those blocks
        // UUID of the trolled player

        loc.setY(200);
        final int yloc = loc.getBlockY();
        final int floor = yloc - 1;

        loc.getWorld().getBlockAt(loc.getBlockX(), floor, loc.getBlockZ()).setType(Material.GLOWSTONE);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 3, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 4, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 5, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 6, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 7, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 8, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 9, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 10, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 3, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 4, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 5, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 6, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 7, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 8, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 9, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 10, loc.getBlockZ())
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 3, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 4, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 5, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 6, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 7, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 8, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 9, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 10, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 3, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 4, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 5, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 6, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 7, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 8, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 9, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 10, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 3, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 3, loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 3, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 3, loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() + 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() + 3)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ() + 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ() + 3)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ() + 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ() + 3)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() - 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() - 3)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ() - 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ() - 3)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ() - 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ() - 3)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ() + 4)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() + 4)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ() + 4)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ() - 4)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() - 4)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ() - 4)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ() + 4)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 4)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ() + 4)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ() - 4)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 4)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ() - 4)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() - 1)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() + 1)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() + 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() + 3)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() + 4)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() + 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() + 3)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() + 4)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() - 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() - 3)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() - 4)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() - 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() - 3)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() - 4)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() - 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() - 3)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() - 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() - 3)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() + 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() + 3)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() + 2)
                .setType(Material.WHITE_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() + 3)
                .setType(Material.WHITE_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 11, loc.getBlockZ())
                .setType(Material.MAGENTA_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 12, loc.getBlockZ())
                .setType(Material.MAGENTA_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 11, loc.getBlockZ())
                .setType(Material.MAGENTA_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 12, loc.getBlockZ())
                .setType(Material.MAGENTA_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 11, loc.getBlockZ() + 1)
                .setType(Material.MAGENTA_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 12, loc.getBlockZ() + 1)
                .setType(Material.MAGENTA_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 11, loc.getBlockZ() - 1)
                .setType(Material.MAGENTA_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 12, loc.getBlockZ() - 1)
                .setType(Material.MAGENTA_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 13, loc.getBlockZ())
                .setType(Material.MAGENTA_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 10, loc.getBlockZ() - 1)
                .setType(Material.MAGENTA_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 11, loc.getBlockZ() - 1)
                .setType(Material.MAGENTA_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 10, loc.getBlockZ() - 1)
                .setType(Material.MAGENTA_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 11, loc.getBlockZ() - 1)
                .setType(Material.MAGENTA_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 10, loc.getBlockZ() + 1)
                .setType(Material.MAGENTA_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 11, loc.getBlockZ() + 1)
                .setType(Material.MAGENTA_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 12, loc.getBlockZ() + 1)
                .setType(Material.MAGENTA_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 13, loc.getBlockZ() + 1)
                .setType(Material.MAGENTA_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 10, loc.getBlockZ() + 1)
                .setType(Material.MAGENTA_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 11, loc.getBlockZ() + 1)
                .setType(Material.MAGENTA_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 12, loc.getBlockZ() + 1)
                .setType(Material.MAGENTA_TERRACOTTA);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 13, loc.getBlockZ() + 1)
                .setType(Material.MAGENTA_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 13, loc.getBlockZ() + 1)
                .setType(Material.MAGENTA_TERRACOTTA);

        loc.getWorld().getBlockAt(loc.getBlockX() + 8, loc.getBlockY(), loc.getBlockZ() + 8).setType(Material.TORCH);
        loc.getWorld().getBlockAt(loc.getBlockX() - 8, loc.getBlockY(), loc.getBlockZ() - 8).setType(Material.TORCH);
        loc.getWorld().getBlockAt(loc.getBlockX() - 8, loc.getBlockY(), loc.getBlockZ() + 8).setType(Material.TORCH);
        loc.getWorld().getBlockAt(loc.getBlockX() + 8, loc.getBlockY(), loc.getBlockZ() - 8).setType(Material.TORCH);

        // FLOOR

        for (int i = 0; i <= 9; i++) {

            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 1).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 2).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 3).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 4).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 5).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 6).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 7).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 8).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 9).setType(Material.GRASS_BLOCK);

            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 1).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 2).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 3).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 4).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 5).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 6).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 7).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 8).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 9).setType(Material.GRASS_BLOCK);

            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 1).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 2).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 3).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 4).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 5).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 6).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 7).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 8).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 9).setType(Material.GRASS_BLOCK);

            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 1).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 2).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 3).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 4).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 5).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 6).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 7).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 8).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 9).setType(Material.GRASS_BLOCK);

            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ()).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ()).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX(), floor, loc.getBlockZ() + i).setType(Material.GRASS_BLOCK);
            loc.getWorld().getBlockAt(loc.getBlockX(), floor, loc.getBlockZ() - i).setType(Material.GRASS_BLOCK);

        }

    }

    private void removeShlong(Location loc) {

        // Optimization: Itereate through the Map <UUID, Location> and simply set all entries to AIR
        // Instead of using all those relative locations

        loc.setY(200);
        final int yloc = loc.getBlockY();
        final int floor = yloc - 1;

        loc.getWorld().getBlockAt(loc.getBlockX(), floor, loc.getBlockZ()).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 3, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 4, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 5, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 6, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 7, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 8, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 9, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 10, loc.getBlockZ()).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 3, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 4, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 5, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 6, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 7, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 8, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 9, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 10, loc.getBlockZ()).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 3, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 4, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 5, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 6, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 7, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 8, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 9, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 10, loc.getBlockZ() + 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 3, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 4, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 5, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 6, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 7, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 8, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 9, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 10, loc.getBlockZ() - 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 3, loc.getBlockZ() - 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 3, loc.getBlockZ() - 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 3, loc.getBlockZ() + 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 3, loc.getBlockZ() + 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() + 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() + 3).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ() + 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ() + 3).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ() + 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ() + 3).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() - 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 2, loc.getBlockZ() - 3).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ() - 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 2, loc.getBlockZ() - 3).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ() - 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 2, loc.getBlockZ() - 3).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ() + 4).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() + 4).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ() + 4).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 1, loc.getBlockZ() - 4).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 1, loc.getBlockZ() - 4).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 1, loc.getBlockZ() - 4).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ() + 4).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() + 4).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ() + 4).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY(), loc.getBlockZ() - 4).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ() - 4).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY(), loc.getBlockZ() - 4).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() + 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() + 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() + 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() + 3).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() + 4).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() + 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() + 3).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() + 4).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() - 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() - 3).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY(), loc.getBlockZ() - 4).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() - 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() - 3).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY(), loc.getBlockZ() - 4).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() - 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() - 3).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() - 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() - 3).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() + 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 2, loc.getBlockY() + 1, loc.getBlockZ() + 3).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() + 2).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 2, loc.getBlockY() + 1, loc.getBlockZ() + 3).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 11, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 12, loc.getBlockZ()).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 11, loc.getBlockZ()).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 12, loc.getBlockZ()).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 11, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 12, loc.getBlockZ() + 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 11, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 12, loc.getBlockZ() - 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 13, loc.getBlockZ()).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 10, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 11, loc.getBlockZ() - 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 10, loc.getBlockZ() - 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 11, loc.getBlockZ() - 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 10, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 11, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 12, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 1, loc.getBlockY() + 13, loc.getBlockZ() + 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 10, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 11, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 12, loc.getBlockZ() + 1).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 1, loc.getBlockY() + 13, loc.getBlockZ() + 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX(), loc.getBlockY() + 13, loc.getBlockZ() + 1).setType(Material.AIR);

        loc.getWorld().getBlockAt(loc.getBlockX() + 8, loc.getBlockY(), loc.getBlockZ() + 8).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 8, loc.getBlockY(), loc.getBlockZ() - 8).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() - 8, loc.getBlockY(), loc.getBlockZ() + 8).setType(Material.AIR);
        loc.getWorld().getBlockAt(loc.getBlockX() + 8, loc.getBlockY(), loc.getBlockZ() - 8).setType(Material.AIR);

        // FLOOR

        for (int i = 0; i <= 9; i++) {

            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 1).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 2).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 3).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 4).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 5).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 6).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 7).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 8).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() + 9).setType(Material.AIR);

            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 1).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 2).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 3).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 4).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 5).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 6).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 7).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 8).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() + 9).setType(Material.AIR);

            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 1).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 2).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 3).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 4).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 5).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 6).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 7).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 8).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ() - 9).setType(Material.AIR);

            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 1).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 2).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 3).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 4).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 5).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 6).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 7).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 8).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ() - 9).setType(Material.AIR);

            loc.getWorld().getBlockAt(loc.getBlockX() + i, floor, loc.getBlockZ()).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX() - i, floor, loc.getBlockZ()).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX(), floor, loc.getBlockZ() + i).setType(Material.AIR);
            loc.getWorld().getBlockAt(loc.getBlockX(), floor, loc.getBlockZ() - i).setType(Material.AIR);

        }

    }

    public void teleportBackFromShlong(Player p) {

        Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {

            nomine.remove(p.getUniqueId());
            p.teleport(oldShlongLocation.get(p.getName()));
            removeShlong(p.getLocation());
            oldShlongLocation.remove(p.getName());
            isShlonged = false;

        }, 20 * 20L);

    }

    public void endTask(Player p) {

        if (!taskID.containsKey(p.getUniqueId())) {
            return;
        }
        final int tid = taskID.get(p.getUniqueId());
        Bukkit.getScheduler().cancelTask(tid);
        taskID.remove(p.getUniqueId());
    }

    public void checkIfIsInUsable(Player p) {

        this.usable.remove(p.getUniqueId());

    }

    public void storeInv(Player p) {

        Inventory clone = Bukkit.createInventory(p, InventoryType.PLAYER);
        clone.setContents(p.getInventory().getContents());
        invstores.put(p.getUniqueId(), clone);

    }

    public void restoreInv(Player p, int sec) {

        if (invstores.containsKey(p.getUniqueId())) {

            Bukkit.getScheduler().scheduleSyncDelayedTask(this, () -> {

                p.getInventory().clear();
                p.getInventory().setContents(invstores.get(p.getUniqueId()).getContents());
                invstores.remove(p.getUniqueId());
                p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
                p.sendMessage("§eJust a prank, here's your old inventory!");

            }, sec * 20L);

        }
    }

    private void createItemForGui(int amount, Material mat, String DisplayName, int slot,
                                  Inventory inventory, String... lore) {

        final ItemStack istack = new ItemStack(mat, amount);
        final ItemMeta istackmeta = istack.getItemMeta();
        istackmeta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
        istackmeta.setDisplayName(DisplayName);
        final ArrayList<String> metalore = new ArrayList<>(Arrays.asList(lore));

        istackmeta.setLore(metalore);
        istack.setItemMeta(istackmeta);
        inventory.setItem(slot, istack);


    }

    public void openBowStatisticsInv(Player p) {

        final Inventory inv = Bukkit.createInventory(null, 9, "§cTrollbow Statistics");

        createItemForGui(1, Material.EMERALD, "§bClose the gui", 8, inv, "§7Closes the Statistics-Gui");
        createItemForGui(1, Material.BOW, "§eBolt Bow", 0, inv, "§7Times used: §a" + getStats("Bows.Bolt"));
        createItemForGui(1, Material.BOW, "§eBoom Bow", 1, inv, "§7Times used: §a" + getStats("Bows.Boom"));
        createItemForGui(1, Material.BOW, "§eCreeper Bow", 2, inv, "§7Times used: §a" + getStats("Bows.Creeper"));
        createItemForGui(1, Material.BOW, "§ePull Bow", 3, inv, "§7Times used: §a" + getStats("Bows.Pull"));

        p.openInventory(inv);

    }

    public void openStatisticsInv(Player p) {

        final Inventory inv = Bukkit.createInventory(null, 54, "§cTroll Statistics");

        createItemForGui(1, Material.EMERALD, "§bClose the gui", 53, inv, "§7Closes the Statistics-Gui");
        createItemForGui(1, Material.APPLE, "§eBadapple", 0, inv, "§7Times used: §a" + getStats("Badapple"), "§7Last used by: §a" + getLastUsedUser("Badapple"));
        createItemForGui(1, Material.FIRE_CHARGE, "§eBolt", 1, inv, "§7Times used: §a" + getStats("Bolt"), "§7Last used by: §a" + getLastUsedUser("Bolt"));
        createItemForGui(1, Material.TNT, "§eBoom", 2, inv, "§7Times used: §a" + getStats("Boom"), "§7Last used by: §a" + getLastUsedUser("Boom"));
        createItemForGui(1, Material.LAVA_BUCKET, "§eBurn", 3, inv, "§7Times used: §a" + getStats("Burn"), "§7Last used by: §a" + getLastUsedUser("Burn"));
        createItemForGui(1, Material.DIRT, "§eBury", 4, inv, "§7Times used: §a" + getStats("Bury"), "§7Last used by: §a" + getLastUsedUser("Bury"));
        createItemForGui(1, Material.STRING, "§eCrash", 5, inv, "§7Times used: §a" + getStats("Crash"), "§7Last used by: §a" + getLastUsedUser("Crash"));
        createItemForGui(1, Material.LEATHER_BOOTS, "§eDenymove", 6, inv, "§7Times used: §a" + getStats("Denymove"), "§7Last used by: §a" + getLastUsedUser("Denymove"));
        createItemForGui(1, Material.DIAMOND, "§eFakeop", 7, inv, "§7Times used: §a" + getStats("Fakeop"), "§7Last used by: §a" + getLastUsedUser("Fakeop"));
        createItemForGui(1, Material.GOLD_INGOT, "§eFakedeop", 8, inv, "§7Times used: §a" + getStats("Fakedeop"), "§7Last used by: §a" + getLastUsedUser("Fakedeop"));
        createItemForGui(1, Material.BLAZE_POWDER, "§eFakerestart", 9, inv, "§7Times used: §a" + getStats("Fakerestart"), "§7Last used by: §a" + getLastUsedUser("Fakerestart"));
        createItemForGui(1, Material.WHITE_WOOL, "§eFreefall", 10, inv, "§7Times used: §a" + getStats("Freefall"), "§7Last used by: §a" + getLastUsedUser("Freefall"));
        createItemForGui(1, Material.ICE, "§eFreeze", 11, inv, "§7Times used: §a" + getStats("Freeze"), "§7Last used by: §a" + getLastUsedUser("Freeze"));
        createItemForGui(1, Material.SOUL_SAND, "§eGokill", 12, inv, "§7Times used: §a" + getStats("Gokill"), "§7Last used by: §a" + getLastUsedUser("Gokill"));
        createItemForGui(1, Material.GLOWSTONE_DUST, "§eHerobrine", 13, inv, "§7Times used: §a" + getStats("Herobrine"), "§7Last used by: §a" + getLastUsedUser("Herobrine"));
        createItemForGui(1, Material.REDSTONE, "§eBoom", 14, inv, "§7Times used: §a" + getStats("Hurt"), "§7Last used by: §a" + getLastUsedUser("Hurt"));
        createItemForGui(1, Material.POTION, "§eInfect", 15, inv, "§7Times used: §a" + getStats("Infect"), "§7Last used by: §a" + getLastUsedUser("Infect"));
        createItemForGui(1, Material.FIREWORK_ROCKET, "§eLaunch", 16, inv, "§7Times used: §a" + getStats("Launch"), "§7Last used by: §a" + getLastUsedUser("Launch"));
        createItemForGui(1, Material.GRASS_BLOCK, "§eNomine", 17, inv, "§7Times used: §a" + getStats("Nomine"), "§7Last used by: §a" + getLastUsedUser("Nomine"));
        createItemForGui(1, Material.BAKED_POTATO, "§ePotatotroll", 18, inv, "§7Times used: §a" + getStats("Potatotroll"), "§7Last used by: §a" + getLastUsedUser("Potatotroll"));
        createItemForGui(1, Material.JACK_O_LANTERN, "§ePumpkinhead", 19, inv, "§7Times used: §a" + getStats("Pumpkinhead"), "§7Last used by: §a" + getLastUsedUser("Pumpkinhead"));
        createItemForGui(1, Material.FEATHER, "§ePush", 20, inv, "§7Times used: §a" + getStats("Push"), "§7Last used by: §a" + getLastUsedUser("Push"));
        createItemForGui(1, Material.ENDER_PEARL, "§eRandomteleport", 21, inv, "§7Times used: §a" + getStats("Randomteleport"), "§7Last used by: §a" + getLastUsedUser("Randomteleport"));
        createItemForGui(1, Material.OAK_SIGN, "§eSpam", 22, inv, "§7Times used: §a" + getStats("Spam"), "§7Last used by: §a" + getLastUsedUser("Spam"));
        createItemForGui(1, Material.CHEST, "§eSpecial", 23, inv, "§7Times used: §a" + getStats("Special"), "§7Last used by: §a" + getLastUsedUser("Special"));
        createItemForGui(1, Material.COOKED_CHICKEN, "§eStarve", 24, inv, "§7Times used: §a" + getStats("Starve"), "§7Last used by: §a" + getLastUsedUser("Starve"));
        createItemForGui(1, Material.FISHING_ROD, "§eTeleporttroll", 25, inv, "§7Times used: §a" + getStats("Teleporttroll"), "§7Last used by: §a" + getLastUsedUser("Teleporttroll"));
        createItemForGui(1, Material.BEDROCK, "§eTrap", 26, inv, "§7Times used: §a" + getStats("Trap"), "§7Last used by: §a" + getLastUsedUser("Trap"));
        createItemForGui(1, Material.IRON_DOOR, "§eTrollkick", 27, inv, "§7Times used: §a" + getStats("Trollkick"), "§7Last used by: §a" + getLastUsedUser("Trollkick"));
        createItemForGui(1, Material.PAPER, "§eTurn", 28, inv, "§7Times used: §a" + getStats("Turn"), "§7Last used by: §a" + getLastUsedUser("Turn"));
        createItemForGui(1, Material.OBSIDIAN, "§eVoid", 29, inv, "§7Times used: §a" + getStats("Void"), "§7Last used by: §a" + getLastUsedUser("Void"));
        createItemForGui(1, Material.COBWEB, "§eWebtrap", 30, inv, "§7Times used: §a" + getStats("Webtrap"), "§7Last used by: §a" + getLastUsedUser("Webtrap"));
        createItemForGui(1, Material.BONE, "§eSpank", 31, inv, "§7Times used: §a" + getStats("Spank"), "§7Last used by: §a" + getLastUsedUser("Spank"));
        createItemForGui(1, Material.COW_SPAWN_EGG, "§eTrample", 32, inv, "§7Times used: §a" + getStats("Trample"), "§7Last used by: §a" + getLastUsedUser("Trample"));
        createItemForGui(1, Material.LEVER, "§eStfu", 33, inv, "§7Times used: §a" + getStats("Stfu"), "§7Last used by: §a" + getLastUsedUser("Stfu"));
        createItemForGui(1, Material.BOOK, "§ePopup", 34, inv, "§7Times used: §a" + getStats("Popup"), "§7Last used by: §a" + getLastUsedUser("Popup"));
        createItemForGui(1, Material.GLASS, "§eSky", 35, inv, "§7Times used: §a" + getStats("Sky"), "§7Last used by: §a" + getLastUsedUser("Sky"));
        createItemForGui(1, Material.CLOCK, "§eAbduct", 36, inv, "§7Times used: §a" + getStats("Abduct"), "§7Last used by: §a" + getLastUsedUser("Abduct"));
        createItemForGui(1, Material.EXPERIENCE_BOTTLE, "§ePopular", 37, inv, "§7Times used: §a" + getStats("Popular"), "§7Last used by: §a" + getLastUsedUser("Popular"));
        createItemForGui(1, Material.CREEPER_SPAWN_EGG, "§eCreeper", 38, inv, "§7Times used: §a" + getStats("Creeper"), "§7Last used by: §a" + getLastUsedUser("Creeper"));
        createItemForGui(1, Material.ARROW, "§eSparta", 39, inv, "§7Times used: §a" + getStats("Sparta"), "§7Last used by: §a" + getLastUsedUser("Sparta"));
        createItemForGui(1, Material.WHEAT, "§eDrug", 40, inv, "§7Times used: §a" + getStats("Drug"), "§7Last used by: §a" + getLastUsedUser("Drug"));
        createItemForGui(1, Material.INK_SAC, "§eSquidrain", 41, inv, "§7Times used: §a" + getStats("Squidrain"), "§7Last used by: §a" + getLastUsedUser("Squidrain"));
        createItemForGui(1, Material.DROPPER, "§eDropinv", 42, inv, "§7Times used: §a" + getStats("Dropinv"), "§7Last used by: §a" + getLastUsedUser("Dropinv"));
        createItemForGui(1, Material.WRITABLE_BOOK, "§eGarbage", 43, inv, "§7Times used: §a" + getStats("Garbage"), "§7Last used by: §a" + getLastUsedUser("Garbage"));
        createItemForGui(1, Material.ANVIL, "§eAnvil", 44, inv, "§7Times used: §a" + getStats("Anvil"), "§7Last used by: §a" + getLastUsedUser("Anvil"));
        createItemForGui(2, Material.PAPER, "§eInvtext", 45, inv, "§7Times used: §a" + getStats("Invtext"), "§7Last used by: §a" + getLastUsedUser("Invtext"));
        createItemForGui(1, Material.IRON_BOOTS, "§eRunforrest", 46, inv, "§7Times used: §a" + getStats("Runforrest"), "§7Last used by: §a" + getLastUsedUser("Runforrest"));
        createItemForGui(1, Material.BOW, "§eTrollbows", 47, inv, "§7Times used: §a" + getAllBowStats(), "§7Click for more information");
        createItemForGui(1, Material.DEAD_BUSH, "§eBorder", 48, inv, "§7Times used: §a" + getStats("Border"), "§7Last used by: §a" + getLastUsedUser("Border"));
        createItemForGui(1, Material.VINE, "§eNoob", 49, inv, "§7Times used: §a" + getStats("Noob"), "§7Last used by: §a" + getLastUsedUser("Noob"));
        createItemForGui(1, Material.PINK_TULIP, "§eShlong", 50, inv, "§7Times used: §a" + getStats("Shlong"), "§7Last used by: §a" + getLastUsedUser("Shlong"));
        p.openInventory(inv);

    }

    public String getVersion() {

        return this.version;
    }

}
