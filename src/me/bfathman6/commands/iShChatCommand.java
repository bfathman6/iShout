package me.bfathman6.commands;

import me.bfathman6.api.InfoType;
import me.bfathman6.iShout.iShout;
import me.bfathman6.util.Messanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class iShChatCommand implements CommandExecutor {
    iShout plugin;

    public iShChatCommand(iShout plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player player = null;

        if (sender instanceof Player)
            player = (Player) sender;

        String cmd = command.getName();

        if (args.length == 0)
            return false;

        if (cmd.equalsIgnoreCase("iShout")) {
            if (args[0].equalsIgnoreCase("version")) {
                if (sender instanceof Player) {
                    if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.version")) {
                        Messanger.sendMessage(sender, "You are not to view the version of iShout.");
                        return true;
                    }

                }

                String cVersion = "&6MineCraft Version: &2" + plugin.pdfFile.getVersion();

                cVersion = cVersion.replaceFirst("-", "^*^&6Jenkins Build&5#&5: &2");
                cVersion = cVersion.replaceFirst("-", "^*^&6Release Version: &2");
                cVersion = cVersion.replaceFirst("_", "^*^&6Fix&5#&5: &2");

                String[] vArray = cVersion.split("\\^\\*\\^");

                Messanger.sendMessage(sender, "&6Full Version: &1" + plugin.pdfFile.getVersion());

                for (String string : vArray)
                    Messanger.sendMessage(sender, string);

                return true;
            } else if (args[0].equalsIgnoreCase("reload")
                    || args[0].equalsIgnoreCase("r")) {
                if (args.length > 1)
                    if (args[1].equalsIgnoreCase("config")
                            || args[1].equalsIgnoreCase("co")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.reload.config")) {
                                Messanger.sendMessage(sender, "You are not allowed to reload iShout.");
                                return true;
                            }

                        plugin.getMainConfig().reload();
                        plugin.getMainConfig().load();
                        Messanger.sendMessage(sender, "Config Reloaded.");
                        return true;
                    } else if (args[1].equalsIgnoreCase("info")
                            || args[1].equalsIgnoreCase("i")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.reload.info")) {
                                Messanger.sendMessage(sender, "You are not allowed to reload iShout.");
                                return true;
                            }

                        plugin.getInfoConfig().reload();
                        plugin.getInfoConfig().load();
                        Messanger.sendMessage(sender, "Info Reloaded.");
                        return true;
                    } else if (args[1].equalsIgnoreCase("censor")
                            || args[1].equalsIgnoreCase("ce")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.reload.censor")) {
                                Messanger.sendMessage(sender, "You are not allowed to reload iShout.");
                                return true;
                            }

                        plugin.getCensorConfig().reload();
                        plugin.getCensorConfig().load();
                        Messanger.sendMessage(sender, "Censor Reloaded.");
                        return true;
                    } else if (args[1].equalsIgnoreCase("locale")
                            || args[1].equalsIgnoreCase("l")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.reload.locale")) {
                                Messanger.sendMessage(sender, "You are not allowed to reload iShout.");
                                return true;
                            }

                        plugin.getLocale().reload();
                        Messanger.sendMessage(sender, "Censor Reloaded.");
                        return true;
                    } else if (args[1].equalsIgnoreCase("all")
                            || args[1].equalsIgnoreCase("a")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.reload.all")) {
                                Messanger.sendMessage(sender, "You are not allowed to reload iShout.");
                                return true;
                            }

                        plugin.reloadConfigs();
                        plugin.setupConfigs();
                        Messanger.sendMessage(sender, "All Config's Reloaded.");
                        return true;
                    }
            } else if (args[0].equalsIgnoreCase("u")
                    || args[0].equalsIgnoreCase("user")) {
                if (args.length == 1) {
                    Messanger.sendMessage(sender, "Use '/iShout user add/set/remove' for user help.");
                    return true;
                } else if (args[1].equalsIgnoreCase("a")
                        || args[1].equalsIgnoreCase("add")) {
                    if (args.length == 2) {
                        Messanger.sendMessage(sender, "Usage for '/iShout user add':");
                        sender.sendMessage("- /iShout user add player Player DefaultGroup");
                        sender.sendMessage("- /iShout user add ivar Player InfoVariable InfoValue");
                        sender.sendMessage("- /iShout user add world Player World");
                        sender.sendMessage("- /iShout user add wvar Player World InfoVariable InfoValue");
                        return true;
                    } else if (args[2].equalsIgnoreCase("p")
                            || args[2].equalsIgnoreCase("player")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.user.add.player")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().addBase(args[3], args[4]);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout user add player Player DefaultGroup");
                            return true;
                        }
                    } else if (args[2].equalsIgnoreCase("iVar")
                            || args[2].equalsIgnoreCase("infoVariable")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.user.add.ivar")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().setInfoVar(args[3], InfoType.USER, args[4], stringArgs(args, 5));
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout user add ivar Player InfoVariable InfoValue");
                            return true;
                        }
                    } else if (args[2].equalsIgnoreCase("w")
                            || args[2].equalsIgnoreCase("world")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.user.add.world")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().addWorld(args[3], InfoType.USER, args[4]);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout user add world Player World");
                            return true;
                        }
                    } else if (args[2].equalsIgnoreCase("wVar")
                            || args[2].equalsIgnoreCase("worldVariable")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.user.add.wvar")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().setWorldVar(args[3], InfoType.USER, args[4], args[5], stringArgs(args, 6));
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout user add world Player World InfoVariable InfoValue");
                            return true;
                        }
                    }
                }  else if (args[1].equalsIgnoreCase("s")
                        || args[1].equalsIgnoreCase("set")) {
                    if (args.length == 2) {
                        Messanger.sendMessage(sender, "Usage for '/iShout user set':");
                        sender.sendMessage("- /iShout user set group Player NewGroup");
                        return true;
                    } else if (args[2].equalsIgnoreCase("g")
                            || args[2].equalsIgnoreCase("group")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.user.set.group")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().setGroup(args[3], args[4]);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout user set group Player NewGroup");
                            return true;
                        }
                    }
                } else if (args[1].equalsIgnoreCase("r")
                        || args[1].equalsIgnoreCase("remove")) {
                    if (args.length == 2) {
                        Messanger.sendMessage(sender, "Usage for '/iShout user remove':");
                        sender.sendMessage("- /iShout user remove Player");
                        sender.sendMessage("- /iShout user remove Player InfoVariable");
                        sender.sendMessage("- /iShout user remove Player World");
                        sender.sendMessage("- /iShout user remove Player World InfoVariable");
                        return true;
                    } else if (args[2].equalsIgnoreCase("p")
                            || args[2].equalsIgnoreCase("player")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.user.remove.player")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().removeBase(args[3], InfoType.USER);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout user remove Player");
                            return true;
                        }
                    } else if (args[2].equalsIgnoreCase("iVar")
                            || args[2].equalsIgnoreCase("infoVariable")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.user.remove.ivar")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().removeInfoVar(args[3], InfoType.USER, args[4]);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout user remove Player InfoVariable");
                            return true;
                        }
                    } else if (args[2].equalsIgnoreCase("w")
                            || args[2].equalsIgnoreCase("world")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.user.remove.world")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().removeWorld(args[3], InfoType.USER, args[4]);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout user remove Player World");
                            return true;
                        }
                    } else if (args[2].equalsIgnoreCase("wVar")
                            || args[2].equalsIgnoreCase("worldVariable")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.user.remove.wvar")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().removeWorldVar(args[3], InfoType.USER, args[4], args[5]);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout user remove Player World InfoVariable");
                            return true;
                        }
                    }
                }
            } else if (args[0].equalsIgnoreCase("g")
                    || args[0].equalsIgnoreCase("group")) {
                if (args.length == 1) {
                    Messanger.sendMessage(sender, "Use '/iShout group add/edit/remove' for group help.");
                    return true;
                } else if (args[1].equalsIgnoreCase("a")
                        || args[1].equalsIgnoreCase("add")) {
                    if (args.length == 2) {
                        Messanger.sendMessage(sender, "Usage for '/iShout group add':");
                        sender.sendMessage("- /iShout group add group Group");
                        sender.sendMessage("- /iShout group add ivar Group InfoVariable InfoValue");
                        sender.sendMessage("- /iShout group add world Group World");
                        sender.sendMessage("- /iShout group add world Group World InfoVariable InfoValue");
                        return true;
                    } else if (args[2].equalsIgnoreCase("g")
                            || args[2].equalsIgnoreCase("group")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.group.add.group")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().addBase(args[3], InfoType.GROUP);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout group add group Group");
                            return true;
                        }
                    } else if (args[2].equalsIgnoreCase("iVar")
                            || args[2].equalsIgnoreCase("infoVariable")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.group.add.ivar")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().setInfoVar(args[3], InfoType.GROUP, args[4], stringArgs(args, 5));
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout group add ivar Group InfoVariable InfoValue");
                            return true;
                        }
                    } else if (args[2].equalsIgnoreCase("w")
                            || args[2].equalsIgnoreCase("world")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.group.add.world")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().addWorld(args[3], InfoType.GROUP, args[4]);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout group add world Group World");
                            return true;
                        }
                    } else if (args[2].equalsIgnoreCase("wVar")
                            || args[2].equalsIgnoreCase("worldVariable")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.group.add.wvar")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().setWorldVar(args[3], InfoType.GROUP, args[4], args[5], stringArgs(args, 6));
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout group add wvar Group World InfoVariable InfoValue");
                            return true;
                        }
                    }
                } else if (args[1].equalsIgnoreCase("r")
                        || args[1].equalsIgnoreCase("remove")) {
                    if (args.length == 2) {
                        Messanger.sendMessage(sender, "Usage for '/iShout group remove':");
                        sender.sendMessage("- /iShout group remove Group");
                        sender.sendMessage("- /iShout group remove Group InfoVariable");
                        sender.sendMessage("- /iShout group remove Group World");
                        sender.sendMessage("- /iShout group remove Group World InfoVariable");
                        return true;
                    } else if (args[2].equalsIgnoreCase("g")
                            || args[2].equalsIgnoreCase("group")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.group.remove.group")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().removeBase(args[3], InfoType.GROUP);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout group remove group Group");
                            return true;
                        }
                    } else if (args[2].equalsIgnoreCase("iVar")
                            || args[2].equalsIgnoreCase("infoVariable")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.group.remove.ivar")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().removeInfoVar(args[3], InfoType.GROUP, args[4]);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout group remove ivar Group InfoVariable");
                            return true;
                        }
                    } else if (args[2].equalsIgnoreCase("w")
                            || args[2].equalsIgnoreCase("world")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.group.remove.world")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().removeWorld(args[3], InfoType.GROUP, args[4]);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout group remove world Group World");
                            return true;
                        }
                    } else if (args[2].equalsIgnoreCase("wVar")
                            || args[2].equalsIgnoreCase("worldVariable")) {
                        if (sender instanceof Player)
                            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.group.remove.wvar")) {
                                Messanger.sendMessage(sender, "You don't have Permission to do that.");
                                return true;
                            }
                        try {
                            plugin.getWriter().removeWorldVar(args[3], InfoType.GROUP, args[4], args[5]);
                            Messanger.sendMessage(sender, "Info Addition Successful.");
                            return true;
                        } catch (ArrayIndexOutOfBoundsException er) {
                            Messanger.sendMessage(sender, "/iShout group remove wvar Group World InfoVariable");
                            return true;
                        }
                    }
                }
            }
        }

        return false;
    }

    String stringArgs(String[] args, Integer startingPoint) {
        String argString = "";

        for (int i = startingPoint; i < args.length; ++i) {
            if (i == args.length - 1) {
                argString += args[i];
            } else
                argString += args[i] + " ";
        }

        return argString;
    }
}