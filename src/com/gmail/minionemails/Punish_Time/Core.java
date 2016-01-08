package com.gmail.minionemails.Punish_Time;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Core extends JavaPlugin implements Listener {
	
	private static Core instance;

	public static Core getInstace() { return instance; }
	
	ForbidCommand ban = new ForbidCommand();
	
	public static File Bans;
	public static File Mutes;
	
	FileConfiguration newBans;
	FileConfiguration newMutes;
	
	public void onEnable() {
		instance = this;
		
		this.getCommand("forbid").setExecutor(ban);
		
		Bans = new File(getDataFolder(), "Bans.yml");
		Mutes = new File(getDataFolder(), "Mutes.yml");
		
		newBans = YamlConfiguration.loadConfiguration(Bans);
		newMutes = YamlConfiguration.loadConfiguration(Mutes);
		
		saveBanConfig();
		saveMuteConfig();
		saveDefaultConfig();
		
		getServer().getPluginManager().registerEvents(new PlayerListener(), this);
	}
	
	public void onDisbable() {
		instance = null;
	}
	
	public void saveBanConfig() {
		try {
			newBans.save(Bans);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void saveMuteConfig() {
		try {
			newMutes.save(Mutes);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
