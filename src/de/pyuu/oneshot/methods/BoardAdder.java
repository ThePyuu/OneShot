package de.pyuu.oneshot.methods;


import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.util.Data;




public class BoardAdder {
	
	@SuppressWarnings("deprecation")
	public static void updateScrboard(final Player p) {
		
				final Scoreboard board = Bukkit.getScoreboardManager().getNewScoreboard();
				Objective obj = board.registerNewObjective("aaa", "bbb");
				
				obj.setDisplayName("§6OneShot");
				obj.setDisplaySlot(DisplaySlot.SIDEBAR);
				
				int leben = lives.getLives(p);
				int zuschauer = Data.spec.size();
				int lebend = Data.alive.size();
				
				Score lives = obj.getScore(Bukkit.getOfflinePlayer("§a§lLeben§8: "));
				Score specs = obj.getScore(Bukkit.getOfflinePlayer("§a§lZuschauer§8: "));
				Score verbleibend = obj.getScore(Bukkit.getOfflinePlayer("§a§lVerbleibend§8: ")); 
				
				specs.setScore(zuschauer);
				
				if(!Data.spec.contains(p)) {
				lives.setScore(leben);
				}
				verbleibend.setScore(lebend);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						p.setScoreboard(board);	
						
					}
				},20L);		
			
	}
	
}
