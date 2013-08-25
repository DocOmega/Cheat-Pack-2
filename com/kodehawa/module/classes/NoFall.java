package com.kodehawa.module.classes;

import net.minecraft.src.Packet13PlayerLookMove;

import org.lwjgl.input.Keyboard;

import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.enums.EnumGuiCategory;

public class NoFall extends ModuleBase {

	public NoFall( ) {
		super("No Fall", "No fall damage", "1.6.2", Keyboard.KEY_V, 
				EnumGuiCategory.PLAYER, true);
        super.setTick(true);
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		 sendPacket(new Packet13PlayerLookMove(getPlayer().motionX, -999.0D, -999.0D, getPlayer().motionZ,
				 getPlayer().rotationYaw, getPlayer().rotationPitch, !getPlayer().onGround));
	}
	
	public void onEnableModule(){
	}
	
	public void onDisableModule(){
	}

}
