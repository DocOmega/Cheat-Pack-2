/**
 * Base classes modified:
 * 
 * 1- @GuiIngame.java
 * 2- @Block.java
 * 3- @EntityPlayer.java
 * 4- @Minecraft.java
 * 5- @TileEntityChestRenderer.java
 */


package com.kodehawa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.src.Gui;
import net.minecraft.src.ILogAgent;
import net.minecraft.src.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.kodehawa.api.CJarLoader;
import com.kodehawa.console.BaseCommand;
import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.core.CModLoader;
import com.kodehawa.core.CThreadUpdateChecker;
import com.kodehawa.core.CheckKey;
import com.kodehawa.core.KeyManager;
import com.kodehawa.core.Strings;
import com.kodehawa.event.Event;
import com.kodehawa.event.EventHandler;
import com.kodehawa.event.events.EventAlert;
import com.kodehawa.event.events.EventKey;
import com.kodehawa.gui.api.components.Frame;
import com.kodehawa.gui.api.components.ModuleGui;
import com.kodehawa.gui.api.testing.AlertHandler;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;
import com.kodehawa.mods.ModuleXray;
import com.kodehawa.players.FrenemyManager;
import com.kodehawa.util.CModTicks;
import com.kodehawa.util.FileManager;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Utils;
import com.kodehawa.util.wrapper.Wrapper;

public final class CheatingEssentials extends Thread implements CModTicks {
	
	/**
	 * @author Kodehawa, Godshawk
	 */
	
    public static CheatingEssentials modinstance;
	public Minecraft minecraft;
	public Wrapper getModWrapper;
	public File mainDir;
	public CheckKey KeyBinding;
	private Event theInternalEvents;
	public ModuleGui MainGui;
	private Utils modUtils;
	public KeyManager modKeyManager;
	public EventHandler eventHandler;
	public static ModManager mainModLoader;
    private File FileWritter;
	public Tickable modInternalTicks;
	public HashMap<Mod, Integer> keys;
	public ArrayList<Tickable> modInternalTicksArray = new ArrayList<Tickable>();
    public ArrayList<Mod> mods = new ArrayList<Mod>();
    public static ArrayList<String> enabledMods = new ArrayList<String>();
    public FrenemyManager theFriendManager;
	public ConsoleHelper theConsoleHelper;
	public CJarLoader externalLoader;
	private AlertHandler alertManager;
	private static CThreadUpdateChecker update;
	private int tick = 0;
	public static boolean outdatedAlert = false;
	private BaseCommand consoleBase;
	private long now;
	private long then;
    private boolean[ ] keymap;
    public static File crashDir;
	private FileManager filemanager;
	private volatile boolean stopRequested = false;

    
    /**
     * Constructor
     */
	
	public CheatingEssentials(Minecraft mc) {
		minecraft = mc;
		KeyBinding = new CheckKey(mc);
		run();
	}
	
	/**
	 * Used for make to the console print debug messages.
	 * Set to true for view a lot of log messages :)
	 */
	public static boolean debugMode = false;

	
	/**
	 * Private method that initialize the basic and integrated things in Cheating Essentials.
	 * It initialize all classes that are needed for the correctly mod funcionality and some debug messages for know things
	 * Yeah, I know that CModLoader it's the most ugly thing in the world, but it's needed for make Keybinding to work :)
	 */
	
	private void modInit() {
		
		//TODO: Mod initialization.
		mainDir = new File(getMinecraftInstance().mcDataDir, "/config/Cheating Essentials/CEXrayBlockList.txt");
        crashDir = new File(getMinecraftInstance().mcDataDir, "/Cheating Essentials/crash/" + File.separator + ".log");
		modinstance = this;
		CELogAgent("OpenGL: " + GL11.glGetString(GL11.GL_VERSION));
        CELogAgent(Strings.MOD_NAME + " " + Strings.MOD_VERSION + " " + "starting in" + " " + Strings.MINECRAFT_VERSION + "...");
        eventHandler = new EventHandler();
		mainModLoader = new ModManager(this);
		CModLoader.addModulestoArray();
		CModLoader.writeModuleDebugInfo();
		modUtils = new Utils(minecraft);
		MainGui = new ModuleGui();
		minecraft = getMinecraftInstance();
        now = System.currentTimeMillis();
        then = now + 250;
		modKeyManager = new KeyManager();
		theConsoleHelper = new ConsoleHelper();
        keys = new HashMap<Mod, Integer>();
        theFriendManager = new FrenemyManager();
        getModWrapper = new Wrapper();
        modKeyManager = new KeyManager();
        keymap = new boolean[ 256 ];
        externalLoader = new CJarLoader();
        update = new CThreadUpdateChecker();
        CModLoader.loadModulesforKB();
        loadXrayList();
        CELogAgent(Strings.MOD_NAME + " " + Strings.MOD_VERSION + " started succefully in " + Strings.MINECRAFT_VERSION);
        if(!mainDir.exists()){
        saveXrayList();
        }
		
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
		return getModWrapper;
	}
	
	/**
	 * Get mod utilies
	 */

	@Override
	public Utils getUtils() {
		return modUtils;
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
    	
		if( getKeyStateFromMap(Keyboard.KEY_G)){
			 minecraft.displayGuiScreen(MainGui);
		}
		
        for( Mod m : mods ) {
            int key = m.getKeybind( );
            if( getKeyStateFromMap( key ) ) {
                eventHandler.call( new EventKey( this, m.getKeybind( ) ) );
                m.toggle();
                break;
            }

        }
        if( getKeyStateFromMap( Keyboard.KEY_NUMPAD0 ) ) {
            eventHandler.call( new EventAlert( this, "Event fired!" ) );
        }
        }
    
