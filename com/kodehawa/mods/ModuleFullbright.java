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

import net.minecraft.src.Minecraft;

import org.lwjgl.input.Keyboard;

import com.kodehawa.CheatingEssentials;
import com.kodehawa.event.Event;
import com.kodehawa.util.Tickable;

public class ModuleFullbright extends Mod
{
	@ModuleInformation(
			credits = "Kodehawa",
			desc = "Allows to the player to see though the darkness without any troubles",
			name = "Fullbright")
	
    public ModuleFullbright( )
    {
        super("Full Bright", "I don't know the darkness", Keyboard.KEY_F);
        
    }
    
    
    @Override
    public void onEnable()
    {
    	float[] brightness = Minecraft.getMinecraft().theWorld.provider.lightBrightnessTable;
        for(int i = 0; i < brightness.length; i++) {
           brightness[i] = 1.0F;
        }
    }

    @Override
    public void onDisable()
    {
    	Minecraft.getMinecraft().theWorld.provider.registerWorld(Minecraft.getMinecraft().theWorld);
    }



	@Override
	public void onEvent(Event event) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public void render() {
		// TODO Auto-generated method stub
		
	}


 
}
