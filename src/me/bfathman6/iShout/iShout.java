package me.bfathman6.iShout;



import java.io.File;
import java.net.Socket;
import java.security.acl.Permission;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.logging.Level;

import me.bfathman6.api.InfoReader;
import me.bfathman6.api.InfoWriter;
import me.bfathman6.api.iShInfoReader;
import me.bfathman6.api.iShInfoWriter;
import me.bfathman6.api.iShoutAPI;
import me.bfathman6.channel.ChannelManager;
import me.bfathman6.commands.PMChatAcceptCommand;
import me.bfathman6.commands.PMChatCommand;
import me.bfathman6.commands.PMChatDenyCommand;
import me.bfathman6.commands.PMChatInviteCommand;
import me.bfathman6.commands.PMChatLeaveCommand;
import me.bfathman6.commands.PMChatReplyCommand;
import me.bfathman6.commands.iShChannelCommand;
import me.bfathman6.commands.iShChatAFKCommand;
import me.bfathman6.commands.iShChatAFKOtherCommand;
import me.bfathman6.commands.iShChatListCommand;
import me.bfathman6.commands.iShChatMeCommand;
import me.bfathman6.commands.iShChatMessagePrefixCommand;
import me.bfathman6.commands.iShChatMuteCommand;
import me.bfathman6.commands.iShChatSayCommand;
import me.bfathman6.commands.iShChatShoutCommand;
import me.bfathman6.commands.iShChatWhoCommand;
import me.bfathman6.configs.CensorConfig;
import me.bfathman6.configs.ChannelConfig;
import me.bfathman6.configs.InfoConfig;
import me.bfathman6.configs.LocaleConfig;
import me.bfathman6.configs.MainConfig;
import me.bfathman6.events.BlockListener;
import me.bfathman6.events.ChannelEventListener;
import me.bfathman6.events.CustomListener;
import me.bfathman6.events.EntityListener;
import me.bfathman6.events.PlayerListener;
import me.bfathman6.external.BroadcastMessage;
import me.bfathman6.util.Messanger;

import org.anjocaido.groupmanager.GroupManager;
import org.anjocaido.groupmanager.dataholder.worlds.WorldsHolder;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.getspout.spoutapi.player.SpoutPlayer;

import com.herocraftonline.heroes.Heroes;
import com.massivecraft.factions.Conf;

import ru.tehkode.permissions.PermissionManager;
import ru.tehkode.permissions.bukkit.PermissionsEx;

public class iShout extends JavaPlugin {
    // Default Plugin Data
    public PluginManager pm;
    public PluginDescriptionFile pdfFile;

    // External Messaging
    public BroadcastMessage bMessage;

    // GroupManager
    public WorldsHolder gmPermissionsWH;
    public Boolean gmPermissionsB = false;

    // PermissionsEX
    public PermissionManager pexPermissions;
    public Boolean PEXB = false;

    // PermissionsBukkit
    public Boolean PermissionBuB = false;

    // bPermissions
    public Boolean bPermB = false;

    // MobDisguise
    public Boolean mobD = false;

    // Factions
    public Boolean factionsB = false;

    // Heroes
    public Heroes heroes;
    public Boolean heroesB = false;

    // Vault
    public Permission vPerm;
    public Boolean vaultB = false;

    // ChannelManager
    public ChannelManager channelManager;

    // Configuration
    public YamlConfiguration iShConfig = null;
    public YamlConfiguration iShIConfig = null;
    public YamlConfiguration iShCConfig = null;
    public YamlConfiguration iShELocale = null;
    public YamlConfiguration iShChConfig = null;

    // Configuration Files
    public File iShConfigF = null;
    public File iShIConfigF = null;
    public File iShCConfigF = null;
    public File iShELocaleF = null;
    public File iShChConfigF = null;

    // Optional iShout only Info Support
    public Boolean useNewInfo = false;

    // Optional Old Nodular Style Formatting
    public Boolean useOldNodes = false;

