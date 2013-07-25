package com.kodehawa.mods;

import net.minecraft.src.EntityLivingBase;
import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleSpider extends Mod implements Tickable{
	
	private Minecraft mc;
	private CheatingEssentials ce;
	private EntityLivingBase entitybase;

	public ModuleSpider(CheatingEssentials cb, Minecraft m) {
		super(Mods.Spider);
		cb = ce;
		m = mc;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		Vars.Climb = true;
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		Vars.Climb = false;
	}

}
