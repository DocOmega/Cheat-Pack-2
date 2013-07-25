package com.kodehawa.module;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;

public class Sprint extends Module {
    public Sprint( ) {
        super( "Sprint", "Run, Forrest, run!", Keyboard.KEY_K, EnumGuiCategory.PLAYER );
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void render( ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void tick( ) {
        // TODO Auto-generated method stub
        if( !CheatingEssentials.getMinecraftInstance().thePlayer.isSprinting( ) ) {
            CheatingEssentials.getMinecraftInstance().thePlayer.setSprinting( true );
        }
    }
  
}
