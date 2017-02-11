package com.myththewolf.MythBroadcast.thread;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.myththewolf.MythBroadcast.MySQL.MythSQLConnect;
import com.myththewolf.MythBroadcast.fields.ThreadMap;

public class MythThread {
	public Connection con = MythSQLConnect.getConnection();
	public PreparedStatement ps;
	public ResultSet rs;
	public JavaPlugin pl;

	public MythThread(JavaPlugin i) {
		pl = i;
	}

	public void getThreads() throws SQLException {
		pl.getLogger().info("Creating threads...");
		ps = (PreparedStatement) con.prepareStatement("SELECT * FROM MythAnnounce_Jobs");
		rs = ps.executeQuery();
		while (rs.next()) {
			int  ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new RunnableText(rs.getString("text")), 0L,
					Long.parseLong(rs.getString("ticks")));
			ThreadMap.addMap(ID, rs.getString("key"));
		}

	}

	public void cancelThread(String name) {
		Bukkit.getScheduler().cancelTask(ThreadMap.threadMap.get(name));
	}

	public void createThread(String name) throws SQLException {
		ps = (PreparedStatement) con.prepareStatement("SELECT * FROM MythAnnounce_Jobs WHERE `key` = ?");
		ps.setString(1, name);
		rs = ps.executeQuery();
		while (rs.next()) {
			int ID = Bukkit.getScheduler().scheduleSyncRepeatingTask(pl, new RunnableText(rs.getString("text")), 0L,
					Long.parseLong(rs.getString("ticks")));
			ThreadMap.addMap(ID, name);

		}
	}
}
