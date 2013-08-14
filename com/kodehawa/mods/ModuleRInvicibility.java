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

    public ModuleRInvicibility( ) {
        super("Invencibility", "I'm a fucking GOD, only saying" , Keyboard.KEY_NONE);
    }

    @Override
    public void onEnable() {
        CheatingEssentials.getCheatingEssentials().addToTick(this);
    }

    @Override
    public void render() {
    }

    @Override
    public void onDisable() {
    	CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
    	disableDamage(false);
    }

    @Override
    public void tick() {
    	disableDamage(true);

    }
    
    public void disableDamage(boolean state) {
        EntityPlayer.getInstance().capabilities.disableDamage = state;
		EntityPlayer.getInstance().sendPlayerAbilities();
     }
}
