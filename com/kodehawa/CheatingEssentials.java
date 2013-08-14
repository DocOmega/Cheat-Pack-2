/**
 * Base classes modified:
 * 
 * 1- @Block.java
 * 2- @EntityPlayer.java
 * 3- @Minecraft.java
 * 4- @TileEntityChestRenderer.java
 */

package com.kodehawa;

import java.util.ArrayList;

import net.minecraft.src.Minecraft;

import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.core.CModLoader;
import com.kodehawa.core.DebugInfo;
import com.kodehawa.event.EventHandler;
import com.kodehawa.event.events.EventTick;
import com.kodehawa.hooks.CE_GuiIngameH;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;
import com.kodehawa.util.FileManager;
import com.kodehawa.util.KeyboardListener;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Utils;
import com.kodehawa.util.wrapper.Wrapper;


/**
 * @author Kodehawa, Godshawk
 */

public final class CheatingEssentials {

    public volatile static CheatingEssentials modinstance;
	public ArrayList<Tickable> modInternalTicksArray = new ArrayList<Tickable>();
    
    /**
     * The constructor. 
     * It initialize the mod in GuiIngame (The hook one) and tick it.
     * It initialize all classes that are needed for the correctly mod funcionality and some debug messages for know things
     * Yeah, I know that CModLoader it's the most ugly / hardcoded thing in the world, but it's needed for make Keybinding to work :)
     */
    
	public CheatingEssentials( ) {
        modinstance = this;
        ModManager.getInstance();
        ConsoleHelper.getInstance();
        CModLoader.getMInstance();
        DebugInfo.debugInfo();
        FileManager.getInstance();
	}

	/**
	 * Get the singleton mod instance
	 */
	
	public static CheatingEssentials getCheatingEssentials(){
		return modinstance;
	}
	
	/**
	 * Get Wrapper instance.
	 */
	
	public Wrapper getModWrapper(){
		return Wrapper.getWInstance();
	}
	
	/**
	 * Get mod utilies
	 */

	public Utils getUtils() {
		return Utils.getInstance();
	}
	
	/**
	 * Get the singleton Minecraft instance.
	 * Used for something and for no call things like mc.d.ft., instead of this call the things like CheatingEssentials.getCheatingEssentials().getMinecraftInstance()....
	 * And for some stability and prevent crashes when loading native MC methods to the mod modules / classes.
	 */
	
	public static Minecraft getMinecraftInstance(){
		return Minecraft.getMinecraft();
	}

	/**
	 * Tick a mod in the Minecraft instance, needed for most modules, but some modules don't need it.
	 */

	public void addToTick(Tickable tickable) {
		 if (!modInternalTicksArray.contains(tickable))
	        {
			 modInternalTicksArray.add(tickable);
	        }
		
	}

	/**
	 * Remove a mod from a current tick.
	 */
	
	public void removeFromCurrentTick(Tickable tickable) {
		 if (modInternalTicksArray.contains(tickable))
	        {
			 modInternalTicksArray.remove(tickable);
	        }
	}

	public static void replaceGUI(){
		if(getMinecraftInstance().ingameGUI.getClass() != CE_GuiIngameH.class){
			CELogAgent("GuiIngame MC " + Minecraft.getMinecraft().ingameGUI.getClass() );
			getMinecraftInstance().ingameGUI = new CE_GuiIngameH(getMinecraftInstance());
			CELogAgent("GuiIngame new " + Minecraft.getMinecraft().ingameGUI.getClass());
		}
	}
	
	/**
	 * Tick the entire mod.
	 */
	
	public void tick() {

		for(Tickable tickable : modInternalTicksArray){
			tickable.tick();
		}

		KeyboardListener.getInstance().handleKeys();
	}
	
	public static void CELogAgent(String log){
		getMinecraftInstance().field_94139_O.logInfo("[Cheating Essentials] " + log);
	}
	
	public static void CELogErrorAgent(String elog){
		getMinecraftInstance().field_94139_O.logSevere( "[Cheating Essentials] " + elog);
	}

}
