package com.kodehawa.core;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.F3UtilAdvancedTooltips;
import com.kodehawa.mods.F3UtilMobHitbox;
import com.kodehawa.mods.F3UtilRerenderLoadedChunks;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModuleAltXray;
import com.kodehawa.mods.ModuleAutoRespawn;
import com.kodehawa.mods.ModuleAutoSwitch;
import com.kodehawa.mods.ModuleClientFastplace;
import com.kodehawa.mods.ModuleFastBreak;
import com.kodehawa.mods.ModuleFly;
import com.kodehawa.mods.ModuleFullbright;
import com.kodehawa.mods.ModuleItemTooltips;
import com.kodehawa.mods.ModuleKillAura;
import com.kodehawa.mods.ModuleNoFall;
import com.kodehawa.mods.ModuleNoKnockback;
import com.kodehawa.mods.ModuleSprint;
import com.kodehawa.mods.ModuleTestChestFinder;
import com.kodehawa.mods.ModuleWaterwalk;
import com.kodehawa.mods.ModuleXray;

public class CModLoader {
	
    private static ModuleXray xray;
    private static ModuleTestChestFinder cesp;
    private static ModuleFullbright fullbright;
    private static ModuleKillAura killa;
    private static ModuleNoFall nofall;
    private static ModuleWaterwalk waterw;
    private static ModuleFastBreak fb;
    private static ModuleNoKnockback nk;
    private static ModuleSprint s;
    private static ModuleClientFastplace fp;
    private static ModuleFly fly;

	
	public static void loadModulesforKB(){
		xray = new ModuleXray();
        fly = new ModuleFly();
		cesp = new ModuleTestChestFinder();
		fullbright = new ModuleFullbright();
		killa = new ModuleKillAura();
		fb = new ModuleFastBreak();
		waterw = new ModuleWaterwalk();
		nk = new ModuleNoKnockback();
		nofall = new ModuleNoFall();
		fp = new ModuleClientFastplace();
		s = new ModuleSprint();
	}
	
	
	/**
	 * Register modules. 
	 * - I'm the only that think that this it's like Item / Block vanilla registering? :)
	 */
	
	public static void addModulestoArray(){
		
		try{
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleFullbright( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleWaterwalk( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleXray( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleAltXray( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleAutoRespawn( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleTestChestFinder( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleFastBreak( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleAutoSwitch( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addWMod(new ModuleClientFastplace( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addPMod(new ModuleFly( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addPMod(new ModuleKillAura( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addPMod(new ModuleNoFall( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addPMod(new ModuleSprint( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addPMod(new ModuleNoKnockback( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addPMod(new ModuleItemTooltips( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addUtils(new F3UtilRerenderLoadedChunks( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addUtils(new F3UtilMobHitbox( ));
        CheatingEssentials.getCheatingEssentials().mainModLoader.addUtils(new F3UtilAdvancedTooltips( ));

        for (Mod m : CheatingEssentials.getCheatingEssentials().mainModLoader.worldMods)
        {
        	CheatingEssentials.getCheatingEssentials().mods.add(m);
        	CheatingEssentials.getCheatingEssentials().CELogAgent.logInfo("Module Loaded: " + m);
        }

        for (Mod m : CheatingEssentials.getCheatingEssentials().mainModLoader.playerMods)
        {
        	CheatingEssentials.getCheatingEssentials().mods.add(m);
        	CheatingEssentials.getCheatingEssentials().CELogAgent.logInfo("Module Loaded: " + m);
        }
        
        for (Mod m : CheatingEssentials.getCheatingEssentials().mainModLoader.f3utils)
        {
        	CheatingEssentials.getCheatingEssentials().mods.add(m);
        	CheatingEssentials.getCheatingEssentials().CELogAgent.logInfo("Module Loaded: " + m);
        }
        
		}
		catch(Exception ex){
			CheatingEssentials.getCheatingEssentials().CELogAgent.logSevere("Can't load basic modules at all or some modules can't be loaded. This will be bad, but the mod it still working.");
			CheatingEssentials.getCheatingEssentials().CELogAgent.logSevere("Report it in MCF thread. Good luck.");
			for (Mod m : CheatingEssentials.getCheatingEssentials().mods)
	        {
				CheatingEssentials.getCheatingEssentials().CELogAgent.logInfo("Can't load module: " + m + " " + ex);
	        }

	            ex.printStackTrace( );
	            System.out.println( "Failed to initialize Cheating Essentials. Damn :(. " + ex.toString( ) );
	            ex.printStackTrace( );
	            
	            String logString = "FT|CrashLog\r\n[PLAIN]\r\n---Begin plain text---\r\n";
	            logString += "Console Log:\r\n";
	            logString += "Error in CE Thread init: " + ex.toString( ) + "\r\n\r\n";
	            for( StackTraceElement ele : ex.getStackTrace( ) ) {
	                logString += ele.getClassName( ) + " " + ele.toString( ) + "\r\n";
	            }
	            CheatingEssentials.getCheatingEssentials().writeCrash( logString );
	        }
		}
	
}
