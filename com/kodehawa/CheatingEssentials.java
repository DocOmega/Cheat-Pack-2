package com.kodehawa;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.src.Gui;
import net.minecraft.src.ILogAgent;
import net.minecraft.src.Minecraft;

import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;

import com.kodehawa.api.reflection.Reflector;
import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.core.CheckKey;
import com.kodehawa.core.HTMLParser;
import com.kodehawa.core.KeyManager;
import com.kodehawa.event.Event;
import com.kodehawa.event.EventHandler;
import com.kodehawa.gui.api.components.Frame;
import com.kodehawa.gui.api.components.Item;
import com.kodehawa.gui.api.components.ModuleGui;
import com.kodehawa.gui.api.font.CustomFont;
import com.kodehawa.gui.api.testing.AlertHandler;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;
import com.kodehawa.mods.ModuleFly;
import com.kodehawa.mods.ModuleXray;
import com.kodehawa.players.FrenemyManager;
import com.kodehawa.util.CModTicks;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.GuiHelper;
import com.kodehawa.util.ModProp;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Utils;
import com.kodehawa.util.wrapper.Wrapper;

public final class CheatingEssentials implements CModTicks, GuiHelper, Runnable {
	
	/**
	 * Hi! I'm a random comment.
	 */
	
	/**
	 * Hola!, soy un comentario aleatorio en Español... Por que no?
	 */
	
    public static CheatingEssentials modinstance;
	public static Minecraft minecraft;
	private static Minecraft mc;
	public static Wrapper getModWrapper = new Wrapper();
	public CheckKey KeyBinding;
	private Event theInternalEvents;
	public EventHandler theEventHandler;
	public ModuleGui MainGui;
	private Reflector mainModReflector;
	private Utils modUtils;
	public KeyManager modKeyManager;
	public static ModManager mainModLoader;
	private Item item;
	public File guiStatesFile;
	private Tickable modInternalTicks;
	public HashMap<Mod, Integer> keys;
	public ArrayList<Tickable> modInternalTicksArray = new ArrayList<Tickable>();
    public ArrayList<Mod> mods = new ArrayList<Mod>();
    public static ArrayList<String> enabledMods = new ArrayList<String>();
	private ConsoleHelper theConsoleHelper;
	public AlertHandler alertManager;
	private int tick = 0;
	public static boolean outdatedAlert = false;
	public boolean debugMode = false;
	public FrenemyManager theFriendManager;
	private File FileWritter;
	public static CustomFont guiFont;
	private ModuleXray xray;
	private ModuleFly fly;
	private long now;
	private long then;
	
    public static String versionFound = "";
    private String modName = "Cheating Essentials";
	public static String version = "2.9.41";
	private String minecraftVersion = "Minecraft version 1.6.2";
	private String modules = "Modules Loaded:" +
    		" X-Ray," +
    		" Fly," +
    		" Killaura," +
    		" Fastplace," +
    		" Autorespawn," +
    		" Noknockback," +
    		" Waterwalk," +
    		" Fullbirght," +
    		" NoFall," +
    		" Chest Finder," +
    		" Sprint.";
    private String vanillaCompatibility = "Optifine," +
    		" ModLoader," +
    		" OptiLeaves," +
    		" Forge.";
	
    /**
     * Constructor
     * @param mc
     * @param reflection
     */
	
	public CheatingEssentials(Minecraft mc, Reflector reflection) {
		minecraft = mc;
		mainModReflector = reflection;
		KeyBinding = new CheckKey(mc);
		modInit();
	}
	
	/**
	 * Get mod instance.
	 * @return
	 */
	
	public static CheatingEssentials getModInstance(){
		return modinstance;
	}
	
	/**
	 * Get Wrapper instance.
	 * @return
	 */
	
	public Wrapper getModWrapper(){
		return getModWrapper;
	}
	
	/**
	 * Init the mod
	 */
	
	private void modInit() {
		
		update();
		guiStatesFile = new File(minecraft.mcDataDir, "/Cheating Essentials/gui.coords");
        CELogAgent.logInfo(modName + " " + version + " " + "starting in" + " " + minecraftVersion + "...");
        if(debugMode){
        	CELogAgent.logInfo(modules);
        	CELogAgent.logInfo("Instance Started in (Miliseconds): " + System.currentTimeMillis());
        	CELogAgent.logInfo("LWJGL version: " + Sys.getVersion());
        	System.out.println("I hate the Integrated Server! :(");
        }
		run();
		mainModLoader = new ModManager(this);
		modUtils = new Utils(minecraft);
		MainGui = new ModuleGui();
		modinstance = this;
		//mainTranslationWritter = new TranslationWritter();
		minecraft = Minecraft.getMinecraft();
        now = System.currentTimeMillis();
        then = now + 250;
		modKeyManager = new KeyManager();
		theEventHandler = new EventHandler();
		theConsoleHelper = new ConsoleHelper();
        keys = new HashMap<Mod, Integer>();
        theFriendManager = new FrenemyManager();
        CELogAgent.logInfo("Basic init finished with no errors.");
        for (Mod m : mainModLoader.mods)
        {
            keys.put(m, m.keyBind);
        }
        CELogAgent.logInfo(modName + " " + version + " started succefully in " + minecraftVersion);
	    
		
	}
	
	/**
	 * Tick the modules
	 */

