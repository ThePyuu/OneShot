package de.pyuu.oneshot.methods;

import org.bukkit.entity.Player;

import de.pyuu.oneshot.util.Data;

public class lives {
	
	public static int getLives(Player p) {
		
		Integer i = Data.live.get(p);
		if(i == null) {
			return 0;
		}
		return i;
	}
	
	public static void setLives(Player p, int i) {
		
		if(i > 4) {
			i = 4;
		}
		if(i <= 0) {
			Data.live.remove(p);
			return;
		}
		
		Data.live.put(p, i);
	}

}
