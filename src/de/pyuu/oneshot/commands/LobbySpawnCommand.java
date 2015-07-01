package de.pyuu.oneshot.commands;

import java.io.File;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.pyuu.oneshot.Main;


public class LobbySpawnCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (!(sender instanceof Player)) {
			System.out
					.println("Du kannst einen LobbyPunkt nur als Spieler setzen.");

			return true;
		}
		Player p = (Player) sender;

		/*
		 * Config-Path
		 */
		
		File file = new File("plugins//OneShot//Lobbyspawn.yml");
		if (!file.exists()) {
			p.sendMessage(Main.PREFIX
					+ "§c§l Es wurde kein Lobby-Spawn gesetzt.");
			return true;
		}
		
		/*
		 * Config wir definiert
		 */
		
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Location loclobby = p.getLocation();

		/*
		 *Koordinaten aus der Config lesen
		 */
		
		double x = cfg.getDouble("X");
		double y = cfg.getDouble("Y");
		double z = cfg.getDouble("Z");
		double yaw = cfg.getDouble("Yaw");
		double pitch = cfg.getDouble("Pitch");
		String worldname = cfg.getString("Worldname");

		World world = Bukkit.getWorld(worldname);

		/*
		 * Koordinaten in die Condig schreiben
		 */
		
		loclobby.setX(x);
		loclobby.setY(y);
		loclobby.setZ(z);
		loclobby.setYaw((float) yaw);
		loclobby.setPitch((float) pitch);
		loclobby.setWorld(world);

		/*
		 * Teleportiert Spieler zu ausgelesenen Koordinaten
		 */
		
		p.teleport(loclobby);

		return true;
	}
}
