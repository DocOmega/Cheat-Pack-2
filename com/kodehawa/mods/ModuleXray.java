/*
* Copyright (c) 2013 David Rubio
*
* Permission is hereby granted, free of charge, to any person obtaining a copy
* of this software and associated documentation files (the "Software"), to deal
* in the Software without restriction, including without limitation the rights
* to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
* copies of the Software, and to permit persons to whom the Software is
* furnished to do so, subject to the following conditions:
*
* The above copyright notice and this permission notice shall be included in
* all copies or substantial portions of the Software.
*
* THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
* IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
* FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
* AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
* LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
* OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
* THE SOFTWARE.
*/

package com.kodehawa.mods;

import java.util.ArrayList;

import net.minecraft.src.Minecraft;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.event.EventHandler;
import com.kodehawa.event.events.EventBlockRender;
import com.kodehawa.util.Tickable;

public class ModuleXray extends Mod implements Tickable
{
	public static ArrayList< Integer > xrayBlocks = new ArrayList< Integer >( );
    
   @ModuleInformation(
		   credits = "Godshawk, Kodehawa",
		   desc = "Allows to the player to see ores through everything",
		   name = "X-Ray")


    public ModuleXray( )
    {
        super("X-Ray", "Shows the hidden ores!", Keyboard.KEY_X);
        
        /**
         * Holy shit...
         */
      
        xrayBlocks.add( 8 );
        xrayBlocks.add( 9 );
        xrayBlocks.add( 10 );
        xrayBlocks.add( 11 );
        xrayBlocks.add( 14 );
        xrayBlocks.add( 15 );
        xrayBlocks.add( 16 );
        xrayBlocks.add( 89 );
        xrayBlocks.add( 57 );
        xrayBlocks.add( 73 );
        xrayBlocks.add( 74 );
        xrayBlocks.add( 152 );
        xrayBlocks.add( 153 );
        xrayBlocks.add( 56 );
        xrayBlocks.add( 41 );
        xrayBlocks.add( 42 );
        xrayBlocks.add( 133 );
        xrayBlocks.add( 129 );
        xrayBlocks.add( 137 );
        xrayBlocks.add( 120 );
        xrayBlocks.add( 97 );
        xrayBlocks.add( 88 );
        xrayBlocks.add( 89 );
        xrayBlocks.add( 112 );
        // TODO Auto-generated constructor stub
    }

    
    @Override
    public void tick()
    {
    	float[] brightness = CheatingEssentials.getMinecraftInstance().theWorld.provider.lightBrightnessTable;
        for(int i = 0; i < brightness.length; i++) {
           brightness[i] = 1.0F;
        }
        // TODO Auto-generated method stub
    }

    @Override
    public void onEnable()
    {
        	EventHandler.getInstance().registerListener( EventBlockRender.class, this );
            CheatingEssentials.getCheatingEssentials().getMinecraftInstance().renderGlobal.loadRenderers();
    }

    @Override
    public void onDisable()
    {
        EventHandler.getInstance().unRegisterListener( EventBlockRender.class, this );
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance().renderGlobal.loadRenderers();
        CheatingEssentials.getMinecraftInstance().theWorld.provider.registerWorld(Minecraft.getMinecraft().theWorld);

    }

	@Override
	public void onEvent(Event e) {
		// TODO Auto-generated method stub
		super.onEvent(e);
		
		if( e instanceof EventBlockRender ) {
            EventBlockRender rEvent = ( EventBlockRender ) e;
            if( rEvent.getType( ).equals( EventBlockRender.EventType.RENDER_XRAY ) ) {
                rEvent.setCancelled( isActive( ) ? true : false );
            }
        }
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}
}
