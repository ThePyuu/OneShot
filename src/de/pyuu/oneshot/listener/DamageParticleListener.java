package de.pyuu.oneshot.listener;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.util.GameState;


public class DamageParticleListener implements Listener {
	
	@EventHandler
	public static void onDamage(EntityDamageByEntityEvent e) {
		Player p = (Player) e.getEntity();
		if(p instanceof Player) {
			
			if(Main.state == GameState.INGAME) {
			p.getWorld().playEffect(p.getLocation(), Effect.STEP_SOUND, Material.REDSTONE_BLOCK);
			}else {
				e.setCancelled(true);
			}
		}else {
			e.setCancelled(true);
		}
	}

}
