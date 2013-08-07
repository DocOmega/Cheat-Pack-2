package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Packet13PlayerLookMove;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.util.Tickable;

public class ModuleNoFall extends Mod implements Tickable
{
    
    public ModuleNoFall( )
    {
        super("No Fall", "I don't like fall damage", Keyboard.KEY_V);
    }

    @Override
    public void tick()
    {
        // TODO Auto-generated method stub
        EntityClientPlayerMP ep = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer;
        ep.sendQueue.addToSendQueue(new Packet13PlayerLookMove(ep.motionX, -999.0D, -999.0D, ep.motionZ,
                ep.rotationYaw, ep.rotationPitch, !ep.onGround));
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
