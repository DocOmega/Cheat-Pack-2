package com.kodehawa.module;

import net.minecraft.src.EntityClientPlayerMP;
import net.minecraft.src.Packet13PlayerLookMove;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;

public class Fly extends Module {
    
    public Fly( ) {
        super( "Fly", "Fly like the wind, Bullseye!", Keyboard.KEY_J, EnumGuiCategory.PLAYER );
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void render( ) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void tick( ) {
        // TODO Auto-generated method stub
        if( !CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.capabilities.isFlying ) {
        	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.capabilities.isFlying = true;
        }
        EntityClientPlayerMP ep = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer;
        ep.sendQueue.addToSendQueue( new Packet13PlayerLookMove( ep.motionX, -999.0D, -999.0D, ep.motionZ,
                ep.rotationYaw, ep.rotationPitch, !ep.onGround ) );
    }
    
    @Override
    public void toggle( ) {
        super.toggle( );
        if( !getActive( ) ) {
        	CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.capabilities.isFlying = false;
        }
    }
    
}
