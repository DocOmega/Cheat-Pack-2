package com.kodehawa.mods;

import net.minecraft.src.Minecraft;
import net.minecraft.src.RenderGlobal;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleTestChestFinder extends Mod implements Tickable {
	
	protected Minecraft minecraft;
	protected CheatBase cb;

	/**
	 * I need OpenGL for this. Dammit!
	 * @param mod
	 * @param c
	 * @param m
	 */
	
	public ModuleTestChestFinder(CheatBase c, Minecraft m) {
		super( Mods.ChestESP );
		cb = c;
		minecraft = m;
		
		// TODO Auto-generated constructor stub
	}
	
	

	@Override
	public void tick() {
		
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		cb.getUtils().addChatMessage("For enable Chest ESP press N!");
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		cb.getUtils().addChatMessage("Thanks for understanding :)");
		
	}



}
