package com.kodehawa.mods;

import net.minecraft.src.RenderManager;

public class F3UtilMobHitbox extends Mod{

	public F3UtilMobHitbox() {
		super(Mods.F3MobHitbox);
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

}
