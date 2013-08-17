package com.kodehawa.module.classes;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.enums.EnumGuiCategory;
import com.kodehawa.util.Tickable;

public class AutoRespawn extends ModuleBase implements Tickable {

	public AutoRespawn( ) {
		super("Auto Respawn", "", "1.6.2", Keyboard.KEY_NUMPAD0, EnumGuiCategory.WORLD, true);
	}

	@Override
	public void tick() {
		if(getPlayer().isDead){
			getPlayer().respawnPlayer();
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
