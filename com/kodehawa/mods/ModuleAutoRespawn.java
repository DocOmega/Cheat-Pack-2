package com.kodehawa.mods;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleAutoRespawn extends Mod implements Tickable
{
    private CheatingEssentials cb;
    private Minecraft mc;

    public ModuleAutoRespawn(CheatingEssentials c, Minecraft m)
    {
        super(Mods.Autorespawn);
        // TODO Auto-generated constructor stub
        cb = c;
        mc = m;
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
        if (mc.thePlayer.isDead)
        {
        	mc.thePlayer.respawnPlayer();
        }
    }

    @Override
    public void onEnable()
    {
        // TODO Auto-generated method stub
        cb.addToTick(this);
        //cb.getUtils().addChatMessage(getActive());
        //cb.getUtils().addChatMessage("I don't like the holy death screen.");
    }

    @Override
    public void onDisable()
    {
        // TODO Auto-generated method stub
        cb.removeFromCurrentTick(this);
        //cb.getUtils().addChatMessage(getActive());
    }
}
