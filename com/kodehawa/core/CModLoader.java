package com.kodehawa.core;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.F3UtilAdvancedTooltips;
import com.kodehawa.mods.F3UtilMobHitbox;
import com.kodehawa.mods.F3UtilRerenderLoadedChunks;
import com.kodehawa.mods.Mod;
import com.kodehawa.mods.ModManager;
import com.kodehawa.mods.ModuleAutoRespawn;
import com.kodehawa.mods.ModuleClientFastplace;
import com.kodehawa.mods.ModuleCreativeFly;
import com.kodehawa.mods.ModuleFastBreak;
import com.kodehawa.mods.ModuleFly;
import com.kodehawa.mods.ModuleFullbright;
import com.kodehawa.mods.ModuleItemTooltips;
import com.kodehawa.mods.ModuleKillAura;
import com.kodehawa.mods.ModuleMobESP;
import com.kodehawa.mods.ModuleNoFall;
import com.kodehawa.mods.ModuleNoKnockback;
import com.kodehawa.mods.ModuleRInvicibility;
import com.kodehawa.mods.ModuleSprint;
import com.kodehawa.mods.ModuleStep;
import com.kodehawa.mods.ModuleTestChestFinder;
import com.kodehawa.mods.ModuleWaterwalk;
import com.kodehawa.mods.ModuleXray;

public class CModLoader {
	
	/**
	 * Where it's the boolean?
	 */
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
    private static ModuleMobESP me;
    private static ModuleCreativeFly fly2;
    private static ModuleStep step;
    private static volatile CModLoader instance;

	public CModLoader(){
		addModulestoArray();
	}
    
	public static void loadModulesforKB(){
		xray = new ModuleXray();
        fly = new ModuleFly();
		cesp = new ModuleTestChestFinder();
		fullbright = new ModuleFullbright();
		killa = new ModuleKillAura();
		fb = new ModuleFastBreak();
        me = new ModuleMobESP();
		waterw = new ModuleWaterwalk();
		nk = new ModuleNoKnockback();
		nofall = new ModuleNoFall();
		fp = new ModuleClientFastplace();
		s = new ModuleSprint();
		fly2 = new ModuleCreativeFly();
		step = new ModuleStep();
	}
	
	
	/**
	 * Register modules. 
	 * - I'm the only that think that this it's like Item / Block vanilla registering? :)
	 */
	
	public static void addModulestoArray(){
		
		try{
			
		ModManager.getInstance().addWMod(new ModuleFullbright( ));
		ModManager.getInstance().addWMod(new ModuleWaterwalk( ));
		ModManager.getInstance().addWMod(new ModuleXray( ));
		ModManager.getInstance().addWMod(new ModuleAutoRespawn( ));
		ModManager.getInstance().addWMod(new ModuleTestChestFinder( ));
		ModManager.getInstance().addWMod(new ModuleFastBreak( ));
        ModManager.getInstance().addWMod(new ModuleClientFastplace( ));
        ModManager.getInstance().addPMod(new ModuleFly( ));
        ModManager.getInstance().addPMod(new ModuleCreativeFly( ));
        ModManager.getInstance().addPMod(new ModuleKillAura( ));
        ModManager.getInstance().addPMod(new ModuleNoFall( ));
        ModManager.getInstance().addPMod(new ModuleSprint( ));
        ModManager.getInstance().addPMod(new ModuleStep( ));
        ModManager.getInstance().addPMod(new ModuleNoKnockback( ));
        ModManager.getInstance().addPMod(new ModuleItemTooltips( ));
        ModManager.getInstance().addPMod(new ModuleRInvicibility( ));
        ModManager.getInstance().addUtils(new F3UtilRerenderLoadedChunks( ));
        ModManager.getInstance().addUtils(new F3UtilMobHitbox( ));
        ModManager.getInstance().addUtils(new F3UtilAdvancedTooltips( ));

        for (Mod m : ModManager.getInstance().worldMods)
        {
        	CheatingEssentials.getCheatingEssentials().mods.add(m);
        }

        for (Mod m : ModManager.getInstance().playerMods)
        {
        	CheatingEssentials.getCheatingEssentials().mods.add(m);
        }
        
        for (Mod m : ModManager.getInstance().f3utils)
        {
        	CheatingEssentials.getCheatingEssentials().mods.add(m);
        }
        
		}
		catch(Exception ex){
			CheatingEssentials.getCheatingEssentials().CELogAgent("Can't load basic modules at all or some modules can't be loaded. This will be bad, but the mod it still working.");
			CheatingEssentials.getCheatingEssentials().CELogAgent("Report it in MCF thread. Good luck.");
			for (Mod m : CheatingEssentials.getCheatingEssentials().mods)
	        {
				CheatingEssentials.getCheatingEssentials().CELogAgent("Can't load module: " + m + " " + ex);
	        }
	            System.out.println( "Error loading basic modules: " + ex.toString( ) );
	            ex.printStackTrace( );
		}
		}

	
    public static CModLoader getMInstance() {
        if (instance == null) {
                instance = new CModLoader();
        }
        return instance;
}
}
