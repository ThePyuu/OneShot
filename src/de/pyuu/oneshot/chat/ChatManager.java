package de.pyuu.oneshot.chat;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import de.pyuu.oneshot.util.Data;

public class ChatManager implements Listener {
	
	
	
	@EventHandler
	public void onChatEvent(AsyncPlayerChatEvent e) {
		Player p = (Player) e.getPlayer();
		String msg = e.getMessage();
		
		if(p instanceof Player) {
		e.setFormat("§7[§a" + "✓" + "§7] §a" + p.getName() + " §8: §7" + msg);
		}
		if(Data.spec.contains(p)) {
			e.setFormat("§7[§4"+ "✖" + "§7] §a" + p.getName() + " §8: §7" + msg);
		}
		if(p.getName().equals("div_124") | p.getName().equals("Pyuu")) {
			e.setFormat("§7[§bDeveloper§7] §4 " + p.getName() + "§8: §e" + msg);
			return;
		}else if(p.getName().equals("Simongamer")){
			e.setFormat("§7[§4Co-Owner§7] §4 " + p.getName() + " §8: §e" + msg);
		}
		
		/*
		 * Chat Channels (Alive, Spec)
		 */
	/*
		e.setCancelled(true);
		
		for(Player alive : Data.alive) {
			if(Data.alive.contains(p)) {
				alive.sendMessage("§7[§a" + "✓" + "§7] §a" + p.getName() + " §8: §7" + msg);
				for(Player admin : Bukkit.getOnlinePlayers()) {
					if(admin.hasPermission("os.developer")) {
						if(!Data.alive.contains(admin)) {
						admin.sendMessage("§8[§5Spy§8] §7[§a" + "✓" + "§7] §a" + p.getName() + " §8: §7" + msg);
						}
					}
				}
			}
		}
		for(Player spec : Data.spec) {
			if(Data.spec.contains(p)) {
				spec.sendMessage("[§4"+ "✖" + "§7] §a" + p.getName() + " §8: §7" + msg);
				for(Player admin : Bukkit.getOnlinePlayers()) {
					if(admin.hasPermission("os.developer")) {
						if(!Data.spec.contains(admin)) {
						admin.sendMessage("§8[§5Spy§8] §7[§4"+ "✖" + "§7] §a" + p.getName() + " §8: §7" + msg);
						}
					}
				}
			}
		}*/
	}
}
