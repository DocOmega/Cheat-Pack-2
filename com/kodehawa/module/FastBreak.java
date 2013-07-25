package com.kodehawa.module;

import net.minecraft.src.PotionEffect;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;

public class FastBreak extends Module {
    
    public FastBreak( ) {
        super( "FastBreak", "Break blocks faster than normal", Keyboard.KEY_G, EnumGuiCategory.PLAYER );
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void render( ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void tick( ) {
        // TODO Auto-generated method stub
        CheatingEssentials.getMinecraftInstance( ).thePlayer.addPotionEffect( new PotionEffect( 3, 999999999,
                2, true ) );
    }
    
    @Override
    public void toggle( ) {
        super.toggle( );
        CheatingEssentials.getMinecraftInstance( ).thePlayer.removePotionEffect( 3 );
    }
  
}
