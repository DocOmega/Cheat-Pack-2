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

import com.kodehawa.CheatingEssentials;
import com.kodehawa.util.Tickable;

public class ModuleXray extends Mod implements Tickable
{
    public CheatingEssentials cheatbase;
    private Minecraft mc;

    public ModuleXray(CheatingEssentials cb, Minecraft m)
    {
        super(Mods.Xray);
        cheatbase = cb;
        mc = m;
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
        cheatbase.addToTick(this);
        Vars.xray = true;
        mc.renderGlobal.loadRenderers();
        mc.gameSettings.gammaSetting = 15.0F;
        cheatbase.getUtils().addChatMessage(getActive());
        cheatbase.getUtils().addChatMessage("Good luck finding Diamonds! Disable Smooth Lighting.");
    }

    @Override
    public void onDisable()
    {
        cheatbase.removeFromCurrentTick(this);
        Vars.xray = false;
        mc.gameSettings.gammaSetting = 0.5F;
        mc.renderGlobal.loadRenderers();
        cheatbase.getUtils().addChatMessage(getActive());
    }
}
