package de.pyuu.oneshot.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.util.CountDownManager;

public class StartCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label,
			String[] args) {
		Player p = (Player)cs;
		if(cmd.getName().equalsIgnoreCase("start")) {
			if(!p.hasPermission("os.start")) {
				p.sendMessage(Main.PREFIX + "§cFEHLER: keine Rechte");
				return true;
			}
			if(args.length == 0) {
				p.sendMessage(Main.PREFIX + "§cSyntax: /Start [verlängern | Start | Instant]");
				return true;
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("start")) {
				CountDownManager.count = 10;
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				Bukkit.broadcastMessage(Main.PREFIX + "Der Countdown wurde auf 10 Sekunden gesetzt");
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				return true;
			}
			if(args.length == 1 && args[0].equalsIgnoreCase("verlängern")) {
				CountDownManager.count = 90;
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				Bukkit.broadcastMessage(Main.PREFIX + "Der Countdown wurde auf 90 Sekunden gesetzt");
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				return true;
			}
			if(args.length == 1 &&args[0].equalsIgnoreCase("instant")) {
				CountDownManager.count = 3;
				CountDownManager.minPlayer = 1;
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				Bukkit.broadcastMessage(Main.PREFIX + "Die Runde wurde schnellgestartet !");
				Bukkit.broadcastMessage("§3§l§m------------------------------------------");
				return true;
			}
		}
		return false;
	}
	

}
