package com.kodehawa.classLoaderTest;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.api.ModuleAnnotation;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.Mods;
import com.kodehawa.util.Tickable;

public class ModuleRandom extends Mod implements Tickable{
	@ModuleAnnotation
	public Minecraft mc;
	
	public ModuleRandom( ) {
		super(Mods.Random);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

}
