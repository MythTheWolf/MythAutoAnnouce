package com.myththewolf.MythBroadcast.fields;

import java.util.HashMap;

public class ThreadMap {
	public static HashMap<String, Integer> threadMap = new HashMap<String, Integer>();

	public static void addMap(int id, String name) {
		threadMap.put(name, id);
	}
}
