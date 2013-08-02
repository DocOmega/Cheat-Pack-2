
package com.kodehawa.mods;

import java.util.ArrayList;

import com.kodehawa.CheatingEssentials;

public class ModManager
{
    public ArrayList<Mod> mods;
    public ArrayList<Mod> worldMods;
    public ArrayList<Mod> playerMods;
    public ArrayList<Mod> f3utils;

    public ModManager(CheatingEssentials c)
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
}
