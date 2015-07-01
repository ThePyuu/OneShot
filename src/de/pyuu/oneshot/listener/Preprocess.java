package de.pyuu.oneshot.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Preprocess implements Listener {
	
	@EventHandler
	public static void PreProcess(PlayerCommandPreprocessEvent e) {
		
		if(e.getMessage().contains("/?")) {
			e.setCancelled(true);
		}
	}

}
