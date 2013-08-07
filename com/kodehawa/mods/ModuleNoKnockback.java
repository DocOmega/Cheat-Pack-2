package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.util.Tickable;

public class ModuleNoKnockback extends Mod implements Tickable
{
 

    public ModuleNoKnockback()
    {
        super("No Knockback", "", Keyboard.KEY_O);
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

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
}
