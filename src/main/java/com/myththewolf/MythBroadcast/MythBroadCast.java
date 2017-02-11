package com.myththewolf.MythBroadcast;

import java.io.File;

import org.bukkit.plugin.java.JavaPlugin;

import com.myththewolf.MythBroadcast.MySQL.MythSQLConnect;
import com.myththewolf.MythBroadcast.commands.createBroadcast;
import com.myththewolf.MythBroadcast.commands.deleteCast;
import com.myththewolf.MythBroadcast.fields.ConfigProperties;
import com.myththewolf.MythBroadcast.thread.MythThread;

public class MythBroadCast extends JavaPlugin{
	public void onEnable(){
		try {
			
			if (!this.getDataFolder().exists()) {
				this.getDataFolder().mkdirs();
			}
			File file = new File(this.getDataFolder(), "config.yml");
			if (!file.exists()) {
				this.getLogger().info("Config.yml not found, creating!");
				this.saveDefaultConfig();
				ConfigProperties.dumpProperties(this);
			} else {
				this.getLogger().info("Config.yml found, loading!");
				ConfigProperties.dumpProperties(this);
			}
			MythSQLConnect.getConnection();
			MythSQLConnect msc = new MythSQLConnect();
			msc.makeTables();
			
			MythThread mt = new MythThread(this);
			mt.getThreads();
			getCommand("createcast").setExecutor(new createBroadcast(this));
			getCommand("deletecast").setExecutor(new deleteCast(this));
		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
