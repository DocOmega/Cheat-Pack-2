package com.kodehawa.mods;

import java.lang.reflect.Field;

import net.minecraft.src.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleFastPlace extends Mod implements Tickable
{
    CheatBase cb;
    Minecraft minecraft;

    public ModuleFastPlace(CheatBase cb, Minecraft mc)
    {
        super(Mods.Fastplace);
        // TODO Auto-generated constructor stub
        this.cb = cb;
        this.minecraft = mc;
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
    	 
       CheatBase.getWrapper.getMinecraft().rightClickDelayTimer = 0;
    }

    @Override
    public void onEnable()
    {
        cb.addToTick(this);
        cb.getUtils().addChatMessage(getActive());
        cb.getUtils().addChatMessage("Place blocks, everywhere!");
    }

    @Override
    public void onDisable()
    {
        cb.removeFromTick(this);
        cb.getUtils().addChatMessage(getActive());
    }
}
