
package com.kodehawa.mods;

import java.util.ArrayList;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.console.ConsoleHelper;
import com.kodehawa.event.EventHandler;
import com.kodehawa.util.Utils;

public class ModManager
{
    public static ArrayList<String> enabledMods = new ArrayList<String>();
    public static ArrayList<Mod> mods;
    public static ArrayList<Mod> worldMods;
    public static ArrayList<Mod> playerMods;
    public ArrayList<Mod> f3utils;
    private static volatile ModManager instance;


    public ModManager( )
    {
        mods = new ArrayList<Mod>();
        worldMods = new ArrayList<Mod>();
        playerMods = new ArrayList<Mod>();
        f3utils = new ArrayList<Mod>();

    }

  
    
    public void addMod(Mod m)
    {
        mods.add(m);
    }
    
    public void addUtils(Mod m){
    	f3utils.add(m);
    }

    public void addWMod(Mod m)
    {
        worldMods.add(m);
    }

    public void addPMod(Mod m)
    {
        playerMods.add(m);
    }
    
    public static ArrayList<Mod> getModules(){
    	return mods;
    }

    public static ArrayList<Mod> getPlayerModules(){
    	return playerMods;
    }
    
    public static ArrayList<Mod> getWorldModules(){
    	return worldMods;
    }
    
    public Mod getCheatsByName(String name)
    {
        for (int i = 0; i < mods.size(); i++)
        {
            if (name.equalsIgnoreCase(mods.get(i).name))
            {
                return mods.get(i);
            }
        }

        return null;
    }
    
    public static ModManager getInstance() {
        if (instance == null) {
                instance = new ModManager();
        }
        return instance;
}
}
