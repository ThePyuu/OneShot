package de.pyuu.oneshot.methods;


import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;




public class ActionManager {
	/*
	 * Methode für die Actionbar
	 */
	
	public static void sendActionBar(Player player, String message) {
        ((CraftPlayer) player).getHandle().playerConnection
                .sendPacket(new PacketPlayOutChat(IChatBaseComponent.ChatSerializer
                        .a("{\"text\": \"" + ChatColor.translateAlternateColorCodes('&', message) + "\"}"), (byte) 2));
	}
}
