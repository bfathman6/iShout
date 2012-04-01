package me.bfathman6.commands;

import me.bfathman6.iShout.iShout;
import me.bfathman6.util.Messanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class iShChatAFKCommand implements CommandExecutor {
    iShout plugin;

    public iShChatAFKCommand(iShout plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName();

        if (cmd.equalsIgnoreCase("iShoutchatafk")) {
            String message = " Away From Keyboard";

            if (args.length > 0) {
                message = "";

                for (String arg : args)
                    message += " " + arg;
            }

            if (!(sender instanceof Player)) {
                Messanger.sendMessage(sender, "Console's can't be AFK.");
                return true;
            }

            Player player = (Player) sender;

            if (plugin.isAFK.get(player.getName()) != null)
                if (plugin.isAFK.get(player.getName())) {
                    plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "iShoutafkother " + player.getName());
                    return true;
                }

            if (!plugin.getAPI().checkPermissions(player.getName(), player.getWorld().getName(), "iShout.afk.self")) {
                Messanger.sendMessage(player, plugin.getLocale().getOption("noPermissions") + " " + cmd + ".");
                return true;
            }

            plugin.getServer().dispatchCommand(plugin.getServer().getConsoleSender(), "iShoutafkother " + sender.getName() + message);
            return true;
        }

        return false;
    }
}