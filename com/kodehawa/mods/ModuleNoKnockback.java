package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleNoKnockback extends Mod implements Tickable
{
 

    public ModuleNoKnockback()
    {
        super(Mods.NoKnockback);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
        EntityClientPlayerMP player = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer;

        if (player.hurtTime > 0 && player.hurtResistantTime > 0)
        {
            player.motionX = 0;
            player.motionZ = 0;
        }
    }

    @Override
    public void onEnable()
    {
        // TODO Auto-generated method stub
    	CheatingEssentials.getCheatingEssentials().addToTick(this);
    }

    @Override
    public void onDisable()
    {
        // TODO Auto-generated method stub
    	CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
}
}
