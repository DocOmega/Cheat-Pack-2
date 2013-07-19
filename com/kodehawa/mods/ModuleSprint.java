package com.kodehawa.mods;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleSprint extends Mod implements Tickable
{
    private final CheatingEssentials cheatbase;
    private final Minecraft mc;

    public ModuleSprint(CheatingEssentials cb, Minecraft m)
    {
        super(Mods.Sprint);
        cheatbase = cb;
        mc = m;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {
        double speedmul = 1 + (Vars.speedbonus / 10);

        // TODO Auto-generated method stub
        if (mc.thePlayer.movementInput.moveForward > 0F)
        {
            mc.thePlayer.setSprinting(true);

            if (Vars.doublespeed)
            {
                if (mc.thePlayer.onGround)
                {
                    mc.thePlayer.motionX *= speedmul;
                    mc.thePlayer.motionZ *= speedmul;
                }
            }
        }
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
