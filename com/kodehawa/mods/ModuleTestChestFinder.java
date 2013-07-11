package com.kodehawa.mods;

import net.minecraft.src.Minecraft;
import net.minecraft.src.RenderGlobal;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleTestChestFinder extends Mod implements Tickable
{
    protected Minecraft minecraft;
    protected CheatBase cb;

    /**
     * I need OpenGL for this. Dammit!
     * @param mod
     * @param c
     * @param m
     */

    public ModuleTestChestFinder(CheatBase c, Minecraft m)
    {
        super(Mods.ChestESP);
        cb = c;
        minecraft = m;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
    }

    /**
     * False=enabled, True=disabled.. FUCK THE LOGIC! :D
     */
    
    @Override
    public void onEnable()
    {
    	Vars.ChestESP = false;
        cb.getUtils().addChatMessage(getActive());
        cb.getUtils().addChatMessage("We need dungeons.. DUNGEONS EVERYWHERE");
        // TODO Auto-generated method stub
    }

    
   
    @Override
    public void onDisable()
    {
        // TODO Auto-generated method stub
    	Vars.ChestESP = true;
        cb.getUtils().addChatMessage(getActive());
    }
}
