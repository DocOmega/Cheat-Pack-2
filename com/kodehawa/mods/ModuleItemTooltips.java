package com.kodehawa.mods;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.util.Tickable;

public class ModuleItemTooltips extends Mod {

	
	
	public ModuleItemTooltips() {
		super( "", "", Keyboard.KEY_NONE, Mods.ItemTooltips );
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onEnable() {
		CheatingEssentials.getCheatingEssentials().getMinecraftInstance().gameSettings.heldItemTooltips = false;
	}

	@Override
	public void onDisable() {
		CheatingEssentials.getCheatingEssentials().getMinecraftInstance().gameSettings.heldItemTooltips = true;
	}


	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
