package de.pyuu.oneshot.methods;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class RandomSpawn {

	public static void TeleportRandomSpawnLoc(Player p, Player refresher) {
		
		Location loc1 = new Location(Bukkit.getWorld("world"), -693.304, 4, 777.300);
		Location loc2 = new Location(Bukkit.getWorld("world"), -661.517, 18.06, 739.498);
		Location loc3 = new Location(Bukkit.getWorld("world"), -670.300, 10.06, 715.398);
		
		Location loc4 = new Location(Bukkit.getWorld("world"), -666.489, 18.06, 743.495);
		Location loc5 = new Location(Bukkit.getWorld("world"), -653.700, 9.5, 760.300);
		Location loc6 = new Location(Bukkit.getWorld("world"), -715, 10.06, 762.492);
		
		Location loc7 = new Location(Bukkit.getWorld("world"), -734.300, 16.06, 738.700);
		Location loc8 = new Location(Bukkit.getWorld("world"), -737, 9.5, 720.700);
		Location loc9 = new Location(Bukkit.getWorld("world"), -731.365, 12.5, 694.469);
		
		Location loc10 = new Location(Bukkit.getWorld("world"), -692.700, 31.06, 712.531);
		Location loc11 = new Location(Bukkit.getWorld("world"), -692.700, 31.06, 734.372);
		Location loc12 = new Location(Bukkit.getWorld("world"), -711.383, 17.06, 766.346);
		
		Location loc13 = new Location(Bukkit.getWorld("world"), -737.551, 4.06, 741.453);
		Location loc14 = new Location(Bukkit.getWorld("world"), -718.579, 5.06, 687.437);
		Location loc15 = new Location(Bukkit.getWorld("world"), -689.509, 5.06, 680.446);
		
		Location loc16 = new Location(Bukkit.getWorld("world"), -669.522, 5.06250, 693.532);
		
		BoardAdder.updateScrboard(refresher);

		loc1.add(0, 0.5, 0);
		loc2.add(0, 0.5, 0);
		loc3.add(0, 0.5, 0);
		loc4.add(0, 0.5, 0);
		loc5.add(0, 0.5, 0);
		loc6.add(0, 0.5, 0);
		loc7.add(0, 0.5, 0);
		loc8.add(0, 0.5, 0);
		loc9.add(0, 0.5, 0);
		loc10.add(0, 0.5, 0);
		loc12.add(0, 0.5, 0);
		loc12.add(0, 0.5, 0);
		loc13.add(0, 0.5, 0);
		loc14.add(0, 0.5, 0);
		loc15.add(0, 0.5, 0);
		loc16.add(0, 0.5, 0);
		
		Random r = new Random();
		int zufall1 = r.nextInt(17);
		switch(zufall1) {
		

		case 0:
			p.teleport(loc1);
			break;
		case 1:
			p.teleport(loc2);
			break;
		case 2:
			p.teleport(loc3);
			break;
		case 3:
			p.teleport(loc4);
			break;
		case 4:
			p.teleport(loc5);
			break;
		case 5:
			p.teleport(loc6);
			break;
		case 6:
			p.teleport(loc7);
			break;
		case 7:
			p.teleport(loc8);
			break;
		case 8:
			p.teleport(loc9);
			break;
		case 9:
			p.teleport(loc10);
			break;
		case 10:
			p.teleport(loc11);
			break;
		case 11:
			p.teleport(loc12);
			break;
		case 12:
			p.teleport(loc13);
			break;
		case 13:
			p.teleport(loc14);
			break;
		case 14:
			p.teleport(loc15);
			break;
		case 15:
			p.teleport(loc16);
			break;
			
			
		}
	}
	
}
