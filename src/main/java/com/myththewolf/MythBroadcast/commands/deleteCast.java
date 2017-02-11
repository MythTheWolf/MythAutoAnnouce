package com.myththewolf.MythBroadcast.commands;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.myththewolf.MythBroadcast.MySQL.DatabaseCommands;
import com.myththewolf.MythBroadcast.thread.MythThread;

public class deleteCast implements CommandExecutor {
	private DatabaseCommands dbc = new DatabaseCommands();
	private JavaPlugin pl;

	public deleteCast(JavaPlugin i) {
		pl = i;
	}
	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (!sender.hasPermission("mythbroadcast.delete")) {
			sender.sendMessage("You can't create broadcast events!");
			return true;
		}
		if (args.length < 1) {
			sender.sendMessage("Usage: /deletecast [name]");
			return true;
		}
		try {
			if (!dbc.exists(args[0])) {
				sender.sendMessage("Event not created!");
				return true;
			}
			dbc.deleteEvent(args[0]);
			MythThread mt = new MythThread(pl);
			mt.cancelThread(args[0]);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return true;
	}

}
