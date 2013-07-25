package com.kodehawa.module;

import net.minecraft.src.Minecraft;

import org.lwjgl.input.Keyboard;

public class FullBright extends Module {
    public FullBright( ) {
        super( "FullBright", "Light in the darkness", Keyboard.KEY_F, EnumGuiCategory.WORLD );
        // TODO Auto-generated constructor stub
    }
    
    private static Minecraft mc;
    
    @Override
    public void render( ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void tick( ) {
        // TODO Auto-generated method stub
        mc.gameSettings.gammaSetting++;
    }
    
    @Override
    public void toggle( ) {
        super.toggle( );
        if( !this.getActive( ) ) {
        	mc.gameSettings.gammaSetting = 0.2F;
        }
    }
  
}
