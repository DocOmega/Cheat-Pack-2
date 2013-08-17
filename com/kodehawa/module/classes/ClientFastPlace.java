package com.kodehawa.module.classes;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.enums.EnumGuiCategory;
import com.kodehawa.util.Tickable;

public class ClientFastPlace extends ModuleBase implements Tickable{

	public ClientFastPlace( ) {
		super("CFast Place", "Place ALL blocks!", "1.6.2", Keyboard.KEY_K, 
				EnumGuiCategory.WORLD, true);
	}

	@Override
	public void onEnableModule( ){
		CheatingEssentials.getCheatingEssentials().addToTick(this);
	}
	
	@Override 
	public void onDisableModule( ){
		CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
	}
	
	@Override
	public void tick( ) {
		CheatingEssentials.getMinecraftInstance().rightClickDelayTimer = 0;
	}

}
