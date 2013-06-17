package com.kodehawa.mods;

import net.minecraft.client.Minecraft;

import com.kodehawa.CheatBase;
import com.kodehawa.util.Tickable;

public class FasterWalk extends Mod implements Tickable {

	public FasterWalk( CheatBase c, Minecraft m ) {
		super( Mods.FASTERWALK );
		cb = c;
		mc = m;
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void tick() {
		mc.thePlayer.capabilities.walkSpeed = 0.2F;
		
	}

	@Override
	public void onEnable() {
		cb.addToTick( this );
		cb.getUtils( ).addChatMessage( getActive( ) );
	}

	@Override
	public void onDisable() {
		cb.removeFromTick( this );
		mc.thePlayer.capabilities.walkSpeed = 0.1F;
		cb.getUtils( ).addChatMessage( getActive( ) );
	}

	public Minecraft mc;
	public CheatBase cb;

}
