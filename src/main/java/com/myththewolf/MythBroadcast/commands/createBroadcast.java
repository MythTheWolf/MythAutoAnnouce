package com.myththewolf.MythBroadcast.commands;

import java.sql.SQLException;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import com.myththewolf.MythBroadcast.MySQL.DatabaseCommands;
import com.myththewolf.MythBroadcast.MySQL.Utils;
import com.myththewolf.MythBroadcast.thread.MythThread;

public class createBroadcast implements CommandExecutor {
	private DatabaseCommands dbc = new DatabaseCommands();
	private JavaPlugin pl;

	public createBroadcast(JavaPlugin i) {
		pl = i;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command arg1, String arg2, String[] args) {
		if (!sender.hasPermission("mythbroadcast.create")) {
			sender.sendMessage("You can't create broadcast events!");
			return true;
		}
		if (args.length < 3) {
			sender.sendMessage("Usage: /createbroadcast [name] [interval] [text]");
			return true;
		}
		try {
			if (dbc.exists(args[0])) {
				sender.sendMessage("Event already created!");
				return true;
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			dbc.addEvent(args[0], Long.parseLong(args[1]), Utils.makeString(args, 2));
			MythThread mt = new MythThread(pl);
			mt.createThread(args[0]);
			sender.sendMessage("Cast created!");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}
