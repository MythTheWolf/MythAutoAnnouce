package com.myththewolf.MythBroadcast.thread;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class RunnableText implements Runnable {
	private String text;

	public RunnableText(String t) {
		text = t;
	}

	public void run() {
		for (Player p : Bukkit.getServer().getOnlinePlayers()) {
			p.sendMessage(ChatColor.translateAlternateColorCodes('&', text));

		}
		System.out.println("[BROADCAST]" + ChatColor.translateAlternateColorCodes('&', text));
	}

}
