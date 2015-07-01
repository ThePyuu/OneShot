package de.pyuu.oneshot.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.util.Data;
import de.pyuu.oneshot.util.GameState;

public class MessageManagerJoinQuit implements Listener {
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		
		if(Main.state == GameState.INGAME | Main.state == GameState.WARMUP) {
			e.setJoinMessage(null);
			for(Player all : Data.alive) {
				all.hidePlayer(p);
			}
		}
	}
	
	public void onQuit(PlayerQuitEvent e) {
		
		if(Main.state == GameState.INGAME | Main.state == GameState.WARMUP) {
			e.setQuitMessage(null);
		}
	}

}
