package com.myththewolf.MythBroadcast.MySQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseCommands {
	private Connection con = MythSQLConnect.getConnection();
	private PreparedStatement ps;
	private ResultSet rs;
	public boolean exists(String name) throws SQLException {
		ps = (PreparedStatement) con.prepareStatement("SELECT * FROM MythAnnounce_Jobs WHERE `key` = ?");
		ps.setString(1, name);
		rs = ps.executeQuery();
		return rs.next();
	}

	public void addEvent(String key, Long interval, String text) throws SQLException {
		ps = (PreparedStatement) con.prepareStatement("INSERT INTO MythAnnounce_Jobs (`key`,`text`,`ticks`) VALUES (?,?,?)");
		ps.setString(1, key);
		ps.setString(2, text);
		ps.setLong(3, interval);
		ps.executeUpdate();
	}

	public void deleteEvent(String key) throws SQLException {
		ps = (PreparedStatement) con.prepareStatement("DELETE FROM MythAnnounce_Jobs WHERE `key` = ?");
		ps.setString(1, key);
		ps.executeUpdate();
		
	}
}
