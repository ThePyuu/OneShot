package de.pyuu.oneshot.listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import de.pyuu.oneshot.Main;

public class FlightListener implements Listener {
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public static void onInteract(PlayerInteractEvent e) {
		
		final ItemStack flight = new ItemStack(Material.BLAZE_ROD);
		ItemMeta flightmeta = flight.getItemMeta();
		flightmeta.setDisplayName("§8[§bOneShot§8] §3§lJetPackBooster");
		flight.setItemMeta(flightmeta);
		
		final ArrayList<String> cooldown_booster = new ArrayList<String>();	
		final Player p = e.getPlayer();
		
		if(e.getAction() == Action.RIGHT_CLICK_BLOCK 
				| e.getAction() == Action.RIGHT_CLICK_AIR) {
			
			
			if(p.getItemInHand().getType() == Material.BLAZE_ROD) {
			
				
				
				if(!cooldown_booster.contains(p.getName())) {
					cooldown_booster.add(p.getName());
					Vector vec = p.getLocation().getDirection().multiply(3D).setY(2D);
					p.setVelocity(vec);
					p.playSound(p.getLocation(), Sound.FIREWORK_TWINKLE2, 1.0F, 1.0F);
					p.playEffect(p.getLocation(), Effect.MOBSPAWNER_FLAMES, 30);
					p.getInventory().remove(Material.BLAZE_ROD);
					
					Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
						
						@Override
						public void run() {
							p.getInventory().addItem(flight);
							
						}
					}, 20L*5);
					
					return;
					
				}
				if(cooldown_booster.contains(p.getName())){
					p.sendMessage(Main.PREFIX + "§cDas ist im Moment nicht verfügbar ... :/");
					e.setCancelled(true);
					return;
				}
			}
		}
	}
}
