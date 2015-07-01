package de.pyuu.oneshot.methods;

import org.bukkit.craftbukkit.v1_8_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R2.IChatBaseComponent;
import net.minecraft.server.v1_8_R2.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R2.PacketPlayOutChat;

public class IchatBaseComp {
	
	public static void sendChatSerializer(Player reciever, String ClickableText,  String Method) {
		
		
		 IChatBaseComponent comp = ChatSerializer
		            .a("{\"text\":\"" +  
		            "\",\"extra\":[{\"text\":\"" + ClickableText + 
		            "\",\"clickEvent\":{\"action\":\"run_command\",\"value\":\"" + 
		            Method +  "\"}}]}");
		 
		 
		 
		 PacketPlayOutChat packet = new PacketPlayOutChat(comp);
		 ((CraftPlayer) reciever).getHandle().playerConnection.sendPacket(packet);	
		 		
	}
	
	

}
