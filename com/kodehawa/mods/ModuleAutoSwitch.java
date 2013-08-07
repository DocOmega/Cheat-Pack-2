package com.kodehawa.mods;

import net.minecraft.src.Block;
import net.minecraft.src.ItemStack;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.event.events.EventBlockClick;
import com.kodehawa.util.Tickable;
import com.kodehawa.util.Vector3D;

public class ModuleAutoSwitch extends Mod implements Tickable {

	public ModuleAutoSwitch( ) {
		super("Auto Switch", "Automatically select tools", Keyboard.KEY_C);
		// TODO Auto-generated constructor stub
	}
	
	public void bestTool( int x, int y, int z ) {
        int blockId = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().theWorld.getBlockId( x, y, z );
        int bestSlot = 0;
        float f = 0.1F;
        for( int i1 = 36; i1 < 45; i1++ ) {
            try {
                ItemStack curSlot = CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.inventoryContainer.getSlot( i1 ).getStack( );
                if( curSlot.getStrVsBlock( Block.blocksList[ blockId ] ) > f ) {
                    bestSlot = i1 - 36;
                    f = curSlot.getStrVsBlock( Block.blocksList[ blockId ] );
                }
            } catch( Exception e ) {
            }
        }
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance().thePlayer.inventory.currentItem = bestSlot;
    }

	@Override
	public void tick( ) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEnable( ) {
		// TODO Auto-generated method stub
		CheatingEssentials.getCheatingEssentials().addToTick(this);
		CheatingEssentials.getCheatingEssentials().eventHandler.registerListener( EventBlockClick.class, this );
	}

	@Override
	public void onDisable( ) {
		// TODO Auto-generated method stub
		CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
		CheatingEssentials.getCheatingEssentials().eventHandler.unRegisterListener( EventBlockClick.class, this );
	}

	@Override
	public void onEvent(Event e) {
		// TODO Auto-generated method stub
		super.onEvent(e);
        
        if( e instanceof EventBlockClick ) {
            Vector3D coords = ( ( EventBlockClick ) e ).getCoords( );
            bestTool( ( int ) coords.getX( ), ( int ) coords.getY( ), ( int ) coords.getZ( ) );
        }
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}

}