    // Optional Leveled Nodes
    public Boolean useLeveledNodes = false;

    // API Only Boolean
    public Boolean iShoutAPIOnly = false;

    // Alter Event Messages Boolean
    public Boolean alterEvents = true;
    public Boolean alterDMessages = true;

    // Add New Players Boolean
    public Boolean useAddDefault = false;

    // Info Related Variables
    public String iShIDefaultGroup = "default";

    // Formatting
    public String varIndicator = "+";
    public String cusVarIndicator = "-";
    public String tabbedListFormat = "+p+dn+s";
    public String listCmdFormat = "+p+dn+s";
    public String chatFormat = "+p+dn+s&f: +m";
    public String nameFormat = "+p+dn+s&e";
    public String eventFormat = "+p+dn+s&e";
    public String meFormat = "* +p+dn+s&e +m";
    public String dateFormat = "HH:mm:ss";

    // Messages
    public String joinMessage = "has joined the game.";
    public String leaveMessage = "has left the game.";
    public String kickMessage = "has been kicked from the game. [ +r ]";
    public String deathInFire = "went up in flames.";
    public String deathOnFire = "burned to death.";
    public String deathLava = "tried to swim in lava.";
    public String deathInWall = "suffocated in a wall.";
    public String deathDrown = "drowned.";
    public String deathStarve = "starved to death.";
    public String deathCactus = "was pricked to death.";
    public String deathFall = "hit the ground too hard.";
    public String deathOutOfWorld = "fell out of the world.";
    public String deathGeneric = "died.";
    public String deathExplosion = "blew up.";
    public String deathMagic = "was killed by magic.";
    public String deathEntity = "was slain by +killer.";
    public String deathMobFormat = "a +killer";
    public String deathArrow = "was shot by +killer.";
    public String deathFireball = "was fireballed by +killer.";
    public String deathThrown = "was pummeled by +killer.";
    public String hMasterT = "The Great";
    public String hMasterF = "The Squire";

    // Strings
    public String eBroadcastIP = "ANY";

    // Booleans
    public Boolean spoutEnabled = true;
    public Boolean healthNotify = false;
    public Boolean healthAchievement = true;
    public Boolean spoutB = false;
    public Boolean iShAFKHQ = true;
    public Boolean iShChatEB = false;
    public Boolean useAFKList = false;
    public Boolean iShChatPB = false;
    public Boolean spoutPM = false;
    public Boolean sJoinB = false;
    public Boolean sDeathB = false;
    public Boolean sQuitB = false;
    public Boolean sKickB = false;
    public Boolean useIPRestrict = true;
    public Boolean useGroupedList = true;
    public Boolean eBroadcast = false;

    // Numbers
    public Integer AFKTimer = 30;
    public Integer AFKKickTimer = 120;
    public Integer sJoinI = 30;
    public Integer sDeathI = 30;
    public Integer sQuitI = 30;
    public Integer sKickI = 30;
    public Integer eBroadcastPort = 1940;
    public Integer cLockRange = 3;

    // Other Config Stuff
    public Double chatDistance = -1.0;
    public String cLVars = "default,Default";
    public String listVar = "group";

    // Timers
    long sTime1;
    long sTime2;
    long sDiff;

    // Maps
    public HashMap<String, Location> AFKLoc = new HashMap<String, Location>();

    public HashMap<String, Boolean> chatt = new HashMap<String, Boolean>();
    public HashMap<String, Boolean> isAFK = new HashMap<String, Boolean>();
    public HashMap<String, Boolean> isConv = new HashMap<String, Boolean>();
    public HashMap<String, Boolean> isShouting = new HashMap<String, Boolean>();
    public HashMap<String, Boolean> isSpying = new HashMap<String, Boolean>();
    public HashMap<String, Boolean> isMuted = new HashMap<String, Boolean>();

