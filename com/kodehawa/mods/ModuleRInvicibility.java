package com.kodehawa.mods;

import net.minecraft.src.EntityPlayer;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.mods.Mod.ModuleInformation;
import com.kodehawa.util.Tickable;


public class ModuleRInvicibility extends Mod implements Tickable {

	@ModuleInformation(
			credits = "Kodehawa",
			desc = "Disable the damage",
			name = "Invencibility")
	
	/* 
	 * R: Relative :)  
	*/
    public ModuleRInvicibility( ) {
        super("Invencibility", "I'm a fucking GOD, only saying" , Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        //CheatingEssentials.getCheatingEssentials().addToTick(this);
    }

    @Override
    public void render() {
    }

    @Override
    public void onDisable() {
    	//CheatingEssentials.getCheatingEssentials().addToTick(this);
    	setDamage(true);
    }

    @Override
    public void tick() {
    	setDamage(false);

    }
    
    public void setDamage(boolean state) {
        EntityPlayer.getInstance().capabilities.disableDamage = !state;
     }
}
