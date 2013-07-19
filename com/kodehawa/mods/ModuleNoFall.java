package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Minecraft;
import net.minecraft.src.Packet13PlayerLookMove;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleNoFall extends Mod implements Tickable
{
    private final CheatingEssentials cheatbase;
    private Minecraft mc;

    public ModuleNoFall(CheatingEssentials cb, Minecraft m)
    {
        super(Mods.Nofall);
        cheatbase = cb;
        mc = m;
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
        EntityClientPlayerMP ep = mc.thePlayer;
        ep.sendQueue.addToSendQueue(new Packet13PlayerLookMove(ep.motionX, -999.0D, -999.0D, ep.motionZ,
                ep.rotationYaw, ep.rotationPitch, !ep.onGround));
    }

    @Override
    public void onEnable()
    {
        cheatbase.addToTick(this);
    }

    @Override
    public void onDisable()
    {
        cheatbase.removeFromCurrentTick(this);
    }
}
