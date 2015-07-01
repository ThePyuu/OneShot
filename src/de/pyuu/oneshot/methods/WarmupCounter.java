package de.pyuu.oneshot.methods;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.util.GameState;



public class WarmupCounter {
	
	static Integer counter;
	
	public static void warumupEnd(final int i, final Player p) {
		
		Main.state = GameState.WARMUP;
		
		counter = Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			int time = i + 1;
			@Override
			public void run() {
				time--;
				if(time != 0) {
					p.setLevel(time);
					}
				
				if(time == 0) {
				    for(Player ps : Bukkit.getOnlinePlayers()) {
				  		lives.setLives(ps, 4);
						PlayItems.setPlayItems(ps);						
						Main.state = GameState.INGAME;
						
						ps.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999999, 1));
				    }
					Bukkit.getScheduler().cancelTask(counter);
					for(int i = 0; i < 300; i++) {
						Bukkit.broadcastMessage(" ");
					}
					Bukkit.broadcastMessage("§3§l§m------------------------------------------");
					Bukkit.getServer().broadcastMessage(Main.PREFIX + "§e§lDie Schutzzeit ist abgelaufen.");
					Bukkit.broadcastMessage("§3§l§m------------------------------------------");
					Main.state = GameState.INGAME;
				}

			}
		}, 20L, 20L);
		
	}

	/*

	 */
}


