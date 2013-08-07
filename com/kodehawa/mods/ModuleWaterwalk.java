package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.util.Tickable;

public class ModuleWaterwalk extends Mod implements Tickable
{
   

    public ModuleWaterwalk()
    {
        super("Water Walk", "Became jesus!", Keyboard.KEY_J);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
        if (getPlayer().isInWater())
        {
            getPlayer().setSprinting(false);
            getPlayer().jump();
            getPlayer().motionY /= 2;
        }
    }

    @Override
    public void onEnable()
    {
    	CheatingEssentials.getCheatingEssentials().addToTick(this);
    }

    @Override
    public void onDisable()
    {
    	CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
    }

    public EntityClientPlayerMP getPlayer()
    {
        return CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer;
    }

    public boolean isMoving()
    {
        return Math.sqrt((getPlayer().motionX * getPlayer().motionX) + (getPlayer().motionZ * getPlayer().motionZ)) >= 0.04;
    }

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
}