        /**
        * Get key things.
        * Like com.kodehawa.core.CheckKey.class
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
	 * Now with a configurable Xray :D
	 * Write the entire file again when a block it's changed in-game
	 */
	
    public void saveXrayList( ) {
        try {
        		CELogAgent("Writting X-Ray block list configuration file...");
            File file = new File( mainDir, "" );
            BufferedWriter bufferedwritter = new BufferedWriter( new FileWriter( file ) );
            for( int i : ModuleXray.xrayBlocks ) {
            	bufferedwritter.write( i + "\r\n" );
            }
            bufferedwritter.close( );
        	
        } catch( Exception ex ) {
        	CELogAgent("Can't write X-Ray configuration file! Custom blocks for X-Ray will be disabled!");
        	 ex.printStackTrace( );
        	 CELogAgent( "Error in CE init: " + ex.toString( ) );
	            ex.printStackTrace( );
        }
    }
    
    /**
     * Load the integers of the Xray list. Only readed once
     * If a error it's finded it saves the X-Ray list again for prevent errors.
     */
    
    public void loadXrayList( ) {
        try {
        	File file = new File( mainDir, "" );
            FileInputStream fstream = new FileInputStream( file.getAbsolutePath( ) );
            DataInputStream in = new DataInputStream( fstream );
            BufferedReader br = new BufferedReader( new InputStreamReader( in ) );
            String line;
            while( ( line = br.readLine( ) ) != null ) {
                String curLine = line.toLowerCase( ).trim( );
                int id = Integer.parseInt( curLine );
                ModuleXray.xrayBlocks.add( id );
            }
            br.close( );
        } catch( Exception ex ) {
            CELogAgent("Can't load X-Ray list. Unreliable results!");
            CELogAgent( "Error in CE init: " + ex.toString( ) );
            ex.printStackTrace( );
            saveXrayList( );
    } 
    }
    
    
	/**
	 * Update "Active Cheats" frame and GUI utils for see what mods are actives.
	 */
	
	@Override
	public void updateArray() {
		for (int i = 0; i < mainModLoader.mods.size(); i++)
        {
            if (mainModLoader.mods.get(i).isActive() && !enabledMods.contains(mainModLoader.mods.get(i).name))
            {
                enabledMods.add(mainModLoader.mods.get(i).name);
            }
            else if (!mainModLoader.mods.get(i).isActive())
            {
                enabledMods.remove(mainModLoader.mods.get(i).name);
            }
        }
	}
	


	/**
	 * Tick a mod in the Minecraft instance, needed for most modules, but some modules don't need it.
	 */

	@Override
	public void addToTick(Tickable tickable) {
		 if (!modInternalTicksArray.contains(tickable))
	        {
			 modInternalTicksArray.add(tickable);
	        }
		
	}

	/**
	 * Remove a mod from a current tick.
	 */
	
	@Override
	public void removeFromCurrentTick(Tickable tickable) {
		 if (modInternalTicksArray.contains(tickable))
	        {
			 modInternalTicksArray.remove(tickable);
	        }
	}

	/**
	 * Tick the entire mod.
	 * Initialize the Arrays, Update the pinned frames and get key pressing.
	 */
	
	@Override
	public void tick() {
		
		for(Tickable tickable : modInternalTicksArray){
			tickable.tick();
		}
		
		updateArray();
        updatePinnedFrames();
        handleKeys();
	}
	
	
	/**
	 * Update pinnable frames. (It's not working, but soonely it will be deleted)
	 */
	public void updatePinnedFrames()
    {
        if ((minecraft.currentScreen == null) || (minecraft.currentScreen == (Gui) minecraft.ingameGUI))
        {
            for (Frame frame : MainGui.frames)
            {
                if (frame.pinned && frame.pinnable)
                {
                    frame.update();
                }
            }
        }
    }
	
	/**
	 * Request the stop of CE loading thread.
	 */

	public void requestThreadStop() {
		  stopRequested = true;
		}
	
	@Override
	public void run() {
		Thread thread = new Thread("Cheating Essentials Main Thread");
        thread.setName("Cheating Essentials Main Thread");
        thread.setPriority(2);
        thread.start();
	    while(!stopRequested){
	    	try {
	    		CELogAgent(Strings.THREAD_NAME + " starting in " + Strings.MOD_NAME + " " + Strings.MOD_VERSION + " for " + Strings.MINECRAFT_VERSION);
                CELogAgent(Strings.THREAD_NAME + " Started: "  + thread.toString());
                modInit();
                update.run();
	    		requestThreadStop();
	    		CELogAgent("Initialization Thread was sucefully runned and finished.");
			} catch (Exception ex) {
				CELogAgent(Strings.THREAD_NAME + " was interrupted for some reason! Unreleable results!");
			    CELogAgent("With this, I'm not sure if the mod can be inited, and Minecraft instance will be affected.");
			    ex.printStackTrace( );
	            System.out.println( "Error in CE Thread init: " + ex.toString( ) );
	            ex.printStackTrace( );
			}
	    }
	    }
	
	public void CELogAgent(String log){
		System.out.println("Cheating Essentials - " + log);
	}

}
