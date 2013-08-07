package com.kodehawa.mods;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.Tickable;

public class ModuleClientFastplace extends Mod implements Tickable {

	
	public ModuleClientFastplace() {
		super("Fast Place", "Place ALL the blocks!", Keyboard.KEY_K, Mods.ClientFastPlace);

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
		if(CheatingEssentials.getCheatingEssentials().getMinecraftInstance().isIntegratedServerRunning()){
			CheatingEssentials.getCheatingEssentials().addToTick(this);
		}
		else{
			CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.addChatMessage(ChatColour.BLUE + "[CE v3] Can't enable Fast Place module in MP servers.");
			CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
			//System.out.println("This it's freaking working?");
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
