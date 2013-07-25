package com.kodehawa.module;

import java.util.List;

import net.minecraft.src.Entity;
import net.minecraft.src.EntityAnimal;
import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.EntityMob;
import net.minecraft.src.EntityOtherPlayerMP;
import net.minecraft.src.EntitySlime;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.ItemSword;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.gui.api.components.Value;
import com.kodehawa.util.EntityUtils;
import com.kodehawa.util.Watcher;

public class KillAura extends Module {

    
    public double val = 2.5;
    private double delay = val * 100;
    
    public static Value v = new Value( "Aura speed" );
    
    public KillAura( ) {
        super( "KillAura", "Sword-raipin' bishez since 1969. Lol 69", Keyboard.KEY_P, EnumGuiCategory.COMBAT );
        // TODO Auto-generated constructor stub
        v.setValue( 1 );
    }
    
    @Override
    public void render( ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void tick( ) {
    	for (int i = 0; i < CheatingEssentials.getCheatingEssentials().getMinecraftInstance().theWorld.loadedEntityList.size(); i++)
        {
            Entity ent = (Entity) CheatingEssentials.getCheatingEssentials().getMinecraftInstance().theWorld.loadedEntityList.get(i);
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

            if ((ent == CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer) || !(ent instanceof EntityLiving) || ent.isDead)
            {
                continue;
            }

            if ((CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.getDistanceSqToEntity(ent) <= 36D) && !ent.isDead && CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.canEntityBeSeen(ent))
            {
                //elb.faceEntity( ent, 100F, 100F );
            	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().playerController.attackEntity(CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer, ent);
            	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.swingItem();
            }
        }
    }
  
}
