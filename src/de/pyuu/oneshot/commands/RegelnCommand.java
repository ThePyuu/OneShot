package de.pyuu.oneshot.commands;


import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public class RegelnCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label,
			String[] args) {
		
		Player p = (Player)cs;
		
		ArrayList<String> lore_Close = new ArrayList<String>();
		
		ItemStack close = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta meta5 = close.getItemMeta();
		meta5.setDisplayName("§cExit");
		lore_Close.add("§e> Schliesst das Menü.");
		meta5.setLore(lore_Close);
		close.setItemMeta(meta5);
		
		ArrayList<String> lore_Back= new ArrayList<String>();
		
		ItemStack back = new ItemStack(Material.ARROW);
		ItemMeta meta4 = back.getItemMeta();
		meta4.setDisplayName("§cZurück");
		lore_Back.add("§e> Bringt dich zurück zum Hauptmenü.");
		meta4.setLore(lore_Back);
		back.setItemMeta(meta4);
		
		ArrayList<String> lore_rules = new ArrayList<String>();
		
		ItemStack rules = new ItemStack(Material.REDSTONE);
		ItemMeta meta3 = rules.getItemMeta();
		meta3.setDisplayName("§3Die Regeln");
		lore_rules.add("§e> Zeigt die Regeln von Oneshot an !");
		meta3.setLore(lore_rules);
		rules.setItemMeta(meta3);
		
		ArrayList<String> lore_Vorschläge = new ArrayList<String>();
		
		ItemStack vorschläge = new ItemStack(Material.PAPER);
		ItemMeta meta2 = vorschläge.getItemMeta();
		meta2.setDisplayName("§3Vorschlage ? Gerne !");
		lore_Vorschläge.add("§e> Wo soll ich hin mit meinen Vorschlägen ?");
		meta2.setLore(lore_Vorschläge);
		vorschläge.setItemMeta(meta2);
		
		
		ArrayList<String> lore_Bugs = new ArrayList<String>();
		
		ItemStack bugs = new ItemStack(Material.LAVA_BUCKET);
		ItemMeta meta1 = bugs.getItemMeta();
		meta1.setDisplayName("§3Du hast einen Bug gefunden ?");
		lore_Bugs.add("§e> Wo soll ich Bugs reporten ?");
		meta1.setLore(lore_Bugs);
		bugs.setItemMeta(meta1);
		

		
		
		
		if(cmd.getName().equalsIgnoreCase("regelnLKJM45637InstanceOFMenue")) {
			
			
			Inventory menü = Bukkit.createInventory(null, 18, "§6Oneshot Menü");
			
			menü.setItem(0, rules);
			menü.setItem(4, bugs);
			menü.setItem(8, vorschläge);
			//menü.setItem(17, back);
			menü.setItem(9, close);
			
			p.openInventory(menü);
			
		}
		return true;
	}
		
}
