package de.pyuu.oneshot.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.util.Data;
import de.pyuu.oneshot.util.GameState;

public class PingEvent implements Listener {
	
	@EventHandler
	public void onRefresh(ServerListPingEvent e) {

		e.setMaxPlayers(16);
		
		if(Main.state == GameState.LOBBY) {
			e.setMotd("§a§lJOIN");
		}
		if(Main.state == GameState.INGAME | Main.state == GameState.WARMUP) {
			e.setMotd("§8§lINGAME");
		}
		if(Data.devMode == true) {
			e.setMotd("§2§lWartung");
		}
	}

}
