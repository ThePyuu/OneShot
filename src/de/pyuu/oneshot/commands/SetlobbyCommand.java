package de.pyuu.oneshot.commands;

import java.io.File;
import java.io.IOException;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.pyuu.oneshot.Main;


public class SetlobbyCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command cmd, String label,
			String[] args) {

		if (!(sender instanceof Player)) {
			System.out
					.println("Du kannst einen Lobby - Spawnpunkt nur als Spieler setzen.");
			return true;
		}

		Player p = (Player) sender;

		/*
		 * Permissions überprfüfung
		 */
		
		if (!p.hasPermission("os.setlobbyspawn")) {
			p.sendMessage(Main.PREFIX
					+ "§c§lDu hast keine Berechtigung diesen Befehl auszuführen.");
			return true;
		}

		/*
		 * Config-Path (Lobbyspawnpunkt) 
		 */
		
		File ordner = new File("plugins//OneShot");
		File file = new File("plugins//OneShot//Lobbyspawn.yml");

		if (!ordner.exists()) {
			ordner.mkdir();
		}
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				p.sendMessage("Die Datei konnte nicht erstellt werden.");
			}
		}

		/*
		 * Lobbyspawnpunkt wird aus der Config gelesen
		 */
		
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Location loc = p.getLocation();
		double x = loc.getX();
		double y = loc.getY();
		double z = loc.getZ();
		double yaw = loc.getYaw();
		double pitch = loc.getPitch();
		String worldname = loc.getWorld().getName();

		/*
		 * Lobbyspawnpunkt wird in die Config geschrieben
		 */
		
		cfg.set("X", x);
		cfg.set("Y", y);
		cfg.set("Z", z);
		cfg.set("Yaw", yaw);
		cfg.set("Pitch", pitch);
		cfg.set("Worldname", worldname);

		try {
			cfg.save(file);
		} catch (IOException e) {
			e.printStackTrace();
		}

		/*
		 * Bestätigung, dass der Lobbyspawnpunkt gesetzt wurde (PlayerPosition)
		 */
		
		p.sendMessage(Main.PREFIX
				+ "§a§l Du hast den Lobby-Spawn erfolgreich gesetzt.");

		return true;
	}
}
