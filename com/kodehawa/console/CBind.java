package com.kodehawa.console;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;

public class CBind implements BaseCommand {

	String endres = "";
	
	@Override
	public void onRun(String[] cmd) {
		// TODO Auto-generated method stub
		endres = output(cmd);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "test";
	}

	@Override
	public String showHelp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String output() {
		// TODO Auto-generated method stub
		return endres;
	}
	
	String output(String[ ] cmd){
		for(Mod m : ModManager.getInstance().mods){
			if(endres.contains(m.name)){
				Integer key = Integer.parseInt(cmd[1]);
				m.keybind = key;
			}
		}
		
		return endres;
		
	}

}
