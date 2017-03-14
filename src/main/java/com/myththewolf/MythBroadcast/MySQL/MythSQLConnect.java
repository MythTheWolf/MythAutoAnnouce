package com.myththewolf.MythBroadcast.MySQL;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.bukkit.Bukkit;

import com.myththewolf.MythBroadcast.fields.ConfigProperties;

import java.sql.Connection;

public class MythSQLConnect {
	private static Connection con;
	private PreparedStatement ps;
	public static Connection getConnection()
	{
		String HOST = ConfigProperties.SQL_HOST;
		String PORT = ConfigProperties.SQL_PORT;
		String USER = ConfigProperties.SQL_USERNAME;
		String PASS = ConfigProperties.SQL_PASSWORD;
		String DATABASE = ConfigProperties.SQL_DATABASE;
		if(!isConnected())
		{
		try{
			con = DriverManager.getConnection("jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE  + "?useUnicode=true&characterEncoding=UTF-8&autoReconnect=true", USER,
					PASS);
			return con;
			
		}catch(SQLException e)
		{
			Bukkit.getConsoleSender().sendMessage("SERVERE: MySQL Connection FAILED!");
			e.printStackTrace();
			return null;
		}
		}else{
			return con;
		}
	}
	
	
		// isConnected
		public static boolean isConnected()
		{
			return (con == null ? false : true);
		}

		
		public void makeTables()
		{
			try{
				ps = (PreparedStatement) con.prepareStatement("CREATE TABLE IF NOT EXISTS `MythAnnounce_Jobs` ( `ID` INT NOT NULL AUTO_INCREMENT ,`key` VARCHAR(255) NOT NULL , `text` VARCHAR(255) NOT NULL , `ticks` BIGINT NOT NULL , PRIMARY KEY (`ID`)) ENGINE = InnoDB;");
				ps.executeUpdate();
				ps.close();
			}catch(SQLException e){
				Bukkit.getConsoleSender().sendMessage("SERVERE: Fatal MySQL Error!");
				e.printStackTrace();
			}
		}
	
}
