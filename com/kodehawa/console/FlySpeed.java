package com.kodehawa.console;

import net.minecraft.src.Minecraft;

public class FlySpeed implements BaseCommand {

	String endres = "";
	float apetecan = 1.0F;
	Minecraft mc;
	
	@Override
	public void onRun(String[] cmd) {
		// TODO Auto-generated method stub
		endres = output(cmd);
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
		float result = (float) Double.parseDouble(cmd [ 1 ]);
		mc.thePlayer.capabilities.setFlySpeed(result);
		return "Fly speed changed to " + result + "!";}
		catch (Exception e)
        {
            return showHelp();
        }
	}
	
	private void setFlySpeed(){
		
	}

}
