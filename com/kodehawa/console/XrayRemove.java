package com.kodehawa.console;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.ModuleXray;

public class XrayRemove implements BaseCommand{
	
    public String endres = "";

	@Override
	public void onRun(String[] cmd) {
		// TODO Auto-generated method stub
		endres = output(cmd);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "xrayRemoveBlock";
	}

	@Override
	public String showHelp() {
		// TODO Auto-generated method stub
		return "Usage: xrayRemoveBlock <integer>";
	}

	@Override
	public String output() {
		// TODO Auto-generated method stub
		return endres;
	}
	
	String output(String[ ] cmd){
		try
        {
        	int olakase = Integer.parseInt( cmd[ 1 ] );
        	ModuleXray.xrayBlocks.remove( olakase );
        	CheatingEssentials.getCheatingEssentials().saveXrayList();
        	CheatingEssentials.getCheatingEssentials().CELogAgent.logInfo("You've removed a block from the X-Ray list: " + olakase);
            return "Block ID removed from Int: " + olakase;
        }
        catch (Exception e)
        {
           return showHelp();
        }
    }
		
	}


