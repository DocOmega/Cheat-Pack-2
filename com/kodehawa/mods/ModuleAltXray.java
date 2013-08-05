package com.kodehawa.mods;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;

public class ModuleAltXray extends Mod{

	public ModuleAltXray( ) {
		super("Alternative X-Ray", "Without lag :)", Keyboard.KEY_P, Mods.AltXray);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		Vars.xray = true;
		CheatingEssentials.getCheatingEssentials().getMinecraftInstance().renderGlobal.loadRenderers();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		Vars.xray = false;
		CheatingEssentials.getCheatingEssentials().getMinecraftInstance().renderGlobal.loadRenderers();

	}

}
