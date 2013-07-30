package com.kodehawa.mods;

import com.kodehawa.CheatingEssentials;

public class F3UtilRerenderLoadedChunks extends Mod {

	public F3UtilRerenderLoadedChunks() {
		super(Mods.F3Renderers);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		CheatingEssentials.getCheatingEssentials().getMinecraftInstance().renderGlobal.loadRenderers();
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		
	}

}
