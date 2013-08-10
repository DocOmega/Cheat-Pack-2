package com.kodehawa.console;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;

public class List implements BaseCommand
{
    @Override
    public void onRun(String[ ] cmd)
    {
        // TODO Auto-generated method stub
    }

    @Override
    public String getName()
    {
        // TODO Auto-generated method stub
        return "list";
    }

    @Override
    public String showHelp()
    {
        // TODO Auto-generated method stub
        return new String("");
    }

    @Override
    public String output()
    {
        // TODO Auto-generated method stub
        String s = "";

        for (Mod m : ModManager.getInstance().mods)
        {
            s += m.name + " - " + Keyboard.getKeyName(m.keyBind) + ", ";
        }

        return s;
    }
}