    public HashMap<String, String> lastPMd = new HashMap<String, String>();
    public HashMap<String, String> getInvite = new HashMap<String, String>();
    public HashMap<String, String> chatPartner = new HashMap<String, String>();
    public HashMap<String, String> iShPrefix = new HashMap<String, String>();

    public HashMap<String, Long> lastMove = new HashMap<String, Long>();

    public TreeMap<String, String> cVarMap = new TreeMap<String, String>();

    // Lists
    public ArrayList<Socket> queryList = new ArrayList<Socket>();

    public void onEnable() {
        // 1st Startup Timer
        sTime1 = new Date().getTime();

        // Initialize Plugin Data
        pm = getServer().getPluginManager();
        pdfFile = getDescription();

        // First we kill EssentialsChat
        killEss();

        if (new File("plugins/iShout/").isDirectory()) {
            getServer().getLogger().log(Level.SEVERE, "[" + pdfFile.getName() + "] Please move the files in the iShout directory to");
            getServer().getLogger().log(Level.SEVERE, "[" + pdfFile.getName() + "] iShout's than delete the iShout directory!");
        }

        // Initialize Configs
        iShConfigF = new File(getDataFolder(), "config.yml");
        iShIConfigF = new File(getDataFolder(), "info.yml");
        iShCConfigF = new File(getDataFolder(), "censor.yml");
        iShELocaleF = new File(getDataFolder(), "locale.yml");
        iShChConfigF = new File(getDataFolder(), "channels.yml");

        iShConfig = YamlConfiguration.loadConfiguration(iShConfigF);
        iShIConfig = YamlConfiguration.loadConfiguration(iShIConfigF);
        iShCConfig = YamlConfiguration.loadConfiguration(iShCConfigF);
        iShELocale = YamlConfiguration.loadConfiguration(iShELocaleF);
        iShChConfig = YamlConfiguration.loadConfiguration(iShChConfigF);

        // Manage Config options
        iShConfig.options().indent(4);
        iShIConfig.options().indent(4);
        iShCConfig.options().indent(4);

        // ChannelManager
        channelManager = new ChannelManager(this);

        // Setup Configs
        setupConfigs();

        // Setup Plugins
        setupPlugins();

        // Setup Permissions
        setupPerms();

        // Register Events
        registerEvents();

        // Setup Tasks
        setupTasks();

        // Setup Commands
        setupCommands();

        // External Messaging
        bMessage = new BroadcastMessage(this);

        if (eBroadcast)
            if (bMessage.connect())
                bMessage.startListeners();

        // Add All Players To Info.yml
        if (useAddDefault)
            for (Player players : getServer().getOnlinePlayers())
                if (iShIConfig.get("users." + players.getName()) == null)
                    getWriter().addBase(players.getName(), iShIDefaultGroup);

        if (iShChatEB) {
            for (Player players : getServer().getOnlinePlayers()) {
                isAFK.put(players.getName(), false);
                chatt.put(players.getName(), false);
                lastMove.put(players.getName(), new Date().getTime());

                if (spoutB) {
                    SpoutPlayer sPlayers = (SpoutPlayer) players;
                    sPlayers.setTitle(getAPI().ParsePlayerName(players.getName(), players.getWorld().getName()));
                }
            }
        }

        // Check for Automatic Factions Support
        setupFactions();

        // 2nd Startup Timer
        sTime2 = new Date().getTime();

        // Calculate Startup Timer
        sDiff = (sTime2 - sTime1);

        Messanger.log("[" + pdfFile.getName() + "] " + pdfFile.getName() + " v" + pdfFile.getVersion() + " is enabled! [" + sDiff + "ms]");
    }

    public void onDisable() {
        getServer().getScheduler().cancelTasks(this);

        if (eBroadcast && bMessage != null)
            bMessage.disconnect();

        getChannelManager().saveChannels();

        Messanger.log("[" + pdfFile.getName() + "] " + pdfFile.getName() + " v" + pdfFile.getVersion() + " is disabled!");
    }

