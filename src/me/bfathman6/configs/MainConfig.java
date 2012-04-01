package me.bfathman6.configs;

import me.bfathman6.iShout.iShout;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;

import java.io.File;
import java.io.IOException;

public class MainConfig {
    iShout plugin;
    Boolean hasChanged = false;

    public MainConfig(iShout plugin) {
        this.plugin = plugin;
    }

    public void reload() {
        plugin.iShConfig = YamlConfiguration.loadConfiguration(plugin.iShConfigF);
        plugin.iShConfig.options().indent(4);
    }

    void save() {
        try {
            plugin.iShConfig.save(plugin.iShConfigF);
        } catch (IOException ignored) {}
    }

    public void load() {
        YamlConfiguration config = plugin.iShConfig;

        checkConfig();

        plugin.dateFormat = config.getString("format.date", plugin.dateFormat);
        plugin.chatFormat = config.getString("format.chat", plugin.chatFormat);
        plugin.nameFormat = config.getString("format.name", plugin.nameFormat);
        plugin.eventFormat = config.getString("format.event", plugin.eventFormat);
        plugin.tabbedListFormat = config.getString("format.tabbedList", plugin.tabbedListFormat);
        plugin.listCmdFormat = config.getString("format.listCmd", plugin.listCmdFormat);
        plugin.meFormat = config.getString("format.me", plugin.meFormat);

        plugin.joinMessage = config.getString("message.join", plugin.joinMessage);
        plugin.leaveMessage = config.getString("message.leave", plugin.leaveMessage);
        plugin.kickMessage = config.getString("message.kick", plugin.kickMessage);
        plugin.deathInFire = config.getString("message.deathInFire", plugin.deathInFire);
        plugin.deathOnFire = config.getString("message.deathOnFire", plugin.deathOnFire);
        plugin.deathLava = config.getString("message.deathLava", plugin.deathLava);
        plugin.deathInWall = config.getString("message.deathInWall", plugin.deathInWall);
        plugin.deathDrown = config.getString("message.deathInWall", plugin.deathInWall);
        plugin.deathStarve = config.getString("message.deathStarve", plugin.deathStarve);
        plugin.deathCactus = config.getString("message.deathCactus", plugin.deathCactus);
        plugin.deathFall = config.getString("message.deathFall", plugin.deathFall);
        plugin.deathOutOfWorld = config.getString("message.deathOutOfWorld", plugin.deathOutOfWorld);
        plugin.deathGeneric = config.getString("message.deathGeneric", plugin.deathGeneric);
        plugin.deathExplosion = config.getString("message.deathExplosion", plugin.deathExplosion);
        plugin.deathMagic = config.getString("message.deathMagic", plugin.deathMagic);
        plugin.deathEntity = config.getString("message.deathEntity", plugin.deathEntity);
        plugin.deathMobFormat = config.getString("message.deathMobFormat", plugin.deathMobFormat);
        plugin.deathArrow = config.getString("message.deathArrow", plugin.deathArrow);
        plugin.deathFireball = config.getString("message.deathFireball", plugin.deathFireball);
        plugin.deathThrown = config.getString("message.deathThrown", plugin.deathThrown);
        plugin.hMasterT = config.getString("message.heroesMasterT", plugin.hMasterT);
        plugin.hMasterF = config.getString("message.heroesMasterF", plugin.hMasterF);

        plugin.iShoutAPIOnly = config.getBoolean("iShout.apiOnly", plugin.iShoutAPIOnly);
        plugin.alterEvents = config.getBoolean("iShout.alterEvents", plugin.alterEvents);
        plugin.alterDMessages = config.getBoolean("iShout.alterDeathEvents", plugin.alterDMessages);
        plugin.chatDistance = config.getDouble("iShout.chatDistance", plugin.chatDistance);
        plugin.varIndicator = config.getString("iShout.varIndicator", plugin.varIndicator);
        plugin.cusVarIndicator = config.getString("iShout.cusVarIndicator", plugin.cusVarIndicator);
        plugin.spoutEnabled = config.getBoolean("iShout.spout", plugin.spoutEnabled);
        plugin.useIPRestrict = config.getBoolean("iShout.IPCensor", plugin.useIPRestrict);
        plugin.cLockRange = config.getInt("iShout.cLockRange", plugin.cLockRange);

        plugin.sDeathB = config.getBoolean("suppress.useDeath", plugin.sDeathB);
        plugin.sJoinB = config.getBoolean("suppress.useJoin", plugin.sJoinB);
        plugin.sKickB = config.getBoolean("suppress.useKick", plugin.sKickB);
        plugin.sQuitB = config.getBoolean("suppress.useQuit", plugin.sQuitB);
        plugin.sDeathI = config.getInt("suppress.maxDeath", plugin.sDeathI);
        plugin.sJoinI = config.getInt("suppress.maxJoin", plugin.sJoinI);
        plugin.sKickI = config.getInt("suppress.maxKick", plugin.sKickI);
        plugin.sQuitI = config.getInt("suppress.maxQuit", plugin.sQuitI);

        plugin.eBroadcast = config.getBoolean("external.enableBroadcast", plugin.eBroadcast);
        plugin.eBroadcastPort = config.getInt("external.broadcastPort", plugin.eBroadcastPort);
        plugin.eBroadcastIP = config.getString("external.broadcastIP", plugin.eBroadcastIP);

        plugin.useNewInfo = config.getBoolean("info.useNewInfo", plugin.useNewInfo);
        plugin.useLeveledNodes = config.getBoolean("info.useLeveledNodes", plugin.useLeveledNodes);
        plugin.useOldNodes = config.getBoolean("info.useOldNodes", plugin.useOldNodes);
        plugin.useAddDefault = config.getBoolean("info.addNewPlayers", plugin.useAddDefault);
        plugin.iShIDefaultGroup = config.getString("info.defaultGroup", plugin.iShIDefaultGroup);

        plugin.iShChatEB = config.getBoolean("iShoute.enable", plugin.iShChatEB);
        plugin.useAFKList = config.getBoolean("iShoute.useAFKList", plugin.useAFKList);
        plugin.healthNotify = config.getBoolean("iShoute.eHealthNotify", plugin.healthNotify);
        plugin.healthAchievement = config.getBoolean("iShoute.eHealthAchievement", plugin.healthAchievement);
        plugin.iShAFKHQ = config.getBoolean("iShoute.eHQAFK", plugin.iShAFKHQ);
        plugin.useGroupedList = config.getBoolean("iShoute.useGroupedList", plugin.useGroupedList);
        plugin.listVar = config.getString("iShoute.listVar", plugin.listVar);
        plugin.cLVars = config.getString("iShoute.collapsedListVars", plugin.cLVars);
        plugin.AFKTimer = config.getInt("iShoute.AFKTimer", plugin.AFKTimer);
        plugin.AFKKickTimer = config.getInt("iShoute.AFKKickTimer", plugin.AFKKickTimer);

        plugin.iShChatPB = config.getBoolean("piShout.enable", plugin.spoutPM);
        plugin.spoutPM = config.getBoolean("piShout.spoutPM", plugin.spoutPM);

        if (plugin.AFKTimer < 10 && plugin.AFKTimer > 0)
            plugin.AFKTimer = 10;

        if (plugin.AFKKickTimer < 20 && plugin.AFKKickTimer > 0)
            plugin.AFKKickTimer = 20;

    }

