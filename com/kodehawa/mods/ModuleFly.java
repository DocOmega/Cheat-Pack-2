package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityPlayerMP;
import net.minecraft.src.Minecraft;
import net.minecraft.src.NetClientHandler;
import net.minecraft.src.Packet13PlayerLookMove;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleFly extends Mod implements Tickable
{
    public NetClientHandler sendQueue;
    public EntityPlayerMP playerEntity;

    public ModuleFly( )
    {
        super(Mods.Fly);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
        if (!CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.capabilities.isFlying)
        {
        	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.capabilities.isFlying = true;
        }

        EntityClientPlayerMP ep = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer;
        ep.sendQueue.addToSendQueue(new Packet13PlayerLookMove(ep.motionX, -999.0D, -999.0D, ep.motionZ,
                ep.rotationYaw, ep.rotationPitch, !ep.onGround));
    }

    @Override
    public void onEnable()
    {
    	CheatingEssentials.getCheatingEssentials().addToTick(this);
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.capabilities.isFlying = true;
    }

    @Override
    public void onDisable()
    {
    	CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.capabilities.isFlying = false;
    }
}
