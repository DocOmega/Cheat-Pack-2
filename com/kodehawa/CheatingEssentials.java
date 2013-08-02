/**
 * This have too many code? It is because YOLO :)
 * At least it's lightweight and pretty stable :D
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
import java.util.Map;
import java.util.Random;

import net.minecraft.src.Gui;
import net.minecraft.src.ILogAgent;
import net.minecraft.src.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.kodehawa.api.CJarLoader;
import com.kodehawa.api.reflection.Reflector;
import com.kodehawa.console.BaseCommand;
import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.core.CheckKey;
import com.kodehawa.core.HTMLParser;
import com.kodehawa.core.KeyManager;
import com.kodehawa.core.Strings;
import com.kodehawa.event.Event;
import com.kodehawa.event.EventHandler;
import com.kodehawa.gui.api.components.Frame;
import com.kodehawa.gui.api.components.ModuleGui;
import com.kodehawa.gui.api.testing.AlertHandler;
import com.kodehawa.mods.F3UtilAdvancedTooltips;
import com.kodehawa.mods.F3UtilMobHitbox;
import com.kodehawa.mods.F3UtilRerenderLoadedChunks;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;
import com.kodehawa.mods.ModuleAutoRespawn;
import com.kodehawa.mods.ModuleFastBreak;
import com.kodehawa.mods.ModuleFastPlace;
import com.kodehawa.mods.ModuleFly;
import com.kodehawa.mods.ModuleFullbright;
import com.kodehawa.mods.ModuleItemTooltips;
import com.kodehawa.mods.ModuleKillAura;
import com.kodehawa.mods.ModuleNoFall;
import com.kodehawa.mods.ModuleNoKnockback;
import com.kodehawa.mods.ModuleSprint;
import com.kodehawa.mods.ModuleTestChestFinder;
import com.kodehawa.mods.ModuleWaterwalk;
import com.kodehawa.mods.ModuleXray;
import com.kodehawa.module.Module;
import com.kodehawa.module.ModuleManager;
import com.kodehawa.players.FrenemyManager;
import com.kodehawa.util.CModTicks;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Utils;
import com.kodehawa.util.wrapper.Wrapper;

public final class CheatingEssentials extends Thread implements CModTicks {
	

	/**
	 * I know that the code it's a bit ugly. I don't recommend use it for learning propouses.
	 */
	
    public static CheatingEssentials modinstance;
	public Minecraft minecraft;
	public static Wrapper getModWrapper = new Wrapper();
	public File mainDir;
	public CheckKey KeyBinding;
	private Event theInternalEvents;
	public ModuleGui MainGui;
	private Reflector mainModReflector;
	private Utils modUtils;
	public KeyManager modKeyManager;
	public static ModManager mainModLoader;
	public static ModuleManager mainModules;
	public static Module module;
	private static EventHandler eventHandler;
	private File guiStatesFile;
    private File FileWritter;
	private Tickable modInternalTicks;
	public HashMap<Mod, Integer> keys;
	public ArrayList<Tickable> modInternalTicksArray = new ArrayList<Tickable>();
    public ArrayList<Mod> mods = new ArrayList<Mod>();
    public static ArrayList<String> enabledMods = new ArrayList<String>();
    public FrenemyManager theFriendManager;
	public ConsoleHelper theConsoleHelper;
	public CJarLoader externalLoader;
	private AlertHandler alertManager;
	private int tick = 0;
	private static boolean outdatedAlert = false;
	private BaseCommand consoleBase;
	private long now;
	private long then;
    private static ModuleXray xray;
    private static ModuleFly fly;
    private static ModuleTestChestFinder cesp;
    private static ModuleFullbright fullbright;
    private static ModuleKillAura killa;
    private static ModuleNoFall nofall;
    private static ModuleFastPlace fp;
    private static ModuleWaterwalk waterw;
    private static ModuleFastBreak fb;
    private static ModuleNoKnockback nk;
	
	
    /**
     * Constructor
     * @param mc
     * @param reflection
     */
	
	public CheatingEssentials(Minecraft mc, Reflector reflection) {
		//TODO: Constructor
		minecraft = mc;
		mainModReflector = reflection;
		KeyBinding = new CheckKey(mc);
		run();
	}
	
	/**
	 * Used for make to the console print debug messages.
	 * Set to true for view a lot of log messages :)
	 */
	public static boolean debugMode = false;

	
	
	/**
	 * Init the mod
	 */
	
	private void modInit() {
		
		//TODO: Mod initialization.
		mainDir = new File(getMinecraftInstance().mcDataDir, "/config/Cheating Essentials/CEXrayBlockList.txt");
		modinstance = this;
		CELogAgent.logInfo("OpenGL: " + GL11.glGetString(GL11.GL_VERSION));
        CELogAgent.logInfo(Strings.MOD_NAME + " " + Strings.MOD_VERSION + " " + "starting in" + " " + Strings.MINECRAFT_VERSION + "...");
        if(debugMode){
        	System.out.println("You only can view this messages if you're viewing the code, using it, or anything else - Debugging and misc. system info.");
        	CELogAgent.logInfo("Instance Started in (Miliseconds): " + System.currentTimeMillis() /1000);
        	System.out.println("I hate the Integrated Server! :(");
        }
        externalLoader = new CJarLoader();
		mainModLoader = new ModManager(this);
		this.addModulestoArray();
		mainModules = new ModuleManager();
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
		xray = new ModuleXray();
        fly = new ModuleFly();
		cesp = new ModuleTestChestFinder();
		fullbright = new ModuleFullbright();
		killa = new ModuleKillAura();
		fp = new ModuleFastPlace();
		fb = new ModuleFastBreak();
		waterw = new ModuleWaterwalk();
		nk = new ModuleNoKnockback();
		nofall = new ModuleNoFall();

        if(!mainDir.exists()){
        saveXrayList();
        }
        loadXrayList();
        for (Mod m : mainModLoader.mods)
        {
            keys.put(m, m.keyBind);
        }
        Random rand = new Random();
        //Easy, lol.
        CELogAgent.logInfo("CE Startup ID: " + rand.nextInt(90000) );
        CELogAgent.logInfo(Strings.MOD_NAME + " " + Strings.MOD_VERSION + " started succefully in " + Strings.MINECRAFT_VERSION);
	    
		
	}
	
	/**
	 * Register modules. 
	 * - I'm the only that think that this it's like Item / Block vanilla registering? :)
	 */
	
	public void addModulestoArray(){
		try{
		CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleFastPlace( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleFullbright( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleWaterwalk( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleXray( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleAutoRespawn( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleTestChestFinder( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleFastBreak( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addPMod(new ModuleFly( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addPMod(new ModuleKillAura( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addPMod(new ModuleNoFall( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addPMod(new ModuleSprint( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addPMod(new ModuleNoKnockback( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addPMod(new ModuleItemTooltips( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addUtils(new F3UtilRerenderLoadedChunks( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addUtils(new F3UtilMobHitbox( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addUtils(new F3UtilAdvancedTooltips( ));

        for (Mod m : CheatingEssentials.getCheatingEssentials().mainModLoader.worldMods)
        {
            mods.add(m);
            CELogAgent.logInfo("Module Loaded: " + m);
        }

        for (Mod m : CheatingEssentials.getCheatingEssentials().mainModLoader.playerMods)
        {
            mods.add(m);
            CELogAgent.logInfo("Module Loaded: " + m);
        }
        
        for (Mod m : CheatingEssentials.getCheatingEssentials().mainModLoader.f3utils)
        {
            mods.add(m);
            CELogAgent.logInfo("Module Loaded: " + m);
        }
        
		}
		catch(Exception ex){
			CELogAgent.logSevere("Can't load basic modules at all or some modules can't be loaded. This will be bad, but the mod it still working.");
			CELogAgent.logSevere("Report it in MCF thread. Good luck.");
			for (Mod m : CheatingEssentials.getCheatingEssentials().mainModLoader.worldMods)
	        {
	            CELogAgent.logInfo("Can't load module: " + m + " " + ex);
	        }

	        for (Mod m : CheatingEssentials.getCheatingEssentials().mainModLoader.playerMods)
	        {
	            CELogAgent.logInfo("Can't load module: " + m + " " + ex);
	        }
	        
	        for (Mod m : CheatingEssentials.getCheatingEssentials().mainModLoader.f3utils)
	        {
	            CELogAgent.logInfo("Can't load module: " + m + " " + ex);
	        }
			ex.printStackTrace();
		}
	}

	
	/**
	 * Get the main mod instance
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
	 * Gets the mod version as a string
	 */
	
	public static String getModVersion(){
		return "3.0.0 A1";	
		}
	
	/**
	 * Tick the modules
	 */

	@Override
	public void modTicks() {
		for(Tickable tickable : modInternalTicksArray){
			tickable.tick();
		}
		
	}
	
	/**
	 * Get mod utilies
	 */

	@Override
	public Utils getUtils() {
		return modUtils;
	}
	
	/**
	 * Get the Minecraft instance
	 */
	
	public static Minecraft getMinecraftInstance(){
		return Minecraft.getMinecraft();
	}

	/**
	 * Handle GUI key press.
	 */
	

	@Override
	public void handleKeyPress() {
		// TODO Keys
	
		if(KeyBinding.checkKey(Keyboard.KEY_G)){
			 minecraft.displayGuiScreen(MainGui);
		
		 for (Map.Entry<Mod, Integer> e : keys.entrySet())
         {
             if (KeyBinding.checkKey(e.getKey().keyBind))
             {
                 e.getKey().toggle();
             }
			 }
		}
	}
	
   
	
	private void handleModuleKeybinding(){
		
		/**
		 * Hardcoded. Only thinked for be used once.
		 */
		try{
            if (KeyBinding.checkKey(Keyboard.KEY_X))
            {
                xray.toggle();
            }
            if (KeyBinding.checkKey(Keyboard.KEY_R))
            {
                fly.toggle();
            }
            if (KeyBinding.checkKey(Keyboard.KEY_N))
            {
                cesp.toggle();
            }
            if (KeyBinding.checkKey(Keyboard.KEY_F))
            {
                fullbright.toggle();
            }
            if (KeyBinding.checkKey(Keyboard.KEY_Y))
            {
                killa.toggle();
            }
            if (KeyBinding.checkKey(Keyboard.KEY_V))
            {
                nofall.toggle();
            }
            if (KeyBinding.checkKey(Keyboard.KEY_M))
            {
                fp.toggle();
            }
            if (KeyBinding.checkKey(Keyboard.KEY_B))
            {
                fb.toggle();
            }
            if (KeyBinding.checkKey(Keyboard.KEY_J))
            {
                waterw.toggle();
            }
            if (KeyBinding.checkKey(Keyboard.KEY_L))
            {
                nk.toggle();
            }
		}
			catch(Exception var23){
				var23.printStackTrace();
			}
	}
	
	/**
	 * Now with a configurable Xray :D
	 * Write the entire file again when a block it's changed in-game
	 */
	
    public void saveXrayList( ) {
        try {
        		CELogAgent.logInfo("Writting X-Ray block list configuration file...");
            File file = new File( mainDir, "" );
            BufferedWriter bufferedwritter = new BufferedWriter( new FileWriter( file ) );
            for( int i : ModuleXray.xrayBlocks ) {
            	bufferedwritter.write( i + "\r\n" );
            }
            bufferedwritter.close( );
        	
        } catch( Exception e ) {
        	CELogAgent.logSevere("Can't write X-Ray configuration file! Custom blocks for X-Ray will be disabled!");
        }
    }
    
    /**
     * Load the integers of the Xray list. Only readed once
     * 
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
            if(debugMode){
            CELogAgent.logInfo("Debug -" + " X-Ray block list array size: " + ModuleXray.xrayBlocks.size() + " lines.");
            }
        } catch( Exception e ) {
            CELogAgent.logSevere("Can't load X-Ray list. Unreliable results!");
            saveXrayList( );
        }

    }
    
    
	/**
	 * Update "Active Cheats" frame.
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
	 * Tick a mod
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
	 */
	
	@Override
	public void tick() {
		modTicks();
        updateArray();
        updatePinnedFrames();
        handleKeyPress();
        handleModuleKeybinding();
	}
	
	/**
	 * Update pinnable frames.
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
	 * Reload the mods.. *Buggy?
	 */
	
	public void reload()
    {
        for (Mod m : mainModLoader.mods)
        {
            m.turnOff();
        }

        modInit();
    }

        
	/**
	 * Throws a chat message in world start... I think.
	 * @param mc
	 * @param f
	 */
	public void onTickInGame(Minecraft mc, float f)
	{
	           tick++;
	           if(tick == 5 && !mc.theWorld.isRemote)
	           {
	                mc.thePlayer.addChatMessage(ChatColour.DARK_GRAY + "Cheating Essentials version 2.9.4 running in Minecraft version 1.6.2");
	           }
	}
	
	private volatile boolean stopRequested = false;
	
	@Override
	public void run() {
		Thread thread = new Thread("Cheating Essentials Main Thread");
        thread.setName("Cheating Essentials Main Thread");
        thread.setPriority(1);
        thread.start();
        
        if(debugMode){
		CELogAgent.logInfo("Thread priority: " + thread.getPriority() );
		CELogAgent.logInfo("Thread State (true / false): " + thread.isAlive());
		CELogAgent.logInfo("Thread ID: " + thread.getId());
        CELogAgent.logInfo("Thread Hashcode: " + thread.hashCode());
        }
	    while(!stopRequested){
	    	try {
	    		CELogAgent.logInfo(Strings.THREAD_NAME + " starting in " + Strings.MOD_NAME + " " + Strings.MOD_VERSION + " for " + Strings.MINECRAFT_VERSION);
                CELogAgent.logInfo(Strings.THREAD_NAME + " Started: "  + thread.toString());
                modInit();
                //update();
	    		requestStop();
	    		CELogAgent.logInfo("Initialization Thread was sucefully runned and finished.");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				CELogAgent.logSevere(Strings.THREAD_NAME + " Thread was interrupted for some reason! Unreleable results!");
			    CELogAgent.logSevere("With this, I'm not sure if the mod can be inited, and Minecraft instance will be affected.");
			    CELogAgent.logSevere("Shutting down Minecraft...");
			    System.exit(1799);
			}
	    	
	    }
	}
	
	public void requestStop() {
		  stopRequested = true;
		}

    public boolean update( ) {
        this.outdatedAlert = false;
        try {
        	CELogAgent.logInfo( "Checking for a Cheating Essentials update..." );
            String ver;
            Strings.VERSION_FOUND = ver = HTMLParser.getStringFromRemoteServer( "http://kodehawa.260mb.net/updates.txt" );
            if( ver.equals( Strings.MOD_VERSION ) ) {
                // Shouldn't hafta do anything, because we're up to date.
                // Returns false, because no need to update.
               CELogAgent.logInfo( "No new updates has been found!" );
                return false;
            } else if( !ver.equals( Strings.MOD_VERSION ) ) {
                if( Integer.parseInt( ver.replaceAll( "\\D+", "" ) ) > Integer.parseInt( Strings.MOD_VERSION.replaceAll(
                        "\\D+", "" ) ) ) {
                    // Obviously, we gotta update!
                	CELogAgent.logInfo( "Update found: " + ver );
                	CELogAgent.logInfo( "Current version: " + Strings.MOD_VERSION );
                    return true;
                } else {
                    if( !ver.replaceAll( "[^A-Za-z]", "" ).equals( Strings.MOD_VERSION.replaceAll( "[^A-Za-z]", "" ) ) ) {
                    	CELogAgent.logInfo( "Update found: " + ver );
                    	CELogAgent.logInfo( "Current version: " + Strings.MOD_VERSION );
                        return true;
                    } else {
                    	//We don't want to fave older versions on main servers. Right?
                    	CELogAgent.logInfo( "No new updates found! (Older version is on the server, report this to the in the Minecraft Forum post!)" );
                        this.outdatedAlert = true;
                        return false;
                    }
                }
            } else {
                throw new NullPointerException( "No update info found!" );
            }
        } catch( NullPointerException e ) {
        	CELogAgent.logSevere( "Issue getting update information" );
            e.printStackTrace( );
            return false;
        }
    }
	
	public ILogAgent CELogAgent = new net.minecraft.src.LogAgent("Cheating Essentials", " [Cheating Essentials]", (new File(FileWritter, "CheatingEssentials.log")).getAbsolutePath());


}
