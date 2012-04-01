package me.bfathman6.api;

import me.bfathman6.iShout.iShout;

public class iShInfoWriter {
    iShout plugin;

    public iShInfoWriter(iShout plugin) {
        this.plugin = plugin;
    }

    public void addBase(String name, InfoType type) {
        plugin.getWriter().addBase(name, type);
    }

    public void addBase(String player, String group) {
        plugin.getWriter().addBase(player, group);
    }

    public void addWorld(String name, InfoType type, String world) {
        plugin.getWriter().addWorld(name, type, world);
    }

    public void setInfoVar(String name, InfoType type, String var, Object value) {
        plugin.getWriter().setInfoVar(name, type, var, value);
    }

    public void setWorldVar(String name, InfoType type, String world, String var, Object value) {
        plugin.getWriter().setWorldVar(name, type, world, var, value);
    }

    public void setGroup(String player, String group) {
        plugin.getWriter().setGroup(player, group);
    }

    public void removeBase(String name, InfoType type) {
        plugin.getWriter().removeBase(name, type);
    }

    public void removeInfoVar(String name, InfoType type, String var) {
        plugin.getWriter().removeInfoVar(name, type, var);
    }

    public void removeWorld(String name, InfoType type, String world) {
        plugin.getWriter().removeWorld(name, type, world);
    }

    public void removeWorldVar(String name, InfoType type, String world, String var) {
        plugin.getWriter().removeWorldVar(name, type, world, var);
    }
}
