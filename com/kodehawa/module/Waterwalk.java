package com.kodehawa.module;

import net.minecraft.src.EntityClientPlayerMP;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;

public class Waterwalk extends Module {

	public Waterwalk( ) {
		super("Waterwalk", "Jesus pls", Keyboard.KEY_H ,EnumGuiCategory.WORLD);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if (getPlayer().isInWater())
        {
            getPlayer().setSprinting(false);
            getPlayer().jump();
            getPlayer().motionY /= 2;
        }
	}

	 public EntityClientPlayerMP getPlayer()
	    {
	        return CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer;
	    }

	    public boolean isMoving()
	    {
	        return Math.sqrt((getPlayer().motionX * getPlayer().motionX) + (getPlayer().motionZ * getPlayer().motionZ)) >= 0.04;
	    }
	
}
