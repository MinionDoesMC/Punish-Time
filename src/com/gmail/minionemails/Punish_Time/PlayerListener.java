package com.gmail.minionemails.Punish_Time;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

public class PlayerListener implements Listener {
	@EventHandler
	public void onPlayerLogin(PlayerLoginEvent e) {
		Player player = e.getPlayer();
		if (player.isBanned() == true) {
			e.setKickMessage(ChatColor.RED + "You are banned for " + Core.getInstace().getConfig().getString(player.getName() + ".banned") + "\n How long?: Permanent \n Appeal at:");
		}
	}
}
