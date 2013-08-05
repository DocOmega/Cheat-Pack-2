package com.kodehawa.mods;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.util.Tickable;

public class ModuleSprint extends Mod implements Tickable
{

    public ModuleSprint( )
    {
        super("Sprint", "Hi, I'm sprinting", Keyboard.KEY_2, Mods.Sprint);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void tick()
    {

        // TODO Auto-generated method stub
        if (!CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.isSprinting())
        {
        	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.setSprinting(true);

            
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
