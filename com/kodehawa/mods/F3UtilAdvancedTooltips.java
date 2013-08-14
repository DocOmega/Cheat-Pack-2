package com.kodehawa.mods;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;

public class F3UtilAdvancedTooltips extends Mod {

	@ModuleInformation(
			credits = "Kodehawa",
			desc = "Shows the advanced Tooltips",
			name = "Advanced Tooltips"
		)
	
	public F3UtilAdvancedTooltips( ) {
		super("Advanced Tooltips", "", 0);
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


	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}


}
