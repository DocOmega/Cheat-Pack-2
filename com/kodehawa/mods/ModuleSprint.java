package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.util.Tickable;

public class ModuleSprint extends Mod implements Tickable
{

	@ModuleInformation(
			credits = "Kodehawa, Godshawk",
			desc = "Allows to the player to sprint infinitely unless if the player is not moving",
			name = "Sprint")
	
    public ModuleSprint( )
    {
        super("Sprint", "Hi, I'm sprinting" /* So bad description heh */, Keyboard.KEY_H);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {

        // TODO Auto-generated method stub
        final EntityClientPlayerMP p = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer;
        if (p.movementInput.moveForward > 0) {
                p.setSprinting(true);
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

	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
}
