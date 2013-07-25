package com.kodehawa.module;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Packet13PlayerLookMove;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;

public class NoFall extends Module {
    public NoFall( ) {
        super( "NoFall", "Fall damage? What fall damage?", Keyboard.KEY_O, EnumGuiCategory.PLAYER );
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void render( ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void tick( ) {
        // TODO Auto-generated method stub
        EntityClientPlayerMP ep = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer;
        ep.sendQueue.addToSendQueue( new Packet13PlayerLookMove( ep.motionX, -999.0D, -999.0D, ep.motionZ,
                ep.rotationYaw, ep.rotationPitch, !ep.onGround ) );
    }
  
    
}
