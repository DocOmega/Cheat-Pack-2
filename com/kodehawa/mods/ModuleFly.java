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
    private final CheatingEssentials cheatbase;
    private final Minecraft mc;
    public NetClientHandler sendQueue;
    public EntityPlayerMP playerEntity;

    public ModuleFly(CheatingEssentials c, Minecraft m)
    {
        super(Mods.Fly);
        cheatbase = c;
        mc = m;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
        if (!mc.thePlayer.capabilities.isFlying)
        {
        	mc.thePlayer.capabilities.isFlying = true;
        }

        EntityClientPlayerMP ep = mc.thePlayer;
        ep.sendQueue.addToSendQueue(new Packet13PlayerLookMove(ep.motionX, -999.0D, -999.0D, ep.motionZ,
                ep.rotationYaw, ep.rotationPitch, !ep.onGround));
    }

    @Override
    public void onEnable()
    {
        cheatbase.addToTick(this);
        mc.thePlayer.capabilities.isFlying = true;
    }

    @Override
    public void onDisable()
    {
        cheatbase.removeFromCurrentTick(this);
        mc.thePlayer.capabilities.isFlying = false;
    }
}
