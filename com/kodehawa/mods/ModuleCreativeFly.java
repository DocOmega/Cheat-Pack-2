package com.kodehawa.mods;

import org.lwjgl.input.Keyboard;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.Packet13PlayerLookMove;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleCreativeFly extends Mod implements Tickable {

    @ModuleInformation(
      name = "Creative Fly",
      desc = "Let's you to fly in survival, but just like when you're in creative",
      credits = "Kodehawa"
     )
    
	
	public ModuleCreativeFly( ) {
		super("Creative Fly", "", Keyboard.KEY_NUMPAD5);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
        setAllowFlying(true);
        
        EntityClientPlayerMP ep = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer;
        ep.sendQueue.addToSendQueue(new Packet13PlayerLookMove(ep.motionX, -999.0D, -999.0D, ep.motionZ,
                ep.rotationYaw, ep.rotationPitch, !ep.onGround));
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
		setAllowFlying(false);
	}

	public boolean getAllowFlying() {
	      return EntityPlayer.getInstance().capabilities.allowFlying;
	   }
	
	public void setAllowFlying(boolean apeteporico) {
		EntityPlayer.getInstance().capabilities.allowFlying = apeteporico;
		EntityPlayer.getInstance().sendPlayerAbilities();
	}
	
}
