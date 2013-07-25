package com.kodehawa.module;

import net.minecraft.src.EntityClientPlayerMP;

import com.kodehawa.CheatingEssentials;

public class AntiKnockback extends Module {
    
    public AntiKnockback( ) {
        super( "AntiKnockback", "Don't move when hit", EnumGuiCategory.KILLAURA );
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void render( ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void tick( ) {
        // TODO Auto-generated method stub
    	EntityClientPlayerMP player = CheatingEssentials.getMinecraftInstance().thePlayer;

        if (player.hurtTime > 0 && player.hurtResistantTime > 0)
        {
            player.motionX = 0;
            player.motionZ = 0;
        }
    }
  
}
