package de.pyuu.oneshot.methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import protocolsupport.api.ProtocolSupportAPI;
import protocolsupport.api.ProtocolVersion;
import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.util.CountDownManager;
import de.pyuu.oneshot.util.GameState;

public class CheckPlayerStatus {
	
	public static void checkLobbyStatus(final Player p) {
		if(Bukkit.getOnlinePlayers().size() < CountDownManager.minPlayer) {
			Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
				
				@Override
				public void run() {
				if(Bukkit.getOnlinePlayers().size() < 4) {
					for(Player collective : Bukkit.getOnlinePlayers()) {
						if(Main.state != GameState.INGAME) {
						if(ProtocolSupportAPI.getProtocolVersion(p) == ProtocolVersion.MINECRAFT_1_8) {

								ActionManager.sendActionBar(collective, "§6§lWarten auf Spieler...§8(§a" + Bukkit.getOnlinePlayers().size() + "§8/§c" + CountDownManager.minPlayer + "§8)");
								
							}else {
								return;
							}
						}
					}
				}
					
				}
			}, 20L, 20L);

		}
	}

}
