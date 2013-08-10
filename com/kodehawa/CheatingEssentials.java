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

import org.lwjgl.input.Keyboard;

import com.kodehawa.core.CModLoader;
import com.kodehawa.event.EventHandler;
import com.kodehawa.event.events.EventKey;
import com.kodehawa.hooks.CE_GuiIngameH;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;
import com.kodehawa.players.FrenemyManager;
import com.kodehawa.util.FileManager;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Utils;
import com.kodehawa.util.wrapper.Wrapper;

public final class CheatingEssentials {
	
	/**
	 * @author Kodehawa, Godshawk
	 */
	
    public static CheatingEssentials modinstance;
	public ArrayList<Tickable> modInternalTicksArray = new ArrayList<Tickable>();
    public ArrayList<Mod> mods = new ArrayList<Mod>();
    public static ArrayList<String> enabledMods = new ArrayList<String>();
    private boolean[ ] keymap;

	public CheatingEssentials( ) {
		modInit();
	}
	
	/**
	 * Private method that initialize the basic and integrated things in Cheating Essentials.
	 * It initialize all classes that are needed for the correctly mod funcionality and some debug messages for know things
	 * Yeah, I know that CModLoader it's the most ugly thing in the world, but it's needed for make Keybinding to work :)
	 */
	
	private void modInit() {
		//TODO: Mod initialization.
		modinstance = this;
        EventHandler.getInstance();
        ModManager.getInstance();
        FileManager.getInstance();
        FrenemyManager.getInstance();
        Wrapper.getWInstance();
        CModLoader.getMInstance();
        keymap = new boolean[ 256 ];
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
	 * Module keybinding.
	 * It handles the events and toggle the specified mod with the keybinding specified in the module class and CModLoader.
	 * Also it handles the GUI key, that shows a hacked-client-style GUI. Sorry for that.
	 * I'm making another GUI, promise :)
	 */
	
    public void handleKeys( ) {
    	//TODO: Module Keys
    	
        for( Mod m : mods ) {
            int key = m.getKeybind( );
            if( getKeyStateFromMap( key ) ) {
                EventHandler.getInstance().call( new EventKey( this, m.getKeybind( ) ) );
                m.toggle();
                break;
            }
          }
        }
    
        /**
        * Get key things.
        * Like the old CheckKey :)
        */
       public boolean getKeyStateFromMap( int i ) {
        if( getMinecraftInstance().currentScreen != null ) {
            return false;
        }
        if( Keyboard.isKeyDown( i ) != keymap[ i ] ) {
            return keymap[ i ] = !keymap[ i ];
        } else {
            return false;
        }
    }

	/**
	 * Update "Active Cheats" frame and GUI utils for see what mods are actives.
	 */
	
	public void updateArray() {
		for (int i = 0; i < ModManager.getInstance().mods.size(); i++)
        {
            if (ModManager.getInstance().mods.get(i).isActive() && !enabledMods.contains(ModManager.getInstance().mods.get(i).name))
            {
                enabledMods.add(ModManager.getInstance().mods.get(i).name);
            }
            else if (!ModManager.getInstance().mods.get(i).isActive())
            {
                enabledMods.remove(ModManager.getInstance().mods.get(i).name);
            }
        }
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
		
		updateArray();
        handleKeys();
	}
	
	public void CELogAgent(String log){
		System.out.println("[Cheating Essentials] [INFO] " + log);
	}
	
	public void CELogErrorAgent(String log){
		System.err.println("[Cheating Essentials] [ERROR] " + log);
	}

}
