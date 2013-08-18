package com.kodehawa.module.classes;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityLiving;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.module.ModuleBase;
import com.kodehawa.module.enums.EnumGuiCategory;
import com.kodehawa.util.EntityUtils;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Watcher;

public class Mobaura extends ModuleBase {

	public Mobaura( ) {
		super("Mob Aura", "", "1.6.2", Keyboard.KEY_L,
				EnumGuiCategory.PLAYER, true);
        super.setTick(true);
	}

	public static double AURA_DISTANCE = 90D;
	
	public static void setAuraDistance( double thing ){
		AURA_DISTANCE = thing;
	}
	
	@Override
	public void tick() {
		// TODO Auto-generated method stub
		for (int i = 0; i < getMinecraft().theWorld.loadedEntityList.size(); i++)
        {
            Entity ent = (Entity) getMinecraft().theWorld.loadedEntityList.get(i);
            int id = ent.entityId;
            long now = System.currentTimeMillis();
            Watcher tracked = EntityUtils.getLastAffected(id);

            if (tracked != null)
            {
                if (tracked.matches(ent, now))
                {
                    continue;
                }
            }

            EntityUtils.setLastAffected(id, ent);

            if ((ent == getMinecraft().thePlayer) || !(ent instanceof EntityLiving) || ent.isDead)
            {
                continue;
            }

            if ((getDistanceSqToEntity(ent) <= AURA_DISTANCE) && !ent.isDead && getMinecraft().thePlayer.canEntityBeSeen(ent))
            {
                
            	getMinecraft().playerController.attackEntity(getMinecraft().thePlayer, ent);
            	
            }
        }
	}

	@Override
	public void onEnableModule() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onDisableModule() {
		// TODO Auto-generated method stub
	}
}
