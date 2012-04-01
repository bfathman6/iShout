package me.bfathman6.events;

import me.bfathman6.channel.Channel;
import me.bfathman6.iShout.iShout;

import me.bfathman6.util.Messanger;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

import java.util.*;

public class ChannelEventListener implements Listener {
    iShout plugin;

    public ChannelEventListener(iShout plugin) {
        this.plugin = plugin;
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onPlayerChat(PlayerChatEvent event) {
        if (event.isCancelled())
            return;

        Player player = event.getPlayer();

        Set<Channel> channels = plugin.getChannelManager().getPlayersActiveChannels(player.getName());

        if (channels.size() < 1)
            return;

        if (event.getMessage() == null)
            return;

        for (Channel channel : channels)
            Messanger.log(channel.getName());

        for (Channel channel : channels)
            channel.broadcastToChannel(player,
                    plugin.getAPI().ParseChatMessage(player.getName(), player.getWorld().getName(), event.getMessage()));

        event.setCancelled(true);
    }
}
