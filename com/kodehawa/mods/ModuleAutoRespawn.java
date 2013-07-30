package com.kodehawa.mods;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleAutoRespawn extends Mod implements Tickable
{
   

    public ModuleAutoRespawn()
    {
        super(Mods.Autorespawn);
        // TODO Auto-generated constructor stub
        
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
        if (CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.isDead)
        {
        	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.respawnPlayer();
        }
    }

    @Override
    public void onEnable()
    {
        // TODO Auto-generated method stub
    	CheatingEssentials.getCheatingEssentials().addToTick(this);
        //cb.getUtils().addChatMessage(getActive());
        //cb.getUtils().addChatMessage("I don't like the holy death screen.");
    }

    @Override
    public void onDisable()
    {
        // TODO Auto-generated method stub
    	CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
        //cb.getUtils().addChatMessage(getActive());
    }
}
