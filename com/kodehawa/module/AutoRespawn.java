package com.kodehawa.module;

import com.kodehawa.CheatingEssentials;

public class AutoRespawn extends Module {
    
    public AutoRespawn( ) {
        super( "AutoRespawn", "Death is but an inconvenience...", EnumGuiCategory.PLAYER );
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void render( ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void tick( ) {
        // TODO Auto-generated method stub
        if( CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.isDead ) {
        	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.respawnPlayer( );
        }
    }
  
}
