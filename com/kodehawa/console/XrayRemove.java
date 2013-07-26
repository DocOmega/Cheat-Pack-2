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
        	Integer blockID = Integer.parseInt( cmd[ 1 ] );
        	ModuleXray.xrayBlocks.remove( blockID );
        	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().renderGlobal.loadRenderers();
            CheatingEssentials.getCheatingEssentials().saveXrayList();
        	CheatingEssentials.getCheatingEssentials().CELogAgent.logInfo("You've removed a block from the X-Ray list: " + blockID);
            return "Block ID removed from Int: " + blockID;
        }
        catch (Exception e)
        {
           return showHelp();
        }
    }
		
	}


