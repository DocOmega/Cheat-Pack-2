package com.kodehawa.gui;

import java.util.HashMap;

import net.minecraft.src.Minecraft;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.core.CheckKey;
import com.kodehawa.mods.Mod;

public class CGuiIngame
{
    public static int tick = 0;
    public static CheatingEssentials cheatbase;
    private Minecraft mc;
    private boolean keyStates[] = new boolean[ 256 ];
    private CheckKey ck;
    public HashMap<Mod, Integer> keyShit;

    public CGuiIngame(Minecraft par1Minecraft)
    {
        
        
        // TODO Auto-generated constructor stub
    }


    private boolean checkKey(int i)
    {
        if (Keyboard.isKeyDown(i) != keyStates [ i ])
        {
            return keyStates [ i ] = !keyStates [ i ];
        }
        else
        {
            return false;
        }
    }
}