    void registerEvents() {
        if (!iShoutAPIOnly) {
            pm.registerEvents(new PlayerListener(this), this);

            pm.registerEvents(new BlockListener(this), this);

            pm.registerEvents(new EntityListener(this), this);

            pm.registerEvents(new ChannelEventListener(this), this);

            if (spoutB)
                pm.registerEvents(new CustomListener(this), this);
        }
    }

    void setupPerms() {
        Plugin permTest;

        permTest = pm.getPlugin("PermissionsBukkit");
        if (permTest != null) {
            PermissionBuB = true;
            Messanger.log("[" + pdfFile.getName() + "] " + permTest.getDescription().getName() + " v" + (permTest.getDescription().getVersion()) + " found hooking in.");
            return;
        }

        permTest = pm.getPlugin("bPermissions");
        if (permTest != null) {
            bPermB = true;
            Messanger.log("[" + pdfFile.getName() + "] " + permTest.getDescription().getName() + " v" + (permTest.getDescription().getVersion()) + " found hooking in.");
            return;
        }

        permTest = pm.getPlugin("PermissionsEx");
        if (permTest != null) {
            pexPermissions = PermissionsEx.getPermissionManager();
            PEXB = true;
            Messanger.log("[" + pdfFile.getName() + "] " + permTest.getDescription().getName() + " v" + (permTest.getDescription().getVersion()) + " found hooking in.");
            return;
        }

        permTest = pm.getPlugin("GroupManager");
        if (permTest != null) {
            gmPermissionsB = true;
            gmPermissionsWH = ((GroupManager) permTest).getWorldsHolder();
            Messanger.log("[" + pdfFile.getName() + "] " + permTest.getDescription().getName() + " v" + (permTest.getDescription().getVersion()) + " found hooking in.");
            return;
        }

        Messanger.log("[" + pdfFile.getName() + "] No Permissions plugins were found defaulting to permissions.yml/info.yml.");
    }

    Boolean setupPlugin(String pluginName) {
        Plugin plugin = pm.getPlugin(pluginName);

        if (plugin != null) {
            Messanger.log("[" + pdfFile.getName() + "] " + plugin.getDescription().getName() + " " + (plugin.getDescription().getVersion()) + " found hooking in.");
            return true;
        }

        return false;
    }

    void setupPlugins() {
        // Setup MobDisguise
        mobD = setupPlugin("MobDisguise");

        // Setup Factions
        factionsB = setupPlugin("Factions");

        // Setup Heroes
        heroesB = setupPlugin("Heroes");

        if (heroesB)
            heroes = (Heroes) pm.getPlugin("Heroes");

        spoutB = setupPlugin("Spout");

        vaultB = setupPlugin("Vault");

        if (vaultB) {
            RegisteredServiceProvider<Permission> permissionProvider = getServer().getServicesManager().getRegistration(Permission.class);

            if (permissionProvider != null)
                vPerm = permissionProvider.getProvider();

            vaultB = vPerm != null;
        }

        if (!spoutEnabled)
            spoutB = false;
    }

    void setupFactions() {
        if (factionsB)
            if (!(Conf.chatTagInsertIndex == 0))
                getServer().dispatchCommand(getServer().getConsoleSender(), "f config chatTagInsertIndex 0");
    }

    void killEss() {
        Plugin plugin = pm.getPlugin("EssentialsChat");

        if (plugin != null)
            pm.disablePlugin(plugin);
    }

    public void setupConfigs() {
        getMainConfig().load();

        getInfoConfig().load();

        getCensorConfig().load();

        getLocale().load();

        getChannelConfig().load();
    }

    public void reloadConfigs() {
        getMainConfig().reload();

        getInfoConfig().reload();

        getCensorConfig().reload();

        getLocale().reload();

        getChannelConfig().reload();
    }

