package com.kodehawa.console;

import net.minecraft.src.EntityPlayer;

import com.kodehawa.CheatingEssentials;

public class PlayerView implements BaseCommand {

	/**
	 * - Thanks ZRBTeam 
	 * @author ZRBTeam
	 */
	
	String endres = "";
	
	@Override
	public void onRun(String[] cmd) {
		// TODO Auto-generated method stub
		endres = output(cmd);
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "playerView";
	}

	@Override
	public String showHelp() {
		// TODO Auto-generated method stub
		return "Usage: playerView <player>. If you like to view your camera in <player> put off";
	}

	@Override
	public String output() {
		// TODO Auto-generated method stub
		return endres;
	}
	
	String output(String[] cmd){
		try {
			   if(cmd[0].equalsIgnoreCase("off")) {
			    CheatingEssentials.getCheatingEssentials().getMinecraftInstance().renderViewEntity = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer;
			    System.out.println("Now viewing your player (" + "Your camera" + ")");
			    return "Now viewing your player (" + "Your camera" + ")";
			   
			   }
			   for(Object o : CheatingEssentials.getCheatingEssentials().getMinecraftInstance().theWorld.loadedEntityList) {
			    if(o instanceof EntityPlayer) {
			     EntityPlayer e = (EntityPlayer) o;
			     if(e.username.equalsIgnoreCase(cmd[0])) {
			    	 CheatingEssentials.getCheatingEssentials().getMinecraftInstance().renderViewEntity = e;
			    	 System.out.println("Now viewing player" + e.username);
			    	 return "Now viewing player " + e.username;
			     }
			    }
			   }
			  } catch(Exception e) {
				  e.printStackTrace();
				  return showHelp();
			  }
		
		return endres;
		
	}

}
