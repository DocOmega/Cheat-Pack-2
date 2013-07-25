package com.kodehawa.module;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;

public class Sneak extends Module {
    public Sneak( ) {
        super( "Sneak", "Sneaky little bugger", Keyboard.KEY_U, EnumGuiCategory.PLAYER );
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void render( ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void tick( ) {
        // TODO Auto-generated method stub
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance( ).gameSettings.keyBindSneak.pressed = true;
    }
    
    @Override
    public void toggle( ) {
        super.toggle( );
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance( ).gameSettings.keyBindSneak.pressed = false;
    }
  
}
