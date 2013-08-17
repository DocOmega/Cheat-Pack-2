package com.reeszrbteam.ce.console.commands;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;
import com.reeszrbteam.ce.console.BaseCommand;

public class CommandModuleList extends BaseCommand{

	public CommandModuleList() {
		super("modulelist", "Kodehawa", "1.6.2");
	}

	@Override
	public void runCommand(String s, String[] args) {
		try{
			for(Mod m: ModManager.getInstance().mods){
				String derp = m.name + " - " + Keyboard.getKeyName(m.getKeybind());
				CheatingEssentials.getCheatingEssentials().getUtils().addChatMessage(derp);
			}
		}catch(Exception e){
			
		}
	}

	@Override
	public String getDescription() {
		return "Shows all Modules with Keybind";
	}

	@Override
	public String getSyntax() {
		return "modulelist";
	}

}
