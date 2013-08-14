package com.kodehawa.mods;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.util.Tickable;

public class ModuleAutoRespawn extends Mod implements Tickable
{
   @ModuleInformation(
		   credits = "Godshawk",
		   desc = "If you death, you will respawn.",
		   name = "Auto Respawn"
		   )

    public ModuleAutoRespawn()
    {
        super("Auto-Respawn", "Death == Respawn :)", Keyboard.KEY_NUMPAD0);
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
