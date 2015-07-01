package de.pyuu.oneshot.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import protocolsupport.api.ProtocolSupportAPI;
import protocolsupport.api.ProtocolVersion;
import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.methods.ActionManager;
import de.pyuu.oneshot.util.CountDownManager;
import de.pyuu.oneshot.util.Data;
import de.pyuu.oneshot.util.GameState;

public class DevMode implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label,
			String[] args) {
		final Player p = (Player)cs;
		if(cmd.getName().equalsIgnoreCase("devMode")) {
			if(p.hasPermission("os.developer")) {
				
				
				if(args.length == 0) {
					p.sendMessage(Main.PREFIX + "§cSytax: /Devmode [On | Off]");
					return true;
				}
				if(args.length == 1) {
					if(args[0].equalsIgnoreCase("On")) {
						
						Main.state = GameState.WARTUNG;
						
						if(Data.devMode == false) {
							Data.devMode = true;
if(Data.lobbyCDisRunning == true) {
	CountDownManager.lobbycd.cancel();
}
							
							Bukkit.broadcastMessage(Main.PREFIX + "Der Server ist nun im Develop-Modus !");
							for(Player all : Bukkit.getOnlinePlayers()) {
								if(!all.hasPermission("os.developer")) {
									all.kickPlayer(Main.PREFIX + "Sorry, der Server wurde in den Develop-Modus gesetzt (Wartungen).");
									
									if(ProtocolSupportAPI.getProtocolVersion(all) == ProtocolVersion.MINECRAFT_1_8) {
										ActionManager.sendActionBar(all, "§4§lWarung: §bDevelop-Modus ist aktiv");
									}
								}
							}
							

							
							return true;
						}else {
							p.sendMessage(Main.PREFIX + "Der Server ist bereits im Develop-Modus");
							return true;
						}
					}
					if(args[0].equalsIgnoreCase("off")) {
						if(Data.devMode == true) {
							Data.devMode = false;
							
							Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
								
								@Override
								public void run() {
									Bukkit.broadcastMessage("§3§l§m------------------------------------------");
									Bukkit.broadcastMessage(Main.PREFIX + "Der Server ist nach dem Neustart im Spiel-Modus !");
									Bukkit.broadcastMessage("§3§l§m------------------------------------------");
									
									Bukkit.shutdown();
									
								}
							}, 60L);
						}
					}
				}
			}
		}
		return false;
	}

}
