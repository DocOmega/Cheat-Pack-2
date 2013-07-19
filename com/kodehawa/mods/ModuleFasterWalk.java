package com.kodehawa.mods;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.ChatColour;
import com.kodehawa.util.Tickable;

public class ModuleFasterWalk extends Mod implements Tickable
{
    public ModuleFasterWalk(CheatingEssentials c, Minecraft m)
    {
        super(Mods.Fasterwalk);
        cb = c;
        mc = m;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {
        mc.thePlayer.capabilities.setPlayerWalkSpeed(0.05F);
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
        mc.thePlayer.capabilities.setPlayerWalkSpeed(0.1F);
        
    }

    public Minecraft mc;
    public CheatingEssentials cb;
}
