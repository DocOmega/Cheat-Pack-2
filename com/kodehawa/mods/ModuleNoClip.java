package com.kodehawa.mods;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Minecraft;
import net.minecraft.src.Packet13PlayerLookMove;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class ModuleNoClip extends Mod implements Tickable{

	private Minecraft mc;
	private CheatBase cb;
	
	public ModuleNoClip(CheatBase c, Minecraft m) {
		super( Mods.NoClip );
		cb = c;
		mc = m;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		 if (!CheatBase.getInstance().getWrapper.getMinecraft().thePlayer.capabilities.isFlying)
	        {
	            CheatBase.getInstance().getWrapper.getMinecraft().thePlayer.capabilities.isFlying = true;
	        }

	        EntityClientPlayerMP ep = CheatBase.getInstance().getWrapper.getMinecraft().thePlayer;
	        ep.sendQueue.addToSendQueue(new Packet13PlayerLookMove(ep.motionX, -999.0D, -999.0D, ep.motionZ,
	                ep.rotationYaw, ep.rotationPitch, !ep.onGround));
	}

	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		cb.addToTick(this);
		mc.gameSettings.noclip = true;
		mc.gameSettings.noclipRate = 3.0F;
		cb.getUtils().addChatMessage(getActive());
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		cb.removeFromTick(this);
		mc.gameSettings.noclip = false;
		mc.thePlayer.capabilities.isFlying = false;
		cb.getUtils().addChatMessage(getActive());

	}

}
