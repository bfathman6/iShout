package me.bfathman6.commands;

import me.bfathman6.iShout.iShout;
import me.bfathman6.util.Messanger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PMChatLeaveCommand implements CommandExecutor {
    iShout plugin;

    public PMChatLeaveCommand(iShout plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        String cmd = command.getName();

        if (!(sender instanceof Player)) {
            sender.sendMessage(formatPMessage(Messanger.addColour("Console's can't send PM's.")));
            return true;
        }

        Player player = (Player) sender;
        String pName = player.getName();

        if (cmd.equalsIgnoreCase("pmchatleave")) {
            String rName = plugin.chatPartner.get(pName);
            Player recipient = null;

            if (rName != null)
                recipient = plugin.getServer().getPlayer(rName);

            if (plugin.isConv.get(pName) == null)
                player.sendMessage(formatPMessage(Messanger.addColour("You are not currently in a Convo.")));
            else if (plugin.isConv.get(pName)) {
                player.sendMessage(formatPMessage(Messanger.addColour("You have left the convo.")));
                recipient.sendMessage(formatPMessage(Messanger.addColour("Conversation has been ended.")));
                plugin.isConv.put(pName, false);
                plugin.isConv.put(rName, false);
                plugin.chatPartner.remove(rName);
                plugin.chatPartner.remove(pName);
            } else
                player.sendMessage(formatPMessage(Messanger.addColour("You are not currently in a Convo.")));

            return true;
        }

        return false;
    }

    String formatPMessage(String message) {
        return (Messanger.addColour("&4[" + (plugin.pdfFile.getName()) + "] " + message));
    }
}