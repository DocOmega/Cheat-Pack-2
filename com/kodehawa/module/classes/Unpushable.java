package com.kodehawa.module.classes;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.enums.EnumGuiCategory;
import com.kodehawa.util.Tickable;

public class Unpushable extends ModuleBase implements Tickable {

	public Unpushable( ) {
		super("Unpushable", "", "1.6.2", Keyboard.KEY_NUMPAD1, EnumGuiCategory.PLAYER , true );
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
       if(getPlayer().hurtTime > 0 && getPlayer().hurtResistantTime > 0){
    	   getPlayer().motionX = 0;
    	   getPlayer().motionZ = 0;
       }
	}

	@Override
	public void onEnableModule() {
		CheatingEssentials.getCheatingEssentials().addToTick(this);
	}

	@Override
	public void onDisableModule() {
		CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
	}

}
