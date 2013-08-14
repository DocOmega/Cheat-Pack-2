package com.kodehawa.mods;

import net.minecraft.server.MinecraftServer;
import net.minecraft.src.EnumChatFormatting;
import net.minecraft.src.Minecraft;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.Tickable;

public class ModuleClientFastplace extends Mod implements Tickable {

	@ModuleInformation(
			credits = "Kodehawa",
			desc = "Allows to the player in SP to place blocks more fast than normal",
			name = "Client Block Fast Place"
			)
	
	public ModuleClientFastplace() {
		super("CFast Place", "Place ALL the blocks!", Keyboard.KEY_K);

		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
            CheatingEssentials.getCheatingEssentials().getMinecraftInstance().rightClickDelayTimer = 0;
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		if(CheatingEssentials.getCheatingEssentials().getMinecraftInstance().isSingleplayer()){
			CheatingEssentials.getCheatingEssentials().addToTick(this);
		}
		else{
			CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
			CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.addChatMessage(EnumChatFormatting.YELLOW + "Can't enable Fast Place module in MP servers.");
			System.out.println("Single Player mode = " +
			Boolean.valueOf(Minecraft.getMinecraft().isSingleplayer()));
		}
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
		

	}

}
