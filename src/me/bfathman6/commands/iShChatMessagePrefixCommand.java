package me.bfathman6.commands;

import me.bfathman6.iShout.iShout;
import me.bfathman6.util.Messanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class iShChatMessagePrefixCommand implements CommandExecutor {
    iShout plugin;

    public iShChatMessagePrefixCommand(iShout plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName();

        if (cmd.equalsIgnoreCase("mchatmessageprefix")) {
            if (args.length > 0) {
                if (args[0].equalsIgnoreCase("set")) {
                    if (!plugin.getAPI().checkPermissions(sender, "mchat.messageprefix")) {
                        Messanger.sendMessage(sender, plugin.getLocale().getOption("noPermissions") + " " + cmd + ".");
                        return true;
                    }

                    String message = "";

                    for (String arg : args)
                        message += " " + arg;

                    message = message.trim();

                    plugin.iShPrefix.put(sender.getName(), message);

                    return true;
                } else if (args[0].equalsIgnoreCase("remove")) {
                    plugin.iShPrefix.put(sender.getName(), "");

                    return true;
                }
            }
        }

        return false;
    }
}