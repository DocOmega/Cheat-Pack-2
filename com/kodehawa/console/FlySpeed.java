package com.kodehawa.console;

import com.kodehawa.CheatingEssentials;

import net.minecraft.src.Minecraft;

public class FlySpeed implements BaseCommand {

	String endres = "";
	Minecraft mc;
	
	@Override
	public void onRun(String[] cmd) {
		// TODO Auto-generated method stub
		endres = output(cmd);
		mc = CheatingEssentials.getCheatingEssentials().getMinecraftInstance();
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "flySpeed";
	}

	@Override
	public String showHelp() {
		// TODO Auto-generated method stub
		return "Usage: " + getName() + " <float number>";
	}

	@Override
	public String output() {
		// TODO Auto-generated method stub
		return endres;
	}

	
	String output(String[ ] cmd){
		try{
		Float result = Float.parseFloat(cmd [ 1 ]);
		if(result >= 1){
			return "Can't assign a value major to 1! Please use 0.x!";
		}
		else if(result <= 1){
		CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.capabilities.setFlySpeed(result);
		return "Fly speed changed to " + result + "!";
		}
		}
		catch (Exception e)
        {
          e.printStackTrace();
            return showHelp();
        }
		return endres;
		
		}

	

}
