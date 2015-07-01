package de.pyuu.oneshot.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.util.Data;

public class FixCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label,
			String[] args) {
		Player p = (Player)cs;
		if(cmd.getName().equalsIgnoreCase("fix")) {
			if(Data.unstuckUsed.contains(p.getName())) {
				p.sendMessage(Main.PREFIX + "Du kannst diesen Command nicht nocheinmal verwenden !");
				return true;
			}else {
				Location playerLOC = p.getLocation();
				playerLOC.add(0, 3, 0);
				p.teleport(playerLOC);
				
				Data.unstuckUsed.add(p.getName());
			}
		}
		return false;
	}
	

}
