package de.pyuu.oneshot.methods;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class PlayItems {
	
	public static void setPlayItems(Player p ) {
		
		/*
		 * Erstellung der Haupt-Spielitems
		 */
		
		ItemStack sword = new ItemStack(Material.WOOD_SWORD);
		ItemMeta swordmeta = sword.getItemMeta();
		swordmeta.setDisplayName("§8[§bOneShot§8] §aNahkampfwaffe");
		sword.setItemMeta(swordmeta);
		
		ItemStack bow = new ItemStack(Material.BOW);
		ItemMeta bowmeta = bow.getItemMeta();
		bowmeta.setDisplayName("§8[§bOneShot§8] §aFernkampfwaffe");
		bow.setItemMeta(bowmeta);
		
		ItemStack arrow = new ItemStack(Material.ARROW);
		ItemMeta arrowmeta = arrow.getItemMeta();
		arrowmeta.setDisplayName("§8[§bOneShot§8] §aPfeil für deinen Bogen !");
		arrow.setItemMeta(arrowmeta);
		
		ItemStack com = new ItemStack(Material.COMPASS);
		ItemMeta commeta = com.getItemMeta();
		commeta.setDisplayName("§8[§bOneShot§8] §5§lTracker §8[§aKommt noch !§8]");
		com.setItemMeta(commeta);
		
		ItemStack flight = new ItemStack(Material.BLAZE_ROD);
		ItemMeta flightmeta = flight.getItemMeta();
		flightmeta.setDisplayName("§8[§bOneShot§8] §3§lJetPackBooster");
		flight.setItemMeta(flightmeta);

		
		p.getInventory().setItem(2, flight);
		p.getInventory().setItem(1, bow);
		p.getInventory().setItem(0, sword);
		p.getInventory().setItem(8, arrow);

		
	}

}
