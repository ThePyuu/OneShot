package de.pyuu.oneshot.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;



public class Data {

	
	
	public static boolean devMode = false;
	/*
	 * Array-Listen, die Spieler in lebend und tod einteilen
	 */
	
	public static ArrayList<Player> alive = new ArrayList<Player>();
	public static ArrayList<Player> death = new ArrayList<Player>();
	public static ArrayList<Player> spec = new ArrayList<Player>();
	
	//Partikel Trail
	public static ArrayList<String> defaulttrail = new ArrayList<>();
	
	/*
	 * Lore für die verschiedenen Items
	 */
	
	public static List<String> lore = new ArrayList<String>();
	public static List<String> lore_bow = new ArrayList<String>();
	public static List<String> lore_sword = new ArrayList<String>();
		
	public static ArrayList<String> unstuckUsed = new ArrayList<String>();
	
	/*
	 * Leben
	 */
	public static HashMap<Player, Integer> live = new HashMap<Player, Integer>();
	
	//SpawnSystem
	public static Map<Integer, Location> spawns = new HashMap<Integer, Location>();
	public static File file = new File("plugins//OneShot//Spawns.yml");
	public static FileConfiguration cfg = YamlConfiguration.loadConfiguration(file); 
	
	public static boolean lobbyCDisRunning = false;
	public static boolean lobbyIsStarting = false;
	
	public static boolean hasChecked = false;
	
	public static String version = "1.3.6 (20.06 16:30)";
	
	


}
