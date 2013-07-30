package com.kodehawa.mods;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleItemTooltips extends Mod {

	
	
	public ModuleItemTooltips() {
		super( Mods.ItemTooltips );
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

}
