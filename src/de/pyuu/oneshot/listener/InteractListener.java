package de.pyuu.oneshot.listener;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.pyuu.oneshot.methods.IchatBaseComp;

public class InteractListener implements Listener {
	
	@EventHandler
	public static void onInteractFATEMenü(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
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
		
		e.setCancelled(true);
		
		if(e.getInventory().getName().equals("§6Oneshot Menü")) {
			if(e.getCurrentItem().getType() == Material.REDSTONE) {
				
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				p.sendMessage("§61. Teams sind §4§lVerboten");
				p.sendMessage("§62. Hacks sind §4§lVerboten");
				p.sendMessage("§63. Spieler-Maps sind §4§lVerboten");
				p.sendMessage("§64. Beleidigungen sind §4§lVerboten");
				p.sendMessage("§65. Camping und SpawnCamping ist §4§lVerboten");
				p.sendMessage(" ");
				p.sendMessage("§cEs gelten die Allg. AGB´s von MAD-Gamble");
				IchatBaseComp.sendChatSerializer(p, "§4§l" + "➤" + "§aSpiel-Menü §8[§eClick§8]", "/regelnLKJM45637InstanceOFMenue");
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				
				p.closeInventory();
				
				return;
			}
			if(e.getCurrentItem().getType() == Material.PAPER) {
				
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				p.sendMessage("§3Buggs bitte bei Div_124 im Forum melden : \n -§awww.Mad-Gamble.net/forum");
				p.sendMessage(" ");
				p.sendMessage("§3Oder im TeamSpeak3 \n -§a /TS");
				p.sendMessage(" ");
				p.sendMessage("§6Wenn du festeckst dann nutze §a/Fix ");
				IchatBaseComp.sendChatSerializer(p, "§4§l" + "➤" + "§aSpiel-Menü §8[§eClick§8]", "/regelnLKJM45637InstanceOFMenue");
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				
				p.closeInventory();
				
				return;
			}
			if(e.getCurrentItem().getType() == Material.LAVA_BUCKET) {
				
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				p.sendMessage("§6Wenn du Vorschläge, Bugreports o.ä. hast, poste diese im Forum unter Dem Titel:");
				p.sendMessage(" ");
				p.sendMessage("§2OneShot (Bugs, Vorschläge)");
				p.sendMessage(" ");
				IchatBaseComp.sendChatSerializer(p, "§4§l" + "➤" + "§aSpiel-Menü §8[§eClick§8]", "/regelnLKJM45637InstanceOFMenue");
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				
				p.closeInventory();
				
				return;
			}
			if(e.getCurrentItem().getType() == Material.ARROW) {
				
				Inventory menü = Bukkit.createInventory(null, 18, "§6Oneshot Menü");
				
				menü.setItem(0, rules);
				menü.setItem(4, bugs);
				menü.setItem(8, vorschläge);
				//menü.setItem(17, back);
				menü.setItem(13, close);
				
				p.openInventory(menü);
			}
			if(e.getCurrentItem().getType() == Material.REDSTONE_BLOCK) {
				
				p.closeInventory();
			}
			
		}
	}

}
