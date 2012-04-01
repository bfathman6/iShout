package me.bfathman6.commands;

import me.bfathman6.iShout.iShout;
import me.bfathman6.util.Messanger;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class iShChatMuteCommand implements CommandExecutor {
    iShout plugin;

    public iShChatMuteCommand(iShout plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName();

        if (cmd.equalsIgnoreCase("iShoutmute")) {
            if (args.length > 0) {
                if (!plugin.getAPI().checkPermissions(sender, "iShout.mute")) {
                    Messanger.sendMessage(sender, plugin.getLocale().getOption("noPermissions") + " " + cmd + ".");
                    return true;
                }
                
                String target = args[0];
                
                if (plugin.getServer().getPlayer(args[0]) != null) {
                    target = plugin.getServer().getPlayer(args[0]).getName();
                }

                plugin.isMuted.put(target, true);

                Messanger.sendMessage(sender, "Target '" + target  + "' successfully muted. To unmute use this command again.");

                return true;
            }
        }

        return false;
    }
}