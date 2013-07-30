package com.kodehawa.mods;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class F3UtilAdvancedTooltips extends Mod{

	public F3UtilAdvancedTooltips( ) {
		super(Mods.F3AdvancedTooltips);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance().gameSettings.advancedItemTooltips = !CheatingEssentials.getCheatingEssentials().getMinecraftInstance().gameSettings.advancedItemTooltips;
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance().gameSettings.saveOptions();
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance().gameSettings.advancedItemTooltips = !CheatingEssentials.getCheatingEssentials().getMinecraftInstance().gameSettings.advancedItemTooltips;
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance().gameSettings.saveOptions();
	}

}
