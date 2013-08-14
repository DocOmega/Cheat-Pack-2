package com.kodehawa.console;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.ModuleStep;

public class StepHeight implements BaseCommand {

	private String endres = "";
	
	@Override
	public void onRun(String[] cmd) {
		// TODO Auto-generated method stub
		endres = output(cmd);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "setStep";
	}

	@Override
	public String showHelp() {
		// TODO Auto-generated method stub
		return "Usage: setStep <float>";
	}

	@Override
	public String output() {
		// TODO Auto-generated method stub
		return endres;
	}
	
	String output(String[ ] cmd){
		try{
		float result = Float.parseFloat(cmd [ 1 ]);
		if(result <= 100.0F){
		ModuleStep.setStepHeight(result);
		return "Step height changed to " + result + "!";
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
