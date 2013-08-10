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

	/**
	 * Variables
	 */
	
    public static CheatingEssentials modinstance;
	public ArrayList<Tickable> modInternalTicksArray = new ArrayList<Tickable>();
    public ArrayList<Mod> mods = new ArrayList<Mod>();
    public static ArrayList<String> enabledMods = new ArrayList<String>();

    
    /**
     * The constructor. 
     * It initialize the mod in GuiIngame (The hook one) and tick it.
     */
    
	public CheatingEssentials( ) {
		modInit();
	}
	
	/**
	 * Private method that initialize the basic and integrated things in Cheating Essentials.
	 * It initialize all classes that are needed for the correctly mod funcionality and some debug messages for know things
	 * Yeah, I know that CModLoader it's the most ugly / hardcoded thing in the world, but it's needed for make Keybinding to work :)
	 */
	
	private void modInit() {
		//TODO: Mod initialization.
		modinstance = this;
        ModManager.getInstance();
        ConsoleHelper.getInstance();
        CModLoader.getMInstance();
        FileManager.getInstance();
	}
	
	/**
	 * Get the singleton mod instance
	 * @return
	 */
	
	public static CheatingEssentials getCheatingEssentials(){
		return CheatingEssentials.modinstance;
	}
	
	/**
	 * Get Wrapper instance.
	 * @return
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
		if(Minecraft.getMinecraft().ingameGUI.getClass() != CE_GuiIngameH.class){
			System.out.println("[CE Hook Manager] [INFO] GuiIngame MC " + Minecraft.getMinecraft().ingameGUI.getClass() );
			Minecraft.getMinecraft().ingameGUI = new CE_GuiIngameH(getMinecraftInstance());
			System.out.println("[CE Hook Manager] [INFO] GuiIngame new " + Minecraft.getMinecraft().ingameGUI.getClass());
		}
	}
	
	/**
	 * Tick the entire mod.
	 * Initialize the Arrays, Update the pinned frames and get key pressing.
	 */
	
	public void tick() {
		
		for(Tickable tickable : modInternalTicksArray){
			tickable.tick();
		}
		
		KeyboardListener.getInstance().handleKeys();
	}
	
	public void CELogAgent(String log){
		System.out.println("[Cheating Essentials] [INFO] " + log);
	}
	
	public void CELogErrorAgent(String elog){
		System.err.println("[Cheating Essentials] [ERROR] " + elog);
	}

}
