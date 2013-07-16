package com.kodehawa.mods;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleItemTooltips extends Mod implements Tickable {

	private Minecraft mc;
	private CheatBase cb;
	
	public ModuleItemTooltips(CheatBase c, Minecraft m) {
		super( Mods.ItemTooltips );
		// TODO Auto-generated constructor stub
		cb = c;
		mc = m;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		mc.gameSettings.heldItemTooltips = false;
		cb.getUtils().addChatMessage(getActive());
		cb.getUtils().addChatMessage("Tooltips disabled");
	}

	@Override
	public void onDisable() {
		mc.gameSettings.heldItemTooltips = true;
		cb.getUtils().addChatMessage(getActive());
	}

}