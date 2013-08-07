package com.kodehawa.mods;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;

public class F3UtilRerenderLoadedChunks extends Mod {

	public F3UtilRerenderLoadedChunks() {
		super("Chunk Re-Render", "", 0);
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


	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
