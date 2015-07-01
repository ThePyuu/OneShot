package de.pyuu.oneshot.methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.pyuu.oneshot.Main;

public class Announcer {
	
	public static void sendAnnounce(Player p) {
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				Bukkit.broadcastMessage("§eAb Version 1.4 kann man mit Kills verschieden Freatures Freischalten !");
				Bukkit.broadcastMessage(" ");
				Bukkit.broadcastMessage("§eDie version seht ihr wenn ihr joint im Chat ;)");
				Bukkit.broadcastMessage("§2MFG OneShot Dev-Team");
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				
			}
		}, 1200L, 1200L);
	}

}
