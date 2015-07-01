 package de.pyuu.oneshot.methods;

import net.minecraft.server.v1_8_R2.PacketPlayInClientCommand;
import net.minecraft.server.v1_8_R2.PacketPlayInClientCommand.EnumClientCommand;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

import de.pyuu.oneshot.Main;


public class InstantRespawn implements Listener {
	
	public static void respawnPlayer(Player p, Player refresher, Location rloc) {
		if(lives.getLives(p) <= 0) {
			PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
			((CraftPlayer)p).getHandle().playerConnection.a(packet);
			p.teleport(rloc);
			p.getInventory().clear();
			
		}
	}
	public static void respawnPlayerRandomLoc(Player p, Player refresher) {
			PacketPlayInClientCommand packet = new PacketPlayInClientCommand(EnumClientCommand.PERFORM_RESPAWN);
			((CraftPlayer)p).getHandle().playerConnection.a(packet);
			p.getInventory().clear();
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		final Player p = e.getPlayer();
		p.getInventory().clear();
		for(Player ps : Bukkit.getOnlinePlayers()) {
		RandomSpawn.TeleportRandomSpawnLoc(p ,ps);
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				
				for(Player ps : Bukkit.getOnlinePlayers()){
					RandomSpawn.TeleportRandomSpawnLoc(p , ps);
				}
				
				PlayItems.setPlayItems(p);
			}
		}, 3L);
		p.updateInventory();
	}

}
