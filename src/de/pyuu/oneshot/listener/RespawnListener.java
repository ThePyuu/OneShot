package de.pyuu.oneshot.listener;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import protocolsupport.api.ProtocolSupportAPI;
import protocolsupport.api.ProtocolVersion;
import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.methods.ActionManager;
import de.pyuu.oneshot.methods.PlayItems;
import de.pyuu.oneshot.methods.RandomSpawn;
import de.pyuu.oneshot.methods.TitleManager;
import de.pyuu.oneshot.methods.lives;
import de.pyuu.oneshot.util.Data;


public class RespawnListener implements Listener {
	
	
	/*
	 * spieler wird beim Respen-Event in den Spectator-Modus gesetzt
	 */
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
	final Player p = e.getPlayer();
		
	
	p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 2));
	
		if(lives.getLives(p) > 0) {
			PlayItems.setPlayItems(p.getKiller());
			p.getKiller().setHealth(20.0D);
		}
		
		for(Player server : Bukkit.getOnlinePlayers()) {
			server.playSound(server.getLocation(), Sound.EXPLODE, 2.0F, 2.0F);
		}
		
		if(lives.getLives(p) <=0) {
			
			p.getInventory().clear();
			p.setGameMode(GameMode.SPECTATOR);
		
			if(ProtocolSupportAPI.getProtocolVersion(p) == ProtocolVersion.MINECRAFT_1_8) {
			TitleManager.sendFullTitle(p, -1, -1, -1, "§cKeine Leben mehr", "§6Du bist nun im Zuschauermodus !");
			}
						
			Bukkit.broadcastMessage(Main.PREFIX + "§6Der Spieler §e" + p.getName() + " §6Hat keine Leben mehr !");
			
			Data.spec.add(p);
			p.getInventory().clear();
			
			for(Player ps : Bukkit.getOnlinePlayers()) {
			RandomSpawn.TeleportRandomSpawnLoc(p ,ps);
			}
					
			if(ProtocolSupportAPI.getProtocolVersion(p) == ProtocolVersion.MINECRAFT_1_8) {
				for(Player server : Bukkit.getOnlinePlayers()) {
					server.hidePlayer(p);
				}
			Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
				
				@Override
				public void run() {

					ActionManager.sendActionBar(p, "§6 Du bist im Zuschauermodus.");
				
					}
				}, 20L, 20L);
			}else {
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				p.sendMessage("§a§lDu bist nun im Zuschauermodus");
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");				
				p.getInventory().clear();
				p.setGameMode(GameMode.SPECTATOR);
				
				
				for(Player server : Bukkit.getOnlinePlayers()) {
					server.hidePlayer(p);
				}
			}
		}
	}
}
