package me.bfathman6.configs;

import me.bfathman6.iShout.iShout;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;

import java.io.File;
import java.io.IOException;

public class CensorConfig {
    iShout plugin;

    public CensorConfig(iShout plugin) {
        this.plugin = plugin;

        reload();
    }

    public void reload() {
        plugin.iShCConfig = YamlConfiguration.loadConfiguration(plugin.iShCConfigF);
        plugin.iShCConfig.options().indent(4);
    }

    void save() {
        try {
            plugin.iShCConfig.save(plugin.iShCConfigF);
        } catch (IOException ignored) {}
    }

    public void load() {
        if (!(new File(plugin.getDataFolder(), "censor.yml").exists()))
            defaultConfig();
    }

    void defaultConfig() {
        YamlConfiguration config = plugin.iShCConfig;
        YamlConfigurationOptions configO = config.options();

        configO.header("iShout Censor File");

        config.set("fuck", "fawg");
        config.set("cunt", "punt");
        config.set("shit", "feces");
        config.set("dick", "LARGE PENIS");
        config.set("bfat", "bfathman6");

        save();
    }
}