package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleNoKnockback extends Mod implements Tickable
{
    private CheatingEssentials cb;
    private Minecraft mc;

    public ModuleNoKnockback(CheatingEssentials c, Minecraft m)
    {
        super(Mods.NoKnockback);
        cb = c;
        mc = m;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
        EntityClientPlayerMP player = mc.thePlayer;

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
        cb.addToTick(this);
        cb.getUtils().addChatMessage(getActive());
        cb.getUtils().addChatMessage("Knockback? What it's this?");
    }

    @Override
    public void onDisable()
    {
        // TODO Auto-generated method stub
        cb.removeFromCurrentTick(this);
        cb.getUtils().addChatMessage(getActive());
    }
}
