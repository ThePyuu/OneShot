package de.pyuu.oneshot.listener;

import de.pyuu.oneshot.Main;
import de.pyuu.oneshot.api.ParticleEffect;
import de.pyuu.oneshot.util.GameState;

import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

public class EntityDamageListener implements Listener {
	@EventHandler
	public void onArrowShoot(EntityDamageByEntityEvent e) {
		if (e.getEntity().getName() == e.getDamager().getName()) {
			e.setCancelled(true);
		} else if (((e.getEntity() instanceof Player))
				&& ((e.getDamager() instanceof Arrow))) {
			Arrow arrow = (Arrow) e.getDamager();
			if ((arrow.getShooter() instanceof Player)) {
				e.setDamage(500.0D);
			}
		}
		if (Main.state == GameState.LOBBY) {
			e.setCancelled(true);
		}
		if (Main.state == GameState.WARMUP) {
			e.setCancelled(true);
			e.getDamager().sendMessage(
					Main.PREFIX + "PvP ist in der Schutzphase aus !");
		}
	}

	@EventHandler
	public void onProjectileHit(ProjectileHitEvent e) {
		if ((e.getEntity() instanceof Arrow)) {
			Arrow arrow = (Arrow) e.getEntity();
			if ((arrow.getShooter() instanceof Player)) {
				ParticleEffect.REDSTONE.display(arrow.getLocation().getBlockX(), arrow.getLocation().getBlockY(), arrow.getLocation().getBlockZ(), 5, 50, arrow.getLocation(), 3);;
				arrow.remove();
			}
		}
	}
}
