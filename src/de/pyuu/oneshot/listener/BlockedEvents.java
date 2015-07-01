package de.pyuu.oneshot.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.util.Data;
import de.pyuu.oneshot.util.GameState;



public class BlockedEvents implements Listener {
	
	
	/*
	 * Eventbasierter Canceller
	 */

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		if(Data.devMode == false) {
		e.setCancelled(true);
		}
	}
	@EventHandler
	public void onBlockPlace(BlockPlaceEvent e) {
		if(Data.devMode == false) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onDropItem(PlayerDropItemEvent e) {
		if(Data.devMode == false) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onPickupItem(PlayerPickupItemEvent e) {
		if(Data.devMode == false) {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {
		if(Data.devMode == false) {
			e.setCancelled(true);
		}
	}	
	@EventHandler
	public void mobSpawn(EntitySpawnEvent e) {
		if(Data.devMode == false) {
			e.setCancelled(true);
		}else {
			e.setCancelled(true);
		}
	}
	@EventHandler
	public void onDamage(EntityDamageEvent e){
		if(!(e.getEntity() instanceof Player)){
			return;
		}else{
			if(e.getCause().equals(DamageCause.FALL)){
				e.setCancelled(true);
			}
		}
	}
	@EventHandler
	public void onSaveTimeDMG(EntityDamageByEntityEvent e) {
		if(Main.state == GameState.WARMUP | Main.state == GameState.LOBBY) {
			e.setCancelled(true);
		}
	}
	
	/*
	 * Essensverlust wird abgeschaltet
	 */
	
	@EventHandler
	public void onFood(FoodLevelChangeEvent e) {
		e.setCancelled(true);
	}	  
	@EventHandler
	public void on(CreatureSpawnEvent e)
	  {
	    Entity es = e.getEntity();
	    if ((!(es instanceof Player))) {
	      e.setCancelled(true);
	    }
	}
	@EventHandler
	public void onWeather(WeatherChangeEvent e) {
		e.setCancelled(true);
	}	
	
}
