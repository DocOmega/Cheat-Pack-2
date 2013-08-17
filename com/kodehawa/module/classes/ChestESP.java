package com.kodehawa.module.classes;

import org.lwjgl.input.Keyboard;

import com.kodehawa.mods.Vars;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.annotations.ModuleLoader;
import com.kodehawa.module.enums.EnumGuiCategory;

public class ChestESP extends ModuleBase {

	@ModuleLoader(type = "Module")
	public ChestESP( ) {
		super("Chest Finder", "Find ALL chests!", "1.6.2", Keyboard.KEY_N,
				EnumGuiCategory.WORLD, true);
		super.setRender(true);
		// TODO Auto-generated constructor stub
	}

	public void onEnableModule( ){
		Vars.ChestESP = true;
	}
	
	public void onDisableModule( ){
		Vars.ChestESP = false;
	}
}
