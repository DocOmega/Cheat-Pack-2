package com.kodehawa.module;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;

public class FastPlace extends Module {
    
    public FastPlace( ) {
        super( "FastPlace", "Place blocks faster than normal", Keyboard.KEY_N, EnumGuiCategory.PLAYER );
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void render( ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void tick( ) {
        // TODO Auto-generated method stub
        try {
        	CheatingEssentials.getMinecraftInstance( ).rightClickDelayTimer = 0;
        } catch( Exception e ) {
            e.printStackTrace( );
        }
    }
  
}
