package com.gmail.minionemails.Punish_Time;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class UnForbidCommand implements CommandExecutor {

	private static UnForbidCommand unForbid = new UnForbidCommand();
	public UnForbidCommand() {
	}
	public static UnForbidCommand getUnForbidCommand() {
		return unForbid;
	}
	
	public List<String> getBannedPlayer(String[] args) {
		if (Core.getInstace().getConfig().getString(args[0] + ".banned")== null) {
			return new ArrayList<>();
		}
		return Core.getInstace().getConfig().getStringList(args[0] + ".banned");
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if (cmd.getName().equalsIgnoreCase("unforbid")) {
			if (!sender.hasPermission("punish.unban")) {
				sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "You need the permission: punish.unban");
				return true;
			}
			if (args.length == 0) {
				sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Who should I unban");
				return true;
			}
			Player target = Bukkit.getServer().getPlayerExact(args[0]);
			if (target == null) {
				sender.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + args[0] + ChatColor.RED + "" + ChatColor.BOLD + " is not online!");
				return true;
			}else if (args.length == 1) {
				sender.sendMessage(ChatColor.RED + "" + ChatColor.BOLD + "Why should " + ChatColor.GOLD + "" + ChatColor.BOLD + args[0] + ChatColor.RED + "" + ChatColor.BOLD + " be unbanned?" );
				return true;
			}
			StringBuilder message = new StringBuilder();
			for (int i = 1; i < args.length; i++) {
				if (i > 1) message.append(" ");
				message.append(args[i]);
			}
			Bukkit.getServer().getPlayer(args[0]).setBanned(false);
			List<String> log = getBannedPlayer(args);
			log.remove(message.toString());
			Core.getInstace().getConfig().set(null, log);
			try {
				Core.getInstace().getConfig().save(Core.Bans);
			} catch (IOException e) {
				e.printStackTrace();
			}
			Bukkit.broadcastMessage(ChatColor.GOLD + "" + ChatColor.BOLD + args[0] + ChatColor.RED + " was unbanned by " + ChatColor.GOLD + "" + ChatColor.BOLD + sender.getName());
		}
		return true;
	}

}
