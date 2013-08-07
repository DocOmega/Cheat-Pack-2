package com.kodehawa.mods;

import com.kodehawa.event.Event;

import net.minecraft.src.RenderManager;

public class F3UtilMobHitbox extends Mod{

	public F3UtilMobHitbox() {
		super("Mob Hitbox", "", 0);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		 RenderManager.field_85095_o = !RenderManager.field_85095_o;
	}

	@Override
	public void onDisable() {
		// TODO Auto-generated method stub
		 RenderManager.field_85095_o = !RenderManager.field_85095_o;
	}


	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
