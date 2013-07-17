package com.kodehawa;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import net.minecraft.src.Gui;
import net.minecraft.src.ILogAgent;
import net.minecraft.src.Minecraft;

import org.lwjgl.input.Keyboard;

import com.kodehawa.api.reflection.Reflector;
import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.core.CheckKey;
import com.kodehawa.core.KeyManager;
import com.kodehawa.core.TranslationWritter;
import com.kodehawa.event.Event;
import com.kodehawa.event.EventHandler;
import com.kodehawa.gui.api.components.Frame;
import com.kodehawa.gui.api.components.ModuleGui;
import com.kodehawa.gui.api.font.CustomFont;
import com.kodehawa.gui.api.testing.AlertHandler;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;
import com.kodehawa.players.FrenemyManager;
import com.kodehawa.util.CModTicks;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Utils;
import com.kodehawa.util.wrapper.Wrapper;

public final class CheatingEssentials implements CModTicks {
	
    
    public static CheatingEssentials modinstance;
	public static Minecraft minecraft;
	private static Minecraft mc;
	public static Wrapper getModWrapper;
	public CheckKey KeyBinding;
	private static Event theInternalEvents;
	public static EventHandler theEventHandler;
	public ModuleGui MainGui;
	private static Reflector mainModReflector;
	private Utils modUtils;
	public KeyManager modKeyManager;
	public static ModManager mainModLoader;
	private TranslationWritter mainTranslationWritter;
	private Tickable modInternalTicks;
	public HashMap<Mod, Integer> keys;
	public ArrayList<Tickable> modInternalTicksArray = new ArrayList<Tickable>();
    public ArrayList<Mod> mods = new ArrayList<Mod>();
    public static ArrayList<String> enabledMods = new ArrayList<String>();
	private ConsoleHelper theConsoleHelper;
	public AlertHandler amanager;
	public FrenemyManager theFriendManager;
	private File FileWritter;
	public static CustomFont guiFont;
	private long now;
	private long then;
	
	
	private String modName = "Cheating Essentials";
	private String modVersion = "2.9.3.195";
	private String minecraftVersion = "Minecraft 1.6.2";
	private String modsLoaded = "Modules:" +
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
	
	
	public CheatingEssentials(Minecraft mc, Reflector reflection) {
		minecraft = mc;
		mainModReflector = reflection;
		KeyBinding = new CheckKey(mc);
		modInit();
	}
	
	public static CheatingEssentials getModInstance(){
		return modinstance;
	}
	
	private void modInit() {
		
		CELogAgent.logInfo(modName + " " + modVersion + " " + "started in" + " " + minecraftVersion);

		mainModLoader = new ModManager(this);
		modUtils = new Utils(minecraft);
		MainGui = new ModuleGui();
		modinstance = this;
		mainTranslationWritter = new TranslationWritter();
		minecraft = Minecraft.getMinecraft();
        now = System.currentTimeMillis();
        then = now + 250;
		modKeyManager = new KeyManager();
		theEventHandler = new EventHandler();
		theConsoleHelper = new ConsoleHelper();
        keys = new HashMap<Mod, Integer>();
        theFriendManager = new FrenemyManager();
        for (Mod m : mainModLoader.mods)
        {
            keys.put(m, m.keyBind);
        }
		
        CELogAgent.logInfo(modName + " " + "is compatible with the following vanilla mods: " + vanillaCompatibility);
        CELogAgent.logInfo(modName + " " + modVersion + " started succefully in " + minecraftVersion);
	
		
	}

	@Override
	public void modTicks() {
		for(Tickable tickable : modInternalTicksArray){
			tickable.tick();
		}
		// TODO Auto-generated method stub
		
	}

	@Override
	public Utils getUtils() {
		// TODO Auto-generated method stub
		return modUtils;
	}

	@Override
	public void handleKeyPress() {
		// TODO Auto-generated method stub
		
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
	

	@Override
	public void addToTick(Tickable tickable) {
		// TODO Auto-generated method stub
		 if (!modInternalTicksArray.contains(tickable))
	        {
			 modInternalTicksArray.add(tickable);
	        }
		
	}

	@Override
	public void removeFromCurrentTick(Tickable tickable) {
		// TODO Auto-generated method stub
		 if (modInternalTicksArray.contains(tickable))
	        {
			 modInternalTicksArray.remove(tickable);
	        }
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		modTicks();

        updateArray();
        handleKeyPress();
	}
	
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
	
	public final ILogAgent CELogAgent = new net.minecraft.src.LogAgent("Cheating Essentials", " [Cheating Essentials]", (new File(FileWritter, "CheatingEssentials.log")).getAbsolutePath());

	
}
