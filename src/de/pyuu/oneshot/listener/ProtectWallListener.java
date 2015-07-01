package de.pyuu.oneshot.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class ProtectWallListener implements Listener {
	
	@EventHandler
	public static void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if(e.getAction() == Action.LEFT_CLICK_AIR 
				| e.getAction() == Action.LEFT_CLICK_BLOCK 
				| e.getAction() == Action.RIGHT_CLICK_BLOCK 
				| e.getAction() == Action.RIGHT_CLICK_AIR) {
			
			if(p.getItemInHand().getType() == Material.BLAZE_ROD) {
				
				
				
			}
		}
	}

}
