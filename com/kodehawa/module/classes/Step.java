package com.kodehawa.module.classes;

import com.reeszrbteam.ce.util.EntitySpectator;
import net.minecraft.src.EntityPlayer;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.annotations.ModuleLoader;
import com.kodehawa.module.enums.EnumGuiCategory;
import com.kodehawa.util.Tickable;

public class Step extends ModuleBase {

	public static float STEP_HEIGHT = 1.0F;

	@ModuleLoader(type = "Module")
	public Step( ) {
		super("Step", "More than a slab!", "1.6.2", Keyboard.KEY_NUMPAD1,
				EnumGuiCategory.PLAYER, true);
		// TODO Auto-generated constructor stub
        super.setTick(true);
	}

    public static void setStepHeight( float f ){
        STEP_HEIGHT = f;
    }

	@Override
	public void onEnableModule(){
	}
	
	@Override
	public void onDisableModule(){
		setStep(0.5F);
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		setStep(STEP_HEIGHT);
	}
	
	public void setStep( float f ){
        getMinecraft().thePlayer.setStepHeight(f);
	}


}
