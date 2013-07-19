package com.kodehawa.mods;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleFastPlace extends Mod implements Tickable
{
	CheatingEssentials cb;
    Minecraft minecraft;

    public ModuleFastPlace(CheatingEssentials ce, Minecraft mc)
    {
        super(Mods.Fastplace);
        // TODO Auto-generated constructor stub
        this.cb = ce;
        this.minecraft = mc;
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
    	 
    	minecraft.rightClickDelayTimer = 0;
    }

    @Override
    public void onEnable()
    {
        cb.addToTick(this);
        
    }

    @Override
    public void onDisable()
    {
        cb.removeFromCurrentTick(this);
    }
}
