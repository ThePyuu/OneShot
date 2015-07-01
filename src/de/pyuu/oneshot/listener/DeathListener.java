package de.pyuu.oneshot.listener;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.methods.InstantRespawn;
import de.pyuu.oneshot.methods.PlayItems;
import de.pyuu.oneshot.methods.lives;
import de.pyuu.oneshot.mysql.SQLStats;
import de.pyuu.oneshot.util.Data;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.scheduler.BukkitTask;


public class DeathListener implements Listener {
	Integer counterrestart;
	Integer counter;
	int varChif = 10;

	public static BukkitTask Error;
	
	
	
	@EventHandler
	public void Event_Death(PlayerDeathEvent e) {
		final Player p = e.getEntity();
		Player killer = p.getKiller();
		killer.getInventory().clear();

		PlayItems.setPlayItems(killer);

		e.setDeathMessage(null);
		Data.unstuckUsed.remove(p.getName());

		lives.setLives(p, lives.getLives(p) - 1);
		if (lives.getLives(p) > 0) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				InstantRespawn.respawnPlayerRandomLoc(p, all);
			}
		}
		
		Random r = new Random();
		int zufall1 = r.nextInt(4);
		switch (zufall1) {
		case 0:
			e.setDeathMessage(Main.PREFIX + "§c" + p.getName()
					+ " §6konnte dem Tod nicht wiederstehen.");
			break;
		case 1:
			Bukkit.getServer().broadcastMessage(
					Main.PREFIX + "§a" + killer.getName() + " §6hat §c"
							+ p.getName() + " §6erledigt.");
			break;
		case 2:
			Bukkit.getServer()
					.broadcastMessage(
							Main.PREFIX
									+ "§8§c"
									+ p.getName()
									+ " §6schaut sich die Radischen jetzt von unten an !");
			break;
		case 3:
			Bukkit.getServer().broadcastMessage(
					Main.PREFIX + "§a" + killer.getName() + " §ahat §c"
							+ p.getName() + " §aentsaftet !");
		}
		e.getDrops().clear();
		if (lives.getLives(p) == 0) {

			p.getInventory().clear();
			
			if (Data.alive.contains(p)) {
				Data.alive.remove(p);
			}
			if (!Data.death.contains(p)) {
				Data.death.add(p);
			} else {
				return;
			}
			for (Player all : Bukkit.getOnlinePlayers()) {
				InstantRespawn.respawnPlayerRandomLoc(p, all);
			}
			killer.playSound(killer.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
			PlayItems.setPlayItems(killer);
		}
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				if(Data.alive.size() == 1) {
					
varChif --;
					
					if(varChif == 9) {
						for(Player winner : Data.alive) {
							if(winner != null) {
								Bukkit.broadcastMessage("§3§l§m------------------------------------------");
								Bukkit.broadcastMessage("§eDer Spieler §3" + winner.getName() + " §eHat die Runde Gewonnen !!!");
								Bukkit.broadcastMessage("§3§l§m------------------------------------------");
							}else {
								Bukkit.broadcastMessage("§3§l§m------------------------------------------");
								Bukkit.broadcastMessage("§6Die Runde ist unentschieden zuende gegangen.");
								Bukkit.broadcastMessage("§3§l§m------------------------------------------");
							}
						}
					}
					if(varChif == 4) {
						Bukkit.shutdown();
					}
					if(varChif == 3) {
						Bukkit.broadcastMessage("§cDer Server startet die Runde in §33 §cSekunden Neu !");
					}
					if(varChif == 2) {
						Bukkit.broadcastMessage("§cDer Server startet die Runde in §32 §cSekunden Neu !");
					}
					if(varChif == 1) {
						Bukkit.broadcastMessage("§cDer Server startet die Runde in §31 §cSekunde Neu !");
					}
					if(varChif == 1) {
						for(Player server : Bukkit.getOnlinePlayers()) {
							server.getInventory().clear();
							server.kickPlayer(" ");
					}
							}
						}

					}
				}, 40L, 40L);

	}

	@EventHandler
	public void onDetailedDeath(PlayerDeathEvent e) {
		final Player p = e.getEntity();
		if ((p.getKiller() instanceof Player)) {
			SQLStats.addDeaths(p.getUniqueId().toString(), Integer.valueOf(1));
			SQLStats.addKills(p.getKiller().getUniqueId().toString(),
					Integer.valueOf(1));
		} else {
			SQLStats.addDeaths(p.getUniqueId().toString(), Integer.valueOf(1));
		}
	}
}
