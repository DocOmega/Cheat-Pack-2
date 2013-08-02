/**
 * I've does my most because I can  *Portal reference :)
 */

package com.kodehawa.mods;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.ChatColour;

public abstract class Mod
{
    public Mod(Mods mod)
    {
        name = mod.getName();
    }

    public void turnOn()
    {
        active = true;
        onEnable();
    }

    public void turnOff()
    {
        active = false;
        onDisable();
    }

    public void toggle()
    {
        active = !active;

        if (active)
        {
            onEnable();
            CheatingEssentials.getCheatingEssentials().enabledMods.add(name);
        }
        else
        {
            onDisable();
            CheatingEssentials.getCheatingEssentials().enabledMods.remove(name);

        }
    }

    public boolean isActive()
    {
        return active;
    }

    public String getActive()
    {
        if (active)
        {
            return ChatColour.WHITE + name + ChatColour.GREEN + " Active";
        }
        else
        {
            return ChatColour.WHITE + name + ChatColour.RED + " Inactive";
        }
    }

    public abstract void onEnable();

    public abstract void onDisable();

    public String name;
    //Make booleans! Ready go! *Ehmm... you forgot the static okay no xD
    private boolean active;
    public int keyBind;
}
