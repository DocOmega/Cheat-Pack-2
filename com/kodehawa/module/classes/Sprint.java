package com.kodehawa.module.classes;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.annotations.ModuleLoader;
import com.kodehawa.module.enums.EnumGuiCategory;
import com.kodehawa.util.Tickable;

public class Sprint extends ModuleBase implements Tickable {

	@ModuleLoader(type = "Module")
	public Sprint( ) {
		super("Sprint", "Without double tapping!", "1.6.2",
				Keyboard.KEY_H, EnumGuiCategory.PLAYER, true);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		if(getPlayer().movementInput.moveForward > 0){
			getPlayer().setSprinting(true);
		}
	}

	@Override
	public void onEnableModule() {
		CheatingEssentials.getCheatingEssentials().addToTick(this);
	}

	@Override
	public void onDisableModule() {
		// TODO Auto-generated method stub
		CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
	}

}
