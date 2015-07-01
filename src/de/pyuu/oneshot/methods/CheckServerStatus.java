package de.pyuu.oneshot.methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.util.Data;
import protocolsupport.api.ProtocolSupportAPI;
import protocolsupport.api.ProtocolVersion;

public class CheckServerStatus {
	
	public static void CheckMode(Player p) {
		for(Player collective : Bukkit.getOnlinePlayers()) {
			if(ProtocolSupportAPI.getProtocolVersion(collective) == ProtocolVersion.MINECRAFT_1_8) {
				if(Data.devMode == true) {
					Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							for(Player all : Bukkit.getOnlinePlayers()) {
							ActionManager.sendActionBar(all, "§4Warung: §bDer Server befindet sich im Develop-Modus !");
							}
							
						}
					}, 20L, 20L);
				}
			}
		}
	}

}