	@Override
	public void modTicks() {
		for(Tickable tickable : modInternalTicksArray){
			tickable.tick();
		}
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Get mod utilies
	 */

	@Override
	public Utils getUtils() {
		// TODO Auto-generated method stub
		return modUtils;
	}

	/**
	 * Handle GUI key press.
	 */
	
	@ModProp
	@Override
	public void handleKeyPress() {
		// TODO Auto-generated method stub
	
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
	 * Update "Active Cheats" frame.
	 */
	
	@Override
	public void updateArray() {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		// After 141414 years, I've fixed that annoying crash :)
		modTicks();

        updateArray();
        handleKeyPress();
	}
	
	/**
	 * Update pinnable frames.
	 */
	// Deprecated only for now
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
	
    public static Object getPrivateValue(Class class1, Object obj, String s) throws IllegalArgumentException, SecurityException, NoSuchFieldException
    {
        try
        {
            Field field = class1.getDeclaredField(s);
            field.setAccessible(true);
            return field.get(obj);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
        	modinstance.CELogAgent.logInfo("Can't assign a public field!");
        }

        return null;
    }

    public static Object getPrivateMethod(Class class1, Object obj, String s) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SecurityException
    {
        try
        {
            Method method = class1.getDeclaredMethod(s);
            method.setAccessible(true);
            return method.invoke(obj);
        }
        catch (IllegalAccessException illegalaccessexception)
        {
        	modinstance.CELogAgent.logInfo("Can't assign a private value to a method");
        }

        return null;
    }
	
	
	@Override
	public void saveGUIToText() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveGUIPositions() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void GuiHelperS() {
		// TODO Auto-generated method stub
		
	}
	
	
	/**
	 * This will be the worst thing that I've ever coded? *And crasheable
	 */
	@SuppressWarnings("resource")
	@ModProp
	@Override
	public void startSaveThread(Item i, int in) {
		// TODO Auto-generated method stub
        
		if((minecraft.currentScreen == MainGui) || (minecraft.currentScreen == (Gui) minecraft.ingameGUI)){
		Thread thread1 = new Thread("Cheating Essentials GUI save thread");
		CELogAgent.logInfo("Saving GUI States....");
		thread1.start();
		try {
		i.x = in;
		i.y = in;
		guiStatesFile.getParentFile().mkdirs();
		guiStatesFile.createNewFile();
        FileWriter fstream;
			fstream = new FileWriter(guiStatesFile);
			BufferedWriter out = new BufferedWriter(fstream);
            out.write(i.x);
    		out.write(i.y);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			CELogAgent.logSevere("For some reason, we can't save your GUI states.");
		}
		Reader();
		
		thread1.getPriority();
	}
	}

	public void Reader()
    {
        int int1;
        String str = "";
        String name;
        InputStream fis = null;
        BufferedReader br;
        String line;
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
		// TODO Auto-generated method stub
		
		Thread thread = new Thread("Cheating Essentials Main Thread");
        thread.setName("Cheating Essentials Main Thread");
        thread.setPriority(3);
        thread.start();
        CELogAgent.logInfo("Main Thread - Starting in " + minecraftVersion + "...");
        if(debugMode){
        //This it's for debug the actual thread state.
		CELogAgent.logInfo("Thread priority: " + thread.getPriority() );
		CELogAgent.logInfo("Thread State (true / false): " + thread.isAlive());
		CELogAgent.logInfo("Thread ID: " + thread.getId());
        CELogAgent.logInfo("Thread Hashcode: " + thread.hashCode());
        //Finished thread debugging.
        }
        CELogAgent.logInfo("Main Thread Started - " + thread.toString());
		
	}
	
    private void log( String s ) {
        log( "Cheating Essentials", s );
    }
    
    /**
     * Logs a message with the given prefix
     * 
     * @param prefix
     * @param s
     */
    public void log( String prefix, String s ) {
        System.out.println( "[" + prefix + "] " + s );
    }
    
    public boolean update( ) {
        this.outdatedAlert = false;
        try {
            log( "Checking for updates..." );
            String ver;
            this.versionFound = ver = HTMLParser.getStringFromRemoteServer( "http://kodehawa.260mb.net/updates.txt" );
            if( ver.equals( this.version ) ) {
                // Shouldn't hafta do anything, because we're up to date.
                // Returns false, because no need to update.
                log( "No new updates has been found!" );
                return false;
            } else if( !ver.equals( this.version ) ) {
                if( Integer.parseInt( ver.replaceAll( "\\D+", "" ) ) > Integer.parseInt( this.version.replaceAll(
                        "\\D+", "" ) ) ) {
                    // Obviously, we gotta update!
                    log( "Update found: " + ver );
                    log( "Current version: " + this.version );
                    return true;
                } else {
                    if( !ver.replaceAll( "[^A-Za-z]", "" ).equals( this.version.replaceAll( "[^A-Za-z]", "" ) ) ) {
                        log( "Update found: " + ver );
                        log( "Current version: " + this.version );
                        return true;
                    } else {
                        log( "No new updates found! (Older version is on the server, report this to the in the Minecraft Forum post!)" );
                        this.outdatedAlert = true;
                        return false;
                    }
                }
            } else {
                throw new NullPointerException( "No update info found!" );
            }
        } catch( NullPointerException e ) {
            log( "Issue getting update information" );
            e.printStackTrace( );
            return false;
        }
    }
	
	public final ILogAgent CELogAgent = new net.minecraft.src.LogAgent("Cheating Essentials", " [Cheating Essentials]", (new File(FileWritter, "CheatingEssentials.log")).getAbsolutePath());


}
