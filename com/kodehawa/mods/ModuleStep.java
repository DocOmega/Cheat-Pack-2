package com.kodehawa.mods;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityPlayer;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleStep extends Mod implements Tickable {

	@ModuleInformation(credits = "Kodehawa",
			desc = "Allows to the player to jump blocks without tapping space",
			name = "Step")
		
	
	
	public ModuleStep( ) {
		super("Step", "More than a slab!", Keyboard.KEY_NUMPAD3);
		// TODO Auto-generated constructor stub
	}
	
	public static float STEP_HEIGHT = 1.0F;
	
	public static void setStepHeight(float number) {
		// TODO Auto-generated method stub
		STEP_HEIGHT = number;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		this.setStep(STEP_HEIGHT);
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		CheatingEssentials.getCheatingEssentials().addToTick(this);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
		this.setStep(0.5F);
	}
	
	private void setStep(float step){
		EntityPlayer.getInstance().setStepHeight(step);
	}

	
}
