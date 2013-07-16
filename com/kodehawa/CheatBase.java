/**
 * Base Classes Modified:
 *
 * @Block.java
 * @Minecraft.java
 * @EntityPlayer.java
 * @TileEntityChestRenderer.java
 */

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

import com.kodehawa.core.CheckKey;
import com.kodehawa.core.KeyManager;
import com.kodehawa.core.TranslationWritter;
import com.kodehawa.event.EventHandler;
import com.kodehawa.gui.api.components.Frame;
import com.kodehawa.gui.api.components.ModuleGui;
import com.kodehawa.gui.api.font.CustomFont;
import com.kodehawa.gui.api.testing.AlertHandler;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;
import com.kodehawa.players.FrenemyManager;
import com.kodehawa.util.Console;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Utils;
import com.kodehawa.util.WaypointManager;
import com.kodehawa.util.wrapper.Wrapper;

public class CheatBase
{
    /**
     *  Throws the instance of the mod. Prevents NullPointerException's.
     */

    public static CheatBase instance;

    /**
     * Minecraft reference.
     */

    private static Minecraft mc;

    /**
     * Get wrapper for some methods and reflections.
     */

    public final static Wrapper getWrapper = new Wrapper();


    /**
     * Field for MC 1.6
     * Public because... BECAUSE I CAN :)
     */

    public static File field_CP2_ol;

    /**
     * @NOTUSED Custom Font renderer.
     */

    public static CustomFont guiFont;


    /**
     * Get variables for console printing.
     */

    private String modName = "Cheating Essentials";
    private String mcversion = "Minecraft 1.6.2";
    private String modversion = "version 2.9.1.850";
    private String random = "Kodehawa's";
    private String modsloaded = "Modules:" +
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
    private String vcompatibility = "Optifine," +
    		" ModLoader," +
    		" OptiLeaves," +
    		" Forge.";
    		

    
    /**
     * I don't remember for what it's this. I'm too lazy to remember it.
     */

    long now;
    long then;

    /**
     * A thing for register things, yeah!
     */

    private static EventHandler eventHandler;

    /**
     * Main method.
     */

    public CheatBase(Minecraft mc)
    {
        instance = this;
        minecraft = mc;
        ck = new CheckKey(mc);
        init();
    }
    public static ArrayList<String> enabledMods = new ArrayList<String>();

    /**
     * Init the main mod class (com.kodehawa.CheatBase.java and the "ck6" or CheatPack *Corebase*)
     */

    public void init()
    {
    	System.out.println("");
    	System.out.println("");
    	CELogAgent.logInfo(modName + " " + modversion + " " + "started in" + " " + mcversion + " ");        
        utils = new Utils(minecraft);
        mc = Minecraft.getMinecraft();
        now = System.currentTimeMillis();
        then = now + 250;
        keyShit = new HashMap<Mod, Integer>();
        mmanager = new ModManager(this);
        modgui = new ModuleGui();
        kmanager = new KeyManager();
        translations = new TranslationWritter();
        femanager = new FrenemyManager();
        console = new Console();
        CELogAgent.logInfo(modsloaded);
        CELogAgent.logInfo("Cheating Essentials its compatible with the following vanilla mods: " + vcompatibility);
        System.out.println("");
        guiFont = new CustomFont(minecraft, "Bauhaus", 20);

        for (Mod m : mmanager.mods)
        {
            keyShit.put(m, m.keyBind);
        }
        System.out.println("");
    }

    /**
     * Get a Callable reflector for Corebase (com.kodehawa.Cheatxx) xx = Anything
     * @Reflection Main
     */

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
        }

        return null;
    }

    /**
     * Get the mod main instance
     * @return instance
     */

    public static CheatBase getInstance()
    {
        return instance;
    }

    /**
     * Reload charged mods
     */

    public void reload()
    {
        for (Mod m : mmanager.mods)
        {
            m.turnOff();
        }

        init();
    }

    /**
     * Main method for GUI and mods ticks. Some mods are bugged.
     */

    public void tick()
    {
        instance.modtick();

        updateArray();
        checkForKeyPress();
        updatePinnedFrames();
    }

    public void modtick(){
    	for (Tickable tick : instance.tickables)
        {
    		//Holy crash.
           tickable.tick();
        }
    }
    
    /**
     * Get the mod utils (com.kodehawa.util)
     * @return utils
     */

    public Utils getUtils()
    {
        return utils;
    }

    /**
     * Update pinned frames.
     */

    public void updatePinnedFrames()
    {
        if ((minecraft.currentScreen == null) || (minecraft.currentScreen == (Gui) minecraft.ingameGUI))
        {
            for (Frame frame : modgui.frames)
            {
                if (frame.pinned && frame.pinnable)
                {
                    frame.update();
                }
            }
        }
    }

    /**
     * For mod ticks and enabling/disabling
     * @param tickable
     */

    public void addToTick(Tickable tickable)
    {
        if (!tickables.contains(tickable))
        {
            tickables.add(tickable);
        }
    }

    public void removeFromTick(Tickable tickable)
    {
        if (tickables.contains(tickable))
        {
            tickables.remove(tickable);
        }
    }

    /**
     * Checkkey... umad?
     */

   public void checkForKeyPress()
    {
        if (ck.checkKey(Keyboard.KEY_G))
        {
            minecraft.displayGuiScreen(modgui);

            

            for (Map.Entry<Mod, Integer> e : keyShit.entrySet())
            {
                if (ck.checkKey(e.getKey().keyBind))
                {
                    e.getKey().toggle();
                }
            }
        }
    }

    public void updateArray()
    {
        for (int i = 0; i < mmanager.mods.size(); i++)
        {
            if (mmanager.mods.get(i).isActive() && !enabledMods.contains(mmanager.mods.get(i).name))
            {
                enabledMods.add(mmanager.mods.get(i).name);
            }
            else if (!mmanager.mods.get(i).isActive())
            {
                enabledMods.remove(mmanager.mods.get(i).name);
            }
        }
    }

    /**
     * Class finder. Pretty simple.
     * @param className
     * @method Reflection
     */

    public boolean classExists(String className)
    {
        try
        {
            Class.forName(className, false, ClassLoader.getSystemClassLoader());
            return true;
        }
        catch (Throwable t)
        {
        }

        return false;
    }
    
    

    /**
     * Log references and printing
     */

    public final ILogAgent CELogAgent = new net.minecraft.src.LogAgent("Cheating Essentials", " [Cheating Essentials]", (new File(field_CP2_ol, "CheatingEssentials.log")).getAbsolutePath());

    /**
     * References.
     */

    public CheckKey ck;
    public Utils utils;
    public static Minecraft minecraft;
    public ArrayList<Tickable> tickables = new ArrayList<Tickable>();
    public ArrayList<Mod> mods = new ArrayList<Mod>();
    public HashMap<Mod, Integer> keyShit;
    public static ModManager mmanager;
    public KeyManager kmanager;
    public TranslationWritter translations;
    public FrenemyManager femanager;
    public ModuleGui modgui;
    public AlertHandler amanager;
    public WaypointManager wmanager;
    public Console console;
    public CheatBase cheatbase;
    private Tickable tickable;
}
