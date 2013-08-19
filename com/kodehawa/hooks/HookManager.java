package com.kodehawa.hooks;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;

public class HookManager {

	public static void gameGui(){
		if(CheatingEssentials.getMinecraftInstance().ingameGUI.getClass() != CE_GuiIngameH.class){
			CheatingEssentials.getMinecraftInstance().ingameGUI = new CE_GuiIngameH(CheatingEssentials.getMinecraftInstance());
		}
	}
	
	public static void rendererSystem(){
		if(CheatingEssentials.getMinecraftInstance().entityRenderer.getClass() != Renderer.class){
			CheatingEssentials.getMinecraftInstance().entityRenderer = new Renderer(CheatingEssentials.getMinecraftInstance());
		}
	}
}
