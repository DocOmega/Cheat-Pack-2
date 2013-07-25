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

import net.minecraft.src.Gui;
import net.minecraft.src.ILogAgent;
import net.minecraft.src.Minecraft;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;

import com.kodehawa.api.reflection.Reflector;
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
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;
import com.kodehawa.mods.ModuleXray;
import com.kodehawa.module.AntiKnockback;
import com.kodehawa.module.ChestESP;
import com.kodehawa.module.FastPlace;
import com.kodehawa.module.Fly;
import com.kodehawa.module.FullBright;
import com.kodehawa.module.KillAura;
import com.kodehawa.module.Module;
import com.kodehawa.module.ModuleManager;
import com.kodehawa.module.Sprint;
import com.kodehawa.module.Xray;
import com.kodehawa.players.FrenemyManager;
import com.kodehawa.util.CModTicks;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.Console;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Utils;
import com.kodehawa.util.wrapper.Wrapper;

public final class CheatingEssentials implements CModTicks, Runnable {
	

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
	private File xrayBlocks;
	private Tickable modInternalTicks;
	public HashMap<Mod, Integer> keys;
	public ArrayList<Tickable> modInternalTicksArray = new ArrayList<Tickable>();
    public ArrayList<Mod> mods = new ArrayList<Mod>();
    public static ArrayList<String> enabledMods = new ArrayList<String>();
    public FrenemyManager theFriendManager;
	private ConsoleHelper theConsoleHelper;
	private AlertHandler alertManager;
	private int tick = 0;
	private static boolean outdatedAlert = false;
	private long now;
	private long then;
	
	
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
		modInit();
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
		mainDir = new File(getMinecraftInstance().mcDataDir, "/Cheating Essentials/CEXrayBlockList.txt");
		modinstance = this;
		update();
        CELogAgent.logInfo(Strings.MOD_NAME + " " + Strings.MOD_VERSION + " " + "starting in" + " " + Strings.MINECRAFT_VERSION + "...");
        if(debugMode){
        	System.out.println("You only can view this messages if you're viewing the code, using it, or anything else - Debugging and misc. system info.");
        	CELogAgent.logInfo(Strings.MOD_AVALIABLE_MODULES);
        	CELogAgent.logInfo("Instance Started in (Miliseconds): " + System.currentTimeMillis() /1000);
        	//WHY IT IS NOT IMPLEMENTED TO VANILLA MC :/
        	CELogAgent.logInfo("OpenGL version: " + GL11.glGetString(GL11.GL_VERSION));
        	CELogAgent.logInfo("OpenGL vendor: " + GL11.glGetString(GL11.GL_VENDOR));
        	System.out.println("I hate the Integrated Server! :(");
        }
		run();
		mainModLoader = new ModManager(this);
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
        CELogAgent.logInfo("Basic init finished with no errors.");
        saveXrayList();
        loadXrayList();
        for (Mod m : mainModLoader.mods)
        {
            keys.put(m, m.keyBind);
        }
        CELogAgent.logInfo(Strings.MOD_NAME + " " + Strings.MOD_VERSION + " started succefully in " + Strings.MINECRAFT_VERSION);
	    
		
	}
	
	 public void initModules( ) {
	       ModuleManager.addModules( new Console( ), new Fly( ), new FastPlace( ), new Gui( ),
	         new ChestESP( ), new Xray( ), new FullBright( ), new KillAura( ), new Sprint( ), new AntiKnockback( ) );
	        int q = ModuleManager.i;
	        CELogAgent.logInfo( "Modules loaded: " + q + "!" );
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
		return "2.9.5";	
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
			 
			 if(KeyBinding.checkKey(Keyboard.KEY_X)){
				
			
			if(KeyBinding.checkKey(Keyboard.KEY_F)){
				
		
		 for (Map.Entry<Mod, Integer> e : keys.entrySet())
         {
             if (KeyBinding.checkKey(e.getKey().keyBind))
             {
                 e.getKey().toggle();
             }
         }
		}
			 }
		}
	}
	
	/**
	 * Now with a configurable Xray :D
	 */
	
    public void saveXrayList( ) {
        try {
        	if(!mainDir.exists()){
        		CELogAgent.logInfo("Writting X-Ray block list configuration file...");
            File file = new File( mainDir, "" );
            BufferedWriter out = new BufferedWriter( new FileWriter( file ) );
            for( int i : ModuleXray.xrayBlocks ) {
                out.write( i + "\r\n" );
            }
            out.close( );
        	}
        } catch( Exception e ) {
        }
    }
    
    /**
     * Load the integers of the Xray list.
     */
    
    public void loadXrayList( ) {
        try {
        	CELogAgent.logInfo("Reading X-Ray block configuration file...");
            File file = new File( mainDir, "" );
            FileInputStream fstream = new FileInputStream( file.getAbsolutePath( ) );
            DataInputStream in = new DataInputStream( fstream );
            BufferedReader br = new BufferedReader( new InputStreamReader( in ) );
            String line;
            CELogAgent.logInfo("Parsing integers, adding blocks.");
            while( ( line = br.readLine( ) ) != null ) {
                String curLine = line.toLowerCase( ).trim( );
                int id = Integer.parseInt( curLine );
                ModuleXray.xrayBlocks.add( id );
            }
            br.close( );
            CELogAgent.logInfo("X-Ray block list readed and binded");
        } catch( Exception e ) {
            e.printStackTrace( );
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
        handleKeyPress();
	}
	
	/**
	 * Update pinnable frames.
	 */
	@Deprecated
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
	

	@Override
	public void run() {
		Thread thread = new Thread("Cheating Essentials Main Thread");
        thread.setName("Cheating Essentials Main Thread");
        thread.setPriority(3);
        thread.start();
        
        if(debugMode){
		CELogAgent.logInfo("Thread priority: " + thread.getPriority() );
		CELogAgent.logInfo("Thread State (true / false): " + thread.isAlive());
		CELogAgent.logInfo("Thread ID: " + thread.getId());
        CELogAgent.logInfo("Thread Hashcode: " + thread.hashCode());
        }
        CELogAgent.logInfo(Strings.THREAD_NAME + " Started "  + thread.toString());
	
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
