package com.kodehawa.hooks;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;

public class HookManager {

	public static void gameGui(){
		if(CheatingEssentials.getMinecraftInstance().ingameGUI.getClass() != CE_GuiIngameH.class){
			CheatingEssentials.getCheatingEssentials().CELogAgent("GuiIngame MC " + Minecraft.getMinecraft().ingameGUI.getClass() );
			CheatingEssentials.getMinecraftInstance().ingameGUI = new CE_GuiIngameH(CheatingEssentials.getMinecraftInstance());
			CheatingEssentials.getCheatingEssentials().CELogAgent("GuiIngame new " + Minecraft.getMinecraft().ingameGUI.getClass());
		}
	}
	
	public static void rendererSystem(){
		if(CheatingEssentials.getMinecraftInstance().entityRenderer.getClass() != Renderer.class){
			CheatingEssentials.getCheatingEssentials().CELogAgent("EntityRenderer MC " + Minecraft.getMinecraft().entityRenderer.getClass() );
			CheatingEssentials.getMinecraftInstance().entityRenderer = new Renderer(CheatingEssentials.getMinecraftInstance());
			CheatingEssentials.getCheatingEssentials().CELogAgent("EntityRenderer new " + Minecraft.getMinecraft().entityRenderer.getClass());
		}
	}
}
