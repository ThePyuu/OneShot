package de.pyuu.oneshot.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.methods.CheckPlayerStatus;
import de.pyuu.oneshot.methods.lives;
import de.pyuu.oneshot.util.Data;
import de.pyuu.oneshot.util.GameState;

public class QuitListener implements Listener {
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		if(Data.devMode == true) {
			if(!p.hasPermission("os.developer")) {
			e.setQuitMessage(null);
			return;
			}
			
		}
		
		if(Data.death.contains(p)) {
			Data.death.remove(p);
		}
		if(Data.alive.contains(p)) {
			Data.alive.remove(p);
		}
		if(Data.spec.contains(p)) {
			Data.spec.remove(p);
		}
		
		e.setQuitMessage(Main.PREFIX + p.getDisplayName() + "§c hat das Spiel verlassen.");
		
		p.setGameMode(GameMode.SURVIVAL);
		
		if(Main.state != GameState.INGAME | Main.state != GameState.WARMUP) {
		CheckPlayerStatus.checkLobbyStatus(p);
		}
		
		if(Main.state == GameState.INGAME) {
			lives.setLives(p, 0);
			
			Data.alive.remove(p);
			Data.death.remove(p);
			Data.spec.remove(p);
			if(Bukkit.getOnlinePlayers().size() == 0 && Main.state == GameState.INGAME && Main.state == GameState.WARMUP) {
				if(Main.state == GameState.INGAME) {
					Bukkit.shutdown();
				}
			}
			
			e.setQuitMessage(Main.PREFIX + p.getDisplayName() + "§c hat das Spiel verlassen.");
		}
		if(Data.alive.size() >= 1) {
			if(Main.state != GameState.INGAME) {
				Bukkit.shutdown();
			}
		}
	}

}
