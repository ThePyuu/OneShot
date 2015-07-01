package de.pyuu.oneshot.listener;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.util.Data;

public class LoginEventListener implements Listener {
	
	public static void onLogin(PlayerLoginEvent e) {
	
		if(Data.devMode == true) {
			if(!e.getPlayer().hasPermission("os.developer")) {
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				e.disallow(Result.KICK_OTHER, Main.PREFIX + "§6Sorry, der Server ist im Moment im Wartungs-Modus !");
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
			}
		}else {
			e.allow();
		}
	}

}
