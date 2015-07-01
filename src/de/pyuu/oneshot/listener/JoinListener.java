package de.pyuu.oneshot.listener;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.methods.ActionManager;
import de.pyuu.oneshot.methods.CheckPlayerStatus;
import de.pyuu.oneshot.methods.IchatBaseComp;
import de.pyuu.oneshot.methods.RandomSpawn;
import de.pyuu.oneshot.methods.TitleManager;
import de.pyuu.oneshot.methods.lives;
import de.pyuu.oneshot.mysql.HoloStats;
import de.pyuu.oneshot.mysql.SQLStats;
import de.pyuu.oneshot.util.Data;
import de.pyuu.oneshot.util.GameState;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.potion.PotionEffectType;

import protocolsupport.api.ProtocolSupportAPI;
import protocolsupport.api.ProtocolVersion;

public class JoinListener implements Listener {
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		
		if(Data.lobbyIsStarting == true) {
			e.setJoinMessage(null);
			if(Main.state != GameState.INGAME || Main.state != GameState.WARMUP) {
			e.getPlayer().kickPlayer(Main.PREFIX + "§cDie Runde ist im Start-Modus !"
					+ "versuche es Später erneut um Als §3Zuschauer §cbeizutreten !");
			}
		}
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				Data.lobbyIsStarting = false;
				
			}
		}, 40L);
		
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather clear 1000");
		
		
		final Player p = e.getPlayer();
		SQLStats.createPlayer(p.getUniqueId().toString());
		new HoloStats(p);

		p.removePotionEffect(PotionEffectType.SPEED);
		p.setLevel(0);
		
		/*
		 * Update 1.0
		 */
	
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {

				for(int i = 0; i < 300; i++) {
					p.sendMessage(" ");
				}
				p.sendMessage("§3§l§m------------------------------------------");
				IchatBaseComp.sendChatSerializer(p, "§4§l" + "➤" + "§aSpiel-Menü §8[§eClick§8]", "/regelnLKJM45637InstanceOFMenue");
				p.sendMessage(" ");
				p.sendMessage("§eDie Runde Startet ab 4 Spielern !");
				p.sendMessage("§7OneShot §aVersion " + Data.version + " §7Update by §3Div_124");
				p.sendMessage("§3§l§m------------------------------------------");
		
			}
		}, 40L);

		
		World ww = Bukkit.getWorld("world");
		ww.setTime(200);
		ww.setThundering(false);
		ww.setStorm(false);
		
		p.setHealth(20.0D);
		p.setFoodLevel(20);
		if (Data.devMode) {
			e.setJoinMessage(null);
			if (!p.hasPermission("os.developer")) {
				p.kickPlayer(Main.PREFIX
						+ "§6Sorry, der Server ist im Moment im Wartungs-Modus !");
				return;
			}
		}
		
		if (p.getGameMode() == GameMode.SPECTATOR) {
			p.setGameMode(GameMode.ADVENTURE);
		}
		p.getInventory().clear();

		p.performCommand("lobbyspawn");
		if (((Main.state == GameState.INGAME ? 1 : 0) | (Main.state == GameState.WARMUP ? 1
				: 0)) != 0) {
			if (Data.spec.size() <= 50) {
				p.setGameMode(GameMode.SPECTATOR);
				for (final Player alive : Bukkit.getOnlinePlayers()) {
					Bukkit.getScheduler().scheduleSyncDelayedTask(
							Main.getInstance(), new Runnable() {
								public void run() {
									alive.hidePlayer(e.getPlayer());
								}
							}, 20L);
				}
				Data.spec.add(p);
				ActionManager.sendActionBar(p, "§6Du bist im Zuschauermodus.");
				if (ProtocolSupportAPI.getProtocolVersion(p) == ProtocolVersion.MINECRAFT_1_8) {
					TitleManager.sendFullTitle(p, Integer.valueOf(-1),
							Integer.valueOf(-1), Integer.valueOf(-1),
							"§aDie Runde hat bereits begonnen.",
							"§6Du bist im Zuschauermodus.");
					for(Player all : Bukkit.getOnlinePlayers()) {
					RandomSpawn.TeleportRandomSpawnLoc(p, all);
					all.hidePlayer(p);
					}
				}
			} else {
				p.setGameMode(GameMode.SURVIVAL);
			}
			lives.setLives(p, 4);
			if (!Data.defaulttrail.contains(p.getName())) {
				Data.defaulttrail.add(p.getName());
			}
			e.setJoinMessage(p.getDisplayName()
					+ "§a§l hat den Server betreten.");
		
			if (ProtocolSupportAPI.getProtocolVersion(p) == ProtocolVersion.MINECRAFT_1_8) {
				CheckPlayerStatus.checkLobbyStatus(p);
				TitleManager.sendTitle(p, Integer.valueOf(-1),
						Integer.valueOf(-1), Integer.valueOf(-1),
						"§aWillkommen zu OneShot");
			}
			CheckPlayerStatus.checkLobbyStatus(p);
			p.getInventory().clear();
			p.getInventory().setArmorContents(null);
			p.setHealth(20.0D);
			p.setFoodLevel(20);
			if (Main.state == GameState.INGAME) {
				if (Data.spec.size() < 25) {
					p.setGameMode(GameMode.SPECTATOR);
				} else if (!p.hasPermission("os.developer")) {
					p.kickPlayer(Main.PREFIX
							+ "Das Spectator-Limit wurde erreicht (25)");
				}
			}
		}
		if (Main.state == GameState.LOBBY) {
			p.setGameMode(GameMode.ADVENTURE);
			e.setJoinMessage("§e" + p.getDisplayName()
					+ " §6hat das Spiel betreten.");
		} else if (Main.state == GameState.INGAME) {
			p.setGameMode(GameMode.SPECTATOR);
			for (Player ps : Bukkit.getOnlinePlayers()) {
				RandomSpawn.TeleportRandomSpawnLoc(p, ps);
			}
		}
		if ((p.getName().equals("div_124")) || (p.getName().equals("Pyuu"))) {
			e.setJoinMessage("§7[§bDeveloper§7] §4" + p.getDisplayName()
					+ " §6hat das Spiel betreten.");
		}
		if (p.getName().equals("MadGrover")) {
			e.setJoinMessage("§7[§4Owner§7§] §4" + p.getDisplayName()
					+ " §6hat das Spiel betreten.");
		}
		if (p.getName().equals("Simongamer")) {
			e.setJoinMessage("§7[§4Co-Owner§7] §4" + p.getDisplayName()
					+ " §6hat das Spiel betreten");
		}
	}

	@EventHandler
	public void onlogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();
		if (Data.alive.size() >= 16) {
			if(Main.state != GameState.INGAME || Main.state != GameState.WARMUP) {
			p.kickPlayer(Main.PREFIX + "§c§lMaximale Spieler Anzahl erreicht!");
			}
		}
	}
}
