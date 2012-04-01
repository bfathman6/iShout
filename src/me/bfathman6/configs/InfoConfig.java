package me.bfathman6.configs;

import me.bfathman6.iShout.iShout;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.file.YamlConfigurationOptions;

import java.io.File;
import java.io.IOException;

public class InfoConfig {
    iShout plugin;
    Boolean hasChanged = false;

    public InfoConfig(iShout plugin) {
        this.plugin = plugin;
    }

    public void reload() {
        plugin.iShIConfig = YamlConfiguration.loadConfiguration(plugin.iShIConfigF);
        plugin.iShIConfig.options().indent(4);
    }

    void save() {
        try {
            plugin.iShIConfig.save(plugin.iShIConfigF);
        } catch (IOException ignored) {}
    }

    void defaultConfig() {
        YamlConfiguration config = plugin.iShIConfig;
        YamlConfigurationOptions configO = config.options();

        configO.header("Info Config");

        config.set("groupnames.Admin", "[A]");
        config.set("groupnames.S-Admin", "[Sa]");
        config.set("groupnames.J-Admin", "[ja]");
        config.set("groupnames.Member", "[M]");

        config.set("worldnames.D3GN", "[D]");
        config.set("worldnames.DtK", "[DtK]");
        config.set("worldnames.Nether", "[N]");
        config.set("worldnames.Hello", "[H]");

        config.set("users.bfathman6.group", "admin");
        config.set("users.bfathman6.worlds.DtK.prefix", "");
        config.set("users.bfathamn6.info.suffix", "");
        config.set("users.bfathman6.info.prefix", "");

        config.set("groups.admin.worlds.DtK.prefix", "");
        config.set("groups.admin.info.prefix", "");
        config.set("groups.admin.info.suffix", "");
        config.set("groups.admin.info.custVar", "");

        config.set("mname.bfathman6", "bfathman6");
        config.set("mname.Laura_RS", "bfathman6's Woman");

        save();
    }

    public void load() {
        if (!(new File(plugin.getDataFolder(), "info.yml").exists()))
            defaultConfig();

        YamlConfiguration config = plugin.iShIConfig;
        YamlConfigurationOptions configO = config.options();

        if (config.get("users") == null) {
            config.set("users.bfathman6.group", "admin");
            config.set("users.bfathman6.worlds.DtK.prefix", "");
            config.set("users.bfathman6.info.suffix", "");
            config.set("users.bfathman6.info.prefix", "");

            hasChanged = true;
        }

        if (config.get("groups") == null) {
            config.set("groups.admin.worlds.DtK.prefix", "");
            config.set("groups.admin.info.prefix", "");
            config.set("groups.admin.info.suffix", "");
            config.set("groups.admin.info.custVar", "");

            hasChanged = true;
        }

        if (config.get("groupnames") == null) {
            config.set("groupnames.Admin", "[A]");
            config.set("groupnames.SAdmin", "[Sa]");
            config.set("groupnames.JAdmin", "[Ja]");
            config.set("groupnames.Member", "[M]");

            hasChanged = true;
        }

        if (config.get("worldnames") == null) {
            config.set("worldnames.D3GN", "[D]");
            config.set("worldnames.DtK", "[DtK]");
            config.set("worldnames.Nether", "[N]");
            config.set("worldnames.Hello", "[H]");

            hasChanged = true;
        }

        if (config.get("mname") == null) {
            config.set("mname.bfathman6", "bfathman6");
            config.set("mname.Laura_RS", "bfathman6's Woman");

            hasChanged = true;
        }


        if (hasChanged) {
            configO.header("Info Config");

            save();
        }
    }
}