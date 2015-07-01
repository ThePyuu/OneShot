package de.pyuu.oneshot.methods;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.pyuu.oneshot.util.Data;



public class DeathItems {
	
	/*
	 * Definition der verschiedenen Items
	 */
	
	public static void setDeathItems(Player p) {
		
	ItemStack cream = new ItemStack(Material.MAGMA_CREAM);
	ItemMeta creammeta = cream.getItemMeta();
	creammeta.setDisplayName("§c§lZurück zur Lobby");
	Data.lore.add("§7§lRechtsklicken um dieses Item zu benutzen!");
	creammeta.setLore(Data.lore);
	cream.setItemMeta(creammeta);

	
	ItemStack bone = new ItemStack(Material.REDSTONE);
	ItemMeta bonemeta = bone.getItemMeta();
	bonemeta.setDisplayName("§c§lDu bist im Spectator-Modus !");
	bone.setItemMeta(bonemeta);
	
	ItemStack glass = new ItemStack(Material.GLASS);
	ItemMeta glassmeta = glass.getItemMeta();
	Data.lore.add(null);
	glassmeta.setDisplayName(null);
	glass.setItemMeta(glassmeta);
	
	p.getInventory().setItem(8, cream);
	p.getInventory().setItem(0, bone);
	
	p.updateInventory();
	}
}
