package de.pyuu.oneshot.mysql;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.api.Hologram;


public class HoloStats implements Runnable {

	Player p;

	public HoloStats(Player p) {
		this.p = p;
		Bukkit.getScheduler().runTaskLater(Main.getInstance(), this, 20 * 2);
	}

	@Override
	public void run() {

		File file = new File("plugins//OneShot//HoloStats.yml");
		if (!file.exists()) {
			p.sendMessage(Main.PREFIX
					+ "§c§l Es wurde kein Holo - Punkt gesetzt.");
			return;
		}
		
		//NORMALE STATS
		YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
		Location loc = p.getLocation();
		
		double x = cfg.getDouble("X");
		double y = cfg.getDouble("Y");
		double z = cfg.getDouble("Z");
		double yaw = cfg.getDouble("Yaw");
		double pitch = cfg.getDouble("Pitch");
		String worldname = cfg.getString("Worldname");

		World world = Bukkit.getWorld(worldname);

		loc.setX(x);
		loc.setY(y +1);
		loc.setZ(z);
		loc.setYaw((float) yaw);
		loc.setPitch((float) pitch);
		loc.setWorld(world);

		
		int deaths = SQLStats.getDeaths(p.getUniqueId().toString());
		int kills = SQLStats.getKills(p.getUniqueId().toString());

		double KD = ((double) kills) / ((double) deaths);
		String kd = Double.valueOf(KD).toString();

		List<String> lines = new ArrayList<>();
		lines.add("§6§lOneShot Stats");
		lines.add(" ");
		lines.add("§3Deine Kills: §e" + kills);
		lines.add("§3Deine Tode: §e" + deaths);
		lines.add("§3Deine K/D: §e" + kd);
		lines.add(" ");

		
		
		
		Hologram holo = new Hologram(loc, lines);
		holo.display(p);
		return;
	}
}
