package de.pyuu.oneshot;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import de.pyuu.oneshot.chat.ChatManager;
import de.pyuu.oneshot.commands.DevMode;
import de.pyuu.oneshot.commands.FixCommand;
import de.pyuu.oneshot.commands.LobbySpawnCommand;
import de.pyuu.oneshot.commands.RegelnCommand;
import de.pyuu.oneshot.commands.SetHolostatsCommand;
import de.pyuu.oneshot.commands.SetlobbyCommand;
import de.pyuu.oneshot.commands.StartCommand;
import de.pyuu.oneshot.listener.BlockedEvents;
import de.pyuu.oneshot.listener.DamageParticleListener;
import de.pyuu.oneshot.listener.DeathListener;
import de.pyuu.oneshot.listener.EntityDamageListener;
import de.pyuu.oneshot.listener.FlightListener;
import de.pyuu.oneshot.listener.InteractListener;
import de.pyuu.oneshot.listener.JoinListener;
import de.pyuu.oneshot.listener.LoginEventListener;
import de.pyuu.oneshot.listener.MessageManagerJoinQuit;
import de.pyuu.oneshot.listener.PingEvent;
import de.pyuu.oneshot.listener.Preprocess;
import de.pyuu.oneshot.listener.QuitListener;
import de.pyuu.oneshot.listener.RespawnListener;
import de.pyuu.oneshot.methods.Announcer;
import de.pyuu.oneshot.methods.CheckPlayerStatus;
import de.pyuu.oneshot.methods.InstantRespawn;
import de.pyuu.oneshot.methods.lives;
import de.pyuu.oneshot.mysql.MySQL;
import de.pyuu.oneshot.util.CountDownManager;
import de.pyuu.oneshot.util.Data;
import de.pyuu.oneshot.util.GameState;



public class Main extends JavaPlugin implements Listener {

	
	public static String PREFIX = "§8[§3OneShot§8] §6";
	static Main instance;
	public static  MySQL mysql;
	private CountDownManager cdmanager;
	
	public static List<String> nicks = new ArrayList<String>();
	
	/*
	 * Permissions:
	 * - os.setlobbyspawn (Lobbyspawn setzen)
	 * - os.setspawn (Game-Spawns setzen)
	 * - os.start (Force-Sart einleiten)
	 * - os.setholostats (Holostats Position setzte)
	 * - os.Spectator.oversize (Anzahl an Macimalen zuschauen überschreiten)
	 */
	

	@Override
	public void onEnable() {	
		Main.state = GameState.LOBBY;
		
		Data.lobbyIsStarting = false;
		
		for(Player collective : Bukkit.getOnlinePlayers()) {
		Announcer.sendAnnounce(collective);
		}
		
		Bukkit.getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		
		System.out.println("[OneShot] Keine Fehler aufgetreten.");
		System.out.println("[OneShot] Plugin wurde gestartet.");
		instance = this;
		
		// Die Registrierung der Events und Commands:
	
		this.getServer().getPluginManager().registerEvents(this, this);
		registerCommands();
		registerEvents();
        mysql=new MySQL(getConfig().getString("Config.MySQL.User"),getConfig().getString("Config.MySQL.Password"),getConfig().getString("Config.MySQL.Host"),getConfig().getString("Config.MySQL.DB"));
        mysql.connect();
		mysql.update("CREATE TABLE IF NOT EXISTS Stats(UUID varchar(64), KILLS int, DEATHS int);");
        loadConfig();
		// CountDownManager usw...
		cdmanager = new CountDownManager(this);
		cdmanager.checkToStart();
		Bukkit.getWorld("world").setGameRuleValue("doDaylightCycle", "false");
		Bukkit.getWorld("world").setTime(0);
		
		for(Player pa : Bukkit.getOnlinePlayers()) {
		CheckPlayerStatus.checkLobbyStatus(pa);
		}
		
		for(World w : Bukkit.getWorlds()) {
			for(Entity e : w.getEntities()) {
				if(!(e instanceof Player)) {
					e.remove();
				}
			}
		}
	}

	@Override
	public void onDisable() {		
		System.out.println("[OneShot] Keine Fehler aufgetreten.");
		System.out.println("[OneShot] Plugin wurde gestoppt.");
		instance = null;
	}

	public static Main getInstance() {
		return instance;
	}
	
	
    public void loadConfig(){
        getConfig().addDefault("Config.MySQL.Host", "Insert");
        getConfig().addDefault("Config.MySQL.User", "Insert");
        getConfig().addDefault("Config.MySQL.Password", "Insert");
        getConfig().addDefault("Config.MySQL.DB", "Insert");
        getConfig().options().copyDefaults(true);
        saveConfig();
      }
	
	
	public void registerEvents() {
		this.getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
		this.getServer().getPluginManager().registerEvents(new BlockedEvents(), this);
		this.getServer().getPluginManager().registerEvents(new DeathListener(), this);
		this.getServer().getPluginManager().registerEvents(new QuitListener(), this);
		this.getServer().getPluginManager().registerEvents(new JoinListener(), this);
		this.getServer().getPluginManager().registerEvents(new RespawnListener(), this);
		this.getServer().getPluginManager().registerEvents(new InstantRespawn(), this);
		this.getServer().getPluginManager().registerEvents(new ChatManager(), this);
		this.getServer().getPluginManager().registerEvents(new LoginEventListener(), this);
		this.getServer().getPluginManager().registerEvents(new Preprocess(), this);
		this.getServer().getPluginManager().registerEvents(new MessageManagerJoinQuit(), this);
		this.getServer().getPluginManager().registerEvents(new PingEvent(), this);
		this.getServer().getPluginManager().registerEvents(new InteractListener(), this);
		this.getServer().getPluginManager().registerEvents(new FlightListener(), this);
		this.getServer().getPluginManager().registerEvents(new DamageParticleListener(), this);
	}

	public void registerCommands() {
		this.getCommand("setlobbyspawn").setExecutor(new SetlobbyCommand());
		this.getCommand("setholostats").setExecutor(new SetHolostatsCommand());
		this.getCommand("lobbyspawn").setExecutor(new LobbySpawnCommand());
		this.getCommand("start").setExecutor(new StartCommand());
		this.getCommand("fix").setExecutor(new FixCommand());
		this.getCommand("devmode").setExecutor(new DevMode());
		this.getCommand("regelnLKJM45637InstanceOFMenue").setExecutor(new RegelnCommand());
	}

	public static void startGame() {
		state=GameState.WARMUP;
		CountDownManager.lobbycd.cancel();
for (Player ps : Bukkit.getOnlinePlayers()) {
			ps.getInventory().clear();
			ps.sendMessage(PREFIX + "§a Das Spiel wurde gestartet!");
			ps.sendMessage(PREFIX + "15 Sekunden Schutzzeit, sucht euch ein gute Position !");	
			
			lives.setLives(ps, 4);
		}

	}
	public static GameState state;
	
}