    void defaultConfig() {
        YamlConfiguration config = plugin.iShConfig;
        YamlConfigurationOptions configO = config.options();

        configO.header("iShout Configuration File");

        config.set("format.date", plugin.dateFormat);
        config.set("format.chat", plugin.chatFormat);
        config.set("format.name", plugin.nameFormat);
        config.set("format.event", plugin.eventFormat);
        config.set("format.tabbedList", plugin.tabbedListFormat);
        config.set("format.listCmd", plugin.listCmdFormat);
        config.set("format.me", plugin.meFormat);

        config.set("message.join", plugin.joinMessage);
        config.set("message.leave", plugin.leaveMessage);
        config.set("message.kick", plugin.kickMessage);
        config.set("message.deathInFire", plugin.deathInFire);
        config.set("message.deathOnFire", plugin.deathOnFire);
        config.set("message.deathLava", plugin.deathLava);
        config.set("message.deathInWall", plugin.deathInWall);
        config.set("message.deathStarve", plugin.deathStarve);
        config.set("message.deathCactus", plugin.deathCactus);
        config.set("message.deathFall", plugin.deathFall);
        config.set("message.deathOutOfWorld", plugin.deathOutOfWorld);
        config.set("message.deathGeneric", plugin.deathGeneric);
        config.set("message.deathExplosion", plugin.deathExplosion);
        config.set("message.deathMagic", plugin.deathMagic);
        config.set("message.deathEntity", plugin.deathEntity);
        config.set("message.deathMobFormat", plugin.deathMobFormat);
        config.set("message.deathArrow", plugin.deathArrow);
        config.set("message.deathFireball", plugin.deathFireball);
        config.set("message.deathThrown", plugin.deathThrown);
        config.set("message.heroesMasterT", plugin.hMasterT);
        config.set("message.heroesMasterF", plugin.hMasterF);

        config.set("iShout.apiOnly", plugin.iShoutAPIOnly);
        config.set("iShout.alterEvents", plugin.alterEvents);
        config.set("iShout.alterDeathEvents", plugin.alterDMessages);
        config.set("iShout.chatDistance", plugin.chatDistance);
        config.set("iShout.varIndicator", plugin.varIndicator);
        config.set("iShout.cusVarIndicator", plugin.cusVarIndicator);
        config.set("iShout.spout", plugin.spoutEnabled);
        config.set("iShout.IPCensor", plugin.useIPRestrict);
        config.set("iShout.cLockRange", plugin.cLockRange);

        config.set("suppress.useDeath", plugin.sDeathB);
        config.set("suppress.useJoin", plugin.sJoinB);
        config.set("suppress.useKick", plugin.sKickB);
        config.set("suppress.useQuit", plugin.sQuitB);
        config.set("suppress.maxDeath", plugin.sQuitI);
        config.set("suppress.maxJoin", plugin.sJoinI);
        config.set("suppress.maxKick", plugin.sKickI);
        config.set("suppress.maxQuit", plugin.sQuitI);

        config.set("external.enableBroadcast", plugin.eBroadcast);
        config.set("external.broadcastPort", plugin.eBroadcastPort);
        config.set("external.broadcastIP", plugin.eBroadcastIP);

        config.set("info.useNewInfo", plugin.useNewInfo);
        config.set("info.useLeveledNodes", plugin.useLeveledNodes);
        config.set("info.useOldNodes", plugin.useOldNodes);
        config.set("info.addNewPlayers", plugin.useAddDefault);
        config.set("info.defaultGroup", plugin.iShIDefaultGroup);

        config.set("iShoute.enable", plugin.iShChatEB);
        config.set("iShoute.eHealthNotify", plugin.healthNotify);
        config.set("iShoute.eHealthAchievement", plugin.healthAchievement);
        config.set("iShoute.eHQAFK", plugin.iShAFKHQ);
        config.set("iShoute.useGroupedList", plugin.useGroupedList);
        config.set("iShoute.listVar", plugin.listVar);
        config.set("iShoute.collapsedListVars", plugin.cLVars);
        config.set("iShoute.AFKTimer", plugin.AFKTimer);
        config.set("iShoute.AFKKickTimer", plugin.AFKKickTimer);
        config.set("iShoute.useAFKList", plugin.useAFKList);

        config.set("piShout.enable", plugin.spoutPM);
        config.set("piShout.spoutPM", plugin.spoutPM);

        save();
    }

