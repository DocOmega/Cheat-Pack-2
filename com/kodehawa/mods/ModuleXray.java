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

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleXray extends Mod implements Tickable
{
	public static ArrayList< Integer > xrayBlocks = new ArrayList< Integer >( );
    

    public ModuleXray( )
    {
        super(Mods.Xray);
        
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
        // TODO Auto-generated method stub
    }

    @Override
    public void onEnable()
    {
    	CheatingEssentials.getCheatingEssentials().addToTick(this);
        Vars.xray = true;
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance().renderGlobal.loadRenderers();
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance().gameSettings.gammaSetting = ModuleFullbright.Fullbright;
    }

    @Override
    public void onDisable()
    {
    	CheatingEssentials.getCheatingEssentials().removeFromCurrentTick(this);
        Vars.xray = false;
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance().gameSettings.gammaSetting = ModuleFullbright.Normalbright;
        CheatingEssentials.getCheatingEssentials().getMinecraftInstance().renderGlobal.loadRenderers();
    }
}