    void setupTasks() {
        getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            public void run() {
                if (!iShChatEB)
                    return;

                if (AFKTimer < 1)
                    return;

                getMainConfig().reload();

                for (Player player : getServer().getOnlinePlayers()) {
                    if (isAFK.get(player.getName()) == null)
                        isAFK.put(player.getName(), false);

                    if (isAFK.get(player.getName()) ||
                            lastMove.get(player.getName()) == null ||
                            getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShchat.bypass.afk"))
                        continue;

                    if (new Date().getTime() - (AFKTimer * 1000) > lastMove.get(player.getName())) {
                        getServer().dispatchCommand(getServer().getConsoleSender(), "iShchatafkother " + player.getName() + " AutoAfk");
                    } else
                        isAFK.put(player.getName(), false);
                }
            }
        }, 20L * 5, 20L * 5);

        getServer().getScheduler().scheduleAsyncRepeatingTask(this, new Runnable() {
            public void run() {
                if (!iShChatEB)
                    return;

                if (AFKKickTimer < 1)
                    return;

                getMainConfig().reload();

                for (Player player : getServer().getOnlinePlayers()) {
                    if (getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.bypass.afkkick"))
                        continue;

                    if (!isAFK.get(player.getName()))
                        continue;

                    if (new Date().getTime() - (AFKKickTimer * 1000) > lastMove.get(player.getName()))
                        player.kickPlayer("iShAFK Kick");
                }
            }
        }, 20L * 10, 20L * 10);
    }

    void setupCommands() {
        regCommands("iShout", new iShChannelCommand(this));
        regCommands("iShoutafk", new iShChatAFKCommand(this));
        regCommands("iShoutafkother", new iShChatAFKOtherCommand(this));

        regCommands("iShoutlist", new iShChatListCommand(this));

        regCommands("iShoutme", new iShChatMeCommand(this));
        regCommands("iShoutsay", new iShChatSayCommand(this));
        regCommands("iShoutwho", new iShChatWhoCommand(this));
        regCommands("iShoutshout", new iShChatShoutCommand(this));
        regCommands("iShoutmute", new iShChatMuteCommand(this));
        regCommands("iShoutmessageprefix", new iShChatMessagePrefixCommand(this));

        regCommands("pm", new PMChatCommand(this));
        regCommands("pmaccept", new PMChatAcceptCommand(this));
        regCommands("pmdeny", new PMChatDenyCommand(this));
        regCommands("pminvite", new PMChatInviteCommand(this));
        regCommands("pmleave", new PMChatLeaveCommand(this));
        regCommands("pmreply", new PMChatReplyCommand(this));

        regCommands("iShchannel", new iShChannelCommand(this));
    }
    
    void regCommands(String command, CommandExecutor executor) {
        if (getCommand(command) != null)
            getCommand(command).setExecutor(executor);
    }

    // Main Config (config.yml)
    public MainConfig getMainConfig() {
        return new MainConfig(this);
    }

    // Info Config (info.yml)
    public InfoConfig getInfoConfig() {
        return new InfoConfig(this);
    }

    // Censor Config (censor.yml)
    public CensorConfig getCensorConfig() {
        return new CensorConfig(this);
    }

    // Channel Config (channels.yml)
    public ChannelConfig getChannelConfig() {
        return new ChannelConfig(this);
    }

    // Language Config
    public LocaleConfig getLocale() {
        return new LocaleConfig(this);
    }

    // API
    public iShoutAPI getAPI() {
        return new iShoutAPI(this);
    }

    // InfoReader
    public InfoReader getReader() {
        return new InfoReader(this);
    }

    // InfoWriter
    public InfoWriter getWriter() {
        return new InfoWriter(this);
    }

    // ChannelManager
    public ChannelManager getChannelManager() {
        return channelManager;
    }


    // MInfoReader
    @Deprecated
    public iShInfoReader getInfoReader() {
        return new iShInfoReader(this);
    }

    // MInfoWriter
    @Deprecated
    public iShInfoWriter getInfoWriter() {
        return new iShInfoWriter(this);
    }
}
