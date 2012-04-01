package me.bfathman6.commands;

import me.bfathman6.iShout.iShout;
import me.bfathman6.util.Messanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class iShChatSayCommand implements CommandExecutor {
    iShout plugin;

    public iShChatSayCommand(iShout plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName();

        if (cmd.equalsIgnoreCase("iShoutsay")) {
            if (args.length > 0) {
                String message = "";

                for (String arg : args)
                    message += " " + arg;

                message = message.trim();

                if (sender instanceof Player) {
                    Player player = (Player) sender;

                    if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.say")) {
                        Messanger.sendMessage(sender, plugin.getLocale().getOption("noPermissions") + " " + cmd + ".");
                        return true;
                    }
                }

                plugin.getServer().broadcastMessage(plugin.getLocale().getOption("sayName") + " " +  message);
                return true;
            }
        }

        return false;
    }
}