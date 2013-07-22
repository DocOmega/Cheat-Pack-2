package com.kodehawa.console;

import net.minecraft.src.Minecraft;

public class FlySpeed implements BaseCommand {

	String endres = "";
	float apetecan = 1.0F;
	Minecraft mc;
	
	@Override
	public void onRun(String[] cmd) {
		// TODO Auto-generated method stub
		apetecan = getOutput(apetecan);
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
		return "Fly speed changed to " + apetecan;
	}
	
	private float getOutput(float fl){
		mc.thePlayer.capabilities.setFlySpeed(fl);
		return fl;
	}
	
	private void setFlySpeed(){
		
	}

}