    void checkConfig() {
        YamlConfiguration config = plugin.iShConfig;
        YamlConfigurationOptions configO = config.options();

        if (!(new File(plugin.getDataFolder(), "config.yml").exists()))
            defaultConfig();

        removeOption(config, "auto-Changed");
        removeOption(config, "iShout.suppressMessages");

        removeOption(config, "iShout.enableList");


        editOption(config, "iShout-date-format", "format.date");
        editOption(config, "iShout-message-format", "format.chat");
        editOption(config, "iShout-name-format", "format.name");
        editOption(config, "iShout-playerEvent-format", "format.event");
        editOption(config, "iShout-playerList-format", "format.list");
        editOption(config, "iShout-join-message", "message.join");
        editOption(config, "iShout-leave-message", "message.leave");
        editOption(config, "iShout-kick-message", "message.kick");
        editOption(config, "iShout-API-only", "iShout.apiOnly");
        editOption(config, "iShout-format-events", "iShout.formatEvents");
        editOption(config, "iShout-chat-distance", "iShout.chatDistance");
        editOption(config, "iShout-info-only", "info.useNewInfo");
        editOption(config, "iShout-oldNodes-only", "info.useOldNodes");
        editOption(config, "iShout-add-info-players", "info.addNewPlayers");

        editOption(config, "format.list", "format.tabbedList");

        editOption(config, "iShout.formatEvents", "iShout.alterEvents");

        checkOption(config, "format.date", plugin.dateFormat);
        checkOption(config, "format.chat", plugin.chatFormat);
        checkOption(config, "format.name", plugin.nameFormat);
        checkOption(config, "format.event", plugin.eventFormat);
        checkOption(config, "format.tabbedList", plugin.tabbedListFormat);
        checkOption(config, "format.listCmd", plugin.listCmdFormat);
        checkOption(config, "format.me", plugin.meFormat);

        checkOption(config, "message.join", plugin.joinMessage);
        checkOption(config, "message.leave", plugin.leaveMessage);
        checkOption(config, "message.kick", plugin.kickMessage);
        checkOption(config, "message.deathInFire", plugin.deathInFire);
        checkOption(config, "message.deathOnFire", plugin.deathOnFire);
        checkOption(config, "message.deathLava", plugin.deathLava);
        checkOption(config, "message.deathInWall", plugin.deathInWall);
        checkOption(config, "message.deathStarve", plugin.deathStarve);
        checkOption(config, "message.deathCactus", plugin.deathCactus);
        checkOption(config, "message.deathFall", plugin.deathFall);
        checkOption(config, "message.deathOutOfWorld", plugin.deathOutOfWorld);
        checkOption(config, "message.deathGeneric", plugin.deathGeneric);
        checkOption(config, "message.deathExplosion", plugin.deathExplosion);
        checkOption(config, "message.deathMagic", plugin.deathMagic);
        checkOption(config, "message.deathEntity", plugin.deathEntity);
        checkOption(config, "message.deathMobFormat", plugin.deathMobFormat);
        checkOption(config, "message.deathArrow", plugin.deathArrow);
        checkOption(config, "message.deathFireball", plugin.deathFireball);
        checkOption(config, "message.deathThrown", plugin.deathThrown);
        checkOption(config, "message.heroesMasterT", plugin.hMasterT);
        checkOption(config, "message.heroesMasterF", plugin.hMasterF);

        checkOption(config, "iShout.apiOnly", plugin.iShoutAPIOnly);
        checkOption(config, "iShout.alterEvents", plugin.alterEvents);
        checkOption(config, "iShout.alterDeathMessages", plugin.alterDMessages);
        checkOption(config, "iShout.chatDistance", plugin.chatDistance);
        checkOption(config, "iShout.varIndicator", plugin.varIndicator);
        checkOption(config, "iShout.cusVarIndicator", plugin.cusVarIndicator);
        checkOption(config, "iShout.spout", plugin.spoutEnabled);
        checkOption(config, "iShout.IPCensor", plugin.useIPRestrict);
        checkOption(config, "iShout.cLockRange", plugin.cLockRange);

        checkOption(config, "suppress.useDeath", plugin.sDeathB);
        checkOption(config, "suppress.useJoin", plugin.sJoinB);
        checkOption(config, "suppress.useKick", plugin.sKickB);
        checkOption(config, "suppress.useQuit", plugin.sQuitB);
        checkOption(config, "suppress.maxDeath", plugin.sQuitI);
        checkOption(config, "suppress.maxJoin", plugin.sJoinI);
        checkOption(config, "suppress.maxKick", plugin.sKickI);
        checkOption(config, "suppress.maxQuit", plugin.sQuitI);

        checkOption(config, "external.enableBroadcast", plugin.eBroadcast);
        checkOption(config, "external.broadcastPort", plugin.eBroadcastPort);
        checkOption(config, "external.broadcastIP", plugin.eBroadcastIP);

        checkOption(config, "info.useNewInfo", plugin.useNewInfo);
        checkOption(config, "info.useLeveledNodes", plugin.useLeveledNodes);
        checkOption(config, "info.useOldNodes", plugin.useOldNodes);
        checkOption(config, "info.addNewPlayers", plugin.useAddDefault);
        checkOption(config, "info.defaultGroup", plugin.iShIDefaultGroup);

        checkOption(config, "iShoute.enable", plugin.iShChatEB);
        checkOption(config, "iShoute.eHealthNotify", plugin.healthNotify);
        checkOption(config, "iShoute.eHealthAchievement", plugin.healthAchievement);
        checkOption(config, "iShoute.eHQAFK", plugin.iShAFKHQ);
        checkOption(config, "iShoute.useGroupedList", plugin.useGroupedList);
        checkOption(config, "iShoute.listVar", plugin.listVar);
        checkOption(config, "iShoute.collapsedListVars", plugin.cLVars);
        checkOption(config, "iShoute.AFKTimer", plugin.AFKTimer);
        checkOption(config, "iShoute.AFKKickTimer", plugin.AFKKickTimer);
        checkOption(config, "iShoute.useAFKList", plugin.useAFKList);

        checkOption(config, "piShout.enable", plugin.iShChatPB);
        checkOption(config, "piShout.spoutPM", plugin.spoutPM);
        
        editValue(config, "message.deathInFire", config.getString("message.deathInFire").replace("+CName", "+killer"));
        editValue(config, "message.deathOnFire", config.getString("message.deathOnFire").replace("+CName", "+killer"));
        editValue(config, "message.deathLava", config.getString("message.deathLava").replace("+CName", "+killer"));
        editValue(config, "message.deathInWall", config.getString("message.deathInWall").replace("+CName", "+killer"));
        editValue(config, "message.deathStarve", config.getString("message.deathStarve").replace("+CName", "+killer"));
        editValue(config, "message.deathCactus", config.getString("message.deathCactus").replace("+CName", "+killer"));
        editValue(config, "message.deathFall", config.getString("message.deathFall").replace("+CName", "+killer"));
        editValue(config, "message.deathOutOfWorld", config.getString("message.deathOutOfWorld").replace("+CName", "+killer"));
        editValue(config, "message.deathGeneric", config.getString("message.deathGeneric").replace("+CName", "+killer"));
        editValue(config, "message.deathExplosion", config.getString("message.deathExplosion").replace("+CName", "+killer"));
        editValue(config, "message.deathMagic", config.getString("message.deathMagic").replace("+CName", "+killer"));
        editValue(config, "message.deathEntity", config.getString("message.deathEntity").replace("+CName", "+killer"));
        editValue(config, "message.deathArrow", config.getString("message.deathArrow").replace("+CName", "+killer"));
        editValue(config, "message.deathFireball", config.getString("message.deathFireball").replace("+CName", "+killer"));
        editValue(config, "message.deathThrown", config.getString("message.deathThrown").replace("+CName", "+killer"));
        
        if (hasChanged) {
            configO.header("iShout Configuration File");

            save();
        }
    }

    void checkOption(YamlConfiguration config, String option, Object defValue) {
        if (!config.isSet(option)) {
            config.set(option, defValue);
            hasChanged = true;
        }
    }

    void editOption(YamlConfiguration config, String oldOption, String newOption) {
        if (config.isSet(oldOption)) {
            config.set(newOption, config.get(oldOption));
            config.set(oldOption, null);
            hasChanged = true;
        }
    }

    void removeOption(YamlConfiguration config, String option) {
        if (config.isSet(option)) {
            config.set(option, null);
            hasChanged = true;
        }
    }

    void editValue(YamlConfiguration config, String option, Object newValue) {
        if (config.isSet(option))
            if (config.get(option) != newValue) {
                config.set(option, newValue);
                hasChanged = true;
            }
    }
}
