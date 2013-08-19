/**
 * Base classes modified:
 * 
 * 1- @Block.java
 * 2- @EntityPlayer.java
 * 3- @Minecraft.java
 */

package com.kodehawa;

import java.util.ArrayList;

import com.kodehawa.api.CJarLoader;
import com.kodehawa.core.DebugInfo;
import com.kodehawa.core.Strings;
import com.kodehawa.module.ModuleManager;
import com.kodehawa.module.loader.BaseLoader;
import com.kodehawa.playerrelations.Enemy;
import com.kodehawa.playerrelations.Friend;
import net.minecraft.src.Minecraft;

import com.kodehawa.util.FileManager;
import com.kodehawa.util.KeyboardListener;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Utils;
import com.kodehawa.util.wrapper.Wrapper;
import com.reeszrbteam.ce.console.CommandManager;

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
        CELogAgent("Starting Cheating Essentials " + Strings.MOD_VERSION + "...");
        modinstance = this;
        ModuleManager.getInstance();
        CJarLoader.getInstance();
        BaseLoader.getInstance();
        DebugInfo.debugInfo();
        Enemy.getInstance();
        Friend.getInstance();
        FileManager.getInstance();
        CELogAgent("Cheating Essentials " + Strings.MOD_VERSION +  " started in Minecraft 1.6.2");
	}
	
    /**
     * ReesZRB crap
     */
	public static void onStart(){
		CELogAgent("Initializing Command interface...");
        CommandManager.getInstance();
		CELogAgent("Initialized Command interface.");
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

	public static Minecraft getMinecraftInstance(){
		return Minecraft.getMinecraft();
	}

	public void tick() {

		for(Tickable tickable : ModuleManager.getInstance().modInternalTicksArray){
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
