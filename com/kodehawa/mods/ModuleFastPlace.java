package com.kodehawa.mods;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleFastPlace extends Mod implements Tickable
{

    public ModuleFastPlace( )
    {
        super(Mods.Fastplace);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
    	 
    	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().rightClickDelayTimer = 0;
    }

    @Override
    public void onEnable()
    {
    	CheatingEssentials.getCheatingEssentials().addToTick(this);
        
    }

    @Override
    public void onDisable()
    {
    	CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
    }
}
