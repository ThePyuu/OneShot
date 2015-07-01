package de.pyuu.oneshot.util;



import org.bukkit.Bukkit;
import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitTask;

import protocolsupport.api.ProtocolSupportAPI;
import protocolsupport.api.ProtocolVersion;
import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.methods.ActionManager;
import de.pyuu.oneshot.methods.RandomSpawn;
import de.pyuu.oneshot.methods.TitleManager;
import de.pyuu.oneshot.methods.WarmupCounter;


public class CountDownManager implements Listener {

	private Main instance;
	private BukkitTask check;
	public static BukkitTask lobbycd;
	public boolean count_started = false;
	public static int minPlayer = 4;
	public static int count = 60;
	public int startPlayer = 16;
	public boolean count_forced = false;
	public boolean forcestart = true;
	
	
	public CountDownManager(Main instance) {
		this.instance = instance;
	}

	public void checkToStart() {
		this.check = Bukkit.getScheduler().runTaskTimer(this.instance, new Runnable() {
					public void run() {
						if (count_started == false) {
							if (Bukkit.getOnlinePlayers().size() >= minPlayer) {

								Player pss = null;
								startCountdown(pss);
							} else {
								if ((minPlayer - Bukkit.getOnlinePlayers()
										.size()) == 1) {

								}
							}
						}
					}
				}, 20L, 40L);
	}

	@SuppressWarnings("static-access")
	public void resetCountdown() {
		this.lobbycd.cancel();
		count_started = false;
		count = 60;

		checkToStart();
	}

	@SuppressWarnings("static-access")
	public void startCountdown(final Player ps) {
		this.check.cancel();
		count_started = true;

		try {
			this.lobbycd.cancel();
		} catch (NullPointerException ex) {
}

		this.lobbycd = Bukkit.getScheduler().runTaskTimer(this.instance,
				new Runnable() {
					@SuppressWarnings("deprecation")
					public void run() {
						
						Data.lobbyCDisRunning = true;
						
						for(Player all : Bukkit.getOnlinePlayers()) {
						if(ProtocolSupportAPI.getProtocolVersion(all) != ProtocolVersion.MINECRAFT_1_8) {
							all.setLevel(count);
							}
						}
						
					if (Bukkit.getOnlinePlayers().size() < minPlayer) {
							Bukkit.broadcastMessage("§3§l§m------------------------------------------");
							Bukkit.broadcastMessage(Main.PREFIX
									+ " §cEs sind zu wenige Spieler online.");
							Bukkit.broadcastMessage(Main.PREFIX
									+ " §cDer Countdown wird abgebrochen!");
							for(Player s : Bukkit.getOnlinePlayers()) {
								s.setLevel(0);
							}
							Bukkit.broadcastMessage("§3§l§m------------------------------------------");

							resetCountdown();
							return;
						}

						if (count_forced == false && forcestart == false) {
							if (Bukkit.getOnlinePlayers().size() >= startPlayer) {
								count_forced = true;
								if (count > 10) {
									count = 10;
									Bukkit.broadcastMessage("§3§l§m------------------------------------------");
									Bukkit.broadcastMessage(Main.PREFIX
											+ " §bEs sind §3"
											+ Bukkit.getOnlinePlayers().size()
											+ " §bSpieler online.");
									Bukkit.broadcastMessage(Main.PREFIX
											+ " §bDer Countdown wurde auf §910 Sekunden§b verkürzt.");
									Bukkit.broadcastMessage("§3§l§m------------------------------------------");
								}
							}
						}
					
						/*
						 *Countdown Chat-Ausgabe 60,30,10,3-0 
						 */
						
						if (count == 60) {
						Data.lobbyCDisRunning = true;
							for (Player p : Bukkit.getOnlinePlayers()) {
								if(ProtocolSupportAPI.getProtocolVersion(p) == ProtocolVersion.MINECRAFT_1_8) {
								TitleManager.sendTitle(p, -1, -1, -1, "§aSpiel startet in §360 §aSekunden");
								}
							}
						}

						if (count == 30) {
							for (Player p : Bukkit.getOnlinePlayers()) {
								if(ProtocolSupportAPI.getProtocolVersion(p) == ProtocolVersion.MINECRAFT_1_8) {
									TitleManager.sendTitle(p, -1, -1, -1, "§aSpiel startet in §330 §aSekunden");
								}
							}
						}

						if (count == 10) {
							for (Player p : Bukkit.getOnlinePlayers()) {
								if(ProtocolSupportAPI.getProtocolVersion(p) == ProtocolVersion.MINECRAFT_1_8) {
									TitleManager.sendTitle(p, -1, -1, -1, "§aSpiel startet in §310 §aSekunden");
								}
							}
						}

						if (count == 3) {
							for (Player p : Bukkit.getOnlinePlayers()) {
								if(ProtocolSupportAPI.getProtocolVersion(p) == ProtocolVersion.MINECRAFT_1_8) {
									TitleManager.sendTitle(p, -1, -1, -1, "§aSpiel startet in §33 §aSekunden");
									
									Data.lobbyIsStarting = true;
									
									}
								p.playNote(p.getLocation(), Instrument.BASS_GUITAR,new Note(1));
							}
						}

						if (count == 2) {
							for (Player p : Bukkit.getOnlinePlayers()) {
								if(ProtocolSupportAPI.getProtocolVersion(p) == ProtocolVersion.MINECRAFT_1_8) {
									TitleManager.sendTitle(p, -1, -1, -1, "§aSpiel startet in §32 §aSekunden");
									}
								p.playNote(p.getLocation(), Instrument.BASS_GUITAR,new Note(1));
								Main.state = GameState.WARMUP;
							}
						}

						if (count == 1) {
							for (Player p : Bukkit.getOnlinePlayers()) {
								if(ProtocolSupportAPI.getProtocolVersion(p) == ProtocolVersion.MINECRAFT_1_8) {
									TitleManager.sendTitle(p, -1, -1, -1, "§aSpiel startet in §31 §aSekunden");
									}
								p.playNote(p.getLocation(), Instrument.BASS_GUITAR,new Note(1));
							}
						}
						if(count >=-1) {
							for(Player p : Bukkit.getOnlinePlayers()) {
								if(ProtocolSupportAPI.getProtocolVersion(p) == ProtocolVersion.MINECRAFT_1_8) {
								ActionManager.sendActionBar(p, "§6§lDas Spiel beginnt in §3§l " + count + " §6§lSek.");
									}
								}
							}

						if (CountDownManager.this.count == 1) {
							

							Main.startGame();
							CountDownManager.lobbycd.cancel();
							Data.lobbyCDisRunning = false;
							
							Main.state = GameState.INGAME;
							
							for (final Player ps : Bukkit.getOnlinePlayers()) {
								
								WarmupCounter.warumupEnd(15, ps);
								
								RandomSpawn.TeleportRandomSpawnLoc(ps ,ps);
								Data.alive.add(ps);

							}
							
							for (Player ps : Bukkit.getOnlinePlayers()) {
								TitleManager.sendFullTitle(ps, -1, -1, -1, "§6Los Gehts !", "§eDu hast pro Runde 4 Leben.");
							}
							/*
							PlapsItems.setPlayItems(ps);
							ps.performCommand("spawn");
							*/
						}
						
						/*
						 * Exp-Countdown 60-30
						 */

						count--;
					}
				}, 20L, 20L);
		
		/*
		 * Announce-Scheduler 
		 */
	}

	@SuppressWarnings({ "unused", "static-access" })
	private float getFloat() {
		return (float) this.count / 0.6F;
	}

}
