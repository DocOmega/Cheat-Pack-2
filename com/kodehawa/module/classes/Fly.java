package com.kodehawa.module.classes;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Packet13PlayerLookMove;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.annotations.ModuleLoader;
import com.kodehawa.module.enums.EnumGuiCategory;
import com.kodehawa.util.Tickable;

public class Fly extends ModuleBase implements Tickable {

	@ModuleLoader(type = "Module")
	public Fly( ) {
		super("Fly", "Fly like a bird!", "1.6.2", Keyboard.KEY_R,
				EnumGuiCategory.PLAYER, true);
	}


	@Override
    public void onEnableModule(){
		CheatingEssentials.getCheatingEssentials().addToTick(this);
		CheatingEssentials.getMinecraftInstance().thePlayer.capabilities.isFlying = true;
	}
	
	@Override
	public void onDisableModule(){
		CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
		CheatingEssentials.getMinecraftInstance().thePlayer.capabilities.isFlying = false;
	}
	
	@Override
	public void tick() {
		if(!CheatingEssentials.getMinecraftInstance().thePlayer.capabilities.isFlying){
			CheatingEssentials.getMinecraftInstance().thePlayer.capabilities.isFlying = true;
		}
		
	    EntityClientPlayerMP ep = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer;
        ep.sendQueue.addToSendQueue(new Packet13PlayerLookMove(ep.motionX, -999.0D, -999.0D, ep.motionZ,
                ep.rotationYaw, ep.rotationPitch, !ep.onGround));
	}

}
