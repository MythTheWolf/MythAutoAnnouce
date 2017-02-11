package com.myththewolf.MythBroadcast.fields;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class ConfigProperties {
	public static FileConfiguration cfg;
	public static String SQL_PORT;
	public static String SQL_HOST;
	public static String SQL_USERNAME;
	public static String SQL_PASSWORD;
	public static String SQL_DATABASE;
	public static boolean DEBUG;

	public static void dumpProperties(JavaPlugin i) {
		cfg = i.getConfig();
		SQL_HOST = cfg.getString("SQL-HOST");
		SQL_PORT = cfg.getString("SQL-PORT");
		SQL_DATABASE = cfg.getString("SQL-DATABASE");
		SQL_USERNAME = cfg.getString("SQL-USER");
		SQL_PASSWORD = cfg.getString("SQL-PASSWORD");
	}

}
